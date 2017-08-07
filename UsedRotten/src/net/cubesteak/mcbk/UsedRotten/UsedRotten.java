package net.cubesteak.mcbk.UsedRotten;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Furnace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.NamespacedKey;

import ro.thehunters.digi.recipeUtil.RecipeUtil;

public class UsedRotten extends JavaPlugin implements Listener {

    private ShapedRecipe leather;
    private Boolean ConfigRottenCrafting;
    private Integer ConfigRottenCraftingShape;
    private Boolean ConfigRottenSmelting;
    private Integer ReadShape;
    
 	@Override
    public void onEnable() {
        getLogger().info("Ready to have UsedRotten!");
        Server server = this.getServer();
        getServer().getPluginManager().registerEvents(this, this); // Register the custom events.
        initializeConfig(); // Initialize the configuration
        ConfigRottenCrafting = getConfig().getBoolean("UsedRotten.RottenCrafting");
        ConfigRottenSmelting = getConfig().getBoolean("UsedRotten.RottenSmelting");
        ConfigRottenCraftingShape = getConfig().getInt("UsedRotten.CraftingShape");

        if (ConfigRottenCrafting) {
            leather = new ShapedRecipe(NamespacedKey.minecraft(getName()),new ItemStack(Material.LEATHER));

            // Setting the Shape Used
            switch (ConfigRottenCraftingShape) {
                case 1:
                    leather.shape("F");
                    ReadShape = 1;
                    break;
                case 2:
                    leather.shape("FF");
                    ReadShape = 2;
                    break;
                case 4:
                    leather.shape("FF", "FF");
                    ReadShape = 4;
                    break;
                case 6:
                    leather.shape("FFF", "FFF");
                    ReadShape = 6;
                    break;
                case 9:
                    leather.shape("FFF", "FFF", "FFF");
                    ReadShape = 9;
                    break;
                default:
                    leather.shape("FF", "FF");
                    ReadShape = 4;
                    break;
            }
            getLogger().info("Using " + ReadShape + " Rotten for Crafting");  // Log read Shape

            leather.setIngredient('F', Material.ROTTEN_FLESH);
            server.addRecipe(leather);
        }

        if (ConfigRottenSmelting) {
            FurnaceRecipe cookedleather = new FurnaceRecipe(new ItemStack(Material.LEATHER), (Material.ROTTEN_FLESH));
            server.addRecipe(cookedleather);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Thank you for having UsedRotten!");
    }

    private void initializeConfig() {
        FileConfiguration config = getConfig();

        config.addDefault("UsedRotten.RottenCrafting", true);
        config.addDefault("UsedRotten.CraftingShape", 4);
        config.addDefault("UsedRotten.RottenSmelting", true);

        config.options().copyDefaults(true);
        saveConfig();
    }

    /**
     * Handle PrepareItemCraftEvent at the normal priority.
     * <p/>
     * These events are used to check permission to craft the recipe,
     * the event is triggered when a recipe is found after placing
     * ingredients.
     *
     * @param event The event to modify
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        // Storing the equality result because it's not cheap to compare recipes.
        // I'm doing this because I'm using it twice, if you're using it only once you don't need to store it.
        boolean equal = RecipeUtil.areEqual(event.getRecipe(), leather);

        // confirmation message of event triggering and recipe equality
        // System.out.print("(debug) recipes equal = " + equal);

        if (!equal) {
            return;
        }

        HumanEntity human = event.getView().getPlayer();

        // check if crafter has permission...
        if (!human.hasPermission("UsedRotten.Crafting")) {
            // basically cancels the event
            event.getInventory().setResult(null);

            // need to check because it could be NPCs or something
            if (human instanceof Player) {
                Player player = (Player) human;
                player.sendMessage("Need permission to craft this!");
            }
        }
    }

    /**
     * Monitor CraftItemEvent.
     *
     * @param event The event to monitor
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onCraftItem(CraftItemEvent event) {
        final HumanEntity whoClicked = event.getWhoClicked();

        if (!(whoClicked instanceof Player)) {
            return;
        }

        ItemStack result = event.getRecipe().getResult();

        if (!result.isSimilar(new ItemStack(Material.LEATHER))) {
            return;
        }

        new PlayerUpdateInventoryTask((Player) whoClicked).runTaskLater(this, 0);
    }
    
    /**
     * Monitor When Rotten is Smelted.
     *
     * @param event The event to monitor
    */     
    
    
    @EventHandler
    public void furnaceBurn(FurnaceBurnEvent event) {
        // Setting cookTime when the fuel is consumed
        Furnace furnace = (Furnace) event.getBlock().getState();
        furnace.update(true);
    }
    
    @EventHandler(ignoreCancelled=true)
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getInventory().getType().equals(InventoryType.FURNACE)) {
        	if (event.isShiftClick() || event.getRawSlot() == 0) {
                boolean needsUpdate = event.isShiftClick();
    
                if (needsUpdate) {
                    final Player p = (Player) event.getWhoClicked();
                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                        @SuppressWarnings("deprecation")
						public void run() {
                            p.updateInventory();
                        }
                    }, 0);

                }
            }
        }
    }
  
}
