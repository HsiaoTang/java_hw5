package Dao.order;

import java.util.List;

import Model.item_management;
import Model.order_management;



/*

ALTER TABLE `swimwear`.`order` 
ADD COLUMN `item1_p` INT NULL AFTER `item1_n`,
ADD COLUMN `item1_q` INT NULL AFTER `item1_p`,
CHANGE COLUMN `item1_name` `item1_n` VARCHAR(45) NULL DEFAULT NULL ;

 */

public interface order_dao {
	Integer get_col_count_i();
	void add_item();
	void create_order(order_management new_order);
	List<order_management> read_order_list_member(String m_acc);
	List<order_management> read_order_list_admin();
	List<order_management> read_order_list_mwf(String s_d, String e_d, String[] il, Integer s_m, Integer t_m , String m_acc);
	order_management read_last_order_member(String m_acc);
	Integer get_col_count_o();
	boolean update_order_creator(String n_m_acc, String o_m_acc);
	/*
	void update_order();
	void delete_order();
	*/
}
