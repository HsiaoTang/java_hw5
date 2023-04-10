package Controller.member;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Dao.member.member_implement;
import Dao.order.order_implement;
import Model.member_management;
import Util.util_member;

public class member_info extends JFrame {

	private JPanel contentPane;
	private static String member_acc;
	private static member_management m;
	private JTextField m_nn_tf;
	private JTextField pn_tf;
	private JTextField em_a_tf;
	private JTextField acc_r_tf;
	private JPasswordField o_pwd_tf;
	private JPasswordField n_pwd_tf;
	private JPasswordField cfm_n_pwd_tf;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					member_info frame = new member_info();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public static void setMember_acc(String member_acc) {
		member_info.member_acc = member_acc;
	}
	
	public static String getMember_acc() {
		return member_info.member_acc;
	}
	
	public static void setM(member_management m) {
		member_info.m = m;
	}
	
	public static member_management getM() {
		return member_info.m;
	}
	
	/**
	 * Create the frame.
	 */
	
	public member_info() {
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
		
		JLabel m_n = new JLabel("會員資料");
		m_n.setHorizontalAlignment(SwingConstants.CENTER);
		m_n.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		m_n.setBounds(20, 20, 100, 40);
		contentPane.add(m_n);
		
		JLabel m_nn = new JLabel("暱稱");
		m_nn.setHorizontalAlignment(SwingConstants.CENTER);
		m_nn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_nn.setBounds(60, 80, 50, 30);
		contentPane.add(m_nn);
		
		m_nn_tf = new JTextField("會員暱稱[1 - 12位]");
		m_nn_tf.setBounds(110, 80, 130, 26);
		m_nn_tf.setForeground(Color.GRAY);
		m_nn_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(m_nn_tf);
		m_nn_tf.setColumns(10);
		m_nn_tf.setText(member_info.m.getMember_nn());
		util_member.placeholder_tf(m_nn_tf, "會員暱稱[1 - 12位]");
		
		JLabel m_nn_h = new JLabel("");
		m_nn_h.setHorizontalAlignment(SwingConstants.LEFT);
		m_nn_h.setForeground(Color.RED);
		m_nn_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		m_nn_h.setBounds(120, 100, 120, 30);
		contentPane.add(m_nn_h);
		
		JLabel m_g = new JLabel("性別");
		m_g.setHorizontalAlignment(SwingConstants.CENTER);
		m_g.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_g.setBounds(240, 80, 50, 30);
		contentPane.add(m_g);
		
		String [] g_arr = new String[2];
		g_arr[0] = "男";
		g_arr[1] = "女";
		JComboBox m_g_cb = new JComboBox(g_arr);
		m_g_cb.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_g_cb.setBounds(290, 82, 70, 27);
		m_g_cb.setSelectedIndex(-1);
		contentPane.add(m_g_cb);
		for(int i = 0; i < g_arr.length; i++) {
			if(g_arr[i].equals(member_info.m.getMember_gender())) {
				m_g_cb.setSelectedIndex(i);
			}
		}
		
		JLabel m_g_h = new JLabel("");
		m_g_h.setHorizontalAlignment(SwingConstants.LEFT);
		m_g_h.setForeground(Color.RED);
		m_g_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		m_g_h.setBounds(300, 100, 80, 30);
		contentPane.add(m_g_h);
		
		JLabel m_bd_lbl = new JLabel("生日");
		m_bd_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		m_bd_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_bd_lbl.setBounds(60, 140, 50, 30);
		contentPane.add(m_bd_lbl);
		
		String[] y_arr = util_member.gen_y_arr();
		JComboBox m_bd_y = new JComboBox(y_arr);
		m_bd_y.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_bd_y.setBounds(110, 142, 90, 27);
		m_bd_y.setSelectedIndex(0);
		contentPane.add(m_bd_y);
		String[] m_bd = member_info.m.getMember_bd().split("-");
		for(int i = 0; i < y_arr.length; i++) {
			if(y_arr[i].equals(m_bd[0])) {
				m_bd_y.setSelectedIndex(i);
			}
		}
		
		String[] m_arr = util_member.gen_m_arr();
		JComboBox m_bd_m = new JComboBox(m_arr);
		m_bd_m.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_bd_m.setBounds(210, 142, 70, 27);
		m_bd_m.setSelectedIndex(0);
		contentPane.add(m_bd_m);
		for(int i = 0; i < m_arr.length; i++) {
			if(m_arr[i].equals(m_bd[1].replace("0", ""))) {
				m_bd_m.setSelectedIndex(i);
			}
		}
		
		int y = Integer.parseInt((String) m_bd_y.getSelectedItem());
		int m = Integer.parseInt((String) m_bd_m.getSelectedItem());
		String[] d_arr = util_member.gen_d_arr(y, m);
		JComboBox m_bd_d = new JComboBox(d_arr);
		m_bd_d.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_bd_d.setBounds(290, 142, 70, 27);
		m_bd_d.setSelectedIndex(-1);
		contentPane.add(m_bd_d);
		for(int i = 0; i < d_arr.length; i++) {
			if(d_arr[i].equals(m_bd[2].replace("0", ""))) {
				m_bd_d.setSelectedIndex(i);
			}
		}
		util_member.update_m_bd_d_cb(m_bd_d, m_bd_y, m_bd_m);
		
		JLabel bd_h = new JLabel("");
		bd_h.setHorizontalAlignment(SwingConstants.LEFT);
		bd_h.setForeground(Color.RED);
		bd_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		bd_h.setBounds(300, 160, 100, 30);
		contentPane.add(bd_h);
		
		JLabel pn_lbl = new JLabel("行動電話");
		pn_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		pn_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		pn_lbl.setBounds(60, 200, 70, 30);
		contentPane.add(pn_lbl);
		
		String[] cc_arr = util_member.gen_cc_arr();
		JComboBox cc_l = new JComboBox(cc_arr);
		cc_l.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		cc_l.setBounds(130, 202, 120, 27);
		cc_l.setSelectedIndex(102);
		contentPane.add(cc_l);
		for(int i = 0; i < cc_arr.length; i++) {
			if(cc_arr[i].split(" ")[1].equals(member_info.m.getMember_country())) {
				cc_l.setSelectedIndex(i);
			}
		}
		
		pn_tf = new JTextField("09XX XXX XXX");
		pn_tf.setBounds(260, 200, 100, 26);
		pn_tf.setForeground(Color.GRAY);
		pn_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(pn_tf);
		pn_tf.setColumns(10);
		pn_tf.setText(member_info.m.getMember_pn());
		util_member.placeholder_tf(pn_tf, "09XX XXX XXX");
		
		JLabel pn_h = new JLabel("");
		pn_h.setHorizontalAlignment(SwingConstants.LEFT);
		pn_h.setForeground(Color.RED);
		pn_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		pn_h.setBounds(270, 220, 100, 30);
		contentPane.add(pn_h);
		
		JLabel em_a = new JLabel("Email");
		em_a.setHorizontalAlignment(SwingConstants.CENTER);
		em_a.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		em_a.setBounds(60, 260, 50, 30);
		contentPane.add(em_a);
		
		em_a_tf = new JTextField();
		em_a_tf.setBounds(110, 260, 250, 26);
		em_a_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(em_a_tf);
		em_a_tf.setColumns(10);
		em_a_tf.setText(member_info.m.getMember_email());
		
		JLabel em_a_h = new JLabel("");
		em_a_h.setHorizontalAlignment(SwingConstants.LEFT);
		em_a_h.setForeground(Color.RED);
		em_a_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		em_a_h.setBounds(120, 280, 100, 30);
		contentPane.add(em_a_h);
		
		JLabel acc_r_lbl = new JLabel("帳號");
		acc_r_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		acc_r_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		acc_r_lbl.setBounds(60, 320, 50, 30);
		contentPane.add(acc_r_lbl);
		
		acc_r_tf = new JTextField("會員帳號[8 - 12位]");
		acc_r_tf.setBounds(110, 320, 250, 26);
		acc_r_tf.setForeground(Color.GRAY);
		acc_r_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(acc_r_tf);
		acc_r_tf.setColumns(10);
		acc_r_tf.setText(member_info.member_acc);
		util_member.placeholder_tf(acc_r_tf, "會員帳號[8 - 12位]");
		
		JLabel acc_r_h = new JLabel("");
		acc_r_h.setHorizontalAlignment(SwingConstants.LEFT);
		acc_r_h.setForeground(Color.RED);
		acc_r_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		acc_r_h.setBounds(120, 340, 150, 30);
		contentPane.add(acc_r_h);
		
		JButton m_mi_btn = new JButton("確認修改");
		m_mi_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_mi_btn.setBounds(175, 370, 100, 35);
		contentPane.add(m_mi_btn);
		
		JLabel o_pwd_lbl = new JLabel("原密碼");
		o_pwd_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		o_pwd_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		o_pwd_lbl.setBounds(60, 430, 60, 30);
		contentPane.add(o_pwd_lbl);
		
		JLabel o_pwd_h = new JLabel("");
		o_pwd_h.setHorizontalAlignment(SwingConstants.LEFT);
		o_pwd_h.setForeground(Color.RED);
		o_pwd_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		o_pwd_h.setBounds(130, 450, 250, 30);
		contentPane.add(o_pwd_h);
		
		o_pwd_tf = new JPasswordField("請輸入原密碼");
		o_pwd_tf.setBounds(120, 430, 240, 26);
		o_pwd_tf.setEchoChar((char)0);
		o_pwd_tf.setForeground(Color.GRAY);
		o_pwd_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(o_pwd_tf);
		o_pwd_tf.setColumns(10);
		util_member.placeholder_pwd_o(o_pwd_tf, "請輸入原密碼");
		
		JLabel new_pwd_lbl = new JLabel("新密碼");
		new_pwd_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		new_pwd_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		new_pwd_lbl.setBounds(60, 490, 60, 30);
		contentPane.add(new_pwd_lbl);
		
		JLabel new_pwd_wp = new JLabel("");
		new_pwd_wp.setHorizontalAlignment(SwingConstants.CENTER);
		new_pwd_wp.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		new_pwd_wp.setBounds(350, 490, 50, 30);
		contentPane.add(new_pwd_wp);
		
		JLabel n_pwd_h = new JLabel("*至少須包含一個大寫字母、一個小寫字母和一個數字");
		n_pwd_h.setHorizontalAlignment(SwingConstants.LEFT);
		n_pwd_h.setForeground(Color.RED);
		n_pwd_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		n_pwd_h.setBounds(130, 510, 250, 30);
		contentPane.add(n_pwd_h);
		
		n_pwd_tf = new JPasswordField("會員密碼[8 - 12位]");
		n_pwd_tf.setBounds(120, 490, 240, 26);
		n_pwd_tf.setEchoChar((char)0);
		n_pwd_tf.setForeground(Color.GRAY);
		n_pwd_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(n_pwd_tf);
		n_pwd_tf.setColumns(10);
		util_member.placeholder_pwd(n_pwd_tf, "會員密碼[8 - 12位]", new_pwd_wp, n_pwd_h);
		
		JLabel cfm_n_pwd_lbl = new JLabel("確認新密碼");
		cfm_n_pwd_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		cfm_n_pwd_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		cfm_n_pwd_lbl.setBounds(60, 550, 85, 30);
		contentPane.add(cfm_n_pwd_lbl);
		
		JLabel cfm_n_pwd_wp = new JLabel("");
		cfm_n_pwd_wp.setHorizontalAlignment(SwingConstants.CENTER);
		cfm_n_pwd_wp.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		cfm_n_pwd_wp.setBounds(350, 550, 50, 30);
		contentPane.add(cfm_n_pwd_wp);
		
		JLabel cfm_n_pwd_h = new JLabel("");
		cfm_n_pwd_h.setHorizontalAlignment(SwingConstants.LEFT);
		cfm_n_pwd_h.setForeground(Color.RED);
		cfm_n_pwd_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		cfm_n_pwd_h.setBounds(150, 570, 250, 30);
		contentPane.add(cfm_n_pwd_h);
		
		cfm_n_pwd_tf = new JPasswordField("請再次輸入新密碼");
		cfm_n_pwd_tf.setBounds(140, 550, 220, 26);
		cfm_n_pwd_tf.setEchoChar((char)0);
		cfm_n_pwd_tf.setForeground(Color.GRAY);
		cfm_n_pwd_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(cfm_n_pwd_tf);
		cfm_n_pwd_tf.setColumns(10);
		util_member. placeholder_cfm_pwd(cfm_n_pwd_tf, n_pwd_tf, "請再次輸入新密碼", cfm_n_pwd_wp); 
		
		JButton pwd_m_btn = new JButton("修改密碼");
		pwd_m_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		pwd_m_btn.setBounds(175, 610, 100, 35);
		contentPane.add(pwd_m_btn);
		
		JButton bt_m_funcs = new JButton("回上層");
		bt_m_funcs.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		bt_m_funcs.setBounds(330, 20, 80, 35);
		contentPane.add(bt_m_funcs);
		
		/*
		private JTextField m_nn_tf;
	private JTextField pn_tf;
	private JTextField em_a_tf;
	private JTextField acc_r_tf;
	private JPasswordField o_pwd_tf;
	private JPasswordField n_pwd_tf;
	private JPasswordField cfm_n_pwd_tf;
		 */
		
		m_mi_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(util_member.field_checker_mi(m_nn_tf, m_nn_h, m_g_cb, m_g_h, m_bd_d, bd_h, pn_tf, pn_h, em_a_tf, em_a_h,
						acc_r_tf, acc_r_h)) {
					member_management m_u = new member_management(
												m_nn_tf.getText(),
												(String) m_g_cb.getSelectedItem(),
												(String) m_bd_y.getSelectedItem() + "-" + (String) m_bd_m.getSelectedItem() + "-" + (String) m_bd_d.getSelectedItem(),
												((String) cc_l.getSelectedItem()).split(" ")[1],
												pn_tf.getText(),
												em_a_tf.getText(),
												acc_r_tf.getText());
					if(new member_implement().update_member_mi(m_u, member_acc)) {
						new order_implement().update_order_creator(acc_r_tf.getText(), member_acc);
						JOptionPane.showMessageDialog(null, "修改成功");
					};
				} 
			}
		});
		
		bt_m_funcs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				member_funcs.setMember_acc(acc_r_tf.getText());
				member_funcs mf = new member_funcs();
				mf.setVisible(true);
				dispose();
			}
		});
		pwd_m_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(util_member.field_checker_mi_pwd(member_acc, o_pwd_tf, o_pwd_h, 
						n_pwd_tf, n_pwd_h, cfm_n_pwd_tf, cfm_n_pwd_h)) {
					
					if(new member_implement().reset_pwd(String.valueOf(n_pwd_tf.getPassword()), member_acc)) {
						JOptionPane.showMessageDialog(null, "修改成功");
					};
				};
				member_info.setMember_acc(acc_r_tf.getText());
				member_info.setM(new member_implement().read_member_mi(member_acc));
				member_info mi = new member_info();
				mi.setVisible(true);
				dispose();
			}
		});
	}

}
