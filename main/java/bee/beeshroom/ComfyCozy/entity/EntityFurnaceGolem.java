package bee.beeshroom.ComfyCozy.entity;

import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
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

    private int attackTimer;
	 private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(EntityFurnaceGolem.class, DataSerializers.BOOLEAN);
	 // private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Item.getItemFromBlock(Blocks.DIRT), Item.getItemFromBlock(Blocks.MYCELIUM), Item.getItemFromBlock(Blocks.GRASS));
	//  private EntityAISit aiSit;
	 //  private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.COAL);
	//private static final TileEntityFurnace tileentity = null;
	
	  protected void initEntityAI()
	    {
		//    this.tasks.addTask(7, new EntityFurnaceGolem.BurnThem(this));
	        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D, 0.0F));
	       // this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 1.0F));
	       // this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
	      //  this.tasks.addTask(8, new EntityAILookIdle(this));
	      //  this.tasks.addTask(3, new EntityAITempt(this, 1.0D, false, TEMPTATION_ITEMS));
	        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
	        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
	       // this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, false, true, new Predicate<EntityLiving>()
	        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, true, false, IMob.MOB_SELECTOR));

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
	        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
	        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
	        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.7D);
	      //  this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.1D);
	       // this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
	    }
	    
	    public EntityFurnaceGolem(World worldIn)
	    {
	        super(worldIn);
	        this.isImmuneToFire = true;
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
	    
	    public void onLivingUpdate()
	    {
	        super.onLivingUpdate();

	        if (!this.world.isRemote && this.getAttackTarget() == null && this.isAttacking())
	        {
	            this.setAttacking(false);
	        }
	        if (this.attackTimer > 0)
	        {
	            --this.attackTimer;
	        }
	        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0)
	        {
	            int i = MathHelper.floor(this.posX);
	            int j = MathHelper.floor(this.posY - 0.20000000298023224D);
	            int k = MathHelper.floor(this.posZ);
	            IBlockState iblockstate = this.world.getBlockState(new BlockPos(i, j, k));

	            /*if (iblockstate.getMaterial() != Material.AIR)
	            {
	                this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, 4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D, Block.getStateId(iblockstate));
	            }*/
	            
	        }
	    }
	    
	    public boolean attackEntityAsMob(Entity entityIn)
	    {
	        this.attackTimer = 10;
	        this.world.setEntityState(this, (byte)4);
	        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(1 + this.rand.nextInt(5)));

	        if (flag) 
	        {
	        	
	        	//  this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	        //	this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
	        	//this.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 1.0F, 1.0F);
	        	 this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	        	entityIn.setFire(3);
	           // entityIn.motionY += 0.4000000059604645D;
	            this.applyEnchantments(this, entityIn);
	        }
	        
	        this.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1.0F, 1.0F);
	        this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
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
	            this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	            this.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 1.0F, 1.0F);
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

    /*
    @Nullable
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SNOWMAN_AMBIENT;
    }*/
    
    protected SoundEvent getAmbientSound()
    {
        if (this.isAttacking())
        {
            return SoundEvents.BLOCK_FIRE_AMBIENT;
        }
        else
        {
            return SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE;
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
    
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
        super.setAttackTarget(entitylivingbaseIn);

        if (entitylivingbaseIn == null)
        {
            this.setAttacking(false);
        }
        else
        {
            this.setAttacking(true);
        }
    }
    
    protected void updateAITasks()
    {
        if (this.isWet())
        {
            //this.attackEntityFrom(DamageSource.DROWN, 1.0F);
        	this.setAttacking(false);
        	this.attackTimer = 0;
        }
    }
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Angry", this.isAttacking());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setAttacking(compound.getBoolean("Attacking"));
    }
    
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

    public void setAttacking(boolean attacking)
    {
        this.dataManager.set(ATTACKING, Boolean.valueOf(attacking));
    }
    
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
