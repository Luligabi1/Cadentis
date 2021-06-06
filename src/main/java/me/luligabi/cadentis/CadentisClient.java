package me.luligabi.cadentis;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class CadentisClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        lessBrightnessKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.cadentis.less_brightness",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT,
                "category.cadentis.keys"
        ));

        moreBrightnessKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.cadentis.more_brightness",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT,
                "category.cadentis.keys"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (lessBrightnessKeyBinding.wasPressed()) {
                if(client.options.gamma > -150) {
                    client.options.gamma -= 10;
                    client.player.sendMessage(new LiteralText(client.options.gamma + ""), false);
                }
            }
            while (moreBrightnessKeyBinding.wasPressed()) {
                if(client.options.gamma < 150) {
                    client.options.gamma += 10;
                    client.player.sendMessage(new LiteralText(client.options.gamma + ""), false);
                }
            }
        });
    }

    private static KeyBinding lessBrightnessKeyBinding;
    private static KeyBinding moreBrightnessKeyBinding;
}