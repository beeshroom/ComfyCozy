package bee.beeshroom.ComfyCozy.init;

import java.util.ArrayList;
import java.util.List;

import bee.beeshroom.ComfyCozy.blocks.cinnamon_log;
import bee.beeshroom.ComfyCozy.blocks.cinnamon_sapling;
//import bee.beeshroom.ComfyCozy.blocks.cinnamon_tree;
import bee.beeshroom.ComfyCozy.blocks.flower_box;
import bee.beeshroom.ComfyCozy.blocks.flower_box_white;
import bee.beeshroom.ComfyCozy.blocks.glass_panel;
import bee.beeshroom.ComfyCozy.blocks.jam_block;
import bee.beeshroom.ComfyCozy.blocks.oat_block;
//import bee.beeshroom.ComfyCozy.blocks.stripped_cinnamon;
import bee.beeshroom.ComfyCozy.blocks.crops.oat_plant;
import bee.beeshroom.ComfyCozy.blocks.crops.strawberry_plant;
import bee.beeshroom.ComfyCozy.blocks.food.bowl_cinnamon;
import bee.beeshroom.ComfyCozy.blocks.food.bowl_gold_apple;
import bee.beeshroom.ComfyCozy.blocks.food.bowl_oatmeal;
import bee.beeshroom.ComfyCozy.blocks.food.bowl_strawberry;
import bee.beeshroom.ComfyCozy.blocks.food.strawberry_cake;
import bee.beeshroom.ComfyCozy.blocks.food.taiga_toast;
import bee.beeshroom.ComfyCozy.blocks.furniture.bonfire;
import bee.beeshroom.ComfyCozy.blocks.furniture.book_stack;
import bee.beeshroom.ComfyCozy.blocks.furniture.bunting;
import bee.beeshroom.ComfyCozy.blocks.furniture.bunting_check;
import bee.beeshroom.ComfyCozy.blocks.furniture.bunting_heart;
import bee.beeshroom.ComfyCozy.blocks.furniture.bunting_spook;
import bee.beeshroom.ComfyCozy.blocks.furniture.candle_jar;
import bee.beeshroom.ComfyCozy.blocks.furniture.carp_banner;
import bee.beeshroom.ComfyCozy.blocks.furniture.carp_banner_2;
import bee.beeshroom.ComfyCozy.blocks.furniture.cushion_diamond;
import bee.beeshroom.ComfyCozy.blocks.furniture.cushion_fire;
import bee.beeshroom.ComfyCozy.blocks.furniture.cushion_pika;
import bee.beeshroom.ComfyCozy.blocks.furniture.cushion_porg;
import bee.beeshroom.ComfyCozy.blocks.furniture.cushion_zigzag;
import bee.beeshroom.ComfyCozy.blocks.furniture.daruma;
import bee.beeshroom.ComfyCozy.blocks.furniture.fishjar;
import bee.beeshroom.ComfyCozy.blocks.furniture.fishjar_clown;
import bee.beeshroom.ComfyCozy.blocks.furniture.fishjar_puff;
import bee.beeshroom.ComfyCozy.blocks.furniture.fishjar_salmon;
import bee.beeshroom.ComfyCozy.blocks.furniture.pole;
import bee.beeshroom.ComfyCozy.blocks.furniture.table;
import bee.beeshroom.ComfyCozy.blocks.furniture.weathervane;
import bee.beeshroom.ComfyCozy.blocks.furniture.awnings.awning_green;
import bee.beeshroom.ComfyCozy.blocks.furniture.awnings.awning_light_blue;
import bee.beeshroom.ComfyCozy.blocks.furniture.awnings.awning_pink;
import bee.beeshroom.ComfyCozy.blocks.furniture.awnings.awning_red;
import bee.beeshroom.ComfyCozy.blocks.furniture.awnings.awning_white;
import bee.beeshroom.ComfyCozy.blocks.furniture.awnings.awning_yellow;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks 
{

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//food
	public static final Block STRAWBERRY_CAKE = new strawberry_cake("strawberry_cake", Material.CAKE);
	public static final Block TAIGA_TOAST = new taiga_toast("taiga_toast", Material.CAKE);
//	public static final Block APPLE_PIE = new apple_pie("apple_pie", Material.CAKE);
	
	//plants
	public static final Block STRAWBERRY_PLANT = new strawberry_plant("strawberry_plant");
	public static final Block OAT_PLANT = new oat_plant("oat_plant");
 //	public static final Block CINNAMON_TREE = new cinnamon_tree("cinnamon_tree", Material.WOOD);
	//public static final Block STRIPPED_CINNAMON = new stripped_cinnamon("stripped_cinnamon", Material.WOOD);
	//public static final Block TURKEY_TAIL_MUSHROOM = new turkey_tail_mushroom("turkey_tail_mushroom", Material.PLANTS);
	
	public static final Block CINNAMON_LOG = new cinnamon_log("cinnamon_log", Material.WOOD);
//	public static final Block CINNAMON_LEAVES = new cinnamon_leaves("cinnamon_leaves", Material.LEAVES);
	public static final Block CINNAMON_SAPLING = new cinnamon_sapling("cinnamon_sapling", Material.PLANTS);
	
	//furniture
	public static final Block BUNTING = new bunting("bunting", Material.CLOTH);
	public static final Block BUNTING_CHECK = new bunting_check("bunting_check", Material.CLOTH);
	public static final Block BUNTING_HEART = new bunting_heart("bunting_heart", Material.CLOTH);
	public static final Block BUNTING_SPOOK = new bunting_spook("bunting_spook", Material.CLOTH);
	
	public static final Block AWNING_WHITE = new awning_white("awning_white", Material.WOOD);
 	public static final Block AWNING_RED = new awning_red("awning_red", Material.WOOD);
	public static final Block AWNING_YELLOW = new awning_yellow("awning_yellow", Material.WOOD);
	public static final Block AWNING_GREEN = new awning_green("awning_green", Material.WOOD);
	public static final Block AWNING_LIGHT_BLUE = new awning_light_blue("awning_light_blue", Material.WOOD);
	public static final Block AWNING_PINK = new awning_pink("awning_pink", Material.WOOD);
	
	public static final Block POLE = new pole("pole", Material.WOOD);
	public static final Block CARP_BANNER = new carp_banner("carp_banner", Material.CLOTH);
	public static final Block CARP_BANNER_2 = new carp_banner_2("carp_banner_2", Material.CLOTH);
	public static final Block DARUMA = new daruma("daruma", Material.ROCK);
	
	public static final Block BONFIRE = new bonfire("bonfire", Material.WOOD);
	
	public static final Block FISHJAR = new fishjar("fishjar", Material.GLASS);
	public static final Block FISHJAR_SALMON = new fishjar_salmon("fishjar_salmon", Material.GLASS);
	public static final Block FISHJAR_CLOWN = new fishjar_clown("fishjar_clown", Material.GLASS);
	public static final Block FISHJAR_PUFF = new fishjar_puff("fishjar_puff", Material.GLASS);
	
	public static final Block BOOK_STACK = new book_stack("book_stack", Material.CLOTH);
	
	public static final Block TABLE = new table("table", Material.WOOD);
	public static final Block WEATHERVANE = new weathervane("weathervane", Material.IRON);
	public static final Block FLOWER_BOX = new flower_box("flower_box", Material.GROUND);
	public static final Block FLOWER_BOX_WHITE = new flower_box_white("flower_box_white", Material.GROUND);
	public static final Block CANDLE_JAR = new candle_jar("candle_jar", Material.WOOD);
	//cushion
	public static final Block CUSHION_FIRE = new cushion_fire("cushion_fire", Material.CLOTH);
	public static final Block CUSHION_ZIGZAG = new cushion_zigzag("cushion_zigzag", Material.CLOTH);
	public static final Block CUSHION_PIKA = new cushion_pika("cushion_pika", Material.CLOTH);
	public static final Block CUSHION_DIAMOND = new cushion_diamond("cushion_diamond", Material.CLOTH);
	public static final Block CUSHION_PORG = new cushion_porg("cushion_porg", Material.CLOTH);

	//blocks
	public static final Block GLASS_PANEL = new glass_panel("glass_panel", Material.GLASS);
	public static final Block OAT_BLOCK = new oat_block("oat_block", Material.CLOTH);
	public static final Block JAM_BLOCK = new jam_block("jam_block", Material.CLOTH);
	
	//display foods
	public static final Block BOWL_OATMEAL = new bowl_oatmeal("bowl_oatmeal", Material.WOOD);
	public static final Block BOWL_CINNAMON = new bowl_cinnamon("bowl_cinnamon", Material.WOOD);
	public static final Block BOWL_STRAWBERRY = new bowl_strawberry("bowl_strawberry", Material.WOOD);
	public static final Block BOWL_GOLD_APPLE = new bowl_gold_apple("bowl_gold_apple", Material.WOOD);

	
	
	
}