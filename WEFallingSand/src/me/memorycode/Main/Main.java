package me.memorycode.Main;

import me.memorycode.Commands.Wefs;
import me.memorycode.Managers.ConfigManager;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public static Plugin pl;
	public void onEnable() {
		pl = this;
		if (!(getDataFolder().exists())) {
			getDataFolder().mkdir();
		}
		ConfigManager.init();
		getCommand("wefs").setExecutor(new Wefs());
	}
	
	public void onDisable() {
		
	}

}
