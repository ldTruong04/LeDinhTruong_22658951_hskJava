package Tuan2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


import java.util.Properties;

public class PhongToChuc extends JFrame implements ActionListener {

    private JTable table;
    private DefaultTableModel model;
    private JButton btnThem, btnXoa, btnThoat;

    public PhongToChuc() {
        setTitle("Phòng tổ chức");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Thiết lập bảng (Center) ---
        String[] headers = {"Mã số", "Họ", "Tên nhân viên", "Phái", "Ngày sinh", "Tiền lương"};
        model = new DefaultTableModel(headers, 0);
        table = new JTable(model);
        
        setupTableControls(); // Thiết lập JComboBox và JDatePicker cho bảng

        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Thanh chức năng (South) ---
        JPanel pnSouth = new JPanel();
        btnThem = new JButton("Thêm dòng");
        btnXoa = new JButton("Xóa dòng");
        btnThoat = new JButton("Thoát");

        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnThoat.addActionListener(this);

        pnSouth.add(btnThem); pnSouth.add(btnXoa); pnSouth.add(btnThoat);
        add(pnSouth, BorderLayout.SOUTH);

        // Thêm dữ liệu mẫu
        model.addRow(new Object[]{"NV111", "Nguyễn", "An", "Nam", "01/03/1997", "3,000 $"});
    }

    /**
     * Thiết lập TableCellEditor cho các cột đặc biệt (Phái và Ngày sinh)
     */
    private void setupTableControls() {
        // 1. Cột Phái: Sử dụng JComboBox
        TableColumn colPhai = table.getColumnModel().getColumn(3);
        JComboBox<String> cboPhai = new JComboBox<>(new String[]{"Nam", "Nữ"});
        colPhai.setCellEditor(new DefaultCellEditor(cboPhai));

       
    }

    /**
     * Hàm điều hướng sự kiện
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            xuLyThemDong();
        } else if (o.equals(btnXoa)) {
            xuLyXoaDong();
        } else if (o.equals(btnThoat)) {
            System.exit(0);
        }
    }

    private void xuLyThemDong() {
        model.addRow(new Object[]{"", "", "", "Nam", "01/01/2000", "0 $"});
    }

    private void xuLyXoaDong() {
        int row = table.getSelectedRow();
        if (row != -1) {
            model.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
        }
    }

    /**
     * Tạo TableCellEditor chứa JDatePicker cho bảng
     */
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PhongToChuc().setVisible(true));
    }
}