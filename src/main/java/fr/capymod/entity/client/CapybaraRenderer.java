package fr.capymod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.capymod.commons.Commons;
import fr.capymod.entity.custom.CapybaraEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;

public class CapybaraRenderer extends MobRenderer<CapybaraEntity, CapybaraModel<CapybaraEntity>> {
    public CapybaraRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CapybaraModel<>(pContext.bakeLayer(ModModelLayers.CAPYBARA_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(CapybaraEntity capybaraEntity) {
        return new ResourceLocation(Commons.MOD_ID, "textures/entity/capybara.png");
    }


    @Override
    public void render(CapybaraEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if(pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
