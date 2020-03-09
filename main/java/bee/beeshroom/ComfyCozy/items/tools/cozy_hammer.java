package bee.beeshroom.ComfyCozy.items.tools;

import java.util.List;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class cozy_hammer extends Item
{
	public cozy_hammer(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.comfycozytab);
		setMaxStackSize(1);
		//setMaxDamage(durability);
		
		ModItems.ITEMS.add(this);
	}
	
	//turtywurty the absolute legend... thankyou for helping me;; i was so lost as to why i couldn't get it to work
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
	  //  System.out.println("BONK");
	    attacker.getEntityWorld().playSound((EntityPlayer)null, attacker.posX, attacker.posY, attacker.posZ, SoundsHandler.BONK, SoundCategory.PLAYERS, 0.5f, 0.3f);
	    return super.hitEntity(stack, target, attacker);
	} 
	
/*
	  public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
{
		  System.out.println("BONK");
		  World worldIn = null;
		this.BONK(attacker, worldIn);;
	
		 return true;
} 
	 
	 public void BONK(EntityLivingBase entityLivingBase, World worldIn)
	 {
	 System.out.println("BONK BONK");
	 worldIn.playSound(null, entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, SoundsHandler.BONK, SoundCategory.PLAYERS, 0.5F, 0.4F);
	 }
	 */
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) 
	{
		if(player.isSneaking())
		{
			world.getBlockState(pos).getBlock().rotateBlock(world, pos, side.getOpposite());
			world.playSound((EntityPlayer)null, pos, SoundsHandler.HAMMER, SoundCategory.BLOCKS, 0.5F, 0.6F);
			 //world.spawnParticle(EnumParticleTypes.END_ROD, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			  double d0 = itemRand.nextGaussian() * 0.02D;
              double d1 = itemRand.nextGaussian() * 0.02D;
              double d2 = itemRand.nextGaussian() * 0.02D;
              world.spawnParticle(EnumParticleTypes.END_ROD, (double)((float)pos.getX() + itemRand.nextFloat()), (double)pos.getY() + (double)itemRand.nextFloat(), (double)((float)pos.getZ() + itemRand.nextFloat()), d0, d1, d2);
			
			//player.getHeldItemMainhand().damageItem(1, player);
			return EnumActionResult.SUCCESS;
		}
		return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
	}
	
	 @Override
	    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	    {
	        super.addInformation(stack, worldIn, tooltip, flagIn);
	        tooltip.add("Right-click to adjust certain furniture. Sneak-right-click to rotate blocks.");
	    }
	 
	/* public int getMaxItemUseDuration(ItemStack stack)
	    {
	        return 12;
	    } */

	/* public boolean hitEntity(World worldIn, EntityPlayer playerIn, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	    {
		 //System.out.println("BONK BONK");
		 worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.BONK, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	        return true;
	    } */
	 

	 
	/* public ActionResult<ItemStack> onItemLeftClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	    {
	        ItemStack itemstack = playerIn.getHeldItem(handIn);

	        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.BONK, SoundCategory.PLAYERS, 0.5F, 0.4F);
	        
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	    } */
	 
	 
	 
	 
/* public boolean itemInteractionForEntity(World worldIn, ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
	    {
	        if (target instanceof EntityLivingBase)
	        {
	        	EntityLivingBase EntityLivingBase = (EntityLivingBase)target;
	            
	        	 worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.BONK, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

	            return true;
	        }
	        else
	        {
	            return false;
	        }
	    } */
	 
	 /* public boolean hitEntity(ItemStack stack, EntityPlayer player, Entity entity, World world, BlockPos pos)
	    {
		 
		  world.playSound(player, pos, SoundsHandler.BONK, SoundCategory.PLAYERS, 1.0F, 1.0F);
		 // world.playSound((EntityPlayer)null, pos, SoundsHandler.BONK, SoundCategory.BLOCKS, 0.8F, 0.6F);
		  return true;
	    }
	    */

}