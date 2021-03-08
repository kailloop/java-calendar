package study;

import java.text.ParseException;
import java.util.Scanner;

public class Front {
	Calendar cal = new Calendar();

	public void printMenu() throws ParseException {
		Scanner sc = new Scanner(System.in);

		boolean run = true;
		while (run) {
			System.out.println();
			System.out.println("*------------달력--프로그램------------*");
			System.out.println("1. 일정 등록");
			System.out.println("2. 일정 검색");
			System.out.println("3. 달력 보기");
			System.out.println("h. 도움말 q. 종료");
			System.out.println("*--------------------------*");
			System.out.print("입력 : ");

			String cmd = sc.nextLine();
			if (cmd.equals("1")) {
				register();
			} else if (cmd.equals("2")) {
				searchPlan();
			} else if (cmd.equals("3")) {
				cal.setMonth();
			} else if (cmd.equals("h")) {

			} else if (cmd.equals("q")) {
				break;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
		System.out.println("달력 일정 프로그램을 종료합니다.");

	}

	public void register() throws ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("일정을 등록 할 날짜를 입력합니다.");
		System.out.print("년 : ");
		String year = sc.nextLine();
		System.out.print("월 : ");
		String month = sc.nextLine();
		System.out.print("일 : ");
		String day = sc.nextLine();

		String result = year + "-" + month + "-" + day;

		System.out.print("일정 내용을 적어주세요 : ");
		String plan = sc.nextLine();
		cal.register(result, plan);
	}

	public void searchPlan() throws ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("검색 할 일정의 날짜를 입력 하세요.");
		String date = "";
		System.out.print("년 : ");
		date += sc.nextLine();
		date += "-";
		System.out.print("월 : ");
		date += sc.nextLine();
		date += "-";
		System.out.print("일 : ");
		date += sc.nextLine();

		System.out.println("입력하신 날짜의 일정입니다.");
		cal.searchPlan(date);
		
	}

}
