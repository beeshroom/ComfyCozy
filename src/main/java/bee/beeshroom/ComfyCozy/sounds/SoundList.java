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

    public static final RegistryObject<SoundEvent> LUCKY_CAT_ATTACK = SOUNDS.register("block.lucky_cat_attack", () -> new SoundEvent(new ResourceLocation(comfycozy.MOD_ID, "block.lucky_cat_attack")));

    public static final RegistryObject<SoundEvent> LUCKY = SOUNDS.register("block.lucky", () -> new SoundEvent(new ResourceLocation(comfycozy.MOD_ID, "block.lucky")));

    public static final RegistryObject<SoundEvent> SHROOMIN_SPLAT = SOUNDS.register("entity.shroomin_splat", () -> new SoundEvent(new ResourceLocation(comfycozy.MOD_ID, "entity.shroomin_splat")));

    public static final RegistryObject<SoundEvent> SHROOMIN_DEATH = SOUNDS.register("entity.shroomin_death", () -> new SoundEvent(new ResourceLocation(comfycozy.MOD_ID, "entity.shroomin_death")));

    public static final RegistryObject<SoundEvent> SHROOMIN_EAT = SOUNDS.register("entity.shroomin_eat", () -> new SoundEvent(new ResourceLocation(comfycozy.MOD_ID, "entity.shroomin_eat")));

  //  public static final RegistryObject<SoundEvent> SHROOMIN_EAT = SOUNDS.register("entity.shroomin_eat", () -> new SoundEvent(new ResourceLocation(comfycozy.MOD_ID, "entity.shroomin_eat2")));

}
