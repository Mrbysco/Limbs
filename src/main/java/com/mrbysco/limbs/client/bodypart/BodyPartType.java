package com.mrbysco.limbs.client.bodypart;

import com.google.common.base.Suppliers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BodyPartType {
	private final Supplier<ModelPart> inventoryPart;
	private final Supplier<ModelPart> bodyPart;
	private final ResourceLocation texture;
	@Nullable
	private final ResourceLocation secondTexture;

	public BodyPartType(Supplier<ModelPart> partSupplier, ResourceLocation texture, @Nullable ResourceLocation texture2) {
		this.texture = texture;
		this.inventoryPart = Suppliers.memoize(partSupplier::get);
		this.bodyPart = Suppliers.memoize(partSupplier::get);
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
