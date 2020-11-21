package dev.latvian.kubejs.thermal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.kubejs.fluid.FluidStackJS;
import dev.latvian.kubejs.item.ingredient.IngredientJS;
import dev.latvian.kubejs.item.ingredient.IngredientStackJS;
import dev.latvian.kubejs.recipe.RecipeExceptionJS;
import dev.latvian.kubejs.recipe.RecipeJS;
import dev.latvian.kubejs.util.ListJS;

import java.util.ArrayList;

/**
 * @author LatvianModder
 */
public class ThermalFuelRecipeJS extends RecipeJS
{
	public final int defaultEnergy;
	public int energy;
	public ArrayList<FluidStackJS> inputFluids = new ArrayList<>();
	public String inKey = "";

	public ThermalFuelRecipeJS(int d)
	{
		defaultEnergy = d;
		energy = defaultEnergy;
	}

	@Override
	public void create(ListJS listJS)
	{
		inKey = "ingredients";

		if (listJS.size() < 2)
		{
			throw new RecipeExceptionJS("Thermal fuel recipes require at least 2 arguments - energy and input!");
		}

		energy = ((Number) listJS.get(0)).intValue();

		for (Object o : ListJS.orSelf(listJS.get(1)))
		{
			if (o instanceof FluidStackJS)
			{
				inputFluids.add((FluidStackJS) o);
			}
			else
			{
				inputItems.add(parseIngredientItem(o));
			}
		}

		if (inputItems.isEmpty() && inputFluids.isEmpty())
		{
			throw new RecipeExceptionJS("Thermal recipe can't have no ingredients!");
		}
	}

	@Override
	public void deserialize()
	{
		inKey = "";

		if (json.has("ingredient"))
		{
			inKey = "ingredient";
		}
		else if (json.has("ingredients"))
		{
			inKey = "ingredients";
		}
		else if (json.has("input"))
		{
			inKey = "input";
		}
		else if (json.has("inputs"))
		{
			inKey = "inputs";
		}

		if (!inKey.isEmpty())
		{
			JsonElement element = json.get(inKey);

			if (element != null)
			{
				JsonArray array;

				if (element.isJsonArray())
				{
					array = element.getAsJsonArray();
				}
				else
				{
					array = new JsonArray();
					array.add(element);
				}

				for (JsonElement e : array)
				{
					if (e.isJsonObject() && e.getAsJsonObject().has("fluid"))
					{
						inputFluids.add(FluidStackJS.fromJson(e.getAsJsonObject()));
					}
					else
					{
						inputItems.add(parseIngredientItem(e));
					}
				}
			}
		}

		if (json.has("energy"))
		{
			energy = json.get("energy").getAsInt();
		}

		if (json.has("energy_mod"))
		{
			energy = (int) ((float) energy * json.get("energy_mod").getAsFloat());
		}
	}

	@Override
	public void serialize()
	{
		json.addProperty("energy", energy);

		if (serializeInputs)
		{
			JsonArray in = new JsonArray();

			for (IngredientJS ingredient : inputItems)
			{
				in.add(ingredient.toJson());
			}

			for (FluidStackJS fluid : inputFluids)
			{
				in.add(fluid.toJson());
			}

			json.add(inKey, in);
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