package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.PhongBan;

public class PhongBan_DAO {

    public ArrayList<PhongBan> getalltbPhongBan() {
        ArrayList<PhongBan> dsphong = new ArrayList<PhongBan>();

        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();

            String sql = "SELECT * FROM PhongBan";
            Statement statement = con.createStatement();

            // Thực thi câu lệnh SQL
            ResultSet rs = statement.executeQuery(sql);

            // Duyệt kết quả
            while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);

                PhongBan p = new PhongBan(maPhong, tenPhong);
                dsphong.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsphong;
    }
}