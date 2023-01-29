package dev.latvian.mods.kubejs.thermal;

import dev.latvian.mods.kubejs.bindings.BlockWrapper;
import dev.latvian.mods.kubejs.fluid.EmptyFluidStackJS;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

/**
 * @author LatvianModder
 */
public class HiveExtractorMappingRecipeJS extends ThermalRecipeJS {
	public FluidStackJS outputFluid = EmptyFluidStackJS.INSTANCE;
	public Block hive = Blocks.AIR;
	public ItemStack item = ItemStack.EMPTY;

	@Override
	public void create(RecipeArguments args) {
		outputFluid = FluidStackJS.of(args.get(0));
		item = parseItemOutput(args.get(1));
		hive = BlockWrapper.getBlock(new ResourceLocation(args.get(1).toString()));
	}

	public HiveExtractorMappingRecipeJS hive(Block block) {
		hive = block;
		serializeInputs = true;
		save();
		return this;
	}

	@Override
	public void deserialize() {
		hive = BlockWrapper.getBlock(new ResourceLocation(json.get("trunk").getAsString()));

		if (json.has("item")) {
			item = parseItemOutput(json.get("item"));
		}

		if (json.has("result")) {
			outputFluid = FluidStackJS.fromJson(json.get("result"));
		} else if (json.has("fluid")) {
			outputFluid = FluidStackJS.fromJson(json.get("fluid"));
		}
	}

	@Override
	public void serialize() {
		if (serializeInputs) {
			json.addProperty("hive", hive.kjs$getId());
		}

		if (serializeOutputs) {
			json.add("result", outputFluid.toJson());
			json.add("item", itemToJson(item));
		}
	}

	@Override
	public boolean hasInput(IngredientMatch match) {
		return false;
	}

	@Override
	public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
		return false;
	}

	@Override
	public boolean hasOutput(IngredientMatch match) {
		return match.contains(item);
	}

	@Override
	public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
		if (match.contains(item)) {
			item = transformer.transform(this, match, item, with);
			return true;
		}

		return false;
	}
}
