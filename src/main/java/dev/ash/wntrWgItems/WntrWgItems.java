package dev.ash.wntrWgItems;

import dev.ash.wntrWgItems.listeners.ItemRestrictionListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class WntrWgItems extends JavaPlugin {

    private static WntrWgItems instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ItemRestrictionListener(this), this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args) {

        if (!command.getName().equalsIgnoreCase("wgitems")) {
            return false;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("wgitems.reload")) {
                sender.sendMessage(colorize("&cYou don't have permission to do that."));
                return true;
            }
            reloadConfig();
            sender.sendMessage(colorize("&aWg-Items config reloaded successfully."));
            return true;
        }

        sender.sendMessage(colorize("&eUsage: /wgitems reload"));
        return true;
    }

    public static WntrWgItems getInstance() {
        return instance;
    }

    public static Component colorize(String text) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(text);
    }

    public Component getMessage(String key, String fallback) {
        String raw = getConfig().getString("messages." + key, fallback);
        return colorize(raw);
    }
}
