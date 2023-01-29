package dev.latvian.mods.kubejs.thermal;


import cofh.thermal.core.init.TCoreRecipeTypes;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeTypesEvent;

import java.util.List;

/**
 * @author LatvianModder
 */
public class KubeJSThermalPlugin extends KubeJSPlugin {
	@Override
	public void registerRecipeTypes(RegisterRecipeTypesEvent event) {
		for (var type : List.of(
				TCoreRecipeTypes.FURNACE_RECIPE,
				TCoreRecipeTypes.SAWMILL_RECIPE,
				TCoreRecipeTypes.PULVERIZER_RECIPE,
				TCoreRecipeTypes.SMELTER_RECIPE,
				TCoreRecipeTypes.CENTRIFUGE_RECIPE,
				TCoreRecipeTypes.PRESS_RECIPE,
				TCoreRecipeTypes.CRUCIBLE_RECIPE,
				TCoreRecipeTypes.CHILLER_RECIPE,
				TCoreRecipeTypes.REFINERY_RECIPE,
				TCoreRecipeTypes.PYROLYZER_RECIPE,
				TCoreRecipeTypes.BREWER_RECIPE,
				TCoreRecipeTypes.BOTTLER_RECIPE,
				TCoreRecipeTypes.CRYSTALLIZER_RECIPE
		)) {
			event.register(type.getId(), BasicThermalRecipeJS::new);
		}

		event.register(TCoreRecipeTypes.INSOLATOR_RECIPE.getId(), InsolatorRecipeJS::new);

		for (var type : List.of(
				TCoreRecipeTypes.PULVERIZER_CATALYST,
				TCoreRecipeTypes.SMELTER_CATALYST,
				TCoreRecipeTypes.INSOLATOR_CATALYST

		)) {
			event.register(type.getId(), CatalystRecipeJS::new);
		}

		for (var type : List.of(
				TCoreRecipeTypes.STIRLING_FUEL,
				TCoreRecipeTypes.COMPRESSION_FUEL,
				TCoreRecipeTypes.MAGMATIC_FUEL,
				TCoreRecipeTypes.NUMISMATIC_FUEL,
				TCoreRecipeTypes.LAPIDARY_FUEL,
				TCoreRecipeTypes.DISENCHANTMENT_FUEL,
				TCoreRecipeTypes.GOURMAND_FUEL
		)) {
			event.register(type.getId(), FuelRecipeJS::new);
		}

		event.register(TCoreRecipeTypes.HIVE_EXTRACTOR_MAPPING.getId(), HiveExtractorMappingRecipeJS::new);
		event.register(TCoreRecipeTypes.TREE_EXTRACTOR_MAPPING.getId(), TreeExtractorMappingRecipeJS::new);
		event.register(TCoreRecipeTypes.TREE_EXTRACTOR_BOOST.getId(), TreeExtractorBoostRecipeJS::new);
		event.register(TCoreRecipeTypes.FISHER_BOOST.getId(), FisherBoostRecipeJS::new);
		event.register(TCoreRecipeTypes.ROCK_GEN_MAPPING.getId(), RockGenMappingRecipeJS::new);
		event.register(TCoreRecipeTypes.POTION_DIFFUSER_BOOST.getId(), PotionDiffuserBoostRecipeJS::new);
	}
}
