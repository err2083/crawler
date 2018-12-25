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
			String urlstr = "http://news.sbs.co.kr/news/%20endPage.do?news_id=N1002950068&plink=ORI&cooper=NAVER"; // sbs �ּ�
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        boolean start = false;	//���������� �����ϴ� ����
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));	//�ѱ��Է� �ޱ�����
		        while((line=bf.readLine())!=null){
		            if(start == false && line.contains("class=\"article_cont_area") == true) {	//���ƽ��� ���ڿ�
		            	start = true;
		            }
		            if(start == true) {	//��� ������
		            	ret += line;
		            }
		            if(start == true && line.contains("(SBS ���̵���)") == true) //sbs��� �������� ���� ��
		            	break;
		        }
		        bf.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
		ret = ret.replace("\t", "");		//sbs�� ���� ����ǥ ������ ������ ��ȯ�Ǽ� ǥ�õǼ� �ٽ� ��ȯ����
		ret = ret.replace("&#39;","'"); //' look
		ret = ret.replace("&quot;","'"); // " look
		ret = ret.replace("&hellip;","..."); // ... look
		ret = ret.replace("&middot;","��"); // middot look
		ret = ret.replace("&nbsp;","\n"); // &nbsp;
		boolean deletev = false;
		String temp = "";
		for(int i=0;i<ret.length();i++) {		// <--> �� �͵��� ���� ��������Ƿ� ����
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
		            if(start == true && line.contains("<!-- ������ / ������ ����� -->") == true)
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
		            if(start == true && line.contains("����") == true)
		            	break;
		        }
		        bf.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
		ret = ret.replace("\t", "");
		ret = ret.replace("�ۼ�","");
		ret = ret.replace("����","");
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
