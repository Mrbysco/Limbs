package com.mrbysco.limbs.registry;

import com.mojang.serialization.Codec;
import com.mrbysco.limbs.Limbs;
import com.mrbysco.limbs.lootmodifiers.LimbDropsModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class LimbLootModifiers {
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Limbs.MOD_ID);

	public static final Supplier<Codec<? extends IGlobalLootModifier>> LIMB_DROPS = GLM.register("limb_drops", LimbDropsModifier.CODEC);
}
