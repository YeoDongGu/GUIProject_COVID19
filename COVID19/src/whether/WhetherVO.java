package whether;

public class WhetherVO {
	String sidoName;
	String stationName;
	String pm10Value; // 미세먼지 농도
	String pm25Value; // 초미세먼지 농도
	String no2Value; // 이산화질소 농도
	String o3Value; // 오존 농도
	String coValue; // 일산화탄소 농도
	String so2Value; // 아황산가스 농도
	String pm10Grade; // 미세먼지 등급
	String pm25Grade; // 초미세먼지 등급
	String no2Grade; // 이산화질소 등급
	String o3Grade; // 오존 등급
	String coGrade; // 일산화탄소 등급
	String so2Grade; // 아황산가스 등급
	String dataTime; // 측정 시간

	public WhetherVO() {
		super();
	}

	public WhetherVO(String sidoName, String stationName, String pm10Value, String pm25Value, String no2Value,
			String o3Value, String coValue, String so2Value, String pm10Grade, String pm25Grade, String no2Grade,
			String o3Grade, String coGrade, String so2Grade, String dataTime) {
		this.sidoName = sidoName;
		this.stationName = stationName;
		this.pm10Value = pm10Value;
		this.pm25Value = pm25Value;
		this.no2Value = no2Value;
		this.o3Value = o3Value;
		this.coValue = coValue;
		this.so2Value = so2Value;
		this.pm10Grade = pm10Grade;
		this.pm25Grade = pm25Grade;
		this.no2Grade = no2Grade;
		this.o3Grade = o3Grade;
		this.coGrade = coGrade;
		this.so2Grade = so2Grade;
		this.dataTime = dataTime;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public void setPm10Value(String pm10Value) {
		this.pm10Value = pm10Value;
	}

	public void setPm25Value(String pm25Value) {
		this.pm25Value = pm25Value;
	}

	public void setNo2Value(String no2Value) {
		this.no2Value = no2Value;
	}

	public void setO3Value(String o3Value) {
		this.o3Value = o3Value;
	}

	public void setCoValue(String coValue) {
		this.coValue = coValue;
	}

	public void setSo2Value(String so2Value) {
		this.so2Value = so2Value;
	}

	public void setPm10Grade(String pm10Grade) {
		this.pm10Grade = pm10Grade;
	}

	public void setPm25Grade(String pm25Grade) {
		this.pm25Grade = pm25Grade;
	}

	public void setNo2Grade(String no2Grade) {
		this.no2Grade = no2Grade;
	}

	public void setO3Grade(String o3Grade) {
		this.o3Grade = o3Grade;
	}

	public void setCoGrade(String coGrade) {
		this.coGrade = coGrade;
	}

	public void setSo2Grade(String so2Grade) {
		this.so2Grade = so2Grade;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getSidoName() {
		return sidoName;
	}

	public String getStationName() {
		return stationName;
	}

	public String getPm10Value() {
		return pm10Value;
	}

	public String getPm25Value() {
		return pm25Value;
	}

	public String getNo2Value() {
		return no2Value;
	}

	public String getO3Value() {
		return o3Value;
	}

	public String getCoValue() {
		return coValue;
	}

	public String getSo2Value() {
		return so2Value;
	}

	public String getPm10Grade() {
		return pm10Grade;
	}

	public String getPm25Grade() {
		return pm25Grade;
	}

	public String getNo2Grade() {
		return no2Grade;
	}

	public String getO3Grade() {
		return o3Grade;
	}

	public String getCoGrade() {
		return coGrade;
	}

	public String getSo2Grade() {
		return so2Grade;
	}

	public String getDataTime() {
		return dataTime;
	}
	
}
