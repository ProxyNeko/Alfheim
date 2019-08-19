package alfheimrsmoons.network;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import zaggy1024.proxy.IProxy;

import java.util.ArrayList;
import java.util.List;

public abstract class Proxy implements IProxy {
    protected IForgeRegistry<Item> itemRegistry;
    protected IForgeRegistry<Block> blockRegistry;

    public void preInit() {
    }

    public List<Item> itemList = new ArrayList<>();

    public void initItemRegistry(IForgeRegistry<Item> registry) {
        itemRegistry = registry;
    }

    public void initBlockRegistry(IForgeRegistry<Block> registry) {
        blockRegistry = registry;
    }

    public void init() {
    }

    public void postInit() {
    }

    @Override
    public void registerItem(Item item, ResourceLocation name, boolean doModel) {
        registerItem(item.setRegistryName(name), doModel);
    }

    public void registerItem(Item item, boolean doModel) {
        itemRegistry.register(item);
    }

    public void registerItem(Item item) {
        registerItem(item, true);
    }

    @Override
    public void registerBlock(Block block, Item item, ResourceLocation name, boolean doModel) {
        blockRegistry.register(block.setRegistryName(name));
        itemList.add(item.setRegistryName(name));
    }

    public void registerBlock(Block block) {
        blockRegistry.register(block);

        ItemBlock itemBlock = new ItemBlock(block);
        itemList.add(itemBlock.setRegistryName(block.getRegistryName()));
    }

    public Fluid registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        return fluid;
    }

    public void registerFluidBlock(Fluid fluid, Block fluidBlock, String name) {
        ForgeRegistries.BLOCKS.register(fluidBlock);
        fluid.setBlock(fluidBlock);
        registerFluidRendering(fluidBlock, name);
    }

    public void registerFluidRendering(Block block, String name) {
    }


}
