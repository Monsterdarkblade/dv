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
/*    */ import org.bukkit.event.entity.EntityDeathEvent;
/*    */ import org.bukkit.event.player.PlayerRespawnEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class Event
/*    */   implements Listener {
/*    */   private JavaPlugin plugin;
/* 25 */   private Set<UUID> execute = new HashSet<>();
/*    */   
/*    */   public Event(JavaPlugin plugin) {
/* 28 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */   
/*    */   public void Ban(Player dead) {
/* 33 */     double hp = Math.abs(this.plugin.getConfig().getDouble("defaultHealth"));
/* 34 */     dead.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
/* 35 */     dead.setHealth(hp);
/* 36 */     ProfileBanList ban = (ProfileBanList)Bukkit.getBanList(BanList.Type.NAME);
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
/*    */   public void Steal(EntityDeathEvent event) {
/* 63 */     if (event.getEntity() instanceof Player && event.getEntity().getKiller() instanceof Player) {
/* 64 */       Player dead = (Player)event.getEntity();
/* 65 */       Player killer = event.getEntity().getKiller();
/*    */       
/* 67 */       if (dead.getUniqueId().equals(killer.getUniqueId()))
/*    */         return; 
/* 69 */       double deadHealth = dead.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
/* 70 */       double killerHealth = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
/* 71 */       if (deadHealth > Math.abs(this.plugin.getConfig().getDouble("stolenHealth"))) {
/* 72 */         dead.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(deadHealth - Math.abs(this.plugin.getConfig().getDouble("stolenHealth")));
/*    */       }
/* 74 */       else if (this.plugin.getConfig().getInt("banTime") >= 0) {
/* 75 */         Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, () -> Ban(dead), 4L);
/*    */       } else {
/* 77 */         this.execute.add(dead.getUniqueId());
/*    */       } 
/*    */       
/* 80 */       if (killerHealth <= Math.abs(this.plugin.getConfig().getDouble("maxHealth")) - Math.abs(this.plugin.getConfig().getDouble("stolenHealth"))) {
/* 81 */         killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(killerHealth + Math.abs(this.plugin.getConfig().getDouble("stolenHealth")));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void respawn(PlayerRespawnEvent event) {
/* 88 */     Player player = event.getPlayer();
/* 89 */     if (this.execute.contains(player.getUniqueId())) {
/* 90 */       Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, () -> runCommand(this.plugin.getConfig().getString("command"), player.getName()), 1L);
/* 91 */       this.execute.remove(player.getUniqueId());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\downloads\LifeSteal.jar!\com\ytg667\main\Event.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */