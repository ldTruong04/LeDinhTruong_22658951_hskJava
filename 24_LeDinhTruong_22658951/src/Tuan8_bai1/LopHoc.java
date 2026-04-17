package Tuan8_bai1;

public class LopHoc {
    private String maLop;
    private String tenLop;
    private String giaoVienCN;

    public LopHoc(String maLop, String tenLop, String giaoVienCN) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.giaoVienCN = giaoVienCN;
    }

    public LopHoc() {}

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getGiaoVienCN() {
        return giaoVienCN;
    }

    public void setGiaoVienCN(String giaoVienCN) {
        this.giaoVienCN = giaoVienCN;
    }
}
