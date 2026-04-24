package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhanVien_Dao;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVien_GUI extends JFrame implements ActionListener {

    private JTable table;
    private JTextField txtMaNV, txthoTen, txtTuoi, txtTienLuong, txtTim;
    private JButton btnTim, btnThem, btnXoa, btnLuu, btnXoaTrang, btnSua;
    private DefaultTableModel tableModel;
    private JComboBox<String> cboPhong;

    private NhanVien_Dao dao;

    public NhanVien_GUI() {

        // KẾT NỐI DB
        ConnectDB.getInstance().connect();
        dao = new NhanVien_Dao();

        setTitle("QUẢN LÝ NHÂN VIÊN");
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel pnlNorth;
        add(pnlNorth = new JPanel(), BorderLayout.NORTH);
        JLabel lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);
        pnlNorth.add(lblTieuDe);

        Box b = Box.createVerticalBox();

        Box b1, b2, b3, b4, b5;
        JLabel lblMaNV, lblhoTen, lblTuoi, lblTienLuong, lblPhong;

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMaNV = new JLabel("Mã NV: "));
        b1.add(txtMaNV = new JTextField());

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblhoTen = new JLabel("Họ tên: "));
        b2.add(txthoTen = new JTextField());

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblTuoi = new JLabel("Tuổi: "));
        b3.add(txtTuoi = new JTextField());

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblPhong = new JLabel("Phòng: "));
        b4.add(cboPhong = new JComboBox<>(new String[]{
        	    "PHONG_TC", "PHONG_KT", "PHONG_NS"
        }));

        b4.add(lblTienLuong = new JLabel("Lương: "));
        b4.add(txtTienLuong = new JTextField());

        b.add(b5 = Box.createHorizontalBox());
        String[] headers = "MaNV;Họ tên;Tuổi;Phòng;Tiền lương".split(";");
        tableModel = new DefaultTableModel(headers, 0);
        table = new JTable(tableModel);
        b5.add(new JScrollPane(table));

        add(b, BorderLayout.CENTER);

        JPanel pSouth = new JPanel();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        pSouth.add(btnThem);
        pSouth.add(btnSua);
        pSouth.add(btnXoa);

        add(pSouth, BorderLayout.SOUTH);

        // EVENT
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtMaNV.setText(table.getValueAt(row, 0).toString());
                txthoTen.setText(table.getValueAt(row, 1).toString());
                txtTuoi.setText(table.getValueAt(row, 2).toString());
                cboPhong.setSelectedItem(table.getValueAt(row, 3).toString());
                txtTienLuong.setText(table.getValueAt(row, 4).toString());
            }
        });

        loadData();
    }

    // LOAD DATA
    public void loadData() {
        tableModel.setRowCount(0);
        for (NhanVien nv : dao.getalltbNhanVien()) {
            tableModel.addRow(new Object[]{
                    nv.getMaNV(),
                    nv.getHoten(),
                    nv.getTuoi(),
                    nv.getPhong().getMaPhong(),
                    nv.getTienluong()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            String ma = txtMaNV.getText();
            String ten = txthoTen.getText();
            int tuoi = Integer.parseInt(txtTuoi.getText());
            String phong = cboPhong.getSelectedItem().toString();
            double luong = Double.parseDouble(txtTienLuong.getText());

            NhanVien nv = new NhanVien(ma, ten, tuoi, new PhongBan(phong), luong);

            if (e.getSource() == btnThem) {
                dao.create(nv);
            }

            if (e.getSource() == btnSua) {
                dao.update(nv);
            }

            if (e.getSource() == btnXoa) {
                dao.delete(ma);
            }

            loadData();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!");
        }
    }

    public static void main(String[] args) {
        new NhanVien_GUI().setVisible(true);
    }
}