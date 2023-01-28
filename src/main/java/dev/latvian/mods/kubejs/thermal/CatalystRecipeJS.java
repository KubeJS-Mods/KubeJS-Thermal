package dev.latvian.mods.kubejs.thermal;


import dev.latvian.mods.kubejs.recipe.RecipeArguments;

/**
 * @author LatvianModder
 */
public class CatalystRecipeJS extends SingleIngredientRecipeJS {
	@Override
	public void create(RecipeArguments args) {
		super.create(args);
		json.addProperty("primary_mod", 1F);
		json.addProperty("secondary_mod", 1F);
		json.addProperty("energy_mod", 1F);
		json.addProperty("min_chance", 0F);
		json.addProperty("use_chance", 1F);
	}

	public CatalystRecipeJS primaryMod(float f) {
		json.addProperty("primary_mod", f);
		save();
		return this;
	}

	public CatalystRecipeJS secondaryMod(float f) {
		json.addProperty("secondary_mod", f);
		save();
		return this;
	}

	public CatalystRecipeJS minChance(float f) {
		json.addProperty("min_chance", f);
		save();
		return this;
	}

	public CatalystRecipeJS useChance(float f) {
		json.addProperty("use_chance", f);
		save();
		return this;
	}
}
