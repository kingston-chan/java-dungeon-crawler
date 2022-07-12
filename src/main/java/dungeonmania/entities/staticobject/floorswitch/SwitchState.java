package dungeonmania.entities.staticobject.floorswitch;

public interface SwitchState {
    
    public void activate();

    public void deactivate();

    public boolean isActivated();
}
