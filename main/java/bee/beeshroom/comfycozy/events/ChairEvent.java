/* package bee.beeshroom.comfycozy.events;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

//@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ChairEvent
{
    @SubscribeEvent
    public static void onInteractWithBlock(PlayerInteractEvent.RightClickBlock event)
    {
        PlayerEntity item = event.getPlayer();
        if(item.getRidingEntity() != null)
        {
            return;
        }

        World worldIn = event.getWorld();
        BlockPos pos = event.getPos();
        Vec3d vec = new Vec3d(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        double maxDist = 3.5D;
        if((vec.x - item.getPosX()) * (vec.x - item.getPosX()) + (vec.y - item.getPosY()) * (vec.y - item.getPosY()) + (vec.z - item.getPosZ()) * (vec.z - item.getPosZ()) > maxDist * maxDist)
        {
            return;
        }

        BlockState state = worldIn.getBlockState(pos);
        ItemStack mainStack = item.getHeldItemMainhand();
        ItemStack offStack = item.getHeldItemOffhand();
        if(!mainStack.isEmpty() || !offStack.isEmpty())
        {
            return;
        }

        //if(state.getBlock() instanceof cushion_fire || state.getBlock() instanceof cushion_zigzag || state.getBlock() instanceof cushion_porg || state.getBlock() instanceof cushion_pika || state.getBlock() instanceof cushion_diamond )
        if(state.getBlock() instanceof cushion)
        {
            //List<SeatStair> seats = worldIn.getEntitiesWithinAABB(SeatStair.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));
            Seat seat = new Seat(worldIn, pos);
            worldIn.addEntity(seat);
          //  worldIn.spawnEntity(seat);
            item.startRiding(seat);
        }
    }

    public class Seat extends Entity
    {
        public Seat(World worldIn, BlockPos pos)
        {
            this(worldIn);
            setPosition(pos.getX() + 0.5D, pos.getY() + 0.135D, pos.getZ() + 0.5D);
        }

        public Seat(World worldIn)
        {
            super(worldIn);
           // setSize(0.0F, 0.0F);
        }

        @Override
        public void onUpdate()
        {
            super.onUpdate();
            BlockPos pos = getPosition();
            //	if(!(getEntityWorld().getBlockState(pos).getBlock() instanceof cushion_fire || getEntityWorld().getBlockState(pos).getBlock() instanceof cushion_zigzag || getEntityWorld().getBlockState(pos).getBlock() instanceof cushion_porg || getEntityWorld().getBlockState(pos).getBlock() instanceof cushion_pika || getEntityWorld().getBlockState(pos).getBlock() instanceof cushion_diamond))
            if(!(getEntityWorld().getBlockState(pos).getBlock() instanceof cushion))
            {
                this.remove();
                return;
            }

            List<Entity> passengers = getPassengers();
            if(passengers.isEmpty())
            {
                this.remove();
            }
            for(Entity entity : passengers)
            {
                if(entity.isSneaking())
                {
                    this.remove();
                }
            }
        }

        @Override
        protected void entityInit()
        {

        }

        @Override
        protected void readAdditional(CompoundNBT compound)
        {

        }

        @Override
        protected void writeAdditional(CompoundNBT compound)
        {

        }
    }
}*/