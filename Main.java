import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Collections;
//import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.ParseException;

public class Main {
	
	Scanner input = new Scanner(System.in);
	static ArrayList<ToDoList> list = new ArrayList<ToDoList>();
	
	public Main() {
		int menu=0;
		do {
			cls();
			printMenu();
			try {
				menu = input.nextInt(); input.nextLine();
			} catch (Exception e) {
				System.out.println("You should input integer!");
			}
			if(menu == 1) newTodoList();
			else if(menu == 2) viewAllToDoList();
			else if(menu == 3) updateToDoList();
			else if(menu == 4) deleteToDoList();
			else if(menu == 5) break;	
		}while(true);
	}
	
	private void newTodoList() {
		String date;
		do {
			System.out.print("Input date [yyyy-mm-dd]: ");
			date = input.nextLine();
			if(Pattern.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]", date)) {
				String[] splitedDate = date.split("-", 3);
				int year = Integer.parseInt(splitedDate[0]);
				int month = Integer.parseInt(splitedDate[1]);
				int day = Integer.parseInt(splitedDate[2]);
				
				if(isAllowed(year, month, day, date)) break;
			}
			else if(!Pattern.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]", date))
				System.out.println("Wrong input format!");
		}while(true);
		
		System.out.println();
		System.out.println("To-do-list");
		System.out.println("==========");
		int x=0, flag=0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getDate().equals(date)) {
				System.out.println(x+1 + ". " + list.get(i).getActivity());
				x++;
				if(flag == 0) flag=1;
			}
		}
		if(flag == 0) System.out.println("No data!");
		
		String activity;
		do {
			System.out.print("Input to do list: ");
			activity = input.nextLine();
		}while(activity.trim().length() <= 0);
		ToDoList tdl = new ToDoList(date, activity);
		list.add(tdl);
		System.out.println("Success!");
		input.nextLine();
	}

	private void viewAllToDoList() {
		do {
			String[] aktifitas = new String[list.size()];
			String[] tanggal = new String[list.size()];
			if(list.size() <= 0) System.out.println("No data!");
			else {
				
				for (int i = 0; i < list.size(); i++) {
					tanggal[i] = String.valueOf(list.get(i).getDate()); 
					aktifitas[i] = String.valueOf(list.get(i).getActivity()); 
				}	
				
				System.out.println("+===========================================================+");
				System.out.printf("| %-12s | %-42s |\n", "Date", "ToDoList");
				System.out.println("+===========================================================+");
				for (int i = 0; i < list.size(); i++) {
					System.out.printf("| %-12s | - %-40s |\n", tanggal[i], aktifitas[i]);
				}			
				System.out.println("+===========================================================+");
			}
			System.out.println("1. Sort date ascendingly");
			System.out.println("2. Sort date descendingly");
			int option=0;
			System.out.print(">> [input 0 to exit]: ");
			option = input.nextInt();
			
			// belum selesai quicksortnya
			if(option == 1) { 
				quickSort(tanggal, aktifitas, 0, list.size()-1);
			}
			else if(option == 2) {
//				quickSort(int[] arr, int low, int high)
			}
			else if(option == 0) break;
		}while(true);
	}
	
	
	private void updateToDoList() {
		if(list.size() <= 0) {
			System.out.println("No data!");
			input.nextLine();
			return;
		}
		else {
			System.out.println("+===========================================================+");
			System.out.printf("| %-12s | %-42s |\n", "Date", "ToDoList");
			System.out.println("+===========================================================+");
			for (int i = 0; i < list.size(); i++) {
				System.out.printf("| %-12s | - %-40s |\n", list.get(i).getDate(), list.get(i).getActivity());
			}			
			System.out.println("+===========================================================+");
			String date;
			
			while(true) {
				System.out.print("Input date [yyyy-mm-dd]: ");
				date = input.nextLine();
				
				if(Pattern.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]", date)) {
					String[] splitedDate = date.split("-", 3);
					int year = Integer.parseInt(splitedDate[0]);
					int month = Integer.parseInt(splitedDate[1]);
					int day = Integer.parseInt(splitedDate[2]);
					
					if(isAllowed(year, month, day, date)) {
						int flag2 = 0;
						for (int i = 0; i < list.size(); i++) {							
							if(list.get(i).getDate().equals(date)) { 
								flag2 = 1;
								break;
							}
						}
						if(flag2 == 1) break;
						System.out.println("No Data!");
					}
				}
				else if(!Pattern.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]", date)) {
					System.out.println("Wrong input format!");					
				}
			}
			
			System.out.println("To-do-list");
			System.out.println("==========");
			int x=0, flag=0;
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getDate().equals(date)) {
					System.out.println(x+1 + ". " + list.get(i).getActivity());
					x++;
					if(flag == 0) flag=1;
				}
			}
			if(flag == 0) System.out.println("No data!");
			
			int index=0;
			do {
				try {
					System.out.println("Input index: ");
					index = input.nextInt(); input.nextLine();
				} catch (Exception e) {
				}
				if(index > 0 && index <= x) break;
			}while(true);
			
			String activity;
			do {
				System.out.print("Input new to do list: ");
				activity = input.nextLine();
			}while(activity.trim().length() <= 0);
			
			int z=0;
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getDate().equals(date)) {
					z++;
					if(z==index) list.get(z-1).setActivity(activity);
				}
			}
			
			System.out.println("Success!");
			input.nextLine();
		}
	}

	private void deleteToDoList() {
		if(list.size() <= 0) {
			System.out.println("No data!");
			input.nextLine();
			return;
		}
		else {
			System.out.println("+===========================================================+");
			System.out.printf("| %-12s | %-42s |\n", "Date", "ToDoList");
			System.out.println("+===========================================================+");
			for (int i = 0; i < list.size(); i++) {
				System.out.printf("| %-12s | - %-40s |\n", list.get(i).getDate(), list.get(i).getActivity());
			}			
			System.out.println("+===========================================================+");
			String date;
			
			while(true) {
				System.out.print("Input date [yyyy-mm-dd]: ");
				date = input.nextLine();
				
				if(Pattern.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]", date)) {
					String[] splitedDate = date.split("-", 3);
					int year = Integer.parseInt(splitedDate[0]);
					int month = Integer.parseInt(splitedDate[1]);
					int day = Integer.parseInt(splitedDate[2]);
					
					if(isAllowed(year, month, day, date)) {
						int flag2 = 0;
						for (int i = 0; i < list.size(); i++) {							
							if(list.get(i).getDate().equals(date)) { 
								flag2 = 1;
								break;
							}
						}
						if(flag2 == 1) break;
						System.out.println("No Data!");
					}
				}
				else if(!Pattern.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]", date)) {
					System.out.println("Wrong input format!");					
				}
			}
			
			String option;
			do {
				System.out.print("Do you want to delete to do list [Y/N] (Case Insensitive): ");
				option = input.nextLine();
				if(option.toUpperCase().equals("N") || option.toUpperCase().equals("Y")) break;
			}while(true);
			
			if(option.toUpperCase().equals("N")) return;
			
			do {
				int randomNum = (int)(Math.random()*8999)+1000;
				System.out.print("Input captcha["+randomNum+"]: ");
				int random = input.nextInt(); input.nextLine();
				if(random == randomNum) break;
			}while(true);
			
			for (int i = list.size()-1; i>=0; i--) {
				if(list.get(i).getDate().equals(date)) {
					list.remove(i);
				}
			}
			
		}
	}
	
	private boolean isAllowed(int year, int month, int day, String date) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		if(year > currentYear ) {
			if(day >= 1 && day <= 31) 
				if(month >= 1 && month <= 12) 
					if(isValidDate(date)) return true;
		}
		else if(year == currentYear) {
			if(month == currentMonth) 
				if(day >= currentDay && day <= 31) 
					if(isValidDate(date)) return true;
			if(month > currentMonth && month <= 12)
				if(day >= 1 && day <= 31)
					if(isValidDate(date)) return true;
		}					
		if(!isValidDate(date)) return false;
		System.out.println("Must at least input current date!");
		return false;
	}
	
	private static boolean isValidDate(String input) {
        String formatString = "yyyy-MM-dd";

        SimpleDateFormat format = new SimpleDateFormat(formatString);
        format.setLenient(false);
        try {
            format.parse(input);
        } catch (ParseException e) {
        	System.out.println("Date is not valid!");
            return false;
        } catch (IllegalArgumentException e) {
        	System.out.println("Date is not valid!");
        	return false;
        }
        return true;
    }
	
	static void swap(String[] arr, int i, int j){
		String temp = String.valueOf(arr[i]);
	    arr[i] = String.valueOf(arr[j]);
	    arr[j] = String.valueOf(temp);
	}
	 
	static int partition(String[] arr, String[] arr2, int low, int high){
		String pivot = arr[high];
	     
	    int i = (low - 1);
	 
	    for(int j = low; j <= high - 1; j++){
	        if (arr[j].compareTo(pivot) < 0){
	            i++;
	            swap(arr, i, j);
	            swap(arr2, i, j);
	        }
	    }
	    swap(arr, i + 1, high);
	    swap(arr2, i + 1, high);
	    return (i + 1);
	}
	 
	static void quickSort(String[] arr, String[] arr2, int low, int high){
	    if (low < high){ 
	        int pi = partition(arr, arr2, low, high);
	 
	        quickSort(arr, arr2, low, pi - 1);
	        quickSort(arr, arr2, pi + 1, high);
	    }
	}

	private void printMenu() {
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime today = LocalDateTime.now(); 
		System.out.println("To-Do List");
		System.out.println();
		System.out.println("Today, " + formater.format(today));
		System.out.println("=================");
		System.out.println("1. Add new to-do list");
		System.out.println("2. View all to-do list");
		System.out.println("3. Update to-do list");
		System.out.println("4. Delete to-do list");
		System.out.println("5. Exit");
		System.out.print(">> ");
	}
	
	private void cls() {
		for (int i = 0; i < 50; i++) 
			System.out.println();
	}

	public static void main(String[] args) {
		new Main();
	}
}
