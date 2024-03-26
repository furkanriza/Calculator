package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.Locale;
import java.util.ResourceBundle;

public class OperationPanel extends JPanel {

	private JTextField number1Field;
	private JTextField number2Field;
	private JButton addButton, subtractButton, multiplyButton, divideButton;
	private JTextArea resultTextArea;
	private JComboBox<String> languageComboBox;
	private JLabel languageLabel, numberLabel1, numberLabel2;

	public OperationPanel() {
		ResourceBundle messages;
		if (Locale.getDefault().getLanguage().equals("tr")) {
			messages = ResourceBundle.getBundle("Messages", new Locale("tr"));
		} else {
			messages = ResourceBundle.getBundle("Messages", new Locale("en"));
		}
		setLayout(new GridLayout(6, 2));
		setBorder(new EmptyBorder(100, 100, 100, 100));

		number1Field = new JTextField();
		number2Field = new JTextField();
		addButton = new JButton(messages.getString("add"));
		subtractButton = new JButton(messages.getString("subtract"));
		multiplyButton = new JButton(messages.getString("multiply"));
		divideButton = new JButton(messages.getString("divide"));
		resultTextArea = new JTextArea();
		languageComboBox = new JComboBox<>(new String[] { "English", "Turkish" });
		languageLabel = new JLabel(messages.getString("language"));
		numberLabel1 = new JLabel(messages.getString("number") + " 1:");
		numberLabel2 = new JLabel(messages.getString("number") + " 2:");
		
		add(languageLabel);
		add(languageComboBox);
		add(numberLabel1);
		add(number1Field);
		add(numberLabel2);
		add(number2Field);
		add(addButton);
		add(subtractButton);
		add(multiplyButton);
		add(divideButton);
		add(resultTextArea);
		
		languageComboBox.setSelectedIndex(Locale.getDefault().getLanguage().equals("tr") ? 1 : 0);

		languageComboBox.addActionListener(e -> {
			System.out.println("Language changed");
			int selectedLanguageIndex = languageComboBox.getSelectedIndex();
			OperationController.changeLanguage(selectedLanguageIndex);
			number1Field.setText("");
			number2Field.setText("");
		});
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

	public JTextField getNumber1Field() {
		return number1Field;
	}

	public JTextField getNumber2Field() {
		return number2Field;
	}

	public JTextArea getResultTextArea() {
		return resultTextArea;
	}

	public JComboBox<String> getLanguageComboBox() {
		return languageComboBox;
	}

	public void setAddButtonText(String text) {
		addButton.setText(text);
	}

	public void setSubtractButtonText(String text) {
		subtractButton.setText(text);
	}

	public void setMultiplyButtonText(String text) {
		multiplyButton.setText(text);
	}

	public void setDivideButtonText(String text) {
		divideButton.setText(text);
	}

	public void setNumber1FieldText(String text) {
		number1Field.setText(text);
	}

	public void setNumber2FieldText(String text) {
		number2Field.setText(text);
	}

	public void setResultTextAreaText(String text) {
		resultTextArea.setText(text);
	}

	public void setLanguageComboBoxIndex(int index) {
		languageComboBox.setSelectedIndex(index);
	}

	public void setLanguageComboBoxItem(String item) {
		languageComboBox.setSelectedItem(item);
	}
	
	public void setNumberLabel1(String text) {
		numberLabel1.setText(text);
	}
	
	public void setNumberLabel2(String text) {
		numberLabel2.setText(text);
	}
	
	public void setLanguageLabel(String text) {
		languageLabel.setText(text);
	}

}
