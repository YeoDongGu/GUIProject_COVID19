package hospital;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GetMap extends JFrame {
	private static final long serialVersionUID = 1L;
	private GoogleAPI gAPI = new GoogleAPI();
	private String location;
	private JLabel gMap;
	private JLabel maker;

	ImageIcon makerimgIcon;

	ImageIcon makerimg = new ImageIcon(GetMap.class.getResource("/image/maker.png"));

	public GetMap(String loc) {
		this.location = loc;
		gAPI.downloadMap(location);
		gMap = new JLabel(gAPI.getMap(location));
		gMap.setBounds(0, 0, 612, 612);
		gAPI.fileDelete(location);

		setImg();
		maker = new JLabel(makerimgIcon);
		maker.setBounds(306, 306, 40, 40);

		add(gMap);
		add(maker);
		setTitle("Google maps");
		setLayout(null);
		setSize(612, 612);
		setVisible(true);
	}

	public void setImg() {
		Image img = makerimg.getImage();
		Image updatemaker = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		makerimgIcon = new ImageIcon(updatemaker);
	}

}
