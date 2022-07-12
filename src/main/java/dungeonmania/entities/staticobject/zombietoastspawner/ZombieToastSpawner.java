package dungeonmania.entities.staticobject.zombietoastspawner;

import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;

public class ZombieToastSpawner extends StaticObject {
    @Override
    public boolean isInteractable() {
        return false;
    }

    public boolean accept(Boulder boulder) {
        return false;
    }
    
    public void updateSpawnRate() {
        //idk what this does either
    }

}
