package bee.beeshroom.ComfyCozy.entity.models;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/*
@SideOnly(Side.CLIENT)
public class ModelDirtyPig extends ModelQuadruped
{
    public ModelDirtyPig()
    {
        this(0.0F);
    }

    public ModelDirtyPig(float scale)
    {
        super(6, scale);
        this.head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1, scale);
        this.childYOffset = 4.0F;
    }
} */

@SideOnly(Side.CLIENT)
public class ModelDirtyPig extends ModelBase
{
	public ModelRenderer head  = new ModelRenderer(this, 0, 0);
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	//changed 8.0 to 4.0
	protected float childYOffset = 4.0F;
    protected float childZOffset = 4.0F;

	public ModelDirtyPig() 
	{	
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		
		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 13.0F, -7.0F);
		head.cubeList.add(new ModelBox(head, 0, 24, -4.0F, -5.0F, -7.0F, 8, 8, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 10, -2.0F, -1.0F, -8.0F, 4, 3, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 0, 1.0F, -8.0F, -5.0F, 2, 3, 0, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -5.0F, -14.0F, -8.0F, 10, 8, 16, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-3.0F, 18.0F, -5.0F);
		leg1.cubeList.add(new ModelBox(leg1, 36, 0, -2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(3.0F, 18.0F, -5.0F);
		leg2.cubeList.add(new ModelBox(leg2, 0, 0, -2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-4.0F, 18.0F, 7.0F);
		leg3.cubeList.add(new ModelBox(leg3, 0, 40, -1.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F, false));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(3.0F, 18.0F, 8.0F);
		leg4.cubeList.add(new ModelBox(leg4, 32, 32, -2.0F, 0.0F, -3.0F, 4, 6, 4, 0.0F, false));
	}

/*	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
	} */
	
	  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	    {
	        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

	        if (this.isChild)
	        {
	            float f = 2.0F;
	            GlStateManager.pushMatrix();
	            GlStateManager.translate(0.0F, this.childYOffset * scale, this.childZOffset * scale);
	            this.head.render(scale);
	            GlStateManager.popMatrix();
	            GlStateManager.pushMatrix();
	            GlStateManager.scale(0.5F, 0.5F, 0.5F);
	            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
	            this.body.render(scale);
	            this.leg1.render(scale);
	            this.leg2.render(scale);
	            this.leg3.render(scale);
	            this.leg4.render(scale);
	            GlStateManager.popMatrix();
	        }
	        else
	        {
	            this.head.render(scale);
	            this.body.render(scale);
	            this.leg1.render(scale);
	            this.leg2.render(scale);
	            this.leg3.render(scale);
	            this.leg4.render(scale);
	        }
	    }

/*	    public ModelDirtyPig(float scale)
	    {
	        super(6, scale);
	        this.head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1, scale);
	        this.childYOffset = 4.0F;
	    } */
	
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
    {
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
} 