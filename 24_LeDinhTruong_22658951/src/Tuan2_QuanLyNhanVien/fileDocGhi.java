package Tuan2_QuanLyNhanVien;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class fileDocGhi {

    public static void writeFile(DanhSachNhanVien ds, String file) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(ds);
        out.close();
    }

    public Object readFile(String file) throws Exception {
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(file));
        Object list = oi.readObject();
        oi.close();
        return list;
    }
}
