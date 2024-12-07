package dtteam.dtru.event;

import dtteam.dtru.DynamicTreesRU;
import dtteam.dtru.model.BambooBranchModelLoader;
import dtteam.dtru.model.EucalyptusBranchModelLoader;
import dtteam.dtru.model.EucalyptusSurfaceRootBlockModelLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DynamicTreesRU.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class BakedModelEventHandler {

    public static final ResourceLocation BAMBOO = DynamicTreesRU.location("bamboo");
    public static final ResourceLocation EUCALYPTUS = DynamicTreesRU.location("eucalyptus");
    public static final ResourceLocation EUCALYPTUS_SURFACE_ROOT = DynamicTreesRU.location("eucalyptus_surface_root");

    @SubscribeEvent
    public static void onModelRegistryEvent(ModelEvent.RegisterGeometryLoaders event) {
        event.register(BAMBOO.getPath(), new BambooBranchModelLoader());
        event.register(EUCALYPTUS.getPath(), new EucalyptusBranchModelLoader());
        event.register(EUCALYPTUS_SURFACE_ROOT.getPath(), new EucalyptusSurfaceRootBlockModelLoader());
    }

}
