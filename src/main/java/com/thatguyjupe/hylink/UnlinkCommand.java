package com.thatguyjupe.hylink;

import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;

import javax.annotation.Nonnull;

public class UnlinkCommand extends CommandBase {

    public UnlinkCommand() {
        super("unlink", "Unlinks the Discord Webhook.");
        this.setPermissionGroup(GameMode.Creative);
    }

    @Override
    protected void executeSync(@Nonnull CommandContext ctx) {
        if (HylinkPlugin.getConfig().getWebhookUrl().isBlank()) {
            ctx.sender().sendMessage(Message.raw("No webhook to unlink!"));
        } else {
            HylinkPlugin.getConfig().setWebhookUrl("");
            ctx.sender().sendMessage(Message.raw("Webhook unlinked!"));
        }
    }
}
