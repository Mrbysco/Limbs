package com.mrbysco.limbs.datagen.data;

import com.mrbysco.limbs.Limbs;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.util.concurrent.CompletableFuture;

public class LimbEntityTags extends EntityTypeTagsProvider {
	public static final TagKey<EntityType<?>> LIMB_ABLE = create(Identifier.fromNamespaceAndPath(Limbs.MOD_ID, "limb_able"));

	public LimbEntityTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider, Limbs.MOD_ID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(LIMB_ABLE).add(EntityType.SKELETON, EntityType.STRAY, EntityType.WITHER_SKELETON, EntityType.ZOMBIE,
				EntityType.HUSK, EntityType.DROWNED, EntityType.ENDERMAN, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.ZOMBIFIED_PIGLIN);
	}

	private static TagKey<EntityType<?>> create(Identifier location) {
		return TagKey.create(Registries.ENTITY_TYPE, location);
	}
}
