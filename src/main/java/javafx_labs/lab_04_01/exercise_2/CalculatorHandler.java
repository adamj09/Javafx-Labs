package javafx_labs.lab_04_01.exercise_2;

import javafx.scene.control.TextField;

/**
 * @author Adam Johnston 2332003
 * 
 *         Class to handle all calculator operations.
 */
public class CalculatorHandler {
    /**
     * Numbers to perform operations with.
     */
    private Double num1, num2;

    /**
     * Converts String values in the number fields to Double values.
     * 
     * If an exception occurs while parsing, set the result field to "Invalid
     * input!".
     * 
     * @param num1Field   First number field.
     * @param num2Field   Second number field.
     * @param resultField Field in which the result of the operation on the two
     *                    numbers are displayed.
     */
    private void parseDoubles(TextField num1Field, TextField num2Field, TextField resultField) {
        try {
            num1 = Double.parseDouble(num1Field.getText());
            num2 = Double.parseDouble(num2Field.getText());
        } catch (Exception ex) { // Catch any exception thrown when parsing data.
            resultField.setText("Invalid input!");
            num1 = null;
            num2 = null;
        }
    }

    /**
     * Adds two parsed values.
     * 
     * @param num1Field   First number field.
     * @param num2Field   Second number field.
     * @param resultField Field in which the result of the operation on the two
     *                    numbers are displayed.
     */
    public void add(TextField num1Field, TextField num2Field, TextField resultField) {
        parseDoubles(num1Field, num2Field, resultField);
        if (num1 != null && num2 != null)
            resultField.setText((num1 + num2) + "");
    }

    /**
     * Subtracts two parsed values.
     * 
     * @param num1Field   First number field.
     * @param num2Field   Second number field.
     * @param resultField Field in which the result of the operation on the two
     *                    numbers are displayed.
     */
    public void subtract(TextField num1Field, TextField num2Field, TextField resultField) {
        parseDoubles(num1Field, num2Field, resultField);
        if (num1 != null && num2 != null)
            resultField.setText((num1 - num2) + "");
    }

    /**
     * Multiplies two parsed values.
     * 
     * @param num1Field   First number field.
     * @param num2Field   Second number field.
     * @param resultField Field in which the result of the operation on the two
     *                    numbers are displayed.
     */
    public void multiply(TextField num1Field, TextField num2Field, TextField resultField) {
        parseDoubles(num1Field, num2Field, resultField);
        if (num1 != null && num2 != null)
            resultField.setText((num1 * num2) + "");
    }

    /**
     * Divides two parsed values.
     * 
     * @param num1Field   First number field.
     * @param num2Field   Second number field.
     * @param resultField Field in which the result of the operation on the two
     *                    numbers are displayed.
     */
    public void divide(TextField num1Field, TextField num2Field, TextField resultField) {
        parseDoubles(num1Field, num2Field, resultField);
        if (num1 != null && num2 != null) {
            if (num2 == 0) { // Check for division by 0.
                resultField.setText("Cannot divide by 0!");
                return;
            }

            resultField.setText((num1 / num2) + "");
        }
    }

    /**
     * Raises the first parsed value to the power of the second parsed value.
     * 
     * @param num1Field   First number field.
     * @param num2Field   Second number field.
     * @param resultField Field in which the result of the operation on the two
     *                    numbers are displayed.
     */
    public void power(TextField num1Field, TextField num2Field, TextField resultField) {
        parseDoubles(num1Field, num2Field, resultField);
        if (num1 != null && num2 != null)
            resultField.setText(Math.pow(num1, num2) + "");
    }
}
