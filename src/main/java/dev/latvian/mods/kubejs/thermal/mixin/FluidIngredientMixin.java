package dev.latvian.mods.kubejs.thermal.mixin;

import cofh.lib.common.fluid.FluidIngredient;
import dev.architectury.hooks.fluid.forge.FluidStackHooksForge;
import dev.latvian.mods.kubejs.fluid.FluidLike;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;

@Mixin(FluidIngredient.class)
public abstract class FluidIngredientMixin implements InputFluid {
	@Shadow
	private int amount;

	@Shadow
	@Final
	private FluidIngredient.IFluidList[] values;

	@Shadow
	public abstract boolean isEmpty();

	@Override
	public long kjs$getAmount() {
		return isEmpty() ? 0L : amount;
	}

	@Override
	public boolean kjs$isEmpty() {
		return isEmpty();
	}

	@Override
	public FluidLike kjs$copy(long amount) {
		if (amount != this.kjs$getAmount()) {
			var in = FluidIngredient.fromValues(Arrays.stream(values));

			if (!in.isEmpty()) {
				in.setAmount((int) amount);
			}

			return (FluidLike) in;
		}

		return this;
	}

	@Override
	public boolean matches(FluidLike other) {
		if (other instanceof FluidStackJS fs) {
			return ((FluidIngredient) (Object) this).test(FluidStackHooksForge.toForge(fs.getFluidStack()));
		}

		return false;
	}
}
