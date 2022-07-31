package dungeonmania.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import dungeonmania.entities.Dungeon;

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
     } catch (IOException i) {
        System.out.println("ERROR");
        i.printStackTrace();
     }
  }

  public static List<String> getAllGames() {
    Reflections reflections = new Reflections("dungeonSaves", Scanners.Resources);
    return reflections.getResources(".*\\.ser")
            .stream()
            .map(s -> s.replace("dungeonSaves/", "").replace(".ser", ""))
            .collect(Collectors.toList());
  }
}
