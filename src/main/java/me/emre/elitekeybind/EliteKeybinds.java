package me.emre.elitekeybind;

import me.emre.elitekeybind.configuration.Options;
import me.emre.elitekeybind.guis.FKeyBindingGui;
import me.emre.elitekeybind.keybindings.FKeybinding;
import me.emre.elitekeybind.keybindings.QKeybinding;
import me.emre.elitekeybind.keybindings.ShiftFKeybinding;
import me.emre.elitekeybind.keybindings.ShiftQKeybinding;
import me.emre.elitekeybind.utils.Metrics;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class EliteKeybinds extends JavaPlugin {

    private int pluginId = 11393;

    private static EliteKeybinds instance;
    private Options messages;

    @Override
    public void onEnable() {
        instance = this;
        this.messages = new Options(this, "messages");
        this.setUpConfigurationFile();
        this.registerKeybindings();
        printEnable();
        registerMetrics(this, this.pluginId);

    }

    private void registerKeybindings()
    {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new FKeybinding(), this);
        pluginManager.registerEvents(new ShiftFKeybinding(), this);
        pluginManager.registerEvents(new QKeybinding(), this);
        pluginManager.registerEvents(new ShiftQKeybinding(), this);
    }

    private void setUpConfigurationFile()
    {
        saveDefaultConfig();
        getConfig().options().setHeader(getHeaderComments());
        saveConfig();
    }

    private List<String> getHeaderComments()
    {
        String version = getDescription().getVersion();
        List<String> header = new ArrayList<>();

        header.add("## ====================== ##");
        header.add("## Plugin by Amowny ##");
        header.add("## ====================== ##");
        header.add(null);
        header.add("GitHub: https://github.com/Amowny/EliteKeyBinds");
        header.add(null);
        header.add(null);
        header.add("Version: " + version);
        header.add(null);
        header.add(null);
        header.add(null);
        header.add("Note that any keybinding involving Q only works if the player holds an item in their hand. There");
        header.add("unfortunately is no way around this!");

        return header;
    }

    public Options getMessages()
    {
        return messages;
    }

    public static EliteKeybinds getInstance()
    {
        return instance;
    }

    private void registerMetrics(JavaPlugin plugin, int pluginId) {
        new Metrics(plugin, pluginId);
    }

    private void printEnable() {
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(" §3  ______     §b_  __ ");
        Bukkit.getConsoleSender().sendMessage(" §3 |  ____|   §b| |/ /");
        Bukkit.getConsoleSender().sendMessage(" §3 | |__     §b | ' /      §7>- " + this.pluginName());
        Bukkit.getConsoleSender().sendMessage(" §3 |  __|    §b |  <       §7>- Owner " + this.pluginOwner());
        Bukkit.getConsoleSender().sendMessage(" §3 | |____   §b | . \\");
        Bukkit.getConsoleSender().sendMessage(" §3 |______|   §b|_|\\_\\     §7>- Running in §aSpigot §7- §2Paper §7v" + this.pluginVersion());
        Bukkit.getConsoleSender().sendMessage(" ");

    }

    /*private String pluginVersion() {
        return getDescription().getVersion();
    }*/

    public String pluginName() {
        String name = "EliteKeybinds";
        String[] color = {
                "#fbcb63", "#fbbe68", "#fbb06d", "#fba371", "#fb9676", "#fb887b", "#fc7b7f", "#fc6d84", "#fc6089", "#fc538d",
                "#fc4592", "#fc3897", "#fd2b9c" };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++)
            sb.append(ChatColor.of(color[i]) + name.split("")[i]);
        return sb.toString();
    }

    public String pluginVersion() {
        String name = getDescription().getVersion();
        String[] color = {
                "#fbcb63", "#fbbe68", "#fbb06d", "#fba371", "#fb9676", "#fb887b", "#fc7b7f", "#fc6d84", "#fc6089", "#fc538d",
                "#fc4592", "#fc3897", "#fd2b9c" };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++)
            sb.append(ChatColor.of(color[i]) + name.split("")[i]);
        return sb.toString();
    }

    public String pluginOwner() {
        String name = "Amowny";
        String[] color = {
                "#1afbb8", "#19f9c6", "#18f6d4", "#17f4e1", "#16f1ef", "#15effd" };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++)
            sb.append(ChatColor.of(color[i]) + name.split("")[i]);
        return sb.toString();
    }


}
