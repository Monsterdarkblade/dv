/*    */ package com.ytg667.main;
/*    */ 
/*    */ import java.util.Date;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.BanList;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.ban.ProfileBanList;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.command.ConsoleCommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.PlayerDeathEvent;
/*    */ import org.bukkit.event.player.PlayerRespawnEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class LoseLifeIfNotKilledByPlayer
/*    */   implements Listener
/*    */ {
/*    */   private JavaPlugin plugin;
/* 26 */   private Set<UUID> execute = new HashSet<>();
/*    */   
/*    */   public LoseLifeIfNotKilledByPlayer(JavaPlugin plugin) {
/* 29 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */   public void Ban(Player dead) {
/* 33 */     double hp = Math.abs(this.plugin.getConfig().getDouble("defaultHealth"));
/* 34 */     dead.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
/* 35 */     dead.setHealth(hp);
/* 36 */     ProfileBanList ban = (ProfileBanList)Bukkit.getBanList(BanList.Type.PROFILE);
/* 37 */     int days = this.plugin.getConfig().getInt("banTime");
/* 38 */     if (days == 0) {
/* 39 */       dead.kickPlayer("" + ChatColor.RED + "You've been banned forever for the reason: \n" + ChatColor.RED + "You lost all your hearts!");
/* 40 */       ban.addBan(dead.getPlayerProfile(), "" + ChatColor.RED + "You lost all your hearts!" + ChatColor.RED, (Date)null, null);
/*    */     } else {
/* 42 */       String message; Date date = new Date(System.currentTimeMillis() + days * 86400000L);
/*    */       
/* 44 */       if (days == 1) { message = "" + ChatColor.RED + "You've been banned for a day for the reason: \n" + ChatColor.RED + "You lost all your hearts!"; }
/* 45 */       else { message = "" + ChatColor.RED + "You've been banned for " + ChatColor.RED + " days for the reason: \n" + days + "You lost all your hearts!"; }
/* 46 */        dead.kickPlayer(message);
/* 47 */       ban.addBan(dead.getPlayerProfile(), "" + ChatColor.RED + "You lost all your hearts!" + ChatColor.RED, date, null);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void runCommand(String command, String name) {
/* 52 */     ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
/* 53 */     for (int i = 1; i < command.length() - 1; i++) {
/* 54 */       if (command.charAt(i - 1) == '<' && command.charAt(i) == 'P' && command.charAt(i + 1) == '>') {
/* 55 */         command = command.replaceAll("<P>", name);
/*    */       }
/*    */     } 
/* 58 */     Bukkit.dispatchCommand((CommandSender)console, command);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void loseLife(PlayerDeathEvent event) {
/* 63 */     if (this.plugin.getConfig().getBoolean("loseLifeIfNotKilledByPlayer") && (event.getEntity().getKiller() == null || event.getEntity().getKiller().getUniqueId().equals(event.getEntity().getUniqueId()))) {
/* 64 */       Player dead = event.getEntity();
/* 65 */       event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), ItemManager.heart.clone());
/* 66 */       double deadHealth = dead.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
/* 67 */       if (deadHealth > Math.abs(this.plugin.getConfig().getDouble("stolenHealth"))) {
/* 68 */         dead.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(deadHealth - Math.abs(this.plugin.getConfig().getDouble("stolenHealth")));
/*    */       }
/* 70 */       else if (this.plugin.getConfig().getInt("banTime") >= 0) {
/* 71 */         Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, () -> Ban(dead), 4L);
/*    */       } else {
/* 73 */         this.execute.add(dead.getUniqueId());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void respawn(PlayerRespawnEvent event) {
/* 81 */     Player player = event.getPlayer();
/* 82 */     if (this.execute.contains(player.getUniqueId())) {
/* 83 */       Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, () -> runCommand(this.plugin.getConfig().getString("command"), player.getName()), 1L);
/* 84 */       this.execute.remove(player.getUniqueId());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\downloads\LifeSteal.jar!\com\ytg667\main\LoseLifeIfNotKilledByPlayer.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */