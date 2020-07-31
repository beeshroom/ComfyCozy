package bee.beeshroom.comfycozy.blocks;

import com.sun.jna.platform.win32.WinDef;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class Bunting extends HorizontalBlock implements IWaterLoggable {

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty LONG = BooleanProperty.create("long");

    public static final VoxelShape NORTH = Block.makeCuboidShape(0, 8.5, 5.5, 16, 14.5, 10.5);
    public static final VoxelShape EAST = Block.makeCuboidShape(5.5, 8.5, 0, 10.5, 14.5, 16);
    public static final VoxelShape STRING_N = Block.makeCuboidShape(0, 13, 7.499999999999998, 16, 14, 8.499999999999998);
    public static final VoxelShape STRING_E = Block.makeCuboidShape(7.500000000000002, 13, -1.7486012637846216e-15, 8.500000000000002, 14, 15.999999999999995);
    public Bunting() {
        super(Properties.create(Material.WOOL)
                        .hardnessAndResistance(0f, 0f)
                        .sound(SoundType.CLOTH)
                        .harvestLevel(0)
                      //  .harvestTool(ToolType.AXE)
                //.setRequiresTool()
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(LONG, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
    }

    /*public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.offset(state.get(HORIZONTAL_FACING))).getBlock();
        return block.isIn(BlockTags.JUNGLE_LOGS);
    } */

    @Override
    public VoxelShape getCollisionShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_)
    {  switch(((Direction)p_220053_1_.get(FACING)).getAxis()) {
        case X:
        default:
            return STRING_N;
        case Z:
            return STRING_E;
        //return VoxelShapes.empty();
        } }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return (BlockState)p_185499_1_.with(FACING, p_185499_2_.rotate((Direction)p_185499_1_.get(FACING)));
    }

    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        return (BlockState)p_185471_1_.with(FACING, p_185471_2_.mirror((Direction)p_185471_1_.get(FACING)));
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch(((Direction)p_220053_1_.get(FACING)).getAxis()) {
            case X:
            default:
                return NORTH;
            case Z:
                return EAST;
        }
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return type == PathType.AIR && !this.blocksMovement ? true : super.allowsMovement(state, worldIn, pos, type);
    }

   /* public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        Direction lvt_2_1_ = p_196258_1_.getFace();
        BlockState lvt_3_1_ = p_196258_1_.getWorld().getBlockState(p_196258_1_.getPos().offset(lvt_2_1_.getOpposite()));
        return lvt_3_1_.getBlock() == this && lvt_3_1_.get(FACING) == lvt_2_1_ ? (BlockState)this.getDefaultState().with(FACING, lvt_2_1_.getOpposite()) : (BlockState)this.getDefaultState().with(FACING, lvt_2_1_);
    } */

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = this.getDefaultState();
        IWorldReader iworldreader = context.getWorld();
        BlockPos blockpos = context.getPos();

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate = blockstate.with(HORIZONTAL_FACING, direction);
                if (blockstate.isValidPosition(iworldreader, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, LONG, WATERLOGGED);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
                                          BlockPos currentPos, BlockPos facingPos)
    {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
     //  return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
       return facing == stateIn.get(HORIZONTAL_FACING) && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }



    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    public PushReaction getPushReaction(BlockState p_149656_1_) {
        return PushReaction.DESTROY;
    }
}

