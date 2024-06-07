package fr.capymod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.capymod.entity.animations.ModAnimationDefinitions;
import fr.capymod.entity.custom.CapybaraEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class CapybaraModel<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart whole;
	private final ModelPart head;

	public CapybaraModel(ModelPart root) {
		this.whole = root.getChild("whole");
		this.head = whole.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition whole = partdefinition.addOrReplaceChild("whole", CubeListBuilder.create(), PartPose.offset(0.0F, 42.0F, 0.0F));

		PartDefinition head = whole.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 21).addBox(-2.9F, -4.4F, -7.7F, 5.7F, 4.8F, 8.8F, new CubeDeformation(0.0F)), PartPose.offset(0.8F, -28.2F, -3.0F));

		PartDefinition ear = head.addOrReplaceChild("ear", CubeListBuilder.create(), PartPose.offset(-0.8F, 9.1F, 3.0F));

		PartDefinition left = ear.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = left.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(5, 7).addBox(-0.1F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2F, -12.8F, -3.4F, 0.0F, -0.3927F, 0.0F));

		PartDefinition right = ear.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 7).addBox(-3.4F, -2.0F, -0.8F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.8F, -3.4F, 0.0F, 0.3927F, 0.0F));

		PartDefinition body = whole.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.8F, -9.3F, -4.5F, 7.0F, 6.5F, 13.2F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.1F, 0.0F));

		PartDefinition front_right_leg = whole.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(28, 0).addBox(-1.1F, -0.2F, -1.0F, 2.3F, 4.4F, 2.2F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -22.3F, -3.4F));

		PartDefinition front_left_leg = whole.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(22, 21).addBox(-1.1F, -0.3F, -1.0F, 2.3F, 4.4F, 2.2F, new CubeDeformation(0.0F)), PartPose.offset(2.8F, -22.2F, -3.4F));

		PartDefinition back_right_leg = whole.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(0, 21).addBox(-1.2F, -0.2F, -1.2F, 2.3F, 4.4F, 2.2F, new CubeDeformation(0.0F)), PartPose.offset(-1.4F, -22.3F, 7.5F));

		PartDefinition back_left_leg = whole.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-1.4F, 0.0F, -1.2F, 2.3F, 4.4F, 2.2F, new CubeDeformation(0.0F)), PartPose.offset(3.1F, -22.5F, 7.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.applyHeadRotation(netHeadYaw, headPitch);

		if(limbSwingAmount > 0){
			this.animateWalk(ModAnimationDefinitions.WALK, limbSwing, limbSwingAmount, 2f, 1f);
		} else if(((CapybaraEntity)entity).isInSittingPose()) {
			this.animate(((CapybaraEntity)entity).sittingAnimationState, ModAnimationDefinitions.SIT, ageInTicks, 1f);
		}
		else {
			this.animate(((CapybaraEntity)entity).idleAnimationState, ModAnimationDefinitions.SLEEP, ageInTicks, 1f);
		}

	}

	private void applyHeadRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30F, 30F);
		headPitch = Mth.clamp(headPitch, -25F, 45F);

		this.head.yRot = headYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		whole.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return whole;
	}
}