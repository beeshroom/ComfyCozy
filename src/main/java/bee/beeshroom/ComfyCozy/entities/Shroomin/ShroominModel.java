package bee.beeshroom.comfycozy.entities.Shroomin;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ShroominModel<T extends ShroominEntity> extends AgeableModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer foot1;
	private final ModelRenderer foot2;

	public ShroominModel() {
		textureWidth = 80;
		textureHeight = 80;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 17.0F, 5.0F);
		head.setTextureOffset(33, 33).addBox(-5.5F, -7.0F, -10.0F, 11.0F, 2.0F, 11.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-7.5F, -5.0F, -12.0F, 15.0F, 6.0F, 15.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 20.0F, 1.0F);
		body.setTextureOffset(0, 21).addBox(-5.5F, -3.0F, -6.0F, 11.0F, 5.0F, 11.0F, 0.0F, false);

		foot1 = new ModelRenderer(this);
		foot1.setRotationPoint(-2.5F, 21.0F, 0.5F);
		foot1.setTextureOffset(0, 37).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

		foot2 = new ModelRenderer(this);
		foot2.setRotationPoint(2.5F, 21.0F, 0.5F);
		foot2.setTextureOffset(33, 21).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

	}

/*	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	} */

	/* @Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		foot1.render(matrixStack, buffer, packedLight, packedOverlay);
		foot2.render(matrixStack, buffer, packedLight, packedOverlay);
	} */

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
                       float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

        head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

        foot1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

        foot2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }


/*	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.body, this.foot1, this.foot2);
	}
	*/

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        //this.Body.rotateAngleZ = ((float)Math.PI / 2F);
        this.foot1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.foot2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of();
	}

	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.body, this.head, this.foot1, this.foot2);
	}

    @Override
    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);

		if (entityIn.isSitting())
		{
            head.setRotationPoint(0.0F, 19.0F, 5.0F);
            body.setRotationPoint(0.0F, 22.0F, 1.0F);

            foot1.setRotationPoint(-0.5F, 21.0F, -0.5F);
          //  setRotationAngle(foot1, -1.5708F, 0.4363F, 0.0F);

            foot2.setRotationPoint(2.5F, 21.0F, 0.5F);
         //   setRotationAngle(foot2, -1.5708F, -0.4363F, 0.0F); */
		}
		else
		{
			head.setRotationPoint(0.0F, 17.0F, 5.0F);
			body.setRotationPoint(0.0F, 20.0F, 1.0F);
			foot1.setRotationPoint(-2.5F, 21.0F, 0.5F);
			foot2.setRotationPoint(2.5F, 21.0F, 0.5F);
		}

    }
}