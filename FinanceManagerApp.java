import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

public class FinanceManagerApp {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField inputAmount;
    private JComboBox<String> categoryComboBox;
    private JTextField customCategoryField;
    private JComboBox<String> monthComboBox;
    private JLabel totalLabel, incomeLabel, errorLabel;
    private int income = 0;
    private Map<String, DefaultTableModel> monthData;

    public FinanceManagerApp() {
        // Initialize data storage
        monthData = new HashMap<>();

        // Frame setup
        frame = new JFrame("Aplikasi Manajemen Keuangan Mahasiswa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        // Custom font and colors
        Font headerFont = new Font("Verdana", Font.BOLD, 18);
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color bgColor = new Color(245, 245, 245);
        Color buttonColor = new Color(70, 130, 180);
        Color expenseColor = Color.RED;
        Color incomeColor = Color.BLUE;

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Aplikasi Manajemen Keuangan Mahasiswa");
        headerLabel.setFont(headerFont);
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(bgColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Pemasukan awal
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel incomeLabelText = new JLabel("Jumlah Pemasukan Awal:");
        incomeLabelText.setFont(labelFont);
        inputPanel.add(incomeLabelText, gbc);

        gbc.gridx = 1;
        JTextField inputIncome = new JTextField(10);
        inputPanel.add(inputIncome, gbc);

        gbc.gridx = 2;
        JButton setIncomeButton = new JButton("Set Pemasukan");
        setIncomeButton.setBackground(buttonColor);
        setIncomeButton.setForeground(Color.WHITE);
        inputPanel.add(setIncomeButton, gbc);

        // Input pengeluaran
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel amountLabel = new JLabel("Jumlah Uang:");
        amountLabel.setFont(labelFont);
        inputPanel.add(amountLabel, gbc);

        gbc.gridx = 1;
        inputAmount = new JTextField(10);
        inputPanel.add(inputAmount, gbc);

        // Kategori
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel categoryLabel = new JLabel("Kategori (Pilih/Tulis):");
        categoryLabel.setFont(labelFont);
        inputPanel.add(categoryLabel, gbc);

        gbc.gridx = 1;
        String[] categories = {"Makanan", "Transport", "Pakaian", "Pendidikan", "Kecantikan", "Kebutuhan Harian", "Kesehatan", "Lainnya"};
        categoryComboBox = new JComboBox<>(categories);
        inputPanel.add(categoryComboBox, gbc);

        gbc.gridx = 2;
        customCategoryField = new JTextField(10);
        inputPanel.add(customCategoryField, gbc);

        // Pilihan Bulan
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel monthLabel = new JLabel("Bulan:");
        monthLabel.setFont(labelFont);
        inputPanel.add(monthLabel, gbc);

        gbc.gridx = 1;
        String[] months = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        monthComboBox = new JComboBox<>(months);
        inputPanel.add(monthComboBox, gbc);

        // Add button
        gbc.gridx = 1;
        gbc.gridy = 4;
        JButton addButton = new JButton("Tambah Catatan");
        addButton.setBackground(buttonColor);
        addButton.setForeground(Color.WHITE);
        inputPanel.add(addButton, gbc);

        // Error Label
        gbc.gridx = 1;
        gbc.gridy = 5;
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        inputPanel.add(errorLabel, gbc);

        // Lihat Laporan Button
        JButton viewReportButton = new JButton("Lihat Laporan");
        viewReportButton.setBackground(buttonColor);
        viewReportButton.setForeground(Color.WHITE);
        inputPanel.add(viewReportButton, gbc);

        // Table
        String[] columnNames = {"Jumlah (Rp)", "Kategori", "Bulan"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setFont(labelFont);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(bgColor);
        totalLabel = new JLabel("Total Pengeluaran: Rp 0", SwingConstants.CENTER);
        totalLabel.setFont(headerFont);
        totalLabel.setForeground(expenseColor);
        bottomPanel.add(totalLabel);

        incomeLabel = new JLabel("Sisa Pemasukan: Rp 0", SwingConstants.CENTER);
        incomeLabel.setFont(headerFont);
        incomeLabel.setForeground(incomeColor);
        bottomPanel.add(incomeLabel);

        // Button Actions
        setIncomeButton.addActionListener(e -> {
            try {
                income = Integer.parseInt(inputIncome.getText());
                updateTableForMonth();
                errorLabel.setText("");
            } catch (NumberFormatException ex) {
                errorLabel.setText("Input pemasukan harus berupa angka!");
            }
        });

        addButton.addActionListener(e -> addRecord());

        monthComboBox.addActionListener(e -> updateTableForMonth());

        viewReportButton.addActionListener(e -> showMonthlyReport());

        // Add panels to frame
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void addRecord() {
        try {
            int amount = Integer.parseInt(inputAmount.getText());
            String category = customCategoryField.getText().trim();
            String month = (String) monthComboBox.getSelectedItem();
            if (category.isEmpty()) {
                category = (String) categoryComboBox.getSelectedItem();
            }

            // Get the model for the selected month
            DefaultTableModel monthModel = monthData.computeIfAbsent(month, k -> new DefaultTableModel(new String[]{"Jumlah (Rp)", "Kategori", "Bulan"}, 0));

            // Update Table
            Vector<Object> row = new Vector<>();
            row.add(formatCurrency(amount));
            row.add(category);
            row.add(month);
            monthModel.addRow(row);

            if (month.equals(monthComboBox.getSelectedItem())) {
                updateTableForMonth();
            }

            inputAmount.setText("");
            customCategoryField.setText("");
            errorLabel.setText("");

        } catch (NumberFormatException ex) {
            errorLabel.setText("Input jumlah uang harus berupa angka!");
        } catch (Exception ex) {
            errorLabel.setText(ex.getMessage());
        }
    }

    private void updateTableForMonth() {
        String selectedMonth = (String) monthComboBox.getSelectedItem();
        DefaultTableModel monthModel = monthData.getOrDefault(selectedMonth, new DefaultTableModel(new String[]{"Jumlah (Rp)", "Kategori", "Bulan"}, 0));

        model.setRowCount(0);

        int monthExpenses = 0;
        for (int i = 0; i < monthModel.getRowCount(); i++) {
            Vector<Object> row = new Vector<>();
            for (int j = 0; j < monthModel.getColumnCount(); j++) {
                row.add(monthModel.getValueAt(i, j));
            }
            monthExpenses += parseCurrency((String) monthModel.getValueAt(i, 0));
            model.addRow(row);
        }

        totalLabel.setText("Total Pengeluaran: " + formatCurrency(monthExpenses));
        int remainingIncome = Math.max(0, income - monthExpenses);
        incomeLabel.setText("Sisa Pemasukan: " + formatCurrency(remainingIncome));
    }

    private void showMonthlyReport() {
        String selectedMonth = (String) monthComboBox.getSelectedItem();
        DefaultTableModel monthModel = monthData.getOrDefault(selectedMonth, new DefaultTableModel(new String[]{"Jumlah (Rp)", "Kategori", "Bulan"}, 0));

        Map<String, Integer> categoryTotals = new HashMap<>();
        int totalExpenses = 0;

        for (int i = 0; i < monthModel.getRowCount(); i++) {
            String category = (String) monthModel.getValueAt(i, 1);
            int amount = parseCurrency((String) monthModel.getValueAt(i, 0));
            categoryTotals.put(category, categoryTotals.getOrDefault(category, 0) + amount);
            totalExpenses += amount;
        }

        StringBuilder report = new StringBuilder("Laporan Pengeluaran Bulan " + selectedMonth + ":\n");
        categoryTotals.forEach((category, total) -> report.append("- ").append(category).append(": ").append(formatCurrency(total)).append("\n"));
        report.append("Total Pengeluaran: ").append(formatCurrency(totalExpenses)).append("\n");
        report.append("Sisa Pemasukan: ").append(formatCurrency(Math.max(income - totalExpenses, 0)));

        JOptionPane.showMessageDialog(frame, report.toString(), "Laporan Bulanan", JOptionPane.INFORMATION_MESSAGE);
    }

    private String formatCurrency(int amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return currencyFormat.format(amount);
    }

    private int parseCurrency(String amountText) {
        try {
            return NumberFormat.getCurrencyInstance(new Locale("id", "ID")).parse(amountText).intValue();
        } catch (Exception ex) {
            return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinanceManagerApp::new);
    }
}