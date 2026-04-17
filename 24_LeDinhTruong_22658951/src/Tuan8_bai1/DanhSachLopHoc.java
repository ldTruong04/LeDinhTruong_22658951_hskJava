package Tuan8_bai1;

import java.sql.*;
import java.util.*;

public class DanhSachLopHoc {

    public ArrayList<LopHoc> docTuBang() {
        ArrayList<LopHoc> dslop = new ArrayList<>();
        try {
            Connection con = Database.getInstance().getConnection();
            String sql = "select * from lophoc";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String gvcn = rs.getString(3);
                dslop.add(new LopHoc(ma, ten, gvcn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dslop;
    }

    public boolean create(LopHoc p) {
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("insert into LopHoc values(?, ?, ?)");
            stmt.setString(1, p.getMaLop());
            stmt.setString(2, p.getTenLop());
            stmt.setString(3, p.getGiaoVienCN());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean update(LopHoc p) {
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement(
                "update LopHoc set tenLop=?, giaoVienCN=? where maLop=?"
            );
            stmt.setString(1, p.getTenLop());
            stmt.setString(2, p.getGiaoVienCN());
            stmt.setString(3, p.getMaLop());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean delete(String ma) {
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("delete from LopHoc where maLop=?");
            stmt.setString(1, ma);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
