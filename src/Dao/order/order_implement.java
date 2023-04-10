package Dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Dao.DBConnection;
import Dao.item.item_implement;
import Model.item_management;
import Model.order_management;



public class order_implement implements order_dao{
	public static void main(String[] args) {
		System.out.println(new order_implement().get_col_count_o());
	}
	@Override
	public Integer get_col_count_i() {
		// TODO Auto-generated method stub
		String SQL = "select count(*) from information_schema.columns where table_schema = ? and table_name = ?";
		Integer col_count = null;
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, "swimwear");
			ps.setString(2, "order");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				col_count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return col_count;
	}

	@Override
	public void add_item() {
		// TODO Auto-generated method stub
		Integer li_count = new item_implement().get_sequence() - 1;
		Integer ei_count = (get_col_count_i() - 5) / 3;
		Integer item_count = ei_count + 1;
		while(ei_count != li_count) {
			if(li_count > ei_count && ei_count == 0) {
				String SQL = "alter table swimwear.order "
						+ "add column " + "item" + item_count + "_n" + " varchar(45) null after order_sum, "
						+ "add column " + "item" + item_count + "_p" + " int null after " + "item" + item_count + "_n, "
						+ "add column " + "item" + item_count + "_q" + " int null after " + "item" + item_count + "_p";
				Connection conn = DBConnection.getDB();
				try {
					Statement s = conn.createStatement();
					s.executeUpdate(SQL);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(li_count > ei_count && ei_count > 0) {
				String SQL = "alter table swimwear.order "
						+ "add column " + "item" + item_count + "_n" + " varchar(45) null after " + "item" + (item_count - 1) + "_q,"
						+ "add column " + "item" + item_count + "_p" + " int null after " + "item" + item_count + "_n,"
						+ "add column " + "item" + item_count + "_q" + " int null after " + "item" + item_count + "_p";
				Connection conn = DBConnection.getDB();
				try {
					Statement s = conn.createStatement();
					s.executeUpdate(SQL);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				return;
			}
			ei_count++;
			item_count++;
		}
	}

	@Override
	public void create_order(order_management new_order) {
		// TODO Auto-generated method stub
		String o_c_t = new_order.getOrder_create_time();
		String o_c = new_order.getOrder_creator();
		Integer m_p = new_order.getMoney_paid();
		new_order.setOrder_sum();
		Integer o_s = new_order.getOrder_sum();
		List<item_management> i_l = new_order.getItem_list();
		List<Integer> q_l = new_order.getQuantity_list();
		Integer i_ct = q_l.size();
		String SQL_pt1 = "insert into swimwear.order (order_create_time, order_creator, money_paid, order_sum, ";
		String i_c_ns = "";
		for(int i = 1; i < i_ct + 1; i++) {
			String i_c_n = "item" + i + "_n, item" + i + "_p, item" + i + "_q";
			i_c_ns = i_c_ns + i_c_n;
			if(i != i_ct) {
				i_c_ns = i_c_ns + ", ";
			}
		}
		String SQL_pt2 = ") values";
		String i_c_vs = " ('" + o_c_t + "', '" + o_c + "', "+ m_p + ", "+ o_s + ", ";
		for(int i = 0; i < i_ct; i++) {
			String i_c_v = "'" + i_l.get(i).getItem_name() + "', " + i_l.get(i).getItem_price() + ", " + q_l.get(i);
			i_c_vs = i_c_vs + i_c_v;
			if(i != i_ct - 1) {
				i_c_vs = i_c_vs + ", ";
			}
		}
		String SQL_pt3 = ")";
		Connection conn = DBConnection.getDB();
		try {
			Statement s = conn.createStatement();
			s.executeUpdate(SQL_pt1 + i_c_ns + SQL_pt2 + i_c_vs + SQL_pt3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public order_management read_last_order_member(String m_acc) {
		// TODO Auto-generated method stub
		order_management o = null;
		int item_count = new item_implement().get_max_sequence() - 1;
		String SQL = "select * from swimwear.order where order_creator = ? order by order_id desc limit 1";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer o_i = rs.getInt("order_id");
				String o_c_t = rs.getString("order_create_time");
				String o_c = rs.getString("order_creator");
				Integer m_p = rs.getInt("money_paid");
				Integer o_s = rs.getInt("order_sum");
				List<item_management> i_l = new ArrayList<item_management>();
				for(int i = 1; i < item_count + 1; i++) {
					i_l.add(new item_management(rs.getString("item" + i + "_n"), rs.getInt("item" + i + "_p")));
				}
				List<Integer> q_l = new ArrayList<Integer>();
				for(int i = 1; i < item_count + 1; i++) {
					q_l.add(rs.getInt("item" + i + "_q"));
				}
				o = new order_management(o_i, o_c_t, o_c, m_p, o_s, i_l, q_l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
	@Override
	public List<order_management> read_order_list_member(String m_acc) {
		// TODO Auto-generated method stub
		List<order_management> o_l = new ArrayList<order_management>();
		int item_count = new item_implement().get_max_sequence() - 1;
		String SQL = "select * from swimwear.order where order_creator = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer o_i = rs.getInt("order_id");
				String o_c_t = rs.getString("order_create_time");
				String o_c = rs.getString("order_creator");
				Integer m_p = rs.getInt("money_paid");
				Integer o_s = rs.getInt("order_sum");
				List<item_management> i_l = new ArrayList<item_management>();
				for(int i = 1; i < item_count + 1; i++) {
					i_l.add(new item_management(rs.getString("item" + i + "_n"), rs.getInt("item" + i + "_p")));
				}
				List<Integer> q_l = new ArrayList<Integer>();
				for(int i = 1; i < item_count + 1; i++) {
					q_l.add(rs.getInt("item" + i + "_q"));
				}
				o_l.add(new order_management(o_i, o_c_t, o_c, m_p, o_s, i_l, q_l));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o_l;
	}

	@Override
	public List<order_management> read_order_list_admin() {
		// TODO Auto-generated method stub
		List<order_management> o_l = new ArrayList<order_management>();
		int item_count = new item_implement().get_max_sequence() - 1;
		String SQL = "select * from swimwear.order";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer o_i = rs.getInt("order_id");
				String o_c_t = rs.getString("order_create_time");
				String o_c = rs.getString("order_creator");
				Integer m_p = rs.getInt("money_paid");
				Integer o_s = rs.getInt("order_sum");
				List<item_management> i_l = new ArrayList<item_management>();
				for(int i = 1; i < item_count + 1; i++) {
					i_l.add(new item_management(rs.getString("item" + i + "_n"), rs.getInt("item" + i + "_p")));
				}
				List<Integer> q_l = new ArrayList<Integer>();
				for(int i = 1; i < item_count + 1; i++) {
					q_l.add(rs.getInt("item" + i + "_q"));
				}
				o_l.add(new order_management(o_i, o_c_t, o_c, m_p, o_s, i_l, q_l));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o_l;
	}
	@Override
	public Integer get_col_count_o() {
		// TODO Auto-generated method stub
		Integer col_count_o = null;
		String SQL = "select count(column_name) from information_schema.columns "
						+ "where table_schema = 'swimwear' and table_name = 'order'";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				col_count_o = rs.getInt("count(column_name)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return col_count_o;
	}
	@Override
	public List<order_management> read_order_list_mwf(String s_d, String e_d, String[] il, Integer s_m, Integer t_m, String m_acc) {
		// TODO Auto-generated method stub
		List<order_management> o_l = new ArrayList<order_management>();
		String SQL = "select * from swimwear.order where";
		int i_c = new item_implement().get_max_sequence() - 1;
		if(s_d.equals("") && e_d.equals("")) {
			SQL = SQL + "";
		}
		if(s_d.equals("") && !e_d.equals("")) {
			SQL = SQL + " order_create_time < '" + e_d + "' and";
		}
		if(!s_d.equals("") && e_d.equals("")) {
			SQL = SQL + " order_create_time > '" + s_d + "' and";
		}
		if(!s_d.equals("") && !e_d.equals("")) {
			SQL = SQL + " order_create_time between '" + s_d + "' and '" + e_d  + "' and";
		}
		
		if(s_m == 0 && t_m == 0) {
			SQL = SQL + "";
		}
		if(s_m != 0 && t_m == 0) {
			SQL = SQL + " order_sum > " + s_m + " and";
		}
		if(s_m == 0 && t_m != 0) {
			SQL = SQL + " order_sum < " + t_m + " and";
		}
		if(s_m != 0 && t_m != 0) {
			SQL = SQL + " order_sum between " + s_m + " and " + t_m + " and";
 		}
		
		if(!il[0].equals("")) {
			SQL = SQL + " (";
			for(int i = 0; i < il.length; i++) {
				for(int j = 1; j < i_c + 1; j++) {
					SQL = SQL + "item" + j + "_n = '" + il[i] + "' or ";
				}
			}
			SQL = SQL.substring(0, SQL.length() - 4) + ") and";
		}
		if(m_acc.equals("")) {
			SQL = SQL + " order_creator <> 'admin123'";
		} else {
			SQL = SQL + " order_creator ='" + m_acc + "'";
		}
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer o_i = rs.getInt("order_id");
				String o_c_t = rs.getString("order_create_time");
				String o_c = rs.getString("order_creator");
				Integer m_p = rs.getInt("money_paid");
				Integer o_s = rs.getInt("order_sum");
				List<item_management> i_l = new ArrayList<item_management>();
				for(int i = 1; i < i_c + 1; i++) {
					i_l.add(new item_management(rs.getString("item" + i + "_n"), rs.getInt("item" + i + "_p")));
				}
				List<Integer> q_l = new ArrayList<Integer>();
				for(int i = 1; i < i_c + 1; i++) {
					q_l.add(rs.getInt("item" + i + "_q"));
				}
				o_l.add(new order_management(o_i, o_c_t, o_c, m_p, o_s, i_l, q_l));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o_l;
	}
	@Override
	public boolean update_order_creator(String n_m_acc, String o_m_acc) {
		// TODO Auto-generated method stub
		boolean result = false;
		String SQL = "update swimwear.order set order_creator = ? WHERE order_creator = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, n_m_acc);
			ps.setString(2, o_m_acc);
			int r_c = ps.executeUpdate();
			if(r_c > 0) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
