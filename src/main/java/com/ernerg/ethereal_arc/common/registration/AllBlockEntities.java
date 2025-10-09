package com.ernerg.ethereal_arc.common.registration;

import java.util.function.Supplier;

import com.ernerg.ethereal_arc.common.EtherealArc;
import com.ernerg.ethereal_arc.common.entitiy.HostilityAnnihilatorBlockEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllBlockEntities {
	
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
		BuiltInRegistries.BLOCK_ENTITY_TYPE, EtherealArc.MOD_ID
	);

	public static final Supplier<BlockEntityType<HostilityAnnihilatorBlockEntity>> HOSTILITY_ANNIHILATOR_BE =
		BLOCK_ENTITIES.register(
			"hostility_annihilator_be",
			() -> BlockEntityType.Builder.of(
				HostilityAnnihilatorBlockEntity::new,
				AllBlocks.HOSTILITY_ANNIHILATOR.get())
			.build(null)
		);

	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}
}
