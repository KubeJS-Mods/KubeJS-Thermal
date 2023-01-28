package dev.latvian.mods.kubejs.thermal;


/**
 * @author LatvianModder
 */
public class CatalystRecipeJS extends SingleIngredientRecipeJS {
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
