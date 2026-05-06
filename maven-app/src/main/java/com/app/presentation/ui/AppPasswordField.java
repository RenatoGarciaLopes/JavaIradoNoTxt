package com.app.presentation.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Styled password field matching AppTextField visual style.
 */
public class AppPasswordField extends JPasswordField {

    public AppPasswordField() {
        super(20);
        setFont(AppTheme.inputFont());
        setBorder(new EmptyBorder(8, 12, 8, 12));
        setOpaque(true);

        addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { repaint(); }
            @Override public void focusLost(FocusEvent e)   { repaint(); }
        });
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(isFocusOwner() ? AppTheme.ACCENT : new Color(100, 100, 120));
        g2.setStroke(new BasicStroke(isFocusOwner() ? 2f : 1f));
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
