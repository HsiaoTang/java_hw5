package Controller.member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import Controller.order.order_rec;
import Dao.member.member_implement;
import Model.member_management;
import Model.order_management;
import Util.util_item;
import Util.util_member;
import Util.util_order;
import Util.util_item.FolderFilter;

public class read_member extends JFrame {

	private JPanel contentPane;
	private JTable member_table;
	private JTextField m_a_tf;
	private JTextField age_1tf;
	private JTextField age_2tf;
	private JTextField m_id_tf;
	static JTextField re_d_tf;
	static JTextField rs_d_tf;
	private static String member_acc;
	private static List<member_management> m_l;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					read_member frame = new read_member();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void setMember_acc(String member_acc) {
		read_member.member_acc = member_acc;
	}
	
	public static String getMember_acc() {
		return read_member.member_acc;
	}

	/**
	 * Create the frame.
	 */
	public read_member() {
		Image img = new ImageIcon(member_lnr.class.getResource("/surfing_i.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setVisible(true);
		requestFocusInWindow(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 100, 590, 700);
		contentPane =new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		m_l = new member_implement().read_member();
		member_table = new JTable();
		member_table.setBounds(20, 210, 550, 440);
		member_table.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		member_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		member_table.setAutoCreateRowSorter(true);
		member_table.setDefaultEditor(Object.class, null);
		contentPane.add(member_table);
		util_member.ml_display(m_l, member_table);
		
		JScrollPane s_p = new JScrollPane(member_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		s_p.setBounds(20, 210, 550, 440);
		contentPane.add(s_p);
		
		JLabel filter_c = new JLabel("篩選條件");
		filter_c.setHorizontalAlignment(SwingConstants.CENTER);
		filter_c.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		filter_c.setBounds(10, 10, 100, 40);
		contentPane.add(filter_c);
		
		JLabel r_t = new JLabel("註冊日期");
		r_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		r_t.setHorizontalAlignment(SwingConstants.CENTER);
		r_t.setBounds(20, 55, 80, 25);
		contentPane.add(r_t);
		
		JLabel m_id_lbl = new JLabel("會員編號");
		m_id_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		m_id_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_id_lbl.setBounds(20, 100, 80, 25);
		contentPane.add(m_id_lbl);
		
		JLabel m_a_lbl = new JLabel("會員帳號");
		m_a_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		m_a_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_a_lbl.setBounds(190, 100, 80, 25);
		contentPane.add(m_a_lbl);
		
		JLabel g_lbl = new JLabel("性別");
		g_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		g_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		g_lbl.setBounds(360, 100, 80, 25);
		contentPane.add(g_lbl);
		
		JLabel age_lbl = new JLabel("年齡");
		age_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		age_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		age_lbl.setBounds(35, 145, 50, 25);
		contentPane.add(age_lbl);
		
		JLabel age_from = new JLabel("從");
		age_from.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		age_from.setHorizontalAlignment(SwingConstants.CENTER);
		age_from.setBounds(55, 145, 50, 25);
		contentPane.add(age_from);
		
		JLabel age_1 = new JLabel("歲");
		age_1.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		age_1.setHorizontalAlignment(SwingConstants.CENTER);
		age_1.setBounds(180, 145, 50, 25);
		contentPane.add(age_1);
		
		JLabel age_to = new JLabel("到");
		age_to.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		age_to.setHorizontalAlignment(SwingConstants.CENTER);
		age_to.setBounds(200, 145, 50, 25);
		contentPane.add(age_to);
		
		JLabel age_2 = new JLabel("歲");
		age_2.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		age_2.setHorizontalAlignment(SwingConstants.CENTER);
		age_2.setBounds(325, 145, 50, 25);
		contentPane.add(age_2);
		
		JLabel c_lbl = new JLabel("國籍");
		c_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		c_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		c_lbl.setBounds(360, 145, 80, 25);
		contentPane.add(c_lbl);
		
		rs_d_tf = new JTextField();
		rs_d_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		rs_d_tf.setBounds(200, 55, 110, 26);
		rs_d_tf.setEditable(false);
		contentPane.add(rs_d_tf);
		rs_d_tf.setColumns(10);
		
		JButton rs_d_btn = new JButton("選擇起始日");
		rs_d_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		rs_d_btn.setBounds(90, 55, 110, 30);
		contentPane.add(rs_d_btn);
		
		re_d_tf = new JTextField();
		re_d_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		re_d_tf.setBounds(420, 55, 110, 26);
		re_d_tf.setEditable(false);
		contentPane.add(re_d_tf);
		re_d_tf.setColumns(10);
		
		JButton re_d_btn = new JButton("選擇結束日");
		re_d_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		re_d_btn.setBounds(310, 55, 110, 30);
		contentPane.add(re_d_btn);
		
		m_id_tf = new JTextField();
		m_id_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_id_tf.setBounds(95, 100, 100, 26);
		contentPane.add(m_id_tf);
		m_id_tf.setColumns(10);
		
		m_a_tf = new JTextField();
		m_a_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_a_tf.setBounds(265, 100, 100, 26);
		contentPane.add(m_a_tf);
		m_a_tf.setColumns(10);
		
		String[] g_arr = new String[] {"", "男", "女"};
		JComboBox g_cb = new JComboBox(g_arr);
		g_cb.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		g_cb.setBounds(430, 100, 105, 26);
		g_cb.setSelectedIndex(0);
		contentPane.add(g_cb);
		
		age_1tf = new JTextField("歲數下限");
		age_1tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		age_1tf.setForeground(Color.GRAY);
		age_1tf.setBounds(95, 145, 100, 26);
		contentPane.add(age_1tf);
		age_1tf.setColumns(10);
		util_member.placeholder_tf(age_1tf, "歲數下限");
		
		age_2tf = new JTextField("歲數上限");
		age_2tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		age_2tf.setForeground(Color.GRAY);
		age_2tf.setBounds(240, 145, 100, 26);
		contentPane.add(age_2tf);
		age_2tf.setColumns(10);
		util_member.placeholder_tf(age_2tf, "歲數上限");
		
		List<String> c_list = new member_implement().get_country_list();
		String[] c_arr = new String[c_list.size()];
		c_list.toArray(c_arr);
		JComboBox c_cb = new JComboBox(c_arr);
		c_cb.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		c_cb.setBounds(430, 145, 105, 26);
		c_cb.setSelectedIndex(0);
		contentPane.add(c_cb);
		
		JButton s_btn = new JButton("查詢");
		s_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		s_btn.setBounds(470, 175, 100, 30);
		contentPane.add(s_btn);
		
		JButton gc_btn = new JButton("性別佔比");
		gc_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		gc_btn.setBounds(320, 175, 100, 30);
		contentPane.add(gc_btn);
		
		JButton cc_btn = new JButton("國籍佔比");
		cc_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		cc_btn.setBounds(170, 175, 100, 30);
		contentPane.add(cc_btn);
		
		JButton e_btn = new JButton("匯出Excel");
		e_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		e_btn.setBounds(20, 175, 100, 30);
		contentPane.add(e_btn);
		
		//起始日
		rs_d_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendar_display_sd_admin_rm c_d = new calendar_display_sd_admin_rm();
				c_d.setVisible(true);
			}
		});
		
		//結束日
		re_d_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendar_display_ed_admin_rm c_d = new calendar_display_ed_admin_rm();
				c_d.setVisible(true);
			}
		});
		
		//查詢
		s_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				util_member.field_check_rms(rs_d_tf, re_d_tf, m_id_tf, age_1tf, age_2tf);
				m_l = util_member.read_member_swf(rs_d_tf, re_d_tf, m_id_tf, m_a_tf, g_cb, age_1tf, age_2tf, c_cb);
				util_member.ml_display(m_l, member_table);
				member_table.repaint();
			}
		});
		
		//匯出Excel
		e_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser("");
				FileFilter dir_ff = new util_item().new FolderFilter();
				file.addChoosableFileFilter(dir_ff);
				file.setAcceptAllFileFilterUsed(false);
				file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = file.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					String path = file.getSelectedFile().getPath();
					util_order.gen_excel(member_table, path + "/會員列表" + new SimpleDateFormat("yyyyMMddddHHmmss").format(new Date()) + ".xlsx");
				}
			}
		});
		gc_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member_chart.c = util_member.create_chart_g(m_l);
				member_chart mc = new member_chart();
			}
		});
		
		cc_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member_chart.c = util_member.create_chart_c(m_l);
				member_chart mc = new member_chart();
			}
		});
	}
}
