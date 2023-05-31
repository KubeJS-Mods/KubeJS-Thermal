package dev.latvian.mods.kubejs.thermal;

import cofh.lib.util.recipes.RecipeJsonUtils;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public interface FisherBoostRecipeSchema { // FisherBoost
	RecipeKey<InputItem> INGREDIENT = ItemComponents.INPUT.key(RecipeJsonUtils.INGREDIENT);
	RecipeKey<Float> OUTPUT_MOD = NumberComponent.FLOAT.key(RecipeJsonUtils.OUTPUT_MOD).optional(1F).preferred("outputMod");
	RecipeKey<Float> USE_CHANCE = NumberComponent.FLOAT.key(RecipeJsonUtils.USE_CHANCE).optional(1F).preferred("useChance");
	RecipeKey<String> LOOT_TABLE = StringComponent.ANY.key(RecipeJsonUtils.LOOT_TABLE).optional(BuiltInLootTables.FISHING_FISH.toString()).preferred("lootTable");

	RecipeSchema SCHEMA = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, INGREDIENT, OUTPUT_MOD, USE_CHANCE, LOOT_TABLE);
}
