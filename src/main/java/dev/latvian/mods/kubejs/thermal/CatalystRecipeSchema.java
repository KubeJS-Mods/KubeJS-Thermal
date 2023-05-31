package dev.latvian.mods.kubejs.thermal;

import cofh.lib.util.recipes.RecipeJsonUtils;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface CatalystRecipeSchema { // MachineCatalystSerializer
	RecipeKey<InputItem> INGREDIENT = ItemComponents.INPUT.key(RecipeJsonUtils.INGREDIENT);
	RecipeKey<Float> PRIMARY_MOD = NumberComponent.FLOAT.key(RecipeJsonUtils.PRIMARY_MOD).optional(1F).preferred("primaryMod");
	RecipeKey<Float> SECONDARY_MOD = NumberComponent.FLOAT.key(RecipeJsonUtils.SECONDARY_MOD).optional(1F).preferred("secondaryMod");
	RecipeKey<Float> ENERGY_MOD = NumberComponent.FLOAT.key(RecipeJsonUtils.ENERGY_MOD).optional(1F).preferred("energyMod");
	RecipeKey<Float> MIN_CHANCE = NumberComponent.FLOAT.key(RecipeJsonUtils.MIN_CHANCE).optional(0F).preferred("minChance");
	RecipeKey<Float> USE_CHANCE = NumberComponent.FLOAT.key(RecipeJsonUtils.USE_CHANCE).optional(1F).preferred("useChance");

	RecipeSchema SCHEMA = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, INGREDIENT, PRIMARY_MOD, SECONDARY_MOD, ENERGY_MOD, MIN_CHANCE, USE_CHANCE);
}
