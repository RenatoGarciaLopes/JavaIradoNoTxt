package com.app.presentation.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Styled button with primary and secondary variants.
 * Provides hover/press micro-interactions without external libraries.
 */
public class AppButton extends JButton {

    public enum Variant { PRIMARY, SECONDARY }

    private final Variant variant;
    private Color currentBg;

    public AppButton(String text, Variant variant) {
        super(text);
        this.variant = variant;
        this.currentBg = baseBg();

        setFont(new Font("Inter", Font.BOLD, 13));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(getPreferredSize().width + 24, 36));

        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                if (isEnabled()) { currentBg = hoverBg(); repaint(); }
            }
            @Override public void mouseExited(MouseEvent e) {
                currentBg = baseBg(); repaint();
            }
            @Override public void mousePressed(MouseEvent e) {
                if (isEnabled()) { currentBg = pressBg(); repaint(); }
            }
            @Override public void mouseReleased(MouseEvent e) {
                currentBg = isEnabled() ? hoverBg() : baseBg(); repaint();
            }
        });
    }

    public static AppButton primary(String text) {
        return new AppButton(text, Variant.PRIMARY);
    }

    public static AppButton secondary(String text) {
        return new AppButton(text, Variant.SECONDARY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(isEnabled() ? currentBg : disabledBg());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), AppTheme.RADIUS, AppTheme.RADIUS);

        if (variant == Variant.SECONDARY) {
            g2.setColor(AppTheme.ACCENT);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, AppTheme.RADIUS, AppTheme.RADIUS);
        }

        g2.dispose();

        setForeground(isEnabled() ? fgColor() : new Color(150, 150, 150));
        super.paintComponent(g);
    }

    private Color baseBg() {
        return variant == Variant.PRIMARY ? AppTheme.ACCENT : new Color(0, 0, 0, 0);
    }

    private Color hoverBg() {
        return variant == Variant.PRIMARY ? AppTheme.ACCENT_HOVER : new Color(AppTheme.ACCENT.getRed(),
                AppTheme.ACCENT.getGreen(), AppTheme.ACCENT.getBlue(), 30);
    }

    private Color pressBg() {
        return variant == Variant.PRIMARY ? AppTheme.ACCENT_PRESS : new Color(AppTheme.ACCENT.getRed(),
                AppTheme.ACCENT.getGreen(), AppTheme.ACCENT.getBlue(), 60);
    }

    private Color disabledBg() {
        return new Color(100, 100, 100, 80);
    }

    private Color fgColor() {
        return variant == Variant.PRIMARY ? Color.WHITE : AppTheme.ACCENT;
    }
}
