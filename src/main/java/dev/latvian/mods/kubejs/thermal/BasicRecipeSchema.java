package dev.latvian.mods.kubejs.thermal;

import cofh.lib.util.recipes.RecipeJsonUtils;
import cofh.thermal.core.init.registries.TCoreRecipeTypes;
import cofh.thermal.core.util.managers.machine.BottlerRecipeManager;
import cofh.thermal.core.util.managers.machine.BrewerRecipeManager;
import cofh.thermal.core.util.managers.machine.CentrifugeRecipeManager;
import cofh.thermal.core.util.managers.machine.ChillerRecipeManager;
import cofh.thermal.core.util.managers.machine.CrucibleRecipeManager;
import cofh.thermal.core.util.managers.machine.CrystallizerRecipeManager;
import cofh.thermal.core.util.managers.machine.FurnaceRecipeManager;
import cofh.thermal.core.util.managers.machine.PressRecipeManager;
import cofh.thermal.core.util.managers.machine.PulverizerRecipeManager;
import cofh.thermal.core.util.managers.machine.PyrolyzerRecipeManager;
import cofh.thermal.core.util.managers.machine.RefineryRecipeManager;
import cofh.thermal.core.util.managers.machine.SawmillRecipeManager;
import cofh.thermal.core.util.managers.machine.SmelterRecipeManager;
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

	RecipeKey<Integer> ENERGY = NumberComponent.INT.key(RecipeJsonUtils.ENERGY).optional(type -> switch (type.id.getPath()) {
		case TCoreRecipeTypes.ID_FURNACE_RECIPE -> FurnaceRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_SAWMILL_RECIPE -> SawmillRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_PULVERIZER_RECIPE, TCoreRecipeTypes.ID_PULVERIZER_RECYCLE_RECIPE -> PulverizerRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_SMELTER_RECIPE, TCoreRecipeTypes.ID_SMELTER_RECYCLE_RECIPE -> SmelterRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_CENTRIFUGE_RECIPE -> CentrifugeRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_PRESS_RECIPE -> PressRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_CRUCIBLE_RECIPE -> CrucibleRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_CHILLER_RECIPE -> ChillerRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_REFINERY_RECIPE -> RefineryRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_PYROLYZER_RECIPE -> PyrolyzerRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_BOTTLER_RECIPE -> BottlerRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_BREWER_RECIPE -> BrewerRecipeManager.instance().getDefaultEnergy();
		case TCoreRecipeTypes.ID_CRYSTALLIZER_RECIPE -> CrystallizerRecipeManager.instance().getDefaultEnergy();
		default -> 0;
	});

	RecipeKey<Float> ENERGY_MOD = NumberComponent.FLOAT.key(RecipeJsonUtils.ENERGY_MOD).optional(1F).preferred("energyMod").exclude();

	RecipeSchema SCHEMA = new RecipeSchema(ThermalRecipeJS.class, ThermalRecipeJS::new, RESULTS, INGREDIENTS, EXPERIENCE, ENERGY, ENERGY_MOD);
}
