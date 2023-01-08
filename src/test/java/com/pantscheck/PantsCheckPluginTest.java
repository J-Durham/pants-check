package com.pantscheck;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PantsCheckPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PantsCheckPlugin.class);
		RuneLite.main(args);
	}
}