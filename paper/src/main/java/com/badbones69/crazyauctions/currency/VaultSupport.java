package com.badbones69.crazyauctions.currency;

import com.badbones69.crazyauctions.CrazyAuctions;
import com.github.adminoid.vault.economy.Economy;
import com.github.adminoid.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class VaultSupport {

    private final CrazyAuctions plugin = CrazyAuctions.get();

    private Economy vault = null;

    public Economy getVault() {
        return this.vault;
    }

    public boolean setupEconomy() {
        RegisteredServiceProvider<Economy> serviceProvider = this.plugin.getServer().getServicesManager().getRegistration(Economy.class);

        if (serviceProvider != null) this.vault = serviceProvider.getProvider();

        return this.vault != null;
    }

    public BigDecimal getMoney(@NotNull Player player) {
        return (BigDecimal) this.vault.getBalance(player);
    }

    public boolean removeMoney(@NotNull Player player, BigDecimal amount) {
        EconomyResponse result = this.vault.withdrawPlayer(player, amount);
        return result.transactionSuccess();
    }

    public boolean removeMoney(@NotNull OfflinePlayer player, BigDecimal amount) {
        EconomyResponse result = this.vault.withdrawPlayer(player, amount);
        return result.transactionSuccess();
    }

    public boolean addMoney(Player player, BigDecimal amount) {
        EconomyResponse result = this.vault.depositPlayer(player, amount);
        return result.transactionSuccess();
    }

    public boolean addMoney(OfflinePlayer player, BigDecimal amount) {
        EconomyResponse result = this.vault.depositPlayer(player, amount);
        return result.transactionSuccess();
    }
}