package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.entities.actor.player.Player;

public interface MercenaryState extends java.io.Serializable {
    public boolean canInteract();

    public boolean isAlly();

    public void recruitedBy(Player player);

    public void mindcontrol();

    public int bribeAmount();

    public void visitInvisiblePlayer(Player player);

    public boolean isAssassin();

    public void movePlayerIsNormal();

    public void movePlayerIsInvincible();

    public void movePlayerIsInvisible();
}
