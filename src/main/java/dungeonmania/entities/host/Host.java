package dungeonmania.entities.host;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.boulder.Boulder;

public interface Host {
	default boolean canAccept(Player player) {
		return true;
	}

	default boolean canAccept(NonPlayableActor entity) {
		return true;
	}

	default boolean canAccept(Boulder boulder) {
		return true;
	}

	default void doAccept(Player player) {
	}

	default void doAccept(NonPlayableActor entity) {
	}

	default void doAccept(Boulder boulder) {
	}
}
