package dev.latvian.kubejs.thermal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import dev.latvian.kubejs.fluid.FluidStackJS;
import dev.latvian.kubejs.item.ItemStackJS;
import dev.latvian.kubejs.item.ingredient.IngredientJS;
import dev.latvian.kubejs.recipe.RecipeExceptionJS;
import dev.latvian.kubejs.util.ListJS;

import java.util.ArrayList;

/**
 * @author LatvianModder
 */
public class ThermalInsolatorRecipeJS extends ThermalRecipeJS
{
	public ArrayList<FluidStackJS> inputFluids = new ArrayList<>();
	public ArrayList<FluidStackJS> outputFluids = new ArrayList<>();
	public String inKey = "";
	public String outKey = "";

	@Override
	public void create(ListJS args)
	{
		inKey = "ingredients";
		outKey = "results";

		for (Object o : ListJS.orSelf(args.get(0)))
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

		for (Object o : ListJS.orSelf(args.get(1)))
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

	public ThermalInsolatorRecipeJS energy(int e)
	{
		json.addProperty("energy", e);
		return this;
	}

	public ThermalInsolatorRecipeJS energyMod(float e)
	{
		json.addProperty("energy_mod", e);
		return this;
	}

	public ThermalInsolatorRecipeJS experience(float e)
	{
		json.addProperty("experience", e);
		return this;
	}

	public ThermalInsolatorRecipeJS minTicks(int i)
	{
		json.addProperty("min_ticks", i);
		return this;
	}

	public ThermalInsolatorRecipeJS water(int e)
	{
		json.addProperty("water", e);
		return this;
	}

	public ThermalInsolatorRecipeJS waterMod(float e)
	{
		json.addProperty("water_mod", e);
		return this;
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
	}

	@Override
	public void serialize()
	{
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
}