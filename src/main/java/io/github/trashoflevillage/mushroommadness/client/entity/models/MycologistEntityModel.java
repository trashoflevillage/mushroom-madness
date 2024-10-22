package io.github.trashoflevillage.mushroommadness.client.entity.models;

import io.github.trashoflevillage.mushroommadness.entity.animation.MycologistEntityAnimations;
import io.github.trashoflevillage.mushroommadness.entity.custom.MycologistEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class MycologistEntityModel<T extends MycologistEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart nose;
	private final ModelPart arms;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart rightArm;
	private final ModelPart rightItem;
	private final ModelPart leftArm;
	private final ModelPart head2;
	private final ModelPart nose2;
	public MycologistEntityModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.nose = this.head.getChild("nose");
		this.arms = this.body.getChild("arms");
		this.leg0 = this.body.getChild("leg0");
		this.leg1 = this.body.getChild("leg1");
		this.rightArm = this.body.getChild("rightArm");
		this.rightItem = this.rightArm.getChild("rightItem");
		this.leftArm = this.body.getChild("leftArm");
		this.head2 = this.head.getChild("head2");
		this.nose2 = this.head2.getChild("nose2");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 20).cuboid(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 38).cuboid(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData nose = head.addChild("nose", ModelPartBuilder.create().uv(24, 0).cuboid(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

		ModelPartData arms = body.addChild("arms", ModelPartBuilder.create().uv(44, 22).cuboid(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F))
				.uv(44, 22).cuboid(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F))
				.uv(40, 38).cuboid(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.0F, -1.0F, -0.743F, 0.0F, 0.0F));

		ModelPartData leg0 = body.addChild("leg0", ModelPartBuilder.create().uv(0, 22).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData leg1 = body.addChild("leg1", ModelPartBuilder.create().uv(0, 22).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData rightArm = body.addChild("rightArm", ModelPartBuilder.create().uv(40, 46).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData rightItem = rightArm.addChild("rightItem", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, 6.0F, 0.5F));

		ModelPartData leftArm = body.addChild("leftArm", ModelPartBuilder.create().uv(40, 46).mirrored().cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData head2 = head.addChild("head2", ModelPartBuilder.create().uv(32, 7).cuboid(-3.0F, -6.0F, -4.0F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -3.0F, 1.0F, 0.1957F, 1.2207F, 0.2051F));

		ModelPartData nose2 = head2.addChild("nose2", ModelPartBuilder.create().uv(32, 2).cuboid(-1.0F, 0.0F, -5.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		body.render(matrices, vertices, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return body;
	}

	@Override
	public void setAngles(T illagerEntity, float f, float g, float h, float i, float j) {
		this.head.yaw = i * (float) (Math.PI / 180.0);
		this.head.pitch = j * (float) (Math.PI / 180.0);
		if (this.riding) {
			this.rightArm.pitch = (float) (-Math.PI / 5);
			this.rightArm.yaw = 0.0F;
			this.rightArm.roll = 0.0F;
			this.leftArm.pitch = (float) (-Math.PI / 5);
			this.leftArm.yaw = 0.0F;
			this.leftArm.roll = 0.0F;
			this.leg0.pitch = -1.4137167F;
			this.leg0.yaw = (float) (Math.PI / 10);
			this.leg0.roll = 0.07853982F;
			this.leg1.pitch = -1.4137167F;
			this.leg1.yaw = (float) (-Math.PI / 10);
			this.leg1.roll = -0.07853982F;
		} else {
			this.rightArm.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * g * 0.5F;
			this.rightArm.yaw = 0.0F;
			this.rightArm.roll = 0.0F;
			this.leftArm.pitch = MathHelper.cos(f * 0.6662F) * 2.0F * g * 0.5F;
			this.leftArm.yaw = 0.0F;
			this.leftArm.roll = 0.0F;
			this.leg0.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g * 0.5F;
			this.leg0.yaw = 0.0F;
			this.leg0.roll = 0.0F;
			this.leg1.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g * 0.5F;
			this.leg1.yaw = 0.0F;
			this.leg1.roll = 0.0F;
		}

		IllagerEntity.State state = illagerEntity.getState();
		if (state == IllagerEntity.State.ATTACKING) {
			if (illagerEntity.getMainHandStack().isEmpty()) {
				CrossbowPosing.meleeAttack(this.leftArm, this.rightArm, true, this.handSwingProgress, h);
			} else {
				CrossbowPosing.meleeAttack(this.rightArm, this.leftArm, illagerEntity, this.handSwingProgress, h);
			}
		} else if (state == IllagerEntity.State.SPELLCASTING) {
			this.rightArm.pivotZ = 0.0F;
			this.rightArm.pivotX = -5.0F;
			this.leftArm.pivotZ = 0.0F;
			this.leftArm.pivotX = 5.0F;
			this.rightArm.pitch = MathHelper.cos(h * 0.6662F) * 0.25F;
			this.leftArm.pitch = MathHelper.cos(h * 0.6662F) * 0.25F;
			this.rightArm.roll = (float) (Math.PI * 3.0 / 4.0);
			this.leftArm.roll = (float) (-Math.PI * 3.0 / 4.0);
			this.rightArm.yaw = 0.0F;
			this.leftArm.yaw = 0.0F;
		} else if (state == IllagerEntity.State.BOW_AND_ARROW) {
			this.rightArm.yaw = -0.1F + this.head.yaw;
			this.rightArm.pitch = (float) (-Math.PI / 2) + this.head.pitch;
			this.leftArm.pitch = -0.9424779F + this.head.pitch;
			this.leftArm.yaw = this.head.yaw - 0.4F;
			this.leftArm.roll = (float) (Math.PI / 2);
		} else if (state == IllagerEntity.State.CROSSBOW_HOLD) {
			CrossbowPosing.hold(this.rightArm, this.leftArm, this.head, true);
		} else if (state == IllagerEntity.State.CROSSBOW_CHARGE) {
			CrossbowPosing.charge(this.rightArm, this.leftArm, illagerEntity, true);
		} else if (state == IllagerEntity.State.CELEBRATING) {
			this.rightArm.pivotZ = 0.0F;
			this.rightArm.pivotX = -5.0F;
			this.rightArm.pitch = MathHelper.cos(h * 0.6662F) * 0.05F;
			this.rightArm.roll = 2.670354F;
			this.rightArm.yaw = 0.0F;
			this.leftArm.pivotZ = 0.0F;
			this.leftArm.pivotX = 5.0F;
			this.leftArm.pitch = MathHelper.cos(h * 0.6662F) * 0.05F;
			this.leftArm.roll = (float) (-Math.PI * 3.0 / 4.0);
			this.leftArm.yaw = 0.0F;
		}

		boolean bl = state == IllagerEntity.State.CROSSED;
		this.arms.visible = bl;
		this.leftArm.visible = !bl;
		this.rightArm.visible = !bl;
	}


	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}
}