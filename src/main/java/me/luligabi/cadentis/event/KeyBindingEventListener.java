package me.luligabi.cadentis.event;

import me.luligabi.cadentis.registry.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;

public class KeyBindingEventListener {

    public static boolean enabled = false;
    public static double gammaState = 0;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(KeyBindingRegistry.enableKeyBinding.wasPressed()) {
                enabled = !enabled;
                client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1f));
            }
            while(KeyBindingRegistry.lessBrightnessKeyBinding.wasPressed()) {
                if(!enabled) return;

                switch((int) client.options.gamma) {
                    case 0: case 1:
                        gammaState = -25;
                        break;
                    case -25:
                        gammaState = -75;
                        break;
                    case -75:
                        gammaState = -150;
                        break;
                    case 25:
                        gammaState = 0;
                        break;
                    case 75:
                        gammaState = 25;
                        break;
                    case 150:
                        gammaState = 75;
                        break;
                }
                client.options.gamma = gammaState;
                client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1f));
            }

            while(KeyBindingRegistry.moreBrightnessKeyBinding.wasPressed()) {
                if(!enabled) return;

                switch((int) client.options.gamma) {
                    case 0: case 1:
                        gammaState = 25;
                        break;
                   case 25:
                        gammaState = 75;
                        break;
                   case 75:
                        gammaState = 150;
                        break;
                   case -150:
                        gammaState = -75;
                        break;
                   case -75:
                        gammaState = -25;
                        break;
                   case -25:
                        gammaState = 0;
                        break;
                }
                client.options.gamma = gammaState;
                client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1f));
            }

            while(KeyBindingRegistry.minBrightnessKeyBinding.wasPressed()) {
                if(!enabled) return;

                gammaState = -150;
                client.options.gamma = gammaState;
                client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1f));
            }

            while(KeyBindingRegistry.maxBrightnessKeyBinding.wasPressed()) {
                if(!enabled) return;

                gammaState = 150;
                client.options.gamma = gammaState;
                client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1f));
            }
        });
    }
}