package bee.beeshroom.comfycozy.blocks;

import bee.beeshroom.comfycozy.init.TileEntityInit;
import bee.beeshroom.comfycozy.sounds.SoundList;
import bee.beeshroom.comfycozy.tileentity.BlackLuckyCat;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.BeaconTileEntity;
import net.minecraft.tileentity.BellTileEntity;
import net.minecraft.tileentity.ConduitTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Random;

import static net.minecraft.block.HorizontalBlock.HORIZONTAL_FACING;
import static net.minecraft.block.RedstoneTorchBlock.LIT;

//thanks TechnoVision

public class LuckyCat_Black extends HorizontalBlock implements IWaterLoggable {
    public static final BooleanProperty POWERED = LIT;
    private static final DirectionProperty FACING = HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape SHAPE = Block.makeCuboidShape(1,0, 1, 15, 16, 15);

    public LuckyCat_Black() {
        super(Properties.create(Material.EARTH)
                .hardnessAndResistance(2.0f, 2.0f)
                .sound(SoundType.STONE)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                //.setRequiresTool()
        );
     //   this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(POWERED, Boolean.valueOf(false)));
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(POWERED, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
        return 1;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(POWERED, Boolean.valueOf(context.getWorld().isBlockPowered(context.getPos())));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE;
            case SOUTH:
                return SHAPE;
            case EAST:
                return SHAPE;
            case WEST:
                return SHAPE;
            default:
                return SHAPE;
        }
    }

    public void onEntityWalk(World p_176199_1_, BlockPos p_176199_2_, Entity p_176199_3_) {
        if (p_176199_3_ instanceof MonsterEntity) {
            p_176199_3_.attackEntityFrom(DamageSource.MAGIC, 1.0F);
        }

        super.onEntityWalk(p_176199_1_, p_176199_2_, p_176199_3_);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        ItemStack itemstack = player.getHeldItem(handIn);
        Item item = itemstack.getItem();

    /*    if ((item == Items.GOLD_INGOT) && player.isPotionActive(Effects.LUCK)){
            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.BLOCKS, 1.0F, 1.0f);
        }
        */
        if ((item == Items.GOLD_INGOT))
      {
          {
                if (!worldIn.isRemote)
                {
                 player.removePotionEffect(Effects.BAD_OMEN);
                    player.removePotionEffect(Effects.NAUSEA);
                    player.removePotionEffect(Effects.SLOWNESS);
                    player.removePotionEffect(Effects.BLINDNESS);
                    player.removePotionEffect(Effects.POISON);
                    player.removePotionEffect(Effects.MINING_FATIGUE);
                    player.removePotionEffect(Effects.UNLUCK);
                    player.removePotionEffect(Effects.WEAKNESS);
                    player.removePotionEffect(Effects.WITHER);
                    player.addPotionEffect(new EffectInstance(Effects.LUCK, 3600, 0));
                 //   player.addPotionEffect(new EffectInstance(Effects.LUCK, 3600, 0));
                      }
          worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0f);

          if(!player.abilities.isCreativeMode) itemstack.shrink(1);
               // return ActionResultType.SUCCESS;
              //TileEntityInit.LUCKYCAT_BLACK.get().create();
            }
}
        if (!worldIn.isRemote)  //(itemstack.isEmpty() && player.isSneaking())
        {
            //BlockState blockstate1 = state.cycle(LIT);
            worldIn.setBlockState(pos, state.cycle(LIT), 2);
       //     worldIn.playSound((PlayerEntity) null, pos, SoundList.MYSTICAL_MEOW.get(), SoundCategory.BLOCKS, 1.0F, 1.0f);
          //  return ActionResultType.SUCCESS;
        }
       // else

          //  BlockState blockstate = this.func_226939_d_(state, worldIn, pos);
          //  float f = blockstate.get(LIT) ? 0.6F : 0.5Freturn ActionResultType.SUCCESS;
            worldIn.playSound((PlayerEntity) null, pos, SoundList.MYSTICAL_MEOW.get(), SoundCategory.BLOCKS, 1.0F, 0.8f);
        return ActionResultType.SUCCESS;
    }

   /* public BlockState func_226939_d_(BlockState p_226939_1_, World p_226939_2_, BlockPos p_226939_3_) {
        p_226939_1_ = p_226939_1_.cycle(LIT);
        p_226939_2_.setBlockState(p_226939_3_, p_226939_1_, 3);
        this.updateNeighbors(p_226939_1_, p_226939_2_, p_226939_3_);
        return p_226939_1_;
    } */

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(LIT) && rand.nextFloat() < 0.25F) {
        } }

 /*   @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return blockState.get(LIT) ? 15 : 0;
    }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return blockState.get(LIT) ? 15 : 0;
    } */

    @Override
    public boolean canProvidePower(BlockState state) {
        //changed true to false :))
        return false;
    }

    private void updateNeighbors(BlockState p_196378_1_, World p_196378_2_, BlockPos p_196378_3_) {
        p_196378_2_.notifyNeighborsOfStateChange(p_196378_3_, this);
        //p_196378_2_.notifyNeighborsOfStateChange(p_196378_3_.offset(getFacing(p_196378_1_).getOpposite()), this);
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
        builder.add(FACING, POWERED, WATERLOGGED);
    }

    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
                                          BlockPos currentPos, BlockPos facingPos)
    {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 0.7F;
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        Block block = worldIn.getBlockState(pos.down()).getBlock();
        if (!worldIn.isRemote) {
            boolean flag = state.get(LIT);
            if (flag != worldIn.isBlockPowered(pos)) {
                if (flag) {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                } else
                    {
                    worldIn.setBlockState(pos, state.cycle(LIT), 2);
                    //BlockState blockstate1 = state.cycle(LIT);
                        if (block == Blocks.NOTE_BLOCK)
                        {
                            worldIn.playSound((PlayerEntity)null, pos, SoundList.MYSTICAL_MEOW.get(), SoundCategory.BLOCKS, 2.0F, 0.8f);
                        }
                        //trying a do/while, i dunno ;;
                        // update: THAT BROKE THE GAME SO BAD LOL
                  /*      do {
                            worldIn.setBlockState(pos, state.cycle(LIT), 2);
                        } while (flag = worldIn.isBlockPowered(pos)); */
                }
            }
        }
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (state.get(LIT) && !worldIn.isBlockPowered(pos)) {
            worldIn.setBlockState(pos, state.cycle(LIT), 2);
        }
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
            return TileEntityInit.LUCKYCAT_BLACK.get().create();
    }

/*   public void onBlockPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        if (p_180633_5_.hasDisplayName()) {
            TileEntity lvt_6_1_ = p_180633_1_.getTileEntity(p_180633_2_);
            if (lvt_6_1_ instanceof BeaconTileEntity) {
                ((BeaconTileEntity)lvt_6_1_).setCustomName(p_180633_5_.getDisplayName());
            }
        }
    } */


//i slashed this out
    public TileEntity createNewTileEntity(IBlockReader p_196283_1_) {
        return new BlackLuckyCat();
    }
}