package me.josh.jreports.commands;

import me.josh.jreports.JReports;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ConfigReloadCommand implements CommandExecutor {

    private JReports plugin;
    public ConfigReloadCommand(JReports plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Player player = (Player) sender;

            if (!player.hasPermission(this.plugin.getConfig().getString("admin-permission"))) {
                player.sendMessage(ChatColor.RED + "No Permission");
            }

            reloadConfig();

            player.sendMessage(ChatColor.GREEN + "Reloaded config for JReports");
        }

        reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Reloaded config for JReports");

        return true;
    }

    private void reloadConfig() {
        plugin.reloadConfig();
        FileConfiguration configuration = plugin.getConfig();
    }
}
