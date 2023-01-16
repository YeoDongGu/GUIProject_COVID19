package hospital;

public class HosVO {
	private String sidonm;
	private String sggunm;
	private String yadmnm;
	private String telno;

	public HosVO() {
		super();
	}

	public HosVO(String sidonm, String sggunm, String yadmnm, String telno) {
		this.sidonm = sidonm;
		this.sggunm = sggunm;
		this.yadmnm = yadmnm;
		this.telno = telno;
	}

	public String getSidonm() {
		return sidonm;
	}

	public void setSidonm(String sidonm) {
		this.sidonm = sidonm;
	}

	public String getSggunm() {
		return sggunm;
	}

	public void setSggunm(String sggunm) {
		this.sggunm = sggunm;
	}

	public String getYadmnm() {
		return yadmnm;
	}

	public void setYadmnm(String yadmnm) {
		this.yadmnm = yadmnm;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

}