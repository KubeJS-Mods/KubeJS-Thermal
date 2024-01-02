package dev.latvian.mods.kubejs.thermal;

import cofh.lib.common.fluid.FluidIngredient;
import cofh.lib.util.recipes.RecipeJsonUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.architectury.hooks.fluid.forge.FluidStackHooksForge;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.util.ListJS;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;

public class ThermalRecipeJS extends RecipeJS {
	@Override
	public InputItem readInputItem(Object from) {
		if (from instanceof JsonObject j) {
			Ingredient ingredient;
			int amount = 1;

			if (j.has("value")) {
				ingredient = Ingredient.fromJson(j.get("value"));
			} else {
				ingredient = Ingredient.fromJson(j);
			}

			if (j.has("count")) {
				amount = j.get("count").getAsInt();
			} else if (j.has("amount")) {
				amount = j.get("amount").getAsInt();
			}

			return InputItem.of(ingredient, amount);
		}

		return super.readInputItem(from);
	}

	@Override
	public JsonElement writeInputItem(InputItem value) {
		if (value.count > 1) {
			var json = new JsonObject();
			json.add("value", value.ingredient.toJson());
			json.addProperty("count", value.count);
			return json;
		} else {
			return value.ingredient.toJson();
		}
	}

	@Override
	public boolean inputFluidHasPriority(Object from) {
		return from instanceof InputFluid || from instanceof JsonObject j && (j.has("fluid") || j.has("fluid_tag"));
	}

	@Override
	public InputFluid readInputFluid(Object from) {
		if (from instanceof InputFluid input) {
			if (input instanceof FluidIngredient) {
				return input;
			} else if (input instanceof FluidStackJS fluid) {
				return (InputFluid) FluidIngredient.of(FluidStackHooksForge.toForge(fluid.getFluidStack()));
			}
		} else if (from instanceof JsonElement j) {
			return (InputFluid) RecipeJsonUtils.parseFluidIngredient(j);
		} else if (from instanceof FluidStack fluid) {
			return (InputFluid) FluidIngredient.of(fluid);
		} else {
			var list = ListJS.orSelf(from);
			var fluidStacks = new ArrayList<FluidStack>();
			for (var element : list) {
				var fluid = FluidStackJS.of(element);
				if (!fluid.kjs$isEmpty()) {
					fluidStacks.add(FluidStackHooksForge.toForge(fluid.getFluidStack()));
				}
			}
			if (!fluidStacks.isEmpty()) {
				return (InputFluid) FluidIngredient.of(fluidStacks.stream());
			}
		}

		return (InputFluid) FluidIngredient.EMPTY;
	}

	@Override
	public JsonElement writeInputFluid(InputFluid value) {
		if (value instanceof FluidIngredient fluid) {
			return fluid.toJson();
		} else {
			return FluidIngredient.EMPTY.toJson();
		}
	}
}
