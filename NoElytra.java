/*    */ package com.ytg667.main;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityToggleGlideEvent;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class NoElytra
/*    */   implements Listener
/*    */ {
/*    */   private JavaPlugin plugin;
/*    */   
/*    */   public NoElytra(JavaPlugin plugin) {
/* 16 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void noElytra(EntityToggleGlideEvent event) {
/* 21 */     if (!this.plugin.getConfig().getBoolean("elytra-toggle")) {
/* 22 */       Player player = (Player)event.getEntity();
/* 23 */       player.sendMessage("" + ChatColor.RED + "You can't use elytras!");
/* 24 */       event.setCancelled(true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\downloads\LifeSteal.jar!\com\ytg667\main\NoElytra.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */