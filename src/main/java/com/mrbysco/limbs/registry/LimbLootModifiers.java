package com.mrbysco.limbs.registry;

import com.mrbysco.limbs.Reference;
import com.mrbysco.limbs.lootmodifiers.LimbDropsModifier;
import com.mrbysco.limbs.lootmodifiers.LimbDropsModifier.Serializer;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LimbLootModifiers {
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, Reference.MOD_ID);

	public static final RegistryObject<Serializer> LIMB_DROPS = GLM.register("limb_drops", LimbDropsModifier.Serializer::new);
}
