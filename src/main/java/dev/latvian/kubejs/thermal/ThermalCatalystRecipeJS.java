package dev.latvian.kubejs.thermal;

import dev.latvian.kubejs.util.ListJS;

/**
 * @author LatvianModder
 */
public class ThermalCatalystRecipeJS extends ThermalRecipeJS
{
	@Override
	public void create(ListJS args)
	{
		inputItems.add(parseIngredientItem(args.get(0)));
		json.addProperty("primary_mod", 1F);
		json.addProperty("secondary_mod", 1F);
		json.addProperty("energy_mod", 1F);
		json.addProperty("min_chance", 0F);
		json.addProperty("use_chance", 1F);
	}

	public ThermalCatalystRecipeJS primaryMod(float f)
	{
		json.addProperty("primary_mod", f);
		return this;
	}

	public ThermalCatalystRecipeJS secondaryMod(float f)
	{
		json.addProperty("secondary_mod", f);
		return this;
	}

	public ThermalCatalystRecipeJS energyMod(float f)
	{
		json.addProperty("energy_mod", f);
		return this;
	}

	public ThermalCatalystRecipeJS minChance(float f)
	{
		json.addProperty("min_chance", f);
		return this;
	}

	public ThermalCatalystRecipeJS useChance(float f)
	{
		json.addProperty("use_chance", f);
		return this;
	}

	@Override
	public void deserialize()
	{
		inputItems.add(parseIngredientItem(json.get("ingredient")));
	}

	@Override
	public void serialize()
	{
		if (serializeInputs)
		{
			json.add("ingredient", inputItems.get(0).toJson());
		}
	}
}