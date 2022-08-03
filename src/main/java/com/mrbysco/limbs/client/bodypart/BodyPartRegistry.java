package com.mrbysco.limbs.client.bodypart;

import com.mrbysco.limbs.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Reference.MOD_ID)
public class BodyPartRegistry {
	public static final ResourceLocation registryLocation = new ResourceLocation(Reference.MOD_ID, "body_parts");
	public static Supplier<IForgeRegistry<BodyPartType>> BODY_PARTS;

	@SubscribeEvent
	public static void onNewRegistry(NewRegistryEvent event) {
		RegistryBuilder<BodyPartType> registryBuilder = new RegistryBuilder<>();
		registryBuilder.setName(registryLocation);
		registryBuilder.disableSaving();
		BODY_PARTS = event.create(registryBuilder);
	}
}
