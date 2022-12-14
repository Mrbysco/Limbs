package com.mrbysco.limbs;

import com.mrbysco.limbs.client.ClientHandler;
import com.mrbysco.limbs.config.LimbConfig;
import com.mrbysco.limbs.registry.LimbLootModifiers;
import com.mrbysco.limbs.registry.LimbRegistry;
import com.mrbysco.limbs.registry.PartRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

import java.util.List;

@Mod(Reference.MOD_ID)
public class Limbs {
	private static final Logger LOGGER = LogManager.getLogger();

	public Limbs() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LimbConfig.commonSpec);

		LimbRegistry.ITEMS.register(eventBus);
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

	private static CreativeModeTab TAB;

	@SubscribeEvent
	public void registerCreativeTabs(final CreativeModeTabEvent.Register event) {
		TAB = event.registerCreativeModeTab(new ResourceLocation(Reference.MOD_ID, "tab"), builder ->
				builder.icon(() -> new ItemStack(LimbRegistry.DROWNED_LIMBS.getHead()))
						.title(Component.translatable("itemGroup.limbs.tab"))
						.displayItems((features, output, hasPermissions) -> {
							List<ItemStack> stacks = LimbRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
							output.acceptAll(stacks);
						}));
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
