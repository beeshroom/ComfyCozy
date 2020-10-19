package bee.beeshroom.comfycozy.events;

import bee.beeshroom.comfycozy.blocks.Cushion;
import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.entities.Cushion.CushionEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.ref.Reference;
import java.util.List;

@Mod.EventBusSubscriber(modid = comfycozy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChairEvent {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getWorld().isRemote) {
            World world = event.getWorld();
            BlockPos pos = event.getPos();
            BlockState state = world.getBlockState(pos);
            Block block = world.getBlockState(pos).getBlock();
            PlayerEntity player = event.getPlayer();

            if ((block instanceof Cushion) && player.getHeldItemMainhand().isEmpty() && !CushionEntity.isInUse(world, pos) && world.getBlockState(pos.up()).isAir(world, pos.up())) {
                CushionEntity entity = new CushionEntity(world, pos);

                if(CushionEntity.trackUse(world, pos, entity))
                {
                    world.addEntity(entity);
                    player.startRiding(entity);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityMount(EntityMountEvent event) {
        if (!event.getWorldObj().isRemote && event.isDismounting()) {
            Entity player = event.getEntityBeingMounted();

            if (player instanceof CushionEntity && CushionEntity.stopTrackingUse(event.getWorldObj(), player.getPosition()))
            {
                player.remove();
            }
        }
    }

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event)
    {
        if(!event.getWorld().isRemote())
        {
            CushionEntity entity = CushionEntity.getTrackedCushion(event.getWorld(), event.getPos());

            if(entity != null && CushionEntity.stopTrackingUse(event.getWorld(), event.getPos()))
            {
                entity.remove();
            }
        }
    }

    private static boolean isValidBlock(World world, BlockPos pos, BlockState state, Block block) {
        boolean isValid = block instanceof Cushion;
        return isValid;
    }
}