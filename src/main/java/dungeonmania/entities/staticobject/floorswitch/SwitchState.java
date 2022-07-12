package dungeonmania.entities.staticobject.floorswitch;

public interface SwitchState {
    
    public boolean activate();

    public boolean deactivate();

    public boolean isActivated();
}
