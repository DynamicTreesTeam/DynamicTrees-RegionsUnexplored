package dtteam.dtru.tree;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.block.branch.BasicBranchBlock;
import com.ferreusveritas.dynamictrees.block.branch.BranchBlock;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import net.minecraft.resources.ResourceLocation;
import com.ferreusveritas.dynamictrees.api.data.BranchStateGenerator;
import com.ferreusveritas.dynamictrees.api.data.Generator;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.block.branch.ThickBranchBlock;
import com.ferreusveritas.dynamictrees.data.provider.DTBlockStateProvider;
import com.ferreusveritas.dynamictrees.util.MutableLazyValue;
import com.ferreusveritas.dynamictrees.util.Optionals;
import com.ferreusveritas.dynamictrees.util.ResourceLocationUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public class BrimwoodFamily extends TransitionLogFamily {

    public static final TypedRegistry.EntryType<Family> TYPE = TypedRegistry.newType(BrimwoodFamily::new);

    public BrimwoodFamily(ResourceLocation name) {
        super(name, false, false);
        magmaBranchStateGenerator = MutableLazyValue.supplied(MagmaBranchStateGenerator::new);
    }

    protected Supplier<BranchBlock> magmaBranch;
    protected Block primitiveMagmaLog;
    protected final MutableLazyValue<Generator<DTBlockStateProvider, Family>> magmaBranchStateGenerator;

    @Override
    public void setupBlocks() {
        super.setupBlocks();

        this.magmaBranch = setupBranch(createMagmaBranch(getBranchName("magma_")), true);
    }

    protected BranchBlock createMagmaBranchBlock(ResourceLocation name) {
        BasicBranchBlock branch = new ThickBranchBlock(name, this.getProperties()){
            @Override
            public Optional<Block> getPrimitiveLog() {
                if (getFamily() instanceof BrimwoodFamily magmaLogFamily)
                    return magmaLogFamily.getPrimitiveMagmaLog();
                return super.getPrimitiveLog();
            }
        };
        if (this.isFireProof()) {
            branch.setFireSpreadSpeed(0).setFlammability(0);
        }

        return branch;
    }

    protected Supplier<BranchBlock> createMagmaBranch(ResourceLocation name) {
        return RegistryHandler.addBlock(ResourceLocationUtils.suffix(name, this.getBranchNameSuffix()), () -> this.createMagmaBranchBlock(name));
    }

    public Family setPrimitiveMagmaLog(Block primitiveLog) {
        this.primitiveMagmaLog = primitiveLog;
        magmaBranch.get().setPrimitiveLogDrops(new ItemStack(primitiveLog));
        return this;
    }

    public Optional<BranchBlock> getMagmaBranch() {
        return Optionals.ofBlock(magmaBranch.get());
    }

    public Optional<Block> getPrimitiveMagmaLog() {
        return Optionals.ofBlock(primitiveMagmaLog);
    }

    public void generateStateData(DTBlockStateProvider provider) {
        super.generateStateData(provider);
        (this.magmaBranchStateGenerator.get()).generate(provider, this);
    }

    public static class MagmaBranchStateGenerator extends BranchStateGenerator{
        public @NotNull Dependencies gatherDependencies(@NotNull Family input) {
            if (input instanceof BrimwoodFamily castedInput)
                return (new Dependencies()).append(BRANCH, castedInput.getMagmaBranch()).append(PRIMITIVE_LOG, castedInput.getPrimitiveMagmaLog());
            return super.gatherDependencies(input);
        }
    }

}
