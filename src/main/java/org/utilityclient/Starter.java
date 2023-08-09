package org.utilityclient;

import net.fabricmc.api.ModInitializer;
import org.utilityclient.api.abstraction.Uncommon;

@Uncommon
public class Starter implements ModInitializer {
	@Override
	public void onInitialize() {
		System.out.println("Loading " + UtilityClient.getClientName() + " using Fabric!");
	}
}