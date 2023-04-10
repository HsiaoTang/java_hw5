package Dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Dao.DBConnection;
import Model.member_management;

public class member_implement implements member_dao{
	public static void main(String[] args) {
		System.out.println(new member_implement().read_member());
	}
	
	@Override
	public boolean create_member(member_management m) {
		
		// TODO Auto-generated method stub
		boolean result = false;
		String SQL = "insert into swimwear.member (member_create_time, member_nn, member_gender, "
				+ "member_bd, member_country, member_pn, member_email, member_acc, member_pwd)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m.getMember_create_time());
			ps.setString(2, m.getMember_nn());
			ps.setString(3, m.getMember_gender());
			ps.setString(4, m.getMember_bd());
			ps.setString(5, m.getMember_country());
			ps.setString(6, m.getMember_pn());
			ps.setString(7, m.getMember_email());
			ps.setString(8, m.getMember_acc());
			ps.setString(9, m.getMember_pwd());
			int r_c = ps.executeUpdate();
			if(r_c == 1) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/*
	String member_nn,
	String member_gender, 
	String member_bd, 
	String member_country,
	String member_pn, 
	String member_email, 
	String member_acc, 
	String member_pwd
	*/

	@Override
	public boolean read_member_ie(String m_acc) {
		// TODO Auto-generated method stub
		boolean result = false;
		int r_c = 0;
		String SQL = "select count(*) from swimwear.member where member_acc = ?";
		Connection conn = DBConnection.getDB();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r_c = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r_c > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public String read_member_pwd(String m_acc) {
		// TODO Auto-generated method stub
		String m_pwd = null;
		String SQL = "select member_pwd from swimwear.member where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				m_pwd = rs.getString("member_pwd");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m_pwd;
	}

	@Override
	public String read_member_nn(String m_acc) {
		// TODO Auto-generated method stub
		String m_nn = null;
		String SQL = "select member_nn from swimwear.member where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				m_nn = rs.getString("member_nn");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m_nn;
	}

	@Override
	public boolean set_member_try(String m_acc) {
		// TODO Auto-generated method stub
		boolean result = false;
		String SQL = "update swimwear.member set member_try = 1, member_try_1st = ? where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			String mlt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis() + 1800 * 1000));
			ps.setString(1, mlt);
			ps.setString(2, m_acc);
			int r_c = ps.executeUpdate();
			if(r_c == 1) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean reset_mt_n_mlt(String m_acc) {
		// TODO Auto-generated method stub
		boolean result = false;
		String SQL = "update swimwear.member set member_try = null, member_try_1st = null, member_login_time = null where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			int r_c = ps.executeUpdate();
			if(r_c == 1) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean add_member_try(String m_acc) {
		// TODO Auto-generated method stub
		boolean result = false;
		String SQL = "update swimwear.member set member_try = member_try + 1 where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			int r_c = ps.executeUpdate();
			if(r_c == 1) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer read_member_try(String m_acc) {
		// TODO Auto-generated method stub
		Integer m_try = null;
		String SQL = "select member_try from swimwear.member where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				m_try = rs.getInt("member_try");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m_try;
	}

	@Override
	public boolean set_member_login_time(String m_acc) {
		// TODO Auto-generated method stub
		boolean result = false;
		String SQL = "update swimwear.member set member_login_time = ?, member_try = null, member_try_1st = null where member_acc = ? and member_try = 3";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			String mlt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis() + 1800 * 1000));
			ps.setString(1, mlt);
			ps.setString(2, m_acc);
			int r_c = ps.executeUpdate();
			if(r_c == 1) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String read_member_login_time(String m_acc) {
		// TODO Auto-generated method stub
		String mlt = null;
		String SQL = "select member_login_time from swimwear.member where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				mlt = rs.getString("member_login_time");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mlt;
	}

	@Override
	public String read_member_try_1st(String m_acc) {
		// TODO Auto-generated method stub
		String mt1 = null;
		String SQL = "select member_try_1st from swimwear.member where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				mt1 = rs.getString("member_try_1st");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mt1;
	}

	@Override
	public String read_member_pn(String m_acc) {
		// TODO Auto-generated method stub
		String m_pn = null;
		String SQL = "select member_pn from swimwear.member where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				m_pn = rs.getString("member_pn");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m_pn;
	}

	@Override
	public String read_member_em_a(String m_acc) {
		// TODO Auto-generated method stub
		String m_em_a = null;
		String SQL = "select member_email from swimwear.member where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				m_em_a = rs.getString("member_email");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m_em_a;
	}

	@Override
	public boolean reset_pwd(String m_pwd, String m_acc) {
		// TODO Auto-generated method stub
		boolean result = false;
		String SQL = "update swimwear.member set member_pwd = ? where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_pwd);
			ps.setString(2, m_acc);
			int r_c = ps.executeUpdate();
			System.out.println(r_c);
			if(r_c == 1) {
				result = true;
			}
		} catch (SQLException e) {
			
		}
		return result;
	}

	@Override
	public member_management read_member_mi(String m_acc) {
		// TODO Auto-generated method stub
		member_management m = null;
		String SQL = "select * from swimwear.member where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m_acc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				m = new member_management(rs.getString("member_nn"),
										rs.getString("member_gender"),
										rs.getString("member_bd"),
										rs.getString("member_country"),
										rs.getString("member_pn"),
										rs.getString("member_email"),
										rs.getString("member_acc"));
	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public boolean update_member_mi(member_management m, String m_acc) {
		// TODO Auto-generated method stub
		boolean result = false;
		String SQL = "update swimwear.member set "
				+ "member_nn = ?, member_gender = ?, member_bd = ?, member_country = ?, "
				+ "member_pn = ?, member_email = ?, member_acc = ? where member_acc = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m.getMember_nn());
			ps.setString(2, m.getMember_gender());
			ps.setString(3, m.getMember_bd());
			ps.setString(4, m.getMember_country());
			ps.setString(5, m.getMember_pn());
			ps.setString(6, m.getMember_email());
			ps.setString(7, m.getMember_acc());
			ps.setString(8, m_acc);
			int r_c = ps.executeUpdate();
			if(r_c == 1) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<member_management> read_member() {
		// TODO Auto-generated method stub
		List<member_management> ml = new ArrayList<member_management>();
		String SQL = "select * from swimwear.member where member_acc <> 'admin123'";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				member_management m = new member_management(rs.getInt("member_id"),
															rs.getString("member_create_time"),
															rs.getString("member_nn"),
															rs.getString("member_gender"),
															rs.getString("member_bd"),
															rs.getString("member_country"),
															rs.getString("member_pn"),
															rs.getString("member_email"),
															rs.getString("member_acc"),
															rs.getString("member_pwd"));
				ml.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ml;
	}

	@Override
	public List<String> get_country_list() {
		// TODO Auto-generated method stub
		List<String> cl = new ArrayList<String>();
		cl.add("");
		String SQL = "select distinct(member_country) from swimwear.member";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String c = rs.getString("member_country");
				cl.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cl;
	}

	@Override
	public List<member_management> read_member_wf(String s_d, String e_d, Integer m_id, String m_acc, String m_g,
			Integer a_t, Integer a_b, String m_c) {
		// TODO Auto-generated method stub
		List<member_management> ml = new ArrayList<member_management>();
		String SQL = "select * from swimwear.member where";
		if(s_d.equals("") && e_d.equals("")) {
			SQL = SQL + "";
		} else if(s_d.equals(e_d)) {
			SQL = SQL + " member_create_time = '" + e_d + "' and";
		} else if(!s_d.equals("") && !e_d.equals("")) {
			SQL = SQL + " member_create_time between '" + s_d + "' and '" + e_d  + "' and";
		}
		if(s_d.equals("") && !e_d.equals("")) {
			SQL = SQL + " member_create_time < '" + e_d + "' and";
		}
		if(!s_d.equals("") && e_d.equals("")) {
			SQL = SQL + " member_create_time > '" + s_d + "' and";
		}
		
		if(m_id == 0) {
			SQL = SQL + "";
		}
		if(m_id != 0) {
			SQL = SQL + " member_id = " + m_id + " and";
		}
		
		if(m_g.equals("")) {
			SQL = SQL + "";
		}
		if(m_g.equals("男")) {
			SQL = SQL + " member_gender = '男' and";
		}
		if(m_g.equals("女")) {
			SQL = SQL + " member_gender = '女' and";
		}
		
		Calendar a1_t = Calendar.getInstance();
		a1_t.set(Calendar.YEAR, a1_t.get(Calendar.YEAR) - a_t);
		Date bd_b = a1_t.getTime();
		Calendar a2_t = Calendar.getInstance();
		a2_t.set(Calendar.YEAR, a2_t.get(Calendar.YEAR) - a_b);
		Date bd_t = a2_t.getTime();
		String bd_t_str = new SimpleDateFormat("yyyy-MM-dd").format(bd_t);
		String bd_b_str = new SimpleDateFormat("yyyy-MM-dd").format(bd_b);
		
		if(a_t == 0 && a_b == 0) {
			SQL = SQL + "";
		} else if(a_t == a_b) {
			SQL = SQL + " member_bd = '" + bd_t_str + "' and";
		} else if(a_t != 0 && a_b != 0) {
			SQL = SQL + " member_bd between '" + bd_b_str + "' and '" + bd_t_str + "' and";
		}
		if(a_t == 0 && a_b != 0) {
			SQL = SQL + " member_bd < '" + bd_t_str + "' and";
		}
		if(a_t != 0 && a_b == 0) {
			SQL = SQL + " member_bd > '" + bd_b_str + "' and";
		}
		
		
		if(m_c.equals("")) {
			SQL = SQL + "";
		}
		if(!m_c.equals("")) {
			SQL = SQL + " member_country = '" + m_c + "' and";
		}
		
		
		if(!m_acc.equals("")) {
			SQL = SQL + " member_acc = '" + m_acc + "'";
		}
		if(m_acc.equals("")) {
			SQL = SQL + " member_acc <> 'admin123'";
		}
		
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				member_management m = new member_management(rs.getInt("member_id"),
															rs.getString("member_create_time"),
															rs.getString("member_nn"),
															rs.getString("member_gender"),
															rs.getString("member_bd"),
															rs.getString("member_country"),
															rs.getString("member_pn"),
															rs.getString("member_email"),
															rs.getString("member_acc"),
															rs.getString("member_pwd"));
				ml.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ml;
	}
}
