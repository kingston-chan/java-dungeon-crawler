package dungeonmania.behaviours.host;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.boulder.Boulder;

public interface HostBehaviour {
  default boolean accept(Player player) {
    return true;
  }

  default boolean accept(NonPlayableActor entity) {
    return true;
  }
  
  default boolean accept(Boulder boulder) {
    return true;
  }
}
