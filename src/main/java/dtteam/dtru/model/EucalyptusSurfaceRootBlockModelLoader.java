package dtteam.dtru.model;

import com.dtteam.dynamictrees.model.geometry.BranchBlockModelGeometry;
import com.dtteam.dynamictrees.model.loader.BranchBlockModelLoader;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

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