package in.sp.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.sp.main.entity.Question;
import in.sp.main.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	QuestionRepository questionrepo;

	public ResponseEntity<List<Question>> getAllQuestions() {
	    try {
	        return new ResponseEntity<>(questionrepo.findAll(), HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}


	public Question addQuestion(Question q) {
		return questionrepo.save(q);
	}

	public Question getQuestionById(int id) {
	    return questionrepo.findById(id).orElse(null);
	}


	public List<Question> getQuestionByCategory(String category) {
		return questionrepo.findByCategory(category);
	}


	public String deleteByQuestionById(int id) {
	    questionrepo.deleteById(id);
	    return "Question deleted successfully";
	}
	

}

