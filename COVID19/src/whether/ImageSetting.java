package whether;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageSetting {
	ImageIcon badIcon;
	ImageIcon bad2Icon;
	ImageIcon goodIcon;
	ImageIcon good2Icon;
	ImageIcon nullIcon;

	ImageIcon bad = new ImageIcon(WhetherContent.class.getResource("/image/bad.png"));
	ImageIcon bad2 = new ImageIcon(WhetherContent.class.getResource("/image/bad4.png"));
	ImageIcon good = new ImageIcon(WhetherContent.class.getResource("/image/default.png"));
	ImageIcon good2 = new ImageIcon(WhetherContent.class.getResource("/image/good3.png"));
	ImageIcon nullimg = new ImageIcon(WhetherContent.class.getResource("/image/!.png"));

	public ImageSetting() {
		super();
	}

	public void setImg(int x, int y) {
		Image img = bad.getImage();
		Image updatebad = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		badIcon = new ImageIcon(updatebad);

		Image img2 = bad2.getImage();
		Image updatebad2 = img2.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		bad2Icon = new ImageIcon(updatebad2);

		Image img3 = good.getImage();
		Image updategood = img3.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		goodIcon = new ImageIcon(updategood);

		Image img4 = good2.getImage();
		Image updategood2 = img4.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		good2Icon = new ImageIcon(updategood2);

		Image img5 = nullimg.getImage();
		Image updatenull = img5.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		nullIcon = new ImageIcon(updatenull);
	}
}
