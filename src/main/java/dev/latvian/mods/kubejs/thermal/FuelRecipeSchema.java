package dev.latvian.mods.kubejs.thermal;

import cofh.lib.util.recipes.RecipeJsonUtils;
import com.mojang.datafixers.util.Either;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface FuelRecipeSchema { // ThermalFuel
	RecipeKey<Either<InputFluid, InputItem>[]> INPUT = FluidComponents.INPUT_OR_ITEM_ARRAY.key(RecipeJsonUtils.INGREDIENTS).alt(RecipeJsonUtils.INGREDIENT, RecipeJsonUtils.INPUT, RecipeJsonUtils.INPUTS);
	RecipeKey<Integer> ENERGY = NumberComponent.INT.key(RecipeJsonUtils.ENERGY).optional(0);
	RecipeKey<Float> ENERGY_MOD = NumberComponent.FLOAT.key(RecipeJsonUtils.ENERGY_MOD).optional(1F).exclude().preferred("energyMod");

	RecipeSchema SCHEMA = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, INPUT, ENERGY, ENERGY_MOD);
}
