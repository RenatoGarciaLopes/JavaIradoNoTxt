package com.app.presentation.panels;

import com.app.presentation.MainFrame;
import com.app.application.service.StatesService;
import com.app.presentation.ui.AppTheme;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
public class StatesPanel extends JInternalFrame {
    private final StatesService service;
    public StatesPanel(StatesService service) {
        super("Estados Brasileiros", true, true, true, true);
        this.service = service;
        buildUi();
        if (MainFrame.FRAME_ICON != null) setFrameIcon(MainFrame.FRAME_ICON);
        setSize(440, 520);
        setLocation(120, 120);
    }
    private void buildUi() {
        String[] columns = {"Código", "UF", "Nome"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        try {
            List<String[]> states = service.getStates();
            for (String[] row : states) model.addRow(row);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar estados: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        JTable table = new JTable(model);
        styleTable(table);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder(
                AppTheme.GAP_LG, AppTheme.GAP_LG, AppTheme.GAP_LG, AppTheme.GAP_LG));
        JLabel title = new JLabel("Estados Brasileiros");
        title.setFont(AppTheme.titleFont());
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, AppTheme.GAP_MD, 0));
        wrapper.add(title, BorderLayout.NORTH);
        wrapper.add(scroll, BorderLayout.CENTER);
        setContentPane(wrapper);
    private void styleTable(JTable table) {
        table.setRowHeight(28);
        table.setFont(AppTheme.inputFont());
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFillsViewportHeight(true);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Inter", Font.BOLD, 12));
        header.setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(220);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
}
