package bee.beeshroom.comfycozy.world.gen;

import bee.beeshroom.comfycozy.blocks.CozyFlower;
import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.init.BlockInit;
import bee.beeshroom.comfycozy.init.EntityTypes;
import bee.beeshroom.comfycozy.lists.FeatureList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SeaPickleBlock;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;
import java.util.function.Function;

//Thankyou Mr.Pineapple https://github.com/Mr-Pineapple/Pine-Tutorial/blob/master/src/main/java/com/pineapple/tutorialmod/world/gen/TutorialGeneration.java


@Mod.EventBusSubscriber(modid = comfycozy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)


public class PlantGen
{

    private static void generateIris() {
       // ForgeRegistries.BIOMES.forEach(biome -> {
       for (Biome biome : ForgeRegistries.BIOMES)
       {

        if (biome == Biomes.FOREST || biome == Biomes.DARK_FOREST || biome == Biomes.DARK_FOREST_HILLS || biome == Biomes.FLOWER_FOREST) {
            biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                    Feature.FLOWER.withConfiguration(FeatureList.IRIS_CONFIG)
                            .withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
        }

           if (biome == Biomes.BIRCH_FOREST || biome == Biomes.TALL_BIRCH_FOREST || biome == Biomes.PLAINS || biome == Biomes.FOREST) {
               biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                       Feature.RANDOM_PATCH.withConfiguration(FeatureList.CLOVERS_CONFIG)
                               .withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
           }
            };
    }

    public static void generate() {
        generateIris();
    }

}