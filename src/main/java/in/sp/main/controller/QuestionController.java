package in.sp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.entity.Question;
import in.sp.main.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionservice;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion() {
		return questionservice.getAllQuestions();
	}
	
	@PostMapping("addQuestion")
	public Question addQuestion(@RequestBody Question q) {
		System.out.println(q);
		return questionservice.addQuestion(q);
	}
	
	@GetMapping("getQueById/{id}")
	public Question getQuestionById(@PathVariable int id) {
		return questionservice.getQuestionById(id);
	}
	
	@GetMapping("getQueByCategory/{category}")
	public List<Question> getQuestionByCategory(@PathVariable String category){
		return questionservice.getQuestionByCategory(category);
	}
	
	@DeleteMapping("/deleteQueById/{id}")
	public String deleteQuestionById(@PathVariable int id) {
		return questionservice.deleteByQuestionById(id);
	}

}
