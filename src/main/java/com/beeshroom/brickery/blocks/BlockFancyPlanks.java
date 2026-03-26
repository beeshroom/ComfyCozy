package com.beeshroom.brickery.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

import com.beeshroom.brickery.util.handlers.ConfigHandler;

public class BlockFancyPlanks extends BlockBase {

    private String type;

    public BlockFancyPlanks(EnumFancyPlanks fancyPlanks) {
        super(String.format("fancy_%s_planks", fancyPlanks.getName()), Material.WOOD);

        this.type = fancyPlanks.getType();
        setSoundType(SoundType.WOOD);
        setHardness(2.0f);
        setResistance(10.0f);
        setHarvestLevel("axe", 0);
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 5;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (ConfigHandler.TOOLTIPS) tooltip.add(type);
    }

}