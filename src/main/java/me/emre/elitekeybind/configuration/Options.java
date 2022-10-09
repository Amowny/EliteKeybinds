package me.emre.elitekeybind.configuration;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Options
{

    private final HashMap<String, Object> cache = new HashMap<>();
    private final FileConfiguration configuration;
    private final File file;

    public Options(JavaPlugin plugin, String name)
    {
        this(plugin, name, true);
    }

    public Options(JavaPlugin plugin, String name, boolean saveDefault)
    {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        if (saveDefault && !file.exists() && plugin.getResource(name + ".yml") != null)
            plugin.saveResource(name + ".yml", false);
        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    public String getTranslatedString(Player player, String path, String... replacements)
    {
        path = player.getLocale().split("_")[0] + "_" + path;
        String message = getString(path);
        if (message == null)
            return "null";
        for (String replacement : replacements)
            message = message.replaceFirst("<v>", replacement);
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getString(String path, String... replacements)
    {
        String message = getString(path);
        if (message == null)
            return "null";
        for (String replacement : replacements)
            message = message.replaceFirst("<v>", replacement);
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getMessage(String path, String... replacements)
    {
        String prefix = "";
        if (configuration.isSet("prefix"))
            prefix = getString("prefix").trim() + " ";
        return prefix + getString(path, replacements).trim();
    }

    public void set(String path, Object value)
    {
        configuration.set(path, value);
        cache.put(path, value);
        saveOptions();
    }

    public boolean getBoolean(String path)
    {
        if (!cache.containsKey(path))
            cache.put(path, configuration.getBoolean(path));
        return (boolean) cache.get(path);
    }

    public Location getLocation(String path)
{
        if (!cache.containsKey(path))
            cache.put(path, configuration.getLocation(path));
        return (Location) cache.get(path);
    }

    public int getInt(String path)
    {
        if (!cache.containsKey(path))
            cache.put(path, configuration.getInt(path));
        return (int) cache.get(path);
    }

    public long getLong(String path)
    {
        if (!cache.containsKey(path))
            cache.put(path, configuration.getLong(path));
        return (long) cache.get(path);
    }

    public List<String> getStringList(String path)
    {
        if (!cache.containsKey(path))
            cache.put(path, configuration.getStringList(path));
        return (List<String>) cache.get(path);
    }

    public double getDouble(String path)
    {
        if (!cache.containsKey(path))
            cache.put(path, configuration.getDouble(path));
        return (double) cache.get(path);
    }

    public String getString(String path)
    {
        if (!cache.containsKey(path))
        {
            String text = configuration.getString(path);
            if (text == null)
                return null;
            cache.put(path, ChatColor.translateAlternateColorCodes('&', text));
        }
        return (String) cache.get(path);
    }

    public File getFile()
    {
        return file;
    }

    private void saveOptions()
    {
        try
        {
            configuration.save(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfiguration()
    {
        return configuration;
    }
}
