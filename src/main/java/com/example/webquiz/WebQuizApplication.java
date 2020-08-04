package com.example.webquiz;

import com.example.webquiz.model.Question;
import com.example.webquiz.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebQuizApplication implements CommandLineRunner {

	@Autowired
	private QuestionRepo questionRepo;

	public static void main(String[] args) {
		SpringApplication.run(WebQuizApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		questionRepo.addQuestion(new Question(
				"The Java Logo",
				"What is depicted on the Java logo?",
				new String[] { "Robot", "Tea leaf", "Cup of coffee", "Bug"}));
	}
}
