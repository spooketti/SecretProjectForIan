package spooketti.ianproject;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ianproject implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("ianproject");

	@Override
	public void onInitialize() 
	{
		registerItems();
	}	

	public static final SpawnZombie SPAWNZOMBIE = new SpawnZombie(new FabricItemSettings().rarity(Rarity.EPIC));
	public static void registerCreative(RegistryKey<ItemGroup> igroup, Item after, Item item)
	{
	ItemGroupEvents.modifyEntriesEvent(igroup).register(content -> {
			content.addAfter(after, item);
		});
	}

	
     public static void registerItems()
    {
        Registry.register(Registries.ITEM, new Identifier("spooketti","spawnzombie"), SPAWNZOMBIE);
		registerCreative(ItemGroups.TOOLS,Items.DIAMOND_PICKAXE,SPAWNZOMBIE);
    }
}