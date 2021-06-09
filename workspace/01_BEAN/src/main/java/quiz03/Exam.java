package quiz03;

import java.util.List;

public class Exam {

	private List<Integer> scores;
	private double average;
	private char grade;
	
	public void info() {
		setAverage();
		setGrade();
		System.out.println("scores: " + scores.toString());
		System.out.println("average: " + average);
		System.out.println("grade: " + grade);
	}
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public void setAverage() {
		int total = 0;
		for (Integer score : scores) {
			total += score;
		}
		this.average = total / scores.size();
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	public void setGrade() {
		if (average >= 90) grade = 'A';
		else if (average >= 80) grade = 'B';
		else if (average >= 70) grade = 'C';
		else if (average >= 60) grade = 'D';
		else grade = 'F';
	}
	
}