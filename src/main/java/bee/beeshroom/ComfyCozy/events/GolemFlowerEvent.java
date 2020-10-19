package bee.beeshroom.comfycozy.events;

import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = comfycozy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GolemFlowerEvent {
    @SubscribeEvent
    public static void GolemFlower(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.getEntityWorld();
       // Random random = livingEntity.world.rand;

         BlockPos blockpos1 = livingEntity.getPosition();
        BlockState blockstate1 = world.getBlockState(blockpos1);
        Block block = blockstate1.getBlock();

        if (livingEntity instanceof GolemEntity)
        {

            if (!world.isRemote) {
                boolean flag = false;

                    if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(world, livingEntity)) {
                        BlockPos blockpos = new BlockPos(livingEntity);
                        BlockState blockstate = BlockInit.GOLEM_FLOWER.get().getDefaultState();

                        if (world.isAirBlock(blockpos) && blockstate.isValidPosition(world, blockpos)) {
                            world.setBlockState(blockpos, blockstate, 3);
                            flag = true;
                        }
                        //if (!world.isA && livingEntity instanceof SnowGolemEntity) {
                        if (block == Blocks.SNOW || block == Blocks.SNOW_BLOCK) {
                            world.setBlockState(blockpos, blockstate, 3);
                            flag = true;
                        }
                    }

              /*      if (!flag) {
                        ItemEntity itementity = new ItemEntity(world, livingEntity.getPosX(), livingEntity.getPosY(), livingEntity.getPosZ(), new ItemStack(Items.WITHER_ROSE));
                        world.addEntity(itementity);
                    } */


            }




        }

    }
}