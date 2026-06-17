package com.mrbysco.limbs.util;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class LimbTags {
	public static final TagKey<Item> HEAD = ItemTags.create(Identifier.fromNamespaceAndPath("curios", "head"));
	public static final TagKey<Item> TORSO = ItemTags.create(Identifier.fromNamespaceAndPath("curios", "torso"));
	public static final TagKey<Item> LEFT_ARM = ItemTags.create(Identifier.fromNamespaceAndPath("curios", "left_arm"));
	public static final TagKey<Item> RIGHT_ARM = ItemTags.create(Identifier.fromNamespaceAndPath("curios", "right_arm"));
	public static final TagKey<Item> LEFT_LEG = ItemTags.create(Identifier.fromNamespaceAndPath("curios", "left_leg"));
	public static final TagKey<Item> RIGHT_LEG = ItemTags.create(Identifier.fromNamespaceAndPath("curios", "right_leg"));
}
