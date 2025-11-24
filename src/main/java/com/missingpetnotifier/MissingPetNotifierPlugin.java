package com.missingpetnotifier;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.api.gameval.VarPlayerID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Missing Pet Notifier"
)
public class MissingPetNotifierPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	private MissingPetNotifierConfig config;
	@Inject
	private OverlayManager overlayManager;

	private MissingPetNotifierOverlay overlay;

	@Getter
	private int numMissingTicks = 0;

	@Override
	protected  void startUp()
	{
		overlay = new MissingPetNotifierOverlay();
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
		overlay = null;
		numMissingTicks = 0;
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (client.getVarpValue(VarPlayerID.FOLLOWER_NPC) != -1  && client.getFollower() == null)
		{
			if (numMissingTicks >= config.timeMissingDelay())
			{
				overlayManager.add(overlay);
			}
			else
			{
				numMissingTicks++;
			}
		}
		else
		{
			numMissingTicks = 0;
			overlayManager.remove(overlay);
		}
	}

	@Provides
	MissingPetNotifierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MissingPetNotifierConfig.class);
	}
}
