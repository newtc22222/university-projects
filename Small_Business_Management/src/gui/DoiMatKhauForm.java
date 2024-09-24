package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import dao.NhanVienDAO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DoiMatKhauForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtTaiKhoan;
	private JTextField txtMatKhauCu;
	private JTextField txtMatKhauMoi;
	private JTextField txtXacNhan;
	private String tenTaiKhoan;

	/**
	 * Create the application.
	 */
	public DoiMatKhauForm(String taiKhoan) {
		setType(Type.UTILITY);
		this.tenTaiKhoan = taiKhoan;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 383, 341);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblThay = new JLabel("Thay \u0110\u1ED5i Th\u00F4ng Tin \u0110\u0103ng Nh\u1EADp");
		lblThay.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblThay.setBounds(10, 10, 305, 30);
		getContentPane().add(lblThay);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 365, 13);
		getContentPane().add(separator);

		JLabel lblMDn = new JLabel("T\u00EAn t\u00E0i kho\u1EA3n");
		lblMDn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMDn.setBounds(10, 50, 113, 30);
		getContentPane().add(lblMDn);

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setBounds(133, 50, 223, 30);
		txtTaiKhoan.setEnabled(false);
		txtTaiKhoan.setText(tenTaiKhoan);
		getContentPane().add(txtTaiKhoan);

		txtMatKhauCu = new JTextField();
		txtMatKhauCu.setColumns(10);
		txtMatKhauCu.setBounds(133, 90, 223, 30);
		getContentPane().add(txtMatKhauCu);

		JLabel lblMDn_1 = new JLabel("M\u1EADt kh\u1EA9u c\u0169");
		lblMDn_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMDn_1.setBounds(10, 90, 113, 30);
		getContentPane().add(lblMDn_1);

		txtMatKhauMoi = new JTextField();
		txtMatKhauMoi.setColumns(10);
		txtMatKhauMoi.setBounds(133, 130, 223, 30);
		getContentPane().add(txtMatKhauMoi);

		JLabel lblMDn_2 = new JLabel("M\u1EADt kh\u1EA9u m\u1EDBi");
		lblMDn_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMDn_2.setBounds(10, 130, 113, 30);
		getContentPane().add(lblMDn_2);

		txtXacNhan = new JTextField();
		txtXacNhan.setColumns(10);
		txtXacNhan.setBounds(133, 170, 223, 30);
		getContentPane().add(txtXacNhan);

		JLabel lblMDn_3 = new JLabel("X\u00E1c nh\u1EADn");
		lblMDn_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMDn_3.setBounds(10, 170, 113, 30);
		getContentPane().add(lblMDn_3);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (NhanVienDAO.kiemTraThongTinDangNhap(txtTaiKhoan.getText(), txtMatKhauCu.getText())) {
					if (txtMatKhauMoi.getText().equals(txtXacNhan.getText())) {
						if (NhanVienDAO.updateMatKhau(txtTaiKhoan.getText(), txtMatKhauMoi.getText())) {
							JOptionPane.showMessageDialog(btnLuu, "Đổi mật khẩu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(btnLuu, "Mật khẩu xác nhận không trùng khớp!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					}
				} 
				else {
					JOptionPane.showMessageDialog(btnLuu, "Mật khẩu cũ không đúng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnLuu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLuu.setBounds(39, 230, 115, 50);
		btnLuu.setIcon(new ImageIcon(getClass().getResource("/images/icons8_save_32px.png")));
		getContentPane().add(btnLuu);

		JButton btnHuy = new JButton("Dọn dẹp");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMatKhauCu.setText("");
				txtMatKhauMoi.setText("");
				txtXacNhan.setText("");
			}
		});
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnHuy.setBounds(193, 230, 134, 50);
		btnHuy.setIcon(new ImageIcon(getClass().getResource("/images/icons8_clear_symbol_32px_1.png")));
		getContentPane().add(btnHuy);
	}
}
