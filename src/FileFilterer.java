import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
public class FileFilterer {




    public ArrayList<String> filterFile(Path filterFile, String filterString)
    {
        ArrayList<String> fileLines = new ArrayList<String>();
        try (Stream<String> lines = Files.lines(filterFile))
        {
            lines.forEach(l ->
            {
                if (l.contains(filterString)) {
                    fileLines.add(l);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLines;
    }
}
