package Controller.member;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.EtchedBorder;

import Dao.member.member_implement;
import Model.member_management;
import Util.util_member;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;

public class member_lnr extends JFrame {

	private JPanel contentPane;
	private JTextField acc_tf;
	private JPasswordField pwd_tf;
	private String v_c;
	private ImageIcon img_vc;
	private JTextField v_tf;
	private JTextField m_nn_tf;
	private JTextField em_a_tf;
	private JTextField acc_r_tf;
	private JPasswordField pwd_r_tf;
	private JPasswordField cfm_pwd_r_tf;
	private JTextField pn_tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					member_lnr frame = new member_lnr();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public member_lnr() {
		Image img = new ImageIcon(member_lnr.class.getResource("/surfing_i.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setVisible(true);
		requestFocusInWindow(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		tabbedPane.setBounds(0, 0, 450, 670);
		tabbedPane.setBackground(Color.ORANGE);
		contentPane.add(tabbedPane);
		
		JPanel login_pg = new JPanel();
		login_pg.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		login_pg.setBackground(new Color(255, 165, 0));
		tabbedPane.addTab("登入", null, login_pg, null);
		tabbedPane.setBackgroundAt(0, new Color(255, 165, 0));
		login_pg.setLayout(null);
		
		ImageIcon sur_pic_img = new ImageIcon(this.getClass().getResource("/surfing.png"));
		JLabel surf_pic = new JLabel(sur_pic_img);
		surf_pic.setBounds(85, 20, 256, 256);
		login_pg.add(surf_pic);
		
		JLabel s_n = new JLabel("快銀衝浪");
		s_n.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		s_n.setHorizontalAlignment(SwingConstants.CENTER);
		s_n.setBounds(165, 290, 100, 30);
		login_pg.add(s_n);
		
		JLabel acc_t = new JLabel("帳號");
		acc_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		acc_t.setHorizontalAlignment(SwingConstants.CENTER);
		acc_t.setBounds(90, 330, 80, 30);
		login_pg.add(acc_t);
		
		JLabel pwd_t = new JLabel("密碼");
		pwd_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		pwd_t.setHorizontalAlignment(SwingConstants.CENTER);
		pwd_t.setBounds(90, 370, 80, 30);
		login_pg.add(pwd_t);
		
		acc_tf = new JTextField("請輸入帳號");
		acc_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		acc_tf.setBounds(150, 330, 130, 30);
		acc_tf.setForeground(Color.GRAY);
		login_pg.add(acc_tf);
		acc_tf.setColumns(10);
		util_member.placeholder_tf(acc_tf, "請輸入帳號");
		
		JLabel acc_l_h = new JLabel("");
		acc_l_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		acc_l_h.setForeground(Color.RED);
		acc_l_h.setHorizontalAlignment(SwingConstants.CENTER);
		acc_l_h.setBounds(265, 330, 80, 30);
		login_pg.add(acc_l_h);
		
		pwd_tf = new JPasswordField("請輸入密碼");
		pwd_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		pwd_tf.setBounds(150, 370, 130, 30);
		pwd_tf.setEchoChar((char)0);
		pwd_tf.setForeground(Color.GRAY);
		login_pg.add(pwd_tf);
		pwd_tf.setColumns(10);
		util_member.placeholder_pwd_l(pwd_tf, "請輸入密碼");
		
		JLabel pwd_l_h = new JLabel("");
		pwd_l_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		pwd_l_h.setForeground(Color.RED);
		pwd_l_h.setHorizontalAlignment(SwingConstants.CENTER);
		pwd_l_h.setBounds(265, 370, 80, 30);
		login_pg.add(pwd_l_h);
		
		JLabel v_pic_t = new JLabel("驗證碼");
		v_pic_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		v_pic_t.setHorizontalAlignment(SwingConstants.CENTER);
		v_pic_t.setBounds(105, 410, 80, 30);
		login_pg.add(v_pic_t);
		
		v_c = util_member.generate_v_pic(Paths.get("").toAbsolutePath().toString());
		img_vc = new ImageIcon(Paths.get("").toAbsolutePath().toString() + "/v_c.png");
		JLabel v_pic = new JLabel(img_vc);
		v_pic.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		v_pic.setHorizontalAlignment(SwingConstants.CENTER);
		v_pic.setBounds(175, 410, 80, 30);
		login_pg.add(v_pic);
		
		v_tf = new JTextField("請輸入驗證碼");
		v_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		v_tf.setBounds(165, 450, 100, 30);
		v_tf.setForeground(Color.GRAY);
		login_pg.add(v_tf);
		v_tf.setColumns(10);
		util_member.placeholder_tf(v_tf, "請輸入驗證碼");
		
		JLabel vc_l_h = new JLabel("*區分大小寫");
		vc_l_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		vc_l_h.setForeground(Color.RED);
		vc_l_h.setHorizontalAlignment(SwingConstants.CENTER);
		vc_l_h.setBounds(155, 470, 120, 30);
		login_pg.add(vc_l_h);
		
		JButton gen_n_vc = new JButton("換一張");
		gen_n_vc.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		gen_n_vc.setHorizontalAlignment(SwingConstants.CENTER);
		gen_n_vc.setBounds(260, 410, 80, 30);
		login_pg.add(gen_n_vc);
		
		JLabel forget_pwd = new JLabel("忘記密碼？");
		forget_pwd.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		forget_pwd.setHorizontalAlignment(SwingConstants.CENTER);
		forget_pwd.setBounds(175, 560, 80, 30);
		login_pg.add(forget_pwd);
		
		JLabel clock_lbl = new JLabel("");
		clock_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		clock_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		clock_lbl.setForeground(Color.DARK_GRAY);
		clock_lbl.setBounds(125, 600, 180, 20);
		login_pg.add(clock_lbl);
		util_member.login_clock(clock_lbl);
		
		JButton login_cfm = new JButton("登入");
		login_cfm.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		login_cfm.setBounds(180, 500, 60, 35);
		login_pg.add(login_cfm);
		
		JPanel register_pg = new JPanel();
		register_pg.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		register_pg.setBackground(new Color(255, 165, 0));
		tabbedPane.addTab("註冊", null, register_pg, null);
		tabbedPane.setBackgroundAt(1, new Color(255, 165, 0));
		register_pg.setLayout(null);
		
		JLabel m_n = new JLabel("會員資料");
		m_n.setHorizontalAlignment(SwingConstants.CENTER);
		m_n.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		m_n.setBounds(20, 20, 100, 40);
		register_pg.add(m_n);
		
		JLabel m_nn = new JLabel("暱稱");
		m_nn.setHorizontalAlignment(SwingConstants.CENTER);
		m_nn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_nn.setBounds(60, 80, 50, 30);
		register_pg.add(m_nn);
		
		m_nn_tf = new JTextField("會員暱稱[1 - 12位]");
		m_nn_tf.setBounds(110, 80, 130, 26);
		m_nn_tf.setForeground(Color.GRAY);
		m_nn_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		register_pg.add(m_nn_tf);
		m_nn_tf.setColumns(10);
		util_member.placeholder_tf(m_nn_tf, "會員暱稱[1 - 12位]");
		
		JLabel m_nn_h = new JLabel("");
		m_nn_h.setHorizontalAlignment(SwingConstants.LEFT);
		m_nn_h.setForeground(Color.RED);
		m_nn_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		m_nn_h.setBounds(120, 100, 120, 30);
		register_pg.add(m_nn_h);
		
		JLabel m_g = new JLabel("性別");
		m_g.setHorizontalAlignment(SwingConstants.CENTER);
		m_g.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_g.setBounds(240, 80, 50, 30);
		register_pg.add(m_g);
		
		String [] g_arr = new String[2];
		g_arr[0] = "男";
		g_arr[1] = "女";
		JComboBox m_g_cb = new JComboBox(g_arr);
		m_g_cb.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_g_cb.setBounds(290, 82, 70, 27);
		m_g_cb.setSelectedIndex(-1);
		register_pg.add(m_g_cb);
		
		JLabel m_g_h = new JLabel("");
		m_g_h.setHorizontalAlignment(SwingConstants.LEFT);
		m_g_h.setForeground(Color.RED);
		m_g_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		m_g_h.setBounds(300, 100, 80, 30);
		register_pg.add(m_g_h);
		
		JLabel m_bd = new JLabel("生日");
		m_bd.setHorizontalAlignment(SwingConstants.CENTER);
		m_bd.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_bd.setBounds(60, 140, 50, 30);
		register_pg.add(m_bd);
		
		String[] y_arr = util_member.gen_y_arr();
		JComboBox m_bd_y = new JComboBox(y_arr);
		m_bd_y.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_bd_y.setBounds(110, 142, 90, 27);
		m_bd_y.setSelectedIndex(0);
		register_pg.add(m_bd_y);
		
		String[] m_arr = util_member.gen_m_arr();
		JComboBox m_bd_m = new JComboBox(m_arr);
		m_bd_m.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_bd_m.setBounds(210, 142, 70, 27);
		m_bd_m.setSelectedIndex(0);
		register_pg.add(m_bd_m);
		
		int y = Integer.parseInt((String) m_bd_y.getSelectedItem());
		int m = Integer.parseInt((String) m_bd_m.getSelectedItem());
		String[] d_arr = util_member.gen_d_arr(y, m);
		JComboBox m_bd_d = new JComboBox(d_arr);
		m_bd_d.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_bd_d.setBounds(290, 142, 70, 27);
		m_bd_d.setSelectedIndex(-1);
		register_pg.add(m_bd_d);
		util_member.update_m_bd_d_cb(m_bd_d, m_bd_y, m_bd_m);

		JLabel bd_h = new JLabel("");
		bd_h.setHorizontalAlignment(SwingConstants.LEFT);
		bd_h.setForeground(Color.RED);
		bd_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		bd_h.setBounds(300, 160, 100, 30);
		register_pg.add(bd_h);
		
		JLabel pn_lbl = new JLabel("行動電話");
		pn_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		pn_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		pn_lbl.setBounds(60, 200, 70, 30);
		register_pg.add(pn_lbl);
		
		String[] cc_arr = util_member.gen_cc_arr();
		JComboBox cc_l = new JComboBox(cc_arr);
		cc_l.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		cc_l.setBounds(130, 202, 120, 27);
		cc_l.setSelectedIndex(102);
		register_pg.add(cc_l);
		
		pn_tf = new JTextField("09XX XXX XXX");
		pn_tf.setBounds(260, 200, 100, 26);
		pn_tf.setForeground(Color.GRAY);
		pn_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		register_pg.add(pn_tf);
		pn_tf.setColumns(10);
		util_member.placeholder_tf(pn_tf, "09XX XXX XXX");
		
		JLabel pn_h = new JLabel("");
		pn_h.setHorizontalAlignment(SwingConstants.LEFT);
		pn_h.setForeground(Color.RED);
		pn_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		pn_h.setBounds(270, 220, 100, 30);
		register_pg.add(pn_h);
		
		JLabel em_a = new JLabel("Email");
		em_a.setHorizontalAlignment(SwingConstants.CENTER);
		em_a.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		em_a.setBounds(60, 260, 50, 30);
		register_pg.add(em_a);
		
		em_a_tf = new JTextField();
		em_a_tf.setBounds(110, 260, 250, 26);
		em_a_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		register_pg.add(em_a_tf);
		em_a_tf.setColumns(10);
		
		JLabel em_a_h = new JLabel("");
		em_a_h.setHorizontalAlignment(SwingConstants.LEFT);
		em_a_h.setForeground(Color.RED);
		em_a_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		em_a_h.setBounds(120, 280, 100, 30);
		register_pg.add(em_a_h);
		
		JLabel acc_r_lbl = new JLabel("帳號");
		acc_r_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		acc_r_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		acc_r_lbl.setBounds(60, 320, 50, 30);
		register_pg.add(acc_r_lbl);
		
		acc_r_tf = new JTextField("會員帳號[8 - 12位]");
		acc_r_tf.setBounds(110, 320, 250, 26);
		acc_r_tf.setForeground(Color.GRAY);
		acc_r_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		register_pg.add(acc_r_tf);
		acc_r_tf.setColumns(10);
		util_member.placeholder_tf(acc_r_tf, "會員帳號[8 - 12位]");
		
		JLabel acc_r_h = new JLabel("");
		acc_r_h.setHorizontalAlignment(SwingConstants.LEFT);
		acc_r_h.setForeground(Color.RED);
		acc_r_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		acc_r_h.setBounds(120, 340, 150, 30);
		register_pg.add(acc_r_h);
		
		JLabel pwd_r_lbl = new JLabel("密碼");
		pwd_r_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		pwd_r_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		pwd_r_lbl.setBounds(60, 380, 50, 30);
		register_pg.add(pwd_r_lbl);
		
		JLabel pwd_r_wp = new JLabel("");
		pwd_r_wp.setHorizontalAlignment(SwingConstants.CENTER);
		pwd_r_wp.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		pwd_r_wp.setBounds(350, 380, 50, 30);
		register_pg.add(pwd_r_wp);
		
		JLabel pwd_h = new JLabel("*至少須包含一個大寫字母、一個小寫字母和一個數字");
		pwd_h.setHorizontalAlignment(SwingConstants.LEFT);
		pwd_h.setForeground(Color.RED);
		pwd_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		pwd_h.setBounds(120, 400, 250, 30);
		register_pg.add(pwd_h);
		
		pwd_r_tf = new JPasswordField("會員密碼[8 - 12位]");
		pwd_r_tf.setBounds(110, 380, 250, 26);
		pwd_r_tf.setEchoChar((char)0);
		pwd_r_tf.setForeground(Color.GRAY);
		pwd_r_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		register_pg.add(pwd_r_tf);
		pwd_r_tf.setColumns(10);
		util_member.placeholder_pwd(pwd_r_tf, "會員密碼[8 - 12位]", pwd_r_wp, pwd_h);
		
		JLabel cfm_pwd_r_lbl = new JLabel("確認密碼");
		cfm_pwd_r_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		cfm_pwd_r_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		cfm_pwd_r_lbl.setBounds(60, 440, 70, 30);
		register_pg.add(cfm_pwd_r_lbl);
		
		JLabel cfm_pwd_r_wp = new JLabel("");
		cfm_pwd_r_wp.setHorizontalAlignment(SwingConstants.CENTER);
		cfm_pwd_r_wp.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		cfm_pwd_r_wp.setBounds(350, 440, 50, 30);
		register_pg.add(cfm_pwd_r_wp);
		
		cfm_pwd_r_tf = new JPasswordField("請再次輸入密碼");
		cfm_pwd_r_tf.setBounds(130, 440, 230, 26);
		cfm_pwd_r_tf.setEchoChar((char)0);
		cfm_pwd_r_tf.setForeground(Color.GRAY);
		cfm_pwd_r_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		register_pg.add(cfm_pwd_r_tf);
		cfm_pwd_r_tf.setColumns(10);
		util_member.placeholder_cfm_pwd(cfm_pwd_r_tf, pwd_r_tf, "請再次輸入密碼", cfm_pwd_r_wp);
		
		JLabel cfm_pwd_h = new JLabel("");
		cfm_pwd_h.setHorizontalAlignment(SwingConstants.LEFT);
		cfm_pwd_h.setForeground(Color.RED);
		cfm_pwd_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		cfm_pwd_h.setBounds(140, 460, 120, 30);
		register_pg.add(cfm_pwd_h);
		
		JButton bm_btn = new JButton("建立會員");
		bm_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		bm_btn.setBounds(164, 510, 100, 35);
		register_pg.add(bm_btn);
		
		
		//換一張
		gen_n_vc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v_c = util_member.refresh_vc_pic(v_c, img_vc, v_pic);
			}
		});
		
		//登入
		login_cfm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acc_l_h.setText("");
				pwd_l_h.setText("");
				vc_l_h.setText("");
				String m_acc = acc_tf.getText();
				String m_pwd = String.valueOf(pwd_tf.getPassword());
				member_implement m_sql_funcs = new member_implement();
				if(!v_tf.getText().equals(v_c)) {
					v_c = util_member.refresh_vc_pic(v_c, img_vc, v_pic);
					v_tf.setText("");
					vc_l_h.setText("*請輸入正確的驗證碼");
					return;
				} else {
					vc_l_h.setText("");
				}
				if(!(m_sql_funcs.read_member_login_time(m_acc) == null)) {
					Date mlt = null;
					Date rn = null;
					try {
						mlt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(m_sql_funcs.read_member_login_time(m_acc));
						rn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(clock_lbl.getText());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if((long) (mlt.getTime() - rn.getTime()) > 0) {
						v_c = util_member.refresh_vc_pic(v_c, img_vc, v_pic);
						v_tf.setText("");
						JOptionPane.showMessageDialog(null, "錯誤次數達3次，請於" 
							+ m_sql_funcs.read_member_login_time(m_acc).split("\\.")[0] + "後再登入");
						return;
					} else {
						m_sql_funcs.reset_mt_n_mlt(m_acc);
					}
				}
				
				if(m_sql_funcs.read_member_ie(m_acc)) {
					acc_l_h.setText("");
					if(m_pwd.equals(m_sql_funcs.read_member_pwd(m_acc))) {
						pwd_l_h.setText("");
						if(m_acc.equals("admin123")) {
							admin_funcs.setMember_acc(m_acc);
							admin_funcs af = new admin_funcs();
							af.setVisible(true);
							dispose();
							return;
						}
						member_funcs.setMember_acc(m_acc);
						member_funcs mf = new member_funcs();
						mf.setVisible(true);
						dispose();
					} else {
						if(m_sql_funcs.read_member_try(m_acc) == 0) {
							m_sql_funcs.set_member_try(m_acc);
						} else if(m_sql_funcs.read_member_try(m_acc) > 0 && m_sql_funcs.read_member_try(m_acc) < 3) {
							Date mlt = null;
							Date rn = null;
							try {
								mlt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(m_sql_funcs.read_member_try_1st(m_acc));
								rn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(clock_lbl.getText());
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if((long) (mlt.getTime() - rn.getTime()) > 0) {
								m_sql_funcs.add_member_try(m_acc);
							} else {
								m_sql_funcs.reset_mt_n_mlt(m_acc);
								m_sql_funcs.set_member_try(m_acc);
							}
							if(m_sql_funcs.read_member_try(m_acc) == 3) {
								m_sql_funcs.set_member_login_time(m_acc);
								JOptionPane.showMessageDialog(null, "錯誤次數達3次，請於" 
										+ m_sql_funcs.read_member_login_time(m_acc).split("\\.")[0] + "後再登入");
							}
						}
						v_c = util_member.refresh_vc_pic(v_c, img_vc, v_pic);
						v_tf.setText("");
						pwd_l_h.setText("*密碼錯誤");
					}
				} else if(!m_sql_funcs.read_member_ie(m_acc)) {
					v_c = util_member.refresh_vc_pic(v_c, img_vc, v_pic);
					v_tf.setText("");
					acc_l_h.setText("*無此帳號");
				}
			}
		});
		
		//變色
		forget_pwd.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				forget_pwd.setForeground(Color.BLUE);
				setCursor(new Cursor(12));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				forget_pwd.setForeground(Color.BLACK);
				setCursor(null);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				member_retrieve_pwd mrp = new member_retrieve_pwd();
				mrp.setVisible(true);
				acc_l_h.setText("");
				pwd_l_h.setText("");
				vc_l_h.setText("");
			}
		});

		
		/*
		 * private JTextField acc_tf;
	private JPasswordField pwd_tf;
	private String v_c;
	private Image img_vc;
	private JLabel v_pic;
	private JTextField v_tf;
	private JTextField m_nn_tf;
	private JTextField em_a_tf;
	private JTextField acc_r_tf;
	private JPasswordField pwd_r_tf;
	private JPasswordField cfm_pwd_r_tf;
	private JTextField pn_tf;
	private JLabel bd_h;
		 */
		//確認帳號
		acc_r_tf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				util_member.acc_r_tf_listener(acc_r_tf, acc_r_h);
			}
		});
		
		//建立會員
		bm_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(util_member.field_checker(m_nn_tf, m_nn_h, m_g_cb, m_g_h, m_bd_d, bd_h, pn_tf, pn_h, em_a_tf, em_a_h,
						acc_r_tf, acc_r_h, pwd_r_tf, pwd_h, cfm_pwd_r_tf, cfm_pwd_h)) {
					String m_nn = m_nn_tf.getText();
					String m_g = (String) m_g_cb.getSelectedItem();
					String m_bd = (String) m_bd_y.getSelectedItem() + "-" + (String) m_bd_m.getSelectedItem() + "-" + (String) m_bd_d.getSelectedItem();
					String m_c = ((String) cc_l.getSelectedItem()).split(" ")[1];
					String m_pn = pn_tf.getText();
					String m_em = em_a_tf.getText();
					String m_acc = acc_r_tf.getText();
					String m_pwd = String.valueOf(pwd_r_tf.getPassword());
					member_management m = new member_management(m_nn, m_g, m_bd, m_c, m_pn, m_em, m_acc, m_pwd);	
					boolean result = new member_implement().create_member(m);
					if(result) {
						JOptionPane.showMessageDialog(null, "建立成功");
						setVisible(false);
						member_lnr btl_pg = new member_lnr();
						btl_pg.setVisible(true);
						btl_pg.getContentPane().requestFocusInWindow();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "建立失敗");
					}
				}
			}
		});
	}
}
