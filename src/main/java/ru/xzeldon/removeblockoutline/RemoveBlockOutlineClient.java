package ru.xzeldon.removeblockoutline;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.xzeldon.removeblockoutline.config.RemoveBlockOutlineConfig;

import java.io.IOException;

public class RemoveBlockOutlineClient implements ClientModInitializer {
    private static KeyBinding toggleKeybind;

    public static RemoveBlockOutlineConfig CONFIG;

    @Override
    public void onInitializeClient() {
        CONFIG = new RemoveBlockOutlineConfig(FabricLoader.getInstance().getConfigDir().resolve("removeblockoutline.properties"));

        try {
            CONFIG.initialize();
        } catch (IOException e) {
            RemoveBlockOutline.LOGGER.error("Failed to initialize configuration, default values will be used instead");
            RemoveBlockOutline.LOGGER.error("", e);
        }

        toggleKeybind = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("removeblockoutline.keybind.toggle",
                        InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_GRAVE_ACCENT,
                        "category.removeblockoutline.keybinds")
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKeybind.wasPressed()) {
                if (CONFIG.isEnableBlockOutline()) {
                    CONFIG.setEnableBlockOutline(false);
                    try {
                        CONFIG.save();
                    } catch (IOException e) {
                        RemoveBlockOutline.LOGGER.error("Failed to save configuration");
                        RemoveBlockOutline.LOGGER.error("", e);
                    }
                } else {
                    CONFIG.setEnableBlockOutline(true);
                    try {
                        CONFIG.save();
                    } catch (IOException e) {
                        RemoveBlockOutline.LOGGER.error("Failed to save configuration");
                        RemoveBlockOutline.LOGGER.error("", e);
                    }
                }
            }
        });
    }
}
