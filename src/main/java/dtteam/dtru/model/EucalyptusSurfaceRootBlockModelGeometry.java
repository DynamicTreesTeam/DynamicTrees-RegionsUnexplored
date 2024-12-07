package dtteam.dtru.model;

import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;

import java.util.function.Function;

public class EucalyptusSurfaceRootBlockModelGeometry extends EucalyptusBranchBlockModelGeometry {

    public EucalyptusSurfaceRootBlockModelGeometry(final ResourceLocation barkResLoc, final ResourceLocation overlayResLoc) {
        super(barkResLoc,null, overlayResLoc, null);
    }

    @Override
    public BakedModel bake(IGeometryBakingContext owner, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform,
                           ItemOverrides overrides, ResourceLocation modelLocation) {
        return new EucalyptusSurfaceRootBlockBakedModel(modelLocation, this.barkTextureLocation, overlayTextureLocation, spriteGetter);
    }
}