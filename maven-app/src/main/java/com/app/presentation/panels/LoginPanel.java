package com.app.presentation.panels;

import com.app.presentation.MainFrame;
import com.app.application.service.AuthService;
import com.app.presentation.ui.AppButton;
import com.app.presentation.ui.AppTextField;
import com.app.presentation.ui.FormPanel;
import com.app.presentation.ui.PasswordToggleField;
import javax.swing.*;
public class LoginPanel extends JInternalFrame {
    private final AuthService service;
    private final AppTextField      emailField    = new AppTextField();
    private final PasswordToggleField passwordField = new PasswordToggleField();
    private FormPanel form;
    public LoginPanel(AuthService service) {
        super("Login", true, true, true, true);
        this.service = service;
        buildUi();
        if (MainFrame.FRAME_ICON != null) setFrameIcon(MainFrame.FRAME_ICON);
        pack();
        setLocation(80, 80);
    }
    private void buildUi() {
        form = new FormPanel("Login");
        form.addField("E-mail:", emailField);
        form.addField("Senha:", passwordField);
        AppButton loginBtn = AppButton.primary("Entrar");
        loginBtn.addActionListener(e -> onLogin());
        passwordField.addActionListener(e -> onLogin());
        form.addButtonRow(loginBtn);
        setContentPane(scrollable(form));
    private static JScrollPane scrollable(JComponent c) {
        JScrollPane sp = new JScrollPane(c,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(null);
        return sp;
    private void onLogin() {
        String email    = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        passwordField.clear();
        if (service.login(email, password)) {
            form.setFeedback("Login realizado com sucesso!", true);
        } else {
            form.setFeedback("E-mail ou senha inválidos.", false);
        }
}
