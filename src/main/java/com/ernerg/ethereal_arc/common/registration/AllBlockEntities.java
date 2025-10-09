package com.ernerg.ethereal_arc.common.registration;

import com.ernerg.ethereal_arc.common.EtherealArc;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllBlockEntities {
	
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
		BuiltInRegistries.BLOCK_ENTITY_TYPE, EtherealArc.MOD_ID
	);

	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}
}
