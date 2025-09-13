package net.dviousdingle.dtruport.model;

//import com.ferreusveritas.dynamictrees.models.geometry.BranchBlockModelGeometry;
//import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.model.geometry.BranchBlockModelGeometry;
import com.dtteam.dynamictrees.tree.family.Family;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.client.model.geometry.IGeometryBakingContext;

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
    public BakedModel bake(IGeometryBakingContext context, ModelBaker modelBaker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides itemOverrides) {
        boolean useThickModel = false;
        if (!useThickModel) {
            return new EucalyptusBranchBlockBakedModel(context, this.barkTextureLocation, this.ringsTextureLocation, this.overlayTextureLocation, spriteGetter);
        } else {
            if (this.thickRingsTextureLocation == null)
                this.thickRingsTextureLocation = this.ringsTextureLocation.withSuffix("_thick");
            return new ThickEucalyptusBranchBlockBakedModel(context, this.barkTextureLocation, this.ringsTextureLocation, this.overlayTextureLocation, this.thickRingsTextureLocation, spriteGetter);
        }
    }

    private ResourceLocation setFamilyName(final ResourceLocation modelLocation) {
        if (this.familyName == null) {
            this.familyName = ResourceLocation.tryBuild(modelLocation.getNamespace(), modelLocation.getPath().replace("block/", "").replace("_branch", "").replace("stripped_", ""));
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
