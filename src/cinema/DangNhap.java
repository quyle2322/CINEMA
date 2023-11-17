/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import entity.KhachHang;
import entity.NhanVien;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import utils.Auth;
import utils.MsgBox;

/**
 *
 * @author ngoth
 */
public class DangNhap {
    NhanVienDAO dao = new NhanVienDAO();
    KhachHangDAO khdao = new KhachHangDAO();
   int mode = 0;
    int h = 550, w = 1000;
    // ch: component high
    int ch = h / 10;
    int cw = w / 5;
    int x = 10, y = 10;

    JFrame mainFrame;
    JPanel mainPanel, btnPanel;
    JLabel lblTitle, lblEmail, lblPassword, lblClose, lblForget, lblright, lblleft;
    JPasswordField txtPassword;
    JTextField txtEmail;
    JButton btnSignup, btnLogin;

    public DangNhap() {
        myGui();
        btnLogin.requestFocus();
    }

    private void myGui() {
        mainFrame = new JFrame();
        mainFrame.setSize(w, h - 50);
        mainFrame.setLayout(null);
        mainFrame.setUndecorated(true);
        mainFrame.setLocationRelativeTo(null);

        lblright = new JLabel();
        lblright.setBounds(0, 0, w / 2-30 + 120, h);
        lblright.setOpaque(true);
        lblright.setBackground(new Color(255,240,235));
        mainFrame.add(lblright);

        ImageIcon image2 = new ImageIcon("D:\\CINEMA\\src\\img\\h.png");
        lblleft = new JLabel(image2);
        lblleft.setBounds(580, 0, w / 2+80 - 120, h);
        lblleft.setOpaque(true);
        lblleft.setBackground(Color.white);
        mainFrame.add(lblleft);

        lblClose = new JLabel("x");
        lblClose.setFont(new Font("Arial", 1, 30));
        lblClose.setBounds(cw + 190, 0, 50, 50);
        lblClose.setCursor(new Cursor(12));

        lblClose.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.exit(0);
            }

            public void mouseEntered(MouseEvent me) {
                lblClose.setForeground(Color.red);
            }

            public void mouseExited(MouseEvent me) {
                lblClose.setForeground(Color.black);
            }
        });
        lblleft.add(lblClose);

        lblTitle = new JLabel("ĐĂNG NHẬP", JLabel.CENTER);
        lblTitle.setBounds(0, 30, w / 2 + 140, ch);
        lblTitle.setFont(new Font("Arial", 1, 40));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(255,240,235));
        lblTitle.setForeground(Color.black);
        lblright.add(lblTitle);

        y += 100;
        mainPanel = new JPanel(new FlowLayout(0, 15, 25));
        mainPanel.setBounds(50, y, w / 2 , h / 2 - 40);
        mainPanel.setBackground(new Color(255,240,235));
        lblright.add(mainPanel);

        ImageIcon image = new ImageIcon("D:\\CINEMA\\src\\img\\email.png");
        lblEmail = new JLabel(image, JLabel.CENTER);
        lblEmail.setPreferredSize(new Dimension(cw - 155, ch + 12));
        lblEmail.setFont(new Font("Arial", 1, 20));
        lblEmail.setOpaque(true);
        lblEmail.setBackground(new Color(255,240,235));
        mainPanel.add(lblEmail);

        txtEmail = new JTextField("Email");
        txtEmail.setBackground(Color.WHITE);
        txtEmail.setBorder(new LineBorder(Color.black, 1, true));

        txtEmail.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fe) {
                txtEmail.setText("");
            }

            public void focusLost(FocusEvent fe) {
                if (txtEmail.getText().equals("")) {
                    txtEmail.setText("Email");
                }
            }
        });
        txtEmail.setPreferredSize(new Dimension(cw * 2 - 10, ch));
        mainPanel.add(txtEmail);

        ImageIcon image1 = new ImageIcon("D:\\CINEMA\\src\\img\\lock.png");
        lblPassword = new JLabel(image1, JLabel.RIGHT);
        lblPassword.setPreferredSize(new Dimension(cw - 155, ch + 12));
//       lblPassword.setOpaque(true);
//       lblPassword.setBackground(Color.yellow);
        mainPanel.add(lblPassword);

        txtPassword = new JPasswordField("*****");
        txtPassword.setBorder(new LineBorder(Color.black, 1, true));
        txtPassword.setBackground(Color.WHITE);
        txtPassword.setPreferredSize(new Dimension(cw * 2 - 10, ch));
        txtPassword.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fe) {
                txtPassword.setText("");
            }

            public void focusLost(FocusEvent fe) {
                if (txtPassword.getText().equals("")) {
                    txtPassword.setText("Mật khẩu");
                }
            }
        });
        mainPanel.add(txtPassword);

        y += ch * 3 + 70;
        btnPanel = new JPanel();
        btnPanel.setBounds(20, y, w / 2 + 100, h / 3);
        btnPanel.setBackground(new Color(255,240,235));
        lblright.add(btnPanel);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setPreferredSize(new Dimension(cw-30, ch));
        btnLogin.setFont(new Font("Arial", 1, 18));
        btnLogin.setBackground(Color.pink);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {    
                Login();               
            }
        });
        btnPanel.add(btnLogin);

        btnSignup = new JButton("Đăng ký");
        btnSignup.setPreferredSize(new Dimension(cw-30, ch));
        btnSignup.setFont(new Font("Arial", 1, 18));
        btnSignup.setBackground(Color.pink);
        //btnSignup.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        btnPanel.add(btnSignup);

        lblForget = new JLabel("Quên mật khẩu?", JLabel.CENTER);
        lblForget.setPreferredSize(new Dimension(35 * x, ch));
        lblForget.setFont(new Font("Arial", 2, 16));
        lblForget.setCursor(new Cursor(12));

        lblForget.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                //lblForget.setFont(new Font("Arial", 3, 17));
                lblForget.setForeground(Color.pink);
            }

            public void mouseExited(MouseEvent me) {
                lblForget.setFont(new Font("Arial", 2, 16));
                lblForget.setForeground(Color.black);
            }

            public void mouseClicked(MouseEvent me) {

            }
        });
        btnPanel.add(lblForget);

        mainFrame.setVisible(true);
    }

    private void Login() {
        Validate();
        String strEmailNV = txtEmail.getText();
        String strPasswordNV = new String(txtPassword.getPassword());
        NhanVien nv = dao.SelectById(strEmailNV);
        
        String strEmailKH = txtEmail.getText();
        String strPasswordKH = new String(txtPassword.getPassword());      
        KhachHang kh = khdao.SelectById(strEmailKH);
        
//        if (nv == null  || kh == null) {
//            MsgBox.alert(null, "Sai Email!");
//        }else{
//            if(!nv.getMatKhau().equals(strPassword) || !kh.getMatKhau().equals(strPassword1)){
//                MsgBox.alert(null, "Sai mật khẩu!");
//            }else{
//                Auth.user = nv;               
//                MsgBox.alert(null, "thanh cong");
//                mainFrame.dispose();
//            }
//        }

    if(nv.getEmailNV().equals(strEmailNV) && !kh.getEmailKH().equals(strEmailKH)) mode=1;
            
    if(!nv.getEmailNV().equals(strEmailNV) && kh.getEmailKH().equals(strEmailKH)) mode = 2;
    
    if(!nv.getEmailNV().equals(strEmailNV) && !kh.getEmailKH().equals(strEmailKH)) mode = 3;
    
    if(nv.getEmailNV().equals(strEmailNV) && kh.getEmailKH().equals(strEmailKH)) mode = 4;
    
    if(mode == 1 && nv.getMatKhau().equals(strPasswordNV)){
        Auth.user = nv;
        MsgBox.alert(null, "thanh cong");
    }else{
        MsgBox.alert(null, "Sai mat khau!!");
    }
    
    if(mode == 2 && kh.getMatKhau().equals(strPasswordKH)){
        Auth.KH = kh;
    }
    
    if(mode==3) MsgBox.alert(null, "Email khong ton tai!!");
    
    }
    
    private boolean Validate(){
        if(txtEmail.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã!");
            txtEmail.requestFocus();
            return false;
        } 
        
//        String email="\\w+@\\w+(\\.\\w+){1,2}";
//        Pattern pattern = Pattern.compile(email);
//        Matcher matcher = pattern.matcher(txtEmail.getText());
//        try{
//            if(matcher.matches()==false)
//                throw new Exception();
//        }catch (Exception e){
//             JOptionPane.showMessageDialog(null, "Email không đúng định dạng");
//             txtEmail.requestFocus();
//             return false;
//           }
        
        if(txtPassword.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã!");
            txtPassword.requestFocus();
            return false;
        } 
        return true;
    }

    public static void main(String[] args) {
        new DangNhap();
    }
}
