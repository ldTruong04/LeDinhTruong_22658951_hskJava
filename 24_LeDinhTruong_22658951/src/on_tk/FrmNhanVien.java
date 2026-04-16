package on_tk;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



public class FrmNhanVien extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtDiaChi;
	private JTextField txtTuoi;
	private JTextField txtEmail;
	
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLuu;
	
	private JTable table;
	private JTextField txtMess;
	private JButton btnXoaRong;

	private DefaultTableModel tableModel;

	private JButton btnLoc;

	public FrmNhanVien() {
		setTitle("Quản lý sách");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildUI();
	}

	private void buildUI() {

		// Phần North
		JPanel pnlNorth;
		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		pnlNorth.setPreferredSize(new Dimension(0, 180));
		pnlNorth.setBorder(BorderFactory.createTitledBorder("Records Editor"));
		pnlNorth.setLayout(null); // Absolute layout

		JLabel lblMaSach, lblTuaSach, lblTacGia, lblNamXB, lblNhaXB, lblSoTrang, lblDonGia, lblISBN;
		pnlNorth.add(lblMaSach = new JLabel("Mã Nhân Viên: "));
		pnlNorth.add(lblTuaSach = new JLabel("Họ Tên: "));
		pnlNorth.add(lblTacGia = new JLabel("địa chỉ: "));
		pnlNorth.add(lblNamXB = new JLabel("Tuổi: "));
		pnlNorth.add(lblNhaXB = new JLabel("website: "));
		
		pnlNorth.add(txtMa = new JTextField());
		pnlNorth.add(txtTen = new JTextField());
		pnlNorth.add(txtDiaChi = new JTextField());
		pnlNorth.add(txtTuoi = new JTextField());
		pnlNorth.add(txtEmail = new JTextField());
		
		pnlNorth.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		int w1 = 100, w2 = 300, h = 20;
		lblMaSach.setBounds(20, 20, w1, h);
		txtMa.setBounds(120, 20, 200, h);

		lblTuaSach.setBounds(20, 45, w1, h);
		txtTen.setBounds(120, 45, w2, h);
		lblTacGia.setBounds(450, 45, w1, h);
		txtDiaChi.setBounds(570, 45, w2, h);

		lblNamXB.setBounds(20, 70, w1, h);
		txtTuoi.setBounds(120, 70, w2, h);
		lblNhaXB.setBounds(450, 70, w1, h);
		txtEmail.setBounds(570, 70, w2, h);

	
		txtMess.setBounds(20, 145, 550, 20);

		// Phần Center
		JPanel pnlCenter;
		add(pnlCenter = new JPanel(), BorderLayout.CENTER);
		pnlCenter.add(btnThem = new JButton("Thêm - kiểm tra dữ liệu ..."));
		pnlCenter.add(btnXoaRong = new JButton("Xóa rỗng"));

		// Phần South
		JScrollPane scroll;
		String[] headers = "MaNhanVien;HoTenNV;Tuoi;DiaChi; Email".split(";");

		tableModel = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách"));
		table.setRowHeight(20);
		scroll.setPreferredSize(new Dimension(0, 350));

		// Xử lý

		
		
		btnThem.addActionListener(this);		
		btnXoaRong.addActionListener(this);
		
	}

	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			String maNV = txtMa.getText();
			String tuoi = txtTuoi.getText();
			String ten = txtTen.getText();
			String diachi = txtDiaChi.getText();
			String web = txtEmail.getText();
			if (validData()) {
				tableModel.addRow(new Object[]{maNV, ten, tuoi, diachi, web});
			    JOptionPane.showMessageDialog(this, "them thanh cong.","thanh cong", JOptionPane.INFORMATION_MESSAGE);
			    
			} 
			}
	}

	

	private boolean validData() {
		String maNV = txtMa.getText().trim();
		String tuoi = txtTuoi.getText().trim();
		String ten = txtTen.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		String web = txtEmail.getText().trim();
		
		
		if(!(maNV.length() > 0 && maNV.matches("[A-Z]\\d{3}"))){
			
			JOptionPane.showMessageDialog(this,"Error: Mã sách theo mẫu: ký tự đầu là hoa theo sau là 3 số");
			txtMa.requestFocus();
			return false;
		}
if(!(tuoi.length() > 0 && tuoi.matches("(1[8-9]|[2-5][0-9]|6[0-5])"))){
			
			JOptionPane.showMessageDialog(this,"Error: tuoi phai tu 18 den 65");
			txtTuoi.requestFocus();
			return false;
		}
if(!(ten.length() > 0 && ten.matches("[a-zA-Z\s]+"))){
	
	JOptionPane.showMessageDialog(this,"Error: ten phai gom ky tu va khoan trang");
	txtTen.requestFocus();
	return false;
}
if(!(web.length() > 0 && web.matches("^www+[a-zA-Z0-9._%+-]+@gmail.com$"))){
	
	JOptionPane.showMessageDialog(this,"Error: email www.xxxxxx@gmail.com");
	txtEmail.requestFocus();
	return false;
}
if(!(diachi.length() > 0 && diachi.matches("[a-zA-Z0-9\s]+"))){
	
	JOptionPane.showMessageDialog(this,"Error: dia chi phai gom ky tu, so va khoan trang");
	txtTen.requestFocus();
	return false;
}
		
		return true;
		
	}

	

	private void clearTextfields() {
		txtMa.setText("");
		txtTen.setText("");
		txtDiaChi.setText("");
		txtTuoi.setText("");
		txtEmail.setText("");
	
		txtMa.setEditable(true);
		txtMa.requestFocus();
	}
}
