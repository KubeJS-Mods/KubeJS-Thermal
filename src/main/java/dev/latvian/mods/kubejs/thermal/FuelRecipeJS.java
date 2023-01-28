package dev.latvian.mods.kubejs.thermal;

import cofh.lib.fluid.FluidIngredient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeExceptionJS;
import dev.latvian.mods.kubejs.util.ListJS;

import java.util.ArrayList;

/**
 * @author LatvianModder
 */
public class FuelRecipeJS extends ThermalRecipeJS { // ThermalFuel
	public ArrayList<FluidIngredient> inputFluids = new ArrayList<>();
	public String inKey = "";

	@Override
	public void create(RecipeArguments args) {
		inKey = "ingredients";

		for (Object o : ListJS.orSelf(args.get(0))) {
			if (o instanceof FluidStackJS) {
				inputFluids.add(fluidFrom((FluidStackJS) o));
			} else {
				inputItems.add(parseIngredientItem(o));
			}
		}

		json.addProperty("energy", 100000);

		if (inputItems.isEmpty() && inputFluids.isEmpty()) {
			throw new RecipeExceptionJS("Thermal recipe can't have no ingredients!");
		}
	}

	@Override
	public void deserialize() {
		inKey = "";

		if (json.has("ingredient")) {
			inKey = "ingredient";
		} else if (json.has("ingredients")) {
			inKey = "ingredients";
		} else if (json.has("input")) {
			inKey = "input";
		} else if (json.has("inputs")) {
			inKey = "inputs";
		}

		if (!inKey.isEmpty()) {
			JsonElement element = json.get(inKey);

			if (element != null) {
				JsonArray array;

				if (element.isJsonArray()) {
					array = element.getAsJsonArray();
				} else {
					array = new JsonArray();
					array.add(element);
				}

				for (JsonElement e : array) {
					if (e.isJsonObject() && (e.getAsJsonObject().has("fluid") || e.getAsJsonObject().has("fluid_tag"))) {
						inputFluids.add(FluidIngredient.fromJson(e));
					} else {
						inputItems.add(parseIngredientItem(e));
					}
				}
			}
		}
	}

	@Override
	public void serialize() {
		if (serializeInputs) {
			JsonArray in = new JsonArray();

			for (IngredientJS ingredient : inputItems) {
				in.add(ingredient.toJson());
			}

			for (FluidIngredient fluid : inputFluids) {
				in.add(fluid.toJson());
			}

			json.add(inKey, in);
		}
	}
}
