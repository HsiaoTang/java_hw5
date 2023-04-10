package Model;

public class coupon_management {
	private Integer coupon_id;
	private String member_acc;
	private String new_member;
	private String normal_member;
	private String platinum_member;
	private String activity_member;
	
	public coupon_management(String member_acc, String new_member, String normal_member, String platinum_member,
			String activity_member) {
		super();
		this.member_acc = member_acc;
		this.new_member = new_member;
		this.normal_member = normal_member;
		this.platinum_member = platinum_member;
		this.activity_member = activity_member;
	}
	
	public String getNew_member() {
		return new_member;
	}
	public void setNew_member(String new_member) {
		this.new_member = new_member;
	}
	public String getNormal_member() {
		return normal_member;
	}
	public void setNormal_member(String normal_member) {
		this.normal_member = normal_member;
	}
	public String getPlatinum_member() {
		return platinum_member;
	}
	public void setPlatinum_member(String platinum_member) {
		this.platinum_member = platinum_member;
	}
	public String getActivity_member() {
		return activity_member;
	}
	public void setActivity_member(String activity_member) {
		this.activity_member = activity_member;
	}
	public Integer getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(Integer coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getMember_acc() {
		return member_acc;
	}
	public void setMember_acc(String member_acc) {
		this.member_acc = member_acc;
	}
	
}
