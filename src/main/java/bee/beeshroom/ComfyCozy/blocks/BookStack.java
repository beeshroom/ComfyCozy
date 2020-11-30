package bee.beeshroom.comfycozy.blocks;

import bee.beeshroom.comfycozy.sounds.SoundList;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.NoteBlockEvent;

import java.util.Random;
import java.util.stream.Stream;

//thanks TechnoVision

public class BookStack extends HorizontalBlock implements IWaterLoggable {
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final IntegerProperty BOOK_STYLE = IntegerProperty.create("book_style", 1, 3);
    public static final IntegerProperty BOOK_COLOR = IntegerProperty.create("book_color", 1, 3);

    public static final VoxelShape SHAPE = Block.makeCuboidShape(2, 0, 2, 15, 8, 14);


    public BookStack() {
        super(Properties.create(Material.WOOL)
                .hardnessAndResistance(1.4f, 1.0f)
                .sound(SoundType.CLOTH)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE)
                //.setRequiresTool()
        );
     //   this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(POWERED, Boolean.valueOf(false)));
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(BOOK_STYLE, Integer.valueOf(1)).with(BOOK_COLOR, Integer.valueOf(1)));
    }

    @Override
    public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
        return 1;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        //BlockState blockstate = this.getDefaultState();
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
        builder.add(FACING, WATERLOGGED, BOOK_STYLE, BOOK_COLOR);
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




    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        int i = state.get(BOOK_STYLE);
        int c = state.get(BOOK_COLOR);

        worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.BLOCKS, 0.4F, 0.8F);

          //  if (!worldIn.isRemote)
           if (!player.isSneaking())
            {
                if (i < 3)
                {
                    worldIn.setBlockState(pos, state.with(BOOK_STYLE, Integer.valueOf(i + 1)), 2);

                }
                else
                {
                    worldIn.setBlockState(pos, state.with(BOOK_STYLE, Integer.valueOf(1)), 2);
                }
            }
        if (player.isSneaking())
        {
            if (c < 3)
            {
                worldIn.setBlockState(pos, state.with(BOOK_COLOR, Integer.valueOf(c + 1)), 2);

            }
            else
            {
                worldIn.setBlockState(pos, state.with(BOOK_COLOR, Integer.valueOf(1)), 2);
            }
        }
        return ActionResultType.SUCCESS;
}

 /*   private boolean changeMode(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
    {
        {
            worldIn.playSound((PlayerEntity) null, pos, SoundList.LUCKY_CAT_ATTACK.get(), SoundCategory.BLOCKS, 0.4F, 0.8F);
          //  int i = ((Integer)state.getValue()).intValue();
//int i = ((Integer)state.getValue(MODE)).intValue();
int i = state.get(BOOK_STYLE);
            if (i <= 1)
            {
                worldIn.setBlockState(pos, state.with(BOOK_STYLE, Integer.valueOf(i + 1)), 2);

            }
            else
            {
                worldIn.setBlockState(pos, state.with(BOOK_STYLE, Integer.valueOf(1)), 2);
            }

            return true;
        }
    } */
}
