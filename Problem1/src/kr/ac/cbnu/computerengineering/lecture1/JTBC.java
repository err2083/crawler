package kr.ac.cbnu.computerengineering.lecture1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JTBC extends AbsCrawler{
	protected String crawlingContents() {
		String ret="";
		try{
			String urlstr = "http://news.jtbc.joins.com/article/article.aspx?news_id=NB11258222";
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        boolean start = false;
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		        while((line=bf.readLine())!=null){
		            if(start == false && line.contains("class=\"article_content\"") == true) {
		            	start = true;
		            }
		            if(start == true) {
		            	ret += line;
		            }
		            if(start == true && line.contains("class=\"journalist_movie_area journalist_movie_area_v2\"") == true)
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
	protected String crawlingTitle() {
		String ret="";
		try{
			String urlstr = "http://news.jtbc.co.kr/html/222/NB11258222.html";
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        boolean start = false;
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		        while((line=bf.readLine())!=null){
		            if(start == false && line.contains("class=\"title\"") == true) {
		            	start = true;
		            }
		            if(start == true)
		            	ret += line;
		            if(start == true && line.contains("id=\"jtbcBody\"") == true)
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
			String urlstr = "http://news.jtbc.co.kr/html/222/NB11258222.html";
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		        while((line=bf.readLine())!=null){
		            if(line.contains("class=\"i_date\"") == true) {
		            	ret += line;
		            	break;
		            }
		        }
		        bf.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
		ret = ret.replace("\t", "");
		ret = ret.replace("ют╥б","");
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
			date = new SimpleDateFormat("YYYY-MM-DD hh:mm").parse(ret);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
