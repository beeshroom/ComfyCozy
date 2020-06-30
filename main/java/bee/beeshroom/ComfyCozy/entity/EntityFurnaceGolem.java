package bee.beeshroom.ComfyCozy.entity;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFurnaceGolem extends EntityGolem //implements IAnimals
{	
	  public int timeUntilNextHeal;
	  private int fuel;
	  private boolean attacking;
    private int attackTimer;
   // private EntityAISit aiSit;
	 private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(EntityFurnaceGolem.class, DataSerializers.BOOLEAN);
	//private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.<Float>createKey(EntityFurnaceGolem.class, DataSerializers.FLOAT);
	 // private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Item.getItemFromBlock(Blocks.DIRT), Item.getItemFromBlock(Blocks.MYCELIUM), Item.getItemFromBlock(Blocks.GRASS));
	//  private EntityAISit aiSit;
	 //  private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.COAL);
	//private static final TileEntityFurnace tileentity = null;
	
	  protected void initEntityAI()
	    {
		/*  this.aiSit = new EntityAISit(this);
		     this.tasks.addTask(2, this.aiSit);
	        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F)); */
		  
	        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D, 0.0F));
	       // this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 1.0F));
	       // this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
	      //  this.tasks.addTask(8, new EntityAILookIdle(this));
	        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
	/////took this out/////    
	        
	        //i added it back in.....
	   //     this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
	       // this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, false, true, new Predicate<EntityLiving>()
	        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, true, false, IMob.MOB_SELECTOR));
	        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F)); //added this 
	       // this.tasks.addTask(8, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));
/*    {
	            public boolean apply(@Nullable EntityLiving p_apply_1_)
	            {
	                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
	            }
	        }));*/
	    }
	  
	
	  
	    public static void registerFixesFurnaceGolem(DataFixer fixer)
	    {
	        EntityLiving.registerFixesMob(fixer, EntityFurnaceGolem.class);
	    }
	    
	    protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
	        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
	        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.7D);
	      //  this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.1D);
	       // this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
	    }
	    
	    public EntityFurnaceGolem(World worldIn)
	    {
	        super(worldIn);
	        this.isImmuneToFire = true;

	        this.timeUntilNextHeal = 50 ;
	      //  this.timeUntilNextHeal = this.rand.nextInt(12) + 15 ;
	       // this.setTamed(false);
	    }

	    protected int decreaseAirSupply(int air)
	    {
	        return air;
	    }
	    
	    
	    public boolean canBePushed()
	    {
	        return true;
	    }
	    
	    protected void collideWithEntity(Entity entityIn)
	    {
	    	  if (!(entityIn instanceof EntityPlayer))
		        {
		            super.collideWithEntity(entityIn);
		        }
	    	if (entityIn instanceof IMob && this.getRNG().nextInt(20) == 0)
	        {
	            this.setAttackTarget((EntityLivingBase)entityIn);
	        }

	        super.collideWithEntity(entityIn);
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public void onLivingUpdate()
	    {
	        super.onLivingUpdate();

	     /*   if (!this.world.isRemote && this.getAttackTarget() == null && this.isAttacking())
	        {
	            this.setAttacking(false);
	        } */
	        if (this.attackTimer > 0)
	        {
	            --this.attackTimer;
	        }

	        if (!this.world.isRemote && --this.timeUntilNextHeal <= 0)
            {
	        	if (this.fuel > 0)
	        	{
                	
                	 this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
                	 this.heal(2f);
                	 this.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 1.0F, 1.0F);
                /*	 double d0 = this.rand.nextGaussian() * 0.02D;
                     double d1 = this.rand.nextGaussian() * 0.02D;
                     double d2 = this.rand.nextGaussian() * 0.02D;
                	   this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);  
                	*/
                	 this.timeUntilNextHeal = 50;
                	   // this.playSound(SoundsHandler.PLANT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }
            
              
                	 this.timeUntilNextHeal = 50;
            }
	        
	    }
	    

	    
	
	    public boolean attackEntityAsMob(Entity entityIn)
	    {
	        this.attackTimer = 10;
	        this.world.setEntityState(this, (byte)4);
	        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(2 + this.rand.nextInt(5)));

	        if (flag) 
	        {
	        	if (this.fuel > 0)
	            {
	            	
	        	//  this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	        //	this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
	        	//this.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 1.0F, 1.0F);
	        	/////////// this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	        	entityIn.setFire(3);
	           // entityIn.motionY += 0.4000000059604645D;
	            this.applyEnchantments(this, entityIn);
	            entityIn.motionX += 0.30D;
	            this.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 0.8F, 1.1F);
		        this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
		     
	        }
	        	if (this.fuel <= 0)
	            {
	            
	            this.applyEnchantments(this, entityIn);
	            entityIn.motionX += 0.1D;
	           // this.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 0.8F, 1.1F);
		    ///nah no particle   // this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
		     
	        }
	        }
	        
	        //this.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1.0F, 1.0F);
	////////        this.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 0.8F, 1.1F);
	 //////       this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
	      //  this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	      
	        return flag; 
	        
	    }

	    /**
	     * Handler for {@link World#setEntityState}
	     */
	    @SideOnly(Side.CLIENT)
	    public void handleStatusUpdate(byte id)
	    {
	        if (id == 4)
	        {
	            this.attackTimer = 10;
	            if (this.fuel > 0)
	            {
	            this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.8D, this.posZ, 0.0D, 0.0D, 0.0D);
	          //  this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	            this.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 1.0F, 1.0F);
	            }
	        /*    if (this.fuel <= 0)
	            {
	            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.8D, this.posZ, 0.0D, 0.0D, 0.0D);
	            } */
	        }
	        else
	        {
	            super.handleStatusUpdate(id);
	        }
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public int getAttackTimer()
	    {
	        return this.attackTimer;
	    }
	    
	    

	    
/*    public EntityFurnaceGolem(World worldIn, double x, double y, double z)
    {
    	super(worldIn, x, y, z);
    } */

 /*   public Item getItemDropped(IBlockState state, Random rand, int fortune, World worldIn, BlockPos pos)
    {
    	//TileEntity tileentity = worldIn.getTileEntity(pos);
       // InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityFurnace)tileentity);
        return Item.getItemFromBlock(Blocks.FURNACE);
    
    }*/
    
    public void fall(float distance, float damageMultiplier)
    {
    }

    @SideOnly(Side.CLIENT)
    protected SoundEvent getAmbientSound()
    {
        if (this.isAttacking())
        {
        	return SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE;
            //return SoundEvents.BLOCK_FIRE_AMBIENT;
        }
        else
        {
            //return SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE;
        	return null;
        }
    }
    
    
    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.BLOCK_STONE_BREAK;
    }

    @Nullable
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.BLOCK_STONE_BREAK;
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 120;
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }
    
    public float getEyeHeight()
    {
        return 1.6F;
    }
    
    @SideOnly(Side.CLIENT)  
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
        super.setAttackTarget(entitylivingbaseIn);

        if (entitylivingbaseIn == null)
        {
            this.setAttacking(false);
            //this.setSprinting(false);
         /*   if (this.fuel > 0)
            {
                --this.fuel;
            }  */
        }
        else
        {
        	
        	//added this if fuel
        	if (this.fuel > 0)
            {
            	this.setAttacking(true);
            }
            
            
        }
    }
   
    @SideOnly(Side.CLIENT)
    protected void updateAITasks()
    {
    	   
       if (this.isWet())
        {
            //this.attackEntityFrom(DamageSource.DROWN, 1.0F);
        	this.setAttacking(false);
        	this.attackTimer = 80;
        	this.setAttackTarget(null);
           // this.setSprinting(false);
            if (this.fuel > 0)
            {
                --this.fuel;
            }
        } 
      //  this.dataManager.set(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
        //super.updateAITasks();
    }
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Attacking", this.isAttacking());
        compound.setShort("Fuel", (short)this.fuel);
        compound.setInteger("HealTime", this.timeUntilNextHeal);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setAttacking(compound.getBoolean("Attacking"));
        this.fuel = compound.getShort("Fuel");
       // if (this.aiSit != null) { this.aiSit.setSitting(compound.getBoolean("Sitting")); }
        if (compound.hasKey("HealTime"))
        {
            this.timeUntilNextHeal = compound.getInteger("HealTime");
        }
    }
    
    
    
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
    	  ItemStack itemstack = player.getHeldItem(hand);
    	  
 //   if (!itemstack.isEmpty())
 //   {
     //   if (itemstack.getItem() instanceof Item)
    //    {
         //   Item item = (Item)itemstack.getItem();

            //if (super.processInitialInteract(player, hand)) return true;
            
    //Furnace Minecraft code referenced	  ///////took away !player.isSneaking() &&  bc it was breaking it maybe? ??? 
            if (this.fuel + 1500 <= 12000 && itemstack.getItem() == Items.COAL || itemstack.getItem() == Item.getItemFromBlock(Blocks.LOG) || itemstack.getItem() == Item.getItemFromBlock(Blocks.LOG2)) //&& ((Float)this.dataManager.get(DATA_HEALTH_ID)).floatValue() < 20.0F)
            {
                if (!player.capabilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }
          
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
                this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
           //     this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
          	  
                this.fuel += 1500;
                this.heal(5f);
                //this.setSprinting(true);
                
             //   this.spawnAsEntity(worldIn, pos, new ItemStack(Items.COAL, 2, 1));
                
             //   this.heal((float)item.getHealAmount(itemstack));
                //return true;
            }
            
          /////****************///////vvvvv////Unslash this when you want to try to make them tameaeable again ///vvvvv////************///////////
            
       ////////////////// TAME EVENT ///////     
     /*       if (!this.isTamed() && player.isSneaking())
            {
            //////////////////////  
            if (!this.world.isRemote)
            {
                    this.setTamedBy(player);
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte)7);
            }
         ////////////////////////
            }
            
            if (!this.world.isRemote && this.isTamed() && this.isOwner(player))
            {
                this.aiSit.setSitting(!this.isSitting());
                } */
      return true;
  
    
}
    
    @SideOnly(Side.CLIENT)
    public void onUpdate()
    {
        super.onUpdate();

        if (this.fuel > 0)
        {
        	this.setAttacking(true);
        	
            --this.fuel;
        }
        
        if (this.fuel <= 0)
        {
        	this.setAttacking(false);
        	this.setSprinting(false);
        }

        if (this.fuel > 0 && this.rand.nextInt(10) == 0)
        {
            this.setSprinting(true);
            this.heal(.05f);
            this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + 0.2D, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
          //  this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

/* BRIGHTNESS ? ? ? Caused crashes. so rip for now.
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
    	BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));

    	if (this.fuel > 0)
    	{
        return 528880;
    	}
    	if (this.world.isBlockLoaded(blockpos$mutableblockpos))
        {
            blockpos$mutableblockpos.setY(MathHelper.floor(this.posY + (double)this.getEyeHeight()));
            return this.world.getCombinedLight(blockpos$mutableblockpos, 0);
        }
        else
        {
            return 0;
        }
    } 

    
     // Gets how bright this entity is.
     
    @SideOnly(Side.CLIENT)
    public float getBrightness()
    {
    	BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));

        if (this.world.isBlockLoaded(blockpos$mutableblockpos))
        {
            blockpos$mutableblockpos.setY(MathHelper.floor(this.posY + (double)this.getEyeHeight()));
            return this.world.getLightBrightness(blockpos$mutableblockpos);
        }
    	if (this.fuel > 0)
    	{
        return 0.2F;
    	}
    	 else
         {
             return 0.0F;
         }
    	
    }
*/


	/* 
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityFurnace();
    }
    
   protected boolean processInteract(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	  if (!super.processInteract(playerIn, hand) && (worldIn.isRemote))
          {
              ItemStack itemstack = playerIn.getHeldItem(hand);
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityFurnace)
            {
                playerIn.displayGUIChest((TileEntityFurnace)tileentity);
                playerIn.addStat(StatList.FURNACE_INTERACTION);
            }

            return true;
        }
    } */
    
   /* public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = itemstack.getItem() == Items.NAME_TAG;

        if (flag)
        {
            itemstack.interactWithEntity(player, this, hand);
            return true;
        }
        else if (this.isEntityAlive()  && !player.isSneaking())
        {
            if (!this.world.isRemote)
            {
            	//TileEntity tileentity = pos.getTileEntity(this);
                player.displayGUIChest((TileEntityFurnace)tileentity);
            }
            else
            {
                return super.processInteract(player, hand);
            }

            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }
    
    public String getGuiID()
    {
        return "minecraft:furnace";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerChest(playerInventory, (IInventory) this, playerIn);
    } */
    
  
    
 /*   public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag)
        {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
            {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
            	entityIn.setFire(2 * (int)f);
            }
        }

        return flag;
    }*/
  
    @SideOnly(Side.CLIENT)
    public boolean isAttacking()
    {
        return ((Boolean)this.dataManager.get(ATTACKING)).booleanValue();
    }

    @SideOnly(Side.CLIENT)
    public void setAttacking(boolean attacking)
    {
        this.dataManager.set(ATTACKING, Boolean.valueOf(attacking));
    }
    
    @SideOnly(Side.CLIENT)
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(ATTACKING, Boolean.valueOf(false));
    }

    
  /*  static class BurnThem extends EntityAIBase
    {
        private final EntityFurnaceGolem parentEntity;
        public int attackTimer;

        public BurnThem(EntityFurnaceGolem entityfurnacegolem)
        {
            this.parentEntity = entityfurnacegolem;
        }

        
         // Returns whether the EntityAIBase should begin execution.
         
        public boolean shouldExecute()
        {
            return this.parentEntity.getAttackTarget() != null;
        }

        
         // Execute a one shot task or start executing a continuous task
         
        public void startExecuting()
        {
            this.attackTimer = 0;
        }

        
         // Reset the task's internal state. Called when this task is interrupted by another one
        
        public void resetTask()
        {
            this.parentEntity.setAttacking(false);
        }

        
         // Keep ticking a continuous task that has already been started
         
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
            double d0 = 64.0D;

            if (entitylivingbase.getDistanceSq(this.parentEntity) < 46.0D && this.parentEntity.canEntityBeSeen(entitylivingbase))
            {
                //World world = this.parentEntity.world;
                ++this.attackTimer;

              /*  if (this.attackTimer == 10)
                {
                   // world.playEvent((EntityPlayer)null, 1015, new BlockPos(this.parentEntity), 0);
                } */
//changed this from 20 to 10 idk
        /*        if (this.attackTimer == 10)
                {
                	*/
                  /*  double d1 = 4.0D;
                    Vec3d vec3d = this.parentEntity.getLook(1.0F);
                    double d2 = entitylivingbase.posX - (this.parentEntity.posX + vec3d.x * 4.0D);
                    double d3 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (0.5D + this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F));
                    double d4 = entitylivingbase.posZ - (this.parentEntity.posZ + vec3d.z * 4.0D);
                    world.playEvent((EntityPlayer)null, 1016, new BlockPos(this.parentEntity), 0);
                    EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, this.parentEntity, d2, d3, d4);
                  //  entitylargefireball.explosionPower = this.parentEntity.getFireballStrength();
                    entitylargefireball.posX = this.parentEntity.posX + vec3d.x * 4.0D;
                    entitylargefireball.posY = this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F) + 0.5D;
                    entitylargefireball.posZ = this.parentEntity.posZ + vec3d.z * 4.0D;
                    world.spawnEntity(entitylargefireball); */
    /* 
    		this.attackTimer = -40;
                }
            }
            else if (this.attackTimer > 0)
            {
                --this.attackTimer;
            }

            this.parentEntity.setAttacking(this.attackTimer > 10);
        }
    } */
}
