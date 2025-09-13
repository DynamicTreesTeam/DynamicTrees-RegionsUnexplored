package net.dviousdingle.dtruport.init;

//import com.ferreusveritas.dynamictrees.api.client.ModelHelper;
//import com.ferreusveritas.dynamictrees.tree.family.Family;
//import dtteam.dtru.block.BambooBranchBlock;
//import dtteam.dtru.tree.EucalyptusFamily;
import com.dtteam.dynamictrees.model.ModelHelper;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.client.TextureHelper;

import net.dviousdingle.dtruport.DtruPort;
import net.dviousdingle.dtruport.block.BambooBranchBlock;
import net.dviousdingle.dtruport.tree.EucalyptusFamily;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

public class DTRUClient {

    public static void setup() {
        registerRenderLayers();
//        registerColorHandlers();
    }

    private static void registerRenderLayers () {
//        ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof BambooBranchBlock)
//                .forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped()));
        BuiltInRegistries.BLOCK.stream().filter(block -> block instanceof BambooBranchBlock).forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped()));
    }

//    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
//            BuiltInRegistries.BLOCK, DtruPort.MOD_ID
//    );

//    @SubscribeEvent
//    @OnlyIn(Dist.CLIENT)
//    private static void registerColorHandlers(registerBlockColorHandlersEvent .Block event) {
////        final int white = 0xFFFFFFFF;
//        final int magenta = 0x00FF00FF;//for errors... because magenta sucks.
//
////         Register Eucalyptus branch Colorizers
//        for (EucalyptusFamily family : Family.REGISTRY.getAll().stream().filter(f -> f instanceof EucalyptusFamily).map(f -> (EucalyptusFamily)f).collect(Collectors.toSet())) {
//            family.getBranch().ifPresent(branchBlock ->
//                    .regColorHandler(branchBlock, (state, level, pos, tintIndex) ->
//                            pos != null ? family. branchColorMultiplier(state, level, pos) : magenta
////                    event.getBlockColors().getColor(Blocks.).
//                    )
//            );
//            family.getSurfaceRoot().ifPresent(surfaceRoot ->
//                    surfaceRoot.regColorHandler(surfaceRoot, (state, level, pos, tintIndex) ->
//                            pos != null ? family.branchColorMultiplier(state, level, pos):magenta
//                    )
//            );
//        }
//
//    }
}
