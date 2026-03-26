 // https://github.com/nivoridocs/strawgolem/blob/master/src/main/java/nivoridocs/strawgolem/StrawGolemCreationEventHandler.java
// credit to fradige95 on Curseforge / nivoridocs on github !! They graciously allowed me to sample their code. Thank-you!

package bee.beeshroom.ComfyCozy.events;

import java.util.logging.Logger;

import bee.beeshroom.ComfyCozy.advancements.GoldAppleLambTrigger;
import bee.beeshroom.ComfyCozy.entity.EntityOatmealSheep;
import bee.beeshroom.ComfyCozy.entity.EnumOatFlavor;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.util.Reference;
import bee.beeshroom.ComfyCozy.util.handlers.ConfigHandler;
import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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

@EventBusSubscriber(modid = Reference.MOD_ID)
public class ComfySummonEvent 
{
	@SubscribeEvent
	public static void onBlockPlaceEvent(BlockEvent.PlaceEvent event) 
	{
		if (!ConfigHandler.SECRET_SUMMON) return;
		
        World worldIn = event.getWorld();
        BlockPos pos = event.getPos();
        Block block = event.getState().getBlock();
        //IBlockState iblockstate = worldIn.getBlockState(pos.north());
        
        BlockPos gapple;
        BlockPos east;
        BlockPos south;
        BlockPos west;
        BlockPos north;
        
        //saving this  else if ((block == ModBlocks.BOWL_OATMEAL) || (block == ModBlocks.BOWL_CINNAMON) || (block == ModBlocks.BOWL_STRAWBERRY) || (block == ModBlocks.BOWL_PEACH))
        if (block == ModBlocks.BOWL_GOLD_APPLE)
        {
            gapple = pos;
            east = pos.east();
            south = pos.south();
            west = pos.west();
            north = pos.north();
        }
        else return;
        
        if (checkStructure(worldIn, east, south, west, north))
        {
            pos = gapple;
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            worldIn.setBlockState(east, Blocks.AIR.getDefaultState());
            worldIn.setBlockState(south, Blocks.AIR.getDefaultState());
            worldIn.setBlockState(west, Blocks.AIR.getDefaultState());
            worldIn.setBlockState(north, Blocks.AIR.getDefaultState());

       /*     EntityLightningBolt EntityLightningBolt = new EntityLightningBolt(worldIn, 0, 0, 0, false);
            EntityLightningBolt.setPosition(getCoord(pos.getX()), pos.getY(), getCoord(pos.getZ())); 
            worldIn.spawnEntity(EntityLightningBolt); */
            
            EntityPlayer player = event.getPlayer();
            if (!worldIn.isRemote && player instanceof EntityPlayerMP) 
            {
            	GoldAppleLambTrigger.INSTANCE.trigger((EntityPlayerMP)player);
            }
            
            EntityOatmealSheep EntityOatmealSheepGoldApple = new EntityOatmealSheep(worldIn);
            
            EntityOatmealSheepGoldApple.setOatFlavor(EnumOatFlavor.GAPPLE);
            EntityOatmealSheepGoldApple.setPosition(getCoord(pos.getX()), pos.getY(), getCoord(pos.getZ())); 
            EntityOatmealSheepGoldApple.setGrowingAge(-29555);
            
            EntityOatmealSheepGoldApple.dropItem(Items.BOWL, 5);

            EntityOatmealSheepGoldApple.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 30, 1));
            
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
	
	private static double getCoord(int c) 
	{
		return c + Math.signum(c)*0.0D;
	}

	private static boolean checkStructure(World worldIn, BlockPos east, BlockPos south, BlockPos west, BlockPos north) 
	{
		boolean oatmeal = false; boolean strawberry = false; boolean cinnamon = false; boolean peach = false;
		Block eastBlock = worldIn.getBlockState(east).getBlock();
		Block southBlock = worldIn.getBlockState(south).getBlock();
		Block westBlock = worldIn.getBlockState(west).getBlock();
		Block northBlock = worldIn.getBlockState(north).getBlock();
		
		if (eastBlock == ModBlocks.BOWL_OATMEAL 
				|| southBlock == ModBlocks.BOWL_OATMEAL
				|| westBlock == ModBlocks.BOWL_OATMEAL
				|| northBlock == ModBlocks.BOWL_OATMEAL)
			oatmeal = true;
		
		if (eastBlock == ModBlocks.BOWL_STRAWBERRY 
				|| southBlock == ModBlocks.BOWL_STRAWBERRY
				|| westBlock == ModBlocks.BOWL_STRAWBERRY
				|| northBlock == ModBlocks.BOWL_STRAWBERRY)
			strawberry = true;

		if (eastBlock == ModBlocks.BOWL_CINNAMON 
				|| southBlock == ModBlocks.BOWL_CINNAMON
				|| westBlock == ModBlocks.BOWL_CINNAMON
				|| northBlock == ModBlocks.BOWL_CINNAMON)
			cinnamon = true;
		
		if (eastBlock == ModBlocks.BOWL_PEACH 
				|| southBlock == ModBlocks.BOWL_PEACH
				|| westBlock == ModBlocks.BOWL_PEACH
				|| northBlock == ModBlocks.BOWL_PEACH)
			peach = true;
		
		return oatmeal && strawberry && cinnamon && peach;
	}
	
	//private GolemEvent() {
//
		//
	//}
} 