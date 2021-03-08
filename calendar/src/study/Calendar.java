package study;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Calendar {

	private static final int[] maxDays = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] leapMaxDays = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static String saveFile = "calendar.date";

	private HashMap<Date, String> planMap;

	public Calendar() {
		// TODO Auto-generated constructor stub
		planMap = new HashMap<Date, String>();
	}

	// 윤년 확인하는 기능 메소드
	public boolean isLeapYear(int year) {
		if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	// 달력 출력하는 기능 메소드
	public void printCalendar(int year, int month) {

		System.out.println("<<" + year + "년 " + month + "월>>");
		System.out.println("SU MO TU WE TH FR SA");
		System.out.println("-------------------");

		// 윤년 체크
		int maxDays;

		// 윤년 여부 적용
		maxDays = getMaxDayOfMonth(year, month);

		// 해당 월에 맞는 일수 출력
		for (int i = 1; i < getWeekDay(year, month, 1); i++) {
			System.out.print(" ");
		}

		int dayCount = getWeekDay(year, month, 1);

		for (int i = 1; i < maxDays + 1; i++) {
			System.out.print(i + " ");
			dayCount++;
			if (dayCount % 7 == 0) {
				System.out.println();
				continue;
			}
		}
	}

	private int getMaxDayOfMonth(int year, int month) {
		if (isLeapYear(year)) {
			return leapMaxDays[month];
		} else {
			return maxDays[month];
		}
	}

	// 달력 화면 출력
	public void setMonth() {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		boolean monthRun = true;
		int year = 0, month = 0;
		System.out.println("======= 달력 =======");
		while (run) {
			System.out.print("년도(1970~2021) : ");
			year = sc.nextInt();
			// 년도를 1970이상 2021년 이하로 입력했는지 확인하는 선택문
			if (year > 1969 && year < 2022) {
				run = false;
			} else {
				// 년도 맞게 입력 하지 않았을 때 되돌아가게 한다.
				System.out.println("년도를 다시 입력하여 주세요.");
				continue;
			}
		}
		while (monthRun) {
			System.out.print("월 : ");
			month = sc.nextInt();
			// 월을 1이상 12이하로 입력했는지 확인하는 선택문
			if (month > 0 && month < 13) {
				// 맞게 입력 하였을 때
				monthRun = false;
			} else {
				// 틀리게 입력 하였을 때
				System.out.println("월을 다시 입력하여 주세요.");
				continue;
			}
		}
		printCalendar(year, month);

	}

	private int getWeekDay(int year, int month, int day) {
		int sYear = 1970;
		final int STANDARD_WEEKDAY = 3; // 1970년/1월/1일 = 목요일

		int count = 0;
		for (int i = sYear; i < year; i++) {
			int delta = isLeapYear(i) ? 366 : 365;
			count += delta;
		}
		for (int i = 1; i < month; i++) {
			int delta = getMaxDayOfMonth(year, i);
			count += delta;
		}
		count += day;

		int weekDay = (count + STANDARD_WEEKDAY) % 7;

		return weekDay;
	}

	// 일정 등록 기능 메소드
	public void register(String enterDate, String plan) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(enterDate);
		System.out.println(date);
		planMap.put(date, plan);
		// 파일 IO로 저장하기
		saveFile = enterDate;
		File f = new File(saveFile);
		if (!f.exists()) {
			// 파일이 존재하지 않으면
			String content = enterDate + "\n" + plan;
			System.out.println(content);
			try {
				FileWriter fw = new FileWriter(f, true);
				fw.write(content);
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

		}

	}

	// 일정 검색 기능 메소드
	public void searchPlan(String enterDate) throws ParseException {
		int end = 0;

		File f = new File(enterDate);
		char[] contentChar = new char[100];
		String[] content = null;
		if (!f.exists()) {
			System.out.println("해당 날짜에 일정이 없습니다.");
			return;
		}

		try {
			FileReader fr = new FileReader(f);
			while ((end = fr.read(contentChar)) != -1) {
				String data = new String(contentChar, 0, end);
				content = data.split("\n");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < content.length; i++) {
			if (i == 0) {
				System.out.println("날짜 : " + content[i]);
			} else if (i == 1) {
				System.out.println("일정내용 : " + content[i]);
			}
		}
	}

}
