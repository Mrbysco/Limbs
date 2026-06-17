package com.mrbysco.limbs.lootmodifiers;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.limbs.config.LimbConfig;
import com.mrbysco.limbs.item.PartItem;
import com.mrbysco.limbs.item.PartLocation;
import com.mrbysco.limbs.registry.LimbLootModifiers;
import com.mrbysco.limbs.registry.LimbRegistry;
import com.mrbysco.limbs.registry.helper.LimbRegHelper;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LimbDropsModifier extends LootModifier {
	public static final Supplier<MapCodec<LimbDropsModifier>> CODEC = Suppliers.memoize(() ->
			RecordCodecBuilder.mapCodec(inst -> codecStart(inst).apply(inst, LimbDropsModifier::new)));

	public LimbDropsModifier(LootItemCondition[] lootConditions, int priority) {
		super(lootConditions, priority);
	}

	@Nonnull
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (context.hasParameter(LootContextParams.THIS_ENTITY)) {
			Entity entity = context.getParameter(LootContextParams.THIS_ENTITY);
			EntityType<?> type = entity.getType();
			for (LimbRegHelper limbRegHelper : LimbRegistry.REGISTERED_LIMBS) {
				if (limbRegHelper.getEntityType() != null && BuiltInRegistries.ENTITY_TYPE.getKey(limbRegHelper.getEntityType()).equals(BuiltInRegistries.ENTITY_TYPE.getKey(type))) {
					List<Item> possibleLimbs = new ArrayList<>();
					Optional<HolderSet.Named<Item>> optionalTag = BuiltInRegistries.ITEM.get(limbRegHelper.getTag());
					if (optionalTag.isPresent()) {
						HolderSet.Named<Item> tag = optionalTag.get();
						tag.forEach(item -> {
							if (LimbConfig.COMMON.dropHeads.get()) {
								possibleLimbs.add(item.value());
							} else if (item instanceof PartItem partItem && partItem.getPartLocation() != PartLocation.HEAD) {
								possibleLimbs.add(item.value());
							}
						});
					}
					if (Math.random() <= LimbConfig.COMMON.limbDropChance.get()) {
						generatedLoot.add(new ItemStack(possibleLimbs.get(entity.level().getRandom().nextInt(possibleLimbs.size()))));
					}
				}
			}
		}

		return generatedLoot;
	}

	@Override
	public MapCodec<? extends IGlobalLootModifier> codec() {
		return LimbLootModifiers.LIMB_DROPS.get();
	}
}