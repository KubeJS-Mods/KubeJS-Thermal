package dev.latvian.mods.kubejs.thermal;

import cofh.lib.util.recipes.RecipeJsonUtils;
import cofh.thermal.core.util.managers.device.TreeExtractorManager;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface TreeExtractorBoostRecipeSchema { // TreeExtractorBoost
	RecipeKey<InputItem> INGREDIENT = ItemComponents.INPUT.key(RecipeJsonUtils.INGREDIENT);
	RecipeKey<Float> OUTPUT = NumberComponent.FLOAT.key(RecipeJsonUtils.OUTPUT).optional(1F);
	RecipeKey<Float> OUTPUT_MOD = NumberComponent.FLOAT.key(RecipeJsonUtils.OUTPUT_MOD).optional(1F).exclude().preferred("outputMod");
	RecipeKey<Integer> CYCLES = NumberComponent.INT.key(RecipeJsonUtils.CYCLES).optional(type -> TreeExtractorManager.instance().getDefaultEnergy());

	RecipeSchema SCHEMA = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, INGREDIENT, OUTPUT, OUTPUT_MOD, CYCLES);
}
