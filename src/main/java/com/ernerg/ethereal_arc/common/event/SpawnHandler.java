package com.ernerg.ethereal_arc.common.event;

import com.ernerg.ethereal_arc.common.EtherealArc;
import com.ernerg.ethereal_arc.common.system.HostilityAnnihilatorManager;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent.PositionCheck.Result;

@EventBusSubscriber(modid = EtherealArc.MOD_ID)
public class SpawnHandler {
	
	@SubscribeEvent
	public static void onCheckSpawn(MobSpawnEvent.PositionCheck event) {
		Entity entity = event.getEntity();
		Level level = (Level) event.getLevel();

		if (!(entity instanceof Monster)) return;

		BlockPos pos = entity.blockPosition();

		if (HostilityAnnihilatorManager.isInSpawnZone(pos)) {
			event.setResult(Result.FAIL);

			if (!level.isClientSide) {
				for (ServerPlayer player : level.getServer().getPlayerList().getPlayers()) {
					player.sendSystemMessage(Component.literal("SPAWN PREVENTED"));
				}
			}
		}
	}
}
