package Dao.item;

import java.util.List;

import Model.item_management;


interface item_dao {
	Integer get_max_sequence();
	Integer get_sequence();
	void set_new_sequence(Integer og_d_s, Integer new_d_s);
	void set_sequence(String item_name, Integer item_price);
	void remove_sequence(String item_name, Integer item_price);
	void create_item(String item_name, Integer item_price, String item_pic_path);
	item_management read_item_update(Integer item_ds);
	item_management read_item_update_null(String item_name, Integer item_price);
	List<item_management> read_item();
	List<item_management> read_item_display();
	void update_item(item_management item);
	void update_item_n_p(String new_item_name, Integer new_item_price, String new_item_pic, String item_name, Integer item_price, String item_pic);
	void update_item_n_p_p(String new_item_name, Integer new_item_price, String new_item_pic, String item_name, Integer item_price, String item_pic);
	void stop_item(String item_name, Integer item_price);
	String get_pic_src(String item_name, Integer item_price);
	void delete_nonstop_item(int display_sequence);
	void delete_stop_item(String item_name, int item_price);
}
