package com.mrbysco.limbs.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class PartItem extends Item implements ICurioItem {
	private final PartLocation partLocation;
	private final ResourceLocation partRegistry;

	public PartItem(Properties properties, PartLocation partLocation, ResourceLocation partRegistry) {
		super(properties);
		this.partLocation = partLocation;
		this.partRegistry = partRegistry;
	}

	public PartLocation getPartLocation() {
		return partLocation;
	}

	public ResourceLocation getPartRegistry() {
		return partRegistry;
	}
}
