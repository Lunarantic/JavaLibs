package utilities.currency.indian;

public class NumberToWord {
    private static final String[] specialNames = { "", " Thousand", " Lakhs", " Crore" };

    private static final String[] tensNames = { "", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety" };

    private static final String[] numNames = { "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen" };

    private String convertLessThanOneThousand(int number) {
        String current;

        if (number % 100 < 20) {
            current = numNames[number % 100];
            number /= 100;
        } else {
            current = numNames[number % 10];
            number /= 10;

            current = tensNames[number % 10] + current;
            number /= 10;
        }
        if (number == 0) {
            return current;
        }
        return numNames[number] + " Hundred" + current;
    }

    public String convert(int number) {

        if (number == 0) {
            return "Zero";
        }

        String prefix = "";

        if (number < 0) {
            number = -number;
            prefix = "Negative";
        }

        String current = "";
        int place = 0;

        int n = number % 1000;
        if (n != 0) {
            String s = convertLessThanOneThousand(n);
            current = s + specialNames[place] + current;
        }
        place++;
        number /= 1000;

        while (number > 0) {
            n = number % 100;
            if (n != 0) {
                String s = convertLessThanOneThousand(n);
                current = s + specialNames[place] + current;
            }
            place++;
            number /= 100;
        }

        return (prefix + current).trim();
    }

    public String convert(Double number) {
        int no = number.intValue();

        String num = convert(no);

        number *= 100;
        no = number.intValue();
        int temp = no % 100;

        if (temp != 0) {
            if (!num.equals("")) {
                num += " and ";
            }

            //		   String l = convert(temp % 10);
            //		   num += convert(temp/10) + " " + l + " Paise";

            num += convert(temp) + " Paise";
        }

        return "Rupees " + num + " only";
    }

    public String convert(String number) {
        String num = " -   ";
        if (!number.equals(num)) {
            num = convert(Double.parseDouble(number));
        }

        return num;
    }

    public static void main(String args[]) {
        NumberToWord obj = new NumberToWord();
        System.out.println("*** " + obj.convert(17856512));
        System.out.println("*** " + obj.convert(288260.67));
        System.out.println("*** " + obj.convert(-55));
    }
}