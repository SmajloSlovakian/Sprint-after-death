package io.github.smajloslovakian.sprintafterdeath.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyMapping.class)
public abstract class ResetToggleMixin implements Comparable<KeyMapping> {

    private static boolean sprintToggledOnBeforeDeath = false;
    private static Minecraft mc=Minecraft.getInstance();
    
    @Inject(at=@At("HEAD"),method="resetToggleKeys")
    private static void saveIfSprinting(CallbackInfo ci) {
        sprintToggledOnBeforeDeath = (mc.options.keySprint.isDown() && mc.options.toggleSprint().get());
    }
;
    @Inject(at=@At("TAIL"),method="resetToggleKeys")
    private static void loadIfSprinting(CallbackInfo ci) {
        mc.options.keySprint.setDown(sprintToggledOnBeforeDeath);
    }
}
