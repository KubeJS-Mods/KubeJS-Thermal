package dev.latvian.kubejs.thermal;

import cofh.thermal.expansion.init.TExpRecipeTypes;
import cofh.thermal.expansion.util.managers.dynamo.CompressionFuelManager;
import cofh.thermal.expansion.util.managers.dynamo.LapidaryFuelManager;
import cofh.thermal.expansion.util.managers.dynamo.MagmaticFuelManager;
import cofh.thermal.expansion.util.managers.dynamo.NumismaticFuelManager;
import cofh.thermal.expansion.util.managers.dynamo.StirlingFuelManager;
import cofh.thermal.expansion.util.managers.machine.BottlerRecipeManager;
import cofh.thermal.expansion.util.managers.machine.BrewerRecipeManager;
import cofh.thermal.expansion.util.managers.machine.CentrifugeRecipeManager;
import cofh.thermal.expansion.util.managers.machine.ChillerRecipeManager;
import cofh.thermal.expansion.util.managers.machine.CrucibleRecipeManager;
import cofh.thermal.expansion.util.managers.machine.FurnaceRecipeManager;
import cofh.thermal.expansion.util.managers.machine.PressRecipeManager;
import cofh.thermal.expansion.util.managers.machine.PulverizerRecipeManager;
import cofh.thermal.expansion.util.managers.machine.RefineryRecipeManager;
import cofh.thermal.expansion.util.managers.machine.SawmillRecipeManager;
import cofh.thermal.expansion.util.managers.machine.SmelterRecipeManager;
import dev.latvian.kubejs.recipe.RegisterRecipeHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author LatvianModder
 */
@Mod(KubeJSThermal.MOD_ID)
@Mod.EventBusSubscriber(modid = KubeJSThermal.MOD_ID)
public class KubeJSThermal
{
	public static final String MOD_ID = "kubejs_thermal";

	@SubscribeEvent
	public static void registerRecipeHandlers(RegisterRecipeHandlersEvent event)
	{
		event.register(TExpRecipeTypes.ID_RECIPE_FURNACE.toString(), () -> new BasicThermalRecipeJS(FurnaceRecipeManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_RECIPE_SAWMILL.toString(), () -> new BasicThermalRecipeJS(SawmillRecipeManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_RECIPE_PULVERIZER.toString(), () -> new BasicThermalRecipeJS(PulverizerRecipeManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_RECIPE_SMELTER.toString(), () -> new BasicThermalRecipeJS(SmelterRecipeManager.instance().getDefaultEnergy()));
		// TODO: event.register(TExpRecipeTypes.ID_RECIPE_INSOLATOR.toString(), () -> new InsolatorRecipeSerializer(InsolatorRecipeManager.instance().getDefaultEnergy(), InsolatorRecipeManager.instance().getDefaultWater()));
		event.register(TExpRecipeTypes.ID_RECIPE_CENTRIFUGE.toString(), () -> new BasicThermalRecipeJS(CentrifugeRecipeManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_RECIPE_PRESS.toString(), () -> new BasicThermalRecipeJS(PressRecipeManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_RECIPE_CRUCIBLE.toString(), () -> new BasicThermalRecipeJS(CrucibleRecipeManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_RECIPE_CHILLER.toString(), () -> new BasicThermalRecipeJS(ChillerRecipeManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_RECIPE_REFINERY.toString(), () -> new BasicThermalRecipeJS(RefineryRecipeManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_RECIPE_BREWER.toString(), () -> new BasicThermalRecipeJS(BrewerRecipeManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_RECIPE_BOTTLER.toString(), () -> new BasicThermalRecipeJS(BottlerRecipeManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_CATALYST_PULVERIZER.toString(), ThermalCatalystRecipeJS::new);
		event.register(TExpRecipeTypes.ID_CATALYST_SMELTER.toString(), ThermalCatalystRecipeJS::new);
		event.register(TExpRecipeTypes.ID_CATALYST_INSOLATOR.toString(), ThermalCatalystRecipeJS::new);
		event.register(TExpRecipeTypes.ID_FUEL_STIRLING.toString(), () -> new ThermalFuelRecipeJS(StirlingFuelManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_FUEL_COMPRESSION.toString(), () -> new ThermalFuelRecipeJS(CompressionFuelManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_FUEL_MAGMATIC.toString(), () -> new ThermalFuelRecipeJS(MagmaticFuelManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_FUEL_NUMISMATIC.toString(), () -> new ThermalFuelRecipeJS(NumismaticFuelManager.instance().getDefaultEnergy()));
		event.register(TExpRecipeTypes.ID_FUEL_LAPIDARY.toString(), () -> new ThermalFuelRecipeJS(LapidaryFuelManager.instance().getDefaultEnergy()));
	}
}