package dev.latvian.kubejs.thermal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.kubejs.fluid.FluidStackJS;
import dev.latvian.kubejs.item.ItemStackJS;
import dev.latvian.kubejs.item.ingredient.IngredientJS;
import dev.latvian.kubejs.item.ingredient.IngredientStackJS;
import dev.latvian.kubejs.recipe.RecipeExceptionJS;
import dev.latvian.kubejs.recipe.RecipeJS;
import dev.latvian.kubejs.util.ListJS;

import java.util.ArrayList;

/**
 * @author LatvianModder
 */
public class ThermalRecipeJS extends RecipeJS
{
	public final int defaultEnergy;
	public int energy;
	public float experience = 0.0F;
	public int minTicks = -1;
	public ArrayList<FluidStackJS> inputFluids = new ArrayList<>();
	public ArrayList<FluidStackJS> outputFluids = new ArrayList<>();
	public String inKey = "";
	public String outKey = "";

	public ThermalRecipeJS(int d)
	{
		defaultEnergy = d;
		energy = defaultEnergy;
	}

	@Override
	public void create(ListJS listJS)
	{
		inKey = "ingredients";
		outKey = "results";

		if (listJS.size() < 2)
		{
			throw new RecipeExceptionJS("Thermal recipes require at least 2 arguments - output and input!");
		}

		for (Object o : ListJS.orSelf(listJS.get(0)))
		{
			if (o instanceof FluidStackJS)
			{
				outputFluids.add((FluidStackJS) o);
			}
			else
			{
				outputItems.add(parseResultItem(o));
			}
		}

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

		if (outputItems.isEmpty() && outputFluids.isEmpty())
		{
			throw new RecipeExceptionJS("Thermal recipe can't have no result!");
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

		outKey = "";

		if (json.has("result"))
		{
			outKey = "result";
		}
		else if (json.has("results"))
		{
			outKey = "results";
		}
		else if (json.has("output"))
		{
			outKey = "output";
		}
		else if (json.has("outputs"))
		{
			outKey = "outputs";
		}

		if (!outKey.isEmpty())
		{
			JsonElement element = json.get(outKey);

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
					if (e.getAsJsonObject().has("fluid"))
					{
						outputFluids.add(FluidStackJS.fromJson(e.getAsJsonObject()));
					}
					else
					{
						outputItems.add(parseResultItem(e));
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

		if (json.has("experience"))
		{
			experience = json.get("experience").getAsFloat();
		}

		if (json.has("min_ticks"))
		{
			minTicks = json.get("min_ticks").getAsInt();
		}
	}

	@Override
	public void serialize()
	{
		if (energy != defaultEnergy)
		{
			json.addProperty("energy", energy);
		}

		if (experience > 0F)
		{
			json.addProperty("experience", experience);
		}

		if (minTicks != -1)
		{
			json.addProperty("min_ticks", minTicks);
		}

		if (serializeOutputs)
		{
			JsonArray out = new JsonArray();

			for (ItemStackJS stack : outputItems)
			{
				out.add(stack.toResultJson());
			}

			for (FluidStackJS fluid : outputFluids)
			{
				out.add(fluid.toJson());
			}

			json.add(outKey, out);
		}

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