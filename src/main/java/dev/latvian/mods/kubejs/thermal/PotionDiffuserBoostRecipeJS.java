package dev.latvian.mods.kubejs.thermal;


import dev.latvian.mods.kubejs.util.ListJS;

/**
 * @author LatvianModder
 */
public class PotionDiffuserBoostRecipeJS extends ThermalRecipeJS {
	@Override
	public void create(ListJS args) {
		inputItems.add(parseIngredientItem(args.get(0)));
	}

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
