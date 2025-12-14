package simsool;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.PacketPlayOutTransaction;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info("ServerTickPacketSender Enabled");
		new BukkitRunnable() {
			@Override
			public void run() {
				sendPacketToAllPlayers();
			}
		}.runTaskTimer(this, 0L, 1L);
	}

	@Override
	public void onDisable() {
		getLogger().info("ServerTickPacketSender Disabled");
	}

	private void sendPacketToAllPlayers() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			sendConfirmTransactionPacket(player);
		}
	}

	private void sendConfirmTransactionPacket(Player player) {
		PacketPlayOutTransaction packet = new PacketPlayOutTransaction(0, (short) 0, true);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
}