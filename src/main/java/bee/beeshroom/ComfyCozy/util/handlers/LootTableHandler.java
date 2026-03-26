package bee.beeshroom.ComfyCozy.util.handlers;


import java.util.logging.Logger;

import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootTableHandler {
	
/*	public static final ResourceLocation ENTITIES_OATMEALSHEEP = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "oatmealsheep"));
    public static final ResourceLocation ENTITIES_OATMEALSHEEP_PLAIN = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "entityoatmealsheep/plain"));
    public static final ResourceLocation ENTITIES_OATMEALSHEEP_PEACH = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "entityoatmealsheep/peach"));
    public static final ResourceLocation ENTITIES_DIRTY_PIG = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "EntityDirtyPig"));
    public static final ResourceLocation ENTITIES_SHROOMINI = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "EntityMushy"));
*/
    public static final ResourceLocation JUNGLE_TEMPLE = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "jungle_temple"));
    
    @SubscribeEvent
    public void LoadLootTables(LootTableLoadEvent event)
    {
    	ResourceLocation name = event.getName();
    	if (name.toString() == "minecraft:chests/jungle_temple")
    	{
    		LootEntryTable entry = new LootEntryTable(JUNGLE_TEMPLE, 1, 0, null, "comfycozy_jungle_temple");
    		event.getTable().addPool(new LootPool(new LootEntry[] { entry }, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "comfycozy_jungle_temple"));
    	}
    }
}