package bee.beeshroom.ComfyCozy.world.generation.generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

//thankyou harry's tech reviews for your tutorial on custom tree generation

public class WorldGenTrees implements IWorldGenerator
{
	private final WorldGenerator CINNAMON = new WorldGenCinnamon(true);
	//private final WorldGenerator STRAWBERRY = new WorldGenStrawberry(null);
	//private final WorldGenerator TURKEYTAIL = new WorldGenTurkeytail();
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		switch(world.provider.getDimension())
		{
		case 1:
			break;
			
		case 0:
			
			runGenerator(CINNAMON, world, random, chunkX, chunkZ, 85, Blocks.GRASS, BiomeJungle.class);
			runGenerator(CINNAMON, world, random, chunkX, chunkZ, 85, Blocks.DIRT, BiomeJungle.class);
			//runGenerator(CINNAMON, world, random, chunkX, chunkZ, 85, Blocks.LEAVES, BiomeJungle.class);
			//runGenerator(CINNAMON, world, random, chunkX, chunkZ, 85, Blocks.LEAVES2, BiomeJungle.class);
		//	System.out.println("JUNGLE");
			runGenerator(CINNAMON, world, random, chunkX, chunkZ, 15, Blocks.GRASS, BiomeForest.class);
			
		//	runGenerator(STRAWBERRY, world, random, chunkX, chunkZ, 15, Blocks.GRASS, BiomeForest.class);
		//	runGenerator(STRAWBERRY, world, random, chunkX, chunkZ, 15, Blocks.GRASS, BiomeTaiga.class);
			
			//runGenerator(TURKEYTAIL, world, random, chunkX, chunkZ, 10, Blocks.LOG, BiomeForest.class);
			//runGenerator(TURKEYTAIL, world, random, chunkX, chunkZ, 15, Blocks.LOG2, BiomeForest.class);
			
		//	runGenerator(CINNAMON, world, random, chunkX, chunkZ, 85, Blocks.GRASS, getBiomes(BiomeDictionary.Type.JUNGLE));
			
			break;
			
		case -1:
		}}
	
	/*private static Biome[] getBiomes(final BiomeDictionary.Type type) {
		return BiomeDictionary.getBiomes(type).toArray(new Biome[0]);
	}*/
	
	private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock);
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(random.nextInt(chance) == 0)
				{
					generator.generate(world, random, pos);
				}
			}}}
	
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock)
	{
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = block == topBlock;
		}
		
		return y;
	}}