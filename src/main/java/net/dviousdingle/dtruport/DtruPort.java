package net.dviousdingle.dtruport;

//import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
//import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
//import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
//import com.ferreusveritas.dynamictrees.block.rooty.SoilProperties;
//import com.ferreusveritas.dynamictrees.tree.family.Family;
//import com.ferreusveritas.dynamictrees.tree.species.Species;
//import dtteam.dtru.init.DTRUClient;
//import dtteam.dtru.init.DTRUPlusRegistries;
//import dtteam.dtru.init.DTRURegistries;
import com.dtteam.dynamictrees.api.registry.RegistryHandler;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.data.GatherDataHelper;
import com.dtteam.dynamictrees.registry.NeoForgeRegistryHandler;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictrees.treepack.Resources;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import net.dviousdingle.dtruport.init.DTRUClient;
import net.dviousdingle.dtruport.init.DTRUPlusRegistries;
import net.dviousdingle.dtruport.init.DTRURegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.data.event.GatherDataEvent;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.fml.ModList;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DtruPort.MOD_ID)

//@EventBusSubscriber(modid = DtruPort.MOD_ID)
public class DtruPort {
    public static final String MOD_ID = "dtru";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final boolean useLogger = Objects.equals(System.getProperty("forgegradle.runs.dev"), "true");

    public DtruPort(IEventBus bus, ModContainer modContainer) {
//        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);
//        NeoForge.EVENT_BUS.addListener(DtruPort::gatherData);

        if (isDynamicTreesPlusLoaded()){
            bus.register(new DTRUPlusRegistries());
        }

//        NeoForge.EVENT_BUS.register(this);
//        MinecraftForge.EVENT_BUS.register(this);
//        bus.register(this);
        NeoForgeRegistryHandler.setup(MOD_ID, bus);
//        RegistryHandler.setup(MOD_ID);
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

//    @SubscribeEvent
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

//        Resources.MANAGER.gatherData();
//        start.dataGen(event);
    }

    public static void logger(Object... x) {

        // if (General.bool.get())
        if (useLogger) {
            StringBuilder output = new StringBuilder();

            for (Object i : x) {
                if (i == null) output.append(", ").append("null");
                else if (i.getClass().isArray()) {
                    output.append(", [");
                    for (Object c : (int[]) i) {
                        output.append(c).append(",");
                    }
                    output.append("]");
                } else if (i instanceof List) {
                    output.append(", [");
                    for (Object c : (List) i) {
                        output.append(c);
                    }
                    output.append("]");
                } else
                    output.append(", ").append(i);
            }
            LOGGER.info(output.substring(1));
        }

    }

    public static ResourceLocation location (final String name){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static boolean isDynamicTreesPlusLoaded(){
        return ModList.get().isLoaded("dynamictreesplus");
    }
}
