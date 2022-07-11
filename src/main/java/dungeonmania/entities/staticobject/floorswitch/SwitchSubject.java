package main.java.dungeonmania.entities.staticobject.floorswitch;

import main.java.dungeonmania.entities.SwitchObserver;

public interface SwitchSubject {
    
    public void add(SwitchObserver observer);

    public void notifySwitchObservers();
}
