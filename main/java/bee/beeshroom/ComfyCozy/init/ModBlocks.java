package bee.beeshroom.ComfyCozy.init;

import java.util.ArrayList;
import java.util.List;

import bee.beeshroom.ComfyCozy.blocks.BlockBase;
import bee.beeshroom.ComfyCozy.blocks.crops.strawberry_plant;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks 
{

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block STRAWBERRY_PLANT = new strawberry_plant("strawberry_plant");
 	public static final Block CINNAMON_TREE = new BlockBase("cinnamon_tree", Material.WOOD);		

	
	//furniture
	public static final Block AWNING_RED = new BlockBase("awning_red", Material.CLOTH);
	
	
}