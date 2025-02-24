package com.missingpetnotifier;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PetHandler
{
	private static ImmutableMap<String, String> itemToNpcMap;
	private static ImmutableSet<String> baseItems;
	private static ImmutableSet<String> baseNpcs;
	private static ImmutableMap<String, String> itemVariantsToBase;
	private static ImmutableMap<String, String> npcVariantsToBase;

	private static String dedupeNpc(String npcName)
	{
		if (baseNpcs.contains(npcName))
		{
			return npcName;
		}
		if (npcVariantsToBase.containsKey(npcName))
		{
			return npcVariantsToBase.get(npcName);
		}

		log.debug("Unrecognized pet npc name: {}", npcName);
		return npcName;
	}

	private static String dedupeItem(String itemName)
	{
		if (baseItems.contains(itemName))
		{
			return itemName;
		}
		if (itemVariantsToBase.containsKey(itemName))
		{
			return itemVariantsToBase.get(itemName);
		}
		log.debug("Unrecognized pet item name: {}", itemName);

		return itemName;
	}

	public static boolean itemMatchesPet(String itemName, String followerName)
	{
		final String dedupedNpcName = dedupeNpc(followerName);
		final String dedupedItemName = dedupeItem(itemName);
		return dedupedItemName.equalsIgnoreCase(dedupedNpcName) ||
			(itemToNpcMap.containsKey(dedupedItemName) && itemToNpcMap.get(dedupedItemName).equals(dedupedNpcName));
	}

	public static void init()
	{
		itemToNpcMap = ImmutableMap.<String, String>builder()
			.put("Pet snakeling", "Snakeling")
			.put("Pet chaos elemental", "Chaos Elemental Jr.")
			.put("Pet dagannoth prime", "Dagannoth Prime Jr.")
			.put("Pet dagannoth rex", "Dagannoth Rex Jr.")
			.put("Pet dagannoth supreme", "Dagannoth Supreme Jr.")
			.put("Pet dark core", "Dark core")
			.put("Pet general graardor", "General Graardor Jr.")
			.put("Pet k'ril tsutsaroth", "K'ril Tsutsaroth Jr.")
			.put("Pet kraken", "Kraken")
			.put("Pet kree'arra", "Kree'arra Jr.")
			.put("Pet smoke devil", "Smoke Devil")
			.put("Pet zilyana", "Zilyana Jr.")
			.put("Pet penance queen", "Penance Pet")
			.build();

		itemVariantsToBase = ImmutableMap.<String, String>builder()
			.put("Baby mole-rat", "Baby mole")
			.put("Little parasite", "Little nightmare")
			.put("Tzrek-zuk", "Jal-nib-rek")
			.put("Pet corporeal critter", "Pet dark core")
			.put("Lil' maiden", "Lil' zik")
			.put("Lil' bloat", "Lil' zik")
			.put("Lil' nylo", "Lil' zik")
			.put("Lil' sot", "Lil' zik")
			.put("Lil' xarp", "Lil' zik")
			.put("Rax", "Nid")
			.put("Midnight", "Noon")
			.put("Puppadile", "Olmlet")
			.put("Tektiny", "Olmlet")
			.put("Enraged tektiny", "Olmlet")
			.put("Vanguard", "Olmlet")
			.put("Vasa minirio", "Olmlet")
			.put("Vespina", "Olmlet")
			.put("Flying vespina", "Olmlet")
			.put("Tumeken's damaged guardian", "Tumeken's guardian")
			.put("Elidinis' guardian", "Tumeken's guardian")
			.put("Elidinis' damaged guardian", "Tumeken's guardian")
			.put("Akkhito", "Tumeken's guardian")
			.put("Babi", "Tumeken's guardian")
			.put("Kephriti", "Tumeken's guardian")
			.put("Zebo", "Tumeken's guardian")
			.put("Jalrek-jad", "Tzrek-jad")
			.put("Corrupted youngllef", "Youngllef")
			.put("Dark squirrel", "Giant squirrel")
			.put("Bone squirrel", "Giant squirrel")
			.put("Great blue heron", "Heron")
			.put("Greatish guardian", "Rift guardian")
			.put("Ziggy", "Rocky")
			.put("Red", "Rocky")
			.put("Lil' destructor", "Lil' creator")
			.put("Fox", "Beaver")
			.put("Pheasant", "Beaver")
			.put("Ric", "Bran")
			.build();

		npcVariantsToBase = ImmutableMap.<String, String>builder()
			.put("Baby Mole-rat", "Baby Mole")
			.put("TzRek-Zuk", "Jal-Nib-Rek")
			.put("Little Parasite", "Little Nightmare")
			.put("Corporeal Critter", "Dark core")
			.put("Lil' Maiden", "Lil' Zik")
			.put("Lil' Bloat", "Lil' Zik")
			.put("Lil' Nylo", "Lil' Zik")
			.put("Lil' Sot", "Lil' Zik")
			.put("Lil' Xarp", "Lil' Zik")
			.put("Rax", "Nid")
			.put("Midnight", "Noon")
			.put("Puppadile", "Olmlet")
			.put("Tektiny", "Olmlet")
			.put("Enraged Tektiny", "Olmlet")
			.put("Vanguard", "Olmlet")
			.put("Vasa Minirio", "Olmlet")
			.put("Vespina", "Olmlet")
			.put("Flying Vespina", "Olmlet")
			.put("Tumeken's Damaged Guardian", "Tumeken's Guardian")
			.put("Elidinis' Guardian", "Tumeken's Guardian")
			.put("Elidinis' Damaged Guardian", "Tumeken's Guardian")
			.put("Akkhito", "Tumeken's Guardian")
			.put("Babi", "Tumeken's Guardian")
			.put("Kephriti", "Tumeken's Guardian")
			.put("Zebo", "Tumeken's Guardian")
			.put("JalRek-Jad", "TzRek-Jad")
			.put("Corrupted Youngllef", "Youngllef")
			.put("Dark Squirrel", "Giant Squirrel")
			.put("Bone Squirrel", "Giant Squirrel")
			.put("Great blue heron", "Heron")
			.put("Greatish guardian", "Rift guardian")
			.put("Ziggy", "Rocky")
			.put("Red", "Rocky")
			.put("Lil' Destructor", "Lil' Creator")
			.put("Fox", "Beaver")
			.put("Pheasant", "Beaver")
			.put("Ric", "Bran")
			.build();

		baseItems = itemToNpcMap.keySet();
		baseNpcs = ImmutableSet.<String>builder()
			.addAll(itemToNpcMap.values())
			.build();
	}

	public static void reset()
	{
		itemToNpcMap = null;
		baseNpcs = null;
		baseItems = null;
		itemVariantsToBase = null;
		npcVariantsToBase = null;
	}
}
