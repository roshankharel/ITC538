package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProcessMarks {
//	static final class GRADE {
//		static final int A = 85;
//		static final int B = 75;
//		static final int C = 65;
//		static final int D = 50;
//	}

	static final HashMap<Character, Integer> gradesMap = new HashMap<Character, Integer>();

	public static void main(String[] args) {
		gradesMap.put('A', 85);
		gradesMap.put('B', 75);
		gradesMap.put('C', 65);
		gradesMap.put('D', 50);
	}

	public static int max(int[] marks) {
		int max = marks[0];

		for (int i = 1; i < marks.length; i++) {
			if (max < marks[i])
				max = marks[i];
		}

		return max;
	}

	public static int min(int[] marks) {
		int min = marks[0];

		for (int i = 1; i < marks.length; i++) {
			if (min > marks[i])
				min = marks[i];
		}

		return min;
	}

	public static int range(int[] marks) {
		return max(marks) - min(marks);
	}

	public static double mean(int[] marks) {
		int sum = 0;

		for (int i = 0; i < marks.length; i++) {
			sum += marks[i];
		}

		return Double.valueOf(sum) / Double.valueOf(marks.length);
	}

	public static double median(int[] marks) {
		marks = marks.clone();
		boolean hasEvenNumberOfElements = marks.length % 2 == 0;

		// sort ascending
		Arrays.sort(marks);

		if (hasEvenNumberOfElements) {
			int middleIndex = marks.length / 2;

			return Double.valueOf(marks[middleIndex - 1] + marks[middleIndex]) / 2.0;

		}

		int middleIndex = (marks.length - 1) / 2;

		return marks[middleIndex];
	}

	public static int mode(int[] marks) {
		HashMap<Integer, Integer> marksMap = new HashMap<Integer, Integer>();
		int maxOccurance = 0;
		int maxOccuredNum = marks[0];

		for (int i = 0; i < marks.length; i++) {
			int occurance = marksMap.containsKey(marks[i]) ? (int) marksMap.get(marks[i]) : 0;
			occurance++;

			marksMap.put(marks[i], occurance);

			if (occurance > maxOccurance) {
				maxOccurance = occurance;
				maxOccuredNum = marks[i];
			}
		}

		return maxOccuredNum;
	}

	public static char[] grades(int[] marks) {
		char[] studentGrades = new char[marks.length];

		for (int i = 0; i < marks.length; i++) {
			int grade = marks[i];
			boolean graded = false;

			for (Map.Entry<Character, Integer> entry : gradesMap.entrySet()) {
				if (grade >= entry.getValue()) {
					studentGrades[i] = entry.getKey();
					graded = true;
					break;
				}
			}

			if (!graded)
				studentGrades[i] = 'F';
		}

		return studentGrades;
	}

	public static int[] gradeDistn(char[] studentGrades) {
		int[] gradesDistribution = new int[5];

		for (int i = 0; i < studentGrades.length; i++) {
			switch (studentGrades[i]) {
			case 'A':
				gradesDistribution[0]++;
				break;
			case 'B':
				gradesDistribution[1]++;
				break;
			case 'C':
				gradesDistribution[2]++;
				break;
			case 'D':
				gradesDistribution[3]++;
				break;
			case 'F':
				gradesDistribution[4]++;
				break;
			}
		}

		return null;
	}

}
