package dungeonmania.entities.staticobject.floorswitch;

public interface SwitchState extends java.io.Serializable {

    public boolean activate();

    public boolean deactivate();

    public boolean isSwitchActivated();
}
