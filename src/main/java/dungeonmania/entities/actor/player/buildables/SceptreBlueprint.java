package dungeonmania.entities.actor.player.buildables;

import dungeonmania.entities.actor.player.Player;

public class SceptreBlueprint implements BuildableBlueprint {
    private static final int NUM_WOOD = 1;
    private static final int NUM_ARROW = 1;

    private static final int NUM_TREASURES = 1;
    private static final int NUM_KEYS = 1;

    private static final int NUM_SUNSTONE = 1;
    private static final String ITEM_TYPE = "sceptre";

    private void build_with_key(){

    }

    private void build_with_arrow(){

    }

    private void build_with_wood(){

    }

    private void build_with_treasure(){

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
