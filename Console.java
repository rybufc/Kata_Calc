import java.util.*;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

public class Console {
    private static numberTypes numberType;

    public static void main(String[] args) throws Exception {
        /*
        List<RomanNumber> tests = new ArrayList<RomanNumber>();

        tests.add(new RomanNumber(-1));
        tests.add(new RomanNumber("IX"));

        for (int i = 0; i < tests.size(); i++)
        {
            System.out.println(tests.get(i).GetValue() + " " + tests.get(i).GetRoman());
        }*/

        String[] input = readInput();
        checkParams(input);

        switch (numberType)
        {
            case roman -> {
                var firstNumber = new RomanNumber(input[0]);
                var secondNumber = new RomanNumber(input[2]);
                var result = new RomanNumber(operations.get(input[1])
                        .applyAsInt(firstNumber.GetValue(), secondNumber.GetValue()));
                System.out.println(result.GetRoman());
            }
            case decimal -> {
                var firstNumber = Integer.parseInt(input[0]);
                var secondNumber = Integer.parseInt(input[2]);
                var result = operations.get(input[1]).applyAsInt(firstNumber, secondNumber);
                System.out.println(result);
            }
        }
    }

    private static String[] readInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        return input.split(" ");
    }

    private static void checkParams(String[] args) throws Exception {
        if (args.length != 3)
            throw new IllegalArgumentException("This program accepts 3 arguments");

        numberType = getNumberType(args[0]);
        if (numberType != getNumberType(args[2]))
            throw new IllegalArgumentException("both numbers must be in same numeral system");

        if (!operations.containsKey(args[1]))
            throw new IllegalArgumentException("Operation is unknown");
    }

    private static numberTypes getNumberType(String input) throws Exception {
        try
        {
            int number = Integer.parseInt(input);
            if (number < 1 | number > 10)
                throw new IllegalArgumentException("Input number must be in range between 0 and 11");
            return numberTypes.decimal;
        }
        catch (NumberFormatException ignored) {}
        catch (IllegalArgumentException e) {
            throw e;
        }
        catch (Exception ignored){}

        try
        {
            var number = new RomanNumber(input);
            if (number.GetValue() < 1 | number.GetValue() > 10)
                throw new IllegalArgumentException("Input number must be in range between 0 and 11");
            return numberTypes.roman;
        }
        catch (NumberFormatException ignored) {}
        catch (IllegalArgumentException e) {
            throw e;
        }
        catch (Exception ignored){}

        throw new IllegalArgumentException("Number must be decimal or roman.");
    }

    private enum numberTypes {
        decimal, roman
    }

    private static final Map<String, IntBinaryOperator> operations = new HashMap<String, IntBinaryOperator>()
    {{
        put("+", (int x, int y) -> {return x + y;});
        put("-", (int x, int y) -> {return x - y;});
        put("*", (int x, int y) -> {return x * y;});
        put("/", (int x, int y) -> {return x / y;});
    }};
}
