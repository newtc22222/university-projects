package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bean.ThanNhan;
import dao.ThanNhanDAO;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ThanNhanForm extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtTenTN;
	private JTextField txtMaNV;
	private JTextField txtNgaySinh;
	private JTextField txtQuanhe;
	private static JTable table;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnHuy;
	private JButton btnXoa;
	private boolean flagThem = false;

	/**
	 * Create the panel.
	 */
	public ThanNhanForm() {
		setLayout(null);
		
		JLabel lblThngTinThn = new JLabel("Th\u00F4ng tin th\u00E2n nh\u00E2n c\u1EE7a nh\u00E2n vi\u00EAn");
		lblThngTinThn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblThngTinThn.setBounds(10, 11, 305, 30);
		add(lblThngTinThn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 52, 936, 13);
		add(separator);
		
		JLabel lblTnThnNhn = new JLabel("T\u00EAn th\u00E2n nh\u00E2n");
		lblTnThnNhn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTnThnNhn.setBounds(10, 76, 113, 30);
		add(lblTnThnNhn);
		
		JLabel lblThiGian_1_1 = new JLabel("M\u00E3 nh\u00E2n vi\u00EAn");
		lblThiGian_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThiGian_1_1.setBounds(10, 124, 113, 30);
		add(lblThiGian_1_1);
		
		JLabel lblThiGian_1_2 = new JLabel("Gi\u1EDBi t\u00EDnh");
		lblThiGian_1_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThiGian_1_2.setBounds(10, 177, 113, 30);
		add(lblThiGian_1_2);
		
		JLabel lblNgySinh = new JLabel("Ng\u00E0y sinh");
		lblNgySinh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNgySinh.setBounds(10, 230, 113, 30);
		add(lblNgySinh);
		
		JLabel lblThiGian_1_1_1 = new JLabel("Quan h\u1EC7");
		lblThiGian_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThiGian_1_1_1.setBounds(10, 282, 113, 30);
		add(lblThiGian_1_1_1);
		
		txtTenTN = new JTextField();
		txtTenTN.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTenTN.setColumns(10);
		txtTenTN.setBounds(154, 76, 193, 30);
		add(txtTenTN);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(154, 124, 193, 30);
		add(txtMaNV);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(154, 230, 193, 30);
		add(txtNgaySinh);
		
		txtQuanhe = new JTextField();
		txtQuanhe.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtQuanhe.setColumns(10);
		txtQuanhe.setBounds(154, 282, 193, 30);
		add(txtQuanhe);
		
		JComboBox<String> cbGioiTinh = new JComboBox<String>();
		cbGioiTinh.addItem("nam");
		cbGioiTinh.addItem("nu");
		cbGioiTinh.addItem("khac");
		cbGioiTinh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cbGioiTinh.setBounds(154, 177, 193, 30);
		add(cbGioiTinh);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(357, 76, 589, 481);
		add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 589, 481);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				txtTenTN.setText(table.getValueAt(i, 0).toString());
				txtMaNV.setText(table.getValueAt(i, 1).toString());
				cbGioiTinh.setSelectedItem(table.getValueAt(i, 2).toString());
				txtNgaySinh.setText(table.getValueAt(i, 3).toString());
				txtQuanhe.setText(table.getValueAt(i, 4).toString());
				EnableControl();
			}
		});
		LoadData(null);
		scrollPane.setViewportView(table);
		
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flagThem = true;
				ClearContent();
				EnableControl();
				btnXoa.setEnabled(false);
			}
		});
		btnThem.setIcon(new ImageIcon(ThanNhanForm.class.getResource("/images/icons8_add_32px.png")));
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setBounds(10, 428, 140, 50);
		add(btnThem);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThanNhan tn = new ThanNhan(txtTenTN.getText(),
										   Integer.valueOf(txtMaNV.getText()),
										   cbGioiTinh.getSelectedItem().toString(),
										   Date.valueOf(txtNgaySinh.getText()),
										   txtQuanhe.getText());
				if(flagThem) {
					if(ThanNhanDAO.themThanNhan(tn)) {
						JOptionPane.showMessageDialog(btnLuu, "Đã thêm thông tin thân nhân mới!");
						LoadData(null);
					}
					else {
						JOptionPane.showMessageDialog(btnLuu, "Vui lòng kiểm tra lại thông tin!");
						return;
					}
				}
				else {
					if(ThanNhanDAO.suaThanNhan(tn)) {
						JOptionPane.showMessageDialog(btnLuu, "Đã sửa thông tin thân nhân!");
						LoadData(null);
					}
					else {
						JOptionPane.showMessageDialog(btnLuu, "Vui lòng kiểm tra lại thông tin!");
						return;
					}
				}
				ClearContent();
				DisableControl();
				flagThem = false;
			}
		});
		btnLuu.setIcon(new ImageIcon(ThanNhanForm.class.getResource("/images/icons8_save_32px.png")));
		btnLuu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLuu.setBounds(10, 503, 140, 50);
		add(btnLuu);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setEnabled(false);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnXoa, "Bạn có chắc xóa thông tin của thân nhân " + txtTenTN.getText() + "?",
						"Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(ThanNhanDAO.xoaThanNhan(txtTenTN.getText())) {
						JOptionPane.showMessageDialog(btnXoa, "Xóa thông tin thành công!");
						LoadData(null);
					}
					else {
						JOptionPane.showMessageDialog(btnXoa, "Không thể xóa thông tin!");
					}
				}
				ClearContent();
				DisableControl();
				flagThem = false;
			}
		});
		btnXoa.setIcon(new ImageIcon(ThanNhanForm.class.getResource("/images/icons8_delete_32px.png")));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoa.setBounds(207, 503, 140, 50);
		add(btnXoa);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setEnabled(false);
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearContent();
				DisableControl();
				flagThem = false;
			}
		});
		btnHuy.setIcon(new ImageIcon(ThanNhanForm.class.getResource("/images/icons8_cancel_32px.png")));
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnHuy.setBounds(207, 428, 140, 50);
		add(btnHuy);
		
		JButton btnReLoad = new JButton("Tải lại");
		btnReLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadData(null);
			}
		});
		btnReLoad.setIcon(new ImageIcon(ThanNhanForm.class.getResource("/images/reset_30px.png")));
		btnReLoad.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnReLoad.setBounds(10, 355, 140, 50);
		add(btnReLoad);
		
		JButton btnFind = new JButton("Tìm");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMaNV.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(btnFind, "Không thể tìm thấy thông tin!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					LoadData(null);
				}
				else {
					LoadData(txtMaNV.getText());
				}
			}
		});
		btnFind.setToolTipText("Tìm theo mã nhân viên");
		btnFind.setIcon(new ImageIcon(ThanNhanForm.class.getResource("/images/search_32px.png")));
		btnFind.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnFind.setBounds(207, 355, 140, 50);
		add(btnFind);
	}
	
	private static void LoadData(String MaNVTN) {
		String[] labels = {"Tên thân nhân", "Mã nhân viên", "Giới tính", "Ngày sinh", "Quan hệ"};
		DefaultTableModel model = new DefaultTableModel(labels, 0);
		
		ArrayList<ThanNhan> danhSach;
		if (MaNVTN == null) {
			danhSach = ThanNhanDAO.LayThongTinThanNhan();
		}
		else {
			danhSach = ThanNhanDAO.LayThongTinThanNhan(Integer.valueOf(MaNVTN));
		}
		
		try {
			for(ThanNhan tn : danhSach) {
				Object[] row = {tn.getTenThanNhan(),
								tn.getMaNVTN(),
								tn.getPhai(),
								tn.getNgaySinh(),
								tn.getQuanHe()};
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		table.setModel(model);
	}
	
	private void ClearContent() {
		txtMaNV.setText("");
		txtTenTN.setText("");
		txtNgaySinh.setText("");
		txtQuanhe.setText("");
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
}
