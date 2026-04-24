package entity;

public class NhanVien {
    private String maNV;
    private String hoten;
    private int tuoi;
    private PhongBan phong;
    private double tienluong;

    public NhanVien() {
        super();
    }

    public NhanVien(String maNV, String hoten, int tuoi, PhongBan phong, double tienluong) {
        super();
        this.maNV = maNV;
        this.hoten = hoten;
        this.tuoi = tuoi;
        this.phong = phong;
        this.tienluong = tienluong;
    }

    public NhanVien(String maNV) {
        super();
        this.maNV = maNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public PhongBan getPhong() {
        return phong;
    }

    public void setPhong(PhongBan phong) {
        this.phong = phong;
    }

    public double getTienluong() {
        return tienluong;
    }

    public void setTienluong(double tienluong) {
        this.tienluong = tienluong;
    }
}