/*    */ package com.ytg667.main;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.inventory.CraftItemEvent;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.event.inventory.InventoryType;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class HeartEvent implements Listener {
/*    */   private static JavaPlugin plugin;
/*    */   
/*    */   public HeartEvent(JavaPlugin plugin) {
/* 24 */     this; HeartEvent.plugin = plugin;
/*    */   }
/*    */   
/*    */   public static boolean isHeart(ItemStack[] items) {
/* 28 */     for (int i = 0; i < items.length; i++) {
/* 29 */       if (items[i].isSimilar(ItemManager.heart)) {
/* 30 */         return true;
/*    */       }
/*    */     } 
/* 33 */     return false;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void OnCraft(CraftItemEvent event) {
/* 38 */     ItemStack[] crafting = event.getInventory().getMatrix();
/* 39 */     if (isHeart(crafting)) {
/* 40 */       event.setCancelled(true);
/* 41 */       event.getWhoClicked().sendMessage("" + ChatColor.RED + "You can't craft with hearts!");
/* 42 */       event.getWhoClicked().closeInventory();
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void OnAnvil(InventoryClickEvent event) {
/* 48 */     if (event.getClickedInventory() == null || event.getCurrentItem() == null)
/* 49 */       return;  if (event.getClickedInventory().getType().equals(InventoryType.ANVIL)) {
/* 50 */       Inventory inv = event.getClickedInventory();
/* 51 */       if (inv.getItem(0).isSimilar(ItemManager.heart) && 
/* 52 */         event.getSlot() == 2) {
/* 53 */         event.setCancelled(true);
/* 54 */         event.getWhoClicked().sendMessage("" + ChatColor.RED + "You can't rename hearts!");
/* 55 */         event.getWhoClicked().closeInventory();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void AddHeart(PlayerInteractEvent event) {
/* 63 */     if (event.getItem() != null && (
/* 64 */       event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.getItem().isSimilar(ItemManager.heart)) {
/* 65 */       if (event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() <= Math.abs(plugin.getConfig().getDouble("maxHealth")) - 2.0D) {
/* 66 */         int count = event.getItem().getAmount();
/* 67 */         Player player = event.getPlayer();
/* 68 */         double health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
/* 69 */         player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health + 2.0D);
/* 70 */         event.getItem().setAmount(count - 1);
/*    */       } else {
/* 72 */         event.getPlayer().sendMessage("" + ChatColor.RED + "You can't get anymore hearts!");
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void knowledge(PlayerJoinEvent event) {
/* 80 */     event.getPlayer().discoverRecipe(new NamespacedKey((Plugin)plugin, "Heart"));
/*    */   }
/*    */ }


/* Location:              D:\downloads\LifeSteal.jar!\com\ytg667\main\HeartEvent.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */