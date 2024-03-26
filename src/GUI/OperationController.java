package GUI;

import javax.swing.*;
import conversion.NumberConversionService;
import operation.OperationService;
import operation.OperationServiceImpl;
import java.util.Locale;
import java.util.ResourceBundle;

public class OperationController {

    private static OperationPanel operationPanel;
    private OperationService operationService;

    public OperationController(NumberConversionService numberConversionService) {
        this.operationPanel = new OperationPanel();
        this.operationService = new OperationServiceImpl(numberConversionService);
        setupListeners();
    }

    static void changeLanguage(int languageIndex) {
    	ResourceBundle messages;
    	if (languageIndex == 1) {
    	    messages = ResourceBundle.getBundle("Messages", new Locale("tr"));
    	} else {
    	    messages = ResourceBundle.getBundle("Messages", new Locale("en"));
    	}
    	
    	Main.operationPanel.setAddButtonText(messages.getString("add"));
    	Main.operationPanel.setSubtractButtonText(messages.getString("subtract"));
    	Main.operationPanel.setMultiplyButtonText(messages.getString("multiply"));
    	Main.operationPanel.setDivideButtonText(messages.getString("divide"));
    	Main.operationPanel.setLanguageLabel(messages.getString("language"));
    	Main.operationPanel.setNumberLabel1(messages.getString("number") + " 1:");
    	Main.operationPanel.setNumberLabel2(messages.getString("number") + " 2:");
    
    }

    private void setupListeners() {
        operationPanel.getAddButton().addActionListener(e -> performOperationWithFetch("ADD"));
        operationPanel.getSubtractButton().addActionListener(e -> performOperationWithFetch("SUBTRACT"));
        operationPanel.getMultiplyButton().addActionListener(e -> performOperationWithFetch("MULTIPLY"));
        operationPanel.getDivideButton().addActionListener(e -> performOperationWithFetch("DIVIDE"));
    }

    private void performOperationWithFetch(String operationType) {
        String num1 = operationPanel.getNumber1Field().getText();
        String num2 = operationPanel.getNumber2Field().getText();
        String result = performOperation(num1, num2, operationType);
        operationPanel.getResultTextArea().setText("Result: " + result);
    }

    public String performOperation(String num1, String num2, String operationType) {
        try {
            return operationService.performOperation(num1, num2, operationType);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return "Error: " + ex.getMessage();
        }
    }
}
