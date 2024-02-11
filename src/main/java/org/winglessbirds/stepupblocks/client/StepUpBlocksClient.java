package org.winglessbirds.stepupblocks.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.winglessbirds.stepupblocks.event.ClientEndTickHandler;

public class StepUpBlocksClient implements ClientModInitializer {

    public static KeyBinding keyStepup;

    @Override
    public void onInitializeClient() {
        keyStepup = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.stepupblocks.stepup", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "category.stepupblocks.main"));
        ClientTickEvents.END_CLIENT_TICK.register(new ClientEndTickHandler());
    }
}
