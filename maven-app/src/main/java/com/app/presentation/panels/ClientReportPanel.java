package com.app.presentation.panels;

import com.app.presentation.MainFrame;
import com.app.application.service.ClientReportService;
import com.app.domain.model.Client;
import com.app.presentation.ui.AppButton;
import com.app.presentation.ui.AppTheme;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
public class ClientReportPanel extends JInternalFrame {
    private final ClientReportService service;
    private DefaultTableModel tableModel;
    private JLabel countLabel;
    public ClientReportPanel(ClientReportService service) {
        super("Relatório de Clientes", true, true, true, true);
        this.service = service;
        buildUi();
        if (MainFrame.FRAME_ICON != null) setFrameIcon(MainFrame.FRAME_ICON);
        setSize(540, 420);
        setLocation(140, 140);
        loadData();
    }
    private void buildUi() {
        String[] columns = {"CPF", "E-mail", "Nome"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(AppTheme.inputFont());
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFillsViewportHeight(true);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Inter", Font.BOLD, 12));
        header.setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(190);
        table.getColumnModel().getColumn(2).setPreferredWidth(160);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        // Top bar: title + count
        JLabel title = new JLabel("Relatório de Clientes");
        title.setFont(AppTheme.titleFont());
        countLabel = new JLabel("");
        countLabel.setFont(AppTheme.labelFont());
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);
        topBar.setBorder(BorderFactory.createEmptyBorder(0, 0, AppTheme.GAP_MD, 0));
        topBar.add(title, BorderLayout.WEST);
        topBar.add(countLabel, BorderLayout.EAST);
        // Bottom bar: refresh button
        AppButton refreshBtn = AppButton.secondary("Atualizar");
        refreshBtn.addActionListener(e -> loadData());
        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        bottomBar.setOpaque(false);
        bottomBar.setBorder(BorderFactory.createEmptyBorder(AppTheme.GAP_SM, 0, 0, 0));
        bottomBar.add(refreshBtn);
        JPanel wrapper = new JPanel(new BorderLayout(0, 0));
        wrapper.setBorder(BorderFactory.createEmptyBorder(
                AppTheme.GAP_LG, AppTheme.GAP_LG, AppTheme.GAP_LG, AppTheme.GAP_LG));
        wrapper.add(topBar, BorderLayout.NORTH);
        wrapper.add(scroll, BorderLayout.CENTER);
        wrapper.add(bottomBar, BorderLayout.SOUTH);
        setContentPane(wrapper);
    private void loadData() {
        try {
            List<Client> clients = service.getReport();
            tableModel.setRowCount(0);
            for (Client c : clients)
                tableModel.addRow(new String[]{c.cpf(), c.email(), c.name()});
            countLabel.setText(clients.size() + " registro" + (clients.size() == 1 ? "" : "s"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar relatório: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
}
