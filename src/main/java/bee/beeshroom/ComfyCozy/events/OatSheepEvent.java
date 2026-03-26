// https://github.com/nivoridocs/strawgolem/blob/master/src/main/java/nivoridocs/strawgolem/StrawGolemCreationEventHandler.java
// credit to fradige95 on Curseforge / nivoridocs on github !! They graciously allowed me to sample their code. Thank-you!

package bee.beeshroom.ComfyCozy.events;

import java.util.Random;

import bee.beeshroom.ComfyCozy.blocks.crops.oat_plant;
import bee.beeshroom.ComfyCozy.entity.EntityOatmealSheep;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.util.Reference;
import bee.beeshroom.ComfyCozy.util.handlers.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//credit to fradige95 on Curseforge / nivoridocs on github. this is just an edit of their golem spawn code.

@EventBusSubscriber(modid = Reference.MOD_ID)
public class OatSheepEvent {

	@SubscribeEvent
	public static void onBlockBreakEvent(BlockEvent.BreakEvent event) {
        World worldIn = event.getWorld();
        BlockPos pos = event.getPos();
        Block block = event.getState().getBlock();
        Random rnd = new Random();
        
        if (block != ModBlocks.OAT_PLANT)
            return;
        
        if (rnd.nextFloat() <= 0.05f && ConfigHandler.OAT_LAMBS && ((oat_plant)block).isMaxAge(worldIn.getBlockState(pos)))
        {
	        if (checkStructure(worldIn, pos) && worldIn.canSeeSky(pos)) {
	            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
	            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_SHEEP_AMBIENT, SoundCategory.BLOCKS, 0.5F, 1.6F);
	            EntityOatmealSheep EntityOatmealSheep = new EntityOatmealSheep(worldIn);
	            //EntityOatmealSheep.setGrowingAge(-22000);
	            EntityOatmealSheep.setGrowingAge(-29555);
	            EntityOatmealSheep.setPosition(getCoord(pos.getX()), pos.getY(), getCoord(pos.getZ()));
	            //EntityOatmealSheep.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, false, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, null);    
	            worldIn.spawnEntity(EntityOatmealSheep);
	        }
        }
    }
	private static double getCoord(int c) {
		//changed 0.5 to 0.0
		return c + Math.signum(c)*0.0D;
	}
	
	private static boolean checkStructure(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getBlock() == ModBlocks.OAT_PLANT;
	}
}
	//private GolemEvent() {
//
		//
	//}




/*

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class OatSheepEvent 
{
	@SubscribeEvent
	public static void onInteractWithBlock(PlayerInteractEvent.LeftClickBlock event)
	{
		EntityPlayer player = event.getEntityPlayer();
		if(player.getRidingEntity() != null)
		{
			return;
		}
		
		World worldIn = event.getWorld();
		BlockPos pos = event.getPos();
		Vec3d vec = new Vec3d(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		double maxDist = 3.5D;
		if((vec.x - player.posX) * (vec.x - player.posX) + (vec.y - player.posY) * (vec.y - player.posY) + (vec.z - player.posZ) * (vec.z - player.posZ) > maxDist * maxDist)
		{
			return;
		}
		
		IBlockState state = worldIn.getBlockState(pos);
		ItemStack mainStack = player.getHeldItemMainhand();
		ItemStack offStack = player.getHeldItemOffhand();
		if(!mainStack.isEmpty() || !offStack.isEmpty())
		{
			return;
		}
		
		if(state.getBlock() instanceof oat_plant) 
		{
			//List<SeatStair> seats = worldIn.getEntitiesWithinAABB(SeatStair.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));
			
			EntityOatmealSheep EntityOatmealSheep = new EntityOatmealSheep(worldIn);
			EntityOatmealSheep.setGrowingAge(-2000);
			worldIn.spawnEntity(EntityOatmealSheep);
		}
	}
	
	public static class Sheep extends Entity
	{
		public Sheep(World worldIn, BlockPos pos) 
		{
			this(worldIn);
			setPosition(pos.getX() + 0.5D, pos.getY() + 0.135D, pos.getZ() + 0.5D);
		}
		
		public Sheep(World worldIn) 
		{
			super(worldIn);
			//setSize(0.0F, 0.0F);
		}
		
		@Override
		protected void entityInit() 
		{
			
		}

		@Override
		protected void readEntityFromNBT(NBTTagCompound compound) 
		{
			
		}

		@Override
		protected void writeEntityToNBT(NBTTagCompound compound) 
		{
			
		}
	}
}*/
