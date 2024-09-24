package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import bean.PhanCong;
import dao.PhanCongDAO;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class PhanCongForm extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtThoiGian;
	private JTextField txtTienThuong;
	private JTextField txtMaDuAn;
	private JTextField txtTenDuAn;
	private JTextField txtPhongTrienKhai;
	private JTextField txtPhongCuaNhanVien;
	private JTextField txtTenNhanVien;
	private JTextField txtMaNhanVien;
	private static JTable table;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnHuy;
	private JButton btnXoa;
	private boolean flagThem = false;

	/**
	 * Create the panel.
	 */
	public PhanCongForm() {
		setLayout(null);
		
		JLabel lblQunLPhn = new JLabel("Thông Tin Phân Công ");
		lblQunLPhn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblQunLPhn.setBounds(10, 10, 305, 30);
		add(lblQunLPhn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 50, 936, 13);
		add(separator);
		
		JLabel lblThiGian = new JLabel("Th\u1EDDi gian");
		lblThiGian.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThiGian.setBounds(10, 60, 113, 30);
		add(lblThiGian);
		
		txtThoiGian = new JTextField();
		txtThoiGian.setToolTipText("Ngày bắt đầu dự án");
		txtThoiGian.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtThoiGian.setColumns(10);
		txtThoiGian.setBounds(133, 61, 330, 30);
		add(txtThoiGian);
		
		JLabel lblThi = new JLabel("Ti\u1EC1n th\u01B0\u1EDFng");
		lblThi.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThi.setBounds(10, 101, 113, 30);
		add(lblThi);
		
		txtTienThuong = new JTextField();
		txtTienThuong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTienThuong.setColumns(10);
		txtTienThuong.setBounds(133, 102, 330, 30);
		add(txtTienThuong);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(10, 327, 453, 177);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblThngTinNhn = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblThngTinNhn.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngTinNhn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThngTinNhn.setBounds(10, 11, 433, 30);
		panel_1.add(lblThngTinNhn);
		
		txtPhongCuaNhanVien = new JTextField();
		txtPhongCuaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtPhongCuaNhanVien.setColumns(10);
		txtPhongCuaNhanVien.setBounds(162, 134, 221, 30);
		panel_1.add(txtPhongCuaNhanVien);
		
		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(162, 94, 281, 30);
		panel_1.add(txtTenNhanVien);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaNhanVien.setColumns(10);
		txtMaNhanVien.setBounds(162, 52, 221, 30);
		panel_1.add(txtMaNhanVien);
		
		JLabel lblMNhnVin = new JLabel("M\u00E3 nh\u00E2n vi\u00EAn");
		lblMNhnVin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMNhnVin.setBounds(10, 50, 142, 30);
		panel_1.add(lblMNhnVin);
		
		JLabel lblThiGian_1_1_1 = new JLabel("T\u00EAn nh\u00E2n vi\u00EAn");
		lblThiGian_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThiGian_1_1_1.setBounds(10, 92, 142, 30);
		panel_1.add(lblThiGian_1_1_1);
		
		JLabel lblThiGian_1_2_1 = new JLabel("Ph\u00F2ng c\u1EE7a nh\u00E2n vi\u00EAn");
		lblThiGian_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThiGian_1_2_1.setBounds(10, 132, 142, 30);
		panel_1.add(lblThiGian_1_2_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(192, 192, 192));
		panel_1_1.setForeground(Color.WHITE);
		panel_1_1.setBounds(10, 142, 453, 178);
		add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblThngTinD = new JLabel("THÔNG TIN DỰ ÁN");
		lblThngTinD.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngTinD.setBackground(UIManager.getColor("Button.shadow"));
		lblThngTinD.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThngTinD.setBounds(10, 11, 433, 30);
		panel_1_1.add(lblThngTinD);
		
		txtMaDuAn = new JTextField();
		txtMaDuAn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaDuAn.setColumns(10);
		txtMaDuAn.setBounds(162, 52, 221, 30);
		panel_1_1.add(txtMaDuAn);
		
		JLabel lblMDn = new JLabel("M\u00E3 d\u1EF1 \u00E1n");
		lblMDn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMDn.setBounds(10, 50, 142, 30);
		panel_1_1.add(lblMDn);
		
		JLabel lblThiGian_1_1 = new JLabel("T\u00EAn d\u1EF1 \u00E1n");
		lblThiGian_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThiGian_1_1.setBounds(10, 92, 142, 30);
		panel_1_1.add(lblThiGian_1_1);
		
		txtTenDuAn = new JTextField();
		txtTenDuAn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTenDuAn.setColumns(10);
		txtTenDuAn.setBounds(162, 92, 281, 30);
		panel_1_1.add(txtTenDuAn);
		
		JLabel lblThiGian_1_2 = new JLabel("Ph\u00F2ng tri\u1EC3n khai");
		lblThiGian_1_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThiGian_1_2.setBounds(10, 132, 142, 30);
		panel_1_1.add(lblThiGian_1_2);
		
		txtPhongTrienKhai = new JTextField();
		txtPhongTrienKhai.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtPhongTrienKhai.setColumns(10);
		txtPhongTrienKhai.setBounds(162, 134, 221, 30);
		panel_1_1.add(txtPhongTrienKhai);
		
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flagThem = true;
				ClearContent();
				EnableControl();
				btnXoa.setEnabled(false);
			}
		});
		btnThem.setIcon(new ImageIcon(PhanCongForm.class.getResource("/images/icons8_add_32px.png")));
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setBounds(249, 515, 115, 50);
		add(btnThem);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setEnabled(false);
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearContent();
				DisableControl();
				flagThem = false;
			}
		});
		btnHuy.setIcon(new ImageIcon(PhanCongForm.class.getResource("/images/icons8_cancel_32px.png")));
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnHuy.setBounds(613, 515, 115, 50);
		add(btnHuy);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date ngBatDau = (txtThoiGian.getText().trim().isEmpty()) ? null: Date.valueOf(txtThoiGian.getText()); 
				PhanCong pc = new PhanCong(Integer.valueOf(txtMaNhanVien.getText()),
										   Integer.valueOf(txtMaDuAn.getText()), 
						   				   ngBatDau,
						   				   Integer.valueOf(txtTienThuong.getText()));
				if(flagThem) {
					if(PhanCongDAO.themPhanCong(pc)) {
						JOptionPane.showMessageDialog(btnLuu, "Đã thêm tạo phân công mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						LoadData();
					}
					else {
						JOptionPane.showMessageDialog(btnLuu, "Vui lòng kiểm tra lại thông tin!", "Thông báo", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				else {
					if(PhanCongDAO.suaPhanCong(pc)) {
						JOptionPane.showMessageDialog(btnLuu, "Đã sửa thông tin phân công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						LoadData();
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
		btnLuu.setIcon(new ImageIcon(PhanCongForm.class.getResource("/images/icons8_save_32px.png")));
		btnLuu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLuu.setBounds(431, 515, 115, 50);
		add(btnLuu);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setEnabled(false);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnXoa, "Bạn có chắc xóa nhân viên " + txtTenNhanVien.getText() +
						" ra khỏi dự án " + txtTenDuAn.getText() + "?", 
						"Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(PhanCongDAO.xoaPhanCong(Integer.valueOf(txtMaDuAn.getText()), 
											   Integer.valueOf(txtMaNhanVien.getText()))) {
						JOptionPane.showMessageDialog(btnXoa, "Xóa thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						LoadData();
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
		btnXoa.setIcon(new ImageIcon(PhanCongForm.class.getResource("/images/icons8_delete_32px.png")));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoa.setBounds(795, 515, 115, 50);
		add(btnXoa);
		
		JPanel panel = new JPanel();
		panel.setBounds(493, 60, 453, 444);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 453, 446);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				LoadDataToComponent(Integer.valueOf(table.getValueAt(i, 0).toString()),
									Integer.valueOf(table.getValueAt(i, 1).toString()));
				EnableControl();
			}
		});
		LoadData();
		scrollPane.setColumnHeaderView(table);
		
		JButton btnReLoad = new JButton("Tải lại");
		btnReLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadData();
			}
		});
		btnReLoad.setIcon(new ImageIcon(PhanCongForm.class.getResource("/images/reset_30px.png")));
		btnReLoad.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnReLoad.setBounds(67, 515, 115, 50);
		add(btnReLoad);
	}
	
	private void LoadDataToComponent(int maNVPC, int maDa) {
		Object[] datas = PhanCongDAO.layThongTinChiTiet(maNVPC, maDa);
		if(datas != null) {
			txtMaNhanVien.setText(datas[0].toString());
			txtMaDuAn.setText(datas[1].toString());
			if(datas[2] != null) {
				txtThoiGian.setText(datas[2].toString());				
			}
			else {
				txtThoiGian.setText("");
			}
			txtTienThuong.setText(datas[3].toString());
			txtTenDuAn.setText(datas[4].toString());
			txtPhongTrienKhai.setText(datas[5].toString());
			txtTenNhanVien.setText(datas[6].toString());
			if(datas[7] == null) {
				txtPhongCuaNhanVien.setText("");
			}
			else {
				txtPhongCuaNhanVien.setText(datas[7].toString());
			}
		}
	}
	
	private static void LoadData() {
		String[] labels = {"Mã nhân viên", "Mã dự án", "Ngày bắt đầu", "Tiền thưởng"};
		DefaultTableModel model = new DefaultTableModel(labels, 0);
		ArrayList<PhanCong> danhSach = PhanCongDAO.LayThongTinPhanCong();
		try {
			for(PhanCong pc : danhSach) {
				Object[] row = {pc.getMaNhanVienPhanCong(),
								pc.getMaDuAn(),
								pc.getThoiGian(),
								pc.getTienThuong()};
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		table.setModel(model);
	}
	
	private void ClearContent() {
		txtMaDuAn.setText("");
		txtMaNhanVien.setText("");
		txtPhongCuaNhanVien.setText("");
		txtPhongTrienKhai.setText("");
		txtTenDuAn.setText("");
		txtTenNhanVien.setText("");
		txtThoiGian.setText("");
		txtTienThuong.setText("");
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
