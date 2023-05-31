package dev.latvian.mods.kubejs.thermal;

import cofh.lib.fluid.FluidIngredient;
import cofh.lib.util.recipes.RecipeJsonUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import net.minecraftforge.fluids.FluidStack;

public class ThermalRecipeJS extends RecipeJS {
	@Override
	public boolean inputFluidHasPriority(Object from) {
		return from instanceof InputFluid || from instanceof JsonObject j && (j.has("fluid") || j.has("fluid_tag"));
	}

	@Override
	public InputFluid readInputFluid(Object from) {
		if (from instanceof ThermalInputFluid fluid) {
			return fluid;
		} else if (from instanceof FluidIngredient fluid) {
			return new ThermalInputFluid(fluid);
		} else if (from instanceof JsonElement j) {
			return new ThermalInputFluid(RecipeJsonUtils.parseFluidIngredient(j));
		} else if (from instanceof FluidStackJS fluid) {
			return new ThermalInputFluid(FluidIngredient.of(new FluidStack(fluid.getFluid(), (int) fluid.getAmount(), fluid.getNbt())));
		} else if (from instanceof FluidStack fluid) {
			return new ThermalInputFluid(FluidIngredient.of(fluid));
		} else {
			return ThermalInputFluid.EMPTY;
		}
	}

	@Override
	public JsonElement writeInputFluid(InputFluid value) {
		if (value instanceof ThermalInputFluid fluid) {
			return fluid.ingredient().toJson();
		} else if (value instanceof FluidIngredient fluid) {
			return fluid.toJson();
		} else {
			return ThermalInputFluid.EMPTY.ingredient().toJson();
		}
	}
}
