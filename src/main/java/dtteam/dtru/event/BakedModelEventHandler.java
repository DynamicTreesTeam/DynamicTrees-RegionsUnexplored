package dtteam.dtru.event;

import dtteam.dtru.DynamicTreesRU;
import dtteam.dtru.model.BambooBranchModelLoader;
import dtteam.dtru.model.EucalyptusBranchModelLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DynamicTreesRU.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class BakedModelEventHandler {

    public static final ResourceLocation BAMBOO = DynamicTreesRU.location("bamboo");
    public static final ResourceLocation EUCALYPTUS = DynamicTreesRU.location("eucalyptus");

    @SubscribeEvent
    public static void onModelRegistryEvent(ModelEvent.RegisterGeometryLoaders event) {
        event.register(BAMBOO.getPath(), new BambooBranchModelLoader());
        event.register(EUCALYPTUS.getPath(), new EucalyptusBranchModelLoader());
    }

}
