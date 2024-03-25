package GUI;

import conversion.NumberConversionServiceImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Locale;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Locale;

public class Main extends JFrame {
    private OperationPanel operationPanel;
    private OperationController operationController;

    public Main() {
        super("Calculator");
        initialize();
    }

    private void initialize() {
        Locale defaultLocale = Locale.getDefault();
        LanguageManager.setLocale(defaultLocale);

        // Set layout to BorderLayout for the main frame
        getContentPane().setLayout(new BorderLayout());

        // Create a JPanel for the top section containing the text
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); // Add some padding

        TextArea englishReadyTextArea = new TextArea();
        englishReadyTextArea.setText("Some ready to use numbers as plain text:\n" + "one thousand fourteen\n"
                + "eight hundred ninety seven million four hundred fifty three thousand two hundred ten\n"
                + "seventy three thousand two hundred\n" + "six thousand seven hundred eighty nine\n"
                + "three trillion two hundred forty five billion six hundred seventy eight million nine hundred one thousand two hundred thirty four\n"
                + "sixty nine million five hundred forty one thousand three hundred twenty two\n");

        TextArea turkishReadyTextArea = new TextArea();
        turkishReadyTextArea.setText("Kullanmaya hazır bazı sayı metinleri:\n" + "bir bin on dört\n"
                + "sekiz yüz doksan yedi milyon dört yüz elli üç bin iki yüz on\n" + "yetmiş üç bin iki yüz\n"
                + "altı bin yedi yüz seksen dokuz\n"
                + "üç trilyon iki yüz kırk beş milyar altı yüz yetmiş sekiz milyon dokuz yüz bir bin iki yüz otuz dört\n"
                + "altmış dokuz milyon beş yüz kırık bir bin üç yüz yirmi iki\n");

        // Add the label to the top panel
        topPanel.add(englishReadyTextArea, BorderLayout.WEST);
        topPanel.add(turkishReadyTextArea, BorderLayout.EAST);

        // Add the top panel to the main frame
        getContentPane().add(topPanel, BorderLayout.NORTH);

        // Set size to a specific width and height
        setSize(1200, 800); // Set width to 800 and height to 600

        // Center the frame on the screen
        setLocationRelativeTo(null);

        operationController = new OperationController(new NumberConversionServiceImpl()); // Adjusted for dependency
        // injection
        operationPanel = new OperationPanel();

        // Add the operationPanel to the main frame
        getContentPane().add(operationPanel, BorderLayout.CENTER);
        addActionListeners();
    }

    private void addActionListeners() {
        operationPanel.getAddButton().addActionListener(this::actionPerformed);
        operationPanel.getSubtractButton().addActionListener(this::actionPerformed);
        operationPanel.getMultiplyButton().addActionListener(this::actionPerformed);
        operationPanel.getDivideButton().addActionListener(this::actionPerformed);
    }

    private void actionPerformed(ActionEvent e) {
        String num1Text = operationPanel.getNumber1Field().getText();
        String num2Text = operationPanel.getNumber2Field().getText();

        try {
            int languageIndex = operationPanel.getLanguageComboBox().getSelectedIndex();
            long num1 = LanguageManager.toNumber(num1Text, languageIndex);
            long num2 = LanguageManager.toNumber(num2Text, languageIndex);
            System.out.println("num1: " + num1 + ", num2: " + num2);
            
            long result;
            if (e.getSource() == operationPanel.getAddButton()) {
                result = num1 + num2;
            } else if (e.getSource() == operationPanel.getSubtractButton()) {
                result = num1 - num2;
            } else if (e.getSource() == operationPanel.getMultiplyButton()) {
                result = num1 * num2;
            } else if (e.getSource() == operationPanel.getDivideButton()) {
                result = num1 / num2;
            } else {
                throw new UnsupportedOperationException("Unsupported operation");
            }

            String resultText = LanguageManager.toText(result, languageIndex);
            operationPanel.getResultTextArea().setText(LanguageManager.getString("result") + ": " + resultText);
        } catch (Exception ex) {
            operationPanel.getResultTextArea().setText(LanguageManager.getString("error") + ": " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}
