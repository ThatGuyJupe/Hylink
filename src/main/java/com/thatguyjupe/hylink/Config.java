package com.thatguyjupe.hylink;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;

public final class Config {
    private static final Path CONFIG_PATH = Paths.get("config.properties");
    private static final String KEY = "discord.webhook_url";

    private final Properties props = new Properties();

    public Config() {
        load();
    }

    private void load() {
        try {
            if (Files.exists(CONFIG_PATH)) {
                try (InputStream in = Files.newInputStream(CONFIG_PATH)) {
                    props.load(in);
                }
            } else {
                props.setProperty(KEY, "");
                save();
            }
        } catch (IOException e) {
            System.err.println("[Config] Failed to load config");
            e.printStackTrace();
        }
    }

    public String getWebhookUrl() {
        return props.getProperty(KEY, "").trim();
    }

    public void setWebhookUrl(String url) {
        props.setProperty(KEY, url == null ? "" : url.trim());
        save();
        System.out.println("Webhook: " + getWebhookUrl());
    }

    private void save() {
        try {
            try (OutputStream out = Files.newOutputStream(CONFIG_PATH)) {
                props.store(out, "App config");
            }
        } catch (IOException e) {
            System.err.println("[Config] Failed to save config");
            e.printStackTrace();
        }
    }
}
