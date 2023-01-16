package hospital;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GetMap extends JFrame {
	private static final long serialVersionUID = 1L;
	private GoogleAPI gAPI = new GoogleAPI();
	private String location;
	private JLabel gMap;

	public GetMap(String loc) {
		this.location = loc;
		gAPI.downloadMap(location);
		gMap = new JLabel(gAPI.getMap(location));
		gAPI.fileDelete(location);
		add(gMap);
		setTitle("Google maps");
		setVisible(true);
		pack();
	}

}
