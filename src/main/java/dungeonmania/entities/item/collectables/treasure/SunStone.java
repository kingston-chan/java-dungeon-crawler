package dungeonmania.entities.item.collectables.treasure;

import javassist.bytecode.stackmap.Tracer;

public class SunStone extends Treasure {
    
    @Override
    public boolean isBribableCurrency() {
        return false;
    }
}
