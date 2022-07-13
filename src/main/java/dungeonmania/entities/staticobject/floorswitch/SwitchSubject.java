package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.SwitchObserver;

public interface SwitchSubject {
    
    public void add(SwitchObserver observer);

    public void remove(SwitchObserver observer);

    public void notifySwitchObservers();
}
