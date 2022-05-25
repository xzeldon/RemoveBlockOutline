package ru.xzeldon.removeblockoutline.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class RemoveBlockOutlineConfig {
    private static final String COMMENT =
            "This file stores configuration options for RemoveBlockOutline";

    private final Path propertiesPath;
    private boolean enableBlockOutline;

    public RemoveBlockOutlineConfig(Path propertiesPath) {
        enableBlockOutline = false;
        this.propertiesPath = propertiesPath;
    }

    public void initialize() throws IOException {
        load();

        if (!Files.exists(propertiesPath)) {
            save();
        }
    }

    public boolean isEnableBlockOutline() {
        return enableBlockOutline;
    }

    public void setEnableBlockOutline(boolean enableBlockOutline) {
        this.enableBlockOutline = enableBlockOutline;
    }

    public void load() throws IOException {
        if (!Files.exists(propertiesPath)) {
            return;
        }

        Properties properties = new Properties();
        properties.load(Files.newInputStream(propertiesPath));
        enableBlockOutline = !"true".equals(properties.getProperty("enableBlockOutline"));
    }

    public void save() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("enableBlockOutline", enableBlockOutline ? "true" : "false");
        properties.store(Files.newOutputStream(propertiesPath), COMMENT);
    }
}
