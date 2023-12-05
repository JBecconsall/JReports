package me.josh.jreports;

import me.josh.jreports.commands.ConfigReloadCommand;
import me.josh.jreports.commands.ReportCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class JReports extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        saveDefaultConfig();
    }

    public void registerCommands() {
        getCommand("report").setExecutor(new ReportCommand(this));
        getCommand("configreloadj").setExecutor(new ConfigReloadCommand(this));
    }
}
