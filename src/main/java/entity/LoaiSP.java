package entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="LOAISP")
public class LoaiSP {
	@Id
	@GeneratedValue
	private int MALOAI;
	private String TENLOAI;
	@OneToMany(mappedBy = "LoaiSP", fetch = FetchType.EAGER)
//	private Collection<SanPham> sanpham;
	public int getMALOAI() {
		return MALOAI;
	}
	public void setMALOAI(int mALOAI) {
		MALOAI = mALOAI;
	}
	public String getTENLOAI() {
		return TENLOAI;
	}
	public void setTENLOAI(String tENLOAI) {
		TENLOAI = tENLOAI;
	}
//	public Collection<SanPham> getSanpham() {
//		return sanpham;
//	}
//	public void setSanpham(Collection<SanPham> sanpham) {
//		this.sanpham = sanpham;
//	}
	
}
