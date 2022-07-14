package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.staticobject.staticbomb.SwitchObserver;

public interface SwitchSubject {

    public void add(SwitchObserver observer);

    public void notifySwitchObservers();
}
