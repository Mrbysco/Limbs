package com.mrbysco.limbs.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.function.Consumer;

public class PartItem extends Item implements ICurioItem {
	private final PartLocation partLocation;
	private final ResourceLocation partRegistry;

	public PartItem(Properties properties, PartLocation partLocation, ResourceLocation partRegistry) {
		super(properties);
		this.partLocation = partLocation;
		this.partRegistry = partRegistry;
	}

	public PartLocation getPartLocation() {
		return partLocation;
	}

	public ResourceLocation getPartRegistry() {
		return partRegistry;
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {

			@Override
			public net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new com.mrbysco.limbs.client.PartItemInventoryRenderer(new net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context(
						net.minecraft.client.Minecraft.getInstance().getBlockEntityRenderDispatcher(),
						net.minecraft.client.Minecraft.getInstance().getBlockRenderer(),
						net.minecraft.client.Minecraft.getInstance().getItemRenderer(),
						net.minecraft.client.Minecraft.getInstance().getEntityRenderDispatcher(),
						net.minecraft.client.Minecraft.getInstance().getEntityModels(),
						net.minecraft.client.Minecraft.getInstance().font
				));
			}
		});
	}
}
