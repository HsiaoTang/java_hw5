package Controller.member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Dao.member.member_implement;
import Util.util_member;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class member_retrieve_pwd extends JFrame {

	private JPanel contentPane;
	private JTextField m_acc_tf;
	private JTextField m_pn_tf;
	private JTextField m_em_a_tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					member_retrieve_pwd frame = new member_retrieve_pwd();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public member_retrieve_pwd() {
		Image img = new ImageIcon(member_lnr.class.getResource("/surfing_i.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setVisible(true);
		requestFocusInWindow(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(125, 300, 400, 250);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel m_r_pwd = new JLabel("請輸入以下資訊");
		m_r_pwd.setHorizontalAlignment(SwingConstants.CENTER);
		m_r_pwd.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_r_pwd.setBounds(50, 15, 300, 20);
		contentPane.add(m_r_pwd);
		
		JLabel m_acc = new JLabel("帳號");
		m_acc.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_acc.setBounds(50, 50, 61, 20);
		contentPane.add(m_acc);
		
		JLabel m_acc_h = new JLabel("");
		m_acc_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		m_acc_h.setForeground(Color.RED);
		m_acc_h.setBounds(325, 50, 61, 20);
		contentPane.add(m_acc_h);
		
		JLabel m_pn = new JLabel("行動電話");
		m_pn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_pn.setBounds(50, 95, 61, 20);
		contentPane.add(m_pn);
		
		JLabel m_pn_h = new JLabel("");
		m_pn_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		m_pn_h.setForeground(Color.RED);
		m_pn_h.setBounds(325, 95, 61, 20);
		contentPane.add(m_pn_h);
		
		JLabel m_em_a = new JLabel("電子郵件");
		m_em_a.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_em_a.setBounds(50, 140, 61, 20);
		contentPane.add(m_em_a);
		
		JLabel m_em_a_h = new JLabel("");
		m_em_a_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		m_em_a_h.setForeground(Color.RED);
		m_em_a_h.setBounds(325, 140, 61, 20);
		contentPane.add(m_em_a_h);
		
		m_acc_tf = new JTextField();
		m_acc_tf.setBounds(120, 48, 200, 26);
		m_acc_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(m_acc_tf);
		m_acc_tf.setColumns(10);
		
		m_pn_tf = new JTextField();
		m_pn_tf.setBounds(120, 93, 200, 26);
		m_pn_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(m_pn_tf);
		m_pn_tf.setColumns(10);
		
		m_em_a_tf = new JTextField();
		m_em_a_tf.setBounds(120, 138, 200, 26);
		m_em_a_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(m_em_a_tf);
		m_em_a_tf.setColumns(10);
		
		JButton retri_new_pwd = new JButton("取得新密碼");
		retri_new_pwd.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		retri_new_pwd.setBounds(150, 175, 100, 35);
		contentPane.add(retri_new_pwd);
		
		//取得新密碼
		retri_new_pwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member_implement m_sql_funcs = new member_implement();
				if(!m_sql_funcs.read_member_ie(m_acc_tf.getText())) {
					m_acc_h.setText("*無此帳號");
				} else {
					m_acc_h.setText("");
				}
				if(!m_sql_funcs.read_member_pn(m_acc_tf.getText()).equals(m_pn_tf.getText())) {
					m_pn_h.setText("*輸入錯誤");
				} else {
					m_pn_h.setText("");
				}
				if(!m_sql_funcs.read_member_em_a(m_acc_tf.getText()).equals(m_em_a_tf.getText())) {
					m_em_a_h.setText("*輸入錯誤");
				} else {
					m_em_a_h.setText("");
				}
				if(m_acc_h.getText().equals("") || m_pn_h.getText().equals("") ||
						m_em_a_h.getText().equals("")) {
					String pwd = util_member.gen_pwd();
					String pattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]{8,}";
					while(!Pattern.matches(pattern, pwd)) {
						pwd = util_member.gen_pwd();
					}
					m_sql_funcs.reset_pwd(pwd, m_acc_tf.getText());
					JOptionPane.showMessageDialog(null, "你的新密碼為 " + pwd);
					dispose();
				} else {
					return;
				}
				
			}
		});
	}
}
