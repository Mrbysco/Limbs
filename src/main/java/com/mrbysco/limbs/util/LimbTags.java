package com.mrbysco.limbs.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class LimbTags {
	public static final TagKey<Item> HEADS = ItemTags.create(new ResourceLocation("forge", "heads"));
	public static final TagKey<Item> HEAD = ItemTags.create(new ResourceLocation("curios", "head"));
	public static final TagKey<Item> TORSO = ItemTags.create(new ResourceLocation("curios", "torso"));
	public static final TagKey<Item> LEFT_ARM = ItemTags.create(new ResourceLocation("curios", "left_arm"));
	public static final TagKey<Item> RIGHT_ARM = ItemTags.create(new ResourceLocation("curios", "right_arm"));
	public static final TagKey<Item> LEFT_LEG = ItemTags.create(new ResourceLocation("curios", "left_leg"));
	public static final TagKey<Item> RIGHT_LEG = ItemTags.create(new ResourceLocation("curios", "right_leg"));
}
