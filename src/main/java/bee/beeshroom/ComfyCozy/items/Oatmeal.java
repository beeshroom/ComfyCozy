package bee.beeshroom.comfycozy.items;

import bee.beeshroom.comfycozy.init.BlockInit;
import bee.beeshroom.comfycozy.init.ItemInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class Oatmeal extends Item
{

    public Oatmeal(Item.Properties builder) {
        super(builder);
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {

        PlayerEntity playerentity = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;

        ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);

   /*     if (entityLiving instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
        } */

        if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
            stack.shrink(1);
        }

        if (!stack.isEmpty()) {
            //return new ItemStack(Items.BOWL);
           playerentity.addItemStackToInventory(new ItemStack(Items.BOWL, 1));
          //  spawnAsEntity(worldIn, entityLiving.getPosition(), new ItemStack(Items.BOWL), 1));
       }
   /*     if (stack.isEmpty())
        {
            return new ItemStack(Items.BOWL);
        } */


        return stack.isEmpty() ? new ItemStack(Items.BOWL) : stack;
     //   return stack;
    }


    //@Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        World worldIn = context.getWorld();
        PlayerEntity player = context.getPlayer();
       // ItemStack itemstack = player.getHeldItem();
        //ItemStack stack = player.getHeldItem(handIn);
       // Item item = stack.getItem();
        BlockPos blockpos = context.getPos();


       if (player.isSneaking() && worldIn.isAirBlock(blockpos.up()))
        {
               // worldIn.setBlockState(pos.up(), BlockInit.BOWL_OATMEAL.get().getDefaultState());
            //BlockPos blockpos = new BlockPos(pos);
            BlockState blockstate = BlockInit.BOWL_OATMEAL.get().getDefaultState();

            if (blockstate.isValidPosition(worldIn, blockpos)) {
                worldIn.setBlockState(blockpos.up(), blockstate, 3);

                if(!((PlayerEntity)player).abilities.isCreativeMode && !worldIn.isRemote)
                {
                    context.getItem().shrink(1);
                }
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
}
