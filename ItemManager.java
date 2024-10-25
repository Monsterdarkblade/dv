/*    */ package com.ytg667.main;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.inventory.ItemFlag;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ public class ItemManager
/*    */ {
/*    */   public static ItemStack heart;
/*    */   
/*    */   public static void createHeart() {
/* 18 */     ItemStack item = new ItemStack(Material.HEART_OF_THE_SEA);
/* 19 */     ItemMeta meta = item.getItemMeta();
/* 20 */     meta.setDisplayName("" + ChatColor.LIGHT_PURPLE + "Heart Essence");
/* 21 */     meta.addEnchant(Enchantment.PROTECTION_FALL, 1, false);
/* 22 */     meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
/* 23 */     List<String> lore = new ArrayList<>();
/* 24 */     lore.add("" + ChatColor.YELLOW + "Right click with it to gain one heart");
/* 25 */     meta.setLore(lore);
/* 26 */     item.setItemMeta(meta);
/* 27 */     heart = item;
/*    */   }
/*    */ }


/* Location:              D:\downloads\LifeSteal.jar!\com\ytg667\main\ItemManager.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */