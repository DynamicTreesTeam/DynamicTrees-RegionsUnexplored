package dtteam.dtru.init;

import dtteam.dtru.block.BambooBranchBlock;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.registries.ForgeRegistries;

public class DTRUClient {

    public static void setup() {
        registerRenderLayers();
    }

    private static void registerRenderLayers () {
        ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof BambooBranchBlock)
                .forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped()));
    }

}
