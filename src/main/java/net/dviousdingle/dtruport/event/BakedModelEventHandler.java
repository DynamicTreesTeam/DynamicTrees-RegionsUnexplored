package net.dviousdingle.dtruport.event;

//import dtteam.dtru.DtruPort;
//import dtteam.dtru.model.BambooBranchModelLoader;
//import dtteam.dtru.model.EucalyptusBranchModelLoader;
//import dtteam.dtru.model.EucalyptusSurfaceRootBlockModelLoader;
import net.dviousdingle.dtruport.DtruPort;
import net.dviousdingle.dtruport.model.BambooBranchModelLoader;
import net.dviousdingle.dtruport.model.EucalyptusBranchModelLoader;
import net.dviousdingle.dtruport.model.EucalyptusSurfaceRootBlockModelLoader;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.fml.common.Mod;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.ModelEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;


@EventBusSubscriber(modid = DtruPort.MOD_ID, value = Dist.CLIENT)
public final class BakedModelEventHandler {

    public static final ResourceLocation BAMBOO = DtruPort.location("bamboo");
    public static final ResourceLocation EUCALYPTUS = DtruPort.location("eucalyptus");
    public static final ResourceLocation EUCALYPTUS_SURFACE_ROOT = DtruPort.location("eucalyptus_surface_root");

    @SubscribeEvent
    public static void onModelRegistryEvent(ModelEvent.RegisterGeometryLoaders event) {
        event.register(BAMBOO, new BambooBranchModelLoader());
        event.register(EUCALYPTUS, new EucalyptusBranchModelLoader());
        event.register(EUCALYPTUS_SURFACE_ROOT, new EucalyptusSurfaceRootBlockModelLoader());
    }

}
