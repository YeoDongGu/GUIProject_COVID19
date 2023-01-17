package frame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class TabImage {
	ImageIcon covid = new ImageIcon(MainFrame.class.getResource("/image/covid.png"));
	ImageIcon hos = new ImageIcon(MainFrame.class.getResource("/image/hos.png"));
	ImageIcon dust = new ImageIcon(MainFrame.class.getResource("/image/dust.png"));
	ImageIcon news = new ImageIcon(MainFrame.class.getResource("/image/news.png"));

	public TabImage(int x, int y) {
		Image img = covid.getImage();
		Image updatecovid = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		covid = new ImageIcon(updatecovid);

		Image img2 = hos.getImage();
		Image updatehos = img2.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		hos = new ImageIcon(updatehos);

		Image img3 = dust.getImage();
		Image updatedust = img3.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		dust = new ImageIcon(updatedust);

		Image img4 = news.getImage();
		Image updatenews = img4.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		news = new ImageIcon(updatenews);
	}
}
