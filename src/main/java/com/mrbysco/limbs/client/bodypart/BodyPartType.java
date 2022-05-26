package com.mrbysco.limbs.client.bodypart;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.LazyLoadedValue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BodyPartType extends ForgeRegistryEntry<BodyPartType> {
	private final LazyLoadedValue<ModelPart> inventoryPart;
	private final LazyLoadedValue<ModelPart> bodyPart;
	private final ResourceLocation texture;
	@Nullable
	private final ResourceLocation secondTexture;

	public BodyPartType(Supplier<ModelPart> partSupplier, ResourceLocation texture, ResourceLocation texture2) {
		this.texture = texture;
		this.inventoryPart = DistExecutor.unsafeRunForDist(() -> () -> new LazyLoadedValue<>(() -> partSupplier.get()),
				() -> () -> null);
		this.bodyPart = DistExecutor.unsafeRunForDist(() -> () -> new LazyLoadedValue<>(() -> partSupplier.get()),
				() -> () -> null);
		this.secondTexture = texture2;
	}

	public BodyPartType(Supplier<ModelPart> partSupplier, ResourceLocation texture) {
		this(partSupplier, texture, null);
	}

	public ModelPart getInventoryPart() {
		return inventoryPart.get();
	}

	public ModelPart getBodyPart() {
		return bodyPart.get();
	}

	public ResourceLocation getTexture() {
		return texture;
	}

	@Nullable
	public ResourceLocation getSecondTexture() {
		return secondTexture;
	}
}
