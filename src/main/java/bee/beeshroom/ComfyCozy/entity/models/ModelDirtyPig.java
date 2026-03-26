package bee.beeshroom.ComfyCozy.entity.models;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelPig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDirtyPig extends ModelPig
{
	public ModelDirtyPig() 
	{
		this(0.0F);
	}
	
	public ModelDirtyPig(float scale) 
	{
		super(scale);
		head.cubeList.add(new ModelBox(head, 0, 0, 1.0F, -7.0F, -6.0F, 2, 3, 0, 0.0F));
	}
}