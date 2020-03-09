package bee.beeshroom.ComfyCozy.init;

import java.util.ArrayList;
import java.util.List;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.items.ItemBase;
import bee.beeshroom.ComfyCozy.items.oat_seeds;
import bee.beeshroom.ComfyCozy.items.strawberry_seeds;
import bee.beeshroom.ComfyCozy.items.armor.ArmorModel;
import bee.beeshroom.ComfyCozy.items.food.ItemCustomFood;
import bee.beeshroom.ComfyCozy.items.food.cinnamon;
import bee.beeshroom.ComfyCozy.items.food.cinnamon_cookie;
import bee.beeshroom.ComfyCozy.items.food.cinnamon_oatmeal;
import bee.beeshroom.ComfyCozy.items.food.gold_apple_cinnamon_oatmeal;
import bee.beeshroom.ComfyCozy.items.food.oatmeal;
import bee.beeshroom.ComfyCozy.items.food.strawberry;
import bee.beeshroom.ComfyCozy.items.food.strawberry_jam;
import bee.beeshroom.ComfyCozy.items.food.strawberry_oatmeal;
import bee.beeshroom.ComfyCozy.items.tools.cozy_hammer;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	//public static final ToolMaterial MATERIAL_BONE = EnumHelper.addToolMaterial("material_bone", 1, 75, 4.0F, 1.9F, 15);
	
	
	//items
	//public static final Item OATS = new ItemBase("oats");
	public static final Item STRAWBERRY = new strawberry("strawberry", 3, .5f, false);
	public static final Item OATS = new ItemCustomFood("oats", 2, .09f, false);
	
	public static final Item STRAWBERRY_SEEDS = new strawberry_seeds("strawberry_seeds");
	public static final Item OAT_SEEDS = new oat_seeds("oat_seeds");

	public static final Item CINNAMON = new cinnamon("cinnamon", 0, 0, false);
	public static final Item CINNAMON_STICK = new ItemBase("cinnamon_stick");
	//public static final Item CINNAMON_TREE = new ItemBlockSpecial("cinnamon_tree");
	//public static final Item CINNAMON_TREE_ITEM = new ItemBase("cinnamon_tree_item");
	
	
	public static final Item OATMEAL = new oatmeal("oatmeal", 6, .6f, false);
	public static final Item CINNAMON_OATMEAL = new cinnamon_oatmeal("cinnamon_oatmeal", 8, .65f, false);
	public static final Item STRAWBERRY_OATMEAL = new strawberry_oatmeal("strawberry_oatmeal", 8, .65f, false);
	public static final Item GOLD_APPLE_CINNAMON_OATMEAL = new gold_apple_cinnamon_oatmeal("gold_apple_cinnamon_oatmeal", 10, .67f, false);
	public static final Item CINNAMON_COOKIE = new cinnamon_cookie("cinnamon_cookie", 2, .4f, false);
	public static final Item STRAWBERRY_JAM = new strawberry_jam("strawberry_jam", 6, .52f, false);
	public static final Item COZY_HAMMER = new cozy_hammer("cozy_hammer");
	
	//Tools
	//public static final ItemSword SKELETON_ARM = new ToolSword("skeleton_arm", MATERIAL_BONE);
	
	//Materials
	public static final ArmorMaterial ARMOR_MUSHROOM = EnumHelper.addArmorMaterial("armor_mushroom", Reference.MOD_ID + ":armor_mushroom", 
			5, new int[] {1, 2, 3, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f);
	//Armor
	public static final Item MUSHROOM_BERET = new ArmorModel("mushroom_beret", Main.comfycozytab, ARMOR_MUSHROOM, EntityEquipmentSlot.HEAD);

	 }
