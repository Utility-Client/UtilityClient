package org.utilityclient;

import net.fabricmc.api.ModInitializer;

public class Starter implements ModInitializer {
	@Override
	public void onInitialize() {
		System.out.println("Loading " + UtilityClient.getClientName() + " using Fabric!");
	}
}