package bee.beeshroom.comfycozy.blocks;

import bee.beeshroom.comfycozy.init.BlockInit;
import bee.beeshroom.comfycozy.sounds.SoundList;
import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static net.minecraft.block.RedstoneTorchBlock.LIT;
import static net.minecraft.block.StemBlock.AGE;

public class GolemFlower extends BushBlock //implements IGrowable
         {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);


    public GolemFlower(Properties builder) {
        super(builder);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Vec3d vec3d = state.getOffset(worldIn, pos);
        return SHAPE.withOffset(vec3d.x, vec3d.y, vec3d.z);
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    }

    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        return false;
    }

  /*  public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof GolemEntity) {
            ((GolemEntity) entityIn).heal(5);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    } */

    /*
     public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        //  spawnAsEntity(worldIn, pos, new ItemStack(this)); }
        BlockState blockstate = Blocks.PUMPKIN_STEM.getDefaultState();

        worldIn.setBlockState(pos, blockstate.with(AGE, Integer.valueOf(7)), 2);

    }  */

/*
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getHeldItem(handIn);
        Item item = itemstack.getItem();


        if (item == Items.BONE_MEAL) {
            if (!player.abilities.isCreativeMode) itemstack.shrink(1);
            worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(7)), 2);
            return ActionResultType.SUCCESS;
        }
    } */
}