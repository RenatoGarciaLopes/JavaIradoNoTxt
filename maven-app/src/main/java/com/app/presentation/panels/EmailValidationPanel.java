package com.app.presentation.panels;

import com.app.presentation.MainFrame;
import com.app.application.service.ValidateEmailService;
import com.app.presentation.ui.AppButton;
import com.app.presentation.ui.AppTextField;
import com.app.presentation.ui.FormPanel;
import javax.swing.*;
public class EmailValidationPanel extends JInternalFrame {
    private final ValidateEmailService service;
    private final AppTextField emailField = new AppTextField();
    private FormPanel form;
    public EmailValidationPanel(ValidateEmailService service) {
        super("Validação de E-mail", true, true, true, true);
        this.service = service;
        buildUi();
        if (MainFrame.FRAME_ICON != null) setFrameIcon(MainFrame.FRAME_ICON);
        pack();
        setLocation(60, 60);
    }
    private void buildUi() {
        form = new FormPanel("Validação de E-mail");
        form.addField("E-mail:", emailField);
        AppButton validateBtn = AppButton.primary("Validar");
        validateBtn.addActionListener(e -> onValidate());
        emailField.addActionListener(e -> onValidate());
        form.addButtonRow(validateBtn);
        setContentPane(scrollable(form));
    private static JScrollPane scrollable(JComponent c) {
        JScrollPane sp = new JScrollPane(c,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(null);
        return sp;
    private void onValidate() {
        String input = emailField.getText().trim();
        boolean valid = service.validate(input);
        emailField.setState(valid ? AppTextField.State.VALID : AppTextField.State.INVALID);
        form.setFeedback(valid ? "E-mail válido" : "E-mail inválido", valid);
}
