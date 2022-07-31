package dungeonmania.factory;

import dungeonmania.behaviours.logicalrules.AndLogic;
import dungeonmania.behaviours.logicalrules.CoAndLogic;
import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.behaviours.logicalrules.OrLogic;
import dungeonmania.behaviours.logicalrules.XorLogic;

public class LogicFactory {
    public static LogicRules getLogicType(String logicType) {
        switch (logicType) {
            case "or":
                return new OrLogic();
            case "and":
                return new AndLogic();
            case "co_and":
                return new CoAndLogic();
            case "xor":
                return new XorLogic();
            default:
                return null;
        }
    }
}
