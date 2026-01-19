package com.thatguyjupe.hylink;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class HylinkCommand extends AbstractCommandCollection {

    public HylinkCommand() {

        super("hylink", "");
        addSubCommand(new LinkCommand());
        addSubCommand(new UnlinkCommand());
    }
}
