package dev.latvian.mods.kubejs.thermal;

import cofh.lib.util.recipes.RecipeJsonUtils;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BlockComponent;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.level.block.Block;

public interface HiveExtractorMappingRecipeSchema { // HiveExtractorMapping
	RecipeKey<OutputFluid> FLUID = FluidComponents.OUTPUT.key(RecipeJsonUtils.FLUID);
	RecipeKey<OutputItem> ITEM = ItemComponents.OUTPUT.key(RecipeJsonUtils.ITEM);
	RecipeKey<Block> HIVE = BlockComponent.INPUT.key(RecipeJsonUtils.HIVE);

	// RecipeKey<Either<OutputFluid, OutputItem>> FLUID_OR_ITEM = FluidComponents.OUTPUT_OR_ITEM.key("output"); // TODO: Implement me

	RecipeSchema SCHEMA = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, FLUID, ITEM, HIVE);
}
