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
public class TreeExtractorMappingRecipeJS extends ThermalRecipeJS {
	public FluidStackJS outputFluid = EmptyFluidStackJS.INSTANCE;
	public Block trunk = Blocks.AIR;
	public Block leaf = Blocks.AIR;

	@Override
	public void create(RecipeArguments args) {
		outputFluid = FluidStackJS.of(args.get(0));
		trunk = BlockWrapper.getBlock(new ResourceLocation(args.get(1).toString()));

		if (args.size() >= 3) {
			leaf = BlockWrapper.getBlock(new ResourceLocation(args.get(2).toString()));
		}
	}

	public TreeExtractorMappingRecipeJS trunk(Block block) {
		trunk = block;
		serializeInputs = true;
		save();
		return this;
	}

	public TreeExtractorMappingRecipeJS leaf(Block block) {
		leaf = block;
		serializeInputs = true;
		save();
		return this;
	}

	@Override
	public void deserialize() {
		trunk = BlockWrapper.getBlock(new ResourceLocation(json.get("trunk").getAsString()));

		if (json.has("leaf")) {
			leaf = BlockWrapper.getBlock(new ResourceLocation(json.get("leaf").getAsString()));
		} else if (json.has("leaves")) {
			leaf = BlockWrapper.getBlock(new ResourceLocation(json.get("leaves").getAsString()));
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
			json.addProperty("trunk", trunk.kjs$getId());
			json.addProperty("leaf", leaf.kjs$getId());
		}

		if (serializeOutputs) {
			json.add("result", outputFluid.toJson());
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
		return false;
	}

	@Override
	public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
		return false;
	}
}
