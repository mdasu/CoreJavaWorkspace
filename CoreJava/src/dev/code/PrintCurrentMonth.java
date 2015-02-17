package dev.code;

import java.io.NotActiveException;
import java.rmi.Remote;
import java.security.PublicKey;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.zip.ZipEntry;

import javax.security.auth.x500.X500Principal;

public class PrintCurrentMonth {

	static int mm,yyyy = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String lastDayofWeek = new String();
		String firstDayofWeek = new String();

		Date date = new Date();
		GregorianCalendar today = new GregorianCalendar();
		Calendar cal = Calendar.getInstance();

		Calendar local = Calendar.getInstance(TimeZone.getDefault());

		// System.out.println(local.get(Calendar.date));

		// Get current day, month and year
		int dd = cal.get(Calendar.DATE);
		mm = cal.get(Calendar.MONTH);
		yyyy = cal.get(Calendar.YEAR);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		// int xfirstDay = cal.get(Calendar.DATE);
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		// System.out.println(lastDay);

		int firstDay = cal.get(Calendar.DAY_OF_WEEK);

		cal.set(Calendar.DAY_OF_MONTH, lastDate);
		int lastDay = cal.get(Calendar.DAY_OF_WEEK);

		/* get day for first and last day of week - i.e. Sat, Sun, etc. */
		firstDayofWeek = setDayOfWeek(firstDay);
		lastDayofWeek = setDayOfWeek(lastDay);

		/*
		 * System.out.println("First day of month " + firstDayofWeek);
		 * System.out.println("Last day of month " + lastDayofWeek);
		 * System.out.println("Last date of month " + lastDate);
		 */

		String[][] calendar = new String[6][8];
		// int i = 0, j = 0;

		boolean firstSet = false;
		// j = firstDay; // 3 = tues
		int dayOfWeek = 1;

		printHeader();

		// Outer loop no more than 6 times since never more than 5 weeks in
		// month + 1 header row
		cal_loop: for (int i = 0; i <= 5; i++) {

			/*
			 * if (i == 0) { for (int j = 1; j <= 7; j++) { calendar[i][j] =
			 * String.valueOf(dayOfWeek); System.out.println(dayOfWeek); } }
			 */

			for (int j = 1; j <= 7; j++) {
				if (!firstSet) {
					if (j == firstDay) {
						calendar[i][j] = "  " + String.valueOf(dayOfWeek);
						// System.out.println("a " + dayOfWeek);
						setDayOfWeek(firstDay);
						dayOfWeek++;
						firstSet = true;
					} else {
						calendar[i][j] = "   ";
					}
				} else {
					String temp = String.valueOf(dayOfWeek);
					int len = temp.length();
					String space = " ";
					if (len == 1) {
						space = "  ";
					} else {
						space = " ";
					}

					calendar[i][j] = space + String.valueOf(dayOfWeek);
					// System.out.println("b " + dayOfWeek);
					// System.out.println("here");
					if (dayOfWeek == lastDate) {
						break cal_loop;
					}
					dayOfWeek++;
					if (dayOfWeek == lastDate) {
						// break cal_loop;
					}
				}
			}
		}

		mainloop: for (int i = 0; i <= 5; i++) {
			for (int j = 1; j <= 7; j++) {
				String day = calendar[i][j];

				if (day.trim().equals(Integer.toString(dd))) {
					System.out.print(calendar[i][j] + "*");
					System.out.print("     ");
				} else {
					System.out.print(calendar[i][j]);
					System.out.print("      ");
				}
				if (day.trim().equals(Integer.toString(lastDate))) {
					break mainloop;
				}
			}
			System.out.println("");
		}
	}

	public static void printHeader() {

		String month = setMonth(mm);
		
		System.out.println(month + " " + yyyy);
	//	System.out.println("");
		
		// Print month headings
		System.out.print("Sun");
		System.out.print("      ");
		System.out.print("Mon");
		System.out.print("      ");
		System.out.print("Tue");
		System.out.print("      ");
		System.out.print("Wed");
		System.out.print("      ");
		System.out.print("Thu");
		System.out.print("      ");
		System.out.print("Fri");
		System.out.print("      ");
		System.out.print("Sat");
		System.out.println("");
	}

	// return string
	public static String setDayOfWeek(int day) {

		String weekDay = null;

		switch (day) {
		case 1:
			weekDay = "Sun";
			break;
		case 2:
			weekDay = "Mon";
			break;
		case 3:
			weekDay = "Tue";
			break;
		case 4:
			weekDay = "Wed";
			break;
		case 5:
			weekDay = "Thu";
			break;
		case 6:
			weekDay = "Fri";
			break;
		case 7:
			weekDay = "Sat";
			break;
		default:
			break;
		}
		return weekDay;
	}
	
	public static String setMonth(int mm) {

		String month = "";

		switch (mm) {
		case 0:
			month = "January";
			break;
		case 1:
			month = "February";
			break;
		case 2:
			month = "March";
			break;
		case 3:
			month = "April";
			break;
		case 4:
			month = "May";
			break;
		case 5:
			month = "June";
			break;
		case 6:
			month = "July";
			break;
		case 7:
			month = "August";
			break;
		case 8:
			month = "September";
			break;
		case 9:
			month = "October";
			break;
		case 10:
			month = "November";
			break;
		case 11:
			month = "December";
			break;
		default:
			break;
		}
		return month;
	}
}
