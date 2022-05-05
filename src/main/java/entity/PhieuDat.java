package entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="PHIEUDAT")
public class PhieuDat {
	@Id
	@GeneratedValue
	private int MAPD;
	private String EMAIL;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date NGAYDAT;
	private String HOTEN;
	private String DIACHI;
	@ManyToOne
	@JoinColumn(name="MAKH")
	private KhachHang khachhang;
	private String SDT;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date NGAYGIAO;
	private int TRANGTHAI;
	@ManyToOne
	@JoinColumn(name="MANV")
	private NhanVien nhanvien;
	
	public PhieuDat() {}
	
	public PhieuDat(String hoten,String email, String Diachi,String SDT) {
		this.HOTEN=hoten;
		this.EMAIL=email;
		this.DIACHI=Diachi;
		this.SDT=SDT;
	}
	
	
	public int getMAPD() {
		return MAPD;
	}
	public void setMAPD(int mAPD) {
		MAPD = mAPD;
	}
	public Date getNGAYDAT() {
		return NGAYDAT;
	}
	public void setNGAYDAT(Date nGAYDAT) {
		NGAYDAT = nGAYDAT;
	}
	public String getHOTEN() {
		return HOTEN;
	}
	public void setHOTEN(String hOTEN) {
		HOTEN = hOTEN;
	}
	public String getDIACHI() {
		return DIACHI;
	}
	public void setDIACHI(String dIACHI) {
		DIACHI = dIACHI;
	}
	
	public Date getNGAYGIAO() {
		return NGAYGIAO;
	}
	public void setNGAYGIAO(Date nGAYGIAO) {
		NGAYGIAO = nGAYGIAO;
	}
	
	
public KhachHang getKhachhang() {
		return khachhang;
	}

	public void setKhachhang(KhachHang khachhang) {
		this.khachhang = khachhang;
	}

	//	public int getMAKH() {
//		return MAKH;
//	}
//	public void setMAKH(int mAKH) {
//		MAKH = mAKH;
//	}
	public int getTRANGTHAI() {
		return TRANGTHAI;
	}
	public void setTRANGTHAI(int tRANGTHAI) {
		TRANGTHAI = tRANGTHAI;
	}
	
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public NhanVien getNhanvien() {
		return nhanvien;
	}
	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public void show() {
		NGAYDAT=
		new Date(System.currentTimeMillis());
		System.out.println(NGAYDAT);
		System.out.println(LocalDateTime.from(NGAYDAT.toInstant().atZone(ZoneId.of("UTC"))).plusDays(1));
	}
}
