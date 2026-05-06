package com.app.presentation.ui;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Text field that auto-formats input according to a mask.
 *
 * Mask syntax: '#' = digit placeholder, any other char = literal separator.
 *   CPF  → "###.###.###-##"
 *   CNPJ → "##.###.###/####-##"
 *
 * Use the static factories for the common cases.
 */
public class MaskedTextField extends AppTextField {

    private final String mask;
    private final int maxDigits;

    public MaskedTextField(String mask) {
        super();
        this.mask = mask;
        this.maxDigits = countDigitSlots(mask);
        ((AbstractDocument) getDocument()).setDocumentFilter(new MaskFilter());
    }

    public static MaskedTextField cpf() {
        return new MaskedTextField("###.###.###-##");
    }

    public static MaskedTextField cnpj() {
        return new MaskedTextField("##.###.###/####-##");
    }

    // -- Mask application ----------------------------------------------------

    private String applyMask(String digits) {
        StringBuilder sb = new StringBuilder();
        int di = 0;
        for (char m : mask.toCharArray()) {
            if (di >= digits.length()) break;
            if (m == '#') sb.append(digits.charAt(di++));
            else          sb.append(m);
        }
        return sb.toString();
    }

    /** Returns the index in the masked string that corresponds to the end of {@code digitCount} digits. */
    private int maskedPositionAfterDigit(int digitCount) {
        int di = 0;
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == '#') {
                di++;
                if (di == digitCount) return i + 1;
            }
        }
        return mask.length();
    }

    private static int countDigitSlots(String mask) {
        int n = 0;
        for (char c : mask.toCharArray()) if (c == '#') n++;
        return n;
    }

    // -- DocumentFilter ------------------------------------------------------

    private final class MaskFilter extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                throws BadLocationException {
            replace(fb, offset, 0, text, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr)
                throws BadLocationException {

            String current  = fb.getDocument().getText(0, fb.getDocument().getLength());
            String digits   = current.replaceAll("\\D", "");
            String newPart  = text == null ? "" : text.replaceAll("\\D", "");

            // How many digits sit before the edit point and inside the removed range
            int digitsBefore   = current.substring(0, offset).replaceAll("\\D", "").length();
            int digitsRemoved  = current.substring(offset, offset + length).replaceAll("\\D", "").length();

            // Rebuild the full digit string
            String allDigits = digits.substring(0, digitsBefore)
                             + newPart
                             + digits.substring(digitsBefore + digitsRemoved);

            if (allDigits.length() > maxDigits)
                allDigits = allDigits.substring(0, maxDigits);

            String masked = applyMask(allDigits);
            fb.replace(0, fb.getDocument().getLength(), masked, attr);

            // Place caret right after the last digit just typed
            int caretDigits = digitsBefore + newPart.length();
            if (caretDigits > allDigits.length()) caretDigits = allDigits.length();
            int caretPos = maskedPositionAfterDigit(caretDigits);
            // Advance past any immediately following literal separators
            while (caretPos < masked.length() && mask.length() > caretPos && mask.charAt(caretPos) != '#')
                caretPos++;

            final int pos = Math.min(caretPos, masked.length());
            // Must defer caret update until after the document event is fully dispatched
            javax.swing.SwingUtilities.invokeLater(() -> setCaretPosition(pos));
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            replace(fb, offset, length, "", null);
        }
    }
}
