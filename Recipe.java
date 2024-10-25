/*    */ package com.ytg667.main;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.inventory.ShapedRecipe;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class Recipe {
/*    */   public static ShapedRecipe Heart;
/*    */   
/*    */   public static void getRecipe(JavaPlugin plugin) {
/* 15 */     Bukkit.getServer().removeRecipe(new NamespacedKey((Plugin)plugin, "Heart"));
/* 16 */     Heart = new ShapedRecipe(new NamespacedKey((Plugin)plugin, "Heart"), ItemManager.heart);
/* 17 */     Heart.shape(new String[] { "012", "345", "678" });
/*    */ 
/*    */ 
/*    */     
/* 21 */     ConfigurationSection config1 = plugin.getConfig().getConfigurationSection("HeartRecipe");
/*    */     
/* 23 */     Heart.setIngredient('0', Material.getMaterial(config1.getString("Slot0")));
/* 24 */     Heart.setIngredient('1', Material.getMaterial(config1.getString("Slot1")));
/* 25 */     Heart.setIngredient('2', Material.getMaterial(config1.getString("Slot2")));
/* 26 */     Heart.setIngredient('3', Material.getMaterial(config1.getString("Slot3")));
/* 27 */     Heart.setIngredient('4', Material.getMaterial(config1.getString("Slot4")));
/* 28 */     Heart.setIngredient('5', Material.getMaterial(config1.getString("Slot5")));
/* 29 */     Heart.setIngredient('6', Material.getMaterial(config1.getString("Slot6")));
/* 30 */     Heart.setIngredient('7', Material.getMaterial(config1.getString("Slot7")));
/* 31 */     Heart.setIngredient('8', Material.getMaterial(config1.getString("Slot8")));
/* 32 */     Bukkit.getServer().addRecipe((org.bukkit.inventory.Recipe)Heart);
/*    */   }
/*    */ }


/* Location:              D:\downloads\LifeSteal.jar!\com\ytg667\main\Recipe.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */