package org.winglessbirds.stepupblocks.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.winglessbirds.stepupblocks.StepUpBlocks;
import org.winglessbirds.stepupblocks.client.StepUpBlocksClient;

public class ClientEndTickHandler implements ClientTickEvents.EndTick {

    private float savedValue;
    private boolean savedValueInitialized = false;
    private boolean modJustDisabled = false;

    @Override
    public void onEndTick(MinecraftClient client) {
        ClientPlayerEntity player = client.player;
        if (player == null) {
            savedValueInitialized = false;
            //StepUpBlocks.LOG.debug("Player is null right now");
            return;
        }

        if (!savedValueInitialized) {            // fires only once upon joining a world (when the player finally becomes not a null)
            savedValue = player.getStepHeight(); // and saves the default step height for compatibility, maybe it was modified
            savedValueInitialized = true;
        }

        if (!StepUpBlocks.CFG.modEnabled) {
            if (!modJustDisabled) {
                player.setStepHeight(savedValue);
                modJustDisabled = true;
                StepUpBlocks.LOG.debug("Returned value " + savedValue + " as player step height because the mod was disabled just now");
            }
            return;
        }

        modJustDisabled = false;

        if     ((!StepUpBlocks.CFG.whenHeld || StepUpBlocksClient.keyStepup.isPressed()) &&
                (StepUpBlocks.CFG.whenSneaking || !player.isSneaking()) &&
                (StepUpBlocks.CFG.whenSwimming || !player.isInSwimmingPose()) &&
                player.getStepHeight() < StepUpBlocks.CFG.stepHeight) {
            player.setStepHeight(StepUpBlocks.CFG.stepHeight);
            return;
        }

        player.setStepHeight(savedValue);
    }
}
