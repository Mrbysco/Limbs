package com.mrbysco.limbs.registry.helper;

import com.mrbysco.limbs.Limbs;
import com.mrbysco.limbs.item.PartItem;
import com.mrbysco.limbs.item.PartLocation;
import com.mrbysco.limbs.registry.LimbRegistry;
import net.minecraft.resources.ResourceLocation;
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
		HEAD = LimbRegistry.ITEMS.register(mobName + "_head", () -> new PartItem(new Item.Properties(),
				PartLocation.HEAD, new ResourceLocation(Limbs.MOD_ID, mobName + "_head")));
		TORSO = LimbRegistry.ITEMS.register(mobName + "_torso", () -> new PartItem(new Item.Properties(),
				PartLocation.TORSO, new ResourceLocation(Limbs.MOD_ID, mobName + "_torso")));
		LEFT_ARM = LimbRegistry.ITEMS.register(mobName + "_left_arm", () -> new PartItem(new Item.Properties(),
				PartLocation.LEFT_ARM, new ResourceLocation(Limbs.MOD_ID, mobName + "_left_arm")));
		RIGHT_ARM = LimbRegistry.ITEMS.register(mobName + "_right_arm", () -> new PartItem(new Item.Properties(),
				PartLocation.RIGHT_ARM, new ResourceLocation(Limbs.MOD_ID, mobName + "_right_arm")));
		LEFT_LEG = LimbRegistry.ITEMS.register(mobName + "_left_leg", () -> new PartItem(new Item.Properties(),
				PartLocation.LEFT_LEG, new ResourceLocation(Limbs.MOD_ID, mobName + "_left_leg")));
		RIGHT_LEG = LimbRegistry.ITEMS.register(mobName + "_right_leg", () -> new PartItem(new Item.Properties(),
				PartLocation.RIGHT_LEG, new ResourceLocation(Limbs.MOD_ID, mobName + "_right_leg")));

		LIMBS_TAG = ItemTags.create(new ResourceLocation(Limbs.MOD_ID, name + "_limbs"));
	}
}
