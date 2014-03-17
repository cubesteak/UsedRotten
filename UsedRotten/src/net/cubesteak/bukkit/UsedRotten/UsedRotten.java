package net.cubesteak.bukkit.UsedRotten;

import org.bukkit.Server;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;


public class UsedRotten extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info("Ready to have UsedRotten!");
		Server server = this.getServer();
		
		ShapedRecipe leather = new ShapedRecipe(new ItemStack(Material.LEATHER));
		leather.shape("FF","FF");
		leather.setIngredient('F', Material.ROTTEN_FLESH);
		server.addRecipe(leather);
		
		FurnaceRecipe cookedleather = new FurnaceRecipe(new ItemStack(Material.LEATHER), (Material.ROTTEN_FLESH));
		server.addRecipe(cookedleather);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Thanks for having UsedRotten!");
	}
	
}
