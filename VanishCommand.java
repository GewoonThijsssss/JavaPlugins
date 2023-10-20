package me.thijs.plugin.command;

import me.thijs.plugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import java.util.HashMap;
import java.util.Map;

public class VanishCommand implements CommandExecutor {
    private final Map<Player, BossBar> inVanish = new HashMap<>();

    public VanishCommand(Main main) {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.RED + "Dit commando kan alleen door spelers worden uitgevoerd!");
            return true;

        }

        Player player = (Player) sender;

        if (player.hasPermission("vanish.toggle")) {

            if (player.isInvisible()) {

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.showPlayer(player);
                }

                player.setInvisible(false);
                player.sendMessage(ChatColor.GREEN + "Je bent niet langer in vanish.");

            } else {

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.hidePlayer(player);
                }

                player.setInvisible(true);
                player.sendMessage(ChatColor.GREEN + "Je bent nu in vanish.");

                BossBar bossBar = Bukkit.createBossBar("Je bent onzichtbaar.", BarColor.WHITE, BarStyle.SOLID);
                inVanish.put(player, bossBar);
                bossBar.addPlayer(player);
            }

        } else {

            player.sendMessage(ChatColor.RED + "Je hebt geen toestemming om dit commando uit te voeren.");

        }

        return true;

    }

    public void onPlayerJoin(Player player) {

        for (BossBar bossBar : inVanish.values()) {
            player.hidePlayer(player);

        }

    }

    public void onPlayerLeave(Player player) {

        BossBar bossBar = inVanish.get(player);

        if (bossBar != null) {
            bossBar.removePlayer(player);

        }

    }

}


