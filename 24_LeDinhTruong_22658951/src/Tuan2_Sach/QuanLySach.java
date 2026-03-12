package Tuan2_Sach;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class QuanLySach extends JFrame implements ActionListener {

    private JTextField txtMa, txtTua, txtTacGia, txtNam, txtNhaXB, txtSoTrang, txtDonGia, txtISBN;
    private JButton btnThem, btnXoaRong, btnXoa, btnSua, btnLuu;
    private JComboBox<String> cboTim;
    private JTable table;
    private DefaultTableModel model;
    private List<Sach> dsSach = new ArrayList<>();
    private final String FILE_PATH = "data/DuLieu.txt";

    public QuanLySach() {
        setTitle("Quản lý sách");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Giao diện phía trên (Input) ---
        JPanel pnNorth = new JPanel();
        pnNorth.setBorder(BorderFactory.createTitledBorder("Records Editor"));
        pnNorth.setLayout(new GridLayout(5, 2, 10, 5));

        pnNorth.add(new JLabel("Mã sách:")); txtMa = new JTextField(); pnNorth.add(txtMa);
        pnNorth.add(new JLabel("Tựa sách:")); txtTua = new JTextField(); pnNorth.add(txtTua);
        pnNorth.add(new JLabel("Tác giả:")); txtTacGia = new JTextField(); pnNorth.add(txtTacGia);
        pnNorth.add(new JLabel("Năm xuất bản:")); txtNam = new JTextField(); pnNorth.add(txtNam);
        pnNorth.add(new JLabel("Nhà xuất bản:")); txtNhaXB = new JTextField(); pnNorth.add(txtNhaXB);
        pnNorth.add(new JLabel("Số trang:")); txtSoTrang = new JTextField(); pnNorth.add(txtSoTrang);
        pnNorth.add(new JLabel("Đơn giá:")); txtDonGia = new JTextField(); pnNorth.add(txtDonGia);
        pnNorth.add(new JLabel("ISBN:")); txtISBN = new JTextField(); pnNorth.add(txtISBN);
        
        add(pnNorth, BorderLayout.NORTH);

        // --- Giao diện giữa (Buttons & Table) ---
        JPanel pnCenter = new JPanel(new BorderLayout());
        
        // Buttons
        JPanel pnButtons = new JPanel();
        btnThem = new JButton("Thêm"); btnXoaRong = new JButton("Xóa rỗng");
        btnXoa = new JButton("Xóa"); btnSua = new JButton("Sửa"); btnLuu = new JButton("Lưu");
        cboTim = new JComboBox<>(); cboTim.setEditable(false);
        
        pnButtons.add(btnThem); pnButtons.add(btnXoaRong); pnButtons.add(btnXoa);
        pnButtons.add(btnSua); pnButtons.add(btnLuu);
        pnButtons.add(new JLabel("Tìm theo mã sách:")); pnButtons.add(cboTim);
        
        // Table
        String[] headers = {"MaSach", "TuaSach", "TacGia", "NamXuatBan", "NhaXuatBan", "SoTrang", "DonGia", "ISBN"};
        model = new DefaultTableModel(headers, 0);
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        
        pnCenter.add(pnButtons, BorderLayout.NORTH);
        pnCenter.add(new JScrollPane(table), BorderLayout.CENTER);
        add(pnCenter, BorderLayout.CENTER);

        // --- Đăng ký sự kiện ---
        btnThem.addActionListener(this); btnXoaRong.addActionListener(this);
        btnXoa.addActionListener(this); btnSua.addActionListener(this);
        btnLuu.addActionListener(this);
        cboTim.addActionListener(this);
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { xuLyHienThiComponent(); }
        });

        // Khởi tạo dữ liệu
        docFile();
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) xuLyThem();
        else if (o.equals(btnXoaRong)) xuLyXoaRong();
        else if (o.equals(btnXoa)) xuLyXoa();
        else if (o.equals(btnSua)) xuLySua();
        else if (o.equals(btnLuu)) xuLyGhiFile();
        else if (o.equals(cboTim)) xuLyTim();
    }

    // --- Các hàm xử lý chi tiết ---

    private void xuLyHienThiComponent() {
        int row = table.getSelectedRow();
        if (row != -1) {
            txtMa.setText(model.getValueAt(row, 0).toString());
            txtTua.setText(model.getValueAt(row, 1).toString());
            txtTacGia.setText(model.getValueAt(row, 2).toString());
            txtNam.setText(model.getValueAt(row, 3).toString());
            txtNhaXB.setText(model.getValueAt(row, 4).toString());
            txtSoTrang.setText(model.getValueAt(row, 5).toString());
            txtDonGia.setText(model.getValueAt(row, 6).toString());
            txtISBN.setText(model.getValueAt(row, 7).toString());
            txtMa.setEditable(false);
        }
    }

    private void xuLyThem() {
        if (kiemTraDuLieu()) {
            String ma = txtMa.getText();
            for (Sach s : dsSach) {
                if (s.getMaSach().equalsIgnoreCase(ma)) {
                    JOptionPane.showMessageDialog(this, "Mã sách đã tồn tại!");
                    return;
                }
            }
            Sach s = createSachFromInput();
            dsSach.add(s);
            capNhatGiaoDien();
            xuLyGhiFile();
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        }
    }

    private void xuLySua() {
        int row = table.getSelectedRow();
        if (row != -1 && kiemTraDuLieu()) {
            Sach sMoi = createSachFromInput();
            dsSach.set(row, sMoi);
            capNhatGiaoDien();
            xuLyGhiFile();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        }
    }

    private void xuLyXoa() {
        int row = table.getSelectedRow();
        if (row != -1) {
            if (JOptionPane.showConfirmDialog(this, "Xóa cuốn sách này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dsSach.remove(row);
                capNhatGiaoDien();
                xuLyGhiFile();
            }
        }
    }

    private void xuLyXoaRong() {
        txtMa.setText(""); txtTua.setText(""); txtTacGia.setText("");
        txtNam.setText(""); txtNhaXB.setText(""); txtSoTrang.setText("");
        txtDonGia.setText(""); txtISBN.setText("");
        txtMa.setEditable(true);
        txtMa.requestFocus();
    }

    private void xuLyTim() {
        String ma = (String) cboTim.getSelectedItem();
        for (int i = 0; i < table.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equals(ma)) {
                table.setRowSelectionInterval(i, i);
                table.scrollRectToVisible(table.getCellRect(i, 0, true));
                xuLyHienThiComponent();
                break;
            }
        }
    }

    // --- Xử lý File ---
    private void docFile() {
        try {
            File f = new File(FILE_PATH);
            if (!f.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(f));
            br.readLine(); // Bỏ qua dòng tiêu đề
            String line;
            while ((line = br.readLine()) != null) {
                String[] v = line.split(";");
                dsSach.add(new Sach(v[0], v[1], v[2], Integer.parseInt(v[3]), v[4], Integer.parseInt(v[5]), Double.parseDouble(v[6]), v[7]));
            }
            br.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void xuLyGhiFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH));
            pw.println("MaSach;TuaSach;TacGia;NamXuatBan;NhaXuatBan;SoTrang;DonGia;ISBN");
            for (Sach s : dsSach) pw.println(s.toString());
            pw.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // --- Helper Methods ---
    private void capNhatGiaoDien() {
        model.setRowCount(0);
        cboTim.removeAllItems();
        for (Sach s : dsSach) {
            model.addRow(new Object[]{s.getMaSach(), s.getTuaSach(), s.getTacGia(), s.getNamXuatBan(), s.getNhaXuatBan(), s.getSoTrang(), s.getDonGia(), s.getIsbn()});
            cboTim.addItem(s.getMaSach());
        }
    }

    private Sach createSachFromInput() {
        return new Sach(txtMa.getText(), txtTua.getText(), txtTacGia.getText(),
                Integer.parseInt(txtNam.getText()), txtNhaXB.getText(),
                txtSoTrang.getText().isEmpty() ? 0 : Integer.parseInt(txtSoTrang.getText()),
                txtDonGia.getText().isEmpty() ? 0 : Double.parseDouble(txtDonGia.getText()),
                txtISBN.getText());
    }

    private boolean kiemTraDuLieu() {
        if (!txtMa.getText().matches("[A-Z]\\d{3}")) {
            JOptionPane.showMessageDialog(this, "Mã sách: 1 chữ HOA và 3 ký số!"); return false;
        }
        if (txtTua.getText().isEmpty() || !txtTua.getText().matches("[\\w\\s\\-\\(\\)]+")) {
            JOptionPane.showMessageDialog(this, "Tựa sách không hợp lệ!"); return false;
        }
        if (txtTacGia.getText().isEmpty() || !txtTacGia.getText().matches("[a-zA-Z'\\s]+")) {
            JOptionPane.showMessageDialog(this, "Tác giả không chứa số!"); return false;
        }
        try {
            int nam = Integer.parseInt(txtNam.getText());
            int hienTai = Calendar.getInstance().get(Calendar.YEAR);
            if (nam < 1900 || nam > hienTai) throw new Exception();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Năm xuất bản từ 1900 đến hiện tại!"); return false;
        }
        if (!txtISBN.getText().matches("(\\d+\\-){3,4}\\d+")) {
            JOptionPane.showMessageDialog(this, "ISBN định dạng X-X-X-X hoặc X-X-X-X-X"); return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new QuanLySach().setVisible(true);
    }
}