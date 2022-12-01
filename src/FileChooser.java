import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileChooser {



    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File chosenFile;
        String rec;


        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        try {
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                chosenFile = chooser.getSelectedFile();
                Path file = chosenFile.toPath();
                DisplayGUI display = new DisplayGUI();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                while (reader.ready()) {
                    rec = reader.readLine();
                    display.writeToTextBox(rec);
                }
                display.fileToFilter = file;
                reader.close(); // must close the file to seal it and flush buffer

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}