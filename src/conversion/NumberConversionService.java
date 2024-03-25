package conversion;

public interface NumberConversionService {
	int toNumber(String text) throws NumberFormatException;
    String toText(int number, int languageIndex);
}