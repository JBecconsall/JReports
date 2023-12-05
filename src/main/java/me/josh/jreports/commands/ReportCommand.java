package me.josh.jreports.commands;

import me.josh.jreports.JReports;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {

    private JReports plugin;
    public ReportCommand(JReports plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Usage: /report <player> <reason>");
                return true;
            }

            String suspectPlayerName = args[0];
            Player reportedPlayer = Bukkit.getPlayer(suspectPlayerName);
            String reason = String.join(" ", args).substring(args[0].length() + 1);

            if (player.hasPermission(this.plugin.getConfig().getString("report-permission"))) {
                if (reportedPlayer == null || !reportedPlayer.isOnline()) {
                    player.sendMessage(ChatColor.RED + "The player you tried to report is not online");
                    return true;
                }

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("report-success").replace("<player>", suspectPlayerName)));

                for (Player staff : Bukkit.getOnlinePlayers()) {
                    if (staff.hasPermission(this.plugin.getConfig().getString("see-reports"))) {
                        staff.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("report-message").replace("<sender>", player.getName()).replace("<target>", suspectPlayerName).replace("<reason>", reason)));
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "No Permission");
            }

        } else {
            sender.sendMessage(ChatColor.RED + "This command cannot be run in console.");
        }

        return true;
    }
}
