/* package bee.beeshroom.comfycozy.events;

import bee.beeshroom.comfycozy.blocks.Cushion;
import bee.beeshroom.comfycozy.comfycozy;
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
public class ChairEvent
{
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		if(!event.getWorld().isRemote)
		{
			World world = event.getWorld();
			BlockPos pos = event.getPos();
			BlockState state = world.getBlockState(pos);
			Block block = world.getBlockState(pos).getBlock();
			PlayerEntity player = event.getPlayer();

			if((block instanceof Cushion) && player.getHeldItemMainhand().isEmpty() && world.getBlockState(pos.up()).isAir(world, pos.up()))
			{
				CushionEntity sit = new CushionEntity(world, pos);

					world.addEntity(sit);
					player.startRiding(sit);

			}
		}
	}

	@SubscribeEvent
	public static void onEntityMount(EntityMountEvent event)
	{
		if(!event.getWorldObj().isRemote && event.isDismounting())
		{
			Entity player = event.getEntityBeingMounted();

			if(player instanceof CushionEntity)
				player.remove();
		}
	}

private static boolean isValidBlock(World world, BlockPos pos, BlockState state, Block block)
        {
        boolean isValid = block instanceof Cushion;
        return isValid;
        }


        } */