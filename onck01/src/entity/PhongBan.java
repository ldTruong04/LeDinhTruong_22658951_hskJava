package entity;


public class PhongBan {
    private String maPhong;
    private String tenPhong;

    public PhongBan() {
    }

    public PhongBan(String maPhong) {
        super();
        this.maPhong = maPhong;
    }

    public PhongBan(String maPhong, String tenPhong) {
        super();
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    @Override
    public String toString() {
        return "PhongBan [maPhongBan=" + maPhong + ", tenPhongBan=" + tenPhong + "]";
    }
}