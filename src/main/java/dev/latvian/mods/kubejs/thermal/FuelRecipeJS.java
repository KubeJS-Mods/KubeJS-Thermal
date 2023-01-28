package dev.latvian.mods.kubejs.thermal;

import cofh.lib.fluid.FluidIngredient;
import cofh.lib.util.recipes.RecipeJsonUtils;
import com.google.gson.JsonArray;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeExceptionJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LatvianModder
 */
public class FuelRecipeJS extends ThermalRecipeJS { // ThermalFuel
	public final List<Ingredient> inputItems = new ArrayList<>(1);
	public final List<FluidIngredient> inputFluids = new ArrayList<>(0);
	public String inKey = "";

	@Override
	public void create(RecipeArguments args) {
		inKey = "ingredients";

		/*
		for (Object o : ListJS.orSelf(args.get(0))) {
			if (o instanceof FluidStackJS) {
				inputFluids.add(fluidFrom((FluidStackJS) o));
			} else {
				inputItems.add(parseIngredientItem(o));
			}
		}
		 */

		json.addProperty("energy", 100000);

		if (inputItems.isEmpty() && inputFluids.isEmpty()) {
			throw new RecipeExceptionJS("Thermal recipe can't have no ingredients!");
		}
	}

	@Override
	public void deserialize() {
		inKey = "";

		if (json.has(RecipeJsonUtils.INGREDIENT)) {
			inKey = RecipeJsonUtils.INGREDIENT;
			RecipeJsonUtils.parseInputs(inputItems, inputFluids, json.get(RecipeJsonUtils.INGREDIENT));
		} else if (json.has(RecipeJsonUtils.INGREDIENTS)) {
			inKey = RecipeJsonUtils.INGREDIENTS;
			RecipeJsonUtils.parseInputs(inputItems, inputFluids, json.get(RecipeJsonUtils.INGREDIENTS));
		} else if (json.has(RecipeJsonUtils.INPUT)) {
			inKey = RecipeJsonUtils.INPUT;
			RecipeJsonUtils.parseInputs(inputItems, inputFluids, json.get(RecipeJsonUtils.INPUT));
		} else if (json.has(RecipeJsonUtils.INPUTS)) {
			inKey = RecipeJsonUtils.INPUTS;
			RecipeJsonUtils.parseInputs(inputItems, inputFluids, json.get(RecipeJsonUtils.INPUTS));
		}
	}

	@Override
	public void serialize() {
		if (serializeInputs) {
			JsonArray in = new JsonArray();

			for (var ingredient : inputItems) {
				in.add(ingredient.toJson());
			}

			for (var fluid : inputFluids) {
				in.add(fluid.toJson());
			}

			json.add(inKey, in);
		}
	}

	@Override
	public boolean hasInput(IngredientMatch match) {
		for (var in : inputItems) {
			if (match.contains(in)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
		boolean changed = false;

		for (int i = 0; i < inputItems.size(); ++i) {
			var in = inputItems.get(i);

			if (match.contains(in)) {
				inputItems.set(i, transformer.transform(this, match, in, with));
				changed = true;
			}
		}

		return changed;
	}

	@Override
	public boolean hasOutput(IngredientMatch match) {
		return false;
	}

	@Override
	public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
		return false;
	}
}
