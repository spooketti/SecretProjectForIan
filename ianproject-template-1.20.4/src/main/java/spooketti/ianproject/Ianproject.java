package spooketti.ianproject;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import spooketti.ianproject.block.JCho;
import spooketti.ianproject.item.SpawnZombie;

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
		ServerMessageEvents.CHAT_MESSAGE.register((message,player,messageType) -> {
			LampBinary.handleChat(message,player,messageType);
		});

		
		
	}	

	public static final JCho JCHO = new JCho(FabricBlockSettings.create().strength(4.0f));
	public static final BlockItem JCHO_ITEM = new BlockItem(JCHO,new FabricItemSettings());

	public static final SpawnZombie SPAWNZOMBIE = new SpawnZombie(new FabricItemSettings().rarity(Rarity.EPIC));
	public static final Item BREADCRUMB = new Item(new FabricItemSettings());
	public static final FoodComponent RAW_NUGGET_COMPONENT = new FoodComponent.Builder().hunger(1).
	saturationModifier(.25f).statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE),1).meat().build();
	public static final FoodComponent MC_NUGGET_COMPONENT = new FoodComponent.Builder().hunger(5).
	saturationModifier(.5f).statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION),1000).build();

	public static final Item RAW_NUGGET = new Item(new FabricItemSettings().food(MC_NUGGET_COMPONENT)); 
	public static final Item MC_NUGGET = new Item(new FabricItemSettings().food(MC_NUGGET_COMPONENT));



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
		Registry.register(Registries.ITEM, new Identifier("spooketti","breadcrumb"), BREADCRUMB);
		registerCreative(ItemGroups.FOOD_AND_DRINK,Items.BREAD,BREADCRUMB);
		Registry.register(Registries.ITEM, new Identifier("spooketti","raw_nugget"), RAW_NUGGET);
		registerCreative(ItemGroups.FOOD_AND_DRINK,Items.CHICKEN,RAW_NUGGET);
		Registry.register(Registries.ITEM, new Identifier("spooketti","mcnugget"), MC_NUGGET);
		registerCreative(ItemGroups.FOOD_AND_DRINK,Items.CHICKEN,MC_NUGGET);
		Registry.register(Registries.BLOCK, new Identifier("spooketti", "jcho"), JCHO);
		Registry.register(Registries.ITEM, new Identifier("spooketti", "jcho"), JCHO_ITEM);
		registerCreative(ItemGroups.COLORED_BLOCKS, Items.YELLOW_CONCRETE,JCHO_ITEM);
    
    }
}