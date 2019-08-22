package bee.beeshroom.ComfyCozy.util.handlers;


import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {
	
	public static final ResourceLocation ENTITIES_OATMEALSHEEP = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "oatmealsheep"));
    public static final ResourceLocation ENTITIES_OATMEALSHEEP_PLAIN = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "entityoatmealsheep/plain"));
    public static final ResourceLocation ENTITIES_OATMEALSHEEP_PEACH = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "entityoatmealsheep/peach"));
}