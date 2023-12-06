package com.mrbysco.limbs.client.bodypart;

import com.mrbysco.limbs.Limbs;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Limbs.MOD_ID)
public class BodyPartRegistry {
	public static final ResourceKey<Registry<BodyPartType>> BODY_PARTS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(Limbs.MOD_ID, "body_parts"));
	public static final Registry<BodyPartType> BODY_PARTS = (new RegistryBuilder<>(BodyPartRegistry.BODY_PARTS_KEY)).create();

	@SubscribeEvent
	public static void onNewRegistry(NewRegistryEvent event) {
		event.register(BODY_PARTS);
	}
}
