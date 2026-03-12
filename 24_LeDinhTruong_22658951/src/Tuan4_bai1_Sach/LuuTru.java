package Tuan4_bai1_Sach;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class LuuTru {
public boolean LuuFile(Object obj, String filePath ) throws Exception{
	ObjectOutputStream oos = null;
	
	oos= new ObjectOutputStream(new FileOutputStream(filePath));
	oos.writeObject(obj);
	oos.flush();
	oos.close();
	return true;
	
}
public Object readFile(String filePath) throws Exception{
	ObjectInputStream objectInputStream= new ObjectInputStream(new FileInputStream(filePath));
	Object object = objectInputStream.readObject();
	objectInputStream.close();
	return 0;
}
}

