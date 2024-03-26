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
    public static OperationPanel operationPanel;
    private OperationController operationController;

    public Main() {
        super("Calculator");
        initialize("Add", "Subtract", "Multiply", "Divide");
    }

    private void initialize(String addLabel, String subtractLabel, String multiplyLabel, String divideLabel) {
        Locale defaultLocale = Locale.getDefault();
        LanguageManager.setLocale(defaultLocale);
        
        getContentPane().setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); // Add some padding

        TextArea englishReadyTextArea = new TextArea();
        englishReadyTextArea.setText("Some ready to use numbers as plain text:\n" + "one thousand fourteen\n"
                + "eight hundred ninety seven million four hundred fifty three thousand two hundred ten\n"
                + "seventy three thousand two hundred\n" + "six thousand seven hundred eighty nine\n"
                + "two hundred forty five billion six hundred seventy eight million nine hundred one thousand two hundred thirty four\n"
                + "sixty nine million five hundred forty one thousand three hundred twenty two\n");

        TextArea turkishReadyTextArea = new TextArea();
        turkishReadyTextArea.setText("Kullanmaya hazır bazı sayı metinleri:\n" + "bir bin on dört\n"
                + "sekiz yüz doksan yedi milyon dört yüz elli üç bin iki yüz on\n" + "yetmiş üç bin iki yüz\n"
                + "altı bin yedi yüz seksen dokuz\n"
                + "iki yüz kırk beş milyar altı yüz yetmiş sekiz milyon dokuz yüz bir bin iki yüz otuz dört\n"
                + "altmış dokuz milyon beş yüz kırık bir bin üç yüz yirmi iki\n");

        topPanel.add(englishReadyTextArea, BorderLayout.WEST);
        topPanel.add(turkishReadyTextArea, BorderLayout.EAST);

        getContentPane().add(topPanel, BorderLayout.NORTH);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        OperationController operationController = new OperationController(new NumberConversionServiceImpl());

        operationPanel = new OperationPanel();
        
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
        int languageIndex = operationPanel.getLanguageComboBox().getSelectedIndex();

        try {
            
            long num1 = LanguageManager.toNumber(num1Text, languageIndex);
            long num2 = LanguageManager.toNumber(num2Text, languageIndex);
            System.out.print("num1: " + num1 + ", num2: " + num2);
            
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
            
            System.out.println(", result: " + result);

            String resultText = LanguageManager.toText(result, languageIndex);
            operationPanel.getResultTextArea().setText(LanguageManager.getString("result", languageIndex) + ": " + resultText);
        } catch (Exception ex) {
            operationPanel.getResultTextArea().setText(LanguageManager.getString("error", languageIndex) + ": " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}
