package whether;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import utillclass.ConnDB;

public class WhetherContent {
	ConnDB cd = new ConnDB();
	ImageSetting imgset = new ImageSetting();
	JPanel ctp;
	JPanel pm10;
	JPanel pm25;
	JPanel no2;
	JPanel o3;

	JLabel sido;
	JLabel dtlabel;
	
	JLabel mainpm10imglabel;
	JLabel pm10imglabel;
	JLabel pm25imglabel;
	JLabel no2imglabel;
	JLabel o3imglabel;
	JLabel coimglabel;
	JLabel so2imglabel;

	String sidonm;
	String stationname;
	String pm10value;
	String pm25value;
	String no2value;
	String o3value;
	String covalue;
	String so2value;
	String datatime;
	String pm10Grade;
	String pm25Grade;
	String no2Grade;
	String o3Grade;
	String coGrade;
	String so2Grade;

	public WhetherContent(String stnm) throws SQLException {
		String sql = "select sidonm, stationname, pm10value, pm25value,no2value, o3value, covalue, so2value, pm10Grade, pm25Grade, no2Grade, o3Grade, coGrade, so2Grade, datatime from whether where stationname like ?";
		cd.pstmt = cd.con.prepareStatement(sql);
		cd.pstmt.setString(1, stnm + "%");
		cd.rs = cd.pstmt.executeQuery();
		while (cd.rs.next()) {
			sidonm = cd.rs.getString("sidonm");
			stationname = cd.rs.getString("stationname");
			pm10value = cd.rs.getString("pm10value");
			pm25value = cd.rs.getString("pm25value");
			no2value = cd.rs.getString("no2value");
			o3value = cd.rs.getString("o3value");
			covalue = cd.rs.getString("covalue");
			so2value = cd.rs.getString("so2value");
			pm10Grade = cd.rs.getString("pm10Grade");
			pm25Grade = cd.rs.getString("pm25Grade");
			no2Grade = cd.rs.getString("no2Grade");
			o3Grade = cd.rs.getString("o3Grade");
			coGrade = cd.rs.getString("coGrade");
			so2Grade = cd.rs.getString("so2Grade");
			datatime = cd.rs.getString("datatime");
		}

		ctp = new JPanel();
		ctp.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				sidonm + " " + stationname + " 대기오염 정보", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("d", Font.BOLD, 17), new Color(0, 0, 0)));
		ctp.setLayout(null);
		ctp.setBounds(15, 85, 565, 550);
		ctp.setBackground(Color.white);

		pm10 = new JPanel();
		pm10.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), " 미세먼지 ",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("d", Font.BOLD, 17), new Color(0, 0, 0)));
		pm10.setLayout(null);
		pm10.setBounds(160, 55, 270, 250);
		pm10.setBackground(Color.white);
		ctp.add(pm10);

		pm25 = new JPanel();
		pm25.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), " 초미세먼지 ",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("d", Font.BOLD, 13), new Color(0, 0, 0)));
		pm25.setBounds(30, 320, 140, 160);
		pm25.setLayout(null);
		pm25.setBackground(Color.white);
		ctp.add(pm25);

		no2 = new JPanel();
		no2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), " 이산화질소 ",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("d", Font.BOLD, 13), new Color(0, 0, 0)));
		no2.setBounds(210, 320, 140, 160);
		no2.setLayout(null);
		no2.setBackground(Color.white);
		ctp.add(no2);

		o3 = new JPanel();
		o3.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), " 오존 ",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("d", Font.BOLD, 13), new Color(0, 0, 0)));
		o3.setBounds(390, 320, 140, 160);
		o3.setLayout(null);
		o3.setBackground(Color.white);
		ctp.add(o3);

		sido = new JLabel();
		sido.setBounds(230, 0, 300, 50);
		sido.setFont(new Font("f", Font.BOLD, 18));
		ctp.add(sido);

		// 측정시간 라벨
		dtlabel = new JLabel("측정시간 : " + datatime);
		dtlabel.setBounds(20, 30, 300, 30);
		dtlabel.setFont(new Font("1", Font.BOLD, 15));
		ctp.add(dtlabel);

		// 메인 미세먼지 정보
		imgset.mainimage();
		mainpm10imglabel = new JLabel();
		mainpm10imglabel.setBounds(60, 20, 150, 150);
		mainpm10imglabel.setHorizontalAlignment(JLabel.CENTER);
		pm10.add(mainpm10imglabel);

		mainpm10imglabel.setIcon(imgset.goodIcon);
		JLabel grade = new JLabel();
		JLabel ment = new JLabel();
		JLabel value = new JLabel(pm10value + " ug/m");
		grade.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
		value.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
		ment.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
//		mgood.setForeground(Color.cyan);
//		mgood2.setForeground(Color.cyan);
		grade.setFont(new Font("1", Font.BOLD, 20));
		ment.setFont(new Font("1", Font.BOLD, 20));
		value.setFont(new Font("1", Font.BOLD, 20));
		grade.setBounds(30, 170, 200, 30);
		ment.setBounds(30, 220, 200, 30);
		value.setBounds(30, 195, 200, 30);
		pm10.add(ment);
		pm10.add(value);
		pm10.add(grade);

		if (pm10Grade.charAt(0) == '1') {
			mainpm10imglabel.setIcon(imgset.good2Icon);
			grade.setText("좋음");
			ment.setText("환기하기 좋은날!");
		} else if (pm10Grade.charAt(0) == '2') {
			mainpm10imglabel.setIcon(imgset.goodIcon);
			grade.setText("보통");
			ment.setText("외출시 마스크 착용!");
		} else if (pm10Grade.charAt(0) == '3') {
			mainpm10imglabel.setIcon(imgset.badIcon);
			grade.setText("나쁨");
			ment.setText("외출은 자제해요~");
		} else if (pm10Grade.charAt(0) == '4') {
			mainpm10imglabel.setIcon(imgset.bad2Icon);
			grade.setText("매우나쁨");
			ment.setText("절대 나가지마세요!");
		} else {
			mainpm10imglabel.setIcon(imgset.nullIcon);
			ment.setText("정보가 없습니다");
		}

		// 서브 대기오염정보 3개
		imgset.subimage();
		pm25imglabel = new JLabel();
		pm25imglabel.setBounds(35, 20, 70, 70);
		pm25imglabel.setHorizontalAlignment(JLabel.CENTER);
		pm25.add(pm25imglabel);

		JLabel grade2 = new JLabel();
		JLabel value2 = new JLabel(pm25value + " ug/m");
		grade2.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
		value2.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
		grade2.setFont(new Font("1", Font.BOLD, 13));
		value2.setFont(new Font("1", Font.BOLD, 13));
		grade2.setBounds(20, 100, 100, 20);
		value2.setBounds(20, 115, 100, 20);

		pm25.add(value2);
		pm25.add(grade2);

		if (pm25Grade.charAt(0) == '1') {
			pm25imglabel.setIcon(imgset.good2Icon);
			grade2.setText("좋음");
		} else if (pm25Grade.charAt(0) == '2') {
			pm25imglabel.setIcon(imgset.goodIcon);
			grade2.setText("보통");
		} else if (pm25Grade.charAt(0) == '3') {
			pm25imglabel.setIcon(imgset.badIcon);
			grade2.setText("나쁨");
		} else if (pm25Grade.charAt(0) == '4') {
			pm25imglabel.setIcon(imgset.bad2Icon);
			grade2.setText("매우나쁨");
		} else {
			pm25imglabel.setIcon(imgset.nullIcon);
			grade2.setText("없음");
		}

		no2imglabel = new JLabel();
		no2imglabel.setBounds(35, 20, 70, 70);
		no2imglabel.setHorizontalAlignment(JLabel.CENTER);
		no2.add(no2imglabel);

		JLabel grade3 = new JLabel();
		JLabel value3 = new JLabel(no2value + " ug/m");
		grade3.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
		value3.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
		grade3.setFont(new Font("1", Font.BOLD, 13));
		value3.setFont(new Font("1", Font.BOLD, 13));
		grade3.setBounds(20, 100, 100, 20);
		value3.setBounds(20, 115, 100, 20);

		no2.add(value3);
		no2.add(grade3);

		if (no2Grade.charAt(0) == '1') {
			no2imglabel.setIcon(imgset.good2Icon);
			grade3.setText("좋음");
		} else if (no2Grade.charAt(0) == '2') {
			no2imglabel.setIcon(imgset.goodIcon);
			grade3.setText("보통");
		} else if (no2Grade.charAt(0) == '3') {
			no2imglabel.setIcon(imgset.badIcon);
			grade3.setText("나쁨");
		} else if (no2Grade.charAt(0) == '4') {
			no2imglabel.setIcon(imgset.bad2Icon);
			grade3.setText("매우나쁨");
		} else {
			no2imglabel.setIcon(imgset.nullIcon);
			grade3.setText("없음");
		}

		o3imglabel = new JLabel();
		o3imglabel.setBounds(35, 20, 70, 70);
		o3imglabel.setHorizontalAlignment(JLabel.CENTER);
		o3.add(o3imglabel);

		JLabel grade4 = new JLabel();
		JLabel value4 = new JLabel(o3value + " ug/m");
		grade4.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
		value4.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
		grade4.setFont(new Font("1", Font.BOLD, 13));
		value4.setFont(new Font("1", Font.BOLD, 13));
		grade4.setBounds(20, 100, 100, 20);
		value4.setBounds(20, 115, 100, 20);

		o3.add(value4);
		o3.add(grade4);

		if (o3Grade.charAt(0) == '1') {
			o3imglabel.setIcon(imgset.good2Icon);
			grade4.setText("좋음");
		} else if (o3Grade.charAt(0) == '2') {
			o3imglabel.setIcon(imgset.goodIcon);
			grade4.setText("보통");
		} else if (o3Grade.charAt(0) == '3') {
			o3imglabel.setIcon(imgset.badIcon);
			grade4.setText("나쁨");
		} else if (o3Grade.charAt(0) == '4') {
			o3imglabel.setIcon(imgset.bad2Icon);
			grade4.setText("매우나쁨");
		} else {
			o3imglabel.setIcon(imgset.nullIcon);
			grade4.setText("없음");
		}

	}

}
