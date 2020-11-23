package dev.latvian.kubejs.thermal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.kubejs.item.ingredient.IngredientStackJS;
import dev.latvian.kubejs.recipe.RecipeJS;

/**
 * @author LatvianModder
 */
public abstract class ThermalRecipeJS extends RecipeJS
{
	@Override
	public JsonElement serializeIngredientStack(IngredientStackJS in)
	{
		JsonObject o = new JsonObject();
		o.addProperty("count", in.getCount());
		o.add("value", in.ingredient.toJson());
		return o;
	}

	public ThermalRecipeJS energy(int e)
	{
		json.addProperty("energy", e);
		save();
		return this;
	}

	public ThermalRecipeJS energyMod(float e)
	{
		json.addProperty("energy_mod", e);
		save();
		return this;
	}
}
