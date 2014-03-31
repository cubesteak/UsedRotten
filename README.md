UsedRotten
==========

Making Rotten Flesh useful in Bukkit.

This is a very simple plugin for Bukkit.  Others have done it, but have lagged a bit behind bukkit updates.

It adds:
 - rotten flesh in a configurable pattern on a crafting table will make 1 leather.  Default is 4 Rotten in a 2x2 pattern.
 - Rotten Flesh is also now cookable in a furnace to make leather.

Permissions:
 - The permission UsedRotten.Crafting allows people to craft flesh to leather.
 - Working on a furnace permission, but none yet.

Configuration:
 The configuration file allows turning Crafting and Smelting of Rotten on or off.
 Additionally, it allows you to pick how many Rotten are used for crafting.  The Crafting options are:
 
 - 1: A single Rotten on a will give Leather: 			R

 - 2: Two Rotten horizontally will give Leather: 		RR

 - 4: Four Rotten in a 2x2 shape will give Leather:		RR
  														RR

 - 6: Six Rotten in a 3x2 shape will give Leather:		RRR
  														RRR
  														
 - 9: Nine Rotten in a 3x3 shape will give Leather		RRR
  														RRR
  														RRR

Source Code:
https://github.com/cubesteak/UsedRotten

Install:
 - Download the jar file (from Files section) and place in server plugins directory.
 - Add permission for crafting if desired.

Written for CraftBukkit Beta Build (1.7.2-R0.3)
 
Releases:
0.2 - Latest. Adds config file, multiple crafting shapes and ability to enable crafting or smelting at a global level.
0.11 - Just removes left over debug lines.
0.1 - Initial release

To do:

 - Add furnace permissions
 - See if I can get a furnace to consume more than 1 flesh per leather created.
 - Allow configuration of output for both using a furnace or a Crafting Table
 

Thanks:
  - Using THDigi / RecipeUtil for recipe compares.