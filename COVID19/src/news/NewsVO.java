package news;

public class NewsVO {
	String title; // 뉴스기사 제목
	String originallink; // 뉴스 기사 원문 url
	String link; // 뉴스 기사 url
	String description; // 뉴스기사를 요약한 메시지 정보
	String pubDate; // 뉴스 기사가 네이버에 제공된 시간

	public NewsVO(String title, String originallink, String link, String description, String pubDate) {
		this.title = title;
		this.originallink = originallink;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;

	}
}
