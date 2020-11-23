package dev.latvian.kubejs.thermal;

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
		event.register("thermal:furnace", BasicThermalRecipeJS::new);
		event.register("thermal:sawmill", BasicThermalRecipeJS::new);
		event.register("thermal:pulverizer", BasicThermalRecipeJS::new);
		event.register("thermal:smelter", BasicThermalRecipeJS::new);
		event.register("thermal:insolator", ThermalInsolatorRecipeJS::new);
		event.register("thermal:centrifuge", BasicThermalRecipeJS::new);
		event.register("thermal:press", BasicThermalRecipeJS::new);
		event.register("thermal:crucible", BasicThermalRecipeJS::new);
		event.register("thermal:chiller", BasicThermalRecipeJS::new);
		event.register("thermal:refinery", BasicThermalRecipeJS::new);
		event.register("thermal:brewer", BasicThermalRecipeJS::new);
		event.register("thermal:bottler", BasicThermalRecipeJS::new);
		event.register("thermal:pulverizer_catalyst", ThermalCatalystRecipeJS::new);
		event.register("thermal:smelter_catalyst", ThermalCatalystRecipeJS::new);
		event.register("thermal:insolator_catalyst", ThermalCatalystRecipeJS::new);
		event.register("thermal:stirling_fuel", ThermalFuelRecipeJS::new);
		event.register("thermal:compression_fuel", ThermalFuelRecipeJS::new);
		event.register("thermal:magmatic_fuel", ThermalFuelRecipeJS::new);
		event.register("thermal:numismatic_fuel", ThermalFuelRecipeJS::new);
		event.register("thermal:lapidary_fuel", ThermalFuelRecipeJS::new);
	}
}