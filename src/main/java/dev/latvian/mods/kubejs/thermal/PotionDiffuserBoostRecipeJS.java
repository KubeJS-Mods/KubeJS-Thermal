package dev.latvian.mods.kubejs.thermal;


/**
 * @author LatvianModder
 */
public class PotionDiffuserBoostRecipeJS extends SingleIngredientRecipeJS {
	public PotionDiffuserBoostRecipeJS amplifier(int a) {
		json.addProperty("amplifier", a);
		save();
		return this;
	}

	public PotionDiffuserBoostRecipeJS durationMod(float f) {
		json.addProperty("duration_mod", f);
		save();
		return this;
	}

	public PotionDiffuserBoostRecipeJS cycles(int c) {
		json.addProperty("cycles", c);
		save();
		return this;
	}
}
