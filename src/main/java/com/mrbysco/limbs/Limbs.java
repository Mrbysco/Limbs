package com.mrbysco.limbs;

import com.mrbysco.limbs.client.ClientHandler;
import com.mrbysco.limbs.client.bodypart.BodyPartRegistry;
import com.mrbysco.limbs.config.LimbConfig;
import com.mrbysco.limbs.registry.LimbLootModifiers;
import com.mrbysco.limbs.registry.LimbRegistry;
import com.mrbysco.limbs.registry.PartRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod(Limbs.MOD_ID)
public class Limbs {
	public static final String MOD_ID = "limbs";
	private static final Logger LOGGER = LogManager.getLogger();

	public Limbs() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LimbConfig.commonSpec);

		LimbRegistry.ITEMS.register(eventBus);
		LimbRegistry.CREATIVE_MODE_TABS.register(eventBus);
		LimbLootModifiers.GLM.register(eventBus);

//		eventBus.addListener(this::sendImc);

		if (FMLEnvironment.dist.isClient()) {
			eventBus.addListener(ClientHandler::onClientSetup);
			NeoForge.EVENT_BUS.addListener(ClientHandler::onRenderArm);
			NeoForge.EVENT_BUS.addListener(EventPriority.HIGH, ClientHandler::onPlayerRenderPre);
			NeoForge.EVENT_BUS.addListener(EventPriority.HIGH, ClientHandler::onPlayerRenderPost);
			PartRegistry.BODY_PARTS.register(eventBus);
		}
	}

//	public void sendImc(InterModEnqueueEvent event) {
//		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("head").icon(
//				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
//		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("torso").icon(
//				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
//		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("left_arm").icon(
//				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
//		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("right_arm").icon(
//				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
//		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("left_leg").icon(
//				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
//		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("right_leg").icon(
//				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
//	}
}
