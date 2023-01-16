package frame;

public class SignupVo {
	private String id;
	private String pwd;
	private String resi;
	private String name;
	private String sido;
	private String dong;

	public SignupVo() {
		
	}
	public SignupVo(String id, String pwd, String resi, String name, String sido, String dong) {
		this.id = id;
		this.pwd = pwd;
		this.resi = resi;
		this.name = name;
		this.sido = sido;
		this.dong = dong;
	}

	public String getId() {
		return id;
	}

	public String getPwd() {
		return pwd;
	}

	public String getResi() {
		return resi;
	}

	public String getName() {
		return name;
	}

	public String getSido() {
		return sido;
	}

	public String getDong() {
		return dong;
	}

}
