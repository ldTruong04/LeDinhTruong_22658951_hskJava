package Tuan1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SoNguyenTo extends JFrame implements ActionListener {

    private JTextField txtN;
    private JTextArea areaResult;
    private JButton btnGenerate;

    public SoNguyenTo() {
        
        setTitle("Primes");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // nut nhập liệu và nút bấm
        JPanel pnlTop = new JPanel(new FlowLayout());
        txtN = new JTextField(15);
        btnGenerate = new JButton("Generate");
        
        // Đăng ký sự kiện
        btnGenerate.addActionListener(this);

        pnlTop.add(txtN);
        pnlTop.add(btnGenerate);
        add(pnlTop, BorderLayout.NORTH);

        //  hiện kết quả
        areaResult = new JTextArea();
        areaResult.setEditable(false);
        areaResult.setBorder(BorderFactory.createLoweredBevelBorder());
        
        // TextArea vào ScrollPane để cuộn ds dài
        JScrollPane scrollPane = new JScrollPane(areaResult);
        add(scrollPane, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        if (o.equals(btnGenerate)) {
            xuLyGenerate();
        }
    }

    private void xuLyGenerate() {
        try {
            int n = Integer.parseInt(txtN.getText());
            if (n <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên dương > 0");
                return;
            }

            String result = timNSoNguyenTo(n);
            areaResult.setText(result);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: Giá trị nhập vào phải là một số nguyên!");
            txtN.requestFocus();
            txtN.selectAll();
        }
    }


    private String timNSoNguyenTo(int n) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int number = 2; 

        while (count < n) {
            if (isPrime(number)) {
                sb.append(number).append("\n");
                count++;
            }
            number++;
        }
        return sb.toString();
    }


    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SoNguyenTo().setVisible(true);
        });
    }
}