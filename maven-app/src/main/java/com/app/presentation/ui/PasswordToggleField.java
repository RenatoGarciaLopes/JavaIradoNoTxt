package com.app.presentation.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

/**
 * Password field with an eye-toggle button to show/hide the typed password.
 * Visually matches AppTextField — same border, radius and focus highlight.
 *
 * Usage:
 *   PasswordToggleField field = new PasswordToggleField();
 *   char[] password = field.getPassword();
 */
public class PasswordToggleField extends JPanel {

    private final JPasswordField passwordField;
    private final EyeButton eyeButton;
    private boolean passwordVisible = false;
    private boolean focusOwned = false;

    public PasswordToggleField() {
        setLayout(new BorderLayout(0, 0));
        setOpaque(false);

        passwordField = new JPasswordField();
        passwordField.setFont(AppTheme.inputFont());
        passwordField.setBorder(new EmptyBorder(8, 12, 8, 4));
        passwordField.setOpaque(false);

        eyeButton = new EyeButton();

        add(passwordField, BorderLayout.CENTER);
        add(eyeButton, BorderLayout.EAST);

        // Track focus on both the field and the button to keep border highlight
        FocusAdapter focusTracker = new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { focusOwned = true;  repaint(); }
            @Override public void focusLost(FocusEvent e)   { focusOwned = false; repaint(); }
        };
        passwordField.addFocusListener(focusTracker);
        eyeButton.addFocusListener(focusTracker);

        eyeButton.addActionListener(e -> toggleVisibility());
    }

    /** Returns the password characters (clears echo-char masking when visible). */
    public char[] getPassword() {
        return passwordField.getPassword();
    }

    /** Clears the field. */
    public void clear() {
        passwordField.setText("");
    }

    /** Allows adding an ActionListener triggered on Enter inside the field. */
    public void addActionListener(java.awt.event.ActionListener l) {
        passwordField.addActionListener(l);
    }

    // -- Toggle --------------------------------------------------------------

    private void toggleVisibility() {
        passwordVisible = !passwordVisible;
        passwordField.setEchoChar(passwordVisible ? (char) 0 : '•');
        eyeButton.repaint();
        passwordField.requestFocusInWindow();
    }

    // -- Border painting on the outer panel ----------------------------------

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(passwordField.getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), AppTheme.RADIUS, AppTheme.RADIUS);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(focusOwned ? AppTheme.ACCENT : new Color(100, 100, 120));
        g2.setStroke(new BasicStroke(focusOwned ? 2f : 1f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, AppTheme.RADIUS, AppTheme.RADIUS);
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = passwordField.getPreferredSize();
        return new Dimension(d.width + eyeButton.getPreferredSize().width, d.height);
    }

    // -- Eye icon button -----------------------------------------------------

    private final class EyeButton extends JButton {

        private boolean hovered = false;

        EyeButton() {
            setPreferredSize(new Dimension(36, 36));
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setOpaque(false);

            addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { hovered = true;  repaint(); }
                @Override public void mouseExited(MouseEvent e)  { hovered = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            Color iconColor = hovered ? AppTheme.ACCENT : new Color(150, 150, 170);
            g2.setColor(iconColor);
            g2.setStroke(new BasicStroke(1.6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            int cx = getWidth() / 2;
            int cy = getHeight() / 2;

            if (!passwordVisible) {
                drawEyeOpen(g2, cx, cy);
            } else {
                drawEyeClosed(g2, cx, cy);
            }

            g2.dispose();
        }

        private void drawEyeOpen(Graphics2D g2, int cx, int cy) {
            // Outer eye arc (almond shape via quadratic curves)
            int rx = 9, ry = 5;
            g2.drawArc(cx - rx, cy - ry, rx * 2, ry * 2, 0, 180);
            g2.drawArc(cx - rx, cy - ry, rx * 2, ry * 2, 180, 180);

            // Pupil
            int pr = 3;
            g2.fill(new Ellipse2D.Float(cx - pr, cy - pr, pr * 2, pr * 2));
        }

        private void drawEyeClosed(Graphics2D g2, int cx, int cy) {
            // Upper arc (eye outline, open version top half)
            int rx = 9, ry = 5;
            g2.drawArc(cx - rx, cy - ry, rx * 2, ry * 2, 0, 180);

            // Horizontal line where the lid closes
            g2.drawLine(cx - rx, cy, cx + rx, cy);

            // Three short lashes going down
            g2.drawLine(cx - 5, cy,     cx - 6, cy + 4);
            g2.drawLine(cx,     cy,     cx,     cy + 5);
            g2.drawLine(cx + 5, cy,     cx + 6, cy + 4);
        }
    }
}
