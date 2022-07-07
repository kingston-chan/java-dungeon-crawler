package dungeonmania;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class DungeonManiaController {
    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    /**
     * /dungeons
     */
    public static List<String> dungeons() {
        return FileLoader.listFileNamesInResourceDirectory("dungeons");
    }

    /**
     * /configs
     */
    public static List<String> configs() {
        return FileLoader.listFileNamesInResourceDirectory("configs");
    }

    /**
     * /game/new
     */
    public DungeonResponse newGame(String dungeonName, String configName) throws IllegalArgumentException {
        try{
            JSONObject resource = new JSONObject(FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json"));
            JSONArray array = resource.getJSONArray("entities");

            for(int i = 0; i < array.length(); i++){
                JSONObject a = array.getJSONObject(i);
                Integer x = (Integer) a.get("x");
                Integer y = (Integer) a.get("y");
                String type = (String) a.get("type");
                // System.out.println(x + " " + y + " " + type);
                // this should be added in some function
            }

            JSONObject goal = resource.getJSONObject("goal-condition");
            String hello = getgoal(goal);
            System.out.println(hello);
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }

        return null;
    }
    // helper fucntion(recursive) for reading goals
    public String getgoal(JSONObject goal){
        String all_goals = "";
        if (goal.getString("goal").equals("AND")){
            JSONArray iterate = goal.getJSONArray("subgoals");
            for (int i = 0; i < iterate.length(); i++){
                all_goals += getgoal((JSONObject) iterate.get(i));
            }
        } else if (goal.getString("goal").equals("OR")){
            JSONArray iterate = goal.getJSONArray("subgoals");
            for (int i = 0; i < iterate.length(); i++){
                all_goals += getgoal((JSONObject) iterate.get(i));
                all_goals += " or ";
            }
        }
        else{
            all_goals += ":" + goal.getString("goal");
        }

        return all_goals;
    }

    /**
     * /game/dungeonResponseModel
     */
    public DungeonResponse getDungeonResponseModel() {
        return null;
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        return null;
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }
}
