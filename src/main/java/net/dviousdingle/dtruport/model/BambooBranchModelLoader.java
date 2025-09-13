package net.dviousdingle.dtruport.model;

//import com.ferreusveritas.dynamictrees.api.TreeRegistry;
//import com.ferreusveritas.dynamictrees.models.geometry.BranchBlockModelGeometry;
//import com.ferreusveritas.dynamictrees.models.loader.BranchBlockModelLoader;
import com.dtteam.dynamictrees.model.geometry.BranchBlockModelGeometry;
import com.dtteam.dynamictrees.model.loader.BranchBlockModelLoader;
import com.dtteam.dynamictrees.api.registry.Registries;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.utility.ResourceLocationUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class BambooBranchModelLoader extends BranchBlockModelLoader {

    private static final String LEAVES = "leaves";

    protected ResourceLocation getLeavesTextureLocation(final JsonObject textureObject) {
        return this.getTextureLocation(textureObject, LEAVES);
    }

    @Override
    public BranchBlockModelGeometry read(JsonObject modelObject, JsonDeserializationContext deserializationContext) throws JsonParseException {
        final JsonObject textures = this.getTexturesObject(modelObject);
        final ResourceLocation familyName = this.getLocation(modelObject);

        return this.getModelGeometry(this.getBarkTextureLocation(textures), this.getRingsTextureLocation(textures), this.getLeavesTextureLocation(textures),
                familyName == null ? null : ResourceLocationUtils.parseDTLocation(familyName));
    }

    protected BranchBlockModelGeometry getModelGeometry(final ResourceLocation barkResLoc, final ResourceLocation ringsResLoc, final ResourceLocation leavesResLoc, @Nullable final ResourceLocation familyResLoc) {
        return new BambooBranchBlockModelGeometry(barkResLoc, ringsResLoc, leavesResLoc);
    }

}
