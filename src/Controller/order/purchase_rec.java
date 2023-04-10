package Controller.order;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.member.calendar_display_ed;
import Controller.member.calendar_display_sd;
import Controller.member.member_lnr;
import Dao.order.order_implement;
import Model.order_management;
import Util.util_member;
import Util.util_order;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class purchase_rec extends JFrame {
	
	private static String member_acc;
	private JPanel contentPane;
	private JTable order_table;
	public static JTextField s_d_tf;
	public static JTextField e_d_tf;
	private static List<order_management> o_l;
	private JTextField i_c_tf;
	private JTextField s_m;
	private JTextField t_m;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					purchase_rec frame = new purchase_rec();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void setMember_acc(String member_acc) {
		purchase_rec.member_acc = member_acc;
	}
	
	public static String getMember_acc() {
		return purchase_rec.member_acc;
	}
	/**
	 * Create the frame.
	 */
	public purchase_rec() {
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
		
		o_l = new order_implement().read_order_list_member(member_acc);
		order_table = new JTable();
		order_table.setBounds(20, 210, 550, 440);
		order_table.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		order_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		order_table.setAutoCreateRowSorter(true);
		order_table.setDefaultEditor(Object.class, null);
		contentPane.add(order_table);
		util_order.ol_display(order_table, o_l);
		
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
		
		JLabel ii_t = new JLabel("包含商品");
		ii_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		ii_t.setHorizontalAlignment(SwingConstants.CENTER);
		ii_t.setBounds(20, 100, 80, 25);
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
		
		i_c_tf = new JTextField();
		i_c_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		i_c_tf.setBounds(95, 100, 435, 26);
		contentPane.add(i_c_tf);
		i_c_tf.setColumns(10);
		
		JLabel i_c_h = new JLabel("*請以逗號分隔不同商品");
		i_c_h.setForeground(Color.RED);
		i_c_h.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		i_c_h.setBounds(105, 125, 130, 20);
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
		
		//起始日
		s_d_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendar_display_sd c_d = new calendar_display_sd();
				c_d.setVisible(true);
			}
		});
		
		//結束日
		e_d_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendar_display_ed c_d = new calendar_display_ed();
				c_d.setVisible(true);
			}
		});
		
		//查詢
		s_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				util_order.field_checker(s_d_tf, e_d_tf, s_m, t_m);
				List<order_management> ol = util_order.read_order_mwf(s_d_tf, e_d_tf, i_c_tf, s_m, t_m, member_acc);
				util_order.ol_display(order_table, ol);
				order_table.repaint();
			}
		});
	}
}
