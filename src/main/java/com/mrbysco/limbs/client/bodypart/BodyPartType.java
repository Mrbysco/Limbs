package com.mrbysco.limbs.client.bodypart;

import com.google.common.base.Suppliers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BodyPartType {
	private final Supplier<ModelPart> inventoryPart;
	private final Supplier<ModelPart> bodyPart;
	private final Identifier texture;
	@Nullable
	private final Identifier secondTexture;

	public BodyPartType(Supplier<ModelPart> partSupplier, Identifier texture, @Nullable Identifier texture2) {
		this.texture = texture;
		this.inventoryPart = Suppliers.memoize(partSupplier::get);
		this.bodyPart = Suppliers.memoize(partSupplier::get);
		this.secondTexture = texture2;
	}

	public BodyPartType(Supplier<ModelPart> partSupplier, Identifier texture) {
		this(partSupplier, texture, null);
	}

	public ModelPart getInventoryPart() {
		return inventoryPart.get();
	}

	public ModelPart getBodyPart() {
		return bodyPart.get();
	}

	public Identifier getTexture() {
		return texture;
	}

	@Nullable
	public Identifier getSecondTexture() {
		return secondTexture;
	}
}
