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

	// ���� Ȯ���ϴ� ��� �޼ҵ�
	public boolean isLeapYear(int year) {
		if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	// �޷� ����ϴ� ��� �޼ҵ�
	public void printCalendar(int year, int month) {

		System.out.println("<<" + year + "�� " + month + "��>>");
		System.out.println("SU MO TU WE TH FR SA");
		System.out.println("-------------------");

		// ���� üũ

		int maxDays;

		// ���� ���� ����
		maxDays = getMaxDayOfMonth(year, month);

		// �ش� �� ���� ���߱�

		// �ش� ���� �´� �ϼ� ���
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

	// ó�� ��� ȭ�� ��� ���� �Է��Ѵ�.
	public void setMonth() {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		boolean monthRun = true;
		int year = 0, month = 0;
		System.out.println("======= �޷� =======");
		while (run) {
			System.out.print("�⵵(1970~2021) : ");
			year = sc.nextInt();
			// �⵵�� 1970�̻� 2021�� ���Ϸ� �Է��ߴ��� Ȯ���ϴ� ���ù�
			if (year > 1969 && year < 2022) {
				run = false;
			} else {
				// �⵵ �°� �Է� ���� �ʾ��� �� �ǵ��ư��� �Ѵ�.
				System.out.println("�⵵�� �ٽ� �Է��Ͽ� �ּ���.");
				continue;
			}
		}

		while (monthRun) {
			System.out.print("�� : ");
			month = sc.nextInt();
			// ���� 1�̻� 12���Ϸ� �Է��ߴ��� Ȯ���ϴ� ���ù�
			if (month > 0 && month < 13) {
				// �°� �Է� �Ͽ��� ��
				monthRun = false;
			} else {
				// Ʋ���� �Է� �Ͽ��� ��
				System.out.println("���� �ٽ� �Է��Ͽ� �ּ���.");
				continue;
			}
		}

		printCalendar(year, month);

	}

	private int getWeekDay(int year, int month, int day) {
		int sYear = 1970;
		final int STANDARD_WEEKDAY = 3; // 1970��/1��/1�� = �����

		int count = 0;
		for (int i = sYear; i < year; i++) {
			int delta = isLeapYear(i) ? 366 : 365;
			count += delta;
		}

		// System.out.println(count);

		for (int i = 1; i < month; i++) {
			int delta = getMaxDayOfMonth(year, i);
			count += delta;
		}

		count += day;

		int weekDay = (count + STANDARD_WEEKDAY) % 7;

		return weekDay;
	}

	public void register(String enterDate, String plan) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(enterDate);
		System.out.println(date);
		planMap.put(date, plan);
		// ���� IO�� �����ϱ�
		saveFile = enterDate;
		File f = new File(saveFile);
		if(!f.exists()) {
			//������ �������� ������
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
		}else {
			
		}
		

	}

	public void searchPlan(String enterDate) throws ParseException {

		int end = 0;

		File f = new File(enterDate);
		char[] contentChar = new char[100];
		String[] content = null;
		if (!f.exists()) {
			System.out.println("�ش� ��¥�� ������ �����ϴ�.");
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
		for(int i=0; i<content.length;i++) {
			if(i==0) {
				System.out.println("��¥ : "+content[i]);
			}else if(i==1) {
				System.out.println("�������� : "+content[i]);
			}
			
		}

	}

}
