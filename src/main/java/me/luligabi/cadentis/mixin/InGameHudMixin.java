package me.luligabi.cadentis.mixin;

import me.luligabi.cadentis.CadentisClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render",
            at = @At("RETURN"),
            cancellable = true)
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo callbackInfo) {
        if(CadentisClient.enabled) {
            MinecraftClient.getInstance().textRenderer.draw(matrixStack, new LiteralText("Gamma: " + MinecraftClient.getInstance().options.gamma), MinecraftClient.getInstance().getWindow().getScaledWidth()-70, 0, 0x0);
        }
        callbackInfo.cancel();
    }
}
