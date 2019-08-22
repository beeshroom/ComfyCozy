package bee.beeshroom.ComfyCozy.init;

import java.util.ArrayList;
import java.util.List;

import bee.beeshroom.ComfyCozy.items.ItemBase;
import bee.beeshroom.ComfyCozy.items.food.ItemCustomFood;
import bee.beeshroom.ComfyCozy.items.tools.ToolSword;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemBlockSpecial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	//public static final ToolMaterial MATERIAL_BONE = EnumHelper.addToolMaterial("material_bone", 1, 75, 4.0F, 1.9F, 15);
	
	
	//items
	//public static final Item OATS = new ItemBase("oats");
	public static final Item STRAWBERRY = new ItemCustomFood("strawberry", 3, false);
	public static final Item OATS = new ItemCustomFood("oats", 2, false);
	
	public static final Item STRAWBERRY_SEEDS = new ItemBase("strawberry_seeds");

	public static final Item CINNAMON = new ItemBase("cinnamon");
	//public static final Item CINNAMON_TREE = new ItemBlockSpecial(ModBlocks.CINNAMON_TREE);
	//public static final Item CINNAMON_TREE_ITEM = new ItemBase("cinnamon_tree_item");
	
	
	//Tools
	//public static final ItemSword SKELETON_ARM = new ToolSword("skeleton_arm", MATERIAL_BONE);


	 }
