
package bee.beeshroom.ComfyCozy.entity;

import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import bee.beeshroom.ComfyCozy.advancements.GoldAppleLambTrigger;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.Reference;
import bee.beeshroom.ComfyCozy.util.handlers.ConfigHandler;
import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
//import bee.beeshroom.ComfyCozy.util.handlers.LootTableHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
//import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityOatmealSheep extends EntityAnimal implements net.minecraftforge.common.IShearable
{                                             
	//changed DYE_COLOR to OAT_FLAVOR
	private static final DataParameter<Boolean> SHEARED = EntityDataManager.<Boolean>createKey(EntityOatmealSheep.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Byte> OAT_FLAVOR = EntityDataManager.<Byte>createKey(EntityOatmealSheep.class, DataSerializers.BYTE);

    private int sheepTimer;
    public int oats;

    public EntityOatmealSheep(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.3F);
    }

    EntityAITempt temptTask;
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        temptTask = new EntityAITempt(this, 1.2D, false, getOatFlavor().getTemptationItems());
        this.tasks.addTask(3, temptTask);
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }
    
    /*public boolean isBreedingItem(ItemStack stack)
    {
        return TEMPTATION_ITEMS.contains(stack.getItem());
    } */
    
    public boolean canTrample(World world, Block block, BlockPos pos, float fallDistance)
    {
        return false;
    }

    protected void updateAITasks()
    {
        super.updateAITasks();
    }

    //
     // Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     // use this to react to sunlight and start to burn.
     //
    public void onLivingUpdate()
    {
        if (this.world.isRemote)
        {
            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
        }
        
        /*if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.9D;
        } */

        super.onLivingUpdate();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.5D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SHEARED, Boolean.valueOf(false));
        this.dataManager.register(OAT_FLAVOR, Byte.valueOf((byte)0));
    }

    //
     // Handler for {@link World#setEntityState}
    //
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 10)
        {
            this.sheepTimer = 40;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }
    
    public void changeOatFlavor(EntityPlayer player, EnumHand hand, EnumOatFlavor flavor)
    {
    	if (!ConfigHandler.OATSHEEPFLAVOR) return;
    	
    	setOatFlavor(flavor);
    	
    	if (flavor == EnumOatFlavor.GAPPLE && !player.world.isRemote && player instanceof EntityPlayerMP)
    	{
    		GoldAppleLambTrigger.INSTANCE.trigger((EntityPlayerMP)player);
    	}
    	
    	if (!player.capabilities.isCreativeMode && (player.getHeldItem(hand).isEmpty() || player.getHeldItem(hand).getItem() == flavor.getOatmealBowl()))
        {
            player.setHeldItem(hand, new ItemStack(Items.BOWL));
        }
        else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.BOWL)))
        {
            player.dropItem(new ItemStack(Items.BOWL), false);
        }
    	
    	this.playSound(SoundsHandler.OATMEAL, 1.0F, 1.0F);
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (this.oats > 0 && !this.getSheared() && itemstack.getItem() == Items.BOWL && this.getGrowingAge() >= 0)
        {
            itemstack.shrink(1);
            this.playSound(SoundsHandler.OATMEAL, 1.0F, 1.0F);
            
            if (this.oats > 0)
            {
            	--this.oats;
            }

            if (this.oats <= 0)
            {
                this.setSheared(true);
                this.oats += 1;
            }

            if (itemstack.isEmpty())
            {
                player.setHeldItem(hand, new ItemStack(getOatFlavor().getOatmealBowl()));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(getOatFlavor().getOatmealBowl())))
            {
                player.dropItem(new ItemStack(getOatFlavor().getOatmealBowl()), false);
            }

            return true;
        }
        
        //refill oat sheep
        else if (this.getSheared() && this.getGrowingAge() >= 0 && (itemstack.getItem() == getOatFlavor().getRefillItem() || itemstack.getItem() == getOatFlavor().getOatmealBowl()))
        {
        	if (itemstack.getItem() == getOatFlavor().getRefillItem())
    		{
	            itemstack.shrink(1);
    		}
        	if (itemstack.getItem() == getOatFlavor().getOatmealBowl() && !player.capabilities.isCreativeMode)
    		{
        		itemstack.shrink(1);
        		
	            if (itemstack.isEmpty())
	            {
	                player.setHeldItem(hand, new ItemStack(Items.BOWL));
	            }
	            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.BOWL)))
	            {
	                player.dropItem(new ItemStack(Items.BOWL), false);
	            }
    		}
        	
            this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
            this.setSheared(false);
            if (this.oats <= 0)
            {
	               this.oats += 1;
            } 
    
            return true;
        }
        
        else if (itemstack.getItem() == ModItems.OATMEAL && getOatFlavor() != EnumOatFlavor.NONE) 
        {
        	changeOatFlavor(player, hand, EnumOatFlavor.NONE);
        	return true;
        }
        else if (itemstack.getItem() == ModItems.STRAWBERRY_OATMEAL && getOatFlavor() != EnumOatFlavor.STRAWBERRY) 
        {
        	changeOatFlavor(player, hand, EnumOatFlavor.STRAWBERRY);
        	return true;
        }
        else if (itemstack.getItem() == ModItems.CINNAMON_OATMEAL && getOatFlavor() != EnumOatFlavor.CINNAMON) 
        {
        	changeOatFlavor(player, hand, EnumOatFlavor.CINNAMON);
        	return true;
        }
        else if (itemstack.getItem() == ModItems.PEACH_OATMEAL && getOatFlavor() != EnumOatFlavor.PEACH) 
        {
        	changeOatFlavor(player, hand, EnumOatFlavor.PEACH);
        	return true;
        }
        else if (itemstack.getItem() == ModItems.GOLD_APPLE_CINNAMON_OATMEAL && getOatFlavor() != EnumOatFlavor.GAPPLE) 
        {
        	if ((ConfigHandler.GAPPLESHEEP))
        	{
        		changeOatFlavor(player, hand, EnumOatFlavor.GAPPLE);
        	}
        	return true;
        }
        /// and anything after this was here before i started adding dumb stuff
        else
        {
            return super.processInteract(player, hand);
        }
    }
    

    public static void registerFixesOatmealSheep(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityOatmealSheep.class);
    }

    @SideOnly(Side.CLIENT)
    public float getHeadRotationPointY(float p_70894_1_)
    {
        if (this.sheepTimer <= 0)
        {
            return 0.0F;
        }
        else if (this.sheepTimer >= 4 && this.sheepTimer <= 36)
        {
            return 1.0F;
        }
        else
        {
            return this.sheepTimer < 4 ? ((float)this.sheepTimer - p_70894_1_) / 4.0F : -((float)(this.sheepTimer - 40) - p_70894_1_) / 4.0F;
        }
    }

    @SideOnly(Side.CLIENT)
    public float getHeadRotationAngleX(float p_70890_1_)
    {
        if (this.sheepTimer > 4 && this.sheepTimer <= 36)
        {
            float f = ((float)(this.sheepTimer - 4) - p_70890_1_) / 32.0F;
            return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
        }
        else
        {
            return this.sheepTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * 0.017453292F;
        }
    }

    //
     // (abstract) Protected helper method to write subclass entity data to NBT.
     //
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Sheared", this.getSheared());
        compound.setShort("oats", (short)this.oats);
        compound.setByte("Flavor", (byte)this.getOatFlavor().getMetadata());
    }

    //
     // (abstract) Protected helper method to read subclass entity data from NBT.
     //
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setSheared(compound.getBoolean("Sheared"));
        this.oats = compound.getShort("oats");
        this.setOatFlavor(EnumOatFlavor.byMetadata(compound.getByte("Flavor")));
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SHEEP_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_SHEEP_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SHEEP_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
    }
    
    public EnumOatFlavor getOatFlavor()
    {
        return EnumOatFlavor.byMetadata(((Byte)this.dataManager.get(OAT_FLAVOR)).byteValue());
    }

    //
     // Sets the oat flavor of this sheep
    //
    public void setOatFlavor(EnumOatFlavor flavor)
    {
        this.dataManager.set(OAT_FLAVOR, Byte.valueOf((byte)(flavor.getMetadata())));
        
        // Update temptation items
        this.tasks.removeTask(temptTask);
        
        temptTask = new EntityAITempt(this, 1.2D, false, getOatFlavor().getTemptationItems());
        this.tasks.addTask(3, temptTask);
    }

    // returns true if a sheeps wool has been sheared
    public boolean getSheared()
    {
        return ((Boolean)this.dataManager.get(SHEARED)).booleanValue();
    }

    // make a sheep sheared if set to true
    public void setSheared(boolean sheared)
    {
    	this.dataManager.set(SHEARED, Boolean.valueOf(sheared));
    } 

    public EntityOatmealSheep createChild(EntityAgeable ageable)
    {
        EntityOatmealSheep other_parent = (EntityOatmealSheep)ageable;
        EntityOatmealSheep child = new EntityOatmealSheep(this.world);
        if (this.rand.nextInt(2) == 0) {
        	child.setOatFlavor(this.getOatFlavor());
        }
        else {
        	child.setOatFlavor(other_parent.getOatFlavor());
        }
        return child;
    }

    //
     // Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
    // when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
    //
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.oats += 1;
        //this.setOatFlavor(getRandomOatFlavor(this.world.rand));
        return livingdata;
    }

    @Override public boolean isShearable(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos){ return !this.getSheared() && !this.isChild(); }
   
    @Override
    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        this.setSheared(true);
        int i = 4 + this.rand.nextInt(2);

        java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        for (int j = 0; j < i; ++j)
        	   //ret.add(new ItemStack(ModItems.OATS), 1, this.getOatFlavor().getMetadata());
        	ret.add(new ItemStack(ModItems.OATS));

        this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
        return ret;
    }

    public float getEyeHeight()
    {
        return 0.95F * this.height;
    }

    public void onStruckByLightning(EntityLightningBolt lightningBolt)
    {
    	if (!this.world.isRemote && !this.isDead)
        	for (int l = 0; l < 4; ++l)
	        {
		    	int i = MathHelper.floor(this.posX + (double)((float)(l % 2 * 2 - 1) * 0.25F));
		        int j = MathHelper.floor(this.posY);
		        int k = MathHelper.floor(this.posZ + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
	            BlockPos blockpos = new BlockPos(i, j, k);
	            
	            if (this.world.getBlockState(blockpos).getMaterial() == Material.AIR)
	            {
	            	this.setDead(); 
	            	this.world.setBlockState(blockpos, getOatFlavor().getDeathOatmealBowl().getDefaultState()); 
	            }
	        }
    }
}
