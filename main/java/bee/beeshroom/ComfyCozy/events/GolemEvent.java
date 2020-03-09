// https://github.com/nivoridocs/strawgolem/blob/master/src/main/java/nivoridocs/strawgolem/StrawGolemCreationEventHandler.java
// credit to fradige95 on Curseforge / nivoridocs on github !! They graciously allowed me to sample their code. Thank-you!

package bee.beeshroom.ComfyCozy.events;

import bee.beeshroom.ComfyCozy.entity.EntityFurnaceGolem;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//credit to fradige95 on Curseforge / nivoridocs on github.

@EventBusSubscriber
public class GolemEvent {

	@SubscribeEvent
	public static void onBlockPlaceEvent(BlockEvent.PlaceEvent event) {
        World worldIn = event.getWorld();
        BlockPos pos = event.getPos();
        Block block = event.getState().getBlock();
        
        BlockPos pumpkin;
        BlockPos furnace;
        
        if (block == Blocks.PUMPKIN) {
            pumpkin = pos;
            furnace = pos.down();
        } else if (block == Blocks.FURNACE) {
            pumpkin = pos.up();
            furnace = pos;
        } else return;
        
        if (checkStructure(worldIn, furnace, pumpkin)) {
            pos = furnace;
            worldIn.setBlockState(pumpkin, Blocks.AIR.getDefaultState());
            worldIn.setBlockState(furnace, Blocks.AIR.getDefaultState());
            EntityFurnaceGolem EntityFurnaceGolem = new EntityFurnaceGolem(worldIn);
            EntityFurnaceGolem.setPosition(getCoord(pos.getX()), pos.getY(), getCoord(pos.getZ()));
            worldIn.spawnEntity(EntityFurnaceGolem);
        }
    }
	private static double getCoord(int c) {
		return c + Math.signum(c)*0.5D;
	}
	
	private static boolean checkStructure(World worldIn, BlockPos furnace, BlockPos pumpkin) {
		return worldIn.getBlockState(furnace).getBlock() == Blocks.FURNACE
				&& worldIn.getBlockState(pumpkin).getBlock() == Blocks.PUMPKIN;
	}
	
	//private GolemEvent() {
//
		//
	//}
}