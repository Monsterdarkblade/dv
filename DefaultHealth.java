/*    */ package com.ytg667.main;
/*    */ 
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class DefaultHealth
/*    */   implements Listener {
/*    */   private JavaPlugin plugin;
/*    */   
/*    */   public DefaultHealth(JavaPlugin plugin) {
/* 15 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void firstJoin(PlayerJoinEvent event) {
/* 20 */     Player player = event.getPlayer();
/* 21 */     if (!player.hasPlayedBefore()) {
/* 22 */       double health = Math.abs(this.plugin.getConfig().getDouble("defaultHealth"));
/* 23 */       player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
/* 24 */       player.setHealth(health);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\downloads\LifeSteal.jar!\com\ytg667\main\DefaultHealth.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */