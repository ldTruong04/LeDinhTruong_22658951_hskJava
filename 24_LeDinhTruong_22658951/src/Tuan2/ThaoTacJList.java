package Tuan2;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ThaoTacJList extends JFrame implements ActionListener {

    private JList<Integer> list;
    private DefaultListModel<Integer> model;
    private JTextField txtNhap;
    private JCheckBox chkAm;
    private JButton btnNhap, btnChan, btnLe, btnNguyenTo, btnBoToDen, btnXoaToDen, btnTong, btnDong;

    public ThaoTacJList() {
        setTitle("Thao tác trên JList");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- North: Tiêu đề ---
        JLabel lblTitle = new JLabel("Thao tác trên JList - Checkbox", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLUE);
        add(lblTitle, BorderLayout.NORTH);

        // --- West: Chọn tác vụ ---
        JPanel pnWest = new JPanel();
        pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));
        pnWest.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "Chọn tác vụ"));
        
        btnChan = new JButton("Tô đen số chẵn");
        btnLe = new JButton("Tô đen số lẻ");
        btnNguyenTo = new JButton("Tô đen số nguyên tố");
        btnBoToDen = new JButton("Bỏ tô đen");
        btnXoaToDen = new JButton("Xóa các giá trị đang tô đen");
        btnTong = new JButton("Tổng giá trị trong JList");

        // Căn lề và đăng ký sự kiện cho các nút bên trái
        JButton[] buttons = {btnChan, btnLe, btnNguyenTo, btnBoToDen, btnXoaToDen, btnTong};
        for (JButton btn : buttons) {
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setMaximumSize(new Dimension(250, 30));
            btn.addActionListener(this);
            pnWest.add(btn);
            pnWest.add(Box.createVerticalStrut(10));
        }
        add(pnWest, BorderLayout.WEST);

        // --- Center: Nhập thông tin và Hiển thị ---
        JPanel pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "Nhập thông tin:"));

        JPanel pnNhap = new JPanel();
        btnNhap = new JButton("Nhập");
        txtNhap = new JTextField(10);
        chkAm = new JCheckBox("Cho nhập số âm");
        pnNhap.add(btnNhap); pnNhap.add(txtNhap); pnNhap.add(chkAm);
        btnNhap.addActionListener(this);

        model = new DefaultListModel<>();
        list = new JList<>(model);
        JScrollPane scroll = new JScrollPane(list);

        pnCenter.add(pnNhap, BorderLayout.NORTH);
        pnCenter.add(scroll, BorderLayout.CENTER);
        add(pnCenter, BorderLayout.CENTER);

        // --- South: Đóng chương trình ---
        JPanel pnSouth = new JPanel();
        btnDong = new JButton("Đóng chương trình");
        btnDong.addActionListener(this);
        pnSouth.add(btnDong);
        pnSouth.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(pnSouth, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnNhap)) xuLyNhap();
        else if (o.equals(btnChan)) xuLyToDenChan();
        else if (o.equals(btnLe)) xuLyToDenLe();
        else if (o.equals(btnNguyenTo)) xuLyToDenNguyenTo();
        else if (o.equals(btnBoToDen)) list.clearSelection();
        else if (o.equals(btnXoaToDen)) xuLyXoaToDen();
        else if (o.equals(btnTong)) xuLyTong();
        else if (o.equals(btnDong)) System.exit(0);
    }

    private void xuLyNhap() {
        try {
            int n = Integer.parseInt(txtNhap.getText());
            if (n < 0 && !chkAm.isSelected()) {
                JOptionPane.showMessageDialog(this, "Chưa cho phép nhập số âm!");
            } else {
                model.addElement(n);
                txtNhap.setText("");
                txtNhap.requestFocus();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên!");
        }
    }

    private void xuLyToDenChan() {
        int[] indices = new int[model.size()];
        int count = 0;
        for (int i = 0; i < model.size(); i++) {
            if (model.getElementAt(i) % 2 == 0) indices[count++] = i;
        }
        list.setSelectedIndices(java.util.Arrays.copyOf(indices, count));
    }

    private void xuLyToDenLe() {
        int[] indices = new int[model.size()];
        int count = 0;
        for (int i = 0; i < model.size(); i++) {
            if (model.getElementAt(i) % 2 != 0) indices[count++] = i;
        }
        list.setSelectedIndices(java.util.Arrays.copyOf(indices, count));
    }

    private void xuLyToDenNguyenTo() {
        int[] indices = new int[model.size()];
        int count = 0;
        for (int i = 0; i < model.size(); i++) {
            if (isPrime(model.getElementAt(i))) indices[count++] = i;
        }
        list.setSelectedIndices(java.util.Arrays.copyOf(indices, count));
    }

    private void xuLyXoaToDen() {
        List<Integer> selectedValues = list.getSelectedValuesList();
        for (Integer val : selectedValues) {
            model.removeElement(val);
        }
    }

    private void xuLyTong() {
        int tong = 0;
        for (int i = 0; i < model.size(); i++) {
            tong += model.getElementAt(i);
        }
        JOptionPane.showMessageDialog(this, "Tổng giá trị trong JList là: " + tong);
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new ThaoTacJList().setVisible(true);
    }
}
