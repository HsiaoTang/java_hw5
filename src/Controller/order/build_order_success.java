package Controller.order;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.member.member_funcs;
import Controller.member.member_lnr;
import Dao.member.member_implement;
import Model.order_management;
import Util.util_order;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;

public class build_order_success extends JFrame {

	private JPanel contentPane;
	private static String member_acc;
	private static order_management o;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					build_order_success frame = new build_order_success();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void setMember_acc(String member_acc) {
		build_order_success.member_acc = member_acc;
	}
	
	public static String getMember_acc() {
		return build_order_success.member_acc;
	}
	
	public static void setO(order_management o){
		build_order_success.o = o;
	}
	
	public static order_management getO() {
		return build_order_success.o;
	}
	/**
	 * Create the frame.
	 */
	public build_order_success() {
		Image img = new ImageIcon(member_lnr.class.getResource("/surfing_i.png")).getImage();
		setIconImage(img);
		setVisible(true);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 60, 450, 750);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel m_nn_lbl = new JLabel(new member_implement().read_member_nn(build_order_success.member_acc));
		m_nn_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		m_nn_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		m_nn_lbl.setBounds(180, 80, 150, 35);
		contentPane.add(m_nn_lbl);
		
		JLabel hi_lbl = new JLabel("嗨！");
		hi_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		hi_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		hi_lbl.setBounds(120, 80, 55, 35);
		contentPane.add(hi_lbl);
		
		JLabel wl_lbl = new JLabel("訂單建立成功！");
		wl_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		wl_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
		wl_lbl.setBounds(150, 125, 150, 35);
		contentPane.add(wl_lbl);
		
		JTextPane od_dtl_txt = new JTextPane();
		od_dtl_txt.setBounds(20, 175, 410, 300);
		od_dtl_txt.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(od_dtl_txt);
		util_order.centering_od_txt(od_dtl_txt);
		od_dtl_txt.setText(util_order.od_display_bos(o));
		
		JScrollPane od_dtl_scrl = new JScrollPane(od_dtl_txt);
		od_dtl_scrl.setBounds(20, 175, 410, 300);
		contentPane.add(od_dtl_scrl);
		
		JButton p_o = new JButton("列印");
		p_o.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		p_o.setBounds(20, 675, 120, 35);
		contentPane.add(p_o);
		
		JButton bt_funcs = new JButton("回功能選單");
		bt_funcs.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		bt_funcs.setBounds(165, 675, 120, 35);
		contentPane.add(bt_funcs);
		
		JButton e_b = new JButton("離開");
		e_b.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		e_b.setBounds(310, 675, 120, 35);
		contentPane.add(e_b);
		
		//列印
		p_o.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					od_dtl_txt.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//
		bt_funcs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member_funcs.setMember_acc(member_acc);
				member_funcs mf = new member_funcs();
				dispose();
			}
		});
		
		//離開
		e_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
	}

}
