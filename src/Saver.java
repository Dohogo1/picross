import java.io.*;

/**
 *
 */
public class Saver {

    /**
     *
     * @param fileName
     * @param input
     * @param solution
     */
    public static void save(String fileName, Grid input, Grid solution) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(new Grid[] {input,solution});
        }catch (IOException e ) {
            System.out.println("invalid file");
        }
    }

    /**
     *
     * @param fileName
     * @return
     */
    public static Grid[] load(String fileName) {
        try{
            ObjectInputStream in = new ObjectInputStream( new FileInputStream(fileName));
            return (Grid[]) in.readObject();
        }catch(IOException | ClassNotFoundException e){
            System.out.println("invalid file");
        }
        return null;
    }
}
