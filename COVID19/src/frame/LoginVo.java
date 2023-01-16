package frame;

public class LoginVo {
	private String id;
	private String pwd;
	private String name;
	private String rsid;
	private String sido;
	private String dong;

	public LoginVo() {

	}

	public LoginVo(String id, String pwd, String name, String rsid, String sido, String dong) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.rsid = rsid;
		this.sido = sido;
		this.dong = dong;
	}

	public String getId() {
		return id;
	}

	public String getPwd() {
		return pwd;
	}

	public String getSido() {
		return sido;
	}

	public String getDong() {
		return dong;
	}

	public String getRsid() {
		return rsid;
	}

	public String getName() {
		return name;
	}

}
