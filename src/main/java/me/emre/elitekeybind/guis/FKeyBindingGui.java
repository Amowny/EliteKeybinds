package me.emre.elitekeybind.guis;

import com.sun.tools.javac.jvm.Items;
import me.emre.elitekeybind.EliteKeybinds;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FKeyBindingGui{

    private final EliteKeybinds plugin;

    public FKeyBindingGui(EliteKeybinds plugin){
        this.plugin = plugin;
    }

    public Inventory FKeyBindingGui(Player player){

        String guiTitle = plugin.getConfig().getString("GUI.Settings.FKeyBind.guiTitle");
        String guiType = plugin.getConfig().getString("GUI.Settings.Items.Colors.Type");
        String fillerItemGrabber = plugin.getConfig().getString("GUI.Settings.Items.Filler");

        Inventory FKeyBindingGui = Bukkit.createInventory((InventoryHolder)player, 54, org.bukkit.ChatColor.translateAlternateColorCodes('&', guiTitle));

        ItemStack fillerItem = new ItemStack(Material.valueOf(fillerItemGrabber), 1);
        ItemStack backButton = new ItemStack(Material.PAPER, 1);
        ItemStack teleport = null;
        ItemStack seviye = null;
        ItemStack yukseltme = null;
        ItemStack roller = null;
        ItemStack banka = null;
        ItemStack warp = null;
        ItemStack profil = null;
        ItemStack sanalsandik = null;
        ItemStack yardimsayfasi = null;
        ItemStack adareklam =  null; //Yıldız
        ItemStack tutorial = null; //Büyüteç

        if (guiType.equalsIgnoreCase("Paper")){
            teleport = new ItemStack(Material.PAPER, 1);
            seviye = new ItemStack(Material.PAPER, 1);
            yukseltme = new ItemStack(Material.PAPER, 1);
            roller = new ItemStack(Material.PAPER, 1);
            banka = new ItemStack(Material.PAPER, 1);
            warp = new ItemStack(Material.PAPER, 1);
            profil = new ItemStack(Material.PAPER, 1);
        }

        ItemMeta filleritem = fillerItem.getItemMeta();
        filleritem.setCustomModelData(3162);
        filleritem.setDisplayName(Color("&7"));
        filleritem.setUnbreakable(true);
        fillerItem.setItemMeta(filleritem);

        ItemMeta backmeta = backButton.getItemMeta();
        backmeta.setCustomModelData(6231);
        backmeta.setDisplayName(Color(Hex("&8Back to menu.")));
        backButton.setItemMeta(backmeta);

        ItemMeta teleportmeta = teleport.getItemMeta();
        teleportmeta.setCustomModelData(3162);
        teleportmeta.setDisplayName(Color(Hex("&8Back to menu.")));
        teleport.setItemMeta(teleportmeta);

        ItemMeta seviyemeta = seviye.getItemMeta();
        seviyemeta.setCustomModelData(3162);
        seviyemeta.setDisplayName(Color(Hex("&8Back to menu.")));
        seviye.setItemMeta(seviyemeta);

        ItemMeta yukseltmemeta = yukseltme.getItemMeta();
        yukseltmemeta.setCustomModelData(3162);
        yukseltmemeta.setDisplayName(Color(Hex("&8Back to menu.")));
        yukseltme.setItemMeta(yukseltmemeta);

        ItemStack[] menuItems = {teleport,teleport,teleport,seviye,seviye,seviye,yukseltme,yukseltme,yukseltme};


        FKeyBindingGui.setContents(menuItems);

        player.openInventory(FKeyBindingGui);

        return FKeyBindingGui;
    }

    private String Color(String colortext){
        colortext = org.bukkit.ChatColor.translateAlternateColorCodes('&', colortext);
        return colortext;
    }

    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");

    public static String Hex(String message) {
        Matcher matcher = HEX_PATTERN.matcher(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, net.md_5.bungee.api.ChatColor.of(matcher.group(1)).toString());
        }

        return matcher.appendTail(buffer).toString();
    }

}
