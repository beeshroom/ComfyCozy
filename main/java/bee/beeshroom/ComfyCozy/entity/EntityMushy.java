package bee.beeshroom.ComfyCozy.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIFollowOwnerFlying;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILandOnOwnersShoulder;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityShoulderRiding;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMushy extends EntityTameable 
{
	  private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityMushy.class, DataSerializers.VARINT);
	  private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Item.getItemFromBlock(Blocks.DIRT), Item.getItemFromBlock(Blocks.MYCELIUM), Item.getItemFromBlock(Blocks.GRASS));
	  private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Item.getItemFromBlock(Blocks.DIRT), Item.getItemFromBlock(Blocks.MYCELIUM), Item.getItemFromBlock(Blocks.GRASS));
	  private EntityAISit aiSit;
	  private EntityAITempt aiTempt;
	  public int timeUntilNextEgg;
//	private Object addGrowth;
	  
	  
	public EntityMushy(World worldIn) 
	{
		
		super(worldIn);
    //    this.setSize(0.4F, 0.7F);
        this.setSize(0.8F, 0.14F);
        this.setTamed(false);
        this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
	}

	   public boolean canTrample(World world, Block block, BlockPos pos, float fallDistance)
	    {
	        return false;
	    }
	   
	   public boolean canMateWith(EntityAnimal otherAnimal)
	    {
	        return false;
	    }

	   @Override 
	   @Nullable
	    public EntityAgeable createChild(EntityAgeable ageable)
	    {
	        return null;
	    }
	   protected void initEntityAI()
	    {
		//   this.aiSit = new AISit<>(this);
		   this.aiSit = new EntityAISit(this);
	      //  this.tasks.addTask(3, new EntityAILandOnOwnersShoulder(this));
	        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, false, TEMPTATION_ITEMS));
	        this.tasks.addTask(1, new EntityAISwimming(this));
	        this.tasks.addTask(2, this.aiSit);
	      //  this.tasks.addTask(0, new EntityAIPanic(this, 1.25D));
	        this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
	        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 13.0F));
	        //this.tasks.addTask(2, this.aiSit);
	     //   this.tasks.addTask(2, new EntityAIFollowOwnerFlying(this, 1.0D, 5.0F, 1.0F));
	       // this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
	      //  this.tasks.addTask(3, new EntityAILandOnOwnersShoulder(this));
	        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
	        //this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));
	   //     this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate<Entity>()
	    /*    {
	            public boolean apply(@Nullable Entity p_apply_1_)
	            {
	                return p_apply_1_ instanceof EntitySheep || p_apply_1_ instanceof EntityRabbit;
	            }
	        }));
	        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, AbstractSkeleton.class, false)); */
	    }
	   
	/*   public boolean isBreedingItem(ItemStack stack)
	    {
	        return TEMPTATION_ITEMS.contains(stack.getItem());
	    }*/
	   
	   @Nullable
	    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	    {
	        this.setVariant(this.rand.nextInt(5));
	        return super.onInitialSpawn(difficulty, livingdata);
	    }

	    protected void applyEntityAttributes()
	    {
	    	super.applyEntityAttributes();
	        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.5D);
	        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	    }

	  /*  public boolean getCanSpawnHere()
	    {
	        int i = MathHelper.floor(this.posX);
	        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
	        int k = MathHelper.floor(this.posZ);
	        BlockPos blockpos = new BlockPos(i, j, k);
	        Block block = this.world.getBlockState(blockpos.down()).getBlock();
	        return block == Blocks.GRASS || block == Blocks.STONE && this.world.getLight(blockpos) < 16 && super.getCanSpawnHere();
	    }*/
	    
	    public boolean getCanSpawnHere()
	    {
	        int i = MathHelper.floor(this.posX);
	        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
	        int k = MathHelper.floor(this.posZ);
	        BlockPos blockpos = new BlockPos(i, j, k);
	        return this.world.getLight(blockpos) < 15 && super.getCanSpawnHere();
	    }

	    protected void playStepSound(BlockPos pos, Block blockIn)
	    {
	        this.playSound(SoundEvents.ENTITY_PARROT_STEP, 0.13F, 1.0F);
	    }
	    
	    public static void registerFixesOcelot(DataFixer fixer)
	    {
	        EntityLiving.registerFixesMob(fixer, EntityMushy.class);
	    }


	    
	    public int getVariant()
	    {
	        return MathHelper.clamp(((Integer)this.dataManager.get(VARIANT)).intValue(), 0, 4);
	    }

	    public void setVariant(int p_191997_1_)
	    {
	        this.dataManager.set(VARIANT, Integer.valueOf(p_191997_1_));
	    }

	    protected void entityInit()
	    {
	        super.entityInit();
	        this.dataManager.register(VARIANT, Integer.valueOf(0));
	      //  this.dataManager.register(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
	    }
	    
	    public void writeEntityToNBT(NBTTagCompound compound)
	    {
	        super.writeEntityToNBT(compound);
	        compound.setInteger("Variant", this.getVariant());
	        compound.setInteger("EggLayTime", this.timeUntilNextEgg);
	    }
	    /**
	     * (abstract) Protected helper method to read subclass entity data from NBT.
	     */
	    public void readEntityFromNBT(NBTTagCompound compound)
	    {
	    	super.readEntityFromNBT(compound);
	        this.setVariant(compound.getInteger("Variant"));
	        if (this.aiSit != null) { this.aiSit.setSitting(compound.getBoolean("Sitting")); } //idk what this is. testing it out
	        if (compound.hasKey("EggLayTime"))
	        {
	            this.timeUntilNextEgg = compound.getInteger("EggLayTime");
	        }
	    }
	/*    private static SoundEvent getAmbientSound(Random random)
	    {
	            return SoundEvents.ENTITY_RABBIT_AMBIENT;
	    } */

	    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	    {
	        return SoundsHandler.SHROOMINI_HURT;
	    }

	    protected SoundEvent getDeathSound()
	    {
	        return SoundsHandler.SHROOMINI_HURT;
	    }

	    /**
	     * Returns the volume for the sounds this mob makes.
	     */
	    protected float getSoundVolume()
	    {
	        return 0.5F;
	    }

	/*    @Nullable
	    protected ResourceLocation getLootTable()
	    {
	        return LootTableList.ENTITIES_WOLF;
	    } */


	    public float getEyeHeight()
	    {
	        return this.height * 0.6F;
	    }

	    /**
	     * Called when the entity is attacked.
	     */
	    public boolean attackEntityFrom(DamageSource source, float amount)
	    {
	        if (this.isEntityInvulnerable(source))
	        {
	            return false;
	        }
	        else
	        {
	            if (this.aiSit != null)
	            {
	                this.aiSit.setSitting(false);
	            }

	            return super.attackEntityFrom(source, amount);
	        }
	    }

	    public boolean attackEntityAsMob(Entity entityIn)
	    {
	        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
	    }

/*	    public void setTamed(boolean tamed)
	    {
	        super.setTamed(tamed);

	        if (tamed)
	        {
	            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	        }
	        else
	        {
	            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
	        }

	        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	    } */


	/*    private boolean InstantTame(EntityPlayer player)
	    {
	        ItemStack itemstack = player.inventory.armorInventory.get(3);

	        if (itemstack.getItem() == ModItems.MUSHROOM_BERET)
	        {
	        	  if (!this.world.isRemote)
		            {
		                if (!net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
		                {
		                    this.setTamedBy(player);
		                    this.playTameEffect(true);
		                    this.world.setEntityState(this, (byte)7);
		                }
		            }
	        	return true;
	        }
	        else
	        {
	           return false;
	        }
	    }*/
	    
	//    this.setSize(0.9F, 1.3F);
	    
	  /*  public void processInteract(EntityPlayer player, ItemStack stack)
	    {
	    	 EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(stack.getMetadata());
	        Item item = stack.getItem();
	        
	        if (enumdyecolor == EnumDyeColor.WHITE)
	        {
	        	  this.setSize(0.8F, 0.14F);
	        }
	       
	        return;
	    } */
	    
	    
	    
	    
	    public boolean processInteract(EntityPlayer player, EnumHand hand)
	    {
	        ItemStack itemstack = player.getHeldItem(hand);
	///////////////////        EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(itemstack.getMetadata());  ///////////////////
	        

	        if (!this.isTamed() && TAME_ITEMS.contains(itemstack.getItem()))
	        {
	            if (!player.capabilities.isCreativeMode)
	            {
	                itemstack.shrink(1);
	            }

	            if (!this.isSilent())
	            {																																		//made this 3.0 instead of 1.0
	                this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PARROT_EAT, this.getSoundCategory(), 1.0F, 3.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
	            }

	            if (!this.world.isRemote)
	            {
	                if (this.rand.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
	                {
	                    this.setTamedBy(player);
	                    this.playTameEffect(true);
	                    this.world.setEntityState(this, (byte)7);
	                }
	                else
	                {
	                    this.playTameEffect(false);
	                    this.world.setEntityState(this, (byte)6);
	                 
	                    ///////////////////////trying something...//////////////////////////
	                    
	                    ItemStack itemstack1 = player.inventory.armorInventory.get(3);

	        	        if (itemstack1.getItem() == ModItems.MUSHROOM_BERET)
	        	        {
	        	        	  if (!this.world.isRemote)
	        		            {
	        		                if (!net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
	        		                {
	        		                    this.setTamedBy(player);
	        		                    this.playTameEffect(true);
	        		                    this.world.setEntityState(this, (byte)7);
	        		                }
	        		            }
	        	        	return true;
	        	        }
	        	        else
	        	        {
	        	           return false;
	        	        }
	                }
	         
	            }

	            return true;
	        }
	 
	        
	        
	        
	        
	        else
	        {
	        	 if (!this.world.isRemote && this.isTamed() && this.isOwner(player))
	             {
	                 this.aiSit.setSitting(!this.isSitting());
	               
	                 
	             ////////////////////    //for future useage///////////////////
	           /*     if (enumdyecolor == EnumDyeColor.WHITE)
	                	
	     	        {
	                	 itemstack.shrink(1);
	                	 if (!this.world.isRemote && !this.isDead)
	                     {
	                         EntityShroomega entityshroomega = new EntityShroomega(this.world);
	                         entityshroomega.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
	                         entityshroomega.setNoAI(this.isAIDisabled());
	                   //     this.getVariant = variant
	                         if (this.hasCustomName())
	                         {
	                        	 entityshroomega.setCustomNameTag(this.getCustomNameTag());
	                        	 entityshroomega.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
	                         }

	                         this.world.spawnEntity(entityshroomega);
	                         this.setDead();
	                     } 
	                	 //////////////////////////////////
	     	       } */
	             }

	             return super.processInteract(player, hand);
	         }
	    }

	    /**
	     * Will return how many at most can spawn in a chunk at once.
	     */
	    public int getMaxSpawnedInChunk()
	    {
	        return 8;
	    }


	    public boolean canBeLeashedTo(EntityPlayer player)
	    {
	        return super.canBeLeashedTo(player);
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
	    }
	    
	    
	    
	    public void onLivingUpdate()
	    {
	        super.onLivingUpdate();
	        
	        if (!this.world.isRemote && !this.hasPath() && this.onGround)
	        {
	           
	            this.world.setEntityState(this, (byte)8);
	        }

	        if (!this.world.isRemote && isSitting() != false)
	        	 for (int l = 0; l < 4; ++l)
	        {
	        	int i = MathHelper.floor(this.posX + (double)((float)(l % 2 * 2 - 1) * 0.25F));
	            int j = MathHelper.floor(this.posY);
	             int k = MathHelper.floor(this.posZ + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
	                BlockPos blockpos = new BlockPos(i, j, k);
	                this.heal(.2f);

	            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this))
	            {
	                return;
	            }

	            if (!this.world.isRemote && --this.timeUntilNextEgg <= 0)
	            {
	                if (this.world.getBlockState(blockpos).getMaterial() == Material.AIR && this.world.getLight(blockpos) < 13 && Blocks.BROWN_MUSHROOM.canPlaceBlockAt(this.world, blockpos))
	                {
	                	 this.heal(5f);
	              /*  	    double d0 = this.rand.nextGaussian() * 0.02D;
	                        double d1 = this.rand.nextGaussian() * 0.02D;
	                        double d2 = this.rand.nextGaussian() * 0.02D;
	                        this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
	            */    //	this.world.spawnParticle(EnumParticleTypes.TOWN_AURA, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);    
	                	 this.playSound(SoundsHandler.PLANT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
	                 
	                	 if (this.getVariant() <= 1)
	                	 {
	                	 this.world.setBlockState(blockpos, Blocks.RED_MUSHROOM.getDefaultState());    
	                	 }
	                	 if (this.getVariant() > 1)
	                	 {
	                	 this.world.setBlockState(blockpos, Blocks.BROWN_MUSHROOM.getDefaultState());  
	                	 }
	                }
	                this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
	            }
	        }
	    }
	    
	    
	 /*   protected boolean handleEating(EntityPlayer player, ItemStack stack)
	    {
	    	  boolean flag = false;
	    	   int i = 0;
	           Item item = stack.getItem();
	    if (this.isTamed() && i == 0)
        {
            this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 0.0D, 0.0D, 0.0D);

            if (!this.world.isRemote)
            {
                this.addGrowth(i);
            }

            flag = true;
        }
	    return flag;
	    } */
	}