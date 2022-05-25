package ru.xzeldon.removeblockoutline;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveBlockOutline implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("removeblockoutline");

	@Override
	public void onInitialize() {
		LOGGER.info("RemoveBlockOutline mod initialized");
	}
}
