package in.sp.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


	public ResponseEntity<List<Question>> addQuestions(List<Question> questions) {
	    List<Question> saved = questionrepo.saveAll(questions);
	    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
    public ResponseEntity<Question> getQuestionById(int id) {
        Optional<Question> question = questionrepo.findById(id);

        if (question.isPresent()) {
            return ResponseEntity.ok(question.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        List<Question> list = questionrepo.findByCategory(category);

        if (!list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(list);
        }
    }

    public ResponseEntity<String> deleteByQuestionById(int id) {
        if (questionrepo.existsById(id)) {
            questionrepo.deleteById(id);
            return ResponseEntity.ok("Question deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        }
    }


	

}

