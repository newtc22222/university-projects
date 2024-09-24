package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Desktop;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.net.URI;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame {
	private static final long serialVersionUID = 1L;

	private NhanVienForm mStaffPanel;
	private PhongBanForm mRoomPanel;
	private DuAnForm mProjectPanel;
	private PhanCongForm mAssignmentPanel;
	private ThanNhanForm mRelativePanel;
	private JTabbedPane tpPane;
	private static MainForm frmMain = null;
	private String taiKhoan;
	JMenuItem mntmDangXuat = new JMenuItem("\u0110\u0103ng xu\u1EA5t");

	public static synchronized MainForm getInstance(String tenTaiKhoan) {
		try {

			frmMain = new MainForm(tenTaiKhoan);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return frmMain;
	}

	public MainForm(String taiKhoan) {
		setType(Type.UTILITY);
		this.taiKhoan = taiKhoan;
		initialize();
	}

	private void btnDangXuatActionPerformed(ActionEvent e) {
		if (JOptionPane.showConfirmDialog(mntmDangXuat, "Bạn muốn đăng xuất?", "Thông báo", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			tpPane.removeAll();
			mAssignmentPanel = null;
			mProjectPanel = null;
			mRelativePanel = null;
			mRoomPanel = null;
			mStaffPanel = null;
			this.dispose();
			LoginForm.getInstance().setVisible(true);

		}
	}

	private void initialize() {
		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(100, 100, 1000, 700);
		getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 986, 30);
		menuBar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		getContentPane().add(menuBar);

		JMenu mnHeThong = new JMenu("H\u1EC7 th\u1ED1ng");
		mnHeThong.setFont(new Font("Times New Roman", Font.BOLD, 16));
		menuBar.add(mnHeThong);

		mntmDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDangXuatActionPerformed(e);
			}
		});

		JMenuItem mntmDoiMatKhau = new JMenuItem("Đổi mật khẩu");
		mntmDoiMatKhau.setIcon(new ImageIcon(getClass().getResource("/images/password_32px.png")));
		mntmDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoiMatKhauForm frmDoiMatKhau = new DoiMatKhauForm(taiKhoan);
				frmDoiMatKhau.setLocationRelativeTo(null);
				frmDoiMatKhau.setVisible(true);
			}
		});
		mntmDoiMatKhau.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnHeThong.add(mntmDoiMatKhau);

		JSeparator separator_4 = new JSeparator();
		mnHeThong.add(separator_4);
		mntmDangXuat.setIcon(new ImageIcon(getClass().getResource("/images/icons8_Logout_32px.png")));
		mntmDangXuat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnHeThong.add(mntmDangXuat);

		JSeparator separator = new JSeparator();
		mnHeThong.add(separator);

		JMenuItem mntmThoat = new JMenuItem("Tho\u00E1t");
		mntmThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(mntmThoat, "Bạn muốn tắt ứng dụng?", "Thông báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		mntmThoat.setIcon(new ImageIcon(getClass().getResource("/images/icons8_cancel_32px.png")));
		mntmThoat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnHeThong.add(mntmThoat);

		JMenu mnQuanLy = new JMenu("Qu\u1EA3n L\u00FD");
		mnQuanLy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		menuBar.add(mnQuanLy);

		JMenu mnTroGiup = new JMenu("Tr\u1EE3 Gi\u00FAp");
		mnTroGiup.setFont(new Font("Times New Roman", Font.BOLD, 16));
		menuBar.add(mnTroGiup);

		JMenuItem mntmThanhVien = new JMenuItem("Nhóm phát triển");
		mntmThanhVien.setIcon(new ImageIcon(MainForm.class.getResource("/images/icons8_group_32px.png")));
		mntmThanhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String DanhSachThanhVien = "19110365 - Phạm Đinh Quốc Hòa\n";
				DanhSachThanhVien += "19110426 - Võ Nhật Phi\n";
				DanhSachThanhVien += "19110436 - Phạm Văn Thắng";
				JOptionPane.showMessageDialog(mntmThanhVien, DanhSachThanhVien);
			}
		});
		mntmThanhVien.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnTroGiup.add(mntmThanhVien);

		JSeparator separator_2 = new JSeparator();
		mnTroGiup.add(separator_2);

		JMenuItem mntmCongTac = new JMenuItem("Cộng tác");
		mntmCongTac.setIcon(new ImageIcon(MainForm.class.getResource("/images/icons8_email_send_32px.png")));
		mntmCongTac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					  Desktop desktop = java.awt.Desktop.getDesktop();
					  URI oURL = new URI("https://github.com/hoadaknong101/NNLTTT_PROJECT");
					  desktop.browse(oURL);
				} catch (Exception err) {
					  err.printStackTrace();
				}
			}
		});
		mntmCongTac.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnTroGiup.add(mntmCongTac);

		tpPane = new JTabbedPane(JTabbedPane.TOP);
		tpPane.setBounds(10, 40, 966, 613);
		getContentPane().add(tpPane);

		JMenuItem mntmQuanLyNhanVien = new JMenuItem("Qu\u1EA3n L\u00FD Nh\u00E2n Vi\u00EAn");
		mntmQuanLyNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mStaffPanel == null) {
					mStaffPanel = new NhanVienForm();
					tpPane.addTab("Quản Lý Nhân Viên", mStaffPanel);

					int index = tpPane.indexOfTab("Quản Lý Nhân Viên");
					JPanel pnlTab = new JPanel(new GridBagLayout());
					pnlTab.setOpaque(false);
					JLabel lblTitle = new JLabel("Quản Lý Nhân Viên");
					JButton btnClose = new JButton("x");

					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.weightx = 1;

					pnlTab.add(lblTitle, gbc);
					gbc.gridx++;
					gbc.weightx = 0;
					pnlTab.add(btnClose, gbc);

					tpPane.setTabComponentAt(index, pnlTab);
					btnClose.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int index = tpPane.indexOfTab("Quản Lý Nhân Viên");
							Component selected = tpPane.getComponentAt(index);
							if (selected != null) {
								mStaffPanel = null;
								tpPane.remove(selected);
							}
						}
					});
					tpPane.setSelectedComponent(mStaffPanel);
				}
				tpPane.setSelectedComponent(mStaffPanel);
			}
		});
		mntmQuanLyNhanVien.setIcon(new ImageIcon(getClass().getResource("/images/icons8_name_tag_32px.png")));
		mntmQuanLyNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnQuanLy.add(mntmQuanLyNhanVien);

		JMenuItem mntmQuanLyPhongBan = new JMenuItem("Qu\u1EA3n L\u00FD Ph\u00F2ng Ban");
		mntmQuanLyPhongBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mRoomPanel == null) {
					mRoomPanel = new PhongBanForm();
					tpPane.addTab("Quản Lý Phòng Ban", mRoomPanel);

					int index = tpPane.indexOfTab("Quản Lý Phòng Ban");
					JPanel pnlTab = new JPanel(new GridBagLayout());
					pnlTab.setOpaque(false);
					JLabel lblTitle = new JLabel("Quản Lý Phòng Ban");
					JButton btnClose = new JButton("x");

					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.weightx = 1;

					pnlTab.add(lblTitle, gbc);
					gbc.gridx++;
					gbc.weightx = 0;
					pnlTab.add(btnClose, gbc);

					tpPane.setTabComponentAt(index, pnlTab);
					btnClose.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int index = tpPane.indexOfTab("Quản Lý Phòng Ban");
							Component selected = tpPane.getComponentAt(index);
							if (selected != null) {
								mRoomPanel = null;
								tpPane.remove(selected);
							}
						}
					});
					tpPane.setSelectedComponent(mRoomPanel);
				}
				tpPane.setSelectedComponent(mRoomPanel);
			}
		});

		JSeparator separator_1 = new JSeparator();
		mnQuanLy.add(separator_1);
		mntmQuanLyPhongBan.setIcon(new ImageIcon(getClass().getResource("/images/icons8_room_32px.png")));
		mntmQuanLyPhongBan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnQuanLy.add(mntmQuanLyPhongBan);
		JMenuItem mntmQuanLyDuAn = new JMenuItem("Qu\u1EA3n L\u00FD D\u1EF1 \u00C1n");
		mntmQuanLyDuAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mProjectPanel == null) {
					mProjectPanel = new DuAnForm();
					tpPane.addTab("Quản Lý Dự Án", mProjectPanel);

					int index = tpPane.indexOfTab("Quản Lý Dự Án");
					JPanel pnlTab = new JPanel(new GridBagLayout());
					pnlTab.setOpaque(false);
					JLabel lblTitle = new JLabel("Quản Lý Dự Án");
					JButton btnClose = new JButton("x");

					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.weightx = 1;

					pnlTab.add(lblTitle, gbc);
					gbc.gridx++;
					gbc.weightx = 0;
					pnlTab.add(btnClose, gbc);

					tpPane.setTabComponentAt(index, pnlTab);
					btnClose.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int index = tpPane.indexOfTab("Quản Lý Dự Án");
							Component selected = tpPane.getComponentAt(index);
							if (selected != null) {
								mProjectPanel = null;
								tpPane.remove(selected);
							}
						}
					});
					tpPane.setSelectedComponent(mProjectPanel);
				}
				tpPane.setSelectedComponent(mProjectPanel);
			}
		});

		JSeparator separator_3 = new JSeparator();
		mnQuanLy.add(separator_3);
		mntmQuanLyDuAn.setIcon(new ImageIcon(getClass().getResource("/images/icons8_project_32px.png")));
		mntmQuanLyDuAn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnQuanLy.add(mntmQuanLyDuAn);

		JMenuItem mntmPhanCong = new JMenuItem("Phân công công việc");
		mntmPhanCong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mAssignmentPanel == null) {
					mAssignmentPanel = new PhanCongForm();
					tpPane.addTab("Phân công công việc", mAssignmentPanel);

					int index = tpPane.indexOfTab("Phân công công việc");
					JPanel pnlTab = new JPanel(new GridBagLayout());
					pnlTab.setOpaque(false);
					JLabel lblTitle = new JLabel("Phân công công việc");
					JButton btnClose = new JButton("x");

					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.weightx = 1;

					pnlTab.add(lblTitle, gbc);
					gbc.gridx++;
					gbc.weightx = 0;
					pnlTab.add(btnClose, gbc);

					tpPane.setTabComponentAt(index, pnlTab);
					btnClose.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int index = tpPane.indexOfTab("Phân công công việc");
							Component selected = tpPane.getComponentAt(index);
							if (selected != null) {
								mAssignmentPanel = null;
								tpPane.remove(selected);
							}
						}
					});
					tpPane.setSelectedComponent(mAssignmentPanel);
				}
				tpPane.setSelectedComponent(mAssignmentPanel);
			}
		});

		JSeparator separator_5 = new JSeparator();
		mnQuanLy.add(separator_5);
		mntmPhanCong.setIcon(new ImageIcon(getClass().getResource("/images/icons8_salary_male_32px.png")));
		mntmPhanCong.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnQuanLy.add(mntmPhanCong);

		JSeparator separator_5_1 = new JSeparator();
		mnQuanLy.add(separator_5_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Thông tin thân nhân");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mRelativePanel == null) {
					mRelativePanel = new ThanNhanForm();
					tpPane.addTab("Thông tin thân nhân", mRelativePanel);

					int index = tpPane.indexOfTab("Thông tin thân nhân");
					JPanel pnlTab = new JPanel(new GridBagLayout());
					pnlTab.setOpaque(false);
					JLabel lblTitle = new JLabel("Thông tin thân nhân");
					JButton btnClose = new JButton("x");

					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.weightx = 1;

					pnlTab.add(lblTitle, gbc);
					gbc.gridx++;
					gbc.weightx = 0;
					pnlTab.add(btnClose, gbc);

					tpPane.setTabComponentAt(index, pnlTab);
					btnClose.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int index = tpPane.indexOfTab("Thông tin thân nhân");
							Component selected = tpPane.getComponentAt(index);
							if (selected != null) {
								mRelativePanel = null;
								tpPane.remove(selected);
							}
						}
					});
					tpPane.setSelectedComponent(mRelativePanel);
				}
				tpPane.setSelectedComponent(mRelativePanel);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainForm.class.getResource("/images/family_32px.png")));
		mntmNewMenuItem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnQuanLy.add(mntmNewMenuItem);
	}
}
