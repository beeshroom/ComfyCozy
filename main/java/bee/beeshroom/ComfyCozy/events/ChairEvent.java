//Thankyou TurtyWurty. https://www.youtube.com/watch?v=vZgYjHwKcHA 

package bee.beeshroom.ComfyCozy.events;

import java.util.List;

import bee.beeshroom.ComfyCozy.blocks.furniture.cushion_diamond;
import bee.beeshroom.ComfyCozy.blocks.furniture.cushion_fire;
import bee.beeshroom.ComfyCozy.blocks.furniture.cushion_pika;
import bee.beeshroom.ComfyCozy.blocks.furniture.cushion_porg;
import bee.beeshroom.ComfyCozy.blocks.furniture.cushion_zigzag;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ChairEvent 
{
	@SubscribeEvent
	public static void onInteractWithBlock(PlayerInteractEvent.RightClickBlock event)
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
		
		if(state.getBlock() instanceof cushion_fire || state.getBlock() instanceof cushion_zigzag || state.getBlock() instanceof cushion_porg || state.getBlock() instanceof cushion_pika || state.getBlock() instanceof cushion_diamond )
		{
			//List<SeatStair> seats = worldIn.getEntitiesWithinAABB(SeatStair.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));
			Seat seat = new Seat(worldIn, pos);
			worldIn.spawnEntity(seat);
			player.startRiding(seat);
		}
	}
	
	public static class Seat extends Entity
	{
		public Seat(World worldIn, BlockPos pos) 
		{
			this(worldIn);
			setPosition(pos.getX() + 0.5D, pos.getY() + 0.135D, pos.getZ() + 0.5D);
		}
		
		public Seat(World worldIn) 
		{
			super(worldIn);
			setSize(0.0F, 0.0F);
		}
		
		@Override
		public void onUpdate() 
		{
			super.onUpdate();
			BlockPos pos = getPosition();
			if(!(getEntityWorld().getBlockState(pos).getBlock() instanceof cushion_fire || getEntityWorld().getBlockState(pos).getBlock() instanceof cushion_zigzag || getEntityWorld().getBlockState(pos).getBlock() instanceof cushion_porg || getEntityWorld().getBlockState(pos).getBlock() instanceof cushion_pika || getEntityWorld().getBlockState(pos).getBlock() instanceof cushion_diamond))
			{
				setDead();
				return;
			}
			
			List<Entity> passengers = getPassengers();
			if(passengers.isEmpty())
			{
				setDead();
			}
			for(Entity entity : passengers)
			{
				if(entity.isSneaking())
				{
					setDead();
				}
			}
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
}