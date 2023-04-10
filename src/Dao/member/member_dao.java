package Dao.member;

import java.util.List;

import Model.member_management;

interface member_dao {
	boolean create_member(member_management m);
	boolean read_member_ie(String m_acc);
	String read_member_pwd(String m_acc);
	String read_member_nn(String m_acc);
	boolean set_member_try(String m_acc);
	boolean add_member_try(String m_acc);
	boolean reset_mt_n_mlt(String m_acc);
	Integer read_member_try(String m_acc);
	String read_member_try_1st(String m_acc);
	boolean set_member_login_time(String m_acc);
	String read_member_login_time(String m_acc);
	String read_member_pn(String m_acc);
	String read_member_em_a(String m_acc);
	boolean reset_pwd(String m_pwd, String m_acc);
	member_management read_member_mi(String m_acc);
	boolean update_member_mi(member_management m, String m_acc);
	List<member_management> read_member();
	List<String> get_country_list();
	List<member_management> read_member_wf(String s_d, String e_d, Integer m_id, String m_acc, String m_g, Integer a_t, Integer a_b, String m_c);
	
}
