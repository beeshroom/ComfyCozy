package bee.beeshroom.comfycozy.blocks;

import com.google.common.collect.Lists;
import javafx.scene.layout.Pane;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import java.util.Queue;
import java.util.Random;
import java.util.stream.Stream;

//thanks TechnoVision

public class FishTank extends Block implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(15, 0, 0, 16, 16, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 16, 1),
            Block.makeCuboidShape(0, 0, 15, 16, 16, 16),
            Block.makeCuboidShape(0, 0, 0, 1, 16, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

/*    public FishTank()
    {
        super(Properties.create(Material.GLASS).doesNotBlockMovement().hardnessAndResistance(0.4F).sound(SoundType.GLASS));
    }

    public FishTank(Block.Properties properties) {
        super(properties);} */

    public FishTank() {
        super(Properties.create(Material.GLASS)
                        .hardnessAndResistance(2.0f, 3.0f)
                        .sound(SoundType.GLASS)
                        .harvestLevel(0)
                        .harvestTool(ToolType.PICKAXE)
                        .notSolid()
                //.setRequiresTool()
        );
        //   this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(POWERED, Boolean.valueOf(false)));
        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.valueOf(false)));
    }

   // public notSolid();

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }

    public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }

    public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
        return false;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_N;
    }

    /*  @Override
      public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity item, Hand handIn, BlockRayTraceResult hit) {
          return null;
      }
  */
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

  /*  @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
                                          BlockPos currentPos, BlockPos facingPos)
    {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    } */

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
      //  return facing.getAxis().isHorizontal() ? stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), this.canAttachTo(facingState, facingState.isSolidSide(worldIn, facingPos, facing.getOpposite()))) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);

    }

    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        queue.add(new Tuple<>(pos, 0));
        Tuple<BlockPos, Integer> tuple = queue.poll();
        BlockPos blockpos = tuple.getA();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        IFluidState ifluidstate = worldIn.getFluidState(blockpos);
        Material material = blockstate.getMaterial();
        if (state.getBlock() == Blocks.AIR) {
            if (blockstate.getBlock() instanceof FlowingFluidBlock) {
                worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 3);
            }
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }

 /*
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity item, Hand handIn, BlockRayTraceResult hit) {

        BlockPos blockpos = pos.north();
        BlockPos blockpos1 = pos.east();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        BlockState blockstate1 = worldIn.getBlockState(blockpos);
        BlockState blockstate2 = worldIn.getBlockState(blockpos2);
        BlockState blockstate3 = worldIn.getBlockState(blockpos3);

        IFluidState ifluidstate = worldIn.getFluidState(blockpos);

      //  Material material = blockstate.getMaterial();
        if (ifluidstate.isTagged(FluidTags.WATER))
        {
            if (state.getBlock() == Blocks.AIR) {
                if (blockstate.getBlock() instanceof FlowingFluidBlock) {
                    worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                }
                if (blockstate1.getBlock() instanceof FlowingFluidBlock) {
                    worldIn.setBlockState(blockpos1, Blocks.AIR.getDefaultState());
                }
                if (blockstate2.getBlock() instanceof FlowingFluidBlock) {
                    worldIn.setBlockState(blockpos2, Blocks.AIR.getDefaultState());
                }
                if (blockstate3.getBlock() instanceof FlowingFluidBlock) {
                    worldIn.setBlockState(blockpos3, Blocks.AIR.getDefaultState());
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    } */
}
