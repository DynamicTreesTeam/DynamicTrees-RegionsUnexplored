package dtteam.dtru.tree;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.block.branch.BasicBranchBlock;
import com.ferreusveritas.dynamictrees.block.branch.BranchBlock;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import dtteam.dtru.block.TransitionLogBranchBlock;
import net.minecraft.resources.ResourceLocation;

public class TransitionLogFamily extends Family {

    public static final TypedRegistry.EntryType<Family> TYPE_STRIPPED = TypedRegistry.newType(res -> new TransitionLogFamily(res, true));
    public static final TypedRegistry.EntryType<Family> TYPE_BASE = TypedRegistry.newType(res -> new TransitionLogFamily(res, false));

    public TransitionLogFamily(ResourceLocation name, boolean stripped) {
        super(name);
        transitionOnStripped = stripped;
    }

    boolean transitionOnStripped;

    @Override
    protected BranchBlock createBranchBlock(ResourceLocation name) {
        final BasicBranchBlock branch = new TransitionLogBranchBlock(name, this.getProperties(), transitionOnStripped);
        if (this.isFireProof()) branch.setFireSpreadSpeed(0).setFlammability(0);
        return branch;
    }

}
