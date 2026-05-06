package com.app.presentation.ui;

import java.awt.*;

/**
 * Central source of truth for all visual tokens: colors, fonts, spacing.
 * Every panel and component reads from here — change once, apply everywhere.
 */
public final class AppTheme {

    // -- Accent palette
    public static final Color ACCENT        = new Color(99, 102, 241);   // indigo-500
    public static final Color ACCENT_HOVER  = new Color(79,  70, 229);   // indigo-600
    public static final Color ACCENT_PRESS  = new Color(67,  56, 202);   // indigo-700

    // -- Semantic
    public static final Color SUCCESS = new Color(34, 197, 94);
    public static final Color ERROR   = new Color(239, 68, 68);
    public static final Color WARNING = new Color(234, 179, 8);

    // -- Spacing
    public static final int GAP_SM  = 8;
    public static final int GAP_MD  = 16;
    public static final int GAP_LG  = 24;

    // -- Border radius (used in custom painting)
    public static final int RADIUS = 8;

    // -- Fonts
    public static Font titleFont() {
        return new Font("Inter", Font.BOLD, 18);
    }

    public static Font labelFont() {
        return new Font("Inter", Font.PLAIN, 13);
    }

    public static Font inputFont() {
        return new Font("Inter", Font.PLAIN, 13);
    }

    private AppTheme() { }
}
