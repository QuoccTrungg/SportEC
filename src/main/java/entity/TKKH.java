package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TKKH")
public class TKKH {
	@Id 
	private String TK;
	private String MK;
	@ManyToOne
	@JoinColumn(name="MAKH")
	
	private KhachHang kh;
	public String getTK() {
		return TK;
	}
	public void setTK(String tK) {
		TK = tK;
	}
	public String getMK() {
		return MK;
	}
	public void setMK(String mK) {
		MK = mK;
	}
	public KhachHang getKh() {
		return kh;
	}
	public void setKh(KhachHang kh) {
		this.kh = kh;
	}
	
}
