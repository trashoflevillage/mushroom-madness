package io.github.trashoflevillage.mushroommadness.sounds;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static RegistryKey<JukeboxSong> of(String id) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(MushroomMadness.MOD_ID, id));
    }

    public static void registerSounds() {
        MushroomMadness.LOGGER.info(("Registering sounds for " + MushroomMadness.MOD_ID));
        ModSounds.registerReference("lentinula_edodes");
    }

    private static void registerReference(String name) {
        Identifier ID = Identifier.of(MushroomMadness.MOD_ID, name);
        Registry.registerReference(Registries.SOUND_EVENT, ID, SoundEvent.of(ID));
    }

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(MushroomMadness.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
