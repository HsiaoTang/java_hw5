package Util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import com.google.i18n.phonenumbers.PhoneNumberUtil;

import Controller.member.calendar_display_sd;
import Controller.member.member_lnr;
import Dao.member.member_implement;
import Model.member_management;





public class util_member {
	public static void main(String[] args) {
		//System.out.println(util_member.gen_pwd());
	}
	
	public static void login_clock(JLabel clock_lbl) {
		Thread clock = new Thread() {
			public void run() {
				try {
					while(true) {
						Calendar cal = new GregorianCalendar();
						int y = cal.get(Calendar.YEAR);
						String m = String.format("%02d", cal.get(Calendar.MONTH) + 1);
						String d = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
						String h = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY));
						String min = String.format("%02d", cal.get(Calendar.MINUTE));
						String s = String.format("%02d", cal.get(Calendar.SECOND));
						String time = y + "-" + m + "-" + d + "    " + h + ":" + min + ":" + s;
						clock_lbl.setText(time);
						sleep(1000);
					}
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
	
	public static String generate_v_pic(String dest_path) {
		int width = 80;
		int height = 30;
		
		BufferedImage v_pic = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = v_pic.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(1,  1, width - 2, height - 2);
		
		String chrs = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		g.setFont(new Font("Microsoft JhengHei", Font.BOLD, 16));
		Random random = new Random();
		String v = "";
		for(int i = 0; i < 4; i++) {
			int idx = random.nextInt(62);
			String chr = chrs.substring(idx, idx + 1);
			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			g.drawString(chr, 5 + 20 * i, random.nextInt(10) + 13);
			v = v + chr;
		}
		
		for(int i = 0; i < 5; i++) {
			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
		}
		
		g.dispose();
		
		try {
			FileOutputStream dest = new FileOutputStream(dest_path + "/v_c.png");
			ImageIO.write(v_pic, "png", dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return v;
	}
	
	public static String[] gen_y_arr() {
		String[] y_arr = new String[100];
		Calendar cal = new GregorianCalendar();
		int y = cal.get(Calendar.YEAR);
		for(int i = 0; i < 100; i++) {
			y_arr[i] = Integer.toString(y);
			y--;	
		}
		return y_arr;
	}
	
	public static String[] gen_m_arr() {
		String[] m_arr = new String[12];
		for(int i = 0; i < 12; i++) {
			m_arr[i] = Integer.toString(i + 1);	
		}
		return m_arr;
	}
	
	public static String[] gen_d_arr(int y, int m) {
		Calendar bd = Calendar.getInstance();
		bd.set(Calendar.YEAR, y);
		bd.set(Calendar.MONTH, m - 1);
		int d = bd.getActualMaximum(Calendar.DAY_OF_MONTH);
		String[] d_arr = new String[d];
		for(int i = 0; i < d; i++) {
			d_arr[i] = Integer.toString(i + 1);
		}
		return d_arr;
	}
	
	public static String[] gen_cc_arr() {
		PhoneNumberUtil pn_util = PhoneNumberUtil.getInstance();
		Set<String> all_regions = pn_util.getSupportedRegions();
		Locale.setDefault(Locale.TAIWAN);
		Locale l = Locale.getDefault();
		Object[] ar_arr = all_regions.toArray();
		String[] cc_arr = new String[ar_arr.length];
		for(int i = 0; i < ar_arr.length; i++) {
			cc_arr[i] = "+" + pn_util.getCountryCodeForRegion((String) ar_arr[i]) + " " 
					+ new Locale("zh-TW", ((String) ar_arr[i])).getDisplayCountry();		
		}	
		return cc_arr;
	}
	
	public static void placeholder_tf(JTextField tf, String tf_h) {
		tf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tf.getText().equals(tf_h)) {
					tf.setText("");
					tf.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tf.getText().isEmpty()) {
					tf.setText(tf_h);
					tf.setForeground(Color.GRAY);
				}
			}
		});
	}
	
	public static void placeholder_pwd_l(JPasswordField pwd_tf, String pwd_h_str) {
		pwd_tf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(pwd_tf.getPassword()).equals(pwd_h_str)) {
					pwd_tf.setText("");
					pwd_tf.setEchoChar('●');
					pwd_tf.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(pwd_tf.getPassword().length == 0) {
					pwd_tf.setText(pwd_h_str);
					pwd_tf.setEchoChar((char)0);
					pwd_tf.setForeground(Color.GRAY);
				}
			}
		});
	}
	
	public static void placeholder_pwd(JPasswordField pwd_tf, String pwd_h_str, JLabel pwd_r_wp, JLabel pwd_h) {
		pwd_tf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(pwd_tf.getPassword()).equals(pwd_h_str)) {
					pwd_tf.setText("");
					pwd_tf.setEchoChar('●');
					pwd_tf.setForeground(Color.BLACK);
				}
				pwd_r_listener(pwd_tf, pwd_r_wp, pwd_h);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(pwd_tf.getPassword().length == 0) {
					pwd_tf.setText(pwd_h_str);
					pwd_tf.setEchoChar((char)0);
					pwd_tf.setForeground(Color.GRAY);
				}
			}
		});
	}
	
	public static void placeholder_cfm_pwd(JPasswordField cfm_pwd_tf, JPasswordField pwd_r_tf, 
			String pwd_h_str, JLabel cfm_pwd_r_wp) {
		cfm_pwd_tf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(cfm_pwd_tf.getPassword()).equals(pwd_h_str)) {
					cfm_pwd_tf.setText("");
					cfm_pwd_tf.setEchoChar('●');
					cfm_pwd_tf.setForeground(Color.BLACK);
				}
				cfm_pwd_r_listener(pwd_r_tf, cfm_pwd_tf, cfm_pwd_r_wp);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(cfm_pwd_tf.getPassword().length == 0) {
					cfm_pwd_tf.setText(pwd_h_str);
					cfm_pwd_tf.setEchoChar((char)0);
					cfm_pwd_tf.setForeground(Color.GRAY);
				}
			}
		});
	}
	
	public static boolean m_nn_checker(JTextField m_nn_tf) {
		boolean result = false;
		String pattern = ".{1,12}";
		result = Pattern.matches(pattern, m_nn_tf.getText());
		return result;
	}
	
	public static boolean pn_checker(JTextField pn_tf) {
		boolean result = false;
		String pattern = "^09\\d{8}$";
		result = Pattern.matches(pattern, pn_tf.getText());
		return result;
	}
	
	public static boolean em_a_checker(JTextField em_a_tf) {
		boolean result = false;
		String pattern = "^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$";
		result = Pattern.matches(pattern, em_a_tf.getText());
		return result;
	}
	
	public static boolean acc_checker(JTextField acc_r_tf) {
		boolean result = false;
		String pattern = ".{8,12}";
		result = Pattern.matches(pattern, acc_r_tf.getText());
		return result;
	}
	
	public static boolean pwd_checker(JPasswordField pwd_tf) {
		boolean result = false;
		String pattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]{8,}";
		result = Pattern.matches(pattern, String.valueOf(pwd_tf.getPassword()));
		return result;
	}
	public static boolean field_checker(JTextField m_nn_tf, JLabel m_nn_h, JComboBox m_g_cb, JLabel m_g_h,
			JComboBox m_bd_d, JLabel bd_h, JTextField pn_tf, JLabel pn_h, JTextField em_a_tf, JLabel em_a_h,
			JTextField acc_r_tf, JLabel acc_r_h, JPasswordField pwd_r_tf, JLabel pwd_h, JPasswordField cfm_pwd_r_tf,
			JLabel cfm_pwd_h) {
		boolean result = false;
		int c = 0;
		if(!m_nn_checker(m_nn_tf)) {
			m_nn_h.setText("*請輸入1 - 12位的暱稱");
		} else {
			m_nn_h.setText("");
			c++;
		}
		if(m_g_cb.getSelectedIndex() == -1) {
			m_g_h.setText("*請選擇性別");
		} else {
			m_g_h.setText("");
			c++;
		}
		if(m_bd_d.getSelectedIndex() == -1) {
			bd_h.setText("*請選擇生日");
		} else {
			bd_h.setText("");
			c++;
		}
		if(!pn_checker(pn_tf)) {
			pn_h.setText("*請輸入行動電話");
		} else {
			pn_h.setText("");
			c++;
		}
		if(!em_a_checker(em_a_tf)) {
			em_a_h.setText("*請輸入正確的Email");
		} else {
			em_a_h.setText("");
			c++;
		}
		if(!acc_checker(acc_r_tf) && acc_r_h.getText().equals("")) {
			acc_r_h.setText("*請輸入8 - 12位的會員帳號");
		} else {
			acc_r_h.setText("");
			c++;
		}
		if(pwd_checker(pwd_r_tf)) {
			pwd_h.setText("");
			c++;
		}
		if(!String.valueOf(pwd_r_tf.getPassword()).equals(String.valueOf(cfm_pwd_r_tf.getPassword()))) {
			cfm_pwd_h.setText("*請再次輸入密碼");
		} else {
			cfm_pwd_h.setText("");
			c++;
		}
		if(c == 8) {
			result = true;
		}
		return result;
	}
	
	public static void pwd_requirement(JPasswordField pwd_r_tf, JLabel pwd_r_wp, JLabel pwd_h) {
		Image red_c_img = new ImageIcon(member_lnr.class.getResource("/red_c.png")).getImage();
		Image green_c_img = new ImageIcon(member_lnr.class.getResource("/green_c.png")).getImage();
		if(pwd_checker(pwd_r_tf)) {
			pwd_r_wp.setIcon(new ImageIcon(green_c_img));
			pwd_h.setText("");
		} else {
			pwd_r_wp.setIcon(new ImageIcon(red_c_img));
			pwd_h.setText("*至少須包含一個大寫字母、一個小寫字母和一個數字");
		}
	}
	
	public static void cfm_pwd_requirement(JPasswordField pwd_r_tf, JPasswordField cfm_pwd_r_tf, JLabel cfm_pwd_r_wp) {
		Image red_c_img = new ImageIcon(member_lnr.class.getResource("/red_c.png")).getImage();
		Image green_c_img = new ImageIcon(member_lnr.class.getResource("/green_c.png")).getImage();
		if(Arrays.equals(pwd_r_tf.getPassword(), cfm_pwd_r_tf.getPassword())) {
			cfm_pwd_r_wp.setIcon(new ImageIcon(green_c_img));
		} else {
			cfm_pwd_r_wp.setIcon(new ImageIcon(red_c_img));
		}
	}
	
	public static void pwd_r_listener(JPasswordField pwd_r_tf, JLabel pwd_r_wp, JLabel pwd_h) {
		pwd_r_tf.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				pwd_requirement(pwd_r_tf, pwd_r_wp, pwd_h);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				pwd_requirement(pwd_r_tf, pwd_r_wp, pwd_h);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				pwd_requirement(pwd_r_tf, pwd_r_wp, pwd_h);
			}
			
		});
	}
	public static void cfm_pwd_r_listener(JPasswordField pwd_r_tf, JPasswordField cfm_pwd_r_tf, JLabel cfm_pwd_r_wp) {
		cfm_pwd_r_tf.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				cfm_pwd_requirement(pwd_r_tf, cfm_pwd_r_tf, cfm_pwd_r_wp);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				cfm_pwd_requirement(pwd_r_tf, cfm_pwd_r_tf, cfm_pwd_r_wp);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				cfm_pwd_requirement(pwd_r_tf, cfm_pwd_r_tf, cfm_pwd_r_wp);
			}
			
		});
	}
	
	public static void acc_r_tf_listener(JTextField acc_r_tf, JLabel acc_r_h) {
		acc_r_tf.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(new member_implement().read_member_ie(acc_r_tf.getText())) {
					acc_r_h.setText("*此帳號已存在");
				} else {
					acc_r_h.setText("");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(new member_implement().read_member_ie(acc_r_tf.getText())) {
					acc_r_h.setText("*此帳號已存在");
				} else {
					acc_r_h.setText("");
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(new member_implement().read_member_ie(acc_r_tf.getText())) {
					acc_r_h.setText("*此帳號已存在");
				} else {
					acc_r_h.setText("");
				}	
			}
			
		});
	}
	public static String refresh_vc_pic(String v_c, ImageIcon img_vc, JLabel v_pic) {
		String new_v_c = util_member.generate_v_pic(System.getProperty("user.dir"));
		img_vc.getImage().flush();
		img_vc = new ImageIcon(System.getProperty("user.dir") + "/v_c.png");
		v_pic.setIcon(img_vc);
		v_pic.revalidate();
		return new_v_c;
	}
	public static String gen_pwd() {
		String pwd = "";
		String chrs = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		for(int i = 0; i < 8; i++) {
			int idx = new Random().nextInt(62);
			String chr = chrs.substring(idx, idx + 1);
			pwd = pwd + chr;
		}
		return pwd;
	}
	
	public static void placeholder_pwd_o(JPasswordField o_pwd_tf, String pwd_h_str) {
		o_pwd_tf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(o_pwd_tf.getPassword()).equals(pwd_h_str)) {
					o_pwd_tf.setText("");
					o_pwd_tf.setEchoChar('●');
					o_pwd_tf.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(o_pwd_tf.getPassword().length == 0) {
					o_pwd_tf.setText(pwd_h_str);
					o_pwd_tf.setEchoChar((char)0);
					o_pwd_tf.setForeground(Color.GRAY);
				}
			}
		});
	}
	
	public static void update_m_bd_d_cb(JComboBox m_bd_d, JComboBox m_bd_y, JComboBox m_bd_m) {
		m_bd_d.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				m_bd_d.removeAllItems();
				int y = Integer.parseInt((String) m_bd_y.getSelectedItem());
				int m = Integer.parseInt((String) m_bd_m.getSelectedItem());
				String[] d_arr = util_member.gen_d_arr(y, m);
				for(String d: d_arr) {
					m_bd_d.addItem(d);
				}
			}
		});
	}
	public static boolean field_checker_mi(JTextField m_nn_tf, JLabel m_nn_h, JComboBox m_g_cb, JLabel m_g_h,
			JComboBox m_bd_d, JLabel bd_h, JTextField pn_tf, JLabel pn_h, JTextField em_a_tf, JLabel em_a_h,
			JTextField acc_r_tf, JLabel acc_r_h) {
		boolean result = false;
		int c = 0;
		if(!m_nn_checker(m_nn_tf)) {
			m_nn_h.setText("*請輸入1 - 12位的暱稱");
		} else {
			m_nn_h.setText("");
			c++;
		}
		if(m_g_cb.getSelectedIndex() == -1) {
			m_g_h.setText("*請選擇性別");
		} else {
			m_g_h.setText("");
			c++;
		}
		if(m_bd_d.getSelectedIndex() == -1) {
			bd_h.setText("*請選擇生日");
		} else {
			bd_h.setText("");
			c++;
		}
		if(!pn_checker(pn_tf)) {
			pn_h.setText("*請輸入行動電話");
		} else {
			pn_h.setText("");
			c++;
		}
		if(!em_a_checker(em_a_tf)) {
			em_a_h.setText("*請輸入正確的Email");
		} else {
			em_a_h.setText("");
			c++;
		}
		if(!acc_checker(acc_r_tf) && acc_r_h.getText().equals("")) {
			acc_r_h.setText("*請輸入8 - 12位的會員帳號");
		} else {
			acc_r_h.setText("");
			c++;
		}
		if(new member_implement().read_member_ie(acc_r_tf.getText())) {
			acc_r_h.setText("*此帳號已存在");
		} else {
			c++;
		}
		if(c == 7) {
			result = true;
		}
		return result;
	}
	
	public static boolean field_checker_mi_pwd(String m_acc, JPasswordField o_pwd_tf, JLabel o_pwd_h, 
			JPasswordField n_pwd_tf, JLabel n_pwd_h, JPasswordField cfm_n_pwd_tf, JLabel cfm_n_pwd_h) {
		boolean result = false;
		int c = 0;
		if(!String.valueOf(o_pwd_tf.getPassword()).equals(new member_implement().read_member_pwd(m_acc))) {
			o_pwd_h.setText("*密碼錯誤");
		} else {
			o_pwd_h.setText("");
			c++;
		}
		if(pwd_checker(n_pwd_tf)){
			n_pwd_h.setText("");
			c++;
		}
		if(!String.valueOf(cfm_n_pwd_tf.getPassword()).equals(String.valueOf(n_pwd_tf.getPassword()))) {
			cfm_n_pwd_h.setText("*請再次輸入密碼");
		} else {
			cfm_n_pwd_h.setText("");
			c++;
		}
		if(c == 3) {
			result = true;
		}
		return result;
	}
	
	public static void regen_cal(JPanel d_p, JTextField y_tf, JTextField m_tf, JFrame c_d, JTextField tf) {
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
		
		Calendar date = Calendar.getInstance();
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
					c_d.setCursor(new Cursor(12));
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
					c_d.setCursor(null);
					if(now.before(date)) {
						m_d.setBackground(new Color(255, 250, 205));
					} else {
						m_d.setBackground(new Color(255, 165, 0));
					}
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					calendar_display_sd.s_d = y_tf.getText() + "-" + String.format("%02d", Integer.parseInt(m_tf.getText().replace("月", "")))
										+ "-" + String.format("%02d", Integer.parseInt(m_d.getText()));
					tf.setText(calendar_display_sd.s_d);
					c_d.dispose();
				}
			});
		}
	}
	public static void ml_display(List<member_management> ml, JTable mt) {
		Object[] col_name = new Object[] {"會員編號", "註冊時間", "暱稱", "性別", "年齡" , "國家", "電話", "信箱", "帳號", "密碼"};
		Object[][] md = new Object[ml.size()][10];
		for(int i = 0; i < ml.size(); i++) {
			md[i][0] = ml.get(i).getMember_id();
			md[i][1] = ml.get(i).getMember_create_time().split("\\.")[0];
			md[i][2] = ml.get(i).getMember_nn();
			md[i][3] = ml.get(i).getMember_gender();
			Date bd = null;
			try {
				bd = new SimpleDateFormat("yyyy-MM-dd").parse(ml.get(i).getMember_bd());
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar now = Calendar.getInstance();
			Calendar bd_c = Calendar.getInstance();
			bd_c.setTime(bd);
			int m_age = now.get(Calendar.YEAR) - bd_c.get(Calendar.YEAR);
			if(bd_c.get(Calendar.DAY_OF_YEAR) > now.get(Calendar.DAY_OF_YEAR)) {
				m_age--;
			}
			md[i][4] = m_age;
			md[i][5] = ml.get(i).getMember_country();
			md[i][6] = ml.get(i).getMember_pn();
			md[i][7] = ml.get(i).getMember_email();
			md[i][8] = ml.get(i).getMember_acc();
			md[i][9] = ml.get(i).getMember_pwd();	
		}
		DefaultTableModel dtm = new DefaultTableModel(md, col_name);
		mt.setModel(dtm);
	}
	
	public static void field_check_rms(JTextField rs_d_tf, JTextField re_d_tf, JTextField m_id_tf, JTextField age_1tf,
			JTextField age_2tf) {
		Date s_d = null;
		Date e_d = null;
		if(!rs_d_tf.getText().equals("") && !re_d_tf.getText().equals("")) {
			try {
				s_d = new SimpleDateFormat("yyyy-MM-dd").parse(rs_d_tf.getText());
				e_d = new SimpleDateFormat("yyyy-MM-dd").parse(re_d_tf.getText());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(e_d.before(s_d)) {
				JOptionPane.showMessageDialog(null, "結束日不應早於起始日");
			}
		}
		if(m_id_tf.getText().equals("")) {
			
		}else if(!util_order.isNumeric(m_id_tf.getText())) {
			JOptionPane.showMessageDialog(null, "請輸入數字");
		}
		if(age_1tf.getText().equals("歲數下限")) {
			age_1tf.setText("0");
		}
		if(age_2tf.getText().equals("歲數上限")) {
			age_2tf.setText("0");
		}
		if(!util_order.isNumeric(age_1tf.getText()) || !util_order.isNumeric(age_2tf.getText())) {
			JOptionPane.showMessageDialog(null, "請輸入數字");
		}
		
		if(Integer.parseInt(age_1tf.getText()) > 0 && Integer.parseInt(age_2tf.getText()) < Integer.parseInt(age_1tf.getText())) {
			JOptionPane.showMessageDialog(null, "歲數上限應大於歲數下限");
		}
	}
	
	public static List<member_management> read_member_swf(JTextField rs_d_tf, JTextField re_d_tf, JTextField m_id_tf, JTextField m_a_tf,
			JComboBox g_cb, JTextField age_1tf, JTextField age_2tf, JComboBox c_cb) {
		List<member_management> ml = null;
		String s_d = rs_d_tf.getText();
		String e_d = re_d_tf.getText();
		Integer m_id = 0;
		if(m_id_tf.getText().equals("")) {
			m_id = 0;
		} else {
			m_id = Integer.parseInt(m_id_tf.getText());
		}
		String m_acc = m_a_tf.getText();
		String m_g = (String)g_cb.getSelectedItem();
		Integer a_t = Integer.parseInt(age_2tf.getText());
		Integer a_b = Integer.parseInt(age_1tf.getText());
		String m_c = (String)c_cb.getSelectedItem();
		
		ml = new member_implement().read_member_wf(s_d, e_d, m_id, m_acc, m_g, a_t, a_b, m_c);
		return ml;
	}
	
	public static JFreeChart create_chart_g(List<member_management> ml) {
		Map<String, Double> info_map = new HashMap<>();
		String k = null;
		Double v = 1.0;
		for(int i = 0; i < ml.size(); i++) {
			k = ml.get(i).getMember_gender();
			if(info_map.containsKey(k)) {
				info_map.replace(k, info_map.get(k) + 1);
			} else {
				info_map.put(k, v);
			}
		}
		Double sum = 0.0;
		for(Double i: info_map.values()) {
			sum = sum + i;
		}
		DecimalFormat df = new DecimalFormat("#%");
		DefaultPieDataset ds = new DefaultPieDataset();
		for(String g :info_map.keySet()) {
			ds.setValue(g, info_map.get(g)/sum);
		}
		JFreeChart chart = ChartFactory.createPieChart("會員性別比", ds, true, true, false);
		PiePlot pp = (PiePlot) chart.getPlot();
		pp.setLabelFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		pp.setLabelGenerator(new StandardPieSectionLabelGenerator(("{0}: ({2})"), 
																	NumberFormat.getNumberInstance(), 
																	new DecimalFormat("0.00%")));
		TextTitle tt = chart.getTitle();
		tt.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		LegendTitle lt = chart.getLegend();
		lt.setItemFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		
		return chart;
	}
	
	public static JFreeChart create_chart_c(List<member_management> ml) {
		Map<String, Double> info_map = new HashMap<>();
		String k = null;
		Double v = 1.0;
		for(int i = 0; i < ml.size(); i++) {
			k = ml.get(i).getMember_country();
			if(info_map.containsKey(k)) {
				info_map.replace(k, info_map.get(k) + 1);
			} else {
				info_map.put(k, v);
			}
		}
		Double sum = 0.0;
		for(Double i: info_map.values()) {
			sum = sum + i;
		}
		DecimalFormat df = new DecimalFormat("#%");
		DefaultPieDataset ds = new DefaultPieDataset();
		for(String g :info_map.keySet()) {
			ds.setValue(g, info_map.get(g)/sum);
		}
		JFreeChart chart = ChartFactory.createPieChart("會員國籍比", ds, true, true, false);
		PiePlot pp = (PiePlot) chart.getPlot();
		pp.setLabelFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		pp.setLabelGenerator(new StandardPieSectionLabelGenerator(("{0}: ({2})"), 
																	NumberFormat.getNumberInstance(), 
																	new DecimalFormat("0.00%")));
		TextTitle tt = chart.getTitle();
		tt.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		LegendTitle lt = chart.getLegend();
		lt.setItemFont(new Font("Microsoft JhengHei", Font.PLAIN, 10));
		
		return chart;
	}
}
