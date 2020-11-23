package dev.latvian.kubejs.thermal;

import cofh.thermal.expansion.init.TExpRecipeTypes;
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
		event.register(TExpRecipeTypes.ID_RECIPE_FURNACE.toString(), BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_SAWMILL.toString(), BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_PULVERIZER.toString(), BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_SMELTER.toString(), BasicThermalRecipeJS::new);
		// TODO: event.register(TExpRecipeTypes.ID_RECIPE_INSOLATOR.toString(), () -> new InsolatorRecipeSerializer(InsolatorRecipeManager.instance().getDefaultEnergy(), InsolatorRecipeManager.instance().getDefaultWater()));
		event.register(TExpRecipeTypes.ID_RECIPE_CENTRIFUGE.toString(), BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_PRESS.toString(), BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_CRUCIBLE.toString(), BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_CHILLER.toString(), BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_REFINERY.toString(), BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_BREWER.toString(), BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_BOTTLER.toString(), BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_CATALYST_PULVERIZER.toString(), ThermalCatalystRecipeJS::new);
		event.register(TExpRecipeTypes.ID_CATALYST_SMELTER.toString(), ThermalCatalystRecipeJS::new);
		event.register(TExpRecipeTypes.ID_CATALYST_INSOLATOR.toString(), ThermalCatalystRecipeJS::new);
		event.register(TExpRecipeTypes.ID_FUEL_STIRLING.toString(), ThermalFuelRecipeJS::new);
		event.register(TExpRecipeTypes.ID_FUEL_COMPRESSION.toString(), ThermalFuelRecipeJS::new);
		event.register(TExpRecipeTypes.ID_FUEL_MAGMATIC.toString(), ThermalFuelRecipeJS::new);
		event.register(TExpRecipeTypes.ID_FUEL_NUMISMATIC.toString(), ThermalFuelRecipeJS::new);
		event.register(TExpRecipeTypes.ID_FUEL_LAPIDARY.toString(), ThermalFuelRecipeJS::new);
	}
}