package alfheimrsmoons.init;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = AlfheimrsMoons.MOD_ID, category = "")
@Mod.EventBusSubscriber(modid = AlfheimrsMoons.MOD_ID)
public class ConfigHandler {
    private static final String LANG = AlfheimrsMoons.MOD_ID + ".config.";

    @Config.LangKey(LANG + "dimsConfig")
    @Config.Comment({"Dimension configs."})
    public static ConfigDims dimsConfig = new ConfigDims();

    public static class ConfigDims {
        @Config.RequiresMcRestart
        @Config.LangKey(LANG + "dimensionID")
        @Config.RangeInt(min = 2, max = 1023)
        @Config.Comment("Chose the dimension id of Alfheimr.")
        public int alfheimrDimID = 304;
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(AlfheimrsMoons.MOD_ID))
            ConfigManager.sync(AlfheimrsMoons.MOD_ID, Config.Type.INSTANCE);
    }
}