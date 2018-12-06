package org.project.testengine.question.dto;


import java.util.Arrays;


public class QuestionDTO {

	private int id;
	private String Name;
	private String Option []=new String[4];
	private String RightAnswer;
	private int Score;
	private String yourAnswer;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getOption() {
		return Option;
	}
	public void setOption(String[] option) {
		this.Option = option;
	}
	public String getRightAnswer() {
		return RightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		RightAnswer = rightAnswer;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		this.Score = score;
	}
	public String getYourAnswer() {
		return yourAnswer;
	}
	public void setYourAnswer(String yourAnswer) {
		this.yourAnswer = yourAnswer;
	}
	@Override
	public String toString() {
		//return id+" " + Name+" " + Arrays.toString(Option)+" "+RightAnswer+" "+Score;
		
		return "QuestionDTO [id=" + id + ", name=" + Name + ", options=" + Arrays.toString(Option) + ", rightAnswer="
		+ RightAnswer + ", score=" + Score + "]";
		
	}
}
