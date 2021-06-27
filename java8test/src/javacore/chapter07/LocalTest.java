package javacore.chapter07;

import javax.swing.text.NumberFormatter;
import java.io.DataOutputStream;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.Locale;

public class LocalTest {
    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        System.out.println(locale.getCountry());
        System.out.println(locale.getDisplayCountry());
        System.out.println(locale.getDisplayLanguage());
        System.out.println(locale.getDisplayName());
        System.out.println(locale.getLanguage());

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println(currencyInstance.format(12345678.9));
        System.out.println(Charset.defaultCharset());
    }

}
