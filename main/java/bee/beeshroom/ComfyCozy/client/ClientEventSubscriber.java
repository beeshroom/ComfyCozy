package bee.beeshroom.ComfyCozy.client;

import static net.minecraftforge.fml.relauncher.Side.CLIENT;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bee.beeshroom.ComfyCozy.entity.EntityOatmealSheep;
import bee.beeshroom.ComfyCozy.entity.RenderOatmealSheep;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * Subscribe to events that should be handled on the PHYSICAL CLIENT in this class
 *
 * @author Cadiboo
 */

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = CLIENT)
public final class ClientEventSubscriber {

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * This method will be called by Forge when it is time for the mod to register its models.
	 * This method will always be called after the block and item registry methods.
	 */
	@SubscribeEvent
	public static void onRegisterModelsEvent(@Nonnull final ModelRegistryEvent event) {

		registerTileEntitySpecialRenderers();
		LOGGER.debug("Registered tile entity special renderers");

		registerEntityRenderers();
		LOGGER.debug("Registered entity renderers");

		 ForgeRegistries.ITEMS.getValuesCollection().stream()
		.filter(item -> item.getRegistryName().getResourceDomain().equals(Reference.MOD_ID))
		.forEach(item -> {
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));
		});

		LOGGER.debug("Registered models"); 

	}

	/**
	 * Helper method to register all TESRs in
	 */
	private static void registerTileEntitySpecialRenderers() {

//		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExampleTileEntity.class, new RenderExampleTileEntity());

	}

	/**
	 * Helper method to register all Entity Renderers in
	 */
	private static void registerEntityRenderers() {

		RenderingRegistry.registerEntityRenderingHandler(EntityOatmealSheep.class, RenderOatmealSheep::new);

	}

}