package dev.ash.wntrWgItems.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import dev.ash.wntrWgItems.WntrWgItems;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public class ItemRestrictionListener implements Listener {

    private final WntrWgItems plugin;

    public ItemRestrictionListener(WntrWgItems plugin) {
        this.plugin = plugin;
    }

    private boolean isInsideRestrictedRegion(Location location, String configKey) {
        if (location == null || location.getWorld() == null) {
            return false;
        }

        List<String> restrictedRegions = plugin.getConfig().getStringList(configKey);
        if (restrictedRegions.isEmpty()) {
            return false;
        }

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(location.getWorld()));
        if (manager == null) {
            return false;
        }

        BlockVector3 vec = BlockVector3.at(
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ()
        );

        for (String regionId : restrictedRegions) {
            ProtectedRegion region = manager.getRegion(regionId);
            if (region != null && region.contains(vec)) {
                return true;
            }
        }
        return false;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onMaceInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.MACE) {
            return;
        }

        Player player = event.getPlayer();
        if (!isInsideRestrictedRegion(player.getLocation(), "Mace-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("mace-disabled", "&cYou cannot use a Mace here.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onMaceDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) {
            return;
        }

        ItemStack held = player.getInventory().getItemInMainHand();
        if (held.getType() != Material.MACE) {
            return;
        }

        if (!isInsideRestrictedRegion(player.getLocation(), "Mace-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("mace-disabled", "&cYou cannot use a Mace here.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onRocketInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.FIREWORK_ROCKET) {
            return;
        }

        Player player = event.getPlayer();
        if (!isInsideRestrictedRegion(player.getLocation(), "Rockets-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("rockets-disabled", "&cFirework rockets are disabled in this area.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onRocketLaunch(ProjectileLaunchEvent event) {
        if (event.getEntityType() != EntityType.FIREWORK_ROCKET) {
            return;
        }
        if (!(event.getEntity().getShooter() instanceof Player player)) {
            return;
        }

        if (!isInsideRestrictedRegion(player.getLocation(), "Rockets-Disable")) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onWindChargeInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.WIND_CHARGE) {
            return;
        }

        Player player = event.getPlayer();
        if (!isInsideRestrictedRegion(player.getLocation(), "Wind-Charge-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("wind-charge-disabled", "&cWind Charges are disabled in this area.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onWindChargeLaunch(ProjectileLaunchEvent event) {
        if (event.getEntityType() != EntityType.WIND_CHARGE) {
            return;
        }
        if (!(event.getEntity().getShooter() instanceof Player player)) {
            return;
        }

        if (!isInsideRestrictedRegion(player.getLocation(), "Wind-Charge-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("wind-charge-disabled", "&cWind Charges are disabled in this area.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onSpearInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.TRIDENT) {
            return;
        }

        Player player = event.getPlayer();
        if (!isInsideRestrictedRegion(player.getLocation(), "Spear-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("spear-disabled", "&cTridents are disabled in this area.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onSpearLaunch(ProjectileLaunchEvent event) {
        if (event.getEntityType() != EntityType.TRIDENT) {
            return;
        }
        if (!(event.getEntity().getShooter() instanceof Player player)) {
            return;
        }

        if (!isInsideRestrictedRegion(player.getLocation(), "Spear-Disable")) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onSpearMeleeHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) {
            return;
        }

        ItemStack held = player.getInventory().getItemInMainHand();
        if (held.getType() != Material.TRIDENT) {
            return;
        }

        if (!isInsideRestrictedRegion(player.getLocation(), "Spear-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("spear-disabled", "&cTridents are disabled in this area.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onElytraToggle(EntityToggleGlideEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        if (!event.isGliding()) {
            return;
        }

        if (!isInsideRestrictedRegion(player.getLocation(), "Elytra-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("elytra-disabled", "&cElytra gliding is disabled in this area.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onElytraGlideMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.isGliding()) {
            return;
        }

        if (!isInsideRestrictedRegion(player.getLocation(), "Elytra-Disable")) {
            return;
        }

        player.setGliding(false);
        Component msg = plugin.getMessage("elytra-disabled", "&cElytra gliding is disabled in this area.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onFishingRodPull(PlayerFishEvent event) {
        PlayerFishEvent.State state = event.getState();
        if (state != PlayerFishEvent.State.CAUGHT_ENTITY
                && state != PlayerFishEvent.State.REEL_IN
                && state != PlayerFishEvent.State.IN_GROUND) {
            return;
        }

        Player angler = event.getPlayer();
        FishHook hook = event.getHook();
        Entity caught = event.getCaught();

        boolean anglerRestricted = isInsideRestrictedRegion(angler.getLocation(), "Fishing-Rod-Pull-Disable");
        boolean victimRestricted = (caught != null)
                && isInsideRestrictedRegion(caught.getLocation(), "Fishing-Rod-Pull-Disable");

        if (!anglerRestricted && !victimRestricted) {
            return;
        }

        event.setCancelled(true);

        if (!hook.isDead()) {
            hook.remove();
        }

        if (caught != null) {
            caught.setVelocity(new Vector(0, 0, 0));
        }

        Component msg = plugin.getMessage("fishing-rod-disabled", "&cFishing-rod pulling is disabled here.");
        angler.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEndCrystalPlace(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.END_CRYSTAL) {
            return;
        }

        if (event.getClickedBlock() == null) {
            return;
        }
        Material surface = event.getClickedBlock().getType();
        if (surface != Material.OBSIDIAN && surface != Material.BEDROCK) {
            return;
        }

        Player player = event.getPlayer();
        if (!isInsideRestrictedRegion(player.getLocation(), "End-Crystal-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("end-crystal-disabled", "&cEnd Crystals are disabled in this area.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEndCrystalDamage(EntityDamageByEntityEvent event) {
        if (event.getEntityType() != EntityType.END_CRYSTAL) {
            return;
        }

        Player player = resolvePlayerDamager(event.getDamager());
        if (player == null) {
            return;
        }

        if (!isInsideRestrictedRegion(event.getEntity().getLocation(), "End-Crystal-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("end-crystal-disabled", "&cEnd Crystals are disabled in this area.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onGoldenAppleConsume(PlayerItemConsumeEvent event) {
        Material type = event.getItem().getType();
        if (type != Material.GOLDEN_APPLE && type != Material.ENCHANTED_GOLDEN_APPLE) {
            return;
        }

        Player player = event.getPlayer();
        if (!isInsideRestrictedRegion(player.getLocation(), "Golden-Apple-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("golden-apple-disabled", "&cGolden Apples are disabled in this area.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onSplashPotionLaunch(ProjectileLaunchEvent event) {
        if (!(event.getEntity() instanceof ThrownPotion)) {
            return;
        }
        if (!(event.getEntity().getShooter() instanceof Player player)) {
            return;
        }

        if (!isInsideRestrictedRegion(player.getLocation(), "Splash-Potion-Disable")) {
            return;
        }

        event.setCancelled(true);
        Component msg = plugin.getMessage("splash-potion-disabled", "&cSplash Potions are disabled in this area.");
        player.sendActionBar(msg);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPotionSplash(PotionSplashEvent event) {
        Location splashLoc = event.getEntity().getLocation();
        if (!isInsideRestrictedRegion(splashLoc, "Splash-Potion-Disable")) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onLingeringPotionSplash(LingeringPotionSplashEvent event) {
        Location splashLoc = event.getEntity().getLocation();
        if (!isInsideRestrictedRegion(splashLoc, "Splash-Potion-Disable")) {
            return;
        }

        event.setCancelled(true);
    }

    private Player resolvePlayerDamager(Entity damager) {
        if (damager instanceof Player p) {
            return p;
        }
        if (damager instanceof Projectile projectile && projectile.getShooter() instanceof Player p) {
            return p;
        }
        return null;
    }
}
