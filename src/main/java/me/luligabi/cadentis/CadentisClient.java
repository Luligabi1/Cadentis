package me.luligabi.cadentis;

import me.luligabi.cadentis.registry.KeyBindingRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;

@Environment(EnvType.CLIENT)
public class CadentisClient implements ClientModInitializer {

    public static boolean enabled = false;

    @Override
    public void onInitializeClient() {
        KeyBindingRegistry.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(KeyBindingRegistry.enableKeyBinding.wasPressed()) {
                enabled = !enabled;
                client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1f));
            }

            while(KeyBindingRegistry.lessBrightnessKeyBinding.wasPressed()) {
                if(enabled && client.options.gamma > -150) {
                    client.options.gamma -= 10;
                    client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1f));
                    client.player.sendMessage(new LiteralText(client.options.gamma + ""), false);
                }
            }

            while(KeyBindingRegistry.moreBrightnessKeyBinding.wasPressed()) {
                if(enabled && client.options.gamma < 150) {
                    client.options.gamma += 10;
                    client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1f));
                    client.player.sendMessage(new LiteralText(client.options.gamma + ""), false);
                }
            }
        });
    }
}