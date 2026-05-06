package com.app.presentation.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Styled text field with focus highlight and inline validation state (valid/invalid/neutral).
 */
public class AppTextField extends JTextField {

    public enum State { NEUTRAL, VALID, INVALID }

    private State state = State.NEUTRAL;

    public AppTextField() {
        this(20);
    }

    public AppTextField(int columns) {
        super(columns);
        setFont(AppTheme.inputFont());
        setBorder(new EmptyBorder(8, 12, 8, 12));
        setOpaque(true);

        addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { repaint(); }
            @Override public void focusLost(FocusEvent e)   { repaint(); }
        });
    }

    public void setState(State s) {
        this.state = s;
        repaint();
    }

    public void clearState() {
        setState(State.NEUTRAL);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color borderColor = switch (state) {
            case VALID   -> AppTheme.SUCCESS;
            case INVALID -> AppTheme.ERROR;
            default      -> isFocusOwner() ? AppTheme.ACCENT : new Color(100, 100, 120);
        };

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(isFocusOwner() || state != State.NEUTRAL ? 2f : 1f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, AppTheme.RADIUS, AppTheme.RADIUS);
        g2.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), AppTheme.RADIUS, AppTheme.RADIUS);
        g2.dispose();
        super.paintComponent(g);
    }
}
