package me.jacky1356400.luckybeans.proxy;

import me.jacky1356400.luckybeans.LuckyBeans;
import me.jacky1356400.luckybeans.init.ModRegistry;
import me.jacky1356400.luckybeans.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onModelRegistry(ModelRegistryEvent e) {
		for (Item item : LuckyBeans.ITEMS)
			if (item instanceof IHasModel)
				((IHasModel) item).initModel(e);
		for (Block block : LuckyBeans.BLOCKS)
			if (block instanceof IHasModel)
				((IHasModel) block).initModel(e);
        ModelLoader.setCustomStateMapper(ModRegistry.BEANLEAVES, (new StateMap.Builder())
                .ignore(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE).build());
		((BlockLeaves) ModRegistry.BEANLEAVES).setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

}
