package bee.beeshroom.ComfyCozy.init;

import java.util.ArrayList;
import java.util.List;

import bee.beeshroom.ComfyCozy.items.ItemBase;
import bee.beeshroom.ComfyCozy.items.oat_seeds;
import bee.beeshroom.ComfyCozy.items.strawberry_seeds;
import bee.beeshroom.ComfyCozy.items.food.ItemCustomFood;
import bee.beeshroom.ComfyCozy.items.food.cinnamon;
import bee.beeshroom.ComfyCozy.items.food.cinnamon_oatmeal;
import bee.beeshroom.ComfyCozy.items.food.gold_apple_cinnamon_oatmeal;
import bee.beeshroom.ComfyCozy.items.food.oatmeal;
import bee.beeshroom.ComfyCozy.items.food.strawberry;
import bee.beeshroom.ComfyCozy.items.food.strawberry_oatmeal;
import net.minecraft.item.Item;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	//public static final ToolMaterial MATERIAL_BONE = EnumHelper.addToolMaterial("material_bone", 1, 75, 4.0F, 1.9F, 15);
	
	
	//items
	//public static final Item OATS = new ItemBase("oats");
	public static final Item STRAWBERRY = new strawberry("strawberry", 1, .1f, false);
	public static final Item OATS = new ItemCustomFood("oats", 2, 0.5f, false);
	
	public static final Item STRAWBERRY_SEEDS = new strawberry_seeds("strawberry_seeds");
	public static final Item OAT_SEEDS = new oat_seeds("oat_seeds");

	public static final Item CINNAMON = new cinnamon("cinnamon", 0, 0, false);
	//public static final Item CINNAMON_TREE = new ItemBlockSpecial("cinnamon_tree");
	//public static final Item CINNAMON_TREE_ITEM = new ItemBase("cinnamon_tree_item");
	
	
	public static final Item OATMEAL = new oatmeal("oatmeal", 6, .6f, false);
	public static final Item CINNAMON_OATMEAL = new cinnamon_oatmeal("cinnamon_oatmeal", 7, .9f, false);
	public static final Item STRAWBERRY_OATMEAL = new strawberry_oatmeal("strawberry_oatmeal", 7, .9f, false);
	public static final Item GOLD_APPLE_CINNAMON_OATMEAL = new gold_apple_cinnamon_oatmeal("gold_apple_cinnamon_oatmeal", 8, 1.2f, false);
	
	public static final Item COZY_HAMMER = new ItemBase("cozy_hammer");
	
	//Tools
	//public static final ItemSword SKELETON_ARM = new ToolSword("skeleton_arm", MATERIAL_BONE);


	 }
