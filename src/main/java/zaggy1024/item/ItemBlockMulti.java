package zaggy1024.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.variant.IMetadata;

import java.util.List;

/**
 * Generic class to use for combo blocks, and can be extended if a block doesn't need a specific subclass to ItemBlock.
 *
 * @author Zaggy1024
 */
public class ItemBlockMulti<V extends IMetadata<V>> extends ItemBlock {
    public final VariantsOfTypesCombo<V> owner;
    public final ObjectType<V, ? extends Block, ? extends ItemBlockMulti<V>> type;

    protected final List<V> variants;

    public ItemBlockMulti(Block block, VariantsOfTypesCombo<V> owner,
                          ObjectType<V, ? extends Block, ? extends ItemBlockMulti<V>> type,
                          List<V> variants, Class<V> variantClass) {
        super(block);

        this.owner = owner;
        this.type = type;

        this.variants = variants;

        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        super.getSubItems(tab, subItems);
    }
	
	/*@Override
	public int getColorFromItemStack(ItemStack stack, int renderPass)
	{
		return getBlock().getRenderColor(owner.getBlockState(type, owner.getVariant(stack)));
	}*/

    @Override
    public String getTranslationKey(ItemStack stack) {
        return owner.getTranslationKey(stack, super.getTranslationKey(stack));
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }
}
