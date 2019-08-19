package alfheimrsmoons.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Instances of AMShapedOreRecipe are sorted before {@link ShapedOreRecipe}.
 */
public class ShapedOreRecipeAM extends ShapedOreRecipe {
    public ShapedOreRecipeAM(Block result, Object... recipe) {
        super(new ResourceLocation(""), serializeItem(result), recipe);
        setRegistryName(result.getRegistryName());
    }

    public ShapedOreRecipeAM(Item result, Object... recipe) {
        super(new ResourceLocation(""), serializeItem(result), recipe);
        setRegistryName(result.getRegistryName());
    }

    public ShapedOreRecipeAM(ItemStack result, Object... recipe) {
        super(new ResourceLocation(""), serializeItem(result), recipe);
        setRegistryName(result.getItem().getRegistryName());
    }

    private static ItemStack serializeItem(Object thing) {
        if (thing instanceof Item) {
            return new ItemStack((Item) thing);
        }
        if (thing instanceof Block) {
            return new ItemStack((Block) thing);
        }
        if (thing instanceof ItemStack) {
            return ((ItemStack) thing);
        }
        throw new IllegalArgumentException("Not a block, item, or stack");
    }
}
