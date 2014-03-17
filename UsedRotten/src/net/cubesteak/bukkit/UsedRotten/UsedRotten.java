package net.cubesteak.bukkit.UsedRotten;

import org.bukkit.Server;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;

import ro.thehunters.digi.recipeUtil.RecipeUtil;

public class UsedRotten extends JavaPlugin implements Listener {

	private ShapedRecipe leather;
	
	@Override
	public void onEnable() {
		getLogger().info("Ready to have UsedRotten!");
		Server server = this.getServer();

		server.getPluginManager().registerEvents(this, this);
		
		leather = new ShapedRecipe(new ItemStack(Material.LEATHER));
		leather.shape("FF","FF");
		leather.setIngredient('F', Material.ROTTEN_FLESH);
		server.addRecipe(leather);
		
		FurnaceRecipe cookedleather = new FurnaceRecipe(new ItemStack(Material.LEATHER), (Material.ROTTEN_FLESH));
		server.addRecipe(cookedleather);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Thank you for having UsedRotten!");
	}
	
	 @EventHandler
	    public void preCraft(PrepareItemCraftEvent event) // event is triggered when a recipe is found after placing ingredients
	    {
			// Storing the equality result because it's not cheap to compare recipes.
	        // I'm doing this because I'm using it twice, if you're using it only once you don't need to store it.
	        boolean equal = RecipeUtil.areEqual(event.getRecipe(), leather);
	        
	        // confirmation message of event triggering and recipe equality
	        System.out.print("(debug) recipes equal = " + equal);
	        
	        if(equal)
	        {
	            HumanEntity human = event.getView().getPlayer();
	            
	            // check if crafter has permission...
	            if(!human.hasPermission("UsedRotten.Crafting"))
	            {
	                // basically cancels the event
	                event.getInventory().setResult(null);
	                
	                // need to check because it could be NPCs or something
	                if(human instanceof Player)
	                {
	                    Player player = (Player)human;
	                    player.sendMessage("Need permission to craft this!");
	                }
	            }
	        }
	    }
	
	
}
