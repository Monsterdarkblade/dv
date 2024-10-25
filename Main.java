/*    */ package com.ytg667.main;
/*    */ 
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Main
/*    */   extends JavaPlugin
/*    */ {
/*    */   public void onEnable() {
/* 12 */     saveDefaultConfig();
/* 13 */     getServer().getPluginManager().registerEvents(new Event(this), (Plugin)this);
/* 14 */     getServer().getPluginManager().registerEvents(new HeartEvent(this), (Plugin)this);
/* 15 */     getServer().getPluginManager().registerEvents(new NoElytra(this), (Plugin)this);
/* 16 */     getServer().getPluginManager().registerEvents(new DefaultHealth(this), (Plugin)this);
/* 17 */     getServer().getPluginManager().registerEvents(new LoseLifeIfNotKilledByPlayer(this), (Plugin)this);
/* 18 */     getCommand("giveHearts").setExecutor(new GiveHearts());
/* 19 */     getCommand("withdraw").setExecutor(new Withdraw());
/* 20 */     getCommand("reloadConfig").setExecutor(new reloadConfig(this));
/* 21 */     ItemManager.createHeart();
/* 22 */     Recipe.getRecipe(this);
/*    */   }
/*    */ }


/* Location:              D:\downloads\LifeSteal.jar!\com\ytg667\main\Main.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */