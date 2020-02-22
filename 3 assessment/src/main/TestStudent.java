package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TestStudent {
	private List<Student> studentList = new ArrayList<Student>();
	private Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		TestStudent testStudent = new TestStudent();

		testStudent.deserializeStudents();
		testStudent.initializeUserInteraction();
	}

	public void deserializeStudents() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("./students.txt"));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] studentDetails = line.split(",");
				int studentId = Integer.parseInt(studentDetails[0]);
				String studentName = studentDetails[1];

				Student student = new Student();
				student.setId(studentId);
				student.setName(studentName);

				studentList.add(student);
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void initializeUserInteraction() {
		while (true) {
			System.out.printf("---------------------Menu---------------------\n" + "1. Diaplay All Students\n"
					+ "2. Add New Student\n" + "3. Show Student\n"
					+ "\nSelect option from menu (or press any other key to exit):");

			try {
				int menuOption = keyboard.nextInt();

				if (menuOption >= 1 && menuOption <= 3) {
					switch (menuOption) {
					case 1:
						break;
					case 2:
						interactiveAddStudent();
						break;
					case 3:
						break;
					default:
						break;
					}
				} else {
					// the program will exit
					break;
				}

			} catch (InputMismatchException e) {
				// the program will exit
				break;
			}
		}

	}

	void interactiveAddStudent() {
		Student student = new Student();

		while (true) {
			System.out.println("Enter student name: ");
			String studentName = keyboard.nextLine().trim();

			if (studentName.length() > 0) {
				student.setName(studentName);
				break;
			} else {
				System.out.println("Error: Student name cannot be empty.");				
			}

		}

		while (true) {
			System.out.println("Enter student id: ");

			try {
				int studentId = Integer.parseInt(keyboard.nextLine());

				if (!student.isValidId(studentId)) {
					System.out.println("Error: Student id must be a six digit integer.");
					continue;
				}

				if (!student.IdExists(studentId)) {
					System.out.println("Error: Student id is not unique.");
					continue;
				}

				student.setId(studentId);
				break;

			} catch (InputMismatchException e) {
				System.out.println("Error: Student id must be a six digit integer.");
			}
		}

		studentList.add(student);
		System.out.println("Success: Student added successfully.");

		persistStudents();
	}

	void persistStudents() {
		try {
			FileWriter fileWriter = new FileWriter("./students.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);

			for (Student student : studentList) {
				printWriter.printf(
						"%d, %s\n",
						student.getId(),
						student.getName()
				);
			}

			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
