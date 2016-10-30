package me.memorycode.Managers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.memorycode.Main.Main;

public class ConfigManager {
	static File f = new File(Main.pl.getDataFolder() + File.separator, "commands.txt");
	public static boolean init() {
		if (!(f.exists())) {
			try {
				f.createNewFile();
			} catch (Exception e) {
				Main.pl.getServer().getConsoleSender().sendMessage(ChatColor.RED + "WEFS encountered an exception; aborting action");
				Main.pl.getServer().getConsoleSender().sendMessage(f.getAbsolutePath());
				e.printStackTrace();
				return false;
			}
		} 
		return true;
	}
	public static boolean append(ArrayList<String> cmds) {
		 if (!(f.exists())) {
			 init();
		 }
		 FileWriter fw;
		 BufferedWriter writer;
		 try {
			 fw = new FileWriter(f.getAbsoluteFile());
			 writer = new BufferedWriter(fw);
		 } catch (Exception e) {
			 e.printStackTrace();
			 writer = null;
		 }
		 for (String cmd : cmds) {
			 try {
		            writer.write(cmd);
		            writer.newLine();
		     } catch (IOException ex) {
		            ex.printStackTrace();
		            return false;
		     }
		 }
		 try {
			 writer.close();
		 } catch (Exception e) {
			 e.printStackTrace();
			 return false;
		 }
		 return true;
	}
	
	public static boolean reset() {
		if (f.exists()) {
			f.delete();
			boolean a = init();
			if (a == true) {
				return true;
			} else {
				return false;
			}
		} else {
			init();
			return f.exists();
		}
	}
}
