package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import dao.NhanVienDAO;

public class LoginForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private static LoginForm frmLogin = new LoginForm();
	JButton btnDangNhap = new JButton("\u0110\u0103ng Nh\u1EADp");
	
	@SuppressWarnings("deprecation")
	public static synchronized LoginForm getInstance(){
        try {
            if (frmLogin == null) {
            	frmLogin = (LoginForm) Class.forName("frmLogin").newInstance();
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println(e.toString());
        }
        return frmLogin;
    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LoginForm.getInstance().setVisible(true);
			}
		});
	}

	public LoginForm() {
		initialize();
	}

	public boolean KiemTraThongTinDangNhap(String taiKhoan,String matKhau)
	{
		return NhanVienDAO.kiemTraThongTinDangNhap(taiKhoan, matKhau);
	}
	
	public void btnDangNhapActionPerformed(ActionEvent e)
	{
		String passText = new String(txtPassword.getPassword());
		
		if(txtUser.getText().trim().isEmpty() || passText.isEmpty()) {
			JOptionPane.showMessageDialog(btnDangNhap, "Vui lòng nhập đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(KiemTraThongTinDangNhap(txtUser.getText(), passText)){
			MainForm.getInstance(txtUser.getText()).setVisible(true);
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(btnDangNhap, "Sai thông tin đăng nhập!"+"\n"
					+ "Xin vui lòng nhập lại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void initialize() {
		setType(Type.UTILITY);
		setBounds(100, 100, 608, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(211, 10, 374, 299);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtUser.setBounds(20, 50, 341, 30);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtPassword.setBounds(20, 130, 341, 30);
		
		panel.add(txtPassword);
		
		JLabel lblNewLabel = new JLabel("T\u00EAn t\u00E0i kho\u1EA3n");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(20, 10, 144, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("M\u1EADt kh\u1EA9u");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(20, 90, 144, 30);
		panel.add(lblNewLabel_1);
		
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDangNhapActionPerformed(e);
				txtUser.setText("");
				txtPassword.setText("");
			}
		});
		btnDangNhap.setIcon(new ImageIcon(getClass().getResource("/images/icons8_login_32px.png")));
		btnDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDangNhap.setBounds(32, 197, 145, 60);
		panel.add(btnDangNhap);
		
		JButton btnThoat = new JButton("Tho\u00E1t");
		btnThoat.setIcon(new ImageIcon(getClass().getResource("/images/icons8_Logout_32px.png")));
		btnThoat.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(btnThoat, "Thoát khỏi chương trình?", "Thông báo", 
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
						System.exit(0);
					}
			}
		});
		btnThoat.setBounds(202, 197, 145, 60);
		panel.add(btnThoat);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(getClass().getResource("/images/icons8_staff_96px.png")));
		lblIcon.setBounds(10, 10, 191, 271);
		getContentPane().add(lblIcon);
	}
}
