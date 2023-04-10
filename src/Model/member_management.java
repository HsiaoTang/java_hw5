package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class member_management {
	private Integer member_id;
	private String member_create_time;
	private String member_lvl;
	private String member_nn;
	private String member_gender;
	private String member_bd;
	private String member_country;
	private String member_pn;
	private String member_email;
	private String member_acc;
	private String member_pwd;
	private String member_try;
	private String member_try_1st;
	public member_management(String member_nn, String member_gender, String member_bd, String member_country,
			String member_pn, String member_email, String member_acc, String member_pwd) {
		super();
		this.member_nn = member_nn;
		setMember_create_time();
		this.member_create_time = getMember_create_time();
		this.member_gender = member_gender;
		this.member_bd = member_bd;
		this.member_country = member_country;
		this.member_pn = member_pn;
		this.member_email = member_email;
		this.member_acc = member_acc;
		this.member_pwd = member_pwd;
	}
	public member_management(String member_nn, String member_gender, String member_bd, String member_country,
			String member_pn, String member_email, String member_acc) {
		super();
		this.member_nn = member_nn;
		this.member_gender = member_gender;
		this.member_bd = member_bd;
		this.member_country = member_country;
		this.member_pn = member_pn;
		this.member_email = member_email;
		this.member_acc = member_acc;
	}
	public member_management(Integer member_id, String member_create_time, String member_nn, String member_gender, String member_bd, String member_country,
			String member_pn, String member_email, String member_acc, String member_pwd) {
		super();
		this.member_id = member_id;
		this.member_create_time = member_create_time;
		this.member_nn = member_nn;
		this.member_gender = member_gender;
		this.member_bd = member_bd;
		this.member_country = member_country;
		this.member_pn = member_pn;
		this.member_email = member_email;
		this.member_acc = member_acc;
		this.member_pwd = member_pwd;
	}
	
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getMember_create_time() {
		return member_create_time;
	}
	public void setMember_create_time() {
		String member_create_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		this.member_create_time = member_create_time;
	}
	public String getMember_lvl() {
		return member_lvl;
	}
	public void setMember_lvl(String member_lvl) {
		this.member_lvl = member_lvl;
	}
	public String getMember_nn() {
		return member_nn;
	}
	public void setMember_nn(String member_nn) {
		this.member_nn = member_nn;
	}
	public String getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}
	public String getMember_bd() {
		return member_bd;
	}
	public void setMember_bd(String member_bd) {
		this.member_bd = member_bd;
	}
	public String getMember_country() {
		return member_country;
	}
	public void setMember_country(String member_country) {
		this.member_country = member_country;
	}
	public String getMember_pn() {
		return member_pn;
	}
	public void setMember_pn(String member_pn) {
		this.member_pn = member_pn;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_acc() {
		return member_acc;
	}
	public void setMember_acc(String member_acc) {
		this.member_acc = member_acc;
	}
	public String getMember_pwd() {
		return member_pwd;
	}
	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}
	public String getMember_try() {
		return member_try;
	}
	public void setMember_try(String member_try) {
		this.member_try = member_try;
	}
	public String getMember_try_1st() {
		return member_try_1st;
	}
	public void setMember_try_1st() {
		String member_try_1st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis() + 1800 * 1000));
		this.member_try_1st = member_try_1st;
	}
	

}
