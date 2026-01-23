package Tuan1;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;




public class GiaPTB2 extends JFrame implements ActionListener{
	private JTextField txtA, txtB, txtC, txtKetQua;
    private JButton btnGiai, btnXoa, btnThoat;
	


	public GiaPTB2() {
		setTitle("^-^");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
		
		
		//North
		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(Color.CYAN);
		JLabel lblTitle = new JLabel("GIẢI PHƯƠNG TRÌNH BẬC HAI");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		pnNorth.add(lblTitle);
		add(pnNorth, BorderLayout.NORTH);
		
		//Center
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(null);
		     //Nhập a
		JLabel lblA = new JLabel("Nhập a:");
		lblA.setBounds(50,30,80,25);
		txtA= new JTextField();
		txtA.setBounds(150,30,280,25);
		      //Nhập b
		JLabel lblB = new JLabel("Nhập b:");
		lblB.setBounds(50,60,80,25);
		txtB = new JTextField();
		txtB.setBounds(150,60,280,25);
		      //Nhập c
		JLabel lblC = new JLabel("Nhập c:");
		lblC.setBounds(50,90,80,25);
		txtC = new JTextField();
		txtC.setBounds(150,90,280,25);
		     //Kết quả 
		
		JLabel lblKQ = new JLabel("Kết Qủa:");
		lblKQ.setBounds(50,120,80,25);
		txtKetQua = new JTextField();
		txtKetQua.setBounds(150,120,280,25);
		txtKetQua.setEditable(false); //Không cho phép nhập
		
		
		
		pnCenter.add(lblA); pnCenter.add(txtA);
		pnCenter.add(lblB); pnCenter.add(txtB);
		pnCenter.add(lblC); pnCenter.add(txtC);
		pnCenter.add(lblKQ); pnCenter.add(txtKetQua);
		add(pnCenter,BorderLayout.CENTER);
		
		//South
		
		JPanel pnSouth = new JPanel();
		pnSouth.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),"Chọn tac vụ",TitledBorder.LEFT,TitledBorder.TOP));
		
		btnGiai = new JButton("Giải");
		btnThoat = new JButton("Thoat");
		btnXoa = new JButton("Xóa");
		
		btnGiai.addActionListener(this);
        btnXoa.addActionListener(this);
        btnThoat.addActionListener(this);
        
		pnSouth.add(btnGiai);
		pnSouth.add(btnThoat);
		pnSouth.add(btnXoa);
		
		add(pnSouth,BorderLayout.SOUTH);
		
		// Xử lý
        btnThoat.addActionListener(e -> System.exit(0));

        btnXoa.addActionListener(e -> {
            txtA.setText("");
            txtB.setText("");
            txtC.setText("");
            txtKetQua.setText("");
            txtA.requestFocus();
        });

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GiaiPhuongTrinhBacHai().setVisible(true));
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
        Object o = e.getSource();
        
        if (o.equals(btnGiai)) {
            xuLyGiai();
        } else if (o.equals(btnXoa)) {
            xuLyXoa();
        } else if (o.equals(btnThoat)) {
            xuLyThoat();
        }
    }

    //

    private void xuLyGiai() {
        
            double a = Double.parseDouble(txtA.getText());
            double b = Double.parseDouble(txtB.getText());
            double c = Double.parseDouble(txtC.getText());
            txtKetQua.setText(giaiToanPT(a, b, c));
        
    }

    private void xuLyXoa() {
        txtA.setText("");
        txtB.setText("");
        txtC.setText("");
        txtKetQua.setText("");
        txtA.requestFocus(); // Đưa con trỏ lên đầu
    }

    private void xuLyThoat() {
        
       System.exit(0);
        
    }

    
    private String giaiToanPT(double a, double b, double c) {
        if (a == 0) {
            if (b == 0) return (c == 0) ? "Vô số nghiệm" : "Vô nghiệm";
            return "x = " + (-c / b);
        }
        double delta = b * b - 4 * a * c;
        if (delta < 0) return "Phương trình vô nghiệm";
        if (delta == 0) return "Nghiệm kép x = " + (-b / (2 * a));
        
        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
        return "x1 = " + x1 + " ; x2 = " + x2;
    }
}
