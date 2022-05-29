package com.mrbysco.limbs.lootmodifiers;

import com.google.gson.JsonObject;
import com.mrbysco.limbs.config.LimbConfig;
import com.mrbysco.limbs.item.PartItem;
import com.mrbysco.limbs.item.PartLocation;
import com.mrbysco.limbs.registry.LimbRegistry;
import com.mrbysco.limbs.registry.helper.LimbRegHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class LimbDropsModifier extends LootModifier {
	public LimbDropsModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		if (context.hasParam(LootContextParams.THIS_ENTITY)) {
			Entity entity = context.getParam(LootContextParams.THIS_ENTITY);
			EntityType<?> type = entity.getType();
			for (LimbRegHelper limbRegHelper : LimbRegistry.REGISTERED_LIMBS) {
				if (limbRegHelper.getEntityType() != null && limbRegHelper.getEntityType().getRegistryName().equals(type.getRegistryName())) {
					List<Item> possibleLimbs = new ArrayList<>();
					ForgeRegistries.ITEMS.tags().getTag(limbRegHelper.getTag()).stream().forEach(item -> {
						if (LimbConfig.COMMON.dropHeads.get()) {
							possibleLimbs.add(item);
						} else if (item instanceof PartItem partItem && partItem.getPartLocation() != PartLocation.HEAD) {
							possibleLimbs.add(item);
						}
					});
					if (Math.random() <= LimbConfig.COMMON.limbDropChance.get()) {
						generatedLoot.add(new ItemStack(possibleLimbs.get(entity.level.random.nextInt(possibleLimbs.size()))));
					}
				}
			}
		}

		return generatedLoot;
	}


	public static class Serializer extends GlobalLootModifierSerializer<LimbDropsModifier> {
		@Override
		public LimbDropsModifier read(ResourceLocation name, JsonObject json, LootItemCondition[] conditionsIn) {
			return new LimbDropsModifier(conditionsIn);
		}

		@Override
		public JsonObject write(LimbDropsModifier instance) {
			return makeConditions(instance.conditions);
		}
	}
}