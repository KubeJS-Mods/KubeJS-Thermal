package dev.latvian.kubejs.thermal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.kubejs.item.ingredient.IngredientStackJS;
import dev.latvian.kubejs.recipe.RecipeExceptionJS;
import dev.latvian.kubejs.recipe.RecipeJS;
import dev.latvian.kubejs.util.ListJS;

/**
 * @author LatvianModder
 */
public class ThermalCatalystRecipeJS extends RecipeJS
{
	public float primaryMod;
	public float secondaryMod;
	public float energyMod;
	public float minChance;
	public float useChance;

	public ThermalCatalystRecipeJS()
	{
		primaryMod = 1F;
		secondaryMod = 1F;
		energyMod = 1F;
		minChance = 0F;
		useChance = 1F;
	}

	@Override
	public void create(ListJS listJS)
	{
		if (listJS.size() < 1)
		{
			throw new RecipeExceptionJS("Thermal catalyst recipes require at least 1 argument - ingredient!");
		}

		inputItems.add(parseIngredientItem(listJS.get(0)));
	}

	public ThermalCatalystRecipeJS primaryMod(float f)
	{
		primaryMod = f;
		return this;
	}

	public ThermalCatalystRecipeJS secondaryMod(float f)
	{
		secondaryMod = f;
		return this;
	}

	public ThermalCatalystRecipeJS energyMod(float f)
	{
		energyMod = f;
		return this;
	}

	public ThermalCatalystRecipeJS minChance(float f)
	{
		minChance = f;
		return this;
	}

	public ThermalCatalystRecipeJS useChance(float f)
	{
		useChance = f;
		return this;
	}

	@Override
	public void deserialize()
	{
		inputItems.add(parseIngredientItem(json.get("ingredient")));

		if (json.has("primary_mod"))
		{
			primaryMod = json.get("primary_mod").getAsFloat();
		}

		if (json.has("secondary_mod"))
		{
			secondaryMod = json.get("secondary_mod").getAsFloat();
		}

		if (json.has("energy_mod"))
		{
			energyMod = json.get("energy_mod").getAsFloat();
		}

		if (json.has("min_chance"))
		{
			minChance = json.get("min_chance").getAsFloat();
		}

		if (json.has("use_chance"))
		{
			useChance = json.get("use_chance").getAsFloat();
		}

	}

	@Override
	public void serialize()
	{
		json.addProperty("primary_mod", primaryMod);
		json.addProperty("secondary_mod", secondaryMod);
		json.addProperty("energy_mod", energyMod);
		json.addProperty("min_chance", minChance);
		json.addProperty("use_chance", useChance);

		if (serializeInputs)
		{
			json.add("ingredient", inputItems.get(0).toJson());
		}
	}

	@Override
	public JsonElement serializeIngredientStack(IngredientStackJS in)
	{
		JsonObject o = new JsonObject();
		o.addProperty("count", in.getCount());
		o.add("value", in.ingredient.toJson());
		return o;
	}
}