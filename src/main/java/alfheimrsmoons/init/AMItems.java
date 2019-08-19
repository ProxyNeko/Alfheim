package alfheimrsmoons.init;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.combo.ComboBioluminescence;
import alfheimrsmoons.combo.ComboOres;
import alfheimrsmoons.combo.ComboTools;
import alfheimrsmoons.item.ItemArrowAM;
import alfheimrsmoons.item.ItemBranchBow;
import alfheimrsmoons.item.ItemSeedPouch;
import alfheimrsmoons.network.Proxy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

public class AMItems {
    public static final Item MANNA_CRYSTAL = new Item()
            .setCreativeTab(AlfheimrsMoons.CREATIVE_TAB)
            .setTranslationKey(AlfheimrsMoons.UNLOCALIZED_PREFIX + "manna_crystal")
            .setRegistryName("manna_crystal");

    public static final Item BRANCH = new Item()
            .setCreativeTab(AlfheimrsMoons.CREATIVE_TAB)
            .setTranslationKey(AlfheimrsMoons.UNLOCALIZED_PREFIX + "branch")
            .setRegistryName("branch")
            .setFull3D();

    public static final Item TWINE = new Item()
            .setCreativeTab(AlfheimrsMoons.CREATIVE_TAB)
            .setTranslationKey(AlfheimrsMoons.UNLOCALIZED_PREFIX + "twine")
            .setRegistryName("twine");

    public static final Item STARDUST = new Item()
            .setCreativeTab(AlfheimrsMoons.CREATIVE_TAB)
            .setTranslationKey(AlfheimrsMoons.UNLOCALIZED_PREFIX + "stardust")
            .setRegistryName("stardust");

    public static final ItemSeedPouch SEED_POUCH = new ItemSeedPouch();

    public static final ComboTools TOOLS = new ComboTools();

    public static final ItemBranchBow BRANCH_BOW = new ItemBranchBow();

    public static final Item ROCK_ARROW = new ItemArrowAM()
            .setTranslationKey(AlfheimrsMoons.UNLOCALIZED_PREFIX + "rock_arrow")
            .setRegistryName("rock_arrow");

    public static final Item KNOWLEDGE_FRUIT = new ItemFood(4, 0.3F, false)
            .setCreativeTab(AlfheimrsMoons.CREATIVE_TAB)
            .setTranslationKey(AlfheimrsMoons.UNLOCALIZED_PREFIX + "knowledge_fruit")
            .setRegistryName("knowledge_fruit");

    public static void registerItems(IForgeRegistry<Item> registry) {
        Proxy proxy = AlfheimrsMoons.proxy;
        proxy.initItemRegistry(registry);
        proxy.registerItem(MANNA_CRYSTAL);
        proxy.registerItem(BRANCH);
        proxy.registerItem(TWINE);
        AMBlocks.ORES.registerVariants(proxy, ComboOres.DROP);
        AMBlocks.ORES.registerVariants(proxy, ComboOres.INGOT);
        AMBlocks.BIOLUMINESCENCE.registerVariants(proxy, ComboBioluminescence.BIOLUMINESCENCE);
        proxy.registerItem(STARDUST);
        proxy.registerItem(SEED_POUCH);
        TOOLS.registerAll(proxy);
        proxy.registerItem(BRANCH_BOW);
        proxy.registerItem(ROCK_ARROW);
        proxy.registerItem(KNOWLEDGE_FRUIT);
        for (Item i : proxy.itemList) {
            proxy.registerItem(i);
        }

    }

    public static void registerOres() {
        OreDictionary.registerOre("stickWood", BRANCH);
    }
}
