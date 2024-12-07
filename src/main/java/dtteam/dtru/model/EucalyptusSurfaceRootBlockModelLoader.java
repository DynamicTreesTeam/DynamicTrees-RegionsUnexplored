package dtteam.dtru.model;

import com.ferreusveritas.dynamictrees.models.geometry.BranchBlockModelGeometry;
import com.ferreusveritas.dynamictrees.models.loader.BranchBlockModelLoader;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EucalyptusSurfaceRootBlockModelLoader extends BranchBlockModelLoader {

    private static final String OVERLAY = "overlay";

    protected ResourceLocation getOverlayTextureLocation(final JsonObject textureObject) {
        return this.getTextureLocation(textureObject, OVERLAY);
    }

    @Override
    public BranchBlockModelGeometry read(JsonObject modelObject, JsonDeserializationContext deserializationContext) {
        final JsonObject textures = this.getTexturesObject(modelObject);
        return new EucalyptusSurfaceRootBlockModelGeometry(this.getBarkTextureLocation(textures), getOverlayTextureLocation(textures));
    }

    @Override
    protected String getModelTypeName() {
        return "Eucalyptus Surface Root";
    }

}