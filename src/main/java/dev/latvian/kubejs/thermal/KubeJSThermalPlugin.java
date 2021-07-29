package dev.latvian.kubejs.thermal;

import cofh.thermal.core.init.TCoreRecipeTypes;
import dev.latvian.kubejs.KubeJSPlugin;
import dev.latvian.kubejs.recipe.RegisterRecipeHandlersEvent;

/**
 * @author LatvianModder
 */
public class KubeJSThermalPlugin extends KubeJSPlugin {
	@Override
	public void addRecipes(RegisterRecipeHandlersEvent event) {
		event.register(TCoreRecipeTypes.ID_RECIPE_FURNACE, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_SAWMILL, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_PULVERIZER, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_SMELTER, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_INSOLATOR, InsolatorRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_CENTRIFUGE, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_PRESS, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_CRUCIBLE, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_CHILLER, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_REFINERY, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_PYROLYZER, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_BREWER, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_RECIPE_BOTTLER, BasicThermalRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_CATALYST_PULVERIZER, CatalystRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_CATALYST_SMELTER, CatalystRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_CATALYST_INSOLATOR, CatalystRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_FUEL_STIRLING, FuelRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_FUEL_COMPRESSION, FuelRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_FUEL_MAGMATIC, FuelRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_FUEL_NUMISMATIC, FuelRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_FUEL_LAPIDARY, FuelRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_BOOST_TREE_EXTRACTOR, TreeExtractorBoostRecipeJS::new);
		event.register(TCoreRecipeTypes.ID_MAPPING_TREE_EXTRACTOR, TreeExtractorMappingRecipeJS::new);
	}
}