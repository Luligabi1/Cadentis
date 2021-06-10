package me.luligabi.cadentis.mixin;

import me.luligabi.cadentis.CadentisClient;
import me.luligabi.cadentis.event.KeyBindingEventListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.text.DecimalFormat;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    boolean resetGammaAfterToggleOff = true;

    @Inject(method = "render",
            at = @At("RETURN"),
            cancellable = true)
    public void cadentis_render(MatrixStack matrixStack, float tickDelta, CallbackInfo callbackInfo) {
        MinecraftClient client = MinecraftClient.getInstance();

        if(KeyBindingEventListener.enabled) {
            client.options.gamma = KeyBindingEventListener.gammaState;
            DecimalFormat format = new DecimalFormat("0.#");
            int gamma = (int) client.options.gamma;
            int xOffset;
            switch(gamma) { //TODO: Make xOffset configurable
                case 25: case 75:
                    xOffset = 102;
                    break;
                case 150:
                    xOffset = 108;
                    break;
                case -25: case -75:
                    xOffset = 109;
                    break;
                case -150:
                    xOffset = 115;
                    break;
                default:
                    xOffset = 91;
                    break;
            }
            resetGammaAfterToggleOff = true;
            client.textRenderer.draw(matrixStack,
                    new TranslatableText("message.cadentis.brightness", format.format(gamma*10L)).append(new LiteralText("%")).formatted(getBrightnessColor(gamma), Formatting.BOLD),
                    client.getWindow().getScaledWidth()-xOffset, 2, 0x0); //TODO: Make yOffset configurable
        } else {
            if(resetGammaAfterToggleOff) {
                client.options.gamma = 1;
                System.out.println(client.options.gamma);
                resetGammaAfterToggleOff = false;
            }
        }
        callbackInfo.cancel();
    }

    private Formatting getBrightnessColor(int brightness) {
        switch(brightness) {
            case 25:
                return Formatting.GREEN;
            case 75:
                return Formatting.YELLOW;
            case 150:
                return Formatting.GOLD;
            case -25:
                return Formatting.GRAY;
            case -75:
                return Formatting.DARK_GRAY;
            case -150:
                return Formatting.BLACK;
            default:
                return Formatting.WHITE;
        }
    }
}
