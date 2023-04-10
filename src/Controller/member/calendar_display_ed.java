package Controller.member;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import Controller.order.purchase_rec;
import Util.util_member;

public class calendar_display_ed extends JFrame {
	
	public static String s_d;
	private JPanel contentPane;
	private JTextField y_tf;
	private JTextField m_tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calendar_display_ed frame = new calendar_display_ed();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void setS_d(String s_d) {
		calendar_display_ed.s_d = s_d;
	}
	
	public static String getS_d() {
		return calendar_display_ed.s_d;
	}
	
	/**
	 * Create the frame.
	 */
	public calendar_display_ed() {
		Image img = new ImageIcon(member_lnr.class.getResource("/surfing_i.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setUndecorated(true);
		requestFocusInWindow(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(970, 210, 300, 335);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		Calendar date = Calendar.getInstance();
		date.get(Calendar.YEAR);
		
		JButton l_y = new JButton("<");
		l_y.setBounds(10, 10, 50, 30);
		contentPane.add(l_y);
		
		JButton n_y = new JButton(">");
		n_y.setBounds(240, 10, 50, 30);
		contentPane.add(n_y);
		
		y_tf = new JTextField("" + date.get(Calendar.YEAR));
		y_tf.setHorizontalAlignment(SwingConstants.CENTER);
		y_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		y_tf.setBounds(75, 10, 150, 30);
		y_tf.setEditable(false);
		contentPane.add(y_tf);
		y_tf.setColumns(10);
		
		JButton l_m = new JButton("<");
		l_m.setBounds(10, 45, 50, 30);
		contentPane.add(l_m);
		
		JButton n_m = new JButton(">");
		n_m.setBounds(240, 45, 50, 30);
		contentPane.add(n_m);

		m_tf = new JTextField((date.get(Calendar.MONTH) + 1) + "月");
		m_tf.setHorizontalAlignment(SwingConstants.CENTER);
		m_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		m_tf.setBounds(75, 45, 150, 30);
		m_tf.setEditable(false);
		contentPane.add(m_tf);
		m_tf.setColumns(10);
		
		JPanel d_p = new JPanel();
		d_p.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		d_p.setBackground(new Color(255, 215, 0));
		d_p.setBounds(10, 85, 280, 215);
		contentPane.add(d_p);
		d_p.setLayout(null);
		
		JButton clr_d = new JButton("清除日期");
		clr_d.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		clr_d.setBounds(200, 300, 90, 29);
		contentPane.add(clr_d);
		
		String[] wds = {"日", "一", "二", "三", "四", "五", "六"};
		for(int i = 0; i < 7; i++) {
			JLabel wd_t = new JLabel(wds[i]);
			wd_t.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
			wd_t.setHorizontalAlignment(SwingConstants.CENTER);
			wd_t.setOpaque(true);
			wd_t.setBackground(new Color(255, 99, 71));
			wd_t.setBounds(20 + i * 35, 5, 30, 20);
			d_p.add(wd_t);
		}
		
		l_y.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(y_tf.getText().equals("1")) {
					l_y.setEnabled(false);
				}
				y_tf.setText("" + (Integer.parseInt(y_tf.getText()) - 1));
				d_p.removeAll();
				util_member.regen_cal(d_p, y_tf, m_tf, calendar_display_ed.this, purchase_rec.e_d_tf);
				d_p.revalidate();
				d_p.repaint();
			}
		});
		
		n_y.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				y_tf.setText("" + (Integer.parseInt(y_tf.getText()) + 1));
				d_p.removeAll();
				util_member.regen_cal(d_p, y_tf, m_tf, calendar_display_ed.this, purchase_rec.e_d_tf);
				d_p.revalidate();
				d_p.repaint();
			}
		});
		
		l_m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(m_tf.getText().equals("1月")) {
					m_tf.setText("12月");
					y_tf.setText("" + (Integer.parseInt(y_tf.getText()) - 1));
				} else {
					m_tf.setText((Integer.parseInt(m_tf.getText().replace("月", "")) - 1) + "月");
				}
				d_p.removeAll();
				util_member.regen_cal(d_p, y_tf, m_tf, calendar_display_ed.this, purchase_rec.e_d_tf);
				d_p.revalidate();
				d_p.repaint();
			}
		});
		
		n_m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(m_tf.getText().equals("12月")) {
					m_tf.setText("1月");
					y_tf.setText("" + (Integer.parseInt(y_tf.getText()) + 1));
				} else {
					m_tf.setText((Integer.parseInt(m_tf.getText().replace("月", "")) + 1) + "月");
				}
				d_p.removeAll();
				util_member.regen_cal(d_p, y_tf, m_tf, calendar_display_ed.this, purchase_rec.e_d_tf);
				d_p.revalidate();
				d_p.repaint();
			}
		});
		
		int y = Integer.parseInt(y_tf.getText());
		int m = Integer.parseInt(m_tf.getText().replace("月", ""));
		date.set(Calendar.YEAR, y);
		date.set(Calendar.MONTH, m - 1);
		
		int ds = date.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int d = 1; d <= ds; d++) {
			date.set(Calendar.DAY_OF_MONTH, d);
			int m_w = date.get(Calendar.WEEK_OF_MONTH);
			int d_w = date.get(Calendar.DAY_OF_WEEK);
			JLabel m_d = new JLabel("" + d);
			m_d.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
			m_d.setHorizontalAlignment(SwingConstants.CENTER);
			m_d.setOpaque(true);
			Calendar now = Calendar.getInstance();
			date.set(Calendar.DAY_OF_MONTH, d);
			if(now.before(date)) {
				m_d.setBackground(new Color(255, 250, 205));
			} else {
				m_d.setBackground(new Color(255, 165, 0));
			}
			m_d.setBounds(20 + (d_w - 1) * 35, 35 + (m_w - 1) * 30, 30, 20);
			d_p.add(m_d);
			m_d.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					setCursor(new Cursor(12));
					m_d.setBackground(new Color(152, 251, 152));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					int y = Integer.parseInt(y_tf.getText());
					int m = Integer.parseInt(m_tf.getText().replace("月", ""));
					int d =  Integer.parseInt(m_d.getText());
;					date.set(Calendar.YEAR, y);
					date.set(Calendar.MONTH, m - 1);
					date.set(Calendar.DAY_OF_MONTH, d);
					setCursor(null);
					if(now.before(date)) {
						m_d.setBackground(new Color(255, 250, 205));
					} else {
						m_d.setBackground(new Color(255, 165, 0));
					}
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					
					calendar_display_ed.s_d = y_tf.getText() + "-" + String.format("%02d", Integer.parseInt(m_tf.getText().replace("月", "")))
											+ "-" + String.format("%02d", Integer.parseInt(m_d.getText()));
					purchase_rec.e_d_tf.setText(s_d);
					dispose();
				}
				
				
			});
			
			this.addWindowFocusListener(new WindowFocusListener() {

				@Override
				public void windowGainedFocus(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowLostFocus(WindowEvent e) {
					// TODO Auto-generated method stub
					dispose();
				}
			});
			clr_d.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					purchase_rec.e_d_tf.setText("");
				}
			});
		}
	}
}
