package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bean.PhongBan;
import dao.PhongBanDAO;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PhongBanForm extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaPhongBan;
	private JTextField txtTenPhongBan;
	private JTextField txtTruongPhong;
	private JTextField txtNgayNhanChuc;
	private JTextField txtDiaDiem;
	private JTable table;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnHuy;
	private JButton btnXoa;
	private boolean flagThem = false;

	/**
	 * Create the panel.
	 */
	public PhongBanForm() {
		setLayout(null);
		
		JLabel lblQunLPhng = new JLabel("Qu\u1EA3n L\u00FD Ph\u00F2ng Ban");
		lblQunLPhng.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblQunLPhng.setBounds(10, 10, 305, 30);
		add(lblQunLPhng);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 46, 936, 13);
		add(separator);
		
		JLabel lblMPhngBan = new JLabel("M\u00E3 Ph\u00F2ng Ban");
		lblMPhngBan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMPhngBan.setBounds(10, 69, 113, 30);
		add(lblMPhngBan);
		
		txtMaPhongBan = new JTextField();
		txtMaPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaPhongBan.setColumns(10);
		txtMaPhongBan.setBounds(133, 71, 144, 30);
		add(txtMaPhongBan);
		
		JLabel lblTnPhngBan = new JLabel("T\u00EAn Ph\u00F2ng Ban");
		lblTnPhngBan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTnPhngBan.setBounds(10, 111, 113, 30);
		add(lblTnPhngBan);
		
		txtTenPhongBan = new JTextField();
		txtTenPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTenPhongBan.setColumns(10);
		txtTenPhongBan.setBounds(133, 112, 502, 30);
		add(txtTenPhongBan);
		
		txtTruongPhong = new JTextField();
		txtTruongPhong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTruongPhong.setColumns(10);
		txtTruongPhong.setBounds(410, 70, 225, 30);
		add(txtTruongPhong);
		
		JLabel lblTrngPhng = new JLabel("Tr\u01B0\u1EDFng Ph\u00F2ng\r\n");
		lblTrngPhng.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTrngPhng.setBounds(287, 69, 113, 30);
		add(lblTrngPhng);
		
		JLabel lblNgyNhnChc = new JLabel("Ng\u00E0y Nh\u1EADn Ch\u1EE9c");
		lblNgyNhnChc.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNgyNhnChc.setBounds(10, 151, 129, 30);
		add(lblNgyNhnChc);
		
		txtNgayNhanChuc = new JTextField();
		txtNgayNhanChuc.setToolTipText("\u0110\u1ECBnh d\u1EA1ng: YYYY-MM-DD");
		txtNgayNhanChuc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtNgayNhanChuc.setColumns(10);
		txtNgayNhanChuc.setBounds(133, 153, 267, 30);
		add(txtNgayNhanChuc);
		
		JLabel lblaim = new JLabel("\u0110\u1ECBa \u0110i\u1EC3m");
		lblaim.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblaim.setBounds(10, 191, 129, 30);
		add(lblaim);
		
		txtDiaDiem = new JTextField();
		txtDiaDiem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDiaDiem.setColumns(10);
		txtDiaDiem.setBounds(133, 193, 502, 30);
		add(txtDiaDiem);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 231, 936, 324);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 936, 324);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				txtMaPhongBan.setText(table.getValueAt(i, 0).toString());
				txtTenPhongBan.setText(table.getValueAt(i, 1).toString());
				if(table.getValueAt(i, 2) != null)
					txtTruongPhong.setText(table.getValueAt(i, 2).toString());
				else 
					txtTruongPhong.setText("");
				if(table.getValueAt(i, 3) != null)
					txtNgayNhanChuc.setText(table.getValueAt(i, 3).toString());
				else
					txtNgayNhanChuc.setText("");
				txtDiaDiem.setText(table.getValueAt(i, 4).toString());
				EnableControl();
			}
		});
		LoadData();
		scrollPane.setViewportView(table);
		
		btnXoa = new JButton("X\u00F3a");
		btnXoa.setEnabled(false);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf(txtMaPhongBan.getText());
				if(JOptionPane.showConfirmDialog(btnXoa, "Bạn có chắc xóa thông tin phòng " + txtTenPhongBan.getText() + "?", 
						"Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(PhongBanDAO.xoaPhongBan(id)) {
						JOptionPane.showMessageDialog(btnXoa, "Xóa phòng ban thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
		btnXoa.setIcon(new ImageIcon(getClass().getResource("/images/icons8_delete_32px.png")));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoa.setBounds(817, 150, 129, 71);
		add(btnXoa);
		
		btnLuu = new JButton("L\u01B0u");
		btnLuu.setEnabled(false);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int truongPhong = (txtTruongPhong.getText().trim().isEmpty()) ? 0 : Integer.valueOf(txtTruongPhong.getText());
				Date ngNhanChuc = (txtNgayNhanChuc.getText().trim().isEmpty()) ? null : Date.valueOf(txtNgayNhanChuc.getText());
				PhongBan pb = new PhongBan(Integer.valueOf(txtMaPhongBan.getText()),
						   txtTenPhongBan.getText(),
						   truongPhong,
						   ngNhanChuc,
						   txtDiaDiem.getText());
				if(flagThem) {
					if(PhongBanDAO.themPhongBan(pb)) {
						JOptionPane.showMessageDialog(btnLuu, "Đã thêm thông tin phòng ban mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						LoadData();
					}
					else {
						JOptionPane.showMessageDialog(btnLuu, "Vui lòng kiểm tra lại thông tin!", "Thông báo", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				else {
					if(PhongBanDAO.suaPhongBan(pb)) {
						JOptionPane.showMessageDialog(btnLuu, "Đã sửa thông tin phòng ban!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
		btnLuu.setIcon(new ImageIcon(getClass().getResource("/images/icons8_save_32px.png")));
		btnLuu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLuu.setBounds(678, 150, 129, 71);
		add(btnLuu);
		
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
		btnThem.setBounds(678, 70, 129, 71);
		add(btnThem);
		
		btnHuy = new JButton("H\u1EE7y");
		btnHuy.setEnabled(false);
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearContent();
				DisableControl();
				flagThem = false;
			}
		});
		btnHuy.setIcon(new ImageIcon(getClass().getResource("/images/icons8_cancel_32px.png")));
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnHuy.setBounds(817, 70, 129, 71);
		add(btnHuy);

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
	
	private void LoadData() {
		String[] labels = {"Mã phòng ban", "Tên phòng ban", "Mã trưởng phòng", "Ngày nhận chức", "Địa điểm"};
		DefaultTableModel model = new DefaultTableModel(labels, 0);
		ArrayList<PhongBan> danhSach = PhongBanDAO.LayThongTinPhongBan();
		try {
			for(PhongBan pb : danhSach) {
				String maTP = (pb.getMaTruongPhong() == 0) ? "" : String.valueOf(pb.getMaTruongPhong());
				Object[] row = {pb.getMaPhongBan(),
								pb.getTenPhongBan(),
								maTP,
								pb.getNgayNhanChuc(),
								pb.getDiaDiem()};
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		table.setModel(model);
	}
	
	private void ClearContent() {
		txtMaPhongBan.setText("");
		txtTenPhongBan.setText("");
		txtTruongPhong.setText("");
		txtNgayNhanChuc.setText("");
		txtDiaDiem.setText("");
	}
}
