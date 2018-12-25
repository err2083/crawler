package kr.ac.cbnu.computerengineering.lecture1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HanKyoReh extends AbsCrawler{
	protected String crawlingContents() {
		String ret="";
		try{
			
			String urlstr = "http://www.hani.co.kr/arti/economy_general/770364.html";
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        boolean start = false;
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		        while((line=bf.readLine())!=null){
		            if(line.contains("<div class=\"article-text\">") == true) {
		            	start = true;
		            }
		            if(start == true) {
		            	ret += line;
		            }
		            if(line.contains("@hani.co.kr") == true)
		            	break;
		        }
		        bf.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
		ret = ret.replace("\t","");
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
			String urlstr = "http://www.hani.co.kr/arti/economy_general/770364.html";
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		        while((line=bf.readLine())!=null){
		            if(line.contains("<span class=\"title\">") == true) {
		            	ret += line;
		            	break;
		            }
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
			String urlstr = "http://www.hani.co.kr/arti/economy_general/770364.html";
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		        while((line=bf.readLine())!=null){
		            if(line.contains("class=\"date-time\"") == true) {
		            	ret += line;
		            	break;
		            }
		        }
		        bf.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
		ret = ret.replace("\t", "");
		ret = ret.replace("등록 :", "");
		ret = ret.replace("수정 :", "");
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
