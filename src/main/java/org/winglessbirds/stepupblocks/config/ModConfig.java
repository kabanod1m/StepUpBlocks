package org.winglessbirds.stepupblocks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = org.winglessbirds.stepupblocks.StepUpBlocks.MODID)
@Config.Gui.Background("minecraft:textures/block/dirt.png")
public class ModConfig implements ConfigData {

    @Comment("Raises player's step height to 1 block")
    public boolean modEnabled = true;

    @Comment("Walk up blocks only when a key is held. Bind the key in controls (default: V)")
    public boolean whenHeld = false;

    @Comment("Allows walking up full blocks when sneaking as well")
    public boolean whenSneaking = true;

    @Comment("Allows walking up full blocks when swimming as well")
    public boolean whenSwimming = true;

    @Comment("Desired increased step height") // test if limits need to be checked additionally
    public float stepHeight = 1.25f;
}
