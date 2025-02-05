package dtteam.dtru.init;

import com.ferreusveritas.dynamictrees.api.client.ModelHelper;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import dtteam.dtru.block.BambooBranchBlock;
import dtteam.dtru.tree.EucalyptusFamily;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

public class DTRUClient {

    public static void setup() {
        registerRenderLayers();
        registerColorHandlers();
    }

    private static void registerRenderLayers () {
        ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof BambooBranchBlock)
                .forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped()));
    }

    private static void registerColorHandlers() {
        final int white = 0xFFFFFFFF;
        final int magenta = 0x00FF00FF;//for errors... because magenta sucks.

        // Register Eucalyptus branch Colorizers
        for (EucalyptusFamily family : Family.REGISTRY.getAll().stream().filter(f -> f instanceof EucalyptusFamily).map(f -> (EucalyptusFamily)f).collect(Collectors.toSet())) {
            family.getBranch().ifPresent(branchBlock ->
                    ModelHelper.regColorHandler(branchBlock, (state, level, pos, tintIndex) ->
                            pos != null ? family.branchColorMultiplier(state, level, pos) : magenta
                    )
            );
            family.getSurfaceRoot().ifPresent(surfaceRoot ->
                    ModelHelper.regColorHandler(surfaceRoot, (state, level, pos, tintIndex) ->
                            pos != null ? family.branchColorMultiplier(state, level, pos) : magenta
                    )
            );
        }

    }

}
