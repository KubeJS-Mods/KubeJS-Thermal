package dev.latvian.mods.kubejs.thermal;

import dev.latvian.mods.kubejs.util.ListJS;

/**
 * @author LatvianModder
 */
public class FisherBoostRecipeJS extends ThermalRecipeJS {
	@Override
	public void create(ListJS args) {
		inputItems.add(parseIngredientItem(args.get(0)));
	}

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
