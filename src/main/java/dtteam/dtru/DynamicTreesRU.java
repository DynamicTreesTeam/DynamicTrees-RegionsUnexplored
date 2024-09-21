package dtteam.dtru;

import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.block.rooty.SoilProperties;
import com.ferreusveritas.dynamictrees.resources.Resources;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.systems.pod.Pod;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import dtteam.dtru.init.DTRUClient;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(dtteam.dtru.DynamicTreesRU.MOD_ID)
public class DynamicTreesRU
{
    public static final String MOD_ID = "dtru";

    public DynamicTreesRU() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::gatherData);

        MinecraftForge.EVENT_BUS.register(this);

        RegistryHandler.setup(MOD_ID);
        dtteam.dtru.init.DTRURegistries.setup();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        dtteam.dtru.init.DTRURegistries.setupBlocks();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DTRUClient.setup();
    }

    private void gatherData(final GatherDataEvent event) {
        Resources.MANAGER.gatherData();
        GatherDataHelper.gatherAllData(
                MOD_ID,
                event,
                SoilProperties.REGISTRY,
                Family.REGISTRY,
                Species.REGISTRY,
                LeavesProperties.REGISTRY,
                Fruit.REGISTRY,
                Pod.REGISTRY
        );
    }

    public static ResourceLocation location(final String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
