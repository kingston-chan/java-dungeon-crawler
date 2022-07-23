package dungeonmania.entities;

public interface TickCost {
    default int tickCost() {
        return 0;
    }
}
