UsedRotten
==========
  
![UsedRottenImg](http://media-elerium.cursecdn.com/attachments/126/187/crafting.png "UsedRotten Image")
  
![Smelted Rotten](https://goo.gl/oGnRvG "Smelting Rotten Flesh")
  
Making Rotten Flesh useful in Spigot/Bukkit/MC. 

This is a very simple plugin for Bukkit.  Others have done it, but have lagged a bit behind bukkit updates.

It adds:

 - rotten flesh in a configurable pattern on a crafting table will make 1 leather.  Default is 4 Rotten in a 2x2 pattern.
 - Rotten Flesh is also now cookable in a furnace to make leather.

### Permissions:

 - The permission UsedRotten.Crafting allows people to craft flesh to leather.
 - Working on a furnace permission, but none yet.

### Configuration:
 The configuration file allows turning Crafting and Smelting of Rotten on or off.
 Additionally, it allows you to pick how many Rotten are used for crafting.  The Crafting options are:
 
 - 1: A single Rotten on a will give Leather:  
&gt;  
R  

 - 2: Two Rotten horizontally will give Leather:  
&gt;  
RR  

 - 4: Four Rotten in a 2x2 shape will give Leather:  
&gt;  
RR  
RR  

 - 6: Six Rotten in a 3x2 shape will give Leather:  
&gt;  
RRR  
RRR  
													
 - 9: Nine Rotten in a 3x3 shape will give Leather:  
&gt;  
RRR  
RRR  
RRR  

### Source Code:
https://github.com/cubesteak/UsedRotten

### Install:

 - Download the jar file (from Files section) and place in server plugins directory.
 - Set config file options to turn crafting and smelting on or off
 - Add permission for crafting if desired.

Developed against CraftBukkit 1.12.1
 
### Releases:  
0.5 - Updated to CraftBukkit 1.12.1

0.4 - Updating plugin.yml to properly reflect new package name an update CraftBukkit to 1.7.9-R0.2

0.3 - Bug fix for crafting multiple items at once, code cleanup and package name change to remove bukkit from the name.  Big thanks to TfT-02 for the help!

0.2 -  Adds config file, multiple crafting shapes and ability to enable crafting or smelting at a global level.

0.11 - Just removes left over debug lines.

0.1 - Initial release

### Known Issues:

- none

### To do(maybe):

 - Add furnace permissions
 - See if I can get a furnace to consume more than 1 flesh per leather created.
 - Allow configuration of output for both using a furnace or a Crafting Table
 

### Thanks:
  - Using THDigi / RecipeUtil for recipe compares.
  - TfT-02 for lending his skills!
