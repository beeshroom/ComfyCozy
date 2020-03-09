package bee.beeshroom.ComfyCozy.entity.models;
//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;

import bee.beeshroom.ComfyCozy.entity.EntityMushy;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelParrot;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelMushy extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer cap;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
    private ModelMushy.State state = ModelMushy.State.STANDING;

	public ModelMushy() {
		textureWidth = 32;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 12, -3.0F, -9.0F, -3.0F, 6, 8, 6, 0.0F, false));

		cap = new ModelRenderer(this);
		cap.setRotationPoint(0.0F, 24.0F, 0.0F);
		cap.cubeList.add(new ModelBox(cap, 0, 0, -4.0F, -8.0F, -4.0F, 8, 4, 8, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(0.0F, 24.0F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 0, 0.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(0.0F, 24.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 4, -2.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		cap.render(f5);
		right_leg.render(f5);
		left_leg.render(f5);
	}
	public void setRotateAngles(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) 
    {
    	this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	//this.RearLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    	//this.RearRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    	
    	//this.VillagerHead.rotateAngleY = netHeadYaw * 0.017453292F;
    	//this.VillagerHead.rotateAngleX = headPitch * 0.017453292F;
    	
    	 if (this.state == ModelMushy.State.SITTING)
         {
             return;
         }
    }
	
	 public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
	    {
	     /*   this.body.rotateAngleX = 0.0F;
	        this.left_leg.rotateAngleX = -0.0299F;
	        this.right_leg.rotateAngleX = -0.0299F;
	        this.left_leg.rotationPointY = 22.0F;
	        this.right_leg.rotationPointY = 22.0F;*/

	        if (entitylivingbaseIn instanceof EntityMushy)
	        {
	            EntityMushy entitymushy = (EntityMushy)entitylivingbaseIn;

	            if (entitymushy.isSitting())
	            {
	                float f = 0.0F;
	                //this.body.rotationPointY = 0F;
	                //this.cap.rotationPointY = 0F;
	                this.body.offsetY = 0.16F;
	                this.cap.offsetY = 0.16F;
	            //    ++this.left_leg.rotationPointY;
	              //  ++this.right_leg.rotationPointY;
	                //++this.left_leg.rotateAngleX;
	                //++this.right_leg.rotateAngleX;
	                this.state = ModelMushy.State.SITTING;
	            }
	            else
	            {
	                this.state = ModelMushy.State.STANDING;
	                this.body.offsetY = 0.0F;
	                this.cap.offsetY = 0.0F;
	            }

	        //    this.left_leg.rotateAngleZ = 0.0F;
	       //     this.right_leg.rotateAngleZ = 0.0F;
	        }
	    }
	 
	 @SideOnly(Side.CLIENT)
	    static enum State
	    {
	        STANDING,
	        SITTING;
	    }
	
}