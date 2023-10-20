import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("vanish").setExecutor((CommandExecutor)new VanishCommand(this));
        getLogger().info("De plugin is opgestart");

    }

    @Override
    public void onDisable() {

        getLogger().info("De plugin is afgesloten");

    }

}
