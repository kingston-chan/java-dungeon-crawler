package dungeonmania.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import dungeonmania.entities.Dungeon;
import dungeonmania.response.models.DungeonResponse;

public class MapStoring {
  public static Dungeon loadDungeon(String name) throws IllegalArgumentException {
    Dungeon loadedDungeon = null;
    try {
        FileInputStream fileIn = new FileInputStream("src/main/resources/dungeonSaves/" + name + ".ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        loadedDungeon = (Dungeon) in.readObject();
        in.close();
        fileIn.close();
    } catch (FileNotFoundException e) {
        throw new IllegalArgumentException();
    } catch (IOException i) {
        i.printStackTrace();
    } catch (ClassNotFoundException c) {
        c.printStackTrace();
    }  

    return loadedDungeon;
  }

  public static void saveDungeon(String name, Dungeon dungeon) throws IllegalArgumentException {
    try {
        FileOutputStream fileOut = new FileOutputStream("src/main/resources/dungeonSaves/" + name + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(dungeon);
        out.close();
        fileOut.close();
     } catch (IllegalArgumentException i) {
        System.out.println("NOT EXIST");
     } catch (IOException i) {
        System.out.println("ERROR");
        i.printStackTrace();
     }
  }

  public static List<String> getAllGames() {
    return FileLoader.listFileNamesInResourceDirectory("dungeonSaves");
  }
}
