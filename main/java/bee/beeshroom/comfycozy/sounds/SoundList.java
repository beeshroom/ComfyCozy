package bee.beeshroom.comfycozy.sounds;

import bee.beeshroom.comfycozy.comfycozy;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundList {

    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, comfycozy.MOD_ID);

    public static final RegistryObject<SoundEvent> MYSTICAL_MEOW = SOUNDS.register("block.mystical_meow", () -> new SoundEvent(new ResourceLocation(comfycozy.MOD_ID, "block.mystical_meow")));
}
