package kitri.foodCourt.user;

import java.util.Iterator;
import java.util.List;

import kitri.foodCourt.user.swing.FLabel;

public interface Basket {
	
	//���� �޼ҵ�
	//�߰� : list�߰��� �ƴ϶� totalPrice�� savePoint ���� ���� ����
	public int add(BasketDetail detail);
	//����(index�� ����) : list������ �ƴ϶� totalPrice�� savePoint ���� ���� ����
	public BasketDetail remove(int index);
	//����(���ؼ� ����)
	//������ null ������ ������ ��ü
	public BasketDetail remove(BasketDetail detailRemove);
	//��� ���� : ��� 0����
	public int removeAll();
	
	public int getRequestNumber();
	public void setRequestNumber(int requestNumber);
	public String getPaymentDate();
	public void setPaymentDate(String paymentDate);
	public int getTotalPrice();
	public void setTotalPrice(int totalPrice);
	public int getSavePoint();
	public void setSavePoint(int savePoint);
	public List<BasketDetail> getDetailList();
	public void setDetailList(List<BasketDetail> detailList);
	public FLabel getOrderCount();
	public void setOrderCount(FLabel orderCount);
	
}
