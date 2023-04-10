package Dao.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.DBConnection;
import Model.item_management;


public class item_implement implements item_dao{
	
	public static void main(String[] args) {
		
	}
	
	@Override
	public Integer get_max_sequence() {
		// TODO Auto-generated method stub
		String SQL = "select max(display_sequence) from swimwear.item";
		Integer max_sequence = null;
		Connection conn = DBConnection.getDB();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				max_sequence = rs.getInt("max(display_sequence)") + 1;
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return max_sequence;
	}

	@Override
	public Integer get_sequence() {
		// TODO Auto-generated method stub
		String SQL = "select count(*) from swimwear.item where display_sequence is not null";
		Integer display_sequence = null;
		Connection conn = DBConnection.getDB();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				display_sequence = rs.getInt("count(*)") + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return display_sequence;
	}
	
	@Override
	public void set_new_sequence(Integer og_d_s, Integer new_d_s) {
		// TODO Auto-generated method stub
		String SQL = "update swimwear.item set display_sequence = ? where display_sequence = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, new_d_s);
			ps.setInt(2, og_d_s);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void set_sequence(String item_name, Integer item_price) {
		// TODO Auto-generated method stub
		String SQL = "update swimwear.item set display_sequence = ?, item_stop_time = null where item_name = ? and item_price = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, get_sequence());
			ps.setString(2, item_name);
			ps.setInt(3, item_price);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void remove_sequence(String item_name, Integer item_price) {
		// TODO Auto-generated method stub
		String SQL = "update swimwear.item set display_sequence = null where item_name = ? and item_price = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, item_name);
			ps.setInt(2, item_price);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void create_item(String item_name, Integer item_price, String item_pic_path) {
		String SQL = "insert into swimwear.item (item_create_time, item_name, item_price, item_pic)"
				+ "values (?, ?, ?, ?)";
		Connection conn = DBConnection.getDB();
		item_management item = new item_management(item_name, item_price, item_pic_path);
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, item.setItem_create_time());
			ps.setString(2, item_name);
			ps.setInt(3, item_price);
			ps.setString(4, item_pic_path);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public item_management read_item_update(Integer item_ds) {
		// TODO Auto-generated method stub
		item_management item_tb_update = null;
		String SQL = "select display_sequence, item_name, item_price, item_pic from swimwear.item "
				+ "where display_sequence = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, item_ds);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				item_tb_update = new item_management(
						rs.getString("item_name"),
						rs.getInt("item_price"),
						rs.getString("item_pic"),
						rs.getInt("display_sequence"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item_tb_update;
	}
	
	@Override
	public item_management read_item_update_null(String item_name, Integer item_price) {
		// TODO Auto-generated method stub
		item_management item_tb_update = null;
		String SQL = "select display_sequence, item_name, item_price, item_pic from swimwear.item "
				+ "where item_name = ? and item_price = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, item_name);
			ps.setInt(2, item_price);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				item_tb_update = new item_management(
						rs.getString("item_name"),
						rs.getInt("item_price"),
						rs.getString("item_pic"),
						rs.getInt("display_sequence"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item_tb_update;
	}

	@Override
	public List<item_management> read_item_display() {
		// TODO Auto-generated method stub
		String SQL = "select * from swimwear.item where display_sequence is not null order by display_sequence";
		Connection conn = DBConnection.getDB();
		List<item_management> item_list_display = new ArrayList<item_management>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				item_management item = new item_management(
						rs.getString("item_name"),
						rs.getInt("item_price"),
						rs.getString("item_pic"),
						rs.getInt("display_sequence"));
				item_list_display.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item_list_display;
	}

	@Override
	public List<item_management> read_item() {
		// TODO Auto-generated method stub
		String SQL = "select * from swimwear.item order by -display_sequence desc";
		Connection conn = DBConnection.getDB();
		List<item_management> item_list = new ArrayList<item_management>();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				item_management item = new item_management(
						rs.getString("item_name"),
						rs.getInt("item_price"),
						rs.getString("item_pic"),
						rs.getInt("display_sequence"));
				item_list.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item_list;
	}
	
	@Override
	public void update_item(item_management item) {
		// TODO Auto-generated method stub
		String SQL = "update swimwear.item set item_name = ?, item_price = ?, item_pic = ? where display_sequence = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, item.getItem_name());
			ps.setInt(2, item.getItem_price());
			ps.setString(3, item.getItem_pic_path());
			ps.setInt(4, item.getDisplay_sequence());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update_item_n_p(String new_item_name, Integer new_item_price, String new_item_pic, String item_name, Integer item_price, String item_pic) {
		// TODO Auto-generated method stub
		String SQL = "update swimwear.item set item_name = ?, item_price = ?, item_pic = ? where item_name = ? and item_price = ? and item_pic = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, new_item_name);
			ps.setInt(2, new_item_price);
			ps.setString(3, new_item_pic);
			ps.setString(4, item_name);
			ps.setInt(5, item_price);
			ps.setString(6, item_pic);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update_item_n_p_p(String new_item_name, Integer new_item_price, String new_item_pic, String item_name,
			Integer item_price, String item_pic) {
		// TODO Auto-generated method stub
		String SQL = "update swimwear.item set item_name = ?, item_price = ?, item_pic = ? where item_name = ? and item_price = ? and item_pic = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, new_item_name);
			ps.setInt(2, new_item_price);
			ps.setString(3, new_item_pic);
			ps.setString(4, item_name);
			ps.setInt(5, item_price);
			ps.setString(6, item_pic);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void stop_item(String item_name, Integer item_price) {
		// TODO Auto-generated method stub
		String SQL = "update swimwear.item set display_sequence = null, item_stop_time = ? where item_name = ? and item_price = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, item_management.setItem_stop_time());
			ps.setString(2, item_name);
			ps.setInt(3, item_price);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String get_pic_src(String item_name, Integer item_price) {
		// TODO Auto-generated method stub
		String img_path = null;
		String SQL = "select item_pic from swimwear.item where item_name = ? and item_price = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, item_name);
			ps.setInt(2, item_price);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				img_path = rs.getString("item_pic");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return img_path;
	}

	@Override
	public void delete_nonstop_item(int display_sequence) {
		// TODO Auto-generated method stub
		String SQL = "delete from swimwear.item where display_sequence = ?";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, display_sequence);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete_stop_item(String item_name, int item_price) {
		// TODO Auto-generated method stub
		String SQL = "delete from swimwear.item where item_name = ? and item_price = ? and display_sequence is null";
		Connection conn = DBConnection.getDB();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, item_name);
			ps.setInt(2, item_price);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
