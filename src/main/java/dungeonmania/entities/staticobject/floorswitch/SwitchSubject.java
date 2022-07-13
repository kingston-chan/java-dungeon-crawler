package dungeonmania.entities.staticobject.floorswitch;

<<<<<<< HEAD
import dungeonmania.entities.staticobject.staticbomb.SwitchObserver;

public interface SwitchSubject {

    public void add(SwitchObserver observer);

    public void remove(SwitchObserver observer);

=======
import dungeonmania.entities.SwitchObserver;

public interface SwitchSubject {
    
    public void add(SwitchObserver observer);

>>>>>>> a27c47dd9425e0398f4397a78ef321e5db8fd525
    public void notifySwitchObservers();
}
