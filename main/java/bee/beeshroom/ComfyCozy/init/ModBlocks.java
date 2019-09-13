package bee.beeshroom.ComfyCozy.init;

import java.util.ArrayList;
import java.util.List;

import bee.beeshroom.ComfyCozy.blocks.cinnamon_tree;
import bee.beeshroom.ComfyCozy.blocks.glass_panel;
import bee.beeshroom.ComfyCozy.blocks.strawberry_cake;
import bee.beeshroom.ComfyCozy.blocks.stripped_cinnamon;
import bee.beeshroom.ComfyCozy.blocks.crops.oat_plant;
import bee.beeshroom.ComfyCozy.blocks.crops.strawberry_plant;
import bee.beeshroom.ComfyCozy.blocks.furniture.awning_green;
import bee.beeshroom.ComfyCozy.blocks.furniture.awning_light_blue;
import bee.beeshroom.ComfyCozy.blocks.furniture.awning_pink;
import bee.beeshroom.ComfyCozy.blocks.furniture.awning_red;
import bee.beeshroom.ComfyCozy.blocks.furniture.awning_white;
import bee.beeshroom.ComfyCozy.blocks.furniture.awning_yellow;
import bee.beeshroom.ComfyCozy.blocks.furniture.bonfire;
import bee.beeshroom.ComfyCozy.blocks.furniture.book_stack;
import bee.beeshroom.ComfyCozy.blocks.furniture.bunting;
import bee.beeshroom.ComfyCozy.blocks.furniture.pole;
import bee.beeshroom.ComfyCozy.blocks.furniture.carp_banner;
import bee.beeshroom.ComfyCozy.blocks.furniture.fishjar;
import bee.beeshroom.ComfyCozy.blocks.oatmeal.bowl_oatmeal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks 
{

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//plants
	public static final Block STRAWBERRY_PLANT = new strawberry_plant("strawberry_plant");
	public static final Block OAT_PLANT = new oat_plant("oat_plant");
 	public static final Block CINNAMON_TREE = new cinnamon_tree("cinnamon_tree", Material.WOOD);		
	public static final Block STRIPPED_CINNAMON = new stripped_cinnamon("stripped_cinnamon", Material.WOOD);
	
	//furniture
	public static final Block BUNTING = new bunting("bunting", Material.CLOTH);
	public static final Block AWNING_WHITE = new awning_white("awning_white", Material.WOOD);
 	public static final Block AWNING_RED = new awning_red("awning_red", Material.WOOD);
	public static final Block AWNING_YELLOW = new awning_yellow("awning_yellow", Material.WOOD);
	public static final Block AWNING_PINK = new awning_pink("awning_pink", Material.WOOD);
	public static final Block AWNING_GREEN = new awning_green("awning_green", Material.WOOD);
	public static final Block AWNING_LIGHT_BLUE = new awning_light_blue("awning_light_blue", Material.WOOD);
	
	public static final Block POLE = new pole("pole", Material.WOOD);
	public static final Block CARP_BANNER = new carp_banner("carp_banner", Material.CLOTH);
	public static final Block BOWL_OATMEAL = new bowl_oatmeal("bowl_oatmeal", Material.WOOD);
	public static final Block FISHJAR = new fishjar("fishjar", Material.GLASS);
	public static final Block BONFIRE = new bonfire("bonfire", Material.WOOD);
	public static final Block BOOK_STACK = new book_stack("book_stack", Material.CLOTH);
	public static final Block GLASS_PANEL = new glass_panel("glass_panel", Material.GLASS);
	
	public static final Block STRAWBERRY_CAKE = new strawberry_cake("strawberry_cake", Material.CAKE);


	
	
}