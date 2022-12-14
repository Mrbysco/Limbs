package com.mrbysco.limbs.registry.helper;

import com.mrbysco.limbs.Reference;
import com.mrbysco.limbs.item.PartItem;
import com.mrbysco.limbs.item.PartLocation;
import com.mrbysco.limbs.registry.LimbRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class LimbRegHelper {
	protected final String name;
	protected final RegistryObject<Item> HEAD;
	protected final RegistryObject<Item> TORSO;
	protected final RegistryObject<Item> LEFT_ARM;
	protected final RegistryObject<Item> RIGHT_ARM;
	protected final RegistryObject<Item> LEFT_LEG;
	protected final RegistryObject<Item> RIGHT_LEG;
	protected Supplier<EntityType<?>> ENTITY_TYPE;
	protected TagKey<Item> LIMBS_TAG;

	public Item getHead() {
		return HEAD.get();
	}

	public Item getTorso() {
		return TORSO.get();
	}

	public Item getLeftArm() {
		return LEFT_ARM.get();
	}

	public Item getRightArm() {
		return RIGHT_ARM.get();
	}

	public Item getLeftLeg() {
		return LEFT_LEG.get();
	}

	public Item getRightLeg() {
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
				PartLocation.HEAD, new ResourceLocation(Reference.MOD_ID, mobName + "_head")));
		TORSO = LimbRegistry.ITEMS.register(mobName + "_torso", () -> new PartItem(new Item.Properties(),
				PartLocation.TORSO, new ResourceLocation(Reference.MOD_ID, mobName + "_torso")));
		LEFT_ARM = LimbRegistry.ITEMS.register(mobName + "_left_arm", () -> new PartItem(new Item.Properties(),
				PartLocation.LEFT_ARM, new ResourceLocation(Reference.MOD_ID, mobName + "_left_arm")));
		RIGHT_ARM = LimbRegistry.ITEMS.register(mobName + "_right_arm", () -> new PartItem(new Item.Properties(),
				PartLocation.RIGHT_ARM, new ResourceLocation(Reference.MOD_ID, mobName + "_right_arm")));
		LEFT_LEG = LimbRegistry.ITEMS.register(mobName + "_left_leg", () -> new PartItem(new Item.Properties(),
				PartLocation.LEFT_LEG, new ResourceLocation(Reference.MOD_ID, mobName + "_left_leg")));
		RIGHT_LEG = LimbRegistry.ITEMS.register(mobName + "_right_leg", () -> new PartItem(new Item.Properties(),
				PartLocation.RIGHT_LEG, new ResourceLocation(Reference.MOD_ID, mobName + "_right_leg")));

		LIMBS_TAG = ItemTags.create(new ResourceLocation(Reference.MOD_ID, name + "_limbs"));
	}
}
