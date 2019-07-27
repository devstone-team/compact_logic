package devstone_team.compact_logic;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// Note this value should match the META-INF/mods.toml
@Mod(CompactLogicMod.MODID)
public class CompactLogicMod {
	public static final String MODID = "compact_logic";
	private static final Logger sLogger = LogManager.getLogger();

	// ==============================================
	public CompactLogicMod() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		// Register the doClientStuff method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	// ==============================================
	private void setup(final FMLCommonSetupEvent event) {
		// some preinit code
		sLogger.info("COMPACT LOGIC SETUP - BEGIN =======================");

		// register blocks
		// sLogger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

		sLogger.info("COMPACT LOGIC SETUP - END =======================");
	}

	// ==============================================
	private void doClientStuff(final FMLClientSetupEvent event) {
		// client side only code
		sLogger.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
	}

	// ==============================================
	private void enqueueIMC(final InterModEnqueueEvent event) {
		// some example code to dispatch IMC to another mod
		InterModComms.sendTo("examplemod", "helloworld", () -> {
			sLogger.info("Hello world from the MDK");
			return "Hello world";
		});
	}

	// ==============================================
	private void processIMC(final InterModProcessEvent event) {
		// some example code to receive and process InterModComms from other mods
		sLogger.info("Got IMC {}",
				event.getIMCStream().map(m -> m.getMessageSupplier().get()).collect(Collectors.toList()));
	}

	// ==============================================
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// do something when the server starts
		sLogger.info("COMPACT LOGIC SERVER SETUP - BEGIN =======================");
		sLogger.info("COMPACT LOGIC SERVER SETUP - END =======================");
	}

	// ==============================================
	// You can use EventBusSubscriber to automatically subscribe events on the
	// contained class (this is subscribing to the MOD
	// Event bus for receiving Registry Events)
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {

		@SubscribeEvent
		public static void registerBlock(RegistryEvent.Register<Block> event) {
			event.getRegistry().register(new ScriptedLogicBlock());
		}

		@SubscribeEvent
		public static void registerItem(RegistryEvent.Register<Item> event) {
			event.getRegistry().register(new ItemBlock(CompactLogic.scripted_logic_block, new Item.Properties())
					.setRegistryName(CompactLogicMod.MODID, "scripted_logic_itemblock"));
		}

	}
}
