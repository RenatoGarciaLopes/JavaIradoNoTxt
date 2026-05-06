package com.app.presentation.panels;

import com.app.presentation.MainFrame;
import com.app.application.service.ValidateCnpjService;
import com.app.presentation.ui.AppButton;
import com.app.presentation.ui.AppTextField;
import com.app.presentation.ui.FormPanel;
import com.app.presentation.ui.MaskedTextField;
import javax.swing.*;
public class CnpjValidationPanel extends JInternalFrame {
    private final ValidateCnpjService service;
    private final MaskedTextField cnpjField = MaskedTextField.cnpj();
    private FormPanel form;
    public CnpjValidationPanel(ValidateCnpjService service) {
        super("Validação de CNPJ", true, true, true, true);
        this.service = service;
        buildUi();
        if (MainFrame.FRAME_ICON != null) setFrameIcon(MainFrame.FRAME_ICON);
        pack();
        setLocation(40, 40);
    }
    private void buildUi() {
        form = new FormPanel("Validação de CNPJ");
        form.addField("CNPJ:", cnpjField);
        AppButton validateBtn = AppButton.primary("Validar");
        validateBtn.addActionListener(e -> onValidate());
        cnpjField.addActionListener(e -> onValidate());
        form.addButtonRow(validateBtn);
        setContentPane(scrollable(form));
    private static JScrollPane scrollable(JComponent c) {
        JScrollPane sp = new JScrollPane(c,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(null);
        return sp;
    private void onValidate() {
        String input = cnpjField.getText().trim();
        boolean valid = service.validate(input);
        cnpjField.setState(valid ? AppTextField.State.VALID : AppTextField.State.INVALID);
        form.setFeedback(valid ? "CNPJ válido" : "CNPJ inválido", valid);
}
