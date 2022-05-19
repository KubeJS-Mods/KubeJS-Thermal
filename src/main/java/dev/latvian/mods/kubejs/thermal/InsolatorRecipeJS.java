package dev.latvian.mods.kubejs.thermal;

import cofh.lib.fluid.FluidIngredient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import dev.latvian.mods.kubejs.recipe.RecipeExceptionJS;
import dev.latvian.mods.kubejs.util.ListJS;

import java.util.ArrayList;

/**
 * @author LatvianModder
 */
public class InsolatorRecipeJS extends ThermalRecipeJS {
	public ArrayList<FluidIngredient> inputFluids = new ArrayList<>();
	public ArrayList<FluidStackJS> outputFluids = new ArrayList<>();
	public String inKey = "";
	public String outKey = "";

	@Override
	public void create(ListJS args) {
		inKey = "ingredients";
		outKey = "results";

		for (Object o : ListJS.orSelf(args.get(0))) {
			if (o instanceof FluidStackJS) {
				outputFluids.add((FluidStackJS) o);
			} else {
				outputItems.add(parseResultItem(o));
			}
		}

		for (Object o : ListJS.orSelf(args.get(1))) {
			if (o instanceof FluidStackJS) {
				inputFluids.add(fluidFrom((FluidStackJS) o));
			} else {
				inputItems.add(parseIngredientItem(o));
			}
		}

		if (outputItems.isEmpty() && outputFluids.isEmpty()) {
			throw new RecipeExceptionJS("Thermal recipe can't have no result!");
		}

		if (inputItems.isEmpty() && inputFluids.isEmpty()) {
			throw new RecipeExceptionJS("Thermal recipe can't have no ingredients!");
		}
	}

	public InsolatorRecipeJS experience(float e) {
		json.addProperty("experience", e);
		return this;
	}

	public InsolatorRecipeJS minTicks(int i) {
		json.addProperty("min_ticks", i);
		return this;
	}

	public InsolatorRecipeJS water(int e) {
		json.addProperty("water", e);
		return this;
	}

	public InsolatorRecipeJS waterMod(float e) {
		json.addProperty("water_mod", e);
		return this;
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

		outKey = "";

		if (json.has("result")) {
			outKey = "result";
		} else if (json.has("results")) {
			outKey = "results";
		} else if (json.has("output")) {
			outKey = "output";
		} else if (json.has("outputs")) {
			outKey = "outputs";
		}

		if (!outKey.isEmpty()) {
			JsonElement element = json.get(outKey);

			if (element != null) {
				JsonArray array;

				if (element.isJsonArray()) {
					array = element.getAsJsonArray();
				} else {
					array = new JsonArray();
					array.add(element);
				}

				for (JsonElement e : array) {
					if (e.getAsJsonObject().has("fluid")) {
						outputFluids.add(FluidStackJS.fromJson(e.getAsJsonObject()));
					} else {
						outputItems.add(parseResultItem(e));
					}
				}
			}
		}
	}

	@Override
	public void serialize() {
		if (serializeOutputs) {
			JsonArray out = new JsonArray();

			for (ItemStackJS stack : outputItems) {
				out.add(stack.toResultJson());
			}

			for (FluidStackJS fluid : outputFluids) {
				out.add(fluid.toJson());
			}

			json.add(outKey, out);
		}

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
