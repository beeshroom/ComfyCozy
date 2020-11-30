package bee.beeshroom.comfycozy.entities.Cushion;

import bee.beeshroom.comfycozy.blocks.Cushion;
import bee.beeshroom.comfycozy.entities.Shroomin.ShroominEntity;
import bee.beeshroom.comfycozy.init.EntityTypes;
import jdk.nashorn.internal.ir.Block;
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
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Create Mod

public class CushionEntity extends Entity {

    public static final Map<Integer, Map<BlockPos, CushionEntity>> IN_USE = new HashMap<Integer, Map<BlockPos, CushionEntity>>();

    public CushionEntity(EntityType<CushionEntity> type, World world)
    {
        super(type, world);
    }

    public CushionEntity(World world, BlockPos pos) {
        this(EntityTypes.CUSHION.get(), world);
        setPosition(pos.getX() + 0.5D, pos.getY() + 0.12D, pos.getZ() + 0.5D);
        noClip = true;
    }

    @Override
    protected void registerData() {
    }

    @Override
    public void readAdditional(CompoundNBT p_70037_1_) {
    }

    @Override
    public void writeAdditional(CompoundNBT p_213281_1_) {
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static boolean trackUse(IWorld world, BlockPos pos, CushionEntity entity)
    {
        if(!world.isRemote())
        {
            int id = world.getDimension().getType().getId();

            if(!IN_USE.containsKey(id))
            {
                IN_USE.put(id, new HashMap<>());
            }

            IN_USE.get(id).put(pos, entity);
            return true;
        }

        return false;
    }

    public static boolean stopTrackingUse(IWorld world, BlockPos pos)
    {
        if(!world.isRemote())
        {
            int id = world.getDimension().getType().getId();

            if(IN_USE.containsKey(id))
            {
                IN_USE.get(id).remove(pos);
                return true;
            }
        }

        return false;
    }

    public static boolean isInUse(IWorld world, BlockPos pos)
    {
        int id = world.getDimension().getType().getId();

        return IN_USE.containsKey(id) && IN_USE.get(id).containsKey(pos);
    }

    public static CushionEntity getTrackedCushion(IWorld world, BlockPos pos)
    {
        int id = world.getDimension().getType().getId();

        if(IN_USE.containsKey(id) && IN_USE.get(id).containsKey(pos))
        {
            return IN_USE.get(id).get(pos);
        }

        return null;
    }
}