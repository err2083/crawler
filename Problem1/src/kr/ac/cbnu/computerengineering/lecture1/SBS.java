package kr.ac.cbnu.computerengineering.lecture1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SBS extends AbsCrawler{
	@Override
	protected String crawlingContents() {
		String ret="";
		try{
			String urlstr = "http://news.sbs.co.kr/news/%20endPage.do?news_id=N1002950068&plink=ORI&cooper=NAVER"; // sbs 주소
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        boolean start = false;	//어디부터인지 지정하는 변수
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));	//한글입력 받기위해
		        while((line=bf.readLine())!=null){
		            if(start == false && line.contains("class=\"article_cont_area") == true) {	//문맥시작 문자열
		            	start = true;
		            }
		            if(start == true) {	//계속 더해줌
		            	ret += line;
		            }
		            if(start == true && line.contains("(SBS 뉴미디어부)") == true) //sbs기사 마지막에 들어가는 말
		            	break;
		        }
		        bf.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
		ret = ret.replace("\t", "");		//sbs는 각종 따움표 같은게 저렇게 변환되서 표시되서 다시 변환해줌
		ret = ret.replace("&#39;","'"); //' look
		ret = ret.replace("&quot;","'"); // " look
		ret = ret.replace("&hellip;","..."); // ... look
		ret = ret.replace("&middot;","·"); // middot look
		ret = ret.replace("&nbsp;","\n"); // &nbsp;
		boolean deletev = false;
		String temp = "";
		for(int i=0;i<ret.length();i++) {		// <--> 인 것들은 전부 상관없으므로 제거
			if(deletev == false && ret.charAt(i) == '<') deletev = true;
			if(deletev == true) temp = temp + ret.charAt(i);
			if(deletev == true && ret.charAt(i) == '>') {
				ret = ret.replace(temp,"");
				deletev = false;
				i=-1;
				temp = "";
			}
		}
		return ret;
	}
	@Override
	protected String crawlingTitle() {
		String ret="";
		try{
			String urlstr = "http://news.sbs.co.kr/news/%20endPage.do?news_id=N1002950068&plink=ORI&cooper=NAVER";
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        boolean start = false;
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		        while((line=bf.readLine())!=null){
		            if(start == false && line.contains("id=\"vmNewsTitle\"") == true) {
		            	start = true;
		            }
		            if(start == true) {
		            	ret += line;
		            }
		            if(start == true && line.contains("<!-- 소제목 / 없으면 비노출 -->") == true)
		            	break;
		        }
		        bf.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
		ret = ret.replace("\t", "");
		boolean deletev = false;
		String temp = "";
		for(int i=0;i<ret.length();i++) {
			if(deletev == false && ret.charAt(i) == '<') deletev = true;
			if(deletev == true) temp = temp + ret.charAt(i);
			if(deletev == true && ret.charAt(i) == '>') {
				ret = ret.replace(temp,"");
				deletev = false;
				i=-1;
				temp = "";
			}
		}
		return ret;
	}
	@Override
	protected Date crawlingData() {
		String ret="";
		try{
			String urlstr = "http://news.sbs.co.kr/news/%20endPage.do?news_id=N1002950068&plink=ORI&cooper=NAVER";
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        boolean start = false;
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		        while((line=bf.readLine())!=null){
		            if(start == false && line.contains("class=\"date\"") == true) {
		            	start = true;
		            }
		            if(start == true) {
		            	ret += line;
		            }
		            if(start == true && line.contains("수정") == true)
		            	break;
		        }
		        bf.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
		ret = ret.replace("\t", "");
		ret = ret.replace("작성","");
		ret = ret.replace("수정","");
		boolean deletev = false;
		String temp = "";
		for(int i=0;i<ret.length();i++) {
			if(deletev == false && ret.charAt(i) == '<') deletev = true;
			if(deletev == true) temp = temp + ret.charAt(i);
			if(deletev == true && ret.charAt(i) == '>') {
				ret = ret.replace(temp,"");
				deletev = false;
				i=-1;
				temp = "";
			}
		}
		Date date = null;
		try {
			date = new SimpleDateFormat("YYYY.MM.DD hh:mm").parse(ret);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
