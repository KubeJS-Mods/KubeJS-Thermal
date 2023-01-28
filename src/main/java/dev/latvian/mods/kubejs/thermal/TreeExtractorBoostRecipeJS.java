package dev.latvian.mods.kubejs.thermal;

import dev.latvian.mods.kubejs.recipe.RecipeArguments;

/**
 * @author LatvianModder
 */
public class TreeExtractorBoostRecipeJS extends ThermalRecipeJS {
	@Override
	public void create(RecipeArguments args) {
		inputItems.add(parseIngredientItem(args.get(0)));
	}

	public TreeExtractorBoostRecipeJS boostMod(float f) {
		json.addProperty("boost_mod", f);
		save();
		return this;
	}

	public TreeExtractorBoostRecipeJS cycles(int c) {
		json.addProperty("cycles", c);
		save();
		return this;
	}

	@Override
	public void deserialize() {
		inputItems.add(parseIngredientItem(json.get("ingredient")));
	}

	@Override
	public void serialize() {
		if (serializeInputs) {
			json.add("ingredient", inputItems.get(0).toJson());
		}
	}
}
