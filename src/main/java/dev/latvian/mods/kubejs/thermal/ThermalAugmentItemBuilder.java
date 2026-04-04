package dev.latvian.mods.kubejs.thermal;

import cofh.core.util.helpers.AugmentDataHelper;
import cofh.thermal.lib.common.item.AugmentItem;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;

import static cofh.lib.util.constants.NBTTags.TAG_AUGMENT_BASE_MOD;
import static cofh.lib.util.constants.NBTTags.TAG_AUGMENT_TYPE_UPGRADE;

public class ThermalAugmentItemBuilder extends ItemBuilder {
	public transient String augmentType;
	public transient LinkedHashMap<String, Float> thermalMods;

	public ThermalAugmentItemBuilder(ResourceLocation i) {
		super(i);
		augmentType = TAG_AUGMENT_TYPE_UPGRADE;
		thermalMods = new LinkedHashMap<>();
	}

	@Info("Sets Thermal augment type, default is Thermal upgrade augment type.")
	public ThermalAugmentItemBuilder augmentType(String type) {
		augmentType = type;
		return this;
	}

	@Info("Sets a Thermal augment numeric modifier by key. Can be called multiple times for different keys.")
	public ThermalAugmentItemBuilder thermalMod(String key, float value) {
		thermalMods.put(key, value);
		return this;
	}

	@Info("Convenience alias for setting Thermal BaseMod.")
	public ThermalAugmentItemBuilder baseMod(float value) {
		thermalMods.put(TAG_AUGMENT_BASE_MOD, value);
		return this;
	}

	@Override
	public Item createObject() {
		var builder = AugmentDataHelper.builder().type(augmentType);
		for (Map.Entry<String, Float> entry : thermalMods.entrySet()) {
			builder.mod(entry.getKey(), entry.getValue());
		}

		return new AugmentItem(createItemProperties(),
				builder.build());
	}
}
