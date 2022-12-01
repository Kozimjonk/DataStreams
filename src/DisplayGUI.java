import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class DisplayGUI extends JFrame {

    JFrame frame = new JFrame();
    JPanel mainPnl;
    JPanel topPnl;
    JPanel midPnl;
    JPanel botPnl;

    JLabel titleLbl;
    JTextArea origFileTxt;
    JTextArea filtFileTxt;

    JTextArea filterTxtInput;
    JLabel txtLbl;
    JScrollPane scroller;
    JScrollPane scroller2;

    JButton filterButton;
    JButton quitButton;

    Path fileToFilter;

    ArrayList<String> filesToFilter = new ArrayList<String>();

    public DisplayGUI() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        createTopPanel();
        mainPnl.add(topPnl, BorderLayout.NORTH);
        createMiddlePanel();
        mainPnl.add(midPnl, BorderLayout.CENTER);
        createBottomPanel();
        mainPnl.add(botPnl, BorderLayout.SOUTH);

        add(mainPnl);

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPanel() {
        topPnl = new JPanel();
        topPnl.setLayout(new GridLayout(3, 0));
        titleLbl = new JLabel("File Filter", JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.TOP);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 50));

        txtLbl = new JLabel("Filter Text:", JLabel.CENTER);
        txtLbl.setVerticalTextPosition(JLabel.BOTTOM);
        txtLbl.setHorizontalTextPosition(JLabel.CENTER);
        txtLbl.setFont(new Font("SansSerif", Font.BOLD, 10));
        filterTxtInput = new JTextArea(1, 10);
        topPnl.add(titleLbl);
        topPnl.add(txtLbl);
        topPnl.add(filterTxtInput);
    }

    private void createMiddlePanel() {
        midPnl = new JPanel();
        midPnl.setLayout(new GridLayout(1,2));
        origFileTxt = new JTextArea(20, 60);
        filtFileTxt = new JTextArea(20, 60);
        scroller = new JScrollPane(origFileTxt);
        scroller2 = new JScrollPane(filtFileTxt);
        midPnl.add(scroller);
        midPnl.add(scroller2);
    }

    private void createBottomPanel() {
        botPnl = new JPanel();
        botPnl.setLayout(new GridLayout(1, 2));
        filterButton = new JButton("filter");
        quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));

        filterButton.addActionListener((ActionEvent ae) -> {
            filtFileTxt.setText("");
            FileFilterer fileFilt = new FileFilterer();
            ArrayList<String> filteredLines = new ArrayList<String>();
            filteredLines = fileFilt.filterFile(fileToFilter, filterTxtInput.getText());
            for(int a = 0; a < filteredLines.toArray().length; a++)
            {
                writeToTextBox2(filteredLines.get(a) + "\n");
            }
        });

        botPnl.add(filterButton);
        botPnl.add(quitButton);
    }

    public void writeToTextBox(String line)
    {
        origFileTxt.append(line + "\n");
        filesToFilter.add(line + "\n");
    }
    public void writeToTextBox2(String line)
    {
        filtFileTxt.append(line + "\n");
    }
}