package bee.beeshroom.comfycozy.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.List;

public class Cushion extends HorizontalBlock implements IWaterLoggable {
        private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
        public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

        public static final VoxelShape SHAPE = Block.makeCuboidShape(1, 0, 1, 15, 6, 15);

        public Cushion() {
            super(Properties.create(Material.WOOL)
                            .hardnessAndResistance(1.0f, 1.0f)
                            .sound(SoundType.CLOTH)
                            .harvestLevel(0)
                            .harvestTool(ToolType.AXE)
                    //.setRequiresTool()
            );
            //   this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(POWERED, Boolean.valueOf(false)));
            this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
        }

        @Override
        public BlockState getStateForPlacement(BlockItemUseContext context) {
            return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
        }

        @Override
        public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
            return SHAPE;
        }

        @Override
        public BlockState rotate(BlockState state, Rotation rot) {
            return state.with(FACING, rot.rotate(state.get(FACING)));
        }

        @Override
        public BlockState mirror(BlockState state, Mirror mirrorIn) {
            return state.rotate(mirrorIn.toRotation(state.get(FACING)));
        }


        protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
            builder.add(FACING, WATERLOGGED);
        }

        @SuppressWarnings("deprecation")
        public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
                                              BlockPos currentPos, BlockPos facingPos)
        {
            if (stateIn.get(WATERLOGGED)) {
                worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
            }

            return facing == Direction.DOWN && !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);

            // return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }

        public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
            return hasEnoughSolidSide(worldIn, pos.down(), Direction.UP);
        }

        public PushReaction getPushReaction(BlockState state) {
            return PushReaction.DESTROY;
        }

        public IFluidState getFluidState(BlockState state) {
            return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
        }

        @Override
        public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
            return 0.9F;
        }


        @Override
        public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
            return 40;
        }

//bed is 0.5f and slime block is 0.0f
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.3F);
    }

    public void onLanded(IBlockReader worldIn, Entity entityIn) {
        if (entityIn.isSuppressingBounce()) {
            super.onLanded(worldIn, entityIn);
        } else {
            this.func_226860_a_(entityIn);
        }
    }

    private void func_226860_a_(Entity p_226860_1_) {
        Vec3d vec3d = p_226860_1_.getMotion();
        if (vec3d.y < 0.0D) {
            double d0 = p_226860_1_ instanceof LivingEntity ? 1.0D : 0.8D;
            p_226860_1_.setMotion(vec3d.x, -vec3d.y * (double)0.66F * d0, vec3d.z);
        }

    }

    }

