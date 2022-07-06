package dungeonmania.entities;

import dungeonmania.behaviours.host.HostBehaviour;
import dungeonmania.util.Position;

public class DungeonObject {
    private HostBehaviour hostBehaviour;
    private Position position;
    private String uniqueId;
    private String type;

    public void setHostBehaviour(HostBehaviour hostBehaviour) {
        this.hostBehaviour = hostBehaviour;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
