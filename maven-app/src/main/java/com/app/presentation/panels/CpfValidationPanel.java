package com.app.presentation.panels;

import com.app.presentation.MainFrame;
import com.app.application.service.ValidateCpfService;
import com.app.presentation.ui.AppButton;
import com.app.presentation.ui.AppTextField;
import com.app.presentation.ui.FormPanel;
import com.app.presentation.ui.MaskedTextField;
import javax.swing.*;
public class CpfValidationPanel extends JInternalFrame {
    private final ValidateCpfService service;
    private final MaskedTextField cpfField = MaskedTextField.cpf();
    private FormPanel form;
    public CpfValidationPanel(ValidateCpfService service) {
        super("Validação de CPF", true, true, true, true);
        this.service = service;
        buildUi();
        if (MainFrame.FRAME_ICON != null) setFrameIcon(MainFrame.FRAME_ICON);
        pack();
        setLocation(20, 20);
    }
    private void buildUi() {
        form = new FormPanel("Validação de CPF");
        form.addField("CPF:", cpfField);
        AppButton validateBtn = AppButton.primary("Validar");
        validateBtn.addActionListener(e -> onValidate());
        cpfField.addActionListener(e -> onValidate());
        form.addButtonRow(validateBtn);
        setContentPane(scrollable(form));
    private static JScrollPane scrollable(JComponent c) {
        JScrollPane sp = new JScrollPane(c,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(null);
        return sp;
    private void onValidate() {
        String input = cpfField.getText().trim();
        boolean valid = service.validate(input);
        cpfField.setState(valid ? AppTextField.State.VALID : AppTextField.State.INVALID);
        form.setFeedback(valid ? "CPF válido" : "CPF inválido", valid);
}
