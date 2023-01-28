package dev.latvian.mods.kubejs.thermal;

import cofh.lib.fluid.FluidIngredient;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.KubeJSRegistries;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.item.ingredient.IngredientStack;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author LatvianModder
 */
public abstract class ThermalRecipeJS extends RecipeJS {
	@Override
	public JsonElement serializeIngredientStack(IngredientStack in) {
		JsonObject o = new JsonObject();
		o.addProperty("count", in.getCount());
		o.add("value", in.getIngredient().toJson());
		return o;
	}

	public ThermalRecipeJS energy(int e) {
		json.addProperty("energy", e);
		save();
		return this;
	}

	public ThermalRecipeJS energyMod(float e) {
		json.addProperty("energy_mod", e);
		save();
		return this;
	}

	public JsonElement fluidToJson(FluidStack fluidStack) {
		JsonObject o = new JsonObject();
		o.addProperty("amount", fluidStack.getAmount());
		o.addProperty("fluid", KubeJSRegistries.fluids().getId(fluidStack.getFluid()).toString());

		if (fluidStack.hasTag()) {
			o.addProperty("nbt", fluidStack.getTag().toString());
		}

		return o;
	}

	public FluidIngredient fluidInputFrom(FluidStackJS fs) {
		return FluidIngredient.of(fluidOutputFrom(fs));
	}

	public FluidStack fluidOutputFrom(FluidStackJS fs) {
		return new FluidStack(fs.getFluid(), (int) fs.getAmount(), fs.getNbt());
	}
}
