/* package bee.beeshroom.ComfyCozy.events;

import java.util.List;
import java.util.Random;

import com.google.common.base.Predicates;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@EventBusSubscriber
public class ShroomHitEvent {

	@Override
	public void onArmorTick(final World world, final EntityPlayer player, final ItemStack itemStack) {
		List<ItemStack> narmor = new ArrayList<ItemStack>();
		narmor.add(new ItemStack(ItemInit.BOOTS_MERTHEW));
		narmor.add(new ItemStack(ItemInit.LEGGINGS_MERTHEW));
		narmor.add(new ItemStack(ItemInit.CHESTPLATE_MERTHEW));
		narmor.add(new ItemStack(ItemInit.HELM_MERTHEW));
		
		List<ItemStack> armor = (List<ItemStack>) player.getArmorInventoryList();
		
		EntityPlayer#getItemStackFromSlot(EntityEquipmentSlot) {
			if (!player.isPotionActive(potionEffect.getPotion())) { // If the Potion isn't currently active,
				player.addPotionEffect(new PotionEffect(potionEffect)); // Apply a copy of the PotionEffect to the player
			}
		}
	}
	
	
	
	@SubscribeEvent
	  public void onUserHurt(EntityLivingBase user, Entity attacker, int level, EntityPlayer playerIn)
    {
		
     //   Random rnd = new Random();
     //   if (rnd.nextFloat() <= 0.01f)
		        Random random = user.getRNG();
		        
		    
		       // ItemStack itemstack = EnchantmentHelper.getEnchantedItem(Enchantments.THORNS, user);
		        ItemStack itemstack = playerIn.getslo
		        EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(itemstack);
		        ItemStack itemstack1 = playerIn.getItemStackFromSlot(entityequipmentslot);

		        if (shouldHit(level, random))
		        {
		            if (attacker != null)
		            {
		            	attacker.setItemStackToSlot(entityequipmentslot, itemstack.copy());
		               // attacker.attackEntityFrom(DamageSource.causeThornsDamage(user), (float)getDamage(level, random));
		                
		            }

		            if (!itemstack.isEmpty())
		            {
		                damageArmor(itemstack, 2, user);
		            }
		        }
		        else if (!itemstack.isEmpty())
		        {
		            damageArmor(itemstack, 1, user);
		        }
		    }

		    public static boolean shouldHit(int level, Random rnd)
		    {
		        if (level <= 0)
		        {
		            return false;
		        }
		        else
		        {
		            return rnd.nextFloat() < 0.15F * (float)level;
		        }
		    }

		/*    public static int getDamage(int level, Random rnd)
		    {
		        return level > 10 ? level - 10 : 1 + rnd.nextInt(4);
		    } */

/*		    private void damageArmor(ItemStack stack, int amount, EntityLivingBase entity)
		    {
		        int slot = -1;
		        int x = 0;
		        for (ItemStack i : entity.getArmorInventoryList())
		        {
		            if (i == stack){
		                slot = x;
		                break;
		            }
		            x++;
		        }
		        if (slot == -1 || !(stack.getItem() instanceof net.minecraftforge.common.ISpecialArmor))
		        {
		            stack.damageItem(1, entity);
		            return;
		        }
		        net.minecraftforge.common.ISpecialArmor armor = (net.minecraftforge.common.ISpecialArmor)stack.getItem();
		        armor.damageArmor(entity, stack, DamageSource.causeThornsDamage(entity), amount, slot);
		    }
		}
	*/