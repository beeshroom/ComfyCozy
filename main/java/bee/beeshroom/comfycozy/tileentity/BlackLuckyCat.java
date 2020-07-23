package bee.beeshroom.comfycozy.tileentity;

import bee.beeshroom.comfycozy.blocks.LuckyCat_Black;
import bee.beeshroom.comfycozy.init.TileEntityInit;
import bee.beeshroom.comfycozy.sounds.SoundList;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.CreeperSwellGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.UUID;

import static net.minecraft.block.RedstoneTorchBlock.LIT;
import static net.minecraft.inventory.EquipmentSlotType.HEAD;

//this is just edited Conduit code
public class BlackLuckyCat extends TileEntity implements ITickableTileEntity {
    private static final Block[] field_205042_e = new Block[]{Blocks.GOLD_BLOCK};
    public int ticksExisted;
    //private float activeRotation;
    private boolean active;
//    private boolean eyeOpen;
    private final List<BlockPos> prismarinePositions = Lists.newArrayList();
    @Nullable
    private LivingEntity target;
    @Nullable
    private UUID targetUuid;
   // private long nextSoundTime;

    public BlackLuckyCat(final TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public BlackLuckyCat() {
        this(TileEntityInit.LUCKYCAT_BLACK.get());
    }

    public void read(CompoundNBT compound) {
        super.read(compound);
        if (compound.contains("target_uuid")) {
            this.targetUuid = NBTUtil.readUniqueId(compound.getCompound("target_uuid"));
        } else {
            this.targetUuid = null;
        }
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.target != null) {
            compound.put("target_uuid", NBTUtil.writeUniqueId(this.target.getUniqueID()));
        }
        return compound;
    }

    /**
     * Retrieves packet to send to the client whenever this Tile Entity is resynced via World.notifyBlockUpdate. For
     * modded TE's, this packet comes back to you clientside in {@link #onDataPacket}
     */
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 5, this.getUpdateTag());
    }

    /**
     * Get an NBT compound to sync to the client with SPacketChunkData, used for initial loading of the chunk or when
     * many blocks change at once. This compound comes back to you clientside in {link handleUpdateTag}
     */
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    public void tick() {
        ++this.ticksExisted;
        long i = this.world.getGameTime();
        if (i % 40L == 0L) {
            //this.setActive(this.shouldBeActive());
            if (!this.world.isRemote && this.isActive()) {
                this.addEffectsToPlayers();
                this.attackMobs();
            }
        }

        if (i % 80L == 0L && this.isActive()) {
          //  this.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT);
        }

    /*    if (i > this.nextSoundTime && this.isActive()) {
            this.nextSoundTime = i + 60L + (long)this.world.getRandom().nextInt(40);
          //  this.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT_SHORT);
        } */

        if (this.world.isRemote) {
            this.updateClientTarget();
            if (this.isActive()) {
             //   ++this.activeRotation;
            }
        }
    }

    private boolean shouldBeActive() {
      /*  this.prismarinePositions.clear();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                for(int k = -1; k <= 1; ++k) {
                    BlockPos blockpos = this.pos.add(i, j, k);
                    if (!this.world.hasWater(blockpos)) {
                        return false;
                    }
                }
            }
        }

        for(int j1 = -2; j1 <= 2; ++j1) {
            for(int k1 = -2; k1 <= 2; ++k1) {
                for(int l1 = -2; l1 <= 2; ++l1) {
                    int i2 = Math.abs(j1);
                    int l = Math.abs(k1);
                    int i1 = Math.abs(l1);
                    if ((i2 > 1 || l > 1 || i1 > 1) && (j1 == 0 && (l == 2 || i1 == 2) || k1 == 0 && (i2 == 2 || i1 == 2) || l1 == 0 && (i2 == 2 || l == 2))) {
                        BlockPos blockpos1 = this.pos.add(j1, k1, l1);
                        BlockState blockstate = this.world.getBlockState(blockpos1);

                        if (blockstate.isConduitFrame(this.world, blockpos1, getPos())) {
                            this.prismarinePositions.add(blockpos1);
                        }
                    }
                }
            }
        }

        this.setEyeOpen(this.prismarinePositions.size() >= 42);*/
      return this.prismarinePositions.size() >= 16;
     // return true;
    }

    private void addEffectsToPlayers() {
        //int i = 40; // this.prismarinePositions.size();
      //  int j = i / 7 * 16;
        int j = 10;
        int k = this.pos.getX();
        int l = this.pos.getY();
        int i1 = this.pos.getZ();

        AxisAlignedBB axisalignedbb = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).grow((double)j).expand(0.0D, (double)this.world.getHeight(), 0.0D);
        List<PlayerEntity> list = this.world.getEntitiesWithinAABB(PlayerEntity.class, axisalignedbb);
        if (!list.isEmpty()) {
            for(PlayerEntity playerentity : list) {
                ItemStack itemstack = playerentity.inventory.armorInventory.get(3);
                if ((this.pos.withinDistance(new BlockPos(playerentity), (double)j) && playerentity.isPotionActive(Effects.INVISIBILITY) || (this.pos.withinDistance(new BlockPos(playerentity), (double)j) && (itemstack.getItem() == Blocks.SKELETON_SKULL.asItem()) || (itemstack.getItem() == Blocks.WITHER_SKELETON_SKULL.asItem()) || (itemstack.getItem() == Blocks.ZOMBIE_HEAD.asItem()) || (itemstack.getItem() == Blocks.CREEPER_HEAD.asItem())) ))
                {

                   //i think itd be funny if it targetted ppl wearing mob skulls
                    // playerentity.addPotionEffect(new EffectInstance(Effects.CONDUIT_POWER, 260, 0, false, false));
                  //  playerentity.attackEntityFrom(DamageSource.MAGIC, 0.5F);
                    playerentity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 260, 0, false, false));
                 //   playerentity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 260, 0, false, false)); */

                    if (playerentity.isPotionActive(Effects.INVISIBILITY)) {
                        if (!world.isRemote) {
                            this.spawnParticles();
                            BlackLuckyCat.this.playSound(SoundList.LUCKY_CAT_ATTACK.get());
                            this.world.playSound((PlayerEntity) null, pos, SoundList.LUCKY_CAT_ATTACK.get(), SoundCategory.BLOCKS, 1.0F, 1.0f);

                            playerentity.removePotionEffect(Effects.INVISIBILITY);
                        }
                    }

                 /*   double x = playerentity.getPosX() - pos.getX() -6D;
                    double y = playerentity.getPosY() - pos.getY() -6D;
                    double z = playerentity.getPosZ() - pos.getZ() -6D;
                    playerentity.setMotion(x, playerentity.getMotion().y, z); */

                }
            }
        }
    }

    private void attackMobs() {
        LivingEntity livingentity = this.target;
        int i = 44; // this.prismarinePositions.size();
        if (i < 42) {
            this.target = null;
        } else if (this.target == null && this.targetUuid != null) {
            this.target = this.findExistingTarget();
            this.targetUuid = null;
        } else if (this.target == null) {
            List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, this.getAreaOfEffect(), (p_205033_0_) -> {
                return p_205033_0_ instanceof IMob; //&& p_205033_0_.isWet();
            });
            if (!list.isEmpty()) {
                this.target = list.get(this.world.rand.nextInt(list.size()));
            }
        } else if (!this.target.isAlive() || !this.pos.withinDistance(new BlockPos(this.target), 8.0D)) {
            this.target = null;
        }
        else if (this.target.hasCustomName())
        {
            this.target = null;
        }

        if (this.target != null) {
            if (!world.isRemote) {
                this.spawnParticles();
                BlackLuckyCat.this.playSound(SoundList.LUCKY_CAT_ATTACK.get());
                this.world.playSound((PlayerEntity) null, pos, SoundList.LUCKY_CAT_ATTACK.get(), SoundCategory.BLOCKS, 1.0F, 1.0f);
            }
           // this.target.attackEntityFrom(DamageSource.MAGIC, 1.0F);
            this.target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 260, 0, false, false));
         //   this.target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 260, 1, false, false));

          double x = target.getPosX() - pos.getX();
          double y = target.getPosY() - pos.getY();
          double z = target.getPosZ() - pos.getZ();
             this.target.setMotion(target.getMotion().x, target.getMotion().y, target.getMotion().z);
        }

        if (livingentity != this.target) {
            BlockState blockstate = this.getBlockState();
            this.world.notifyBlockUpdate(this.pos, blockstate, blockstate, 2);
        }

    }

    private void updateClientTarget() {
        if (this.targetUuid == null) {
            this.target = null;
        } else if (this.target == null || !this.target.getUniqueID().equals(this.targetUuid)) {
            this.target = this.findExistingTarget();
            if (this.target == null) {
                this.targetUuid = null;
            }
        }

    }

    private AxisAlignedBB getAreaOfEffect() {
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        return (new AxisAlignedBB((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1))).grow(8.0D);
    }

    @Nullable
    private LivingEntity findExistingTarget() {
        List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, this.getAreaOfEffect(), (p_205032_1_) -> {
            return p_205032_1_.getUniqueID().equals(this.targetUuid);
        });
        return list.size() == 1 ? list.get(0) : null;
    }

     private void spawnParticles() {
         Random random = this.world.rand;
         double d0 = (double) (MathHelper.sin((float) (this.ticksExisted + 35) * 0.1F) / 2.0F + 0.5F);
         d0 = (d0 * d0 + d0) * (double) 0.3F;
         Vec3d vec3d = new Vec3d((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 1.5D + d0, (double) this.pos.getZ() + 0.5D);

         for (BlockPos blockpos : this.prismarinePositions) {
             if (random.nextInt(50) == 0) {
                 float f = -0.5F + random.nextFloat();
                 float f1 = -2.0F + random.nextFloat();
                 float f2 = -0.5F + random.nextFloat();
                 BlockPos blockpos1 = blockpos.subtract(this.pos);
                 Vec3d vec3d1 = (new Vec3d((double) f, (double) f1, (double) f2)).add((double) blockpos1.getX(), (double) blockpos1.getY(), (double) blockpos1.getZ());
                 this.world.addParticle(ParticleTypes.SMOKE, vec3d.x, vec3d.y, vec3d.z, vec3d1.x, vec3d1.y, vec3d1.z);
             }
         }
     }
     /*   if (this.target != null) {
            Vec3d vec3d2 = new Vec3d(this.target.getPosX(), this.target.getPosYEye(), this.target.getPosZ());
            float f3 = (-0.5F + random.nextFloat()) * (3.0F + this.target.getWidth());
            float f4 = -1.0F + random.nextFloat() * this.target.getHeight();
            float f5 = (-0.5F + random.nextFloat()) * (3.0F + this.target.getWidth());
            Vec3d vec3d3 = new Vec3d((double)f3, (double)f4, (double)f5);
            this.world.addParticle(ParticleTypes.SMOKE, vec3d2.x, vec3d2.y, vec3d2.z, vec3d3.x, vec3d3.y, vec3d3.z);
        }


        if (this.target != null) {
            Vec3d vec3d2 = new Vec3d(this.target.getPosX(), this.target.getPosYEye(), this.target.getPosZ());
            float f3 = (-0.5F + random.nextFloat()) * (3.0F + this.target.getWidth());
            float f4 = -1.0F + random.nextFloat() * this.target.getHeight();
            float f5 = (-0.5F + random.nextFloat()) * (3.0F + this.target.getWidth());
            Vec3d vec3d3 = new Vec3d((double)f3, (double)f4, (double)f5);
            this.world.addParticle(ParticleTypes.SMOKE, vec3d2.x, vec3d2.y, vec3d2.z, vec3d3.x, vec3d3.y, vec3d3.z);
        }

    } */

    public boolean isActive() {
        //return this.active;
            return true;
        }

    /*
    @OnlyIn(Dist.CLIENT)
    public boolean isEyeOpen() {
        return this.eyeOpen;
    } */

  /*    private void setActive(boolean p_205739_1_) {
        //if (p_205739_1_ != this.active) {
       //     this.playSound(p_205739_1_ ? SoundEvents.BLOCK_CONDUIT_ACTIVATE : SoundEvents.BLOCK_CONDUIT_DEACTIVATE);
      //  }
        this.active = p_205739_1_;
    } */
/*
    private void setEyeOpen(boolean p_207736_1_) {
        this.eyeOpen = p_207736_1_;
    }

    @OnlyIn(Dist.CLIENT)
    public float getActiveRotation(float p_205036_1_) {
        return (this.activeRotation + p_205036_1_) * -0.0375F;
    }
*/
    public void playSound(SoundEvent p_205738_1_) {
        this.world.playSound((PlayerEntity)null, this.pos, p_205738_1_, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}