package dev.latvian.kubejs.thermal;

import cofh.thermal.core.init.TCoreRecipeTypes;
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
		event.register(TExpRecipeTypes.ID_RECIPE_FURNACE, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_SAWMILL, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_PULVERIZER, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_SMELTER, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_INSOLATOR, InsolatorRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_CENTRIFUGE, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_PRESS, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_CRUCIBLE, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_CHILLER, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_REFINERY, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_PYROLYZER, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_BREWER, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_RECIPE_BOTTLER, BasicThermalRecipeJS::new);
		event.register(TExpRecipeTypes.ID_CATALYST_PULVERIZER, CatalystRecipeJS::new);
		event.register(TExpRecipeTypes.ID_CATALYST_SMELTER, CatalystRecipeJS::new);
		event.register(TExpRecipeTypes.ID_CATALYST_INSOLATOR, CatalystRecipeJS::new);
		event.register(TExpRecipeTypes.ID_FUEL_STIRLING, FuelRecipeJS::new);
		event.register(TExpRecipeTypes.ID_FUEL_COMPRESSION, FuelRecipeJS::new);
		event.register(TExpRecipeTypes.ID_FUEL_MAGMATIC, FuelRecipeJS::new);
		event.register(TExpRecipeTypes.ID_FUEL_NUMISMATIC, FuelRecipeJS::new);
		event.register(TExpRecipeTypes.ID_FUEL_LAPIDARY, FuelRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_BOOST_TREE_EXTRACTOR, TreeExtractorBoostRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_MAPPING_TREE_EXTRACTOR, TreeExtractorMappingRecipeJS::new);
	}
}