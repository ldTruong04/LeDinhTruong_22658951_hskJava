package Tuan4_bai1_Sach;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class DanhSachSach implements Serializable{
 private  ArrayList<Sach>list;
 public ArrayList<Sach> getlistSach(){
	 return list;
 }
 public DanhSachSach(){
	 list= new ArrayList<Sach>();
 }
 public boolean themSach(Sach sach) {
	 if(list.contains(sach)) {
		 return false;
	 }else {
		 list.add(sach);
		 return true;
	 }
 }
	public boolean xoaSach(DanhSachSach sach) {
		if(list.contains(sach)) {
			list.remove(sach);
			return true;
		}
		return false;
	}
public Sach timKiem(String maSach) {
	 for(Sach sach: list) {
		 if(sach.getMaSach().trim().equalsIgnoreCase(maSach))
			 return list.get(list.indexOf(sach));
		 
	 }return null;
}
public int getSize() {
	return list.size();
	
}
public Sach getElement(int index) {
	if(index<0|| index>list.size())
		return null;
	return list.get(index);
	}
}

