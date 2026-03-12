package Tuan4_bai1_Sach;

import java.io.Serializable;
import java.util.Objects;

public class Sach implements Serializable{
 private String maSach;
 private String  tuaSach;
 private String tacGia;
 private int namXuatBan;
 private String nhaXuatBan;
 private boolean phai;
 private double donGia;
public String getMaSach() {
	return maSach;
}
public void setMaSach(String maSach) {
	this.maSach = maSach;
}
public String getTuaSach() {
	return tuaSach;
}
public void setTuaSach(String tuaSach) {
	this.tuaSach = tuaSach;
}
public String getTacGia() {
	return tacGia;
}
public void setTacGia(String tacGia) {
	this.tacGia = tacGia;
}
public int getNamXuatBan() {
	return namXuatBan;
}
public void setNamXuatBan(int namXuatBan) {
	this.namXuatBan = namXuatBan;
}
public String getNhaXuatBan() {
	return nhaXuatBan;
}
public void setNhaXuatBan(String nhaXuatBan) {
	this.nhaXuatBan = nhaXuatBan;
}
public boolean isPhai() {
	return phai;
}
public void setPhai(boolean phai) {
	this.phai = phai;
}
public double getDonGia() {
	return donGia;
}
public void setDonGia(double donGia) {
	this.donGia = donGia;
}
@Override
public int hashCode() {
	return Objects.hash(maSach);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Sach other = (Sach) obj;
	return Objects.equals(maSach, other.maSach);
}

public Sach() {
	super();
	// TODO Auto-generated constructor stub
}
public Sach(String maSach, String tuaSach, String tacGia, int namXuatBan, String nhaXuatBan, boolean phai,
		double donGia) {
	super();
	this.maSach = maSach;
	this.tuaSach = tuaSach;
	this.tacGia = tacGia;
	this.namXuatBan = namXuatBan;
	this.nhaXuatBan = nhaXuatBan;
	this.phai = phai;
	this.donGia = donGia;
}
@Override
public String toString() {
	return maSach +";"+ tuaSach+";"+ tacGia+";"+ namXuatBan +";"+ nhaXuatBan+";"+ phai+";"+ donGia ;
}
 
 
}

