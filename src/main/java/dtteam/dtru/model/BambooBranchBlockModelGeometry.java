package dtteam.dtru.model;

import com.ferreusveritas.dynamictrees.models.geometry.BranchBlockModelGeometry;
import com.ferreusveritas.dynamictreesplus.model.baked.CactusBranchBlockBakedModel;
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

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class BambooBranchBlockModelGeometry extends BranchBlockModelGeometry {

    protected final ResourceLocation leavesTextureLocation;

    public BambooBranchBlockModelGeometry(ResourceLocation barkTextureLocation, ResourceLocation ringsTextureLocation, ResourceLocation leavesTextureLocation) {
        super(barkTextureLocation, ringsTextureLocation, null, false);
        this.leavesTextureLocation = leavesTextureLocation;
    }

    @Override
    public BakedModel bake(IGeometryBakingContext owner, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
        return new BambooBranchBlockBakedModel(owner, modelLocation, this.barkTextureLocation, this.ringsTextureLocation, this.leavesTextureLocation, spriteGetter);
    }

}
