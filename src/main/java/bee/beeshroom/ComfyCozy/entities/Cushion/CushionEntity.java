/*package bee.beeshroom.comfycozy.entities.Cushion;
import bee.beeshroom.comfycozy.blocks.Cushion;
import bee.beeshroom.comfycozy.entities.Shroomin.ShroominEntity;
import bee.beeshroom.comfycozy.init.EntityTypes;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

//Create Mod

public class CushionEntity extends MobEntity implements IEntityAdditionalSpawnData {

    public CushionEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super((EntityType<? extends MobEntity>) p_i48580_1_, p_i48580_2_);
    }

    public CushionEntity(World world, BlockPos pos) {
        this(EntityTypes.CUSHION.get(), world);
        noClip = true;
    }

    public static EntityType.Builder<?> build(EntityType.Builder<?> builder) {
        @SuppressWarnings("unchecked")
        EntityType.Builder<CushionEntity> entityBuilder = (EntityType.Builder<CushionEntity>) builder;
        return entityBuilder.size(0.25f, 0.35f);
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return super.getBoundingBox();
    }

   // @Override
    public void setPos(double x, double y, double z) {
        super.setPosition(x, y, z);
        AxisAlignedBB bb = getBoundingBox();
        Vec3d diff = new Vec3d(x, y, z).subtract(bb.getCenter());
        setBoundingBox(bb.offset(diff));
    }

    @Override
    public void setMotion(Vec3d p_213317_1_) {}

    @Override
    public void tick() {
        if (world.isRemote)
            return;
        boolean blockPresent = world.getBlockState(getPosition())
                .getBlock() instanceof Cushion;
        if (isBeingRidden() && blockPresent)
            return;
        this.remove();
    }

    @Override
    protected boolean canBeRidden(Entity p_184228_1_) {
        return true;
    }

    @Override
    protected void removePassenger(Entity entity) {
        super.removePassenger(entity);
        Vec3d pos = entity.getPositionVec();
        entity.setPosition(pos.x, pos.y + 0.85f, pos.z);
    }

    @Override
    protected void registerData() {}

    @Override
    public void readAdditional(CompoundNBT p_70037_1_) {}

    @Override
    public void writeAdditional(CompoundNBT p_213281_1_) {}

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static class Render extends EntityRenderer<CushionEntity> {

        public Render(EntityRendererManager p_i46179_1_) {
            super(p_i46179_1_);
        }

        @Override
        public boolean shouldRender(CushionEntity p_225626_1_, ClippingHelperImpl p_225626_2_, double p_225626_3_,
                                    double p_225626_5_, double p_225626_7_) {
            return false;
        }

        @Override
        public ResourceLocation getEntityTexture(CushionEntity p_110775_1_) {
            return null;
        }
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {}

    @Override
    public void readSpawnData(PacketBuffer additionalData) {}
} */