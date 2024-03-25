package operation;
import conversion.NumberConversionService;

public class OperationServiceImpl implements OperationService{
	private NumberConversionService conversionService;

    public OperationServiceImpl(NumberConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public String performOperation(String num1, String num2, String operation) throws Exception {
        int number1 = conversionService.toNumber(num1);
        int number2 = conversionService.toNumber(num2);
        int result;

        switch (operation) {
            case "ADD":
                result = number1 + number2;
                break;
            case "SUBTRACT":
                result = number1 - number2;
                break;
            case "MULTIPLY":
                result = number1 * number2;
                break;
            case "DIVIDE":
                if (number2 == 0) throw new ArithmeticException("Division by zero");
                result = number1 / number2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }

        return conversionService.toText(result, 0);
    }
}
