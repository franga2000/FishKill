package com.franga2000.fishkill;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class FishKill extends JavaPlugin implements Listener{
	public boolean firework = true;
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e){
		Entity killer = e.getEntity().getKiller();
		if(killer instanceof Player){
			Material itemInHand = ((Player) killer).getItemInHand().getType();
			if(itemInHand == Material.RAW_FISH || itemInHand == Material.COOKED_FISH){
				this.getServer().broadcastMessage(((Player) killer).getName() + " got a fishkill!");
				if(firework){
					Location loc = killer.getLocation();
					Firework firework = killer.getWorld().spawn(loc, Firework.class);
			        FireworkMeta data = (FireworkMeta) firework.getFireworkMeta();
			        data.addEffects(FireworkEffect.builder().withColor(Color.GREEN, Color.YELLOW, Color.RED).with(Type.BALL_LARGE).withTrail().build());
			        data.setPower(1);
			        firework.setFireworkMeta(data);
				}
			}
			
		}
	}
}
