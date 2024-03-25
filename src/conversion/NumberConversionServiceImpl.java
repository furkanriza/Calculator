package conversion;

public class NumberConversionServiceImpl implements NumberConversionService{
	@Override
    public int toNumber(String text) throws NumberFormatException {
        return Integer.parseInt(text);
    }

    @Override
    public String toText(int number, int languageIndex) {
        return String.valueOf(number);
    }

}
