package in.sp.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.sp.main.entity.Question;
import in.sp.main.entity.QuestionWrapper;
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

	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
		Optional<Quiz> quiz=quizRepository.findById(id);
		
		List<Question> questionsFromDb=quiz.get().getQuestions();
		List<QuestionWrapper> questionsFrorUser=new ArrayList<QuestionWrapper>();
		
		for(Question q: questionsFromDb) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionsFrorUser.add(qw);
			
		}
		return new ResponseEntity<>(questionsFrorUser,HttpStatus.OK);
	}
	
}
