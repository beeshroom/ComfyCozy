package bee.beeshroom.ComfyCozy.entity;

import bee.beeshroom.ComfyCozy.entity.models.ModelMushy;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMushy extends RenderLiving<EntityMushy>
{
	//public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/mushy.png");
	  public static final ResourceLocation[] MUSHY_TEXTURES = new ResourceLocation[] {new ResourceLocation(Reference.MOD_ID + ":textures/entity/mushy_red.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/mushy_brown.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/mushy_truffle.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/mushy_yellow.png"), new ResourceLocation(Reference.MOD_ID + ":textures/entity/mushy_white.png")};
	
	  public RenderMushy(RenderManager p_i47375_1_)
	    {
	        super(p_i47375_1_, new ModelMushy(), 0.5F);
	    }
	
	@Override
	protected ResourceLocation getEntityTexture(EntityMushy entity)
    {
        return MUSHY_TEXTURES[entity.getVariant()];
    }

	@Override
	protected void applyRotations(EntityMushy entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}