package dev.latvian.kubejs.thermal;


import dev.latvian.mods.kubejs.util.ListJS;

/**
 * @author LatvianModder
 */
public class CatalystRecipeJS extends ThermalRecipeJS {
	@Override
	public void create(ListJS args) {
		inputItems.add(parseIngredientItem(args.get(0)));
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