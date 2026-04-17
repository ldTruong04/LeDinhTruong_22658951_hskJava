package Tuan8_bai1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FrmLopHoc extends JFrame implements ActionListener, MouseListener {

    private JTextField txtMaLop, txtTenLop, txtGVCN;
    private JButton btnThem, btnLuu, btnSua, btnXoa;
    private DefaultTableModel dataModel;
    private JTable table;

    DanhSachLopHoc dslh = new DanhSachLopHoc();

    public FrmLopHoc() {
        setTitle("Thông tin lớp học");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel p = new JPanel(new GridLayout(4,2));

        p.add(new JLabel("Mã lớp:"));
        txtMaLop = new JTextField();
        p.add(txtMaLop);

        p.add(new JLabel("Tên lớp:"));
        txtTenLop = new JTextField();
        p.add(txtTenLop);

        p.add(new JLabel("GVCN:"));
        txtGVCN = new JTextField();
        p.add(txtGVCN);

        add(p, BorderLayout.NORTH);

        String[] headers = {"Mã lớp", "Tên lớp", "GVCN"};
        dataModel = new DefaultTableModel(headers,0);
        table = new JTable(dataModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();

        btnThem = new JButton("Thêm");
        btnLuu = new JButton("Lưu");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        btnPanel.add(btnThem);
        btnPanel.add(btnLuu);
        btnPanel.add(btnSua);
        btnPanel.add(btnXoa);

        add(btnPanel, BorderLayout.SOUTH);

        btnThem.addActionListener(this);
        btnLuu.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        table.addMouseListener(this);

        Database.getInstance().connect();
        loadData();
    }

    private void loadData() {
        List<LopHoc> list = dslh.docTuBang();
        for (LopHoc s : list) {
            dataModel.addRow(new Object[]{
                s.getMaLop(),
                s.getTenLop(),
                s.getGiaoVienCN()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btnThem)) {
            clear();
        }

        else if (o.equals(btnLuu)) {
            LopHoc lh = new LopHoc(
                txtMaLop.getText(),
                txtTenLop.getText(),
                txtGVCN.getText()
            );

            if (dslh.create(lh)) {
                dataModel.addRow(new Object[]{
                    txtMaLop.getText(),
                    txtTenLop.getText(),
                    txtGVCN.getText()
                });
            }
        }

        else if (o.equals(btnSua)) {
            int row = table.getSelectedRow();
            if (row >= 0) {
                LopHoc lh = new LopHoc(
                    txtMaLop.getText(),txtTenLop.getText(),
                    txtGVCN.getText()
                );

                if (dslh.update(lh)) {
                    table.setValueAt(txtTenLop.getText(), row, 1);
                    table.setValueAt(txtGVCN.getText(), row, 2);
                }
            }
        }

        else if (o.equals(btnXoa)) {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String ma = table.getValueAt(row, 0).toString();
                if (dslh.delete(ma)) {
                    dataModel.removeRow(row);
                }
            }
        }
    }

    private void clear() {
        txtMaLop.setText("");
        txtTenLop.setText("");
        txtGVCN.setText("");
        txtMaLop.requestFocus();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        txtMaLop.setText(table.getValueAt(row,0).toString());
        txtTenLop.setText(table.getValueAt(row,1).toString());
        txtGVCN.setText(table.getValueAt(row,2).toString());
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
