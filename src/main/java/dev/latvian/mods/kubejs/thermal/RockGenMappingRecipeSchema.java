package dev.latvian.mods.kubejs.thermal;

import cofh.lib.util.recipes.RecipeJsonUtils;
import cofh.thermal.core.util.managers.device.RockGenManager;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BlockComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public interface RockGenMappingRecipeSchema { // RockGenMapping
	RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key(RecipeJsonUtils.RESULT).alt(RecipeJsonUtils.ITEM);
	RecipeKey<Block> BELOW = BlockComponent.INPUT.key(RecipeJsonUtils.BELOW).optional(Blocks.AIR).alt(RecipeJsonUtils.BASE).allowEmpty();
	RecipeKey<Block> ADJACENT = BlockComponent.INPUT.key(RecipeJsonUtils.ADJACENT).optional(Blocks.AIR).allowEmpty();
	RecipeKey<Integer> TIME = NumberComponent.INT.key(RecipeJsonUtils.TIME).optional(() -> RockGenManager.instance().getDefaultEnergy());

	RecipeSchema SCHEMA = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, RESULT, BELOW, ADJACENT, TIME);
}
