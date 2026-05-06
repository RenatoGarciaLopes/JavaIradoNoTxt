package com.app.presentation.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Base panel used by all form-based internal frames.
 * Provides: title label, consistent padding, a form area (GridBagLayout),
 * a feedback label for inline messages, and a button row.
 */
public class FormPanel extends JPanel {

    private final JLabel feedbackLabel;
    protected final JPanel formArea;
    protected final GridBagConstraints gbc;
    private int nextRow = 0;

    public FormPanel(String title) {
        setLayout(new BorderLayout(0, AppTheme.GAP_MD));
        setBorder(BorderFactory.createEmptyBorder(
                AppTheme.GAP_LG, AppTheme.GAP_LG, AppTheme.GAP_LG, AppTheme.GAP_LG));

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(AppTheme.titleFont());
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, AppTheme.GAP_SM, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Form area
        formArea = new JPanel(new GridBagLayout());
        formArea.setOpaque(false);
        add(formArea, BorderLayout.CENTER);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(AppTheme.GAP_SM / 2, 0, AppTheme.GAP_SM / 2, AppTheme.GAP_SM);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        // Feedback label at the bottom
        feedbackLabel = new JLabel(" ");
        feedbackLabel.setFont(AppTheme.labelFont());
        feedbackLabel.setBorder(BorderFactory.createEmptyBorder(AppTheme.GAP_SM, 0, 0, 0));
        add(feedbackLabel, BorderLayout.SOUTH);
    }

    /** Adds a label + field pair on the next row. The field stretches horizontally. */
    public void addField(String labelText, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = nextRow;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(AppTheme.labelFont());
        formArea.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formArea.add(field, gbc);

        nextRow++;
    }

    /** Adds a button row spanning both columns. */
    public void addButtonRow(JComponent... buttons) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.RIGHT, AppTheme.GAP_SM, 0));
        row.setOpaque(false);
        for (JComponent btn : buttons) row.add(btn);

        gbc.gridx = 0;
        gbc.gridy = nextRow;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(AppTheme.GAP_MD, 0, 0, 0);
        formArea.add(row, gbc);
        gbc.gridwidth = 1;
        gbc.insets = new Insets(AppTheme.GAP_SM / 2, 0, AppTheme.GAP_SM / 2, AppTheme.GAP_SM);
        nextRow++;
    }

    public void setFeedback(String message, boolean success) {
        feedbackLabel.setText(message);
        feedbackLabel.setForeground(success ? AppTheme.SUCCESS : AppTheme.ERROR);
    }

    public void clearFeedback() {
        feedbackLabel.setText(" ");
    }
}
