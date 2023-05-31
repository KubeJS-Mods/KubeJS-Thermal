package dev.latvian.mods.kubejs.thermal;

import cofh.lib.util.recipes.RecipeJsonUtils;
import com.mojang.datafixers.util.Either;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface BasicRecipeSchema { // MachineRecipeSerializer
	RecipeKey<Either<OutputFluid, OutputItem>[]> RESULTS = FluidComponents.OUTPUT_OR_ITEM_ARRAY.key(RecipeJsonUtils.RESULTS).alt(RecipeJsonUtils.RESULT, RecipeJsonUtils.OUTPUT, RecipeJsonUtils.OUTPUTS);
	RecipeKey<Either<InputFluid, InputItem>[]> INGREDIENTS = FluidComponents.INPUT_OR_ITEM_ARRAY.key(RecipeJsonUtils.INGREDIENTS).alt(RecipeJsonUtils.INGREDIENT, RecipeJsonUtils.INPUT, RecipeJsonUtils.INPUTS);
	RecipeKey<Float> EXPERIENCE = NumberComponent.FLOAT.key(RecipeJsonUtils.EXPERIENCE).optional(0F).preferred(RecipeJsonUtils.XP);
	RecipeKey<Integer> ENERGY = NumberComponent.INT.key(RecipeJsonUtils.ENERGY).optional(4000);
	RecipeKey<Float> ENERGY_MOD = NumberComponent.FLOAT.key(RecipeJsonUtils.ENERGY_MOD).optional(1F).preferred("energyMod").exclude();

	RecipeSchema SCHEMA = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, RESULTS, INGREDIENTS, EXPERIENCE, ENERGY, ENERGY_MOD);
}
