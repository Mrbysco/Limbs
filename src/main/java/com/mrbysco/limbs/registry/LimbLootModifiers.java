package com.mrbysco.limbs.registry;

import com.mojang.serialization.Codec;
import com.mrbysco.limbs.Reference;
import com.mrbysco.limbs.lootmodifiers.LimbDropsModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegistryObject;

public class LimbLootModifiers {
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Reference.MOD_ID);

	public static final RegistryObject<Codec<? extends IGlobalLootModifier>> LIMB_DROPS = GLM.register("limb_drops", LimbDropsModifier.CODEC);
}
