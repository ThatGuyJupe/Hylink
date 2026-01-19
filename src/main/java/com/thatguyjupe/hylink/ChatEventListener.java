package com.thatguyjupe.hylink;

import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;

public class ChatEventListener {
    static WebhookSender webhookSender = new WebhookSender();
    public static PlayerChatEvent onPlayerChat(PlayerChatEvent event) {
        if (!HylinkPlugin.getConfig().getWebhookUrl().isBlank()) {
            try {
                webhookSender.send(event.getSender().getUsername(), event.getContent().toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return event;
        }
        else return null;
    }
}

