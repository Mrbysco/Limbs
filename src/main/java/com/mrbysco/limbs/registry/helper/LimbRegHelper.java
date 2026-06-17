package com.mrbysco.limbs.registry.helper;

import com.mrbysco.limbs.Limbs;
import com.mrbysco.limbs.item.PartItem;
import com.mrbysco.limbs.item.PartLocation;
import com.mrbysco.limbs.registry.LimbRegistry;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class LimbRegHelper {
	protected final String name;
	protected final DeferredItem<PartItem> HEAD;
	protected final DeferredItem<PartItem> TORSO;
	protected final DeferredItem<PartItem> LEFT_ARM;
	protected final DeferredItem<PartItem> RIGHT_ARM;
	protected final DeferredItem<PartItem> LEFT_LEG;
	protected final DeferredItem<PartItem> RIGHT_LEG;
	protected Supplier<EntityType<?>> ENTITY_TYPE;
	protected TagKey<Item> LIMBS_TAG;

	public PartItem getHead() {
		return HEAD.get();
	}

	public PartItem getTorso() {
		return TORSO.get();
	}

	public PartItem getLeftArm() {
		return LEFT_ARM.get();
	}

	public PartItem getRightArm() {
		return RIGHT_ARM.get();
	}

	public PartItem getLeftLeg() {
		return LEFT_LEG.get();
	}

	public PartItem getRightLeg() {
		return RIGHT_LEG.get();
	}

	public EntityType<?> getEntityType() {
		return ENTITY_TYPE.get();
	}

	public TagKey<Item> getTag() {
		return LIMBS_TAG;
	}

	@Nonnull
	public String getName() {
		return name;
	}

	public LimbRegHelper(String mobName, Supplier<EntityType<?>> typeSupplier) {
		this.name = mobName;
		this.ENTITY_TYPE = typeSupplier;
		HEAD = LimbRegistry.ITEMS.registerItem(mobName + "_head", (properties) -> new PartItem(properties,
				PartLocation.HEAD, Identifier.fromNamespaceAndPath(Limbs.MOD_ID, mobName + "_head")));
		TORSO = LimbRegistry.ITEMS.registerItem(mobName + "_torso", (properties) -> new PartItem(properties,
				PartLocation.TORSO, Identifier.fromNamespaceAndPath(Limbs.MOD_ID, mobName + "_torso")));
		LEFT_ARM = LimbRegistry.ITEMS.registerItem(mobName + "_left_arm", (properties) -> new PartItem(properties,
				PartLocation.LEFT_ARM, Identifier.fromNamespaceAndPath(Limbs.MOD_ID, mobName + "_left_arm")));
		RIGHT_ARM = LimbRegistry.ITEMS.registerItem(mobName + "_right_arm", (properties) -> new PartItem(properties,
				PartLocation.RIGHT_ARM, Identifier.fromNamespaceAndPath(Limbs.MOD_ID, mobName + "_right_arm")));
		LEFT_LEG = LimbRegistry.ITEMS.registerItem(mobName + "_left_leg", (properties) -> new PartItem(properties,
				PartLocation.LEFT_LEG, Identifier.fromNamespaceAndPath(Limbs.MOD_ID, mobName + "_left_leg")));
		RIGHT_LEG = LimbRegistry.ITEMS.registerItem(mobName + "_right_leg", (properties) -> new PartItem(properties,
				PartLocation.RIGHT_LEG, Identifier.fromNamespaceAndPath(Limbs.MOD_ID, mobName + "_right_leg")));

		LIMBS_TAG = ItemTags.create(Identifier.fromNamespaceAndPath(Limbs.MOD_ID, "limbs/" + name));
	}
}
