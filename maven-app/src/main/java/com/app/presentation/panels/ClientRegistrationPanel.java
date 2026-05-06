package com.app.presentation.panels;

import com.app.presentation.MainFrame;
import com.app.application.service.ClientService;
import com.app.presentation.ui.AppButton;
import com.app.presentation.ui.AppTextField;
import com.app.presentation.ui.FormPanel;
import com.app.presentation.ui.MaskedTextField;
import javax.swing.*;
public class ClientRegistrationPanel extends JInternalFrame {
    private final ClientService service;
    private final AppTextField    nameField  = new AppTextField();
    private final MaskedTextField cpfField   = MaskedTextField.cpf();
    private final AppTextField    emailField = new AppTextField();
    private FormPanel form;
    public ClientRegistrationPanel(ClientService service) {
        super("Cadastro de Cliente", true, true, true, true);
        this.service = service;
        buildUi();
        if (MainFrame.FRAME_ICON != null) setFrameIcon(MainFrame.FRAME_ICON);
        pack();
        setLocation(100, 100);
    }
    private void buildUi() {
        form = new FormPanel("Cadastro de Cliente");
        form.addField("Nome:", nameField);
        form.addField("CPF:", cpfField);
        form.addField("E-mail:", emailField);
        AppButton clearBtn = AppButton.secondary("Limpar");
        AppButton saveBtn  = AppButton.primary("Salvar");
        clearBtn.addActionListener(e -> clearFields());
        saveBtn.addActionListener(e -> onSave());
        form.addButtonRow(clearBtn, saveBtn);
        setContentPane(scrollable(form));
    private static JScrollPane scrollable(JComponent c) {
        JScrollPane sp = new JScrollPane(c,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(null);
        return sp;
    private void onSave() {
        clearFieldStates();
        try {
            service.register(nameField.getText(), cpfField.getText(), emailField.getText());
            form.setFeedback("Cliente salvo com sucesso.", true);
            clearFields();
        } catch (IllegalArgumentException ex) {
            highlightInvalidField(ex.getMessage());
            form.setFeedback(ex.getMessage(), false);
        } catch (Exception ex) {
            form.setFeedback("Erro ao salvar cliente: " + ex.getMessage(), false);
        }
    private void highlightInvalidField(String message) {
        String lower = message.toLowerCase();
        if (lower.contains("cpf"))
            cpfField.setState(AppTextField.State.INVALID);
        else if (lower.contains("e-mail") || lower.contains("email"))
            emailField.setState(AppTextField.State.INVALID);
        else if (lower.contains("nome"))
            nameField.setState(AppTextField.State.INVALID);
    private void clearFields() {
        nameField.setText("");
        cpfField.setText("");
        emailField.setText("");
        form.clearFeedback();
    private void clearFieldStates() {
        nameField.clearState();
        cpfField.clearState();
        emailField.clearState();
}
