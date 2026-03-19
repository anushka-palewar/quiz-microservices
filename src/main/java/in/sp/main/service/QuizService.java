package in.sp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.sp.main.entity.Question;
import in.sp.main.entity.Quiz;
import in.sp.main.repository.QuestionRepository;
import in.sp.main.repository.QuizRepository;
import jakarta.websocket.server.ServerEndpoint;

@Service
public class QuizService {
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	QuestionRepository questionrepo;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> questions=questionrepo.findRandomQuestionsByCategory(category,numQ);
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		
		quizRepository.save(quiz);
		
		return new ResponseEntity<>("success",HttpStatus.CREATED);
		
		
	}
	
}
