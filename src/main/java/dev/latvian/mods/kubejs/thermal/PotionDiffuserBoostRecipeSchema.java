package dev.latvian.mods.kubejs.thermal;

import cofh.lib.util.recipes.RecipeJsonUtils;
import cofh.thermal.core.util.managers.device.PotionDiffuserManager;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface PotionDiffuserBoostRecipeSchema { // PotionDiffuserBoost
	RecipeKey<InputItem> INGREDIENT = ItemComponents.INPUT.key(RecipeJsonUtils.INGREDIENT);
	RecipeKey<Integer> AMPLIFIER = NumberComponent.INT.key(RecipeJsonUtils.AMPLIFIER).optional(0);
	RecipeKey<Float> DURATION_MOD = NumberComponent.FLOAT.key(RecipeJsonUtils.DURATION_MOD).optional(1F).preferred("durationMod");
	RecipeKey<Integer> CYCLES = NumberComponent.INT.key(RecipeJsonUtils.CYCLES).optional(() -> PotionDiffuserManager.instance().getDefaultEnergy());

	RecipeSchema SCHEMA = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, INGREDIENT, AMPLIFIER, DURATION_MOD, CYCLES);
}
