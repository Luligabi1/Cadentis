package me.luligabi.cadentis;

import me.luligabi.cadentis.event.KeyBindingEventListener;
import me.luligabi.cadentis.registry.KeyBindingRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class CadentisClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBindingRegistry.init();
        KeyBindingEventListener.init();
    }
}