package dev.latvian.kubejs.thermal;

import dev.latvian.kubejs.fluid.EmptyFluidStackJS;
import dev.latvian.kubejs.fluid.FluidStackJS;
import dev.latvian.kubejs.util.ListJS;
import net.minecraft.resources.ResourceLocation;

/**
 * @author LatvianModder
 */
public class TreeExtractorMappingRecipeJS extends ThermalRecipeJS {
	public FluidStackJS outputFluid = EmptyFluidStackJS.INSTANCE;

	@Override
	public void create(ListJS args) {
		outputFluid = FluidStackJS.of(args.get(0));
		json.addProperty("trunk", args.get(1).toString());
		json.addProperty("leaf", args.get(1).toString());
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
}