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
			System.out.println("*------------�޷�--���α׷�------------*");
			System.out.println("1. ���� ���");
			System.out.println("2. ���� �˻�");
			System.out.println("3. �޷� ����");
			System.out.println("h. ���� q. ����");
			System.out.println("*--------------------------*");
			System.out.print("�Է� : ");

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
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
		}
		System.out.println("�޷� ���� ���α׷��� �����մϴ�.");

	}

	public void register() throws ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("������ ��� �� ��¥�� �Է��մϴ�.");
		System.out.print("�� : ");
		String year = sc.nextLine();
		System.out.print("�� : ");
		String month = sc.nextLine();
		System.out.print("�� : ");
		String day = sc.nextLine();

		String result = year + "-" + month + "-" + day;

		System.out.print("���� ������ �����ּ��� : ");
		String plan = sc.nextLine();
		cal.register(result, plan);
	}

	public void searchPlan() throws ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("�˻� �� ������ ��¥�� �Է� �ϼ���.");
		String date = "";
		System.out.print("�� : ");
		date += sc.nextLine();
		date += "-";
		System.out.print("�� : ");
		date += sc.nextLine();
		date += "-";
		System.out.print("�� : ");
		date += sc.nextLine();

		System.out.println("�Է��Ͻ� ��¥�� �����Դϴ�.");
		cal.searchPlan(date);
		
	}

}
