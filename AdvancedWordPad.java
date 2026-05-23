import javax.swing.*; 
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AdvancedWordPad {

    JFrame frame;
    JTextPane textPane;

    public AdvancedWordPad() {
        frame = new JFrame("Advanced WordPad - Tanishq Agrawal | Reg No: 23BCE10623");
        textPane = new JTextPane();

        JScrollPane scrollPane = new JScrollPane(textPane);
        frame.add(scrollPane);

        createMenuBar();

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // FILE MENU
        JMenu fileMenu = new JMenu("File");

        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");

        newFile.addActionListener(e -> textPane.setText(""));
        openFile.addActionListener(e -> openFile());
        saveFile.addActionListener(e -> saveFile());

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);

        // EDIT MENU
        JMenu editMenu = new JMenu("Edit");

        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");

        cut.addActionListener(e -> textPane.cut());
        copy.addActionListener(e -> textPane.copy());
        paste.addActionListener(e -> textPane.paste());

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        // FORMAT MENU
        JMenu formatMenu = new JMenu("Format");

        JMenuItem fontSize = new JMenuItem("Change Font Size");
        JMenuItem textColor = new JMenuItem("Change Text Color");
        JMenuItem bold = new JMenuItem("Bold");
        JMenuItem italic = new JMenuItem("Italic");
        JMenuItem bgColor = new JMenuItem("Background Color");
        JMenuItem upperCase = new JMenuItem("Convert to UPPERCASE");
        JMenuItem lowerCase = new JMenuItem("Convert to lowercase");

        fontSize.addActionListener(e -> changeFontSize());
        textColor.addActionListener(e -> changeTextColor());
        bold.addActionListener(e -> setBold());
        italic.addActionListener(e -> setItalic());
        bgColor.addActionListener(e -> changeBackgroundColor());
        upperCase.addActionListener(e -> convertToUpperCase());
        lowerCase.addActionListener(e -> convertToLowerCase());

        formatMenu.add(fontSize);
        formatMenu.add(textColor);
        formatMenu.add(bold);
        formatMenu.add(italic);
        formatMenu.add(bgColor);
        formatMenu.add(upperCase);
        formatMenu.add(lowerCase);

        // ADD MENUS
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);

        frame.setJMenuBar(menuBar);
    }

    // FILE OPERATIONS
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(frame);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                textPane.read(br, null);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error opening file");
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(frame);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                textPane.write(bw);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error saving file");
            }
        }
    }

    // FORMAT FEATURES
    private void changeFontSize() {
        String sizeStr = JOptionPane.showInputDialog("Enter Font Size:");

        try {
            int size = Integer.parseInt(sizeStr);

            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setFontSize(attr, size);

            doc.setCharacterAttributes(
                    textPane.getSelectionStart(),
                    textPane.getSelectionEnd() - textPane.getSelectionStart(),
                    attr,
                    false
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid size");
        }
    }

    private void changeTextColor() {
        Color color = JColorChooser.showDialog(frame, "Choose Text Color", Color.BLACK);

        if (color != null) {
            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setForeground(attr, color);

            doc.setCharacterAttributes(
                    textPane.getSelectionStart(),
                    textPane.getSelectionEnd() - textPane.getSelectionStart(),
                    attr,
                    false
            );
        }
    }

    private void setBold() {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBold(attr, true);

        doc.setCharacterAttributes(
                textPane.getSelectionStart(),
                textPane.getSelectionEnd() - textPane.getSelectionStart(),
                attr,
                false
        );
    }

    private void setItalic() {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setItalic(attr, true);

        doc.setCharacterAttributes(
                textPane.getSelectionStart(),
                textPane.getSelectionEnd() - textPane.getSelectionStart(),
                attr,
                false
        );
    }

    private void changeBackgroundColor() {
        Color color = JColorChooser.showDialog(frame, "Choose Background Color", Color.WHITE);

        if (color != null) {
            textPane.setBackground(color);
        }
    }

    // NEW FEATURES
    private void convertToUpperCase() {
        String selectedText = textPane.getSelectedText();

        if (selectedText != null) {
            textPane.replaceSelection(selectedText.toUpperCase());
        }
    }

    private void convertToLowerCase() {
        String selectedText = textPane.getSelectedText();

        if (selectedText != null) {
            textPane.replaceSelection(selectedText.toLowerCase());
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdvancedWordPad::new);
    }
}