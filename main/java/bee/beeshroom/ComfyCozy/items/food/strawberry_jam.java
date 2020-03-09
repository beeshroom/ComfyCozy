package bee.beeshroom.ComfyCozy.items.food;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class strawberry_jam extends ItemFood
{
	private Item containerItem;
	
	public strawberry_jam(String name, int amount, float saturation, boolean isWolfFood) 
	{
		super(amount, saturation, isWolfFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.comfycozytab);
		//setAlwaysEdible();
		 this.setMaxStackSize(1);
		 setContainerItem(Items.GLASS_BOTTLE);
		
		ModItems.ITEMS.add(this);
	}

/*	public boolean hasContainerItem() {
		return true;
	}
	
	public Item setContainerItem(Item containerItem) {
		setContainerItem(Items.BOTTLE)
		ModItems.STRAWBERRY_JAM.setContainerItem(ModItems.ItemWhatever);
	}*/

/*	 @SideOnly(Side.CLIENT)
	    public ItemStack getDefaultInstance()
	    {
	        return PotionUtils.addPotionToItemStack(super.getDefaultInstance(), PotionTypes.WATER);
	    }*/
	
	 public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	    {
	        super.onItemUseFinish(stack, worldIn, entityLiving);
	        return new ItemStack(Items.GLASS_BOTTLE);
	       // return spawnAsEntity(worldIn, pos, new ItemStack(Items.GLASS_BOTTLE));
	    }
	 
	  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving, EntityPlayer player)
	    {
	        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;

	        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
	        {
	            stack.shrink(1);
	        }

	        if (entityplayer instanceof EntityPlayerMP)
	        {
	            CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
	            player.getFoodStats().addStats(6, .52F);
	        }

	        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
	        {
	            if (stack.isEmpty())
	            {
	                return new ItemStack(Items.GLASS_BOTTLE);
	            }

	            if (entityplayer != null)
	            {
	                entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
	            }
	        }

	        return stack;
	    }

	  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	    {
	        playerIn.setActiveHand(handIn);
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	    }
	
	  public EnumAction getItemUseAction(ItemStack stack)
	    {
	        return EnumAction.DRINK;
	    }
	  
	  public int getMaxItemUseDuration(ItemStack stack)
	    {
	        return 32;
	    }

}