package kr.ac.cbnu.computerengineering.lecture1;

public class PressDataCollector {
	AbsCrawler sbs = new SBS();
	AbsCrawler jtbc = new JTBC();
	AbsCrawler donga = new DongA();
	AbsCrawler joongang = new JoongAng();
	AbsCrawler hankyoreh = new HanKyoReh();
	DataManager my_manager = new DataManager();
	
	public void collectPressData() {
		PressDatatype sbsdata = sbs.doCrawling();
		PressDatatype jtbcdata = jtbc.doCrawling();
		PressDatatype dongadata = donga.doCrawling();
		PressDatatype joongangdata = joongang.doCrawling();
		PressDatatype hankyorehdata = hankyoreh.doCrawling();
		my_manager.saveData(hankyorehdata, "D:\\webdata_������.txt");
		my_manager.saveData(sbsdata, "D:\\webdata_������.txt");
		my_manager.saveData(jtbcdata, "D:\\webdata_������.txt");
		my_manager.saveData(joongangdata, "D:\\webdata_������.txt");
		my_manager.saveData(dongadata, "D:\\webdata_������.txt");
	}
}
