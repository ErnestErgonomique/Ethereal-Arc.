package com.ernerg.ethereal_arc.common.registration;

import com.ernerg.ethereal_arc.common.EtherealArc;
import com.ernerg.ethereal_arc.common.menu.HostilityAnnihilatorMenu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllMenuTypes {
	
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(
		Registries.MENU, EtherealArc.MOD_ID
	);

	public static final DeferredHolder<MenuType<?>, MenuType<HostilityAnnihilatorMenu>> HOSTILITY_ANNIHILATOR_MENU =
		registerMenuType("hostility_annihilator_menu", HostilityAnnihilatorMenu::new);

	private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(
		String name, IContainerFactory<T> factory
	) {
		return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
	}

	public static void register(IEventBus eventBus) {
		MENUS.register(eventBus);
	}
}
