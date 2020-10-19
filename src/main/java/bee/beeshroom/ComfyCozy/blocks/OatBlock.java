package bee.beeshroom.comfycozy.blocks;

import bee.beeshroom.comfycozy.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class OatBlock extends Block {

    public OatBlock() {
        super(Block.Properties.create(Material.WOOL)
                        .hardnessAndResistance(1.0f, 0.4f)
                        .sound(SoundType.CLOTH)
                        .harvestLevel(0)
                        .harvestTool(ToolType.SHOVEL)
                //.setRequiresTool()
        );
    }
}