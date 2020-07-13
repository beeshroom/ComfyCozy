 // https://github.com/nivoridocs/strawgolem/blob/master/src/main/java/nivoridocs/strawgolem/StrawGolemCreationEventHandler.java
// credit to fradige95 on Curseforge / nivoridocs on github !! They graciously allowed me to sample their code. Thank-you!

package bee.beeshroom.ComfyCozy.events;

import bee.beeshroom.ComfyCozy.entity.EntityOatmealSheepGoldApple;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.util.handlers.ConfigHandler;
import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//credit to fradige95 on Curseforge / nivoridocs on github.

@EventBusSubscriber
public class ComfySummonEvent 
{
	@SubscribeEvent
	public static void onBlockPlaceEvent(BlockEvent.PlaceEvent event) 
	{
        World worldIn = event.getWorld();
        BlockPos pos = event.getPos();
        Block block = event.getState().getBlock();
        IBlockState iblockstate = worldIn.getBlockState(pos.north());
        
        BlockPos gapple;
        BlockPos plain;
        BlockPos cinnamon;
        BlockPos strawberry;
        BlockPos peach;
        
        //saving this  else if ((block == ModBlocks.BOWL_OATMEAL) || (block == ModBlocks.BOWL_CINNAMON) || (block == ModBlocks.BOWL_STRAWBERRY) || (block == ModBlocks.BOWL_PEACH))
        if ((block == ModBlocks.BOWL_GOLD_APPLE) && iblockstate.getBlock() == ModBlocks.BOWL_CINNAMON)
        {
            gapple = pos;
           plain = pos.east();
           strawberry = pos.south();
           peach = pos.west();
           cinnamon = pos.north();
        }
        else if ((block == ModBlocks.BOWL_GOLD_APPLE) && iblockstate.getBlock() == ModBlocks.BOWL_STRAWBERRY)
        {
            gapple = pos;
           plain = pos.west();
           strawberry = pos.north();
           peach = pos.east();
           cinnamon = pos.south();
        }
     /*   else if ((block == ModBlocks.BOWL_GOLD_APPLE) && iblockstate.getBlock() == ModBlocks.BOWL_PEACH)
        {
            gapple = pos;
            peach = pos.north();
            plain = pos.east();
            strawberry = pos.west();
            cinnamon = pos.south();
        }
        else if ((block == ModBlocks.BOWL_GOLD_APPLE) && iblockstate.getBlock() == ModBlocks.BOWL_CINNAMON)
        {
            gapple = pos;
            cinnamon = pos.north();
            peach = pos.east();
            plain = pos.west();
            strawberry = pos.south();
        }
        else if ((block == ModBlocks.BOWL_GOLD_APPLE) && iblockstate.getBlock() == ModBlocks.BOWL_STRAWBERRY)
        {
            gapple = pos;
            strawberry = pos.north();
            cinnamon = pos.east();
            peach = pos.west();
            plain = pos.south();
        } */
        
        else return;
 
        if(ConfigHandler.SECRET_SUMMON)
        { 
        if (checkStructure(worldIn, gapple, plain, cinnamon, strawberry, peach))
        {
            pos = gapple;
            worldIn.setBlockState(gapple, Blocks.AIR.getDefaultState());
            worldIn.setBlockState(plain, Blocks.AIR.getDefaultState());
            worldIn.setBlockState(cinnamon, Blocks.AIR.getDefaultState());
            worldIn.setBlockState(strawberry, Blocks.AIR.getDefaultState());
            worldIn.setBlockState(peach, Blocks.AIR.getDefaultState());

       /*     EntityLightningBolt EntityLightningBolt = new EntityLightningBolt(worldIn, 0, 0, 0, false);
            EntityLightningBolt.setPosition(getCoord(pos.getX()), pos.getY(), getCoord(pos.getZ())); 
            worldIn.spawnEntity(EntityLightningBolt); */
            
            EntityOatmealSheepGoldApple EntityOatmealSheepGoldApple = new EntityOatmealSheepGoldApple(worldIn);
            //EntityFurnaceGolem EntityFurnaceGolem = new EntityFurnaceGolem(worldIn);
            EntityOatmealSheepGoldApple.setPosition(getCoord(pos.getX()), pos.getY(), getCoord(pos.getZ())); 
            EntityOatmealSheepGoldApple.setGrowingAge(-29555);
            
            EntityOatmealSheepGoldApple.dropItem(Items.BOWL, 5);

            EntityOatmealSheepGoldApple.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 80, 1));
            
            worldIn.spawnEntity(EntityOatmealSheepGoldApple);
            EntityOatmealSheepGoldApple.oats += 3;      
            
            worldIn.playSound((EntityPlayer)null, pos, SoundsHandler.OATMEAL, SoundCategory.NEUTRAL, 0.3F, 0.1F);
            
         /*   EntityAreaEffectCloud EntityAreaEffectCloud = new EntityAreaEffectCloud(worldIn);
            EntityAreaEffectCloud.setPosition(getCoord(pos.getX()), pos.getY(), getCoord(pos.getZ())); 
            
            EntityAreaEffectCloud.addEffect(new PotionEffect(MobEffects.REGENERATION));
          //  EntityAreaEffectCloud.addEffect(new PotionEffect(MobEffects.ABSORPTION));
            
            worldIn.spawnEntity(EntityAreaEffectCloud); */
        }
        }
    }
	
	private static double getCoord(int c) 
	{
		return c + Math.signum(c)*0.0D;
	}

	private static boolean checkStructure(World worldIn, BlockPos gapple, BlockPos plain, BlockPos cinnamon, BlockPos strawberry, BlockPos peach) 
	{
		return worldIn.getBlockState(gapple).getBlock() == ModBlocks.BOWL_GOLD_APPLE
		    && worldIn.getBlockState(plain).getBlock() == ModBlocks.BOWL_OATMEAL
		    && worldIn.getBlockState(cinnamon).getBlock() == ModBlocks.BOWL_CINNAMON
		    && worldIn.getBlockState(strawberry).getBlock() == ModBlocks.BOWL_STRAWBERRY
		    && worldIn.getBlockState(peach).getBlock() == ModBlocks.BOWL_PEACH;
	}
	
	//private GolemEvent() {
//
		//
	//}
} 