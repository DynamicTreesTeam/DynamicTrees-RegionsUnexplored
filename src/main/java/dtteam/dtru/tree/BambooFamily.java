package dtteam.dtru.tree;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.block.branch.BranchBlock;
import com.ferreusveritas.dynamictrees.data.provider.BranchLoaderBuilder;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.util.BlockBounds;
import dtteam.dtru.block.BambooBranchBlock;
import dtteam.dtru.event.BakedModelEventHandler;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.BiFunction;

public class BambooFamily extends Family {

    public static final TypedRegistry.EntryType<Family> TYPE = TypedRegistry.newType(BambooFamily::new);

    public BambooFamily(ResourceLocation name) {
        super(name);
    }

    @Override
    protected BranchBlock createBranchBlock(ResourceLocation name) {
        return new BambooBranchBlock(name, this.getProperties());
    }

//    @Override
//    public int getPrimaryThickness() {
//        return 1;
//    }
//
//    @Override
//    public int getSecondaryThickness() {
//        return 2;
//    }

    public BlockBounds expandLeavesBlockBounds(BlockBounds bounds) {
        return bounds.expand(3).expand(Direction.DOWN, 3);
    }

    @Override
    public BiFunction<BlockModelBuilder, ExistingFileHelper, BranchLoaderBuilder> getBranchLoaderConstructor() {
        return (parent, existingFileHelper) -> new BranchLoaderBuilder(BakedModelEventHandler.BAMBOO, parent, existingFileHelper);
    }

}
