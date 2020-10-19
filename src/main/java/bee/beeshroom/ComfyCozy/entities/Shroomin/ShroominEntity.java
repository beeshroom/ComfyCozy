package bee.beeshroom.comfycozy.entities.Shroomin;

import bee.beeshroom.comfycozy.init.EntityTypes;
import bee.beeshroom.comfycozy.init.ItemInit;
import bee.beeshroom.comfycozy.sounds.SoundList;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.UUID;

public class ShroominEntity extends TameableEntity
{
    private static final DataParameter<Integer> SHROOMIN_TYPE = EntityDataManager.createKey(ShroominEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> SIZE = EntityDataManager.createKey(ShroominEntity.class, DataSerializers.VARINT);

    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.BONE_MEAL);
    public int MushroomTimer = this.rand.nextInt(6000) + 6000;
    public int Compost = this.rand.nextInt(8);


    public ShroominEntity(EntityType<? extends ShroominEntity> type, World worldIn) {
        super(type, worldIn);
        this.setTamed(false);
    }

    @Override
    public EntitySize getSize(Pose poseIn) {
        if (this.isChild())
        {
            return super.getSize(poseIn).scale(0.7F, 0.7F);
        }
        else
            return super.getSize(poseIn).scale(1.0F, 1.0F);
    }
/*
    @Override
    public EntitySize getSize(Pose poseIn) {
        int i = this.getShroominSize();
        EntitySize entitysize = super.getSize(poseIn);
        float f = (entitysize.width + 0.2F * (float)i) / entitysize.width;
        return entitysize.scale(f);
    }


    public void setShroominSize(int sizeIn) {
        this.dataManager.set(SIZE, MathHelper.clamp(sizeIn, 0, 64));
    }

    private void updateShroominSize() {
        this.recalculateSize();
      //  this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((double)(6 + this.getShroominSize()));
    }

    public int getShroominSize() {
        return this.dataManager.get(SIZE);
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (SIZE.equals(key)) {
            this.updateShroominSize();
        }

        super.notifyDataManagerChange(key);
    } */

  /*  public int getVariant() {
        return MathHelper.clamp(this.dataManager.get(VARIANT), 0, 4);
    }

    public void setVariant(int variantIn) {
        this.dataManager.set(VARIANT, variantIn);
    } */

    protected void registerGoals() {

        this.sitGoal = new SitGoal(this);
        this.goalSelector.addGoal(2, this.sitGoal);
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());

        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 4.0F));
       // this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));


    }

 /*   @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        ShroominEntity entity = new ShroominEntity(EntityTypes.SHROOMIN_ENTITY.get(), this.world);
        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)),
                SpawnReason.BREEDING, (ILivingEntityData) null, (CompoundNBT) null);
        return entity;
    } */

    //@Override
    public ShroominEntity createChild(AgeableEntity ageable) {
       // this.setVariant(this.rand.nextInt(2));
     //   this.setShroominSize(0);
        int i = this.getRandomShroominType(this.world);
     //   ShroominEntity baby = EntityTypes.SHROOMIN_ENTITY.get().create(this.world);
        ShroominEntity shroominEntity = new ShroominEntity(EntityTypes.SHROOMIN_ENTITY.get(), this.world);
        shroominEntity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(shroominEntity)),
                SpawnReason.BREEDING, (ILivingEntityData) null, (CompoundNBT) null);
        UUID uuid = this.getOwnerId();
        if (uuid != null) {
            shroominEntity.setOwnerId(uuid);
            shroominEntity.setTamed(true);
        }


        //this is totally going to break, i bet

        if (this.rand.nextInt(4) != 0) {
            if (ageable instanceof ShroominEntity && this.rand.nextBoolean()) {
                i = ((ShroominEntity)ageable).getShroominType();
            } else {
                i = this.getShroominType();
            }
        }

        shroominEntity.setShroominType(i);
       // shroominEntity.setShroominSize(0);
    //    shroominEntity.setGrowingAge(-100);
        return shroominEntity;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.8F;
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.3F);
        if (this.isTamed()) {
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        } else {
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(7.0D);
        }

        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    public void livingTick() {
        super.livingTick();
        BlockPos blockpos = getPosition().down();
        BlockState blockstate = world.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (this.isSitting() && block == Blocks.GRASS_BLOCK || this.isSitting() && block == Blocks.MYCELIUM || this.isSitting() && block == Blocks.PODZOL)
        {
            this.heal(2);
        }
  /*      int i = MathHelper.floor(this.getPosX());
        int j = MathHelper.floor(this.getPosY());
        int k = MathHelper.floor(this.getPosZ());
        BlockPos blockpos = new BlockPos(i, j, k);
     //   BlockPos blockpos = getPosition().down();
        BlockState blockstate = world.getBlockState(blockpos);
        Block block = blockstate.getBlock();


        if (!this.world.isRemote && this.isAlive() && !this.isChild() && --this.MushroomTimer <= 0 && world.getLightSubtracted(blockpos, 0) < 13 && blockstate.isSolid() && this.world.getBlockState(blockpos).getMaterial() == Material.AIR)
        {
            this.playSound(SoundEvents.ITEM_CROP_PLANT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
       //     this.entityDropItem(Items.RED_MUSHROOM);

            if (!this.world.isRemote && this.getAttackTarget() == null && this.isAngry()) {
                this.setAngry(false);
            }

            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this))
            {
                return;
            }

                if (this.getShroominType() == 0 || this.getShroominType() == 2 || this.getShroominType() == 3 || this.getShroominType() == 6 || this.getShroominType() == 7)
            {
                this.world.setBlockState(blockpos, Blocks.RED_MUSHROOM.getDefaultState());
            }

               if (this.getShroominType() == 1 || this.getShroominType() == 4 || this.getShroominType() == 5)
            {
                this.world.setBlockState(blockpos, Blocks.BROWN_MUSHROOM.getDefaultState());
            }
            this.MushroomTimer = this.rand.nextInt(6000) + 6000;
        } */}

  //  protected SoundEvent getAmbientSound() { return SoundEvents.ENTITY_CHICKEN_AMBIENT; }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundList.SHROOMIN_SPLAT.get();
    }

    protected SoundEvent getDeathSound() { return SoundList.SHROOMIN_DEATH.get(); }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }

    protected int getExperiencePoints(PlayerEntity player) {
        return super.getExperiencePoints(player);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(SHROOMIN_TYPE, 0);
    }

    public int getShroominType() {
        return this.dataManager.get(SHROOMIN_TYPE);
    }

    public void setShroominType(int shroominTypeId) {

        this.dataManager.set(SHROOMIN_TYPE, shroominTypeId);
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
     //   this.setShroominSize(compound.getInt("Size"));
        this.setShroominType(compound.getInt("ShroominType"));
        if (compound.contains("MushroomTimer")) {
            this.MushroomTimer = compound.getInt("MushroomTimer");
            this.Compost = compound.getShort("Compost");
        } }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("ShroominType", this.getShroominType());
        compound.putInt("MushroomTimer", this.MushroomTimer);
        compound.putShort("Compost", (short)this.Compost);
    //    compound.putInt("Size", this.getShroominSize());
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int i = this.getRandomShroominType(worldIn);
        if (spawnDataIn instanceof ShroominEntity.ShroominData) {
            i = ((ShroominEntity.ShroominData)spawnDataIn).typeData;
        } else {
            spawnDataIn = new ShroominEntity.ShroominData(i);
        }

    //    this.setShroominSize(0);

        this.setShroominType(i);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static class ShroominData extends AgeableEntity.AgeableData {
        public final int typeData;

        public ShroominData(int type) {
            this.typeData = type;
            this.func_226258_a_(1.0F);
        }
    }

    private int getRandomShroominType(IWorld p_213610_1_) {
        int i = this.rand.nextInt(4);
            return i; //< 50 ? 0 : (i < 90 ? 5 : 2);
        }



    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getTrueSource();
            if (this.sitGoal != null) {
                this.sitGoal.setSitting(false);
            }

            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof AbstractArrowEntity)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue()));
        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        } else {
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn);
        if (entitylivingbaseIn == null) {
            this.setAngry(false);
        } else if (!this.isTamed()) {
            this.setAngry(true);
        }
    }

    public boolean canTrample(World world, Block block, BlockPos pos, float fallDistance)
    {
        return false;
    }

    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (itemstack.getItem() instanceof SpawnEggItem) {
            return super.processInteract(player, hand);
        } else if (this.world.isRemote) {
            return this.isOwner(player) || item == Items.BONE_MEAL && !this.isAngry();
        } else {

            if (this.Compost + 1 <= 5 && ( item.isFood() || item instanceof BlockItem && ((BlockItem) item).getBlock() instanceof BushBlock || item instanceof BlockItem && ((BlockItem) item).getBlock() instanceof IPlantable) )
            {
                this.world.playSound((PlayerEntity)null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundList.SHROOMIN_EAT.get(), this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                //random chance to add to the Compost level, like a Compost Block
                if (this.rand.nextInt(1) == 0) {
                    this.Compost += 1;
                }
                if (this.rand.nextInt(2) == 0) {
                    this.Compost += 1;
                }

                //heal the shroomin when fed
                this.heal(1);

                //give bonemeal when full
                if (Compost >= 5 )
                {

                    this.entityDropItem(Items.BONE_MEAL);

                    //random chance for bonus bone meal
                    if (this.rand.nextInt(5) == 0)
                    {
                        this.entityDropItem(Items.BONE_MEAL);
                    }
                    this.Compost = 0;
                 //   this.world.playSound((PlayerEntity)null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_PLAYER_BURP, this.getSoundCategory(), 1.0F, 2.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);

                }
            }

            if (this.isTamed() ) {

                //makes it so you dont make it stand and sit over and over while Composting
                if (this.isOwner(player) && !item.isFood() && !(item instanceof BlockItem && ((BlockItem) item).getBlock() instanceof BushBlock) && !(item instanceof BlockItem && ((BlockItem) item).getBlock() instanceof IPlantable) )
                {
                    this.sitGoal.setSitting(!this.isSitting());
                    this.isJumping = false;
                    this.navigator.clearPath();
                    this.setAttackTarget((LivingEntity)null);
                }
            }

            else if (item == Items.BONE_MEAL && !this.isAngry() && !this.isTamed()) {
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }
                this.world.playSound((PlayerEntity)null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundList.SHROOMIN_EAT.get(), this.getSoundCategory(), 1.0F, 2.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);

                if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.heal(1);
                    this.setTamedBy(player);
                    this.navigator.clearPath();
                    this.setAttackTarget((LivingEntity)null);
                    this.sitGoal.setSitting(true);
                    this.world.setEntityState(this, (byte)7);
                } else {
                    this.world.setEntityState(this, (byte)6);
                }

                return true;
            }

            return super.processInteract(player, hand);
        }
    }

    public int getMaxSpawnedInChunk() {
        return 6;
    }

    public boolean isAngry() {
        return (this.dataManager.get(TAMED) & 2) != 0;
    }

    public void setAngry(boolean angry) {
        byte b0 = this.dataManager.get(TAMED);
        if (angry) {
            this.dataManager.set(TAMED, (byte)(b0 | 2));
        } else {
            this.dataManager.set(TAMED, (byte)(b0 & -3));
        }
    }

    public boolean canMateWith(AnimalEntity otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!this.isTamed()) {
            return false;
        } else if (!(otherAnimal instanceof ShroominEntity)) {
            return false;
        } else {
            ShroominEntity shroominEntity = (ShroominEntity) otherAnimal;
            if (!shroominEntity.isTamed()) {
                return false;
            } else if (shroominEntity.isSitting()) {
                return false;
            } else {
                return this.isInLove() && shroominEntity.isInLove();
            }
        }
    }

    public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {
        if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
            if (target instanceof ShroominEntity) {
                ShroominEntity shroominEntity = (ShroominEntity)target;
                return !shroominEntity.isTamed() || shroominEntity.getOwner() != owner;
            } else if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).canAttackPlayer((PlayerEntity)target)) {
                return false;
            } else if (target instanceof AbstractHorseEntity && ((AbstractHorseEntity)target).isTame()) {
                return false;
            } else {
                return !(target instanceof TameableEntity) || !((TameableEntity)target).isTamed();
            }
        } else {
            return false;
        }
    }

    public boolean canBeLeashedTo(PlayerEntity player) {
        return !this.isAngry() && super.canBeLeashedTo(player);
    }






    public boolean isPotionApplicable(EffectInstance potioneffectIn) {
        if (potioneffectIn.getPotion() == Effects.POISON) {
            net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent event = new net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent(this, potioneffectIn);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
            return event.getResult() == Event.Result.ALLOW;
        }
        return super.isPotionApplicable(potioneffectIn);
    }
}
