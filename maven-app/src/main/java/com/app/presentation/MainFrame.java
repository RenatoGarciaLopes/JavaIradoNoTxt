package com.app.presentation;

import com.app.application.service.*;
import com.app.infrastructure.repository.ClientRepository;
import com.app.infrastructure.repository.ClientTxtRepository;
import com.app.infrastructure.repository.StatesCSVRepository;
import com.app.presentation.panels.*;
import com.app.presentation.ui.AppTheme;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

public class MainFrame extends JFrame implements Runnable {

    private final JDesktopPane desktop = new JDesktopPane();

    private final ValidateCpfService   cpfService;
    private final ValidateCnpjService  cnpjService;
    private final ValidateEmailService emailService;
    private final AuthService          authService;
    private final ClientService        clientService;
    private final ClientReportService  reportService;
    private final StatesService        statesService;

    // Panels — lazily instantiated, reused on reopen
    private CpfValidationPanel      cpfPanel;
    private CnpjValidationPanel     cnpjPanel;
    private EmailValidationPanel    emailPanel;
    private LoginPanel              loginPanel;
    private ClientRegistrationPanel registrationPanel;
    private StatesPanel             statesPanel;
    private ClientReportPanel       reportPanel;

    public MainFrame(
            ValidateCpfService   cpfService,
            ValidateCnpjService  cnpjService,
            ValidateEmailService emailService,
            AuthService          authService,
            ClientService        clientService,
            ClientReportService  reportService,
            StatesService        statesService) {

        super("App — Gestão Empresarial");
        this.cpfService    = cpfService;
        this.cnpjService   = cnpjService;
        this.emailService  = emailService;
        this.authService   = authService;
        this.clientService = clientService;
        this.reportService = reportService;
        this.statesService = statesService;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setSize(1100, 740);
        setLocationRelativeTo(null);

        desktop.setBackground(new Color(30, 31, 34));
        setContentPane(desktop);
        setJMenuBar(buildMenuBar());
        setStatusBar();
    }

    @Override
    public void run() {
        setVisible(true);
    }

    // -- Menu ----------------------------------------------------------------

    private JMenuBar buildMenuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu validation = menu("Validação");
        validation.add(item("Validar CPF",   () -> open(getCpfPanel())));
        validation.add(item("Validar CNPJ",  () -> open(getCnpjPanel())));
        validation.add(item("Validar E-mail", () -> open(getEmailPanel())));
        bar.add(validation);

        JMenu access = menu("Acesso");
        access.add(item("Login", () -> open(getLoginPanel())));
        bar.add(access);

        JMenu clients = menu("Clientes");
        clients.add(item("Cadastrar Cliente", () -> open(getRegistrationPanel())));
        clients.add(item("Relatório de Clientes", () -> open(getReportPanel())));
        bar.add(clients);

        JMenu data = menu("Dados");
        data.add(item("Estados Brasileiros", () -> open(getStatesPanel())));
        bar.add(data);

        JMenu window = menu("Janela");
        window.add(item("Organizar em Grade", this::tileWindows));
        window.add(item("Cascata",            this::cascadeWindows));
        window.addSeparator();
        window.add(item("Fechar Todas",       this::closeAll));
        bar.add(window);

        return bar;
    }

    private JMenu menu(String label) {
        JMenu m = new JMenu(label);
        m.setFont(AppTheme.labelFont());
        return m;
    }

    private JMenuItem item(String label, Runnable action) {
        JMenuItem i = new JMenuItem(label);
        i.setFont(AppTheme.labelFont());
        i.addActionListener(e -> action.run());
        return i;
    }

    // -- Status bar ----------------------------------------------------------

    private void setStatusBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBorder(BorderFactory.createEmptyBorder(4, 12, 4, 12));
        JLabel hint = new JLabel("Use os menus acima para abrir as funcionalidades.");
        hint.setFont(new Font("Inter", Font.PLAIN, 11));
        bar.add(hint, BorderLayout.WEST);
        add(bar, BorderLayout.SOUTH);
    }

    // -- MDI helpers ---------------------------------------------------------

    private void open(JInternalFrame panel) {
        if (panel.getParent() == null) {
            desktop.add(panel);
        }
        panel.setVisible(true);
        try { panel.setSelected(true); }
        catch (java.beans.PropertyVetoException ignored) { }
    }

    private void tileWindows() {
        JInternalFrame[] frames = desktop.getAllFrames();
        if (frames.length == 0) return;
        int cols  = (int) Math.ceil(Math.sqrt(frames.length));
        int rows  = (int) Math.ceil((double) frames.length / cols);
        int cellW = desktop.getWidth()  / cols;
        int cellH = desktop.getHeight() / rows;
        for (int i = 0; i < frames.length; i++) {
            try { frames[i].setMaximum(false); } catch (Exception ignored) { }
            frames[i].setBounds((i % cols) * cellW, (i / cols) * cellH, cellW, cellH);
        }
    }

    private void cascadeWindows() {
        JInternalFrame[] frames = desktop.getAllFrames();
        int offset = 30;
        for (int i = 0; i < frames.length; i++) {
            try { frames[i].setMaximum(false); } catch (Exception ignored) { }
            frames[i].setLocation(i * offset, i * offset);
            frames[i].toFront();
        }
    }

    private void closeAll() {
        for (JInternalFrame f : desktop.getAllFrames()) f.setVisible(false);
    }

    // -- Lazy panel getters --------------------------------------------------

    private CpfValidationPanel getCpfPanel() {
        if (cpfPanel == null) cpfPanel = new CpfValidationPanel(cpfService);
        return cpfPanel;
    }

    private CnpjValidationPanel getCnpjPanel() {
        if (cnpjPanel == null) cnpjPanel = new CnpjValidationPanel(cnpjService);
        return cnpjPanel;
    }

    private EmailValidationPanel getEmailPanel() {
        if (emailPanel == null) emailPanel = new EmailValidationPanel(emailService);
        return emailPanel;
    }

    private LoginPanel getLoginPanel() {
        if (loginPanel == null) loginPanel = new LoginPanel(authService);
        return loginPanel;
    }

    private ClientRegistrationPanel getRegistrationPanel() {
        if (registrationPanel == null) registrationPanel = new ClientRegistrationPanel(clientService);
        return registrationPanel;
    }

    private StatesPanel getStatesPanel() {
        if (statesPanel == null) statesPanel = new StatesPanel(statesService);
        return statesPanel;
    }

    private ClientReportPanel getReportPanel() {
        if (reportPanel == null) reportPanel = new ClientReportPanel(reportService);
        return reportPanel;
    }

    // -- Icon ----------------------------------------------------------------

    /** Loaded once, shared with all JInternalFrames. */
    static ImageIcon FRAME_ICON;

    private static void applyIcon(MainFrame frame) {
        try {
            URL url = MainFrame.class.getResource("/icons/app.png");
            if (url == null) return;
            Image full = ImageIO.read(url);
            if (full == null) return;

            // JFrame / taskbar: use full resolution
            frame.setIconImages(List.of(full));

            // macOS Dock
            if (Taskbar.isTaskbarSupported()) {
                Taskbar taskbar = Taskbar.getTaskbar();
                if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE))
                    taskbar.setIconImage(full);
            }

            // JInternalFrame title-bar icon: 16×16
            Image small = full.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            FRAME_ICON = new ImageIcon(small);

        } catch (Exception ignored) { }
    }

    // -- Entry point ---------------------------------------------------------

    public static void main(String[] args) {
        FlatDarkLaf.setup();

        // Fine-tune global UI properties after FlatLaf is set
        UIManager.put("InternalFrame.titleFont", new Font("Inter", Font.BOLD, 13));
        UIManager.put("MenuItem.font",           AppTheme.labelFont());
        UIManager.put("Menu.font",               AppTheme.labelFont());
        UIManager.put("Button.arc",              AppTheme.RADIUS);
        UIManager.put("Component.arc",           AppTheme.RADIUS);
        UIManager.put("TextComponent.arc",       AppTheme.RADIUS);

        Path statesPath;
        try {
            URL resource = MainFrame.class.getResource("/data/states.csv");
            if (resource == null) throw new RuntimeException("states.csv not found in classpath");
            statesPath = Path.of(resource.toURI());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível carregar states.csv: " + ex.getMessage());
            return;
        }

        Path clientsPath = Path.of("clients.txt");

        ClientRepository clientRepo  = new ClientTxtRepository(clientsPath);
        StatesCSVRepository statesRepo = new StatesCSVRepository(statesPath);

        ValidateCpfService   cpfSvc    = new ValidateCpfService();
        ValidateCnpjService  cnpjSvc   = new ValidateCnpjService();
        ValidateEmailService emailSvc  = new ValidateEmailService();
        AuthService          authSvc   = new AuthService();
        ClientService        clientSvc = new ClientService(clientRepo);
        ClientReportService  reportSvc = new ClientReportService(clientRepo);
        StatesService        statesSvc = new StatesService(statesRepo);

        MainFrame frame = new MainFrame(cpfSvc, cnpjSvc, emailSvc, authSvc,
                clientSvc, reportSvc, statesSvc);
        applyIcon(frame);
        SwingUtilities.invokeLater(frame);
    }
}
