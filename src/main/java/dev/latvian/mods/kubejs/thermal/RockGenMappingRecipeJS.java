package dev.latvian.mods.kubejs.thermal;

import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * @author LatvianModder
 */
public class RockGenMappingRecipeJS extends ThermalRecipeJS {
	public ItemStack result;

	@Override
	public void create(RecipeArguments args) {
		result = parseItemOutput(args.get(0));
	}

	public RockGenMappingRecipeJS below(String id) {
		json.addProperty("below", id);
		save();
		return this;
	}

	public RockGenMappingRecipeJS adjacent(String id) {
		json.addProperty("adjacent", id);
		save();
		return this;
	}

	public RockGenMappingRecipeJS adjacent(int t) {
		json.addProperty("time", t);
		save();
		return this;
	}

	@Override
	public void deserialize() {
		result = parseItemOutput(json.get("result"));
	}

	@Override
	public void serialize() {
		if (serializeOutputs) {
			json.add("result", itemToJson(result));
		}
	}

	@Override
	public boolean hasInput(IngredientMatch match) {
		return false;
	}

	@Override
	public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
		return false;
	}

	@Override
	public boolean hasOutput(IngredientMatch match) {
		return match.contains(result);
	}

	@Override
	public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
		if (match.contains(result)) {
			result = transformer.transform(this, match, result, with);
			return true;
		}

		return false;
	}
}
