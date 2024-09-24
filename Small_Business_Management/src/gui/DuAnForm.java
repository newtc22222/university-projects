package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bean.DuAn;
import dao.DuAnDAO;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DuAnForm extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaDuAn;
	private JTextField txtTenDuAn;
	private JTextField txtDiaDiem;
	private JTextField txtPhong;
	private JTable table;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnHuy;
	private JButton btnXoa;
	private boolean flagThem = false;

	/**
	 * Create the panel.
	 */
	public DuAnForm() {
		setLayout(null);
		
		JLabel lblQunLD = new JLabel("Qu\u1EA3n L\u00FD D\u1EF1 \u00C1n");
		lblQunLD.setBounds(10, 10, 305, 30);
		lblQunLD.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lblQunLD);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 50, 936, 13);
		add(separator);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 193, 936, 356);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 936, 356);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flagThem = false;
				int i = table.getSelectedRow();
				txtMaDuAn.setText(table.getValueAt(i, 0).toString());
				txtTenDuAn.setText(table.getValueAt(i, 1).toString());
				txtDiaDiem.setText(table.getValueAt(i, 2).toString());
				txtPhong.setText(table.getValueAt(i, 3).toString());
				EnableControl();
			}
		});
		LoadData(null);
		scrollPane.setViewportView(table);
		
		JLabel lblMDn = new JLabel("M\u00E3 D\u1EF1 \u00C1n");
		lblMDn.setBounds(10, 73, 95, 30);
		lblMDn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add(lblMDn);
		
		txtMaDuAn = new JTextField();
		txtMaDuAn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaDuAn.setBounds(118, 75, 95, 30);
		txtMaDuAn.setColumns(10);
		add(txtMaDuAn);
		
		JLabel lblTnDn = new JLabel("T\u00EAn D\u1EF1 \u00C1n");
		lblTnDn.setBounds(10, 114, 95, 30);
		lblTnDn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add(lblTnDn);
		
		txtTenDuAn = new JTextField();
		txtTenDuAn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTenDuAn.setBounds(118, 114, 453, 30);
		txtTenDuAn.setColumns(10);
		add(txtTenDuAn);
		
		JLabel lblaim = new JLabel("\u0110\u1ECBa \u0110i\u1EC3m");
		lblaim.setBounds(10, 150, 95, 30);
		lblaim.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add(lblaim);
		
		txtDiaDiem = new JTextField();
		txtDiaDiem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDiaDiem.setBounds(118, 152, 453, 30);
		txtDiaDiem.setColumns(10);
		add(txtDiaDiem);
		
		JLabel lblPhng = new JLabel("Ph\u00F2ng");
		lblPhng.setBounds(223, 73, 57, 30);
		lblPhng.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add(lblPhng);
		
		txtPhong = new JTextField();
		txtPhong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtPhong.setBounds(290, 74, 281, 30);
		txtPhong.setColumns(10);
		add(txtPhong);
		
		btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flagThem = true;
				ClearContent();
				EnableControl();
				btnXoa.setEnabled(false);
			}
		});
		btnThem.setBounds(706, 74, 115, 50);
		btnThem.setIcon(new ImageIcon(getClass().getResource("/images/icons8_add_32px.png")));
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
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
		btnHuy.setBounds(831, 74, 115, 50);
		btnHuy.setIcon(new ImageIcon(getClass().getResource("/images/icons8_cancel_32px.png")));
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add(btnHuy);
		
		btnXoa = new JButton("X\u00F3a");
		btnXoa.setEnabled(false);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf(txtMaDuAn.getText());
				if(JOptionPane.showConfirmDialog(btnXoa, "Bạn có chắc xóa thông tin dự án \n" + txtTenDuAn.getText() + "?", 
						"Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(DuAnDAO.xoaDuAn(id)) {
						JOptionPane.showMessageDialog(btnXoa, "Xóa thông tin dự án thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
		btnXoa.setBounds(831, 132, 115, 50);
		btnXoa.setIcon(new ImageIcon(getClass().getResource("/images/icons8_delete_32px.png")));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add(btnXoa);
		
		btnLuu = new JButton("L\u01B0u");
		btnLuu.setEnabled(false);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DuAn da = new DuAn(Integer.valueOf(txtMaDuAn.getText()),
								   txtTenDuAn.getText(),
								   txtDiaDiem.getText(),
								   Integer.valueOf(txtPhong.getText()));
				if(flagThem) {
					if(DuAnDAO.themDuAn(da)) {
						JOptionPane.showMessageDialog(btnLuu, "Đã thêm thông tin dự án mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						LoadData(null);
					}
					else {
						JOptionPane.showMessageDialog(btnLuu, "Vui lòng kiểm tra lại thông tin!", "Thông báo", JOptionPane.ERROR_MESSAGE);
						return;
					}	
				}
				else {
					if(DuAnDAO.suaDuAn(da)) {
						JOptionPane.showMessageDialog(btnLuu, "Đã sửa thông tin dự án!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
		btnLuu.setBounds(706, 132, 115, 50);
		btnLuu.setIcon(new ImageIcon(getClass().getResource("/images/icons8_save_32px.png")));
		btnLuu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add(btnLuu);
		
		JButton btnReload = new JButton("Tải lại");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadData(null);
			}
		});
		btnReload.setIcon(new ImageIcon(DuAnForm.class.getResource("/images/reset_30px.png")));
		btnReload.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnReload.setBounds(581, 74, 115, 50);
		add(btnReload);
		
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
		btnFind.setToolTipText("Tìm dự án theo số phòng");
		btnFind.setIcon(new ImageIcon(DuAnForm.class.getResource("/images/search_32px.png")));
		btnFind.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnFind.setBounds(581, 132, 115, 50);
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
	
	private void LoadData(String maPB) {
		String[] labels = {"Mã dự án", "Tên dự án", "Địa điểm", "Phòng"};
		DefaultTableModel model = new DefaultTableModel(labels, 0);
		
		ArrayList<DuAn> danhSach;
		if(maPB == null) {
			danhSach = DuAnDAO.LayThongTinDuAn();
		}
		else {
			danhSach = DuAnDAO.LayThongTinDuAn(Integer.valueOf(maPB));
		}
		
		try {
			for(DuAn da : danhSach) {
				Object[] row = {da.getMaDuAn(),
								da.getTenDuAn(),
								da.getDiaDiem(),
								da.getPhong()};
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		table.setModel(model);
	}
	
	private void ClearContent() {
		txtMaDuAn.setText("");
		txtTenDuAn.setText("");
		txtPhong.setText("");
		txtDiaDiem.setText("");
	}
}
