package Controller.member;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

import Controller.order.create_order;
import Controller.order.purchase_rec;
import Dao.item.item_implement;
import Dao.member.member_implement;
import Dao.order.order_implement;

public class member_funcs extends JFrame {

	private JPanel contentPane;
	private static String member_acc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					member_funcs frame = new member_funcs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void setMember_acc(String member_acc) {
		member_funcs.member_acc = member_acc;
	}
	
	public static String getMember_acc() {
		return member_funcs.member_acc;
	}
	
	/**
	 * Create the frame.
	 */
	public member_funcs() {
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
		
		JLabel m_nn_lbl = new JLabel(new member_implement().read_member_nn(member_funcs.member_acc));
		m_nn_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		m_nn_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		m_nn_lbl.setBounds(180, 80, 150, 35);
		contentPane.add(m_nn_lbl);
		
		JLabel hi_lbl = new JLabel("嗨！");
		hi_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		hi_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		hi_lbl.setBounds(120, 80, 55, 35);
		contentPane.add(hi_lbl);
		
		JLabel wl_lbl = new JLabel("你想要...");
		wl_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		wl_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		wl_lbl.setBounds(150, 125, 150, 35);
		contentPane.add(wl_lbl);
		
		JPanel info_m = new JPanel();
		info_m.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		info_m.setBackground(new Color(255, 165, 0));
		info_m.setBounds(75, 225, 130, 130);
		contentPane.add(info_m);
		info_m.setLayout(null);
		
		ImageIcon m_img = new ImageIcon(this.getClass().getResource("/member.png"));
		JLabel info_pic = new JLabel(m_img);
		info_pic.setBounds(33, 20, 64, 64);
		info_m.add(info_pic);
		
		JLabel info_t = new JLabel("修改資料");
		info_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		info_t.setHorizontalAlignment(SwingConstants.CENTER);
		info_t.setBounds(20, 100, 90, 30);
		info_m.add(info_t);
		
		JPanel shop = new JPanel();
		shop.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		shop.setBackground(new Color(255, 165, 0));
		shop.setBounds(245, 225, 130, 130);
		contentPane.add(shop);
		shop.setLayout(null);
		
		ImageIcon shop_img = new ImageIcon(this.getClass().getResource("/shopping.png"));
		JLabel shop_pic = new JLabel(shop_img);
		shop_pic.setBounds(33, 20, 64, 64);
		shop.add(shop_pic);
		
		JLabel shop_t = new JLabel("選購商品");
		shop_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		shop_t.setHorizontalAlignment(SwingConstants.CENTER);
		shop_t.setBounds(20, 100, 90, 30);
		shop.add(shop_t);
		
		JPanel shop_rec = new JPanel();
		shop_rec.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		shop_rec.setBackground(new Color(255, 165, 0));
		shop_rec.setBounds(75, 395, 130, 130);
		contentPane.add(shop_rec);
		shop_rec.setLayout(null);
		
		ImageIcon shop_rec_img = new ImageIcon(this.getClass().getResource("/note.png"));
		JLabel shop_rec_pic = new JLabel(shop_rec_img);
		shop_rec_pic.setBounds(33, 20, 64, 64);
		shop_rec.add(shop_rec_pic);
		
		JLabel shop_rec_t = new JLabel("購買記錄");
		shop_rec_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		shop_rec_t.setHorizontalAlignment(SwingConstants.CENTER);
		shop_rec_t.setBounds(20, 100, 90, 30);
		shop_rec.add(shop_rec_t);
		
		JPanel exit = new JPanel();
		exit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		exit.setBackground(new Color(255, 165, 0));
		exit.setBounds(245, 395, 130, 130);
		contentPane.add(exit);
		exit.setLayout(null);
		
		ImageIcon exit_img = new ImageIcon(this.getClass().getResource("/exit.png"));
		JLabel exit_pic = new JLabel(exit_img);
		exit_pic.setBounds(33, 20, 64, 64);
		exit.add(exit_pic);
		
		JLabel exit_t = new JLabel("離開");
		exit_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		exit_t.setHorizontalAlignment(SwingConstants.CENTER);
		exit_t.setBounds(20, 100, 90, 30);
		exit.add(exit_t);
		
		info_m.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				member_info.setMember_acc(member_acc);
				member_info.setM(new member_implement().read_member_mi(member_acc));
				member_info mi = new member_info();
				mi.setVisible(true);
				dispose();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				info_m.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				info_m.setBackground(new Color(255, 215, 0));
				info_m.setBounds(65, 215, 150, 150);
				
				ImageIcon info_img_b = new ImageIcon(this.getClass().getResource("/member_b.png"));
				info_pic.setIcon(info_img_b);
				info_pic.setBounds(38, 15, 75, 75);
				
				info_t.setBounds(30, 110, 90, 30);
				info_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
				setCursor(new Cursor(12));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				info_m.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				info_m.setBackground(new Color(255, 165, 0));
				info_m.setBounds(75, 225, 130, 130);
				
				info_pic.setIcon(m_img);
				info_pic.setBounds(33, 20, 64, 64);
				
				info_t.setBounds(20, 100, 90, 30);
				info_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
				setCursor(null);
			}
		});
		
		if(new item_implement().get_max_sequence() > 1) {
			shop.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					new order_implement().add_item();
					create_order.setMember_acc(member_acc);
					create_order co = new create_order();
					co.setVisible(true);
					dispose();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					shop.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
					shop.setBackground(new Color(255, 215, 0));
					shop.setBounds(235, 215, 150, 150);
					
					ImageIcon shop_img_b = new ImageIcon(this.getClass().getResource("/shopping_b.png"));
					shop_pic.setIcon(shop_img_b);
					shop_pic.setBounds(38, 15, 75, 75);
					
					shop_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
					shop_t.setBounds(30, 110, 90, 30);
					setCursor(new Cursor(12));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					shop.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					shop.setBackground(new Color(255, 165, 0));
					shop.setBounds(245, 225, 130, 130);
					
					shop_pic.setIcon(shop_img);
					shop_pic.setBounds(33, 20, 64, 64);
					
					shop_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
					shop_t.setBounds(20, 100, 90, 30);
					setCursor(null);
					
				}
			});
		}
		
		shop_rec.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				purchase_rec.setMember_acc(member_acc);
				purchase_rec pr = new purchase_rec();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				shop_rec.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				shop_rec.setBackground(new Color(255, 215, 0));
				shop_rec.setBounds(65, 385, 150, 150);
				
				ImageIcon shop_rec_img_b = new ImageIcon(this.getClass().getResource("/note_b.png"));
				shop_rec_pic.setIcon(shop_rec_img_b);
				shop_rec_pic.setBounds(38, 15, 75, 75);
				
				shop_rec_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
				shop_rec_t.setBounds(30, 110, 90, 30);
				setCursor(new Cursor(12));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				shop_rec.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				shop_rec.setBackground(new Color(255, 165, 0));
				shop_rec.setBounds(75, 395, 130, 130);
				
				shop_rec_pic.setIcon(shop_rec_img);
				shop_rec_pic.setBounds(33, 20, 64, 64);
				
				shop_rec_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
				shop_rec_t.setBounds(20, 100, 90, 30);
				setCursor(null);
			}
		});
		
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(ABORT);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				exit.setBackground(new Color(255, 215, 0));
				exit.setBounds(235, 385, 150, 150);
				
				ImageIcon exit_img_b = new ImageIcon(this.getClass().getResource("/exit_b.png"));
				exit_pic.setIcon(exit_img_b);
				exit_pic.setBounds(38, 15, 75, 75);
				
				exit_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
				exit_t.setBounds(30, 110, 90, 30);
				setCursor(new Cursor(12));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				exit.setBackground(new Color(255, 165, 0));
				exit.setBounds(245, 395, 130, 130);
				
				exit_pic.setIcon(exit_img);
				exit_pic.setBounds(33, 20, 64, 64);
				
				exit_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
				exit_t.setBounds(20, 100, 90, 30);
				setCursor(null);
			}
		});
		
	}
}
