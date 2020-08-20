package bee.beeshroom.comfycozy.world.gen;

import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.init.EntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

//thank you for your tutorial TechnoVision  ( https://www.youtube.com/watch?v=Vcz4Adcu5bY )

    @Mod.EventBusSubscriber(modid = comfycozy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class EntitySpawns {

        @SubscribeEvent
        public static void generateEntities(FMLLoadCompleteEvent event) {
            for (Biome biome : ForgeRegistries.BIOMES) {

                if (biome.getCategory() == Biome.Category.NETHER) { }

                else if (biome.getCategory() == Biome.Category.THEEND) { }

                else {
                    if (biome.getCategory() == Biome.Category.MUSHROOM && biome.getCategory() != Biome.Category.OCEAN) {
                        biome.getSpawns(EntityClassification.CREATURE)
                                .add(new Biome.SpawnListEntry(EntityTypes.SHROOMIN_ENTITY.get(), 8, 2, 4));
                           //   .add(new Biome.SpawnListEntry(EntityTypes.SHROOMIN_ENTITY.get(), 8, 2, 4));

                    }
                }
            }
        }
    }