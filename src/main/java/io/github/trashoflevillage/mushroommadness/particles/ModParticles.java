package io.github.trashoflevillage.mushroommadness.particles;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final SimpleParticleType GLOWCAP_SPORE = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(
                Registries.PARTICLE_TYPE,
                Identifier.of(MushroomMadness.MOD_ID, "glowcap_spore"),
                GLOWCAP_SPORE
        );
    }
}
