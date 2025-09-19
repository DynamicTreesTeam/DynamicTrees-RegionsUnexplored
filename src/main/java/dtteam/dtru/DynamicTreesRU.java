package dtteam.dtru;

import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.data.GatherDataHelper;
import com.dtteam.dynamictrees.registry.NeoForgeRegistryHandler;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import dtteam.dtru.init.DTRUClient;
import dtteam.dtru.init.DTRUPlusRegistries;
import dtteam.dtru.init.DTRURegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DynamicTreesRU.MOD_ID)

public class DynamicTreesRU {
    public static final String MOD_ID = "dtru";

    public DynamicTreesRU(IEventBus bus, ModContainer modContainer) {

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);

        if (isDynamicTreesPlusLoaded()){
            bus.register(new DTRUPlusRegistries());
        }

        NeoForgeRegistryHandler.setup(MOD_ID, bus);
        DTRURegistries.setup();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        if (isDynamicTreesPlusLoaded()){
            DTRUPlusRegistries.setup();
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DTRUClient.setup();
    }

    private void gatherData(final GatherDataEvent event) {
        if (isDynamicTreesPlusLoaded()){
            DTRUPlusRegistries.gatherData(event);
        } else {
            GatherDataHelper.gatherAllData(MOD_ID, event,
                    SoilProperties.REGISTRY,
                    Family.REGISTRY,
                    Species.REGISTRY,
                    LeavesProperties.REGISTRY
            );
        }
    }

    public static ResourceLocation location (final String name){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static boolean isDynamicTreesPlusLoaded(){
        return ModList.get().isLoaded("dynamictreesplus");
    }
}
