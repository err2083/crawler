package kr.ac.cbnu.computerengineering.lecture1;

public class Main {
	
	/*
	 * 크롤링을 돌리는 부분은 어려움없이 했는데, 오히려 객체구조를 만드는것에서 많이 막힌것같다.
	 */
	public static void main(String[] args) {
		PressDataCollector my_collector = new PressDataCollector();
		my_collector.collectPressData();
	}
}
