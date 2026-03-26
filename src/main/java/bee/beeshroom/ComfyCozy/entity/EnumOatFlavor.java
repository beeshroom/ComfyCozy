package bee.beeshroom.ComfyCozy.entity;

import java.util.Set;
import java.util.logging.Logger;
import com.google.common.collect.Sets;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public enum EnumOatFlavor {
	NONE(0, ModItems.OATS, ModItems.OATMEAL, ModBlocks.BOWL_OATMEAL),
	STRAWBERRY(1, ModItems.STRAWBERRY, ModItems.STRAWBERRY_OATMEAL, ModBlocks.BOWL_STRAWBERRY),
	CINNAMON(2, ModItems.CINNAMON, ModItems.CINNAMON_OATMEAL, ModBlocks.BOWL_CINNAMON),
	PEACH(3, ModItems.PEACH, ModItems.PEACH_OATMEAL, ModBlocks.BOWL_PEACH),
	GAPPLE(4, Items.GOLDEN_APPLE, ModItems.GOLD_APPLE_CINNAMON_OATMEAL, ModBlocks.BOWL_GOLD_APPLE);
	
	private final int meta;
	private final Item oatmealBowl;
	private final Item refillItem;
	private final Block deathBowl;
	
	private EnumOatFlavor(int metaIn, Item refillItemIn, Item oatmealBowlIn, Block deathBowlIn)
    {
        this.meta = metaIn;
        this.refillItem = refillItemIn;
        this.oatmealBowl = oatmealBowlIn;
        this.deathBowl = deathBowlIn;
    }
	
	public int getMetadata()
    {
        return this.meta;
    }
	
	public Item getRefillItem()
    {
        return this.refillItem;
    }
	
	public Item getOatmealBowl()
    {
        return this.oatmealBowl;
    }
	
	public Set<Item> getTemptationItems()
	{
		return Sets.newHashSet(this.getRefillItem(), this.getOatmealBowl());
	}
	
	public Block getDeathOatmealBowl()
    {
        return this.deathBowl;
    }
	
	private static final EnumOatFlavor[] META_LOOKUP = new EnumOatFlavor[values().length];
	
	public static EnumOatFlavor byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }
	
	static
    {
        for (EnumOatFlavor enumoatflavor : values())
        {
            META_LOOKUP[enumoatflavor.getMetadata()] = enumoatflavor;
        }
    }
}
