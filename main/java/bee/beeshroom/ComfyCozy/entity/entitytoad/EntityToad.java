/*package bee.beeshroom.ComfyCozy.entity.entitytoad;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import bee.beeshroom.ComfyCozy.entity.EntityMushy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
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
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityToad extends EntityAnimal
{
	 private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityMushy.class, DataSerializers.VARINT);
	  private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Item.getItemFromBlock(Blocks.DIRT), Item.getItemFromBlock(Blocks.MYCELIUM), Item.getItemFromBlock(Blocks.GRASS));
	  private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Item.getItemFromBlock(Blocks.DIRT), Item.getItemFromBlock(Blocks.MYCELIUM), Item.getItemFromBlock(Blocks.GRASS));
    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;
    private int carrotTicks;

    public EntityToad(World worldIn)
    {
        super(worldIn);
        this.setSize(0.4F, 0.5F);
        this.jumpHelper = new EntityJumpHelper(this);
        this.moveHelper = new EntityToad.RabbitMoveHelper(this);
        this.setMovementSpeed(0.0D);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
       // this.tasks.addTask(1, new EntityToad.AIPanic(this, 2.2D));
        this.tasks.addTask(2, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, Items.CARROT, false));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, Items.GOLDEN_CARROT, false));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, Item.getItemFromBlock(Blocks.YELLOW_FLOWER), false));
       //this.tasks.addTask(4, new EntityRabbit.AIAvoidEntity(this, EntityPlayer.class, 8.0F, 2.2D, 2.2D));
       // this.tasks.addTask(4, new EntityRabbit.AIAvoidEntity(this, EntityWolf.class, 10.0F, 2.2D, 2.2D));
        //this.tasks.addTask(4, new EntityRabbit.AIAvoidEntity(this, EntityMob.class, 4.0F, 2.2D, 2.2D));
       // this.tasks.addTask(5, new EntityRabbit.AIRaidFarm(this));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
    }

    protected float getJumpUpwardsMotion()
    {
        if (!this.collidedHorizontally && (!this.moveHelper.isUpdating() || this.moveHelper.getY() <= this.posY + 0.5D))
        {
            Path path = this.navigator.getPath();

            if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
            {
                Vec3d vec3d = path.getPosition(this);

                if (vec3d.y > this.posY + 0.5D)
                {
                    return 0.5F;
                }
            }

            return this.moveHelper.getSpeed() <= 0.6D ? 0.2F : 0.3F;
        }
        else
        {
            return 0.5F;
        }
    }

    
     // Causes this entity to do an upwards motion (jumping).
     
    protected void jump()
    {
        super.jump();
        double d0 = this.moveHelper.getSpeed();

        if (d0 > 0.0D)
        {
            double d1 = this.motionX * this.motionX + this.motionZ * this.motionZ;

            if (d1 < 0.010000000000000002D)
            {
                this.moveRelative(0.0F, 0.0F, 1.0F, 0.1F);
            }
        }

        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)1);
        }
    }

    @SideOnly(Side.CLIENT)
    public float setJumpCompletion(float p_175521_1_)
    {
        return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + p_175521_1_) / (float)this.jumpDuration;
    }

    public void setMovementSpeed(double newSpeed)
    {
        this.getNavigator().setSpeed(newSpeed);
        this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
    }

    public void setJumping(boolean jumping)
    {
        super.setJumping(jumping);

        if (jumping)
        {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }
    }

    public void startJumping()
    {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(VARIANT, Integer.valueOf(0));
      //  this.dataManager.register(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
    }

    public void updateAITasks()
    {
        if (this.currentMoveTypeDuration > 0)
        {
            --this.currentMoveTypeDuration;
        }

        if (this.carrotTicks > 0)
        {
            this.carrotTicks -= this.rand.nextInt(3);

            if (this.carrotTicks < 0)
            {
                this.carrotTicks = 0;
            }
        }

        if (this.onGround)
        {
            if (!this.wasOnGround)
            {
                this.setJumping(false);
                this.checkLandingDelay();
            }

            EntityToad.RabbitJumpHelper entitytoad$rabbitjumphelper = (EntityToad.RabbitJumpHelper)this.jumpHelper;

            if (!entitytoad$rabbitjumphelper.getIsJumping())
            {
                if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0)
                {
                    Path path = this.navigator.getPath();
                    Vec3d vec3d = new Vec3d(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());

                    if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
                    {
                        vec3d = path.getPosition(this);
                    }

                    this.calculateRotationYaw(vec3d.x, vec3d.z);
                    this.startJumping();
                }
            }
            else if (!entitytoad$rabbitjumphelper.canJump())
            {
                this.enableJumpControl();
            }
        }

        this.wasOnGround = this.onGround;
    }

    
     // Attempts to create sprinting particles if the entity is sprinting and not in water.
     
    public void spawnRunningParticles()
    {
    }

    private void calculateRotationYaw(double x, double z)
    {
        this.rotationYaw = (float)(MathHelper.atan2(z - this.posZ, x - this.posX) * (180D / Math.PI)) - 90.0F;
    }

    private void enableJumpControl()
    {
        ((EntityToad.RabbitJumpHelper)this.jumpHelper).setCanJump(true);
    }

    private void disableJumpControl()
    {
        ((EntityToad.RabbitJumpHelper)this.jumpHelper).setCanJump(false);
    }

    private void updateMoveTypeDuration()
    {
        if (this.moveHelper.getSpeed() < 2.2D)
        {
            this.currentMoveTypeDuration = 10;
        }
        else
        {
            this.currentMoveTypeDuration = 1;
        }
    }

    private void checkLandingDelay()
    {
        this.updateMoveTypeDuration();
        this.disableJumpControl();
    }

    
     // Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     // use this to react to sunlight and start to burn.
     
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.jumpTicks != this.jumpDuration)
        {
            ++this.jumpTicks;
        }
        else if (this.jumpDuration != 0)
        {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
    }

    public static void registerFixesRabbit(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityToad.class);
    }
    
    public int getVariant()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(VARIANT)).intValue(), 0, 4);
    }

    public void setVariant(int p_191997_1_)
    {
        this.dataManager.set(VARIANT, Integer.valueOf(p_191997_1_));
    }

    
     // (abstract) Protected helper method to write subclass entity data to NBT.
     
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getVariant());
    }

    
     // (abstract) Protected helper method to read subclass entity data from NBT.
     
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);
        this.setVariant(compound.getInteger("Variant"));
        if (this.aiSit != null) { this.aiSit.setSitting(compound.getBoolean("Sitting")); } //idk what this is. testing it out
    }
    protected SoundEvent getJumpSound()
    {
        return SoundEvents.ENTITY_RABBIT_JUMP;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_RABBIT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_RABBIT_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_RABBIT_DEATH;
    }



    
     // Called when the entity is attacked.
     
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_RABBIT;
    }

    private boolean isRabbitBreedingItem(Item itemIn)
    {
        return itemIn == Items.CARROT || itemIn == Items.GOLDEN_CARROT || itemIn == Item.getItemFromBlock(Blocks.YELLOW_FLOWER);
    }

    
     //Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     // the animal type)
    
    public boolean isBreedingItem(ItemStack stack)
    {
        return this.isRabbitBreedingItem(stack.getItem());
    }

  /*  public void setRabbitType(int rabbitTypeId)
    {
        if (rabbitTypeId == 99)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
            this.tasks.addTask(4, new EntityToad.AIEvilAttack(this));
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, true));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.KillerBunny.name"));
            }
        }

        this.dataManager.set(TOAD_TYPE, Integer.valueOf(rabbitTypeId));
    }*/

 /*   protected void createEatingParticles()
    {
        BlockCarrot blockcarrot = (BlockCarrot)Blocks.CARROTS;
        IBlockState iblockstate = blockcarrot.withAge(blockcarrot.getMaxAge());
        this.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 0.0D, 0.0D, 0.0D, Block.getStateId(iblockstate));
        this.carrotTicks = 40;
    } */

    
/*
      Handler for {@link World#setEntityState}
     
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 1)
        {
            this.createRunningParticles();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    static class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T>
        {
            private final EntityToad toad;

            public AIAvoidEntity(EntityToad toad, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_)
            {
                super(toad, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
                this.toad = toad;
            }

    static class AIPanic extends EntityAIPanic
        {
            private final EntityToad toad;

            public AIPanic(EntityToad toad, double speedIn)
            {
                super(toad, speedIn);
                this.toad = toad;
            }

            
             // Keep ticking a continuous task that has already been started
             
            public void updateTask()
            {
                super.updateTask();
                this.toad.setMovementSpeed(this.speed);
            }
        }

    static class AIRaidFarm extends EntityAIMoveToBlock
        {
            private final EntityToad toad;
            private boolean wantsToRaid;
            private boolean canRaid;

            public AIRaidFarm(EntityToad rabbitIn)
            {
                super(rabbitIn, 0.699999988079071D, 16);
                this.toad = rabbitIn;
            }

            
             // Returns whether the EntityAIBase should begin execution.
             
            public boolean shouldExecute()
            {
                if (this.runDelay <= 0)
                {
                    if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.toad.world, this.toad))
                    {
                        return false;
                    }

                    this.canRaid = false;
                    //this.wantsToRaid = this.toad.isCarrotEaten();
                    this.wantsToRaid = true;
                }

                return super.shouldExecute();
            }

            
             // Returns whether an in-progress EntityAIBase should continue executing
             
            public boolean shouldContinueExecuting()
            {
                return this.canRaid && super.shouldContinueExecuting();
            }

            
             // Keep ticking a continuous task that has already been started
             
            public void updateTask()
            {
                super.updateTask();
                this.toad.getLookHelper().setLookPosition((double)this.destinationBlock.getX() + 0.5D, (double)(this.destinationBlock.getY() + 1), (double)this.destinationBlock.getZ() + 0.5D, 10.0F, (float)this.toad.getVerticalFaceSpeed());

                if (this.getIsAboveDestination())
                {
                    World world = this.toad.world;
                    BlockPos blockpos = this.destinationBlock.up();
                    IBlockState iblockstate = world.getBlockState(blockpos);
                    Block block = iblockstate.getBlock();

                    if (this.canRaid && block instanceof BlockCarrot)
                    {
                        Integer integer = (Integer)iblockstate.getValue(BlockCarrot.AGE);

                        if (integer.intValue() == 0)
                        {
                            world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
                            world.destroyBlock(blockpos, true);
                        }
                        else
                        {
                            world.setBlockState(blockpos, iblockstate.withProperty(BlockCarrot.AGE, Integer.valueOf(integer.intValue() - 1)), 2);
                            world.playEvent(2001, blockpos, Block.getStateId(iblockstate));
                        }

                       // this.toad.createEatingParticles();
                    }

                    this.canRaid = false;
                    this.runDelay = 10;
                }
            }

            //
              Return true to set given position as destination
             
            protected boolean shouldMoveTo(World worldIn, BlockPos pos)
            {
                Block block = worldIn.getBlockState(pos).getBlock();

                if (block == Blocks.FARMLAND && this.wantsToRaid && !this.canRaid)
                {
                    pos = pos.up();
                    IBlockState iblockstate = worldIn.getBlockState(pos);
                    block = iblockstate.getBlock();

                    if (block instanceof BlockCarrot && ((BlockCarrot)block).isMaxAge(iblockstate))
                    {
                        this.canRaid = true;
                        return true;
                    }
                }

                return false;
            }
        }

    public class RabbitJumpHelper extends EntityJumpHelper
    {
        private final EntityToad toad;
        private boolean canJump;

        public RabbitJumpHelper(EntityToad toad)
        {
            super(toad);
            this.toad = toad;
        }

        public boolean getIsJumping()
        {
            return this.isJumping;
        }

        public boolean canJump()
        {
            return this.canJump;
        }

        public void setCanJump(boolean canJumpIn)
        {
            this.canJump = canJumpIn;
        }

        
         // Called to actually make the entity jump if isJumping is true.
         
        public void doJump()
        {
            if (this.isJumping)
            {
                this.toad.startJumping();
                this.isJumping = false;
            }
        }
    }

    static class RabbitMoveHelper extends EntityMoveHelper
        {
            private final EntityToad toad;
            private double nextJumpSpeed;

            public RabbitMoveHelper(EntityToad toad)
            {
                super(toad);
                this.toad = toad;
            }

            public void onUpdateMoveHelper()
            {
                if (this.toad.onGround && !this.toad.isJumping && !((EntityToad.RabbitJumpHelper)this.toad.jumpHelper).getIsJumping())
                {
                    this.toad.setMovementSpeed(0.0D);
                }
                else if (this.isUpdating())
                {
                    this.toad.setMovementSpeed(this.nextJumpSpeed);
                }

                super.onUpdateMoveHelper();
            }

            
             // Sets the speed and location to move to
             
            public void setMoveTo(double x, double y, double z, double speedIn)
            {
                if (this.toad.isInWater())
                {
                    speedIn = 1.5D;
                }

                super.setMoveTo(x, y, z, speedIn);

                if (speedIn > 0.0D)
                {
                    this.nextJumpSpeed = speedIn;
                }
            }
        }

    public static class RabbitTypeData implements IEntityLivingData
        {
            public int typeData;

            public RabbitTypeData(int type)
            {
                this.typeData = type;
            }
        }
}}*/