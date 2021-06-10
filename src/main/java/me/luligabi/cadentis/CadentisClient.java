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
    public static double gammaState = 0;

    @Override
    public void onInitializeClient() {
        KeyBindingRegistry.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(KeyBindingRegistry.enableKeyBinding.wasPressed()) {
                enabled = !enabled;
                client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1f));
            }
            while(KeyBindingRegistry.lessBrightnessKeyBinding.wasPressed()) {
                if(enabled) {
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
                    client.player.sendMessage(new LiteralText(client.options.gamma + ""), false);
                }
            }

            while(KeyBindingRegistry.moreBrightnessKeyBinding.wasPressed()) {
                if(enabled) {
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
                    client.player.sendMessage(new LiteralText(client.options.gamma + ""), false);
                }
            }
        });
    }
}