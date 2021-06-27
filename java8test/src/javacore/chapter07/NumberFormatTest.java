package javacore.chapter07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Consumer;

public class NumberFormatTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            JFrame frame = new NumberFormatFrame();
            frame.setTitle("NumberFormatTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    static class NumberFormatFrame extends JFrame {
        private Locale[] locales;
        private double currentNumber;
        private JComboBox<String> localeCombo = new JComboBox<>();
        private JButton parseButton = new JButton("Parse");
        private JTextField numberText = new JTextField(30);
        private JRadioButton numberRadioButton = new JRadioButton("Number");
        private JRadioButton currencyRadioButton = new JRadioButton("Currency");
        private JRadioButton percentRadioButton = new JRadioButton("Percent");
        private ButtonGroup rbGroup = new ButtonGroup();
        private NumberFormat currentNumberFormat;

        public NumberFormatFrame() {
            setLayout(new GridBagLayout());
            ActionListener listener = event -> updateDisplay();
            JPanel p = new JPanel();
            addRadioButton(p, numberRadioButton, rbGroup, listener);
            addRadioButton(p, currencyRadioButton, rbGroup, listener);
            addRadioButton(p, percentRadioButton, rbGroup, listener);
            add(new JLabel("Locale:"), new GBC(0, 0).setAnchor(GBC.EAST));
            add(p, new GBC(1, 1));
            add(parseButton, new GBC(0, 2).setInsets(2));
            add(localeCombo, new GBC(1, 0).setAnchor(GBC.WEST));
            add(numberText, new GBC(1, 2).setFill(GBC.HORIZONTAL));
            locales = Locale.getAvailableLocales().clone();
            Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
            for (Locale locale : locales) {
                localeCombo.addItem(locale.getDisplayName());
            }
            localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
            currentNumber = 123456.78;
            updateDisplay();
            localeCombo.addActionListener(listener);
            parseButton.addActionListener(event->{
                String trim = numberText.getText().trim();
                System.out.println("==========="+trim);
                try {
                    Number parse = currentNumberFormat.parse(trim);
                    if (parse != null) {
                        currentNumber = parse.doubleValue();
                        updateDisplay();
                    }else{
                        numberText.setText("Parse error: " + trim);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    numberText.setText("Parse error: " +e);
                }
            });
        }

        public void addRadioButton(Container p, JRadioButton b, ButtonGroup g, ActionListener listener)
        {
            b.setSelected(g.getButtonCount() == 0);
            b.addActionListener(listener);
            g.add(b);
            p.add(b);
        }

        public void updateDisplay()
        {
            Locale currentLocale = locales[localeCombo.getSelectedIndex()];
            currentNumberFormat = null;
            if (numberRadioButton.isSelected())
                currentNumberFormat = NumberFormat.getNumberInstance(currentLocale);
            else if (currencyRadioButton.isSelected())
                currentNumberFormat = NumberFormat.getCurrencyInstance(currentLocale);
            else if (percentRadioButton.isSelected())
                currentNumberFormat = NumberFormat.getPercentInstance(currentLocale);
            String formatted = currentNumberFormat.format(currentNumber);
            numberText.setText(formatted);
        }
    }
}
