package dev.latvian.mods.kubejs.thermal;

import cofh.lib.util.recipes.RecipeJsonUtils;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BlockComponent;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public interface TreeExtractorMappingRecipeSchema { // TreeExtractorMapping
	RecipeKey<OutputFluid> RESULT = FluidComponents.OUTPUT.key(RecipeJsonUtils.RESULT).alt(RecipeJsonUtils.FLUID);
	RecipeKey<Block> TRUNK = BlockComponent.INPUT.key(RecipeJsonUtils.TRUNK).optional(Blocks.AIR).allowEmpty();
	RecipeKey<Block> LEAVES = BlockComponent.INPUT.key(RecipeJsonUtils.LEAVES).optional(Blocks.AIR).alt(RecipeJsonUtils.LEAF).allowEmpty();

	RecipeSchema SCHEMA = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, RESULT, TRUNK, LEAVES);
}
