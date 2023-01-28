package dev.latvian.mods.kubejs.thermal;

import dev.latvian.mods.kubejs.fluid.EmptyFluidStackJS;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * @author LatvianModder
 */
public class TreeExtractorMappingRecipeJS extends ThermalRecipeJS {
	public FluidStackJS outputFluid = EmptyFluidStackJS.INSTANCE;

	@Override
	public void create(RecipeArguments args) {
		outputFluid = FluidStackJS.of(args.get(0));
		json.addProperty("trunk", args.get(1).toString());
		json.addProperty("leaf", args.get(2).toString());
	}

	public TreeExtractorMappingRecipeJS trunk(ResourceLocation id) {
		json.addProperty("trunk", id.toString());
		save();
		return this;
	}

	public TreeExtractorMappingRecipeJS leaf(ResourceLocation id) {
		json.addProperty("leaf", id.toString());
		save();
		return this;
	}

	@Override
	public void deserialize() {
		if (json.has("result")) {
			outputFluid = FluidStackJS.fromJson(json.get("result"));
		} else if (json.has("fluid")) {
			outputFluid = FluidStackJS.fromJson(json.get("fluid"));
		}
	}

	@Override
	public void serialize() {
		if (serializeInputs) {
			json.add("result", outputFluid.toJson());
		}
	}

	@Override
	public boolean hasInput(IngredientMatch ingredientMatch) {
		return false;
	}

	@Override
	public boolean replaceInput(IngredientMatch ingredientMatch, Ingredient ingredient, ItemInputTransformer itemInputTransformer) {
		return false;
	}

	@Override
	public boolean hasOutput(IngredientMatch ingredientMatch) {
		return false;
	}

	@Override
	public boolean replaceOutput(IngredientMatch ingredientMatch, ItemStack itemStack, ItemOutputTransformer itemOutputTransformer) {
		return false;
	}
}
