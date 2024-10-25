/*    */ package com.ytg667.main;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class Withdraw
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
/* 27 */     if (!(sender instanceof Player)) {
/* 28 */       sender.sendMessage("You need to be a player to do that!");
/* 29 */       return true;
/*    */     } 
/* 31 */     if (args.length > 0) {
/* 32 */       Player player = (Player)sender;
/* 33 */       double Health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
/*    */       try {
/* 35 */         int i = Integer.parseInt(args[0]);
/* 36 */       } catch (NumberFormatException e) {
/* 37 */         sender.sendMessage("" + ChatColor.RED + "Invalid number! The number must be an integer!");
/* 38 */         return true;
/*    */       } 
/* 40 */       int hearts = Integer.parseInt(args[0]);
/* 41 */       if (hearts <= 0) {
/* 42 */         sender.sendMessage("" + ChatColor.RED + "Invalid number! The number must be positive!");
/* 43 */         return true;
/*    */       } 
/* 45 */       if (hearts >= Health / 2.0D) {
/* 46 */         sender.sendMessage("" + ChatColor.RED + "You don't have enough hearts for that!");
/*    */       } else {
/* 48 */         player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Health - (2 * hearts));
/* 49 */         giveAmount(player, ItemManager.heart, hearts);
/*    */       } 
/*    */     } else {
/* 52 */       sender.sendMessage("" + ChatColor.RED + "Invalid input!");
/* 53 */       sender.sendMessage("" + ChatColor.RED + "/withdraw <hearts>");
/*    */     } 
/*    */ 
/*    */     
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\downloads\LifeSteal.jar!\com\ytg667\main\Withdraw.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */