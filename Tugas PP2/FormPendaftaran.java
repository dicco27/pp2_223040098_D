import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;

public class FormPendaftaran extends JFrame implements ActionListener, ItemListener {

    private JTextField kotakNama;
    private JCheckBox checkBoxWNA;
    private JRadioButton radioLaki;
    private JRadioButton radioPerempuan;
    private ButtonGroup bg;
    private boolean isCheckBoxSelected;
    private JList<String> listTabungan;
    private JSlider sliderFrekuensiTransaksi;
    private JPasswordField passwordField;
    private JSpinner spinnerTanggalLahir;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuReset;
    private JMenuItem menuExit;
    private JTable table;
    private DefaultTableModel tableModel;

    public FormPendaftaran() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen mode
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuReset = new JMenuItem("Reset");
        menuExit = new JMenuItem("Exit");
        menu.add(menuReset);
        menu.add(menuExit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        menuReset.addActionListener(e -> resetForm());
        menuExit.addActionListener(e -> System.exit(0));

        JLabel labelNama = new JLabel("Nama:");
        kotakNama = new JTextField(20);

        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        radioLaki = new JRadioButton("Laki-Laki");
        radioPerempuan = new JRadioButton("Perempuan");
        bg = new ButtonGroup();
        bg.add(radioLaki);
        bg.add(radioPerempuan);

        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        spinnerTanggalLahir = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd-MM-yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);

        JLabel labelStatusWNA = new JLabel("Status Warga Negara:");
        checkBoxWNA = new JCheckBox("Warga Negara Asing");
        checkBoxWNA.addItemListener(this);

        JLabel labelTabungan = new JLabel("Jenis Tabungan:");
        String[] tabunganOptions = {
                "Tabungan Masa Depan",
                "Tabungan Pendidikan",
                "Tabungan Haji",
                "Tabungan Crypto"
        };
        listTabungan = new JList<>(tabunganOptions);
        JScrollPane scrollPaneTabungan = new JScrollPane(listTabungan);
        listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPaneTabungan.setPreferredSize(new Dimension(200, 60));

        JLabel labelFrekuensi = new JLabel("Frekuensi Transaksi:");
        sliderFrekuensiTransaksi = new JSlider(0, 100, 0);
        sliderFrekuensiTransaksi.setMajorTickSpacing(10);
        sliderFrekuensiTransaksi.setMinorTickSpacing(10);
        sliderFrekuensiTransaksi.setPaintTicks(true);
        sliderFrekuensiTransaksi.setPaintLabels(true);
        sliderFrekuensiTransaksi.setPreferredSize(new Dimension(300, 50));

        JLabel labelPassword = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        JButton tombol = new JButton("Simpan");
        tombol.addActionListener(this);

        // Layout configuration
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(labelNama, gbc);

        gbc.gridx = 1;
        add(kotakNama, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelJenisKelamin, gbc);

        gbc.gridx = 1;
        add(radioLaki, gbc);

        gbc.gridy = 3;
        add(radioPerempuan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelTanggalLahir, gbc);

        gbc.gridx = 1;
        add(spinnerTanggalLahir, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(labelStatusWNA, gbc);

        gbc.gridx = 1;
        add(checkBoxWNA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(labelTabungan, gbc);

        gbc.gridx = 1;
        add(scrollPaneTabungan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(labelFrekuensi, gbc);

        gbc.gridx = 1;
        add(sliderFrekuensiTransaksi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(labelPassword, gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        add(tombol, gbc);

        // Tabel untuk menampilkan data
        String[] columnNames = { "Nama", "Jenis Kelamin", "Tanggal Lahir", "Status WNA", "Jenis Tabungan",
                "Frekuensi Transaksi" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(600, 400));

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 10;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.8;
        gbc.weighty = 1;
        add(tableScrollPane, gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void resetForm() {
        kotakNama.setText("");
        bg.clearSelection();
        checkBoxWNA.setSelected(false);
        listTabungan.clearSelection();
        sliderFrekuensiTransaksi.setValue(0);
        passwordField.setText("");
        spinnerTanggalLahir.setValue(new java.util.Date());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        isCheckBoxSelected = e.getStateChange() == ItemEvent.SELECTED;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = kotakNama.getText();
        String jenisKelamin = radioLaki.isSelected() ? "Laki-Laki" : "Perempuan";
        String wnaStatus = isCheckBoxSelected ? "Warga Negara Asing" : "Warga Negara Indonesia";
        String tabunganTerpilih = listTabungan.getSelectedValue();
        String frekuensiTransaksi = String.valueOf(sliderFrekuensiTransaksi.getValue());
        String password = new String(passwordField.getPassword());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String tanggalLahir = dateFormat.format(spinnerTanggalLahir.getValue());

        tableModel.addRow(
                new Object[] { nama, jenisKelamin, tanggalLahir, wnaStatus, tabunganTerpilih, frekuensiTransaksi });
    }

    public static void main(String[] args) {
        new FormPendaftaran();
    }
}
