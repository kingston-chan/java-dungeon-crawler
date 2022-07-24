package dungeonmania.entities.actor.player.buildables;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;


public class MidnightArmourBlueprint implements BuildableBlueprint{
    private static final int NUM_SWORD = 1;
    private static final int NUM_SUNSTONE = 1;
    private static final String ITEM_TYPE = "midnight_armour";

    private Item CreateNewMidnightArmour(){
        return null;
    }

    private boolean check_zombies_existence(){
        return false;
    }

    @Override
    public boolean canPlayerBuild(Player player) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void playerBuild(Player player) {
        // TODO Auto-generated method stub
        
    }
    
}
