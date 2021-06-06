package me.luligabi.cadentis.registry;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindingRegistry {

    public static void init() {
        enableKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.cadentis.enable",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "category.cadentis.keys"
        ));

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
    }
    public static KeyBinding enableKeyBinding;
    public static KeyBinding lessBrightnessKeyBinding;
    public static KeyBinding moreBrightnessKeyBinding;
}
