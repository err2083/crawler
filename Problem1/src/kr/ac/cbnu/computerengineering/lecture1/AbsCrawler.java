package kr.ac.cbnu.computerengineering.lecture1;
import java.util.Date;

public abstract class AbsCrawler {
	public PressDatatype doCrawling() {
		AbsCrawler my_crawler = null;
		PressDatatype data = null;
		if(this instanceof SBS)
			my_crawler = new SBS();
		else if(this instanceof JTBC)
			my_crawler = new JTBC();
		else if(this instanceof DongA)
			my_crawler = new DongA();
		else if(this instanceof JoongAng)
			my_crawler = new JoongAng();
		else if(this instanceof HanKyoReh)
			my_crawler = new HanKyoReh();
		else return data;
		data = new PressDatatype(my_crawler.crawlingData(),my_crawler.crawlingTitle(),my_crawler.crawlingContents());
		return data;
	}
	protected abstract String crawlingContents();
	protected abstract String crawlingTitle();
	protected abstract Date crawlingData();
}
