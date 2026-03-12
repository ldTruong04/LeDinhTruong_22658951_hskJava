package Tuan4_bai1_Sach;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.ByteLookupTable;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;



public class frmSach extends JFrame implements ActionListener, MouseListener{
private JPanel mainPanel;
private JPanel nouthPanel;
private JLabel lblMaSach;
private JTextField txtMaSach;
private JLabel lblTuaSach;
private JTextField txtTuaSach;
private JLabel lblTacGia;
private JTextField txtTacGia;
private JLabel lblNamXuatBan;
private JTextField txtTNamXuatBan;
private JTextField txtNamXuatBan;
private JLabel lblNhaXuatBan;
private JTextField txtNhaXuatBan;
private JLabel lblDonGia;
private JTextField txtDonGia;
private JRadioButton radNu;
private JLabel lblPhai;
private JPanel centerPanel;
private JButton btnTem;
private JButton btnXoaR;
private JButton btnXoa;
private JButton btnSua;
private JButton btnLuu;
private JButton btnThem;
private JPanel southPanel;
private JPanel pTable;
private DefaultTableModel modelSach;
private JTable tableSach;
private DanhSachSach list;

public frmSach() {
	setSize(900,750);
	setTitle("Quan Ly Sach");
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(new BorderLayout());
	
	JLabel lblTitle = new JLabel("THÔNG TIN SÁCH", JLabel.CENTER);
    lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
    lblTitle.setForeground(Color.blue);
    mainPanel = new JPanel(new BorderLayout(10, 10));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Thêm tiêu đề vào phần trên của mainPanel
    mainPanel.add(lblTitle, BorderLayout.NORTH);
    
	nouthPanel = new JPanel(new GridLayout(0,4,10,10));
	lblMaSach= new JLabel("Ma Sach:");
	txtMaSach= new JTextField();
	nouthPanel.add(lblMaSach);
	nouthPanel.add(txtMaSach);
	
	lblTuaSach= new JLabel("Tua Sach:");
	txtTuaSach= new JTextField();
	nouthPanel.add(lblTuaSach);
	nouthPanel.add(txtTuaSach);

	lblTacGia= new JLabel("TacGia:");
	txtTacGia= new JTextField();
	nouthPanel.add(lblTacGia);
	nouthPanel.add(txtTacGia);
	
	lblNamXuatBan= new JLabel("Nam xb:");
	txtNamXuatBan= new JTextField();
	nouthPanel.add(lblNamXuatBan);
	nouthPanel.add(txtNamXuatBan);
	
	lblNhaXuatBan= new JLabel("nha xb:");
	txtNhaXuatBan= new JTextField();
	nouthPanel.add(lblNhaXuatBan);
	nouthPanel.add(txtNhaXuatBan);
	
	lblPhai= new JLabel("phai:");
	radNu= new JRadioButton();
	nouthPanel.add(lblPhai);
	nouthPanel.add(radNu);
	
	lblDonGia= new JLabel("don gia:");
	txtDonGia= new JTextField();
	nouthPanel.add(lblDonGia);
	nouthPanel.add(txtDonGia);
	
	nouthPanel.setBorder(BorderFactory.createTitledBorder("le dinh truong"));
    mainPanel.add(nouthPanel, BorderLayout.NORTH);
	
    centerPanel = new JPanel();
    btnThem= new JButton("Them");
    btnXoaR= new JButton("xoas TRANG");
    btnXoa = new JButton("Xoa");
    btnSua = new JButton("sua");
    btnLuu = new JButton("luu");
    
     centerPanel.add(btnThem);
     centerPanel.add(btnXoa);
     centerPanel.add(btnXoaR);
     centerPanel.add(btnSua);
     centerPanel.add(btnLuu);
     
     JComboBox<String> comboBox = new JComboBox<>(new String[] {"a","b","c"});
     comboBox.setEditable(true);
     centerPanel.add(new JLabel("tim theo ma sach"));
     centerPanel.add(comboBox);
     
     mainPanel.add(centerPanel, BorderLayout.CENTER);
     
     
     southPanel = new JPanel();
     pTable = new JPanel();
     pTable.setLayout(new BorderLayout());
     String[] cols= {"ma SACH","tUA SACH","TacGia","Nhan Xua Ban","NamXuatBAn","phai", "don gia"};
     modelSach = new DefaultTableModel(cols,0);
     tableSach = new JTable(modelSach);
     pTable.add(new JScrollPane(tableSach),BorderLayout.CENTER);
     southPanel.setBorder(BorderFactory.createTitledBorder("Danh SACH"));
     southPanel.add(pTable);
     mainPanel.add(southPanel, BorderLayout.SOUTH);
     
     list = new DanhSachSach();
     
     LuuTru l = new LuuTru();
     try {
		list = (DanhSachSach) l.readFile("DuLieuDemo");
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "k tim thay file");
	}
     
    readData();
     
     
     
    add(mainPanel);
    
    btnThem.addActionListener(this);
    btnSua.addActionListener(this);
    btnXoa.addActionListener(this);
    btnXoaR.addActionListener(this);
    btnLuu.addActionListener(this);
    
    saveToFile();
    
    comboBox.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String selectMaSachString = (String) comboBox.getSelectedItem();
			for(int i=0; i< tableSach.getRowCount(); i++) {
				if(modelSach.getValueAt(i, 0).equals(selectMaSachString)) {
					tableSach.setRowSelectionInterval(i, i);
					tableSach.scrollRectToVisible(new Rectangle(tableSach.getCellRect(i, 0, true)));
					break;
				}
			}
		}
	});
    

}
public static void main(String[] args) {
	new frmSach().setVisible(true);
}
public void readData() {
	for (int i=0; i< list.getSize(); i++) {
		Sach sach = list.getElement(i);
		modelSach.addRow(new Object[]{sach.getMaSach(),sach.getTuaSach(),sach.getTacGia(),sach.getNamXuatBan(),sach.getNhaXuatBan(),sach.isPhai(),sach.getDonGia()});
		
	}
}
public void saveToFile() {
	try {
		LuuTru l = new LuuTru();
		list = (DanhSachSach) l.readFile("DuLieuDemo.txt");
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "k tim thay file");
	}
}
@Override
public void mouseClicked(java.awt.event.MouseEvent e) {
	// TODO Auto-generated method stub
	int r = tableSach.getSelectedRow();
	txtMaSach.setText(modelSach.getValueAt(r, 0).toString());
	txtTacGia.setText(modelSach.getValueAt(r, 3).toString());
	txtNamXuatBan.setText(modelSach.getValueAt(r, 4).toString());
	txtNhaXuatBan.setText(modelSach.getValueAt(r, 5).toString());
	radNu.setSelected(modelSach.getValueAt(r,6)=="nam"? true:false);
	txtDonGia.setText(modelSach.getValueAt(r, 7).toString());
	txtTuaSach.setText(modelSach.getValueAt(r, 2).toString());
}
@Override
public void mousePressed(java.awt.event.MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(java.awt.event.MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseEntered(java.awt.event.MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(java.awt.event.MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	Object o= e.getSource();
	if(o.equals(btnXoaR)) {
		txtMaSach.setText("");
		txtTacGia.setText("");
		txtTuaSach.setText("");
		txtNamXuatBan.setText("");
		txtNhaXuatBan.setText("");
		txtDonGia.setText("");
		txtMaSach.requestFocus();
		
	}
	
	if (o.equals(btnThem)) {
	    String ma = txtMaSach.getText();
	    String tua = txtTuaSach.getText();
	    String tGia = txtTacGia.getText();
	    String namXB = txtNamXuatBan.getText();
	    String nhaXB = txtNhaXuatBan.getText();
	    
	    String dGia = txtDonGia.getText();
	   

	    if (ma.matches("")) {
	        JOptionPane.showMessageDialog(this, "Mã sách không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (tua.trim().isEmpty() ) {
	        JOptionPane.showMessageDialog(this, "Tựa sách không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (tGia.trim().isEmpty() ) {
	        JOptionPane.showMessageDialog(this, "Tác giả không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	   
	    int namXuatBan = Integer.parseInt(namXB);
	    if (namXuatBan < 1900 || namXuatBan > Calendar.getInstance().get(Calendar.YEAR)) {
	        JOptionPane.showMessageDialog(this, "Năm xuất bản không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    double donGia = dGia.trim().isEmpty() ? 0 : Double.parseDouble(dGia);
	    if (donGia < 0) {
	        JOptionPane.showMessageDialog(this, "Đơn giá không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    boolean rad = radNu.isSelected();

	    Sach s = new Sach(ma, tua, tGia, namXuatBan, nhaXB, rad,donGia);
	    if (!list.themSach(s)) {
	        JOptionPane.showMessageDialog(this, "Không thể thêm sách do trùng mã sách hoặc lỗi khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    } else {
	        modelSach.addRow(new Object[]{ma, tua, tGia, namXuatBan, nhaXB,rad, donGia});
	        JOptionPane.showMessageDialog(this, "Thêm sách thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	if (o.equals(btnXoa)) {
		int r = tableSach.getSelectedRow();
		if (r >= 0) {		    
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);		           
			if (confirm == JOptionPane.YES_OPTION) {
				modelSach.removeRow(r);
				Sach sach = list.getElement(r);
				list.xoaSach(list);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Chọn sách cần xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	} 

	if(o.equals(btnLuu)) {
		LuuTru l = new LuuTru();
		try {
			l.LuuFile(list,"DuLieu.txt");
			System.out.println("Luu thanh cong");
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}
}
