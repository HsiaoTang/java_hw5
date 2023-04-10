package Controller.order;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import Controller.item.create_item;
import Controller.member.admin_funcs;
import Controller.member.member_funcs;
import Controller.member.member_lnr;
import Dao.item.item_implement;
import Dao.order.order_implement;
import Model.item_management;
import Model.order_management;
import Util.util_item;
import Util.util_order;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class create_order extends JFrame {
	
	private static String member_acc;
	private JPanel contentPane;
	private JTextField pg_num;
	private static JLayeredPane layeredPane;
	private JTextField m_p_tf;
	private JTextField c_tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					create_order frame = new create_order();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static void setMember_acc(String member_acc) {
		create_order.member_acc = member_acc;
	}
	
	public static String getMember_acc() {
		return create_order.member_acc;
	}
	
	public static void switch_screen(JPanel p) {
		layeredPane.removeAll();
		layeredPane.add(p);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public create_order() {
		Image img = new ImageIcon(member_lnr.class.getResource("/surfing_i.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setVisible(true);
		requestFocusInWindow(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 60, 450, 750);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 450, 720);
		contentPane.add(layeredPane);
		
		List<item_management> item_list = new item_implement().read_item_display();
		List<JPanel> od_pgs = new ArrayList<JPanel>();
		List<JLabel> pics = new ArrayList<JLabel>();
		List<JLabel> p_titles = new ArrayList<JLabel>();
		List<JLabel> p_tags = new ArrayList<JLabel>();
		List<JTextField> qs = new ArrayList<JTextField>();
		List<JButton> q_i = new ArrayList<JButton>();
		List<JButton> q_d = new ArrayList<JButton>();
		if(item_list.size() % 2 == 1) {
			for(int pg_count = 0; pg_count < item_list.size() / 2 + 1; pg_count++) {
				JPanel od_pg = new JPanel();
				od_pg.setBackground(Color.ORANGE);
				od_pg.setBounds(0, 100, 450, 590);
				layeredPane.add(od_pg);
				od_pg.setLayout(null);
				od_pgs.add(od_pg);
			}
		} else {
			for(int pg_count = 0; pg_count < item_list.size() / 2; pg_count++) {
				JPanel od_pg = new JPanel();
				od_pg.setBackground(Color.ORANGE);
				od_pg.setBounds(0, 100, 450, 620);
				layeredPane.add(od_pg);
				od_pg.setLayout(null);
				od_pgs.add(od_pg);
			}
		}
		
		JPanel check_pg = new JPanel();
		check_pg.setBackground(Color.ORANGE);
		check_pg.setBounds(0, 75, 450, 650);
		layeredPane.add(check_pg);
		od_pgs.add(check_pg);
		check_pg.setLayout(null);
		
		JLabel check_pg_t = new JLabel("訂單明細");
		check_pg_t.setHorizontalAlignment(SwingConstants.CENTER);
		check_pg_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		check_pg_t.setBounds(185, 10, 80, 30);
		check_pg.add(check_pg_t);
		
		JTextPane od_dtl_txt = new JTextPane();
		od_dtl_txt.setBounds(20, 50, 410, 300);
		od_dtl_txt.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		check_pg.add(od_dtl_txt);
		util_order.centering_od_txt(od_dtl_txt);
		
		JScrollPane od_dtl_scrl = new JScrollPane(od_dtl_txt);
		od_dtl_scrl.setBounds(20, 50, 410, 300);
		check_pg.add(od_dtl_scrl);
		
		JLabel m_p_t = new JLabel("支付");
		m_p_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_p_t.setHorizontalAlignment(SwingConstants.CENTER);
		m_p_t.setBounds(20, 480, 61, 35);
		check_pg.add(m_p_t);
		
		m_p_tf = new JTextField();
		m_p_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		m_p_tf.setBounds(90, 480, 130, 35);
		check_pg.add(m_p_tf);
		m_p_tf.setColumns(10);
		
		JLabel c_t = new JLabel("找零");
		c_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		c_t.setHorizontalAlignment(SwingConstants.CENTER);
		c_t.setBounds(230, 480, 61, 35);
		check_pg.add(c_t);
		
		c_tf = new JTextField();
		c_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		c_tf.setBounds(300, 480, 130, 35);
		check_pg.add(c_tf);
		c_tf.setColumns(10);
		
		JButton b_o_b = new JButton("建立訂單");
		b_o_b.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		b_o_b.setBounds(20, 600, 120, 35);
		check_pg.add(b_o_b);
		if(member_acc.equals("admin123")) {
			b_o_b.setEnabled(false);
		}
		
		JButton e_b = new JButton("離開");
		e_b.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		e_b.setBounds(310, 600, 120, 35);
		check_pg.add(e_b);
		
		
			
		for(int d_s = 0; d_s < item_list.size(); d_s++) {
			if(d_s % 2 == 0) {
				
				JLabel pic_1 = new JLabel("New label");
				pic_1.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
				ImageIcon ii = new ImageIcon(item_list.get(d_s).getItem_pic_path());
				pic_1.setBounds(20, 0, ii.getIconWidth(), ii.getIconWidth());
				pic_1.setIcon(new ImageIcon(ii.getImage()));
				od_pgs.get(d_s / 2).add(pic_1);
				pics.add(pic_1);
				
				JLabel p_title_1 = new JLabel("New label");
				p_title_1.setHorizontalAlignment(SwingConstants.CENTER);
				p_title_1.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
				p_title_1.setBounds(60, 260, 100, 30);
				p_title_1.setText(item_list.get(d_s).getItem_name());
				od_pgs.get(d_s / 2).add(p_title_1);
				p_titles.add(p_title_1);
				
				JLabel p_tag_1 = new JLabel("New label");
				p_tag_1.setHorizontalAlignment(SwingConstants.CENTER);
				p_tag_1.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
				p_tag_1.setBounds(180, 260, 100, 30);
				p_tag_1.setText(item_list.get(d_s).getItem_price() + " 元");
				od_pgs.get(d_s / 2).add(p_tag_1);
				p_tags.add(p_tag_1);
				
				JTextField q_1 = new JTextField("0");
				q_1.setHorizontalAlignment(SwingConstants.CENTER);
				q_1.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
				q_1.setBounds(355, 110, 50, 30);
				od_pgs.get(d_s / 2).add(q_1);
				q_1.setColumns(10);
				qs.add(q_1);
		
				JButton q_1_i = new JButton("+");
				q_1_i.setBounds(350, 40, 60, 40);
				q_1_i.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
				od_pgs.get(d_s / 2).add(q_1_i);
				q_i.add(q_1_i);
				
				JButton q_1_d = new JButton("-");
				q_1_d.setBounds(350, 165, 60, 40);
				q_1_d.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
				od_pgs.get(d_s / 2).add(q_1_d);
				q_d.add(q_1_d);
				
				//數量加1
				q_1_i.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(!util_item.p_checker(q_1)) {
							JOptionPane.showMessageDialog(null, "請填入合理數量");
							return;
						}
						Integer new_q_1 = Integer.parseInt(q_1.getText()) + 1;
						q_1.setText(new_q_1 + "");
					}
				});
				
				//數量減1
				q_1_d.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(!util_item.p_checker(q_1)) {
							JOptionPane.showMessageDialog(null, "請填入合理數量");
							return;
						}
						Integer new_q_1 = Integer.parseInt(q_1.getText()) - 1;
						if(Integer.parseInt(q_1.getText()) != 0) {
							q_1.setText(new_q_1 + "");
						} else {
							q_1.setText("0");
						}
						
					}
				});
				
			} else if(d_s % 2 == 1) {
				JLabel pic_2 = new JLabel("New label");
				pic_2.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
				ImageIcon ii = new ImageIcon(item_list.get(d_s).getItem_pic_path());
				pic_2.setBounds(20, 300, ii.getIconWidth(), ii.getIconHeight());
				pic_2.setIcon(new ImageIcon(ii.getImage()));
				od_pgs.get(d_s / 2).add(pic_2);
				pics.add(pic_2);
				
				JLabel p_title_2 = new JLabel("New label");
				p_title_2.setHorizontalAlignment(SwingConstants.CENTER);
				p_title_2.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
				p_title_2.setBounds(60, 570, 100, 30);
				p_title_2.setText(item_list.get(d_s).getItem_name());
				od_pgs.get(d_s / 2).add(p_title_2);
				p_titles.add(p_title_2);
				
				JLabel p_tag_2 = new JLabel("New label");
				p_tag_2.setHorizontalAlignment(SwingConstants.CENTER);
				p_tag_2.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
				p_tag_2.setBounds(180, 570, 100, 30);
				p_tag_2.setText(item_list.get(d_s).getItem_price() + " 元");
				od_pgs.get(d_s / 2).add(p_tag_2);
				p_tags.add(p_tag_2);
				
				JTextField q_2 = new JTextField("0");
				q_2.setHorizontalAlignment(SwingConstants.CENTER);
				q_2.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
				q_2.setBounds(355, 410, 50, 30);
				od_pgs.get(d_s / 2).add(q_2);
				q_2.setColumns(10);
				qs.add(q_2);
				
				
				JButton q_2_i = new JButton("+");
				q_2_i.setBounds(350, 340, 60, 40);
				q_2_i.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
				od_pgs.get(d_s / 2).add(q_2_i);
				q_i.add(q_2_i);
				
				JButton q_2_d = new JButton("-");
				q_2_d.setBounds(350, 465, 60, 40);
				q_2_d.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
				od_pgs.get(d_s / 2).add(q_2_d);
				q_d.add(q_2_d);
				
				//數量加1
				q_2_i.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(!util_item.p_checker(q_2)) {
							JOptionPane.showMessageDialog(null, "請填入合理數量");
							return;
						}
						Integer new_q_2 = Integer.parseInt(q_2.getText()) + 1;
						q_2.setText(new_q_2 + "");
					}
				});
				
				//數量減1
				q_2_d.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(!util_item.p_checker(q_2)) {
							JOptionPane.showMessageDialog(null, "請填入合理數量");
							return;
						}
						Integer new_q_2 = Integer.parseInt(q_2.getText()) - 1;
						if(Integer.parseInt(q_2.getText()) != 0) {
							q_2.setText(new_q_2 + "");
						} else {
							q_2.setText("0");
						}
					}	
				});
			}
		}
		
		switch_screen(od_pgs.get(0));
		
		JPanel control = new JPanel();
		control.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		control.setBackground(new Color(255, 165, 0));
		control.setBounds(5, 5, 440, 70);
		contentPane.add(control);
		control.setLayout(null);
		
		JButton last_pg = new JButton("上一頁");
		last_pg.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		last_pg.setBounds(6, 10, 80, 50);
		last_pg.setEnabled(false);
		control.add(last_pg);
		
		JButton next_pg = new JButton("下一頁");
		next_pg.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		next_pg.setBounds(270, 10, 80, 50);
		if(od_pgs.size() == 2) {
			next_pg.setEnabled(false);
		}
		control.add(next_pg);
		
		pg_num = new JTextField("1");
		pg_num.setHorizontalAlignment(SwingConstants.CENTER);
		pg_num.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		pg_num.setBounds(133, 20, 40, 30);
		control.add(pg_num);
		pg_num.setColumns(10);
		
		JLabel go_to_txts = new JLabel("移至第                頁");
		go_to_txts.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		go_to_txts.setBounds(90, 25, 120, 20);
		control.add(go_to_txts);
		
		JButton go_to_cfm = new JButton("確定");
		go_to_cfm.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		go_to_cfm.setBounds(200, 20, 60, 29);
		control.add(go_to_cfm);
		
		JButton func_select = new JButton("回上層");
		func_select.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		func_select.setBounds(350, 8, 80, 25);
		control.add(func_select);
		
		JButton go_to_check = new JButton("結帳");
		go_to_check.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		go_to_check.setBounds(350, 36, 80, 25);
		control.add(go_to_check);
		
		//前往頁數
		go_to_cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!util_order.isNumeric(pg_num.getText())) {
					JOptionPane.showMessageDialog(null, "請填入合理頁數");
					return;
				}
				Integer cur_pg = Integer.parseInt(pg_num.getText());
				switch_screen(od_pgs.get(cur_pg - 1));
				if(Integer.parseInt(pg_num.getText()) == 1) {
					last_pg.setEnabled(false);
				} else if(Integer.parseInt(pg_num.getText()) != 1){
					last_pg.setEnabled(true);
				}
				if(Integer.parseInt(pg_num.getText()) != od_pgs.size() - 1) {
					next_pg.setEnabled(true);
				} else if(Integer.parseInt(pg_num.getText()) == od_pgs.size() - 1) {
					next_pg.setEnabled(false);
				}
				
			}
		});
		
		//上一頁
		last_pg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(pg_num.getText().equals("-")) {
					switch_screen(od_pgs.get(od_pgs.size() - 2));
					pg_num.setText(od_pgs.size() - 1 + "");

					if(Integer.parseInt(pg_num.getText()) == 1) {
						last_pg.setEnabled(false);
					}
					return;
				}
				if(!util_order.isNumeric(pg_num.getText())) {
					JOptionPane.showMessageDialog(null, "請填入合理頁數");
					return;
				}
				Integer cur_pg = Integer.parseInt(pg_num.getText());
				switch_screen(od_pgs.get(cur_pg - 2));
				pg_num.setText(cur_pg - 1 + "");
				if(Integer.parseInt(pg_num.getText()) == 1) {
					last_pg.setEnabled(false);
				} else {
					last_pg.setEnabled(true);
				}
				if(Integer.parseInt(pg_num.getText()) != od_pgs.size() - 1) {
					next_pg.setEnabled(true);
				} else {
					next_pg.setEnabled(false);
				}
			}
		});
		
		//下一頁
		next_pg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!util_order.isNumeric(pg_num.getText())) {
					JOptionPane.showMessageDialog(null, "請填入合理頁數");
					return;
				}
				Integer cur_pg = Integer.parseInt(pg_num.getText());
				switch_screen(od_pgs.get(cur_pg));
				pg_num.setText(cur_pg + 1 + "");
				if(Integer.parseInt(pg_num.getText()) > 1) {
					last_pg.setEnabled(true);
				}
				if(Integer.parseInt(pg_num.getText()) == od_pgs.size() - 1) {
					next_pg.setEnabled(false);
				}
			}
		});
		/*
		private Integer order_id;
		private String order_create_time;
		private String order_creator;
		private List<item_management> item_list;
		private List<Integer> quantity_list;
		
		List<item_management> item_list = new item_implement().read_item_display();
		List<JPanel> od_pgs = new ArrayList<JPanel>();
		List<JLabel> pics = new ArrayList<JLabel>();
		List<JLabel> p_titles = new ArrayList<JLabel>();
		List<JLabel> p_tags = new ArrayList<JLabel>();
		List<JTextField> qs = new ArrayList<JTextField>();
		
		 */
		
		//結帳
		go_to_check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				od_dtl_txt.setText("");
				pg_num.setText("-");
				switch_screen(od_pgs.get(od_pgs.size() - 1));
				String od_dtl = util_order.od_display(member_acc, qs, p_titles, p_tags);
				od_dtl_txt.setText(od_dtl);
				next_pg.setEnabled(false);
				last_pg.setEnabled(true);
			}
		});
		
		//order_management n_od= util_order.build_od_object(od_dtl_txt, qs, p_titles, p_tags);
		//權限
		func_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(create_order.member_acc.equals("admin123")) {
					create_item.setMember_acc(member_acc);
					create_item ci = new create_item();
					ci.setVisible(true);
					dispose();
				} else {
					member_funcs.setMember_acc(member_acc);
					member_funcs.setMember_acc(member_acc);
					member_funcs mf = new member_funcs();
					mf.setVisible(true);
					dispose();
				}
			}
		});
		
		//找零
		util_order.cal_change(m_p_tf, od_dtl_txt, c_tf);
		
		//離開
		e_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		
		//建立訂單
		b_o_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] s_a = od_dtl_txt.getText().split("：");
				String t_y = s_a[s_a.length - 1];
				int t = Integer.parseInt(t_y.substring(0, t_y.length() - 1));
				if(t == 0) {
					JOptionPane.showMessageDialog(null, "未選購商品");
					return;
				}
				if(!util_order.isNumeric(m_p_tf.getText())) {
					JOptionPane.showMessageDialog(null, "請輸入合理金額");
					return;
				}
				if(c_tf.getText().equals("-")){
					JOptionPane.showMessageDialog(null, "金額不足");
					return;
				}
				order_management o = util_order.build_od_object(member_acc, m_p_tf, qs, p_titles, p_tags);
				new order_implement().create_order(o);
				build_order_success.setMember_acc(member_acc);
				build_order_success.setO(new order_implement().read_last_order_member(member_acc));
				build_order_success bos = new build_order_success();
				dispose();
			}
		});
	}
}
