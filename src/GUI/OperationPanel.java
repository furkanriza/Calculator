package GUI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

public class OperationPanel extends JPanel {

    private JTextField number1Field;
    private JTextField number2Field;
    private JButton addButton, subtractButton, multiplyButton, divideButton;
    private JTextArea resultTextArea; // Change resultLabel to JTextArea
    private JComboBox<String> languageComboBox;

    public OperationPanel() {
        setLayout(new GridLayout(6, 2)); // Simple grid layout
        
        // Define padding
        int top = 100;
        int left = 100;
        int bottom = 100;
        int right = 100;
        setBorder(new EmptyBorder(top, left, bottom, right));

        number1Field = new JTextField();
        number2Field = new JTextField();
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        resultTextArea = new JTextArea();
        languageComboBox = new JComboBox<>(new String[]{"English", "Turkish"});
        add(new JLabel("Language:"));
        add(languageComboBox);
        add(new JLabel("Number 1:"));
        add(number1Field);
        add(new JLabel("Number 2:"));
        add(number2Field);
        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(resultTextArea);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getSubtractButton() {
        return subtractButton;
    }

    public JButton getMultiplyButton() {
        return multiplyButton;
    }

    public JButton getDivideButton() {
        return divideButton;
    }

    // Getter methods for input fields and result label
    public JTextField getNumber1Field() {
        return number1Field;
    }

    public JTextField getNumber2Field() {
        return number2Field;
    }

    public JTextArea getResultTextArea() { // Change return type to JTextArea
        return resultTextArea;
    }

    public JComboBox<String> getLanguageComboBox() {
        return languageComboBox;
    }
}
