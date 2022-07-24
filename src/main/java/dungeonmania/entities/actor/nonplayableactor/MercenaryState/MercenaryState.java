package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.behaviours.movement.MovementBehaviour;

public interface MercenaryState extends  java.io.Serializable {

    public boolean canInteract();

    public void updateMovement(MovementBehaviour movementBehaviour);

    public boolean isAlly();

    public void recruit();

    public void mindcontrol();
}
