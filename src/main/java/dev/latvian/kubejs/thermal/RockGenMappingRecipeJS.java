package dev.latvian.kubejs.thermal;

import dev.latvian.kubejs.util.ListJS;

/**
 * @author LatvianModder
 */
public class RockGenMappingRecipeJS extends ThermalRecipeJS {
	@Override
	public void create(ListJS args) {
		outputItems.add(parseResultItem(args.get(0)));
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
		outputItems.add(parseResultItem(json.get("result")));
	}

	@Override
	public void serialize() {
		if (serializeOutputs) {
			json.add("result", outputItems.get(0).toResultJson());
		}
	}
}