package calamityAPI;

public class Calamity {
	String create_date; // 문자 생성일시
	String location_name; // 수신지역 이름
	String msg; // 내용

	public Calamity(String create_date, String location_name, String msg) {
		this.create_date = create_date;
		this.location_name = location_name;
		this.msg = msg;
	}
}