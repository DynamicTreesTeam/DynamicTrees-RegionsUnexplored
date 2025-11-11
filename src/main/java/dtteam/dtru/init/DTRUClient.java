package dtteam.dtru.init;

import com.dtteam.dynamictrees.tree.family.Family;
import dtteam.dtru.DynamicTreesRU;
import dtteam.dtru.block.BambooBranchBlock;
import dtteam.dtru.tree.EucalyptusFamily;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.stream.Collectors;

@EventBusSubscriber(modid = DynamicTreesRU.MOD_ID, value = Dist.CLIENT)
public class DTRUClient {

    public static void setup() {
        registerRenderLayers();
    }

    private static void registerRenderLayers () {
        BuiltInRegistries.BLOCK.stream().filter(block -> block instanceof BambooBranchBlock).forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped()));
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    private static void registerColorHandlers(RegisterColorHandlersEvent.Block event) {
        final int white = 0xFFFFFFFF;
        final int magenta = 0x00FF00FF;//for errors... because magenta sucks.

//         Register Eucalyptus branch Colorizers
        for (EucalyptusFamily family : Family.REGISTRY.getAll().stream().filter(f -> f instanceof EucalyptusFamily).map(f -> (EucalyptusFamily)f).collect(Collectors.toSet())) {
            family.getBranch().ifPresent(branchBlock ->{event.register((state, level, pos, tintIndex) -> pos != null
                    ? family.branchColorMultiplier(state, level, pos) : magenta, branchBlock);});
            family.getSurfaceRoot().ifPresent(surfaceRoot -> {event.register((state, level, pos, tintIndex) -> pos != null
                    ? family.branchColorMultiplier(state, level, pos) : magenta, surfaceRoot);
            });
        }

    }
}
