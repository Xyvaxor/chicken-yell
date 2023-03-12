package com.chickenyell;

import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.GameTick;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.api.ChatMessageType;

@PluginDescriptor(
		name = "Chicken Detector",
		description = "Yells \"Chicken!\" when a chicken is detected near the player",
		tags = {"chicken", "detector", "yell"}
)
public class ExamplePlugin extends Plugin
{
	@Inject
	private Client client;

	private static final String CHICKEN_NAME = "Chicken";

	@Subscribe
	public void onGameTick(GameTick event)
	{
		// Get the local player's position
		int playerX = client.getLocalPlayer().getWorldLocation().getX();
		int playerY = client.getLocalPlayer().getWorldLocation().getY();

		// Loop through all NPCs in the area
		for (NPC npc : client.getNpcs())
		{
			// If the NPC is a chicken and is within 5 tiles of the player
			if (npc.getName().equals(CHICKEN_NAME) && npc.getWorldLocation().distanceTo(client.getLocalPlayer().getWorldLocation()) <= 5)
			{
				// Yell "Chicken!"
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Chicken!", null);
				break;
			}
		}
	}
}