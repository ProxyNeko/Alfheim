package zaggy1024.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import zaggy1024.combo.ObjectType;
import zaggy1024.combo.VariantsOfTypesCombo;
import zaggy1024.combo.VariantsOfTypesCombo.BlockProperties;
import zaggy1024.combo.variant.IMetadata;
import zaggy1024.combo.variant.PropertyIMetadata;
import zaggy1024.util.BlockStateToMetadata;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Generic class to use for combo blocks, and can be extended if an block doesn't need a specific subclass to Block.
 *
 * @author Zaggy1024
 */
public class BlockMulti<V extends IMetadata<V>> extends Block {
    /**
     * Used in {@link VariantsOfTypesCombo}.
     */
    @BlockProperties
    public static IProperty<?>[] getProperties() {
        return new IProperty[]{};
    }

    public final VariantsOfTypesCombo<V> owner;
    public final ObjectType<V, ? extends BlockMulti<V>, ? extends Item> type;

    public final List<V> variants;
    public final PropertyIMetadata<V> variantProp;

    protected final HashSet<V> noItemVariants = new HashSet<>();

    public BlockMulti(VariantsOfTypesCombo<V> owner,
                      ObjectType<V, ? extends BlockMulti<V>, ? extends Item> type,
                      List<V> variants, Class<V> variantClass,
                      Material material, MapColor mapColor, SoundType sound) {
        super(material, mapColor);

        this.owner = owner;
        this.type = type;

        this.variants = variants;
        variantProp = new PropertyIMetadata<>("variant", variants, variantClass);

        blockState = new BlockStateContainer(this, variantProp);
        setDefaultState(getBlockState().getBaseState());

        setSoundType(sound);
    }

    public BlockMulti(VariantsOfTypesCombo<V> owner,
                      ObjectType<V, ? extends BlockMulti<V>, ? extends Item> type,
                      List<V> variants, Class<V> variantClass,
                      Material material, SoundType sound) {
        this(owner, type, variants, variantClass, material, material.getMaterialMapColor(), sound);
    }

    @Override
    public BlockMulti<V> setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return BlockStateToMetadata.getMetaForBlockState(state, variantProp);
    }

    @Override
    public IBlockState getStateFromMeta(int metadata) {
        return BlockStateToMetadata.getBlockStateFromMeta(getDefaultState(), metadata, variantProp);
    }

    @Override
    public BlockMulti<V> setTranslationKey(String name) {
        super.setTranslationKey(name);
        return this;
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        owner.fillSubItems(type, variants, list);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        return Collections.singletonList(owner.getStack(type, state.getValue(variantProp)));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return owner.getItemMetadata(type, state.getValue(variantProp));
    }
}
