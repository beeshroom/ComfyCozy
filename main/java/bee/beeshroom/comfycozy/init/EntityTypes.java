package bee.beeshroom.comfycozy.init;

import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.entities.Shroomin.ShroominEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


//thankyou for your tutorials turtywurty : https://www.youtube.com/watch?v=xTO-pVwpKwA

public class EntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES,
            comfycozy.MOD_ID);

    public static final RegistryObject<EntityType<ShroominEntity>> SHROOMIN_ENTITY = ENTITY_TYPES
            .register("shroomin",
                    () -> EntityType.Builder.<ShroominEntity>create(ShroominEntity::new, EntityClassification.CREATURE)

                            .size(0.6f, 0.7f)
                            .build(new ResourceLocation(comfycozy.MOD_ID, "shroomin_entity").toString()));

/*
    public static final RegistryObject<EntityType<CushionEntity>> CUSHION = ENTITY_TYPES
            .register("cushion_entity",
                    () -> EntityType.Builder.<CushionEntity>create(CushionEntity::new, EntityClassification.MISC)
            // );
                            .size(0.0f, 0.0f)
       .build(new ResourceLocation(comfycozy.MOD_ID, "cushion_entity").toString()));

                       //     .build(new ResourceLocation(null).toString()));

    //  public static final RegistryObject<EntityType<CushionEntity>> CUSHION = ENTITY_TYPES
  //            .register("cushion", CushionEntity::new, EntityClassification.MISC, 0, Integer.MAX_VALUE, false, CushionEntity::build);

    @OnlyIn(value = Dist.CLIENT)
    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(CUSHION.get(), CushionEntity.Render::new);
    } */
}