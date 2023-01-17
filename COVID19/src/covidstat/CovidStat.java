package covidstat;

public class CovidStat {
	private String gubun; // 시도명
	private String incDec; // 전일대비 확진자 증감수
	private String localOccCnt; // 지역발생수
	private String overFlowCnt; // 해외유입수
	private String qurRate; // 10만명당 발생수
	private String stdDay; // 기준일자

	public CovidStat(String gubun, String incDec, String localOccCnt, String overFlowCnt, String qurRate,
			String stdDay) {
		this.gubun = gubun;
		this.incDec = incDec;
		this.localOccCnt = localOccCnt;
		this.overFlowCnt = overFlowCnt;
		this.qurRate = qurRate;
		this.stdDay = stdDay;
	}

	public String getGubun() {
		return gubun;
	}

	public String getIncDec() {
		return incDec;
	}

	public String getLocalOccCnt() {
		return localOccCnt;
	}

	public String getOverFlowCnt() {
		return overFlowCnt;
	}

	public String getQurRate() {
		return qurRate;
	}

	public String getStdDay() {
		return stdDay;
	}
	
}
