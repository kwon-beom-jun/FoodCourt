package kitri.foodCourt.dto;

import java.util.Date;

public class UserDto {
	//�������� 
	String userId;
	String name;
	String phoneNumberFirst;
	String phoneNumberMiddle;
	String phoneNumberlast;
	int userPoint;
	
	//������
	Date joinDate;
	Date secessionDate;
	char enable;
	//ȸ�� �ʿ��� ��й�ȣ�� ��й�ȣ ���� �亯�� �ý����� ��� �־���ϴ°�?
	//�����ý����� �翬 �ʿ������, �����ڿ����� �Ⱥ��̰� �Ұ��� ���ؾ��ҵ�
	String password;
	String passwordQuiz;
	String passwordAnswer;
	
	public UserDto() {
		super();
	}
	
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Date getSecessionDate() {
		return secessionDate;
	}
	public void setSecessionDate(Date secessionDate) {
		this.secessionDate = secessionDate;
	}
	public char getEnable() {
		return enable;
	}
	public void setEnable(char enable) {
		this.enable = enable;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumberFirst() {
		return phoneNumberFirst;
	}
	public void setPhoneNumberFirst(String phoneNumberFirst) {
		this.phoneNumberFirst = phoneNumberFirst;
	}
	public String getPhoneNumberMiddle() {
		return phoneNumberMiddle;
	}
	public void setPhoneNumberMiddle(String phoneNumberMiddle) {
		this.phoneNumberMiddle = phoneNumberMiddle;
	}
	public String getPhoneNumberlast() {
		return phoneNumberlast;
	}
	public void setPhoneNumberlast(String phoneNumberlast) {
		this.phoneNumberlast = phoneNumberlast;
	}
	public int getUserPoint() {
		return userPoint;
	}
	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", name=" + name + ", phoneNumberFirst=" + phoneNumberFirst
				+ ", phoneNumberMiddle=" + phoneNumberMiddle + ", phoneNumberlast=" + phoneNumberlast + ", userPoint="
				+ userPoint + ", joinDate=" + joinDate + ", secessionDate=" + secessionDate + ", enable=" + enable
				+ ", password=" + password + ", passwordQuiz=" + passwordQuiz + ", passwordAnswer=" + passwordAnswer
				+ "]";
	}
}
