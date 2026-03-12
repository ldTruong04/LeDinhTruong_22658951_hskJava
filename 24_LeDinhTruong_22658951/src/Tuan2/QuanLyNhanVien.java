package Tuan2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuanLyNhanVien extends JFrame implements ActionListener {

    private JTextField txtMaNV, txtHo, txtTen, txtTuoi, txtTienLuong, txtTimKiem;
    private JCheckBox chkNu;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnLuu;

    public QuanLyNhanVien() {
        setTitle("^-^");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- North: Tiêu đề ---
        JLabel lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLUE);
        add(lblTitle, BorderLayout.NORTH);

        // --- Center: Nhập liệu và Bảng ---
        JPanel pnCenter = new JPanel(new BorderLayout());
        
        // Form nhập liệu
        JPanel pnInput = new JPanel(null);
        pnInput.setPreferredSize(new Dimension(0, 150));

        JLabel lblMa = new JLabel("Mã nhân viên:"); lblMa.setBounds(10, 10, 100, 25);
        txtMaNV = new JTextField(); txtMaNV.setBounds(110, 10, 660, 25);

        JLabel lblHo = new JLabel("Họ:"); lblHo.setBounds(10, 40, 100, 25);
        txtHo = new JTextField(); txtHo.setBounds(110, 40, 250, 25);
        JLabel lblTen = new JLabel("Tên nhân viên:"); lblTen.setBounds(370, 40, 100, 25);
        txtTen = new JTextField(); txtTen.setBounds(470, 40, 300, 25);

        JLabel lblTuoi = new JLabel("Tuổi:"); lblTuoi.setBounds(10, 70, 100, 25);
        txtTuoi = new JTextField(); txtTuoi.setBounds(110, 70, 550, 25);
        JLabel lblPhai = new JLabel("Phái:"); lblPhai.setBounds(670, 70, 40, 25);
        chkNu = new JCheckBox("Nữ"); chkNu.setBounds(710, 70, 60, 25);

        JLabel lblLuong = new JLabel("Tiền lương:"); lblLuong.setBounds(10, 100, 100, 25);
        txtTienLuong = new JTextField(); txtTienLuong.setBounds(110, 100, 660, 25);

        pnInput.add(lblMa); pnInput.add(txtMaNV);
        pnInput.add(lblHo); pnInput.add(txtHo);
        pnInput.add(lblTen); pnInput.add(txtTen);
        pnInput.add(lblTuoi); pnInput.add(txtTuoi);
        pnInput.add(lblPhai); pnInput.add(chkNu);
        pnInput.add(lblLuong); pnInput.add(txtTienLuong);

        // Bảng dữ liệu
        String[] headers = {"Mã NV", "Họ", "Tên", "Phái", "Tuổi", "Tiền lương"};
        model = new DefaultTableModel(headers, 0);
        table = new JTable(model);
        
        // Thêm ComboBox vào cột "Phái" (TableCellEditor)
        JComboBox<String> comboPhai = new JComboBox<>(new String[]{"Nam", "Nữ"});
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboPhai));

        pnCenter.add(pnInput, BorderLayout.NORTH);
        pnCenter.add(new JScrollPane(table), BorderLayout.CENTER);
        add(pnCenter, BorderLayout.CENTER);

        // --- South: Chức năng ---
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        JPanel pnTim = new JPanel();
        pnTim.add(new JLabel("Nhập mã số cần tìm:"));
        txtTimKiem = new JTextField(10); pnTim.add(txtTimKiem);
        btnTim = new JButton("Tìm"); pnTim.add(btnTim);

        JPanel pnChucNang = new JPanel();
        btnThem = new JButton("Thêm");
        btnXoaTrang = new JButton("Xóa trắng");
        btnXoa = new JButton("Xóa");
        btnLuu = new JButton("Lưu");
        pnChucNang.add(btnThem); pnChucNang.add(btnXoaTrang);
        pnChucNang.add(btnXoa); pnChucNang.add(btnLuu);

        split.setLeftComponent(pnTim);
        split.setRightComponent(pnChucNang);
        add(split, BorderLayout.SOUTH);

        // Đăng ký sự kiện
        btnTim.addActionListener(this);
        btnThem.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        btnXoa.addActionListener(this);
        btnLuu.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) xuLyThem();
        else if (o.equals(btnXoaTrang)) xuLyXoaTrang();
        else if (o.equals(btnXoa)) xuLyXoa();
        else if (o.equals(btnTim)) xuLyTim();
    }

    private void xuLyThem() {
        String ma = txtMaNV.getText();
        String ho = txtHo.getText();
        String ten = txtTen.getText();
        String phai = chkNu.isSelected() ? "Nữ" : "Nam";
        String tuoi = txtTuoi.getText();
        String luong = txtTienLuong.getText();
        
        Object[] row = {ma, ho, ten, phai, tuoi, luong};
        model.addRow(row);
    }

    private void xuLyXoaTrang() {
        txtMaNV.setText("");
        txtHo.setText("");
        txtTen.setText("");
        txtTuoi.setText("");
        txtTienLuong.setText("");
        chkNu.setSelected(false);
        txtMaNV.requestFocus();
    }

    private void xuLyXoa() {
        int row = table.getSelectedRow();
        if (row != -1) {
            model.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa!");
        }
    }

    private void xuLyTim() {
        String maTim = txtTimKiem.getText();
        for (int i = 0; i < table.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equalsIgnoreCase(maTim)) {
                table.setRowSelectionInterval(i, i);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Không tìm thấy!");
    }

    public static void main(String[] args) {
        new QuanLyNhanVien().setVisible(true);
    }
}
