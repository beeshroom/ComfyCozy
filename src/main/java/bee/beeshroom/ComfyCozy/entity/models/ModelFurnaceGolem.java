package bee.beeshroom.ComfyCozy.entity.models;
//Made with Blockbench

import bee.beeshroom.ComfyCozy.entity.EntityFurnaceGolem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelFurnaceGolem extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer head;
	private final ModelRenderer tail;
	 // private ModelFurnaceGolem.State state = ModelFurnaceGolem.State.STANDING;

	public ModelFurnaceGolem() {
		textureWidth = 80;
		textureHeight = 80;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 15.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -7.0F, -7.0F, -7.0F, 14, 14, 14, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-3.5F, 20.5F, -3.5F);
		leg1.cubeList.add(new ModelBox(leg1, 48, 36, -2.5F, 0.5F, -2.5F, 5, 3, 5, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(3.5F, 20.5F, -3.5F);
		leg2.cubeList.add(new ModelBox(leg2, 43, 46, -2.5F, 0.5F, -2.5F, 5, 3, 5, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-3.5F, 20.5F, 3.5F);
		leg3.cubeList.add(new ModelBox(leg3, 42, 0, -2.5F, 0.5F, -2.5F, 5, 3, 5, 0.0F, false));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(3.5F, 20.5F, 3.5F);
		leg4.cubeList.add(new ModelBox(leg4, 36, 28, -2.5F, 0.5F, -2.5F, 5, 3, 5, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 6.8F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 28, -6.0F, -9.3F, -6.0F, 12, 11, 12, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 18.5F, 6.5F);
		setRotationAngle(tail, 0.0873F, 0.0F, 0.0F);
		tail.cubeList.add(new ModelBox(tail, 0, 0, -0.1F, 0.5F, -0.1F, 0, 5, 7, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
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
	    	this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	    	this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	    	this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	    	
	    	 this.head.rotateAngleX = headPitch * 0.017453292F;
	         this.head.rotateAngleY = netHeadYaw * 0.017453292F;
	    }
	  
/*	  public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
	    {
	        if (entitylivingbaseIn instanceof EntityFurnaceGolem)
	        {
	            EntityFurnaceGolem entityfurnacegolem = (EntityFurnaceGolem)entitylivingbaseIn;

	            if (entityfurnacegolem.isSitting())
	            {
	                float f = 0.0F;
	                //this.body.rotationPointY = 0F;
	                //this.cap.rotationPointY = 0F;
	                this.body.offsetY = 0.18F;
	                this.head.offsetY = 0.18F;
	            //    ++this.left_leg.rotationPointY;
	              //  ++this.right_leg.rotationPointY;
	                //++this.left_leg.rotateAngleX;
	                //++this.right_leg.rotateAngleX;
	                this.state = ModelFurnaceGolem.State.SITTING;
	            }
	            else
	            {
	                this.state = ModelFurnaceGolem.State.STANDING;
	                this.body.offsetY = 0.0F;
	                this.head.offsetY = 0.0F;
	            }
	        }}
	  
	  @SideOnly(Side.CLIENT)
	    static enum State
	    {
	        STANDING,
	        SITTING;
	    } */
}