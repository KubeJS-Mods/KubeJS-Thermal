package dev.latvian.mods.kubejs.thermal;

import cofh.lib.util.recipes.RecipeJsonUtils;
import cofh.thermal.core.init.registries.TCoreRecipeTypes;
import cofh.thermal.core.util.managers.dynamo.CompressionFuelManager;
import cofh.thermal.core.util.managers.dynamo.DisenchantmentFuelManager;
import cofh.thermal.core.util.managers.dynamo.GourmandFuelManager;
import cofh.thermal.core.util.managers.dynamo.LapidaryFuelManager;
import cofh.thermal.core.util.managers.dynamo.MagmaticFuelManager;
import cofh.thermal.core.util.managers.dynamo.NumismaticFuelManager;
import cofh.thermal.core.util.managers.dynamo.StirlingFuelManager;
import cofh.thermal.lib.util.managers.SingleFluidFuelManager;
import cofh.thermal.lib.util.managers.SingleItemFuelManager;
import com.mojang.datafixers.util.Either;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface FuelRecipeSchema { // ThermalFuel
	RecipeKey<Either<InputFluid, InputItem>[]> INPUT = FluidComponents.INPUT_OR_ITEM_ARRAY.key(RecipeJsonUtils.INGREDIENTS).alt(RecipeJsonUtils.INGREDIENT, RecipeJsonUtils.INPUT, RecipeJsonUtils.INPUTS);

	RecipeKey<Integer> ITEM_FUEL_ENERGY = NumberComponent.intRange(SingleItemFuelManager.MIN_ENERGY, SingleItemFuelManager.MAX_ENERGY).key(RecipeJsonUtils.ENERGY).optional(type -> switch (type.id.getPath()) {
		case TCoreRecipeTypes.ID_STIRLING_FUEL -> StirlingFuelManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_NUMISMATIC_FUEL -> NumismaticFuelManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_LAPIDARY_FUEL -> LapidaryFuelManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_DISENCHANTMENT_FUEL -> DisenchantmentFuelManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_GOURMAND_FUEL -> GourmandFuelManager.instance().getDefaultEnergy();
		default -> 0;
	});

	RecipeKey<Integer> FLUID_FUEL_ENERGY = NumberComponent.intRange(SingleFluidFuelManager.MIN_ENERGY, SingleFluidFuelManager.MAX_ENERGY).key(RecipeJsonUtils.ENERGY).optional(type -> switch (type.id.getPath()) {
		case TCoreRecipeTypes.ID_COMPRESSION_FUEL -> CompressionFuelManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_MAGMATIC_FUEL -> MagmaticFuelManager.instance().getDefaultEnergy();
		default -> 0;
	});

	RecipeKey<Float> ENERGY_MOD = NumberComponent.FLOAT.key(RecipeJsonUtils.ENERGY_MOD).optional(1F).exclude().preferred("energyMod");

	RecipeSchema ITEM_FUEL = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, INPUT, ITEM_FUEL_ENERGY, ENERGY_MOD);
	RecipeSchema FLUID_FUEL = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, INPUT, FLUID_FUEL_ENERGY, ENERGY_MOD);
}
