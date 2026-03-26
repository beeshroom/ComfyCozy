package bee.beeshroom.ComfyCozy.advancements;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import bee.beeshroom.ComfyCozy.util.Reference;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

// adapted from the KilledTrigger code
public class GoldAppleLambTrigger implements ICriterionTrigger<GoldAppleLambTrigger.Instance>
{
    private final Map<PlayerAdvancements, GoldAppleLambTrigger.Listeners> listeners = Maps.<PlayerAdvancements, GoldAppleLambTrigger.Listeners>newHashMap();
    private static final ResourceLocation ID = new ResourceLocation(Reference.MOD_ID, "gold_apple_lamb");
    public static final GoldAppleLambTrigger INSTANCE = new GoldAppleLambTrigger();
    
    private GoldAppleLambTrigger() {}

    public ResourceLocation getId()
    {
        return ID;
    }

    public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<GoldAppleLambTrigger.Instance> listener)
    {
        GoldAppleLambTrigger.Listeners gapplelambtrigger$listeners = this.listeners.get(playerAdvancementsIn);

        if (gapplelambtrigger$listeners == null)
        {
        	gapplelambtrigger$listeners = new GoldAppleLambTrigger.Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, gapplelambtrigger$listeners);
        }

        gapplelambtrigger$listeners.add(listener);
    }

    public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<GoldAppleLambTrigger.Instance> listener)
    {
        GoldAppleLambTrigger.Listeners gapplelambtrigger$listeners = this.listeners.get(playerAdvancementsIn);

        if (gapplelambtrigger$listeners != null)
        {
        	gapplelambtrigger$listeners.remove(listener);

            if (gapplelambtrigger$listeners.isEmpty())
            {
                this.listeners.remove(playerAdvancementsIn);
            }
        }
    }

    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn)
    {
        this.listeners.remove(playerAdvancementsIn);
    }

    public GoldAppleLambTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
    {
        return new GoldAppleLambTrigger.Instance(ID);
    }

    public void trigger(EntityPlayerMP player)
    {
        GoldAppleLambTrigger.Listeners gapplelambtrigger$listeners = this.listeners.get(player.getAdvancements());
        
        if (gapplelambtrigger$listeners != null)
        {
        	Logger.getLogger(Reference.MOD_ID).info("triggered");
        	gapplelambtrigger$listeners.trigger(player);
        }
    }

    public static class Instance extends AbstractCriterionInstance
    {
        public Instance(ResourceLocation criterionIn)
        {
            super(criterionIn);
        }
    }

    static class Listeners
    {
        private final PlayerAdvancements playerAdvancements;
        private final Set<ICriterionTrigger.Listener<GoldAppleLambTrigger.Instance>> listeners = Sets.<ICriterionTrigger.Listener<GoldAppleLambTrigger.Instance>>newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn)
        {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty()
        {
            return this.listeners.isEmpty();
        }

        public void add(ICriterionTrigger.Listener<GoldAppleLambTrigger.Instance> listener)
        {
            this.listeners.add(listener);
        }

        public void remove(ICriterionTrigger.Listener<GoldAppleLambTrigger.Instance> listener)
        {
            this.listeners.remove(listener);
        }

        public void trigger(EntityPlayerMP player)
        {
            List<ICriterionTrigger.Listener<GoldAppleLambTrigger.Instance>> list = null;

            for (ICriterionTrigger.Listener<GoldAppleLambTrigger.Instance> listener : this.listeners)
            {
                if (list == null)
                {
                    list = Lists.<ICriterionTrigger.Listener<GoldAppleLambTrigger.Instance>>newArrayList();
                }

                list.add(listener);
            }

            if (list != null)
            {
                for (ICriterionTrigger.Listener<GoldAppleLambTrigger.Instance> listener1 : list)
                {
                    listener1.grantCriterion(this.playerAdvancements);
                }
            }
        }
    }
}