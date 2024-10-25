/*    */ package com.ytg667.main;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class GiveHearts
/*    */   implements CommandExecutor {
/*    */   private void giveAmount(Player p, ItemStack is, int amount) {
/* 15 */     if (amount > 0) {
/* 16 */       is = is.clone();
/* 17 */       is.setAmount(amount);
/* 18 */       World world = p.getWorld();
/* 19 */       p.getInventory().addItem(new ItemStack[] { is }).values().stream()
/* 20 */         .filter(item -> !item.getType().isAir())
/* 21 */         .forEach(item -> world.dropItemNaturally(p.getLocation(), item));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
/* 27 */     if (!sender.hasPermission("giveHearts")) {
/* 28 */       sender.sendMessage("" + ChatColor.RED + "You don't have permissions to do that!");
/*    */     }
/* 30 */     else if (args.length == 0) {
/* 31 */       giveAmount((Player)sender, ItemManager.heart, 1);
/*    */     }
/* 33 */     else if (args.length == 1) {
/*    */       try {
/* 35 */         int i = Integer.parseInt(args[0]);
/* 36 */       } catch (NumberFormatException e) {
/* 37 */         sender.sendMessage("" + ChatColor.RED + "That is not a valid number!");
/*    */       } 
/* 39 */       int amount = Integer.parseInt(args[0]);
/* 40 */       if (amount <= 0) {
/* 41 */         sender.sendMessage("" + ChatColor.RED + "Invalid number! The number must be positive.");
/* 42 */         return true;
/*    */       } 
/* 44 */       Player player = (Player)sender;
/* 45 */       giveAmount(player, ItemManager.heart, amount);
/* 46 */     } else if (args.length >= 2) {
/*    */       try {
/* 48 */         int i = Integer.parseInt(args[0]);
/* 49 */       } catch (NumberFormatException e) {
/* 50 */         sender.sendMessage("" + ChatColor.RED + "That is not a valid number!");
/* 51 */         return true;
/*    */       } 
/* 53 */       int amount = Integer.parseInt(args[0]);
/* 54 */       if (amount <= 0) {
/* 55 */         sender.sendMessage("" + ChatColor.RED + "Invalid number! The number must be positive.");
/* 56 */         return true;
/*    */       } 
/* 58 */       Player player = Bukkit.getPlayer(args[1]);
/* 59 */       if (player == null) {
/* 60 */         sender.sendMessage("" + ChatColor.RED + "That is not a valid player!");
/*    */       } else {
/* 62 */         giveAmount(player, ItemManager.heart, amount);
/*    */       } 
/*    */     } 
/*    */     
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\downloads\LifeSteal.jar!\com\ytg667\main\GiveHearts.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */