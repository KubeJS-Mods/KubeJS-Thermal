package dev.latvian.mods.kubejs.thermal;

import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class SingleIngredientRecipeJS extends ThermalRecipeJS {
	public Ingredient ingredient;

	@Override
	public void create(RecipeArguments args) {
		ingredient = parseItemInput(args.get(0));
	}

	@Override
	public void deserialize() {
		ingredient = parseItemInput(json.get("ingredient"));
	}

	@Override
	public void serialize() {
		if (serializeInputs) {
			json.add("ingredient", ingredient.toJson());
		}
	}

	@Override
	public boolean hasInput(IngredientMatch match) {
		return match.contains(ingredient);
	}

	@Override
	public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
		if (match.contains(ingredient)) {
			ingredient = transformer.transform(this, match, ingredient, with);
			return true;
		} else {
			return false;
		}
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
