package net.dviousdingle.dtruport.init;


import com.dtteam.dynamictrees.deserialization.PropertyAppliers;
import com.dtteam.dynamictrees.event.ApplierRegistryEvent;
import com.dtteam.dynamictrees.tree.family.Family;
import com.google.gson.JsonElement;
import net.dviousdingle.dtruport.DtruPort;
import net.dviousdingle.dtruport.tree.BrimwoodFamily;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = DtruPort.MOD_ID)
public final class RegisterJSONAppliers {

    @SubscribeEvent
    public static void registerAppliersFamily(final ApplierRegistryEvent.Reload<Family, JsonElement> event) {
        registerFamilyAppliers(event.getAppliers());
    }

    public static void registerFamilyAppliers(PropertyAppliers<Family, JsonElement> appliers) {
        appliers.register("primitive_magma_log", BrimwoodFamily.class, Block.class,
                BrimwoodFamily::setPrimitiveMagmaLog);
    }

    @SubscribeEvent public static void registerAppliersFamily(final ApplierRegistryEvent.GatherData<Family, JsonElement> event) { registerFamilyAppliers(event.getAppliers()); }

}