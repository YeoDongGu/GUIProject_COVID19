package hospital;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GetMap{
	private GoogleAPI gAPI = new GoogleAPI();
	private JFrame gmf = new JFrame();;
	private String location;
	private JLabel gMap;


	public GetMap(String loc) {
		this.location = loc;
		gAPI.downloadMap(location);
		gMap = new JLabel(gAPI.getMap(location));
		gMap.setBounds(0, 0, 612, 612);
		gAPI.fileDelete(location);


		gmf.add(gMap);
		gmf.setTitle("Google maps");
		gmf.setLayout(null);
		gmf.setSize(612, 612);
		gmf.setVisible(true);
	}

}
