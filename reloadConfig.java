/*    */ package com.ytg667.main;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class reloadConfig
/*    */   implements CommandExecutor {
/*    */   private JavaPlugin plugin;
/*    */   
/*    */   public reloadConfig(JavaPlugin plugin) {
/* 14 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
/* 19 */     if (!sender.hasPermission("configReload")) {
/* 20 */       sender.sendMessage("" + ChatColor.RED + "You don't have permissions to do that!");
/*    */     } else {
/* 22 */       this.plugin.reloadConfig();
/* 23 */       sender.sendMessage("" + ChatColor.GREEN + "Reload complete.");
/* 24 */       Recipe.getRecipe(this.plugin);
/*    */     } 
/* 26 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\downloads\LifeSteal.jar!\com\ytg667\main\reloadConfig.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */