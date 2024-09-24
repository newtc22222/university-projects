package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import bean.NhanVien;
import dao.NhanVienDAO;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NhanVienForm extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNhanVien;
	private JTextField txtPhong;
	private JTextField txtHoTen;
	private JTextField txtNgaySinh;
	private JTextField txtMaNQL;
	private JTextField txtDiaChi;
	private JTextField txtLuong;
	private JLabel lblAvatar;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnHuy;
	private JButton btnXoa;
	private boolean flagThem = false;

	/**
	 * Create the panel.
	 */
	public NhanVienForm() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản Lý Nhân Viên");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 305, 30);
		add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 51, 936, 13);
		add(separator);
		
		JLabel lblMNhnVin = new JLabel("M\u00E3 Nh\u00E2n Vi\u00EAn");
		lblMNhnVin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMNhnVin.setBounds(10, 73, 113, 30);
		add(lblMNhnVin);
		
		JLabel lblHVTn = new JLabel("H\u1ECD V\u00E0 T\u00EAn");
		lblHVTn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblHVTn.setBounds(10, 113, 113, 30);
		add(lblHVTn);
		
		JLabel lblNgySinh = new JLabel("Ng\u00E0y Sinh");
		lblNgySinh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNgySinh.setBounds(10, 153, 113, 30);
		add(lblNgySinh);
		
		JLabel lblaCh = new JLabel("\u0110\u1ECBa Ch\u1EC9");
		lblaCh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblaCh.setBounds(10, 193, 113, 30);
		add(lblaCh);
		
		JLabel lblGiiTnh = new JLabel("Gi\u1EDBi T\u00EDnh");
		lblGiiTnh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblGiiTnh.setBounds(10, 233, 113, 30);
		add(lblGiiTnh);
		
		JLabel lblLng = new JLabel("L\u01B0\u01A1ng");
		lblLng.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLng.setBounds(10, 273, 113, 30);
		add(lblLng);
		
		JLabel lblPhng = new JLabel("Ph\u00F2ng");
		lblPhng.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPhng.setBounds(357, 73, 64, 30);
		add(lblPhng);
		
		JLabel lblMNgiQun = new JLabel("M\u00E3 Ng\u01B0\u1EDDi Qu\u1EA3n L\u00FD");
		lblMNgiQun.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMNgiQun.setBounds(357, 153, 146, 30);
		add(lblMNgiQun);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaNhanVien.setBounds(166, 75, 181, 30);
		add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(166, 113, 500, 30);
		add(txtHoTen);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(166, 153, 181, 30);
		add(txtNgaySinh);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(166, 193, 500, 30);
		add(txtDiaChi);
		
		txtLuong = new JTextField();
		txtLuong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtLuong.setColumns(10);
		txtLuong.setBounds(166, 273, 500, 30);
		add(txtLuong);
		
		txtPhong = new JTextField();
		txtPhong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtPhong.setColumns(10);
		txtPhong.setBounds(495, 75, 171, 30);
		add(txtPhong);
		
		txtMaNQL = new JTextField();
		txtMaNQL.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaNQL.setColumns(10);
		txtMaNQL.setBounds(495, 153, 171, 30);
		add(txtMaNQL);
		
		JPanel panel = new JPanel();
		panel.setBounds(706, 61, 240, 323);
		add(panel);
		panel.setLayout(null);
		
		JButton btnChonAnh = new JButton("Ch\u1ECDn \u1EA2nh");
		btnChonAnh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnChonAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f =new JFileChooser ("D:\\");
				f.setDialogTitle("Mở file");
				f.showOpenDialog(null);
				try {
					File ftenanh = f.getSelectedFile();
					String path = ftenanh.getAbsolutePath();
					if (path != "") {
						BufferedImage img = ImageIO.read(ftenanh);
						Image dimg = img.getScaledInstance(lblAvatar.getWidth(), lblAvatar.getHeight(),
						        Image.SCALE_SMOOTH);
						ImageIcon icon = new ImageIcon(dimg);
						lblAvatar.setIcon(icon);
					}
				} catch (Exception err) {
					JOptionPane.showMessageDialog(btnChonAnh, "Không có ảnh được chọn!");
				}
			}
		});
		btnChonAnh.setBounds(67, 262, 105, 50);
		panel.add(btnChonAnh);
		
		lblAvatar = new JLabel("\u1EA2nh");
		lblAvatar.setIcon(new ImageIcon("D:/2.jpg"));
		lblAvatar.setBounds(20, 11, 200, 240);
		panel.add(lblAvatar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 313, 686, 254);
		add(panel_1);
		panel_1.setLayout(null);
		
		JComboBox<String> cbGender = new JComboBox<String>();
		cbGender.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cbGender.addItem("nam");
		cbGender.addItem("nu");
		cbGender.addItem("khac");
		cbGender.setBounds(166, 235, 181, 27);
		add(cbGender);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 686, 254);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flagThem = false;
				int i = table.getSelectedRow();
				txtMaNhanVien.setText(table.getValueAt(i, 0).toString());
				txtHoTen.setText(table.getValueAt(i, 1).toString());
				txtDiaChi.setText(table.getValueAt(i, 3).toString());
				txtLuong.setText(table.getValueAt(i, 5).toString());
				txtMaNQL.setText(table.getValueAt(i, 6).toString());
				txtNgaySinh.setText(table.getValueAt(i, 2).toString());
				txtPhong.setText(table.getValueAt(i, 7).toString());
				String gender = table.getValueAt(i, 4).toString();
				cbGender.setSelectedItem(gender);
				try {
					ImageIcon img = (ImageIcon)table.getValueAt(i, 8);
					lblAvatar.setIcon(img);
				} catch (Exception e2) {
					lblAvatar.setIcon(null);
				}
				EnableControl();
			}
		});
		LoadData(null);
		scrollPane.setViewportView(table);
		
		btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flagThem = true;
				ClearContent();
				EnableControl();
				btnXoa.setEnabled(false);
			}
		});
		btnThem.setIcon(new ImageIcon(getClass().getResource("/images/icons8_add_32px.png")));
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setBounds(706, 395, 115, 50);
		add(btnThem);
		
		btnHuy = new JButton("H\u1EE7y");
		btnHuy.setEnabled(false);
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisableControl();
				ClearContent();
				flagThem = false;
			}
		});
		btnHuy.setIcon(new ImageIcon(getClass().getResource("/images/icons8_cancel_32px.png")));
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnHuy.setBounds(831, 456, 115, 50);
		add(btnHuy);
		
		btnLuu = new JButton("L\u01B0u");
		btnLuu.setEnabled(false);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int maNQL;
				if(txtMaNQL.getText().trim().isEmpty()) {
					maNQL = 0;
				}
				else {
					maNQL = Integer.valueOf(txtMaNQL.getText());
				}
				NhanVien nv = new NhanVien(Integer.valueOf(txtMaNhanVien.getText()),
						   txtHoTen.getText(),
						   Date.valueOf(txtNgaySinh.getText()),
						   txtDiaChi.getText(),
						   cbGender.getSelectedItem().toString(),
						   txtLuong.getText(),
						   maNQL,
						   Integer.valueOf(txtPhong.getText()),
						   (ImageIcon)lblAvatar.getIcon());
				if(flagThem) {
					if(NhanVienDAO.themNhanVien(nv)) {
						JOptionPane.showMessageDialog(btnLuu, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						LoadData(null);
					}
					else {
						JOptionPane.showMessageDialog(btnLuu, "Vui lòng kiểm tra lại thông tin!", "Thông báo", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				else {
					if(NhanVienDAO.suaNhanVien(nv)) {
						JOptionPane.showMessageDialog(btnLuu, "Cập nhật thông tin nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						LoadData(null);
					}
					else {
						JOptionPane.showMessageDialog(btnLuu, "Vui lòng kiểm tra lại thông tin!", "Thông báo", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				ClearContent();
				DisableControl();
				flagThem = false;
			}
		});
		btnLuu.setIcon(new ImageIcon(getClass().getResource("/images/icons8_save_32px.png")));
		btnLuu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLuu.setBounds(831, 395, 115, 50);
		add(btnLuu);
		
		btnXoa = new JButton("X\u00F3a");
		btnXoa.setEnabled(false);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf(txtMaNhanVien.getText());
				if(JOptionPane.showConfirmDialog(btnXoa, "Bạn có chắc xóa thông tin nhân viên " + txtHoTen.getText() + "?",
						"Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(NhanVienDAO.xoaNhanVien(id)) {
						JOptionPane.showMessageDialog(btnXoa, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						LoadData(null);
					}
					else {
						JOptionPane.showMessageDialog(btnXoa, "Không thể xóa thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					}
				}
				ClearContent();
				DisableControl();
				flagThem = false;
			}
		});
		btnXoa.setIcon(new ImageIcon(getClass().getResource("/images/icons8_delete_32px.png")));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoa.setBounds(706, 456, 115, 50);
		add(btnXoa);
		
		JButton btnReLoad = new JButton("Tải lại");
		btnReLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadData(null);
			}
		});
		btnReLoad.setIcon(new ImageIcon(NhanVienForm.class.getResource("/images/reset_30px.png")));
		btnReLoad.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnReLoad.setBounds(706, 517, 115, 50);
		add(btnReLoad);
		
		JButton btnFind = new JButton("Tìm");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPhong.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(btnFind, "Không thể tìm thấy thông tin!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					LoadData(null);
				}
				else {
					LoadData(txtPhong.getText());
				}
			}
		});
		btnFind.setToolTipText("Tìm theo phòng ban");
		btnFind.setIcon(new ImageIcon(NhanVienForm.class.getResource("/images/search_32px.png")));
		btnFind.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnFind.setBounds(831, 517, 115, 50);
		add(btnFind);
	}
	
	private void EnableControl() {
		btnLuu.setEnabled(true);
		btnHuy.setEnabled(true);
		btnXoa.setEnabled(true);
	}
	
	private void DisableControl() {
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);
		btnXoa.setEnabled(false);
	}
	
	private void ClearContent() {
		txtMaNhanVien.setText("");
		txtHoTen.setText("");
		txtNgaySinh.setText("");
		txtDiaChi.setText("");
		txtLuong.setText("");
		txtMaNQL.setText("");
		txtPhong.setText("");
		lblAvatar.setIcon(null);
	}
	
	private void LoadData(String MaPB) {
		String[] labels = {"Mã NV", "Họ tên", "Ngày sinh", "Địa chỉ", "Giới tính", "Lương cơ bản", "Mã NQL", "Phòng", "Image"};
		DefaultTableModel tbModel = new DefaultTableModel(labels, 0);
		
		ArrayList<NhanVien> nhanViens;
		if(MaPB == null) {
			nhanViens = NhanVienDAO.LayThongTinNhanVien();
		}
		else {
			nhanViens = NhanVienDAO.LayThongTinNhanVien(Integer.valueOf(MaPB));
		}
		
		try {
			for (NhanVien nhanVien : nhanViens) {
				String MaNQL = (nhanVien.getMaNQL() == 0) ? "" : String.valueOf(nhanVien.getMaNQL());
				Object[] row = {nhanVien.getMaNhanVien(),
								nhanVien.getHoTen(),
								nhanVien.getNgaySinh(),
								nhanVien.getDiaChi(),
								nhanVien.getPhai(),
								nhanVien.getLuong(),
								MaNQL,
								nhanVien.getMaPB(),
								nhanVien.getImage()};
				tbModel.addRow(row);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		table.setModel(tbModel);
		table.getColumnModel().getColumn(8).setMinWidth(0);
		table.getColumnModel().getColumn(8).setMaxWidth(0);
	}
}
