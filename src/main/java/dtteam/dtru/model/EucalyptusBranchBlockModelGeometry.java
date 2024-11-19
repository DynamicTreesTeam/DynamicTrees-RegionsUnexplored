package dtteam.dtru.model;

import com.ferreusveritas.dynamictrees.models.geometry.BranchBlockModelGeometry;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;

import javax.annotation.Nullable;
import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class EucalyptusBranchBlockModelGeometry extends BranchBlockModelGeometry {

    protected final ResourceLocation overlayTextureLocation;

    public EucalyptusBranchBlockModelGeometry(ResourceLocation barkTextureLocation, ResourceLocation ringsTextureLocation, ResourceLocation overlayTextureLocation, @Nullable final ResourceLocation familyName) {
        super(barkTextureLocation, ringsTextureLocation, familyName, false);
        this.overlayTextureLocation = overlayTextureLocation;
    }

    @Override
    public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
        boolean useThickModel = this.useThickModel(this.setFamily(modelLocation));
        if (!useThickModel) {
            return new EucalyptusBranchBlockBakedModel(context, modelLocation, this.barkTextureLocation, this.ringsTextureLocation, this.overlayTextureLocation, spriteGetter);
        } else {
            if (this.thickRingsTextureLocation == null)
                this.thickRingsTextureLocation = this.ringsTextureLocation.withSuffix("_thick");
            return new ThickEucalyptusBranchBlockBakedModel(context, modelLocation, this.barkTextureLocation, this.ringsTextureLocation, this.overlayTextureLocation, this.thickRingsTextureLocation, spriteGetter);
        }
    }

    private ResourceLocation setFamilyName(final ResourceLocation modelLocation) {
        if (this.familyName == null) {
            this.familyName = new ResourceLocation(modelLocation.getNamespace(), modelLocation.getPath().replace("block/", "").replace("_branch", "").replace("stripped_", ""));
        }
        return this.familyName;
    }

    private Family setFamily(final ResourceLocation modelResLoc) {
        if (this.family == null) {
            this.family = Family.REGISTRY.get(this.setFamilyName(modelResLoc));
        }
        return this.family;
    }

}
