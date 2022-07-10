package dungeonmania.entities;

import dungeonmania.behaviours.host.HostBehaviour;
import dungeonmania.util.Position;

public abstract class DungeonObject {
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

    public abstract boolean isInteractable();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DungeonObject other = (DungeonObject) obj;

        return other.getUniqueId().equals(getUniqueId());
    }
}
