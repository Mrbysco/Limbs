package com.mrbysco.limbs.datagen.data;

import com.mrbysco.limbs.Limbs;
import com.mrbysco.limbs.datagen.LimbDataGen;
import com.mrbysco.limbs.lootmodifiers.LimbDropsModifier;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class LimbLootProvider extends GlobalLootModifierProvider {
	public LimbLootProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider, Limbs.MOD_ID);
	}

	@Override
	protected void start() {
		HolderLookup<EntityType<?>> entityTypes = registries.lookupOrThrow(Registries.ENTITY_TYPE);
		this.add("limb_drops", new LimbDropsModifier(
				new LootItemCondition[]{
						LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(entityTypes, LimbEntityTags.LIMB_ABLE)).build(),
						LootItemKilledByPlayerCondition.killedByPlayer().build()
				}, 1000));
	}
}
