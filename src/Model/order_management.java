package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class order_management {
	private Integer order_id;
	private String order_create_time;
	private String order_creator;
	private Integer order_sum;
	private Integer money_paid;
	private List<item_management> item_list;
	private List<Integer> quantity_list;
	
	
	public order_management(String order_creator, Integer money_paid, List<item_management> item_list, List<Integer> quantity_list) {
		super();
		this.order_creator = order_creator;
		this.money_paid = money_paid;
		this.order_create_time = setOrder_create_time();
		this.item_list = item_list;
		this.quantity_list = quantity_list;
	}
	
	public order_management(Integer order_id, String order_create_time, String order_creator, Integer money_paid, Integer order_sum, List<item_management> item_list, List<Integer> quantity_list) {
		super();
		this.order_id = order_id;
		this.order_create_time = order_create_time;
		this.order_creator = order_creator;
		this.money_paid = money_paid;
		this.order_sum = order_sum;
		this.item_list = item_list;
		this.quantity_list = quantity_list;
	}
	
	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	public String getOrder_create_time() {
		return order_create_time;
	}

	public String setOrder_create_time() {
		String order_create_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return this.order_create_time = order_create_time;
	}
	
	public String getOrder_creator() {
		return order_creator;
	}

	public void setOrder_creator(String order_creator) {
		this.order_creator = order_creator;
	}
	
	public Integer getMoney_paid() {
		return money_paid;
	}
	
	public void setMoney_paid(Integer money_paid) {
		this.money_paid = money_paid;
	}
	
	public Integer getOrder_sum() {
		return order_sum;
	}
	
	public void setOrder_sum() {
		Integer order_sum = 0;
		for(int i = 0; i < item_list.size(); i++) {
			order_sum = order_sum + item_list.get(i).getItem_price() * quantity_list.get(i);
		}
		this.order_sum = order_sum;
	}
	
	public List<item_management> getItem_list() {
		return item_list;
	}

	public void setItem_list(List<item_management> item_list) {
		this.item_list = item_list;
	}
	
	public List<Integer> getQuantity_list() {
		return quantity_list;
	}

	public void setQuantity_list(List<Integer> quantity_list) {
		this.quantity_list = quantity_list;
	}
}
