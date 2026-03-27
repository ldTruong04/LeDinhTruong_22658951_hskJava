package Tuan2_QuanLyNhanVien;

public class Starting {
    public static void main(String[] args) throws Exception {
        DanhSachNhanVien dao = new DanhSachNhanVien();
        new frmNhanVien(dao).setVisible(true);
    }
}