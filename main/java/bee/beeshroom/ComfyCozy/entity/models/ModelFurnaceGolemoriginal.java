/*package bee.beeshroom.ComfyCozy.entity.models;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFurnaceGolem extends ModelBase {
	private final ModelRenderer leg4;
	private final ModelRenderer leg3;
	private final ModelRenderer leg2;
	private final ModelRenderer leg1;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer tail;

	public ModelFurnaceGolem() {
		textureWidth = 128;
		textureHeight = 128;

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(0.0F, 24.0F, -11.0F);
		leg4.cubeList.add(new ModelBox(leg4, 48, 48, -7.0F, -3.0F, 2.0F, 5, 3, 5, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg3.cubeList.add(new ModelBox(leg3, 48, 48, 2.0F, -3.0F, 2.0F, 5, 3, 5, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg2.cubeList.add(new ModelBox(leg2, 36, 36, 2.0F, -3.0F, -7.0F, 5, 3, 5, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg1.cubeList.add(new ModelBox(leg1, 48, 48, -7.0F, -3.0F, -7.0F, 5, 3, 5, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -8.0F, -18.0F, -8.0F, 16, 16, 16, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 32, -6.0F, -29.0F, -6.0F, 12, 11, 12, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(tail, -0.1745F, 0.0F, 0.0F);
		tail.cubeList.add(new ModelBox(tail, 0, 0, -0.5F, -7.0F, 6.5F, 0, 5, 7, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		leg4.render(f5);
		leg3.render(f5);
		leg2.render(f5);
		leg1.render(f5);
		body.render(f5);
		head.render(f5);
		tail.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) 
    {
    	this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    	this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    	
    	 this.head.rotateAngleX = headPitch * 0.017453292F;
         this.head.rotateAngleY = netHeadYaw * 0.017453292F;
    }
}*/