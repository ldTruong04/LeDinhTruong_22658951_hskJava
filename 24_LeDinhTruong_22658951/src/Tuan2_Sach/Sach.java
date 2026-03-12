package Tuan2_Sach;

public class Sach {
    private String maSach, tuaSach, tacGia, nhaXuatBan, isbn;
    private int namXuatBan, soTrang;
    private double donGia;

    public Sach(String maSach, String tuaSach, String tacGia, int namXuatBan, String nhaXuatBan, int soTrang, double donGia, String isbn) {
        this.maSach = maSach;
        this.tuaSach = tuaSach;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.nhaXuatBan = nhaXuatBan;
        this.soTrang = soTrang;
        this.donGia = donGia;
        this.isbn = isbn;
    }

    // Các hàm Getter/Setter và toString phục vụ việc ghi file
    public String getMaSach() { return maSach; }
    public String getTuaSach() { return tuaSach; }
    public String getTacGia() { return tacGia; }
    public int getNamXuatBan() { return namXuatBan; }
    public String getNhaXuatBan() { return nhaXuatBan; }
    public int getSoTrang() { return soTrang; }
    public double getDonGia() { return donGia; }
    public String getIsbn() { return isbn; }

    @Override
    public String toString() {
        return maSach + ";" + tuaSach + ";" + tacGia + ";" + namXuatBan + ";" + nhaXuatBan + ";" + soTrang + ";" + (long)donGia + ";" + isbn;
    }
}
