package Util;

import java.awt.BasicStroke;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import Dao.item.item_implement;
import Dao.order.order_implement;
import Model.item_management;
import Model.order_management;



public class util_order {
	public static void main(String[] args) {
		int col_count_o = new order_implement().get_col_count_o();
		int item_count = new item_implement().get_max_sequence() - 1;
		Object[] col_name = new Object[col_count_o - 2];
		col_name[0] = "訂單編號";
		col_name[1] = "建立時間";
		col_name[2] = "消費金額";
		for(int i = 0; i < item_count; i++) {
			col_name[2 + (i * 3 + 1)] = "商品" + (i + 1) + "名稱";
			col_name[2 + (i * 3 + 2)] = "商品" + (i + 1) + "價格";
			col_name[2 + (i * 3 + 3)] = "商品" + (i + 1) + "數量";
		}
		for(Object o: col_name) {
			System.out.println(o);
		}
	}
	
	public static order_management build_od_object(String member_acc, JTextField money_paid, List<JTextField> qs, List<JLabel> p_titles, List<JLabel> p_tags) {
		order_management n_od = null;
		String m_acc = member_acc;
		Integer m_p = Integer.parseInt(money_paid.getText());
		List<item_management> i_l = new ArrayList<item_management>();
		List<Integer> q_l = new ArrayList<Integer>();
		for(int i = 0; i < qs.size(); i++) {
			if(!qs.get(i).getText().equals("0")) {
				String item_n = p_titles.get(i).getText();
				String item_p_yen = p_tags.get(i).getText();
				Integer item_p = Integer.parseInt(item_p_yen.substring(0, item_p_yen.length() - 2));
				item_management i_b = new item_management(item_n, item_p);
				i_l.add(i_b);
				Integer item_q = Integer.parseInt(qs.get(i).getText());
				q_l.add(item_q);
			}
		}
		n_od = new order_management(m_acc, m_p, i_l, q_l);
		
		return n_od;
	}
	
	public static String od_display(String member_acc, List<JTextField> qs, List<JLabel> p_titles, List<JLabel> p_tags) {
		String od_dtl = "";
		String m_acc = member_acc;
		Integer o_s = 0;
		od_dtl = "會員帳號：" + m_acc + "\n";
		for(int i = 0; i < qs.size(); i++) {
			if(!qs.get(i).getText().equals("0")) {
				String item_n = p_titles.get(i).getText();
				String item_p_yen = p_tags.get(i).getText();
				Integer item_p = Integer.parseInt(item_p_yen.substring(0, item_p_yen.length() - 2));
				Integer item_q = Integer.parseInt(qs.get(i).getText());
				o_s = o_s + item_p * item_q;
				od_dtl = od_dtl + item_n + "：" + item_p_yen + " * " + item_q + " = " + item_p * item_q + "元\n";
			}
		}
		od_dtl = od_dtl + "合計：" + o_s + "元";
		return od_dtl;
	}
	
	public static void centering_od_txt(JTextPane od_dtl_txt) {
		StyledDocument doc = od_dtl_txt.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
	}
	
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static void cal_change(JTextField m_p_tf, JTextPane od_dtl_txt, JTextField c_tf) {
		m_p_tf.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				String[]  s_a = od_dtl_txt.getText().split("：");
				String t_y = s_a[s_a.length - 1];
				int t = Integer.parseInt(t_y.substring(0, t_y.length() - 1));
				int m_p = 0;
				if(util_order.isNumeric(m_p_tf.getText())) {
					m_p = Integer.parseInt(m_p_tf.getText());
				} else if(m_p_tf.getText().equals("")){
					m_p = 0;
				} else {
					JOptionPane.showMessageDialog(null, "請輸入合理金額");
					return;
				}
				if(m_p - t >= 0) {
					c_tf.setText(m_p - t + "");
				} else {
					c_tf.setText("-");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String[]  s_a = od_dtl_txt.getText().split("：");
				String t_y = s_a[s_a.length - 1];
				int t = Integer.parseInt(t_y.substring(0, t_y.length() - 1));
				int m_p = 0;
				if(util_order.isNumeric(m_p_tf.getText())) {
					m_p = Integer.parseInt(m_p_tf.getText());
				} else if(m_p_tf.getText().equals("")){
					m_p = 0;
				} else {
					JOptionPane.showMessageDialog(null, "請輸入合理金額");
					return;
				}
				if(m_p - t >= 0) {
					c_tf.setText(m_p - t + "");
				} else {
					c_tf.setText("-");
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String[]  s_a = od_dtl_txt.getText().split("：");
				String t_y = s_a[s_a.length - 1];
				int t = Integer.parseInt(t_y.substring(0, t_y.length() - 1));
				int m_p = 0;
				if(util_order.isNumeric(m_p_tf.getText())) {
					m_p = Integer.parseInt(m_p_tf.getText());
				} else if(m_p_tf.getText().equals("")){
					m_p = 0;
				} else {
					JOptionPane.showMessageDialog(null, "請輸入合理金額");
					return;
				}
				if(m_p - t >= 0) {
					c_tf.setText(m_p - t + "");
				} else {
					c_tf.setText("-");
				}
			}
			
		});
	}
	
	public static String od_display_bos(order_management o) {
		String od_d_pt1 = "";
		String od_d_pt2 = "";
		String od_d_pt3 = "";
		String od_d_txt = "";
		List<item_management> i_l = o.getItem_list();
		List<Integer> q_l = o.getQuantity_list();
		od_d_pt1 = "訂單編號：" + o.getOrder_id() + "\n"
					+ "建立時間：" + o.getOrder_create_time().split("\\.")[0] + "\n"
					+ "會員帳號：" + o.getOrder_creator() + "\n";
		for(int i = 0; i < i_l.size(); i++) {
			if(q_l.get(i) != 0) {
				od_d_pt2 = od_d_pt2 + i_l.get(i).getItem_name() + "：" + i_l.get(i).getItem_price() + " 元 * " 
						+ q_l.get(i) + " = " + (i_l.get(i).getItem_price() * q_l.get(i)) + "\n";
			}
		}
		od_d_pt3 = "合計：" + o.getOrder_sum() + "\n"
					+ "支付：" + o.getMoney_paid() + "\n"
					+ "找零：" + (o.getMoney_paid() - o.getOrder_sum());
		od_d_txt = od_d_pt1 + od_d_pt2 + od_d_pt3;
		return od_d_txt;
	}
	
	public static void ol_display(JTable order_table, List<order_management> ol) {
		int col_count_o = new order_implement().get_col_count_o();
		int item_count = new item_implement().get_max_sequence() - 1;
		Object[] col_name = new Object[col_count_o - 2];
		col_name[0] = "訂單編號";
		col_name[1] = "建立時間";
		col_name[2] = "消費金額";
		for(int i = 0; i < item_count; i++) {
			col_name[2 + (i * 3 + 1)] = "商品" + (i + 1) + "名稱";
			col_name[2 + (i * 3 + 2)] = "商品" + (i + 1) + "價格";
			col_name[2 + (i * 3 + 3)] = "商品" + (i + 1) + "數量";
		}
		Object[][] old_arr = new Object[ol.size()][col_count_o - 2];
		for(int i = 0; i < ol.size(); i++) {
			old_arr[i] = new Object[col_count_o - 2];
			old_arr[i][0] = ol.get(i).getOrder_id();
			old_arr[i][1] = ol.get(i).getOrder_create_time().split("\\.")[0];
			old_arr[i][2] = ol.get(i).getOrder_sum();
			List<item_management> il = ol.get(i).getItem_list();
			List<Integer> ql = ol.get(i).getQuantity_list();
			for(int j = 1; j < il.size() + 1; j++) {
				old_arr[i][j * 3] = il.get(j - 1).getItem_name();
				old_arr[i][j * 3 + 1] = il.get(j - 1).getItem_price();
				old_arr[i][j * 3 + 2] = ql.get(j - 1);
			}
		}
		
		DefaultTableModel dtm = new DefaultTableModel(old_arr, col_name);
		for(int i = 0; i < ol.size(); i++) {
			for(int j = 0; j < col_count_o - 2; j++) {
				if(dtm.getValueAt(i, j) instanceof Integer && (int) dtm.getValueAt(i, j) == 0 ||
					!(dtm.getValueAt(i, j) instanceof Integer) && (dtm.getValueAt(i, j) == null)) {
					dtm.setValueAt((Object)"-", i, j);
				}
			}
		}
		order_table.setModel(dtm);
	}
	
	public static void ol_display_admin(JTable order_table, List<order_management> ol) {
		int col_count_o = new order_implement().get_col_count_o();
		int item_count = new item_implement().get_max_sequence() - 1;
		Object[] col_name = new Object[col_count_o - 1];
		col_name[0] = "訂單編號";
		col_name[1] = "建立時間";
		col_name[2] = "會員帳號";
		col_name[3] = "消費金額";
		for(int i = 0; i < item_count; i++) {
			col_name[3 + (i * 3 + 1)] = "商品" + (i + 1) + "名稱";
			col_name[3 + (i * 3 + 2)] = "商品" + (i + 1) + "價格";
			col_name[3 + (i * 3 + 3)] = "商品" + (i + 1) + "數量";
		}
		Object[][] old_arr = new Object[ol.size()][col_count_o - 1];
		for(int i = 0; i < ol.size(); i++) {
			old_arr[i] = new Object[col_count_o - 1];
			old_arr[i][0] = ol.get(i).getOrder_id();
			old_arr[i][1] = ol.get(i).getOrder_create_time().split("\\.")[0];
			old_arr[i][2] = ol.get(i).getOrder_creator();
			old_arr[i][3] = ol.get(i).getOrder_sum();
			List<item_management> il = ol.get(i).getItem_list();
			List<Integer> ql = ol.get(i).getQuantity_list();
			for(int j = 1; j < il.size() + 1; j++) {
				old_arr[i][j * 3 + 1] = il.get(j - 1).getItem_name();
				old_arr[i][j * 3 + 2] = il.get(j - 1).getItem_price();
				old_arr[i][j * 3 + 3] = ql.get(j - 1);
			}
		}
		
		DefaultTableModel dtm = new DefaultTableModel(old_arr, col_name);
		for(int i = 0; i < ol.size(); i++) {
			for(int j = 0; j < col_count_o - 1; j++) {
				if(dtm.getValueAt(i, j) instanceof Integer && (int) dtm.getValueAt(i, j) == 0 ||
					!(dtm.getValueAt(i, j) instanceof Integer) && (dtm.getValueAt(i, j) == null)) {
					dtm.setValueAt((Object)"-", i, j);
				}
			}
		}
		order_table.setModel(dtm);
	}
	
	public static void replace_nz_on_jt(JTable order_table) {
		int rc = order_table.getRowCount();
		int cc = order_table.getColumnCount();
		for(int r = 0; r < rc; r++) {
			for (int c = 0; c < cc; c++) {
			if((!((String) order_table.getModel().getValueAt(rc, cc) instanceof String) 
					&& (int) order_table.getModel().getValueAt(rc, cc) == 0) ||
					(!((String) order_table.getModel().getValueAt(rc, cc) instanceof String) 
					&& (int) order_table.getModel().getValueAt(rc, cc) == 0)) {
				DefaultTableModel dtm = (DefaultTableModel)order_table.getModel();
				}
			}
		}
	}
	public static void field_checker(JTextField s_d_tf, JTextField e_d_tf, JTextField s_m, JTextField t_m) {
		Date s_d = null;
		Date e_d = null;
		if(!s_d_tf.getText().equals("") && !e_d_tf.getText().equals("")) {
			try {
				s_d = new SimpleDateFormat("yyyy-MM-dd").parse(s_d_tf.getText());
				e_d = new SimpleDateFormat("yyyy-MM-dd").parse(e_d_tf.getText());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(e_d.before(s_d)) {
				JOptionPane.showMessageDialog(null, "結束日不應早於起始日");
			}
		}
		if(s_m.getText().equals("金額下限")) {
			s_m.setText("0");
		}
		if(t_m.getText().equals("金額上限")) {
			t_m.setText("0");
		}
		if(!isNumeric(s_m.getText()) || !isNumeric(t_m.getText())) {
			JOptionPane.showMessageDialog(null, "請輸入數字");
		}
		
		if(Integer.parseInt(t_m.getText()) > 0 && Integer.parseInt(t_m.getText()) < Integer.parseInt(s_m.getText())) {
			JOptionPane.showMessageDialog(null, "金額上限應大於金額下限");
		}
	}
	
	public static List<order_management> read_order_mwf(JTextField s_d_tf, JTextField e_d_tf, 
			JTextField i_c_tf, JTextField s_m, JTextField t_m, String m_acc) {
		List<order_management> ol = null;
		String s_d = s_d_tf.getText();
		String e_d = e_d_tf.getText();
		String[] il = i_c_tf.getText().split(",");
		Integer s = 0;
		if(s_m.getText().equals("")) {
			s = 0;
		} else {
			s = Integer.parseInt(s_m.getText());
		}
		Integer t = 0;
		if(t_m.getText().equals("")) {
			t = 0;
		} else {
			t = Integer.parseInt(t_m.getText());
		}
		ol = new order_implement().read_order_list_mwf(s_d, e_d, il, s, t, m_acc);
		
		return ol;
	}
	
	public static boolean gen_excel(JTable t, String file_path) {
		boolean result = false;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("訂單列表");
		
		Font defaultFont = workbook.createFont();
		defaultFont.setFontName("Microsoft JhengHei");
		defaultFont.setFontHeightInPoints((short)12);
		
		CellStyle defaultStyle = workbook.createCellStyle();
		defaultStyle.setFont(defaultFont);
		defaultStyle.setAlignment(HorizontalAlignment.LEFT);
		defaultStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		defaultStyle.setBorderTop(BorderStyle.NONE);
		defaultStyle.setBorderBottom(BorderStyle.NONE);
		defaultStyle.setBorderLeft(BorderStyle.NONE);
		defaultStyle.setBorderRight(BorderStyle.NONE);
		
		int cc = t.getColumnCount();
		int rc = t.getRowCount();
//		Object[] hdrs = new Object[cc];
//		for(int i = 0; i < cc; i++) {
//			hdrs[i] = t.getModel().getColumnName(cc);
//		}
		
		XSSFRow excel_hdrs = sheet.createRow(0);
		for(int i = 0; i < cc; i++) {
			XSSFCell hdr = excel_hdrs.createCell(i);
			hdr.setCellValue((String)t.getColumnModel().getColumn(i).getHeaderValue());
			hdr.setCellStyle(defaultStyle);
		}
//		Object[][] iit = new Object[rc][cc];
		for(int i = 0; i < rc; i++) {
			XSSFRow iit_row = sheet.createRow(i + 1);
			for(int j = 0; j < cc; j++) {
				 XSSFCell iit_cell = iit_row.createCell(j);
				iit_cell.setCellStyle(defaultStyle);
				Object tbf = t.getModel().getValueAt(i, j);
				if(tbf instanceof String) {
					iit_cell.setCellValue((String) tbf);
				} else if(tbf instanceof Integer) {
					iit_cell.setCellValue((Integer) tbf);
				}		
			}
		}
		for(int i = 0; i < cc; i++) {
			sheet.autoSizeColumn(i);
		}
		try {
			FileOutputStream ol = new FileOutputStream(file_path);
			workbook.write(ol);
			result = true;
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static JFreeChart create_chart_i(List<order_management> ol) {
		Map<String, Integer> info_map = new HashMap<>();
		for(int i = 0; i < ol.size(); i++) {
			List<item_management> il = ol.get(i).getItem_list();
			List<Integer> ql = ol.get(i).getQuantity_list();
			for(int j = 0; j < ql.size(); j++) {
				String in = il.get(j).getItem_name();
				Integer p = il.get(j).getItem_price();
				Integer q = ql.get(j);
				if(in != null && !info_map.containsKey(in)) {
					info_map.put(in, p * q);
				} else if(in != null && info_map.containsKey(in)){
					info_map.replace(in, info_map.get(in) + p * q);
				}
			}
		}
		
		DefaultKeyedValues kv = new DefaultKeyedValues();
		for(String i: info_map.keySet()) {
			kv.addValue(i, info_map.get(i));
		}
		CategoryDataset ds = DatasetUtilities.createCategoryDataset("商品名稱", kv);
		JFreeChart chart = ChartFactory.createBarChart("各商品銷售金額", "商品名稱", "銷售金額", ds, PlotOrientation.VERTICAL, false, false, false);
		TextTitle tt = chart.getTitle();
		java.awt.Font f = new java.awt.Font("Microsoft JhengHei", java.awt.Font.PLAIN, 10);
		tt.setFont(f);
		CategoryPlot cp = chart.getCategoryPlot();
		CategoryAxis ca = cp.getDomainAxis();
		ca.setTickLabelFont(f);
		ca.setLabelFont(f);
		ca.setAxisLineStroke(new BasicStroke(5));
		ValueAxis va = cp.getRangeAxis();
		va.setTickLabelFont(f);
		va.setLabelFont(f);
		
		return chart;
	}
	
	public static JFreeChart create_chart_m(List<order_management> ol) {
		Map<String, Integer> info_map = new HashMap<>();
		for(int i = 0; i < ol.size(); i++) {
			String oc = ol.get(i).getOrder_creator();
			List<item_management> il = ol.get(i).getItem_list();
			List<Integer> ql = ol.get(i).getQuantity_list();
			for(int j = 0; j < ql.size(); j++) {
				Integer p = il.get(j).getItem_price();
				Integer q = ql.get(j);
				if(info_map.containsKey(oc)) {
					info_map.replace(oc, info_map.get(oc) + p * q);
				} else {
					info_map.put(oc, p * q);
				}
			}
		}
		
		DefaultKeyedValues kv = new DefaultKeyedValues();
		for(String i: info_map.keySet()) {
			kv.addValue(i, info_map.get(i));
		}
		CategoryDataset ds = DatasetUtilities.createCategoryDataset("會員帳號", kv);
		JFreeChart chart = ChartFactory.createBarChart("各會員消費金額", "會員帳號", "消費金額", ds, PlotOrientation.VERTICAL, false, false, false);
		TextTitle tt = chart.getTitle();
		java.awt.Font f = new java.awt.Font("Microsoft JhengHei", java.awt.Font.PLAIN, 10);
		tt.setFont(f);
		CategoryPlot cp = chart.getCategoryPlot();
		CategoryAxis ca = cp.getDomainAxis();
		ca.setTickLabelFont(f);
		ca.setLabelFont(f);
		ca.setAxisLineStroke(new BasicStroke(5));
		ValueAxis va = cp.getRangeAxis();
		va.setTickLabelFont(f);
		va.setLabelFont(f);
		
		return chart;
	}
}
