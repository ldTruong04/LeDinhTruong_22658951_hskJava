package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVien_Dao {
    ArrayList<NhanVien> dsnv;

    public NhanVien_Dao() {
        dsnv = new ArrayList<NhanVien>();
    }

    public ArrayList<NhanVien> getalltbNhanVien() {
        ArrayList<NhanVien> dsnv = new ArrayList<>(); // tạo mới mỗi lần

        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM NhanVien";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dsnv.add(new NhanVien(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3),
                    new PhongBan(rs.getString(4)),
                    rs.getDouble(5)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsnv;
    }

    public boolean create(NhanVien nv) {
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;

        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO NhanVien VALUES(?, ?, ?, ?, ?)");
            stmt.setString(1, nv.getMaNV());
            stmt.setString(2, nv.getHoten());
            stmt.setInt(3, nv.getTuoi());
            stmt.setString(4, nv.getPhong().getMaPhong());
            stmt.setDouble(5, nv.getTienluong());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean update(NhanVien nv) {
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;

        int n = 0;
        try {
            stmt = con.prepareStatement(
                "UPDATE NhanVien SET hoTen=?, tuoi=?, maPhong=?, tienLuong=? WHERE maNV=?"
            );

            stmt.setString(1, nv.getHoten());
            stmt.setInt(2, nv.getTuoi());
            stmt.setString(3, nv.getPhong().getMaPhong());
            stmt.setDouble(4, nv.getTienluong());
            stmt.setString(5, nv.getMaNV());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean delete(String manv) {
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;

        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM nhanvien WHERE maNV = ?");
            stmt.setString(1, manv);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<NhanVien> getNhanVienTheoPhongBan(String map) {
        ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();

        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();

        try {
            String sql = "SELECT * FROM nhanvien WHERE maPhong = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, map);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String maNV = rs.getString(1);
                String hoten = rs.getString(2);
                int tuoi = rs.getInt(3);
                PhongBan pBan = new PhongBan(rs.getString(4));
                double luong = rs.getDouble(5);

                NhanVien nv = new NhanVien(maNV, hoten, tuoi, pBan, luong);
                dsnv.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsnv;
    }
}