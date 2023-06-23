package com.mrbysco.limbs;

import com.mrbysco.limbs.client.ClientHandler;
import com.mrbysco.limbs.config.LimbConfig;
import com.mrbysco.limbs.registry.LimbLootModifiers;
import com.mrbysco.limbs.registry.LimbRegistry;
import com.mrbysco.limbs.registry.PartRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod(Reference.MOD_ID)
public class Limbs {
	private static final Logger LOGGER = LogManager.getLogger();

	public Limbs() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LimbConfig.commonSpec);

		LimbRegistry.ITEMS.register(eventBus);
		LimbRegistry.CREATIVE_MODE_TABS.register(eventBus);
		LimbLootModifiers.GLM.register(eventBus);

		eventBus.addListener(this::sendImc);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::onClientSetup);
			MinecraftForge.EVENT_BUS.addListener(ClientHandler::onRenderArm);
			MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ClientHandler::onPlayerRenderPre);
			MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ClientHandler::onPlayerRenderPost);
			PartRegistry.BODY_PARTS.register(eventBus);
		});
	}

	public void sendImc(InterModEnqueueEvent event) {
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("head").icon(
				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("torso").icon(
				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("left_arm").icon(
				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("right_arm").icon(
				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("left_leg").icon(
				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("right_leg").icon(
				new ResourceLocation(CuriosApi.MODID, "slot/empty_curio_slot")).size(1).build());
	}
}
