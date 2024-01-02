package dev.latvian.mods.kubejs.thermal;


import cofh.lib.util.constants.ModIds;
import cofh.thermal.core.init.registries.TCoreRecipeTypes;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;

public class KubeJSThermalPlugin extends KubeJSPlugin {
	@Override
	public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
		event.namespace(ModIds.ID_THERMAL)
				.register(TCoreRecipeTypes.ID_FURNACE_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_SAWMILL_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_PULVERIZER_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_PULVERIZER_RECYCLE_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_SMELTER_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_SMELTER_RECYCLE_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_CENTRIFUGE_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_PRESS_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_CRUCIBLE_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_CHILLER_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_REFINERY_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_PYROLYZER_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_BOTTLER_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_BREWER_RECIPE, BasicRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_CRYSTALLIZER_RECIPE, BasicRecipeSchema.SCHEMA)

				.register(TCoreRecipeTypes.ID_INSOLATOR_RECIPE, InsolatorRecipeSchema.SCHEMA)

				.register(TCoreRecipeTypes.ID_PULVERIZER_CATALYST, CatalystRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_SMELTER_CATALYST, CatalystRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_INSOLATOR_CATALYST, CatalystRecipeSchema.SCHEMA)

				.register(TCoreRecipeTypes.ID_STIRLING_FUEL, FuelRecipeSchema.ITEM_FUEL)
				.register(TCoreRecipeTypes.ID_COMPRESSION_FUEL, FuelRecipeSchema.FLUID_FUEL)
				.register(TCoreRecipeTypes.ID_MAGMATIC_FUEL, FuelRecipeSchema.FLUID_FUEL)
				.register(TCoreRecipeTypes.ID_NUMISMATIC_FUEL, FuelRecipeSchema.ITEM_FUEL)
				.register(TCoreRecipeTypes.ID_LAPIDARY_FUEL, FuelRecipeSchema.ITEM_FUEL)
				.register(TCoreRecipeTypes.ID_DISENCHANTMENT_FUEL, FuelRecipeSchema.ITEM_FUEL)
				.register(TCoreRecipeTypes.ID_GOURMAND_FUEL, FuelRecipeSchema.ITEM_FUEL)

				.register(TCoreRecipeTypes.ID_HIVE_EXTRACTOR_MAPPING, HiveExtractorMappingRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_TREE_EXTRACTOR_MAPPING, TreeExtractorMappingRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_TREE_EXTRACTOR_BOOST, TreeExtractorBoostRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_FISHER_BOOST, FisherBoostRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_ROCK_GEN_MAPPING, RockGenMappingRecipeSchema.SCHEMA)
				.register(TCoreRecipeTypes.ID_POTION_DIFFUSER_BOOST, PotionDiffuserBoostRecipeSchema.SCHEMA)
		;
	}
}
