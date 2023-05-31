package dev.latvian.mods.kubejs.thermal;

import cofh.lib.fluid.FluidIngredient;
import dev.latvian.mods.kubejs.fluid.InputFluid;

public record ThermalInputFluid(FluidIngredient ingredient) implements InputFluid {
	public static final ThermalInputFluid EMPTY = new ThermalInputFluid(FluidIngredient.EMPTY);

	@Override
	public boolean isInputEmpty() {
		return ingredient.isEmpty();
	}
}
