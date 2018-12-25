package kr.ac.cbnu.computerengineering.lecture1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DongA extends AbsCrawler{
	protected String crawlingContents() {
		String ret="";
		try{
			
			String urlstr = "http://bizn.donga.com/car/3/all/20170904/86157740/2";
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        boolean start = false;
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		        while((line=bf.readLine())!=null){
		            if(line.contains("class=\"article_txt\"") == true) {
		            	start = true;
		            }
		            if(start == true) {
		            	ret += line;
		            }
		            if(line.contains("@donga.com") == true)
		            	break;
		        }
		        bf.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
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
			String urlstr = " http://bizn.donga.com/car/3/all/20170904/86157740/2";
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        boolean start = false;
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		        while((line=bf.readLine())!=null){
		            if(start == false && line.contains("class=\"article_title\"") == true) {
		            	start = true;
		            }
		            if(start == true && line.contains("class='repoter'") == true)
		            	break;
		            if(start == true)
		            	ret += line;
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
			String urlstr = " http://bizn.donga.com/car/3/all/20170904/86157740/2";
		        URL url = new URL(urlstr);
		        BufferedReader bf;
		        String line;
		        bf = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		        while((line=bf.readLine())!=null){
		            if(line.contains("class='date'") == true) {
		            	ret += line;
		            	break;
		            }
		        }
		        bf.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
		ret = ret.replace("\t", "");
		ret = ret.replace("입력", "");
		ret = ret.replace("수정", "");
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
			date = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss").parse(ret);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
