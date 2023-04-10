package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class item_management {
	private Integer item_id;
	private String item_create_time;
	private String item_launch_time;
	private String item_name;
	private Integer item_price;
	private String item_pic_path;
	private Integer display_sequence;
	private String item_stop_time;
	
	public item_management() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public item_management(String item_name, Integer item_price) {
		super();
		this.item_name = item_name;
		this.item_price = item_price;
	}
	
	public item_management(String item_name, Integer item_price, String item_pic_path) {
		super();
		this.item_name = item_name;
		this.item_price = item_price;
		this.item_pic_path = item_pic_path;
	}
	
	public item_management(String item_name, Integer item_price, String item_pic_path, Integer display_sequence) {
		super();
		this.item_name = item_name;
		this.item_price = item_price;
		this.item_pic_path = item_pic_path;
		this.display_sequence = display_sequence;
	}
	
	public String getItem_create_time() {
		return item_create_time;
	}
	public String setItem_create_time() {
		String item_create_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return item_create_time;
	}
	
	public String getItem_launch_time() {
		return item_launch_time;
	}

	public String setItem_launch_time() {
		String item_launch_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return item_launch_time;
	}
	
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public Integer getItem_price() {
		return item_price;
	}
	public void setItem_price(Integer item_price) {
		this.item_price = item_price;
	}
	public String getItem_pic_path() {
		return item_pic_path;
	}
	public void setItem_pic_path(String item_pic_path) {
		this.item_pic_path = item_pic_path;
	}
	
	public Integer getDisplay_sequence() {
		return display_sequence;
	}
	public void setDisplay_sequence(Integer display_sequence) {
		this.display_sequence = display_sequence;
	}
	public String getItem_stop_time() {
		return item_stop_time;
	}
	public static String setItem_stop_time() {
		String item_stop_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return item_stop_time;
	}

	
	

}
