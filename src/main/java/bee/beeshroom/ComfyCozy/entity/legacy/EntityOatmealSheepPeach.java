package bee.beeshroom.ComfyCozy.entity.legacy;

import bee.beeshroom.ComfyCozy.entity.EntityOatmealSheep;
import bee.beeshroom.ComfyCozy.entity.EnumOatFlavor;
import net.minecraft.world.World;

public class EntityOatmealSheepPeach extends EntityOatmealSheep {

	public EntityOatmealSheepPeach(World worldIn) {
		super(worldIn);
	}
	
	protected void entityInit()
    {
        super.entityInit();
        this.setOatFlavor(EnumOatFlavor.PEACH);
    }

}
