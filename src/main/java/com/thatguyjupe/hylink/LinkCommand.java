package com.thatguyjupe.hylink;

import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;

import javax.annotation.Nonnull;

public class LinkCommand extends CommandBase {

    private final RequiredArg<String> url =
            this.withRequiredArg("webhook", "Discord webhook URL", ArgTypes.STRING);

    public LinkCommand() {
        super("link", "Link a Discord webhook");
        this.setPermissionGroup(GameMode.Creative);
    }

    @Override
    protected void executeSync(@Nonnull CommandContext ctx) {
        String webhook = url.get(ctx);

        if (HylinkPlugin.getConfig().getWebhookUrl().isBlank()) {
            HylinkPlugin.getConfig().setWebhookUrl(webhook);
            ctx.sender().sendMessage(Message.raw("Webhook linked!"));
        } else {
            System.out.println(HylinkPlugin.getConfig().getWebhookUrl());
            ctx.sender().sendMessage(Message.raw("Webhook already linked. Use /hylink unlink."));
        }
    }
}
