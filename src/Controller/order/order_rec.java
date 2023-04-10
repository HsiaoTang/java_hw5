package Controller.order;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import Controller.member.calendar_display_ed_admin;
import Controller.member.calendar_display_sd_admin;
import Controller.member.member_chart;
import Controller.member.member_lnr;
import Dao.member.member_implement;
import Dao.order.order_implement;
import Model.order_management;
import Util.util_item;
import Util.util_member;
import Util.util_order;
import Util.util_item.FolderFilter;

public class order_rec extends JFrame {

	private JPanel contentPane;
	private static String member_acc;
	private JTable order_table;
	public static JTextField s_d_tf;
	public static JTextField e_d_tf;
	private static List<order_management> o_l;
	private JTextField i_c_tf;
	private JTextField m_a_tf;
	private JTextField s_m;
	private JTextField t_m;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					order_rec frame = new order_rec();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void setMember_acc(String member_acc) {
		order_rec.member_acc = member_acc;
	}
	
	public static String getMember_acc() {
		return order_rec.member_acc;
	}

	/**
	 * Create the frame.
	 */
	public order_rec() {
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
		
		order_table = new JTable();
		order_table.setBounds(20, 210, 550, 440);
		order_table.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		order_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		order_table.setAutoCreateRowSorter(true);
		order_table.setDefaultEditor(Object.class, null);
		contentPane.add(order_table);
		o_l = new order_implement().read_order_list_admin();
		util_order.ol_display_admin(order_table, o_l);
		
		JScrollPane s_p = new JScrollPane(order_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		s_p.setBounds(20, 210, 550, 440);
		contentPane.add(s_p);
		
		JLabel filter_c = new JLabel("篩選條件");
		filter_c.setHorizontalAlignment(SwingConstants.CENTER);
		filter_c.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		filter_c.setBounds(10, 10, 100, 40);
		contentPane.add(filter_c);
		
		JLabel pd_t = new JLabel("購買日期");
		pd_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		pd_t.setHorizontalAlignment(SwingConstants.CENTER);
		pd_t.setBounds(20, 55, 80, 25);
		contentPane.add(pd_t);
		
		JLabel m_a_lbl = new JLabel("會員帳號");
		m_a_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		m_a_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_a_lbl.setBounds(20, 100, 80, 25);
		contentPane.add(m_a_lbl);
		
		JLabel ii_t = new JLabel("包含商品");
		ii_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		ii_t.setHorizontalAlignment(SwingConstants.CENTER);
		ii_t.setBounds(245, 100, 80, 25);
		contentPane.add(ii_t);
		
		JLabel ms_t = new JLabel("消費金額");
		ms_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		ms_t.setHorizontalAlignment(SwingConstants.CENTER);
		ms_t.setBounds(20, 145, 80, 25);
		contentPane.add(ms_t);
		
		s_d_tf = new JTextField();
		s_d_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		s_d_tf.setBounds(200, 55, 110, 26);
		s_d_tf.setEditable(false);
		contentPane.add(s_d_tf);
		s_d_tf.setColumns(10);
		
		JButton s_d_btn = new JButton("選擇起始日");
		s_d_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		s_d_btn.setBounds(90, 55, 110, 30);
		contentPane.add(s_d_btn);
		
		e_d_tf = new JTextField();
		e_d_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		e_d_tf.setBounds(420, 55, 110, 26);
		e_d_tf.setEditable(false);
		contentPane.add(e_d_tf);
		e_d_tf.setColumns(10);
		
		JButton e_d_btn = new JButton("選擇結束日");
		e_d_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		e_d_btn.setBounds(310, 55, 110, 30);
		contentPane.add(e_d_btn);
		
		m_a_tf = new JTextField();
		m_a_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_a_tf.setBounds(95, 100, 155, 26);
		contentPane.add(m_a_tf);
		m_a_tf.setColumns(10);
		
		i_c_tf = new JTextField();
		i_c_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		i_c_tf.setBounds(320, 100, 210, 26);
		contentPane.add(i_c_tf);
		i_c_tf.setColumns(10);
		
		JLabel i_c_h = new JLabel("*請以逗號分隔不同商品");
		i_c_h.setForeground(Color.RED);
		i_c_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		i_c_h.setBounds(330, 125, 130, 20);
		contentPane.add(i_c_h);
		
		s_m = new JTextField("金額下限");
		s_m.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		s_m.setBounds(120, 145, 190, 26);
		s_m.setForeground(Color.GRAY);
		contentPane.add(s_m);
		s_m.setColumns(10);
		util_member.placeholder_tf(s_m, "金額下限");
		
		JLabel from_lbl = new JLabel("從");
		from_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		from_lbl.setBounds(100, 145, 30, 25);
		contentPane.add(from_lbl);
		
		t_m = new JTextField("金額上限");
		t_m.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		t_m.setBounds(340, 145, 190, 26);
		t_m.setForeground(Color.GRAY);
		contentPane.add(t_m);
		t_m.setColumns(10);
		util_member.placeholder_tf(t_m, "金額上限");
		
		JLabel to_lbl = new JLabel("至");
		to_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		to_lbl.setBounds(320, 145, 30, 25);
		contentPane.add(to_lbl);
		
		JButton s_btn = new JButton("查詢");
		s_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		s_btn.setBounds(470, 175, 100, 30);
		contentPane.add(s_btn);
		
		JButton gc_btn = new JButton("商品銷售金額");
		gc_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		gc_btn.setBounds(310, 175, 120, 30);
		contentPane.add(gc_btn);
		
		JButton cc_btn = new JButton("會員消費金額");
		cc_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		cc_btn.setBounds(160, 175, 120, 30);
		contentPane.add(cc_btn);
		
		JButton e_btn = new JButton("匯出Excel");
		e_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		e_btn.setBounds(20, 175, 100, 30);
		contentPane.add(e_btn);
		
		//起始日
		s_d_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendar_display_sd_admin c_d = new calendar_display_sd_admin();
				c_d.setVisible(true);
			}
		});
		
		//結束日
		e_d_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendar_display_ed_admin c_d = new calendar_display_ed_admin();
				c_d.setVisible(true);
			}
		});
		
		//查詢
		s_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				util_order.field_checker(s_d_tf, e_d_tf, s_m, t_m);
				o_l = util_order.read_order_mwf(s_d_tf, e_d_tf, i_c_tf, s_m, t_m, m_a_tf.getText());
				util_order.ol_display_admin(order_table, o_l);
				order_table.repaint();
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
					util_order.gen_excel(order_table, path + "/訂單列表" + new SimpleDateFormat("yyyyMMddddHHmmss").format(new Date()) + ".xlsx");
				}
			}
		});
		
		gc_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order_chart.c = util_order.create_chart_i(o_l);
				order_chart oc = new order_chart();
			}
		});
		
		cc_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order_chart.c = util_order.create_chart_m(o_l);
				order_chart oc = new order_chart();
			}
		});
	}

}
