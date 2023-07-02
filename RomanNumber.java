import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RomanNumber {
    private int value;
    private String roman;

    public int GetValue() { return value; }
    public String GetRoman() { return roman; }

    public RomanNumber(int value) throws IllegalArgumentException {
        this.value = value;
        this.roman = convertToRoman(value);
    }

    public RomanNumber(String roman) throws IllegalArgumentException {
        this.roman = roman;
        this.value = convertFromRoman(roman);
    }

    private String convertToRoman(int value)  throws IllegalArgumentException {
        if (value <= 0)
            throw new IllegalArgumentException("Roman Number Can't Be lesser than 1");

        StringBuilder roman = new StringBuilder();
        final Numeral[] values = Numeral.values();

        for (int i = values.length - 1; i >= 0; i--)
        {
            while (value >= values[i].weight) {
                value -= values[i].weight;
                roman.append(values[i]);
            }
        }

        return roman.toString();
    }

    private int convertFromRoman(String number) throws IllegalArgumentException {
        int value = 0;
        number = number.toUpperCase();

        try
        {
            number = number.replace("IV", "IIII");
            number = number.replace("IX", "VIIII");
            number = number.replace("XL", "XXXX");
            number = number.replace("XC", "LXXXX");

            for (int i = 0; i < number.length(); i++)
            {
                value += NumeralMap.get(String.valueOf(number.charAt(i)));
            }

            return value;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    private final Map<String, Integer> NumeralMap = new HashMap<String, Integer>() {
        {
            put("I", 1);
            put("IV", 4);
            put("V", 5);
            put("IX", 9);
            put("X", 10);
            put("XL", 40);
            put("L", 50);
            put("XC", 90);
            put("C", 100);
        }
    };

    private enum Numeral {
        I(1), IV(4), V(5), IX(9), X(10), XL(40),
        L(50), XC(90), C(100);
        int weight;
        Numeral(int weight) {
            this.weight = weight;
        }
    }
};


