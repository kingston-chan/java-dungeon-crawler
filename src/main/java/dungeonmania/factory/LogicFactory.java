package dungeonmania.factory;

import dungeonmania.behaviours.logicalrules.And;
import dungeonmania.behaviours.logicalrules.CoAnd;
import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.behaviours.logicalrules.Or;
import dungeonmania.behaviours.logicalrules.Xor;

public class LogicFactory {
    public static LogicRules getLogicType(String logicType) {
        switch (logicType) {
            case "or":
                return new Or();
            case "and":
                return new And();
            case "co_and":
                return new CoAnd();
            case "xor":
                return new Xor();
            default:
                return null;
        }
    }
}
