package com.pantscheck;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.OverheadTextChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Pants Check"
)
public class PantsCheckPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private PantsCheckConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onOverheadTextChanged(OverheadTextChanged e) {
		if(e.getActor().equals(client.getLocalPlayer()) && e.getOverheadText().equals("Pants?")) {
			client.getLocalPlayer().setOverheadText("Yep, I got pants on!");
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}
	//https://static.runelite.net/api/runelite-api/net/runelite/api/PlayerComposition.html
	//https://github.com/runelite/runelite/issues/7954
	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		final ItemContainer container = event.getItemContainer();
		final Item[] inv = container.getItems();
		client.getLocalPlayer().setOverheadText(inv.toString());
	}

	@Provides
	PantsCheckConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PantsCheckConfig.class);
	}
}
