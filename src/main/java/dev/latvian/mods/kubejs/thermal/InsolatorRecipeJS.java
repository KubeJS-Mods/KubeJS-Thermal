package dev.latvian.mods.kubejs.thermal;

import cofh.lib.fluid.FluidIngredient;
import cofh.lib.util.recipes.RecipeJsonUtils;
import com.google.gson.JsonArray;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LatvianModder
 */
public class InsolatorRecipeJS extends ThermalRecipeJS {
	public final List<Ingredient> inputItems = new ArrayList<>(1);
	public final List<FluidIngredient> inputFluids = new ArrayList<>(0);
	public final List<ItemStack> outputItems = new ArrayList<>(1);
	public final List<Float> outputItemChances = new ArrayList<>(1);
	public final List<FluidStack> outputFluids = new ArrayList<>(0);
	public String inKey = "";
	public String outKey = "";

	@Override
	public void create(RecipeArguments args) {
		inKey = "ingredients";
		outKey = "results";

		/*
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
		 */
	}

	public InsolatorRecipeJS xp(float e) {
		json.addProperty("xp", e);
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
		outKey = "";

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

		if (json.has(RecipeJsonUtils.RESULT)) {
			outKey = RecipeJsonUtils.RESULT;
			RecipeJsonUtils.parseOutputs(outputItems, outputItemChances, outputFluids, json.get(RecipeJsonUtils.RESULT));
		} else if (json.has(RecipeJsonUtils.RESULTS)) {
			outKey = RecipeJsonUtils.RESULTS;
			RecipeJsonUtils.parseOutputs(outputItems, outputItemChances, outputFluids, json.get(RecipeJsonUtils.RESULTS));
		} else if (json.has(RecipeJsonUtils.OUTPUT)) {
			outKey = RecipeJsonUtils.OUTPUT;
			RecipeJsonUtils.parseOutputs(outputItems, outputItemChances, outputFluids, json.get(RecipeJsonUtils.OUTPUT));
		} else if (json.has(RecipeJsonUtils.OUTPUTS)) {
			outKey = RecipeJsonUtils.OUTPUTS;
			RecipeJsonUtils.parseOutputs(outputItems, outputItemChances, outputFluids, json.get(RecipeJsonUtils.OUTPUTS));
		}
	}

	@Override
	public void serialize() {
		if (serializeOutputs && !outKey.isEmpty()) {
			JsonArray out = new JsonArray();

			for (var stack : outputItems) {
				out.add(itemToJson(stack));
			}

			for (var fluid : outputFluids) {
				out.add(fluidToJson(fluid));
			}

			json.add(outKey, out);
		}

		if (serializeInputs && !inKey.isEmpty()) {
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
		for (var out : outputItems) {
			if (match.contains(out)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
		boolean changed = false;

		for (int i = 0; i < outputItems.size(); ++i) {
			var out = outputItems.get(i);

			if (match.contains(out)) {
				outputItems.set(i, transformer.transform(this, match, out, with));
				changed = true;
			}
		}

		return changed;
	}
}
