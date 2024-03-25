package GUI;

import operation.OperationService;
import operation.OperationServiceImpl;
import conversion.NumberConversionService;
import conversion.NumberConversionServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationController {

	private final OperationPanel operationPanel;
	private OperationService operationService;

	public OperationController(NumberConversionService numberConversionService) {
		this.operationPanel = new OperationPanel();
		this.operationService = new OperationServiceImpl(numberConversionService);
	}

	public OperationController(OperationPanel operationPanel) {
		this.operationPanel = operationPanel;
		this.operationService = new OperationServiceImpl(new NumberConversionServiceImpl());

		// Setup listeners for the operation buttons
		setupListeners();
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
			//operationPanel.getResultLabel().setText("Result: " + result);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return "Error: " + ex.getMessage();
		}
	}

	private void changeLanguage() {
		// Example method to handle language change.
		// Actual implementation would need to update all text fields and labels in the
		// application.
	}
}
