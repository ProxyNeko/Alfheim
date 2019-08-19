package alfheimrsmoons;

import alfheimrsmoons.init.*;
import alfheimrsmoons.network.Proxy;
import alfheimrsmoons.world.gen.AMWorldGenerator;
import alfheimrsmoons.world.structure.StructureSacredSpring;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod
        (
                modid = AlfheimrsMoons.MOD_ID,
                name = AlfheimrsMoons.MOD_NAME,
                version = AlfheimrsMoons.MOD_VERSION,
                // certificateFingerprint = AlfheimrsMoons.FINGERPRINT,
                useMetadata = true
        )
public class AlfheimrsMoons {
    public static final String MOD_ID = "alfheimrsmoons";
    public static final String MOD_NAME = "Alfheimrs Moons";
    public static final String MOD_VERSION = "@VERSION@";
    // public static final String FINGERPRINT = "@fingerprint@";
    public static final Logger logger = LogManager.getLogger(MOD_NAME);

    public static final String UNLOCALIZED_PREFIX = MOD_ID + ".";

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(AMBlocks.YGGDRASIL_LEAVES);
        }
    };

    @Instance(MOD_ID)
    public static AlfheimrsMoons instance = new AlfheimrsMoons();
    @SidedProxy(clientSide = "alfheimrsmoons.client.ProxyClient", serverSide = "alfheimrsmoons.network.ProxyServer")
    public static Proxy proxy;

    @EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
        FMLLog.bigWarning("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the developers!");
    }

    public AlfheimrsMoons() {
        FluidRegistry.enableUniversalBucket();
    }

    //TODO: Move
    public IForgeRegistry shapelessRegistry;
    public IForgeRegistry shapedRegistry;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        shapelessRegistry = GameRegistry.findRegistry(IRecipe.class);
        shapedRegistry = GameRegistry.findRegistry(IRecipe.class);
        // AMBlocks.registerBlocks();
        AMBlocks.registerFluids();
        // AMItems.registerItems();
        // AMRecipes.addRecipes();
        AMEntities.registerEntities();
        AMBiomes.registerBiomes();
        AMDimensions.registerDimensions();
        GameRegistry.registerWorldGenerator(new AMWorldGenerator(), 0);
        GameRegistry.registerWorldGenerator(new StructureSacredSpring(), 100);
        MinecraftForge.EVENT_BUS.register(new AMEventHandler());
        proxy.preInit();
        AMBlocks.registerOres();
        AMItems.registerOres();
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        AMBlocks.registerBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        AMItems.registerItems(event.getRegistry());
    }

    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        AMRecipes.addRecipes();
    }


    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
