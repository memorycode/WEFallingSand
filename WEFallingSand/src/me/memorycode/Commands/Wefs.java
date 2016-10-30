package me.memorycode.Commands;

import java.util.ArrayList;

import me.memorycode.Main.Main;
import me.memorycode.Managers.ConfigManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class Wefs implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String l,
			String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			if (p.hasPermission("use.wefs") || p.isOp()) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reset")) {
						p.sendMessage(ChatColor.GREEN + "Resetting file...");
						boolean t = ConfigManager.reset();
						if (t == true) {
							p.sendMessage(ChatColor.GREEN + "File reset!");
							return true;
						} else {
							p.sendMessage(ChatColor.RED + "File was unable to be reset.");
							return false;
						}
					}
					return false;
				}
				WorldEditPlugin we = (WorldEditPlugin)Main.pl.getServer().getPluginManager().getPlugin("WorldEdit");
		        Selection sel = we.getSelection(p);
		        if(sel == null){
		           p.sendMessage(ChatColor.RED + "Please make a selection first.");
		           return false;
		        }
		        Location max = sel.getMaximumPoint();
		        Location min = sel.getMinimumPoint();
		        ArrayList<String> cmds = new ArrayList<String>();
		        for(int x = min.getBlockX(); x <= max.getBlockX(); x++) {
		        	  for(int y = min.getBlockY(); y <= max.getBlockY(); y++) {
		        	    for(int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
		        	      Block b = p.getWorld().getBlockAt(new Location(p.getWorld(), x,y,z));
		        	      if (b.getTypeId() == 0) {
		        	    	  
		        	      } else {
		        	    	  cmds.add("summon FallingSand " 
		        	    			  + x 
		        	    			  + " " 
		        	    			  + y 
		        	    			  + " " 
		        	    			  + z 
		        	    			  + " {TileID:" 
		        	    			  + b.getTypeId() 
		        	    			  + ",Data:" 
		        	    			  + b.getData() 
		        	    			  + ",Time:1,DropItem:0,Riding:{id:\"ArmorStand\",Marker:1b,Invisible:1,NoGravity:1}}");
		        	      }
		        	    }
		        	  }
		        }
		        boolean suc = ConfigManager.append(cmds);
		        if (suc == true) {
		        	p.sendMessage(ChatColor.GREEN + "Successfully transfered " + cmds.size() + " block(s) into commands.");
		        	return true;
		        } else if (suc == false){
		        	p.sendMessage(ChatColor.RED + "Unable to transfer " + cmds.size() + " block(s) into commands.");
		        	return false;
		        } else {
		        	p.sendMessage(ChatColor.RED + "Request nulled.");
		        	return false;
		        }
			} else {
				p.sendMessage(ChatColor.RED + "You're not allowed to do that.");
				return false;
			}
		} else {
			s.sendMessage(ChatColor.RED + "You're unable to perform this command.");
			return false;
		}
	}

	
}
