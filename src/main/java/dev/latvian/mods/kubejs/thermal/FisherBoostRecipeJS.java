package dev.latvian.mods.kubejs.thermal;

/**
 * @author LatvianModder
 */
public class FisherBoostRecipeJS extends SingleIngredientRecipeJS {
	public FisherBoostRecipeJS lootTable(String id) {
		json.addProperty("loot_table", id);
		save();
		return this;
	}

	public FisherBoostRecipeJS output(float f) {
		json.addProperty("output", f);
		save();
		return this;
	}

	public FisherBoostRecipeJS useChance(float c) {
		json.addProperty("use_chance", c);
		save();
		return this;
	}
}
