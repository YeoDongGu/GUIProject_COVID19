package covidstat;

public class CovidStat {
	String gubun; // 시도명
	String incDec; // 전일대비 확진자 증감수
	String localOccCnt; // 지역발생수
	String overFlowCnt; // 해외유입수
	String qurRate; // 10만명당 발생수
	String stdDay; // 기준일자

	public CovidStat(String gubun, String incDec, String localOccCnt, String overFlowCnt, String qurRate,
			String stdDay) {
		this.gubun = gubun;
		this.incDec = incDec;
		this.localOccCnt = localOccCnt;
		this.overFlowCnt = overFlowCnt;
		this.qurRate = qurRate;
		this.stdDay = stdDay;
	}
}
