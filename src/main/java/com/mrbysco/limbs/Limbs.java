package com.mrbysco.limbs;

import com.mrbysco.limbs.client.ClientHandler;
import com.mrbysco.limbs.config.LimbConfig;
import com.mrbysco.limbs.registry.LimbLootModifiers;
import com.mrbysco.limbs.registry.LimbRegistry;
import com.mrbysco.limbs.registry.PartRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Limbs.MOD_ID)
public class Limbs {
	public static final String MOD_ID = "limbs";
	private static final Logger LOGGER = LogManager.getLogger();

	public Limbs(IEventBus eventBus, ModContainer container, Dist dist) {
		container.registerConfig(ModConfig.Type.COMMON, LimbConfig.commonSpec);

		LimbRegistry.ITEMS.register(eventBus);
		LimbRegistry.CREATIVE_MODE_TABS.register(eventBus);
		LimbLootModifiers.GLM.register(eventBus);

		if (dist.isClient()) {
			eventBus.addListener(ClientHandler::onClientSetup);
			NeoForge.EVENT_BUS.addListener(ClientHandler::onRenderArm);
			NeoForge.EVENT_BUS.addListener(EventPriority.HIGH, ClientHandler::onPlayerRenderPre);
			NeoForge.EVENT_BUS.addListener(EventPriority.HIGH, ClientHandler::onPlayerRenderPost);
			PartRegistry.BODY_PARTS.register(eventBus);
		}
	}
}
