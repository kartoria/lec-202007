package kr.or.ddit.guestbook.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/book", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GuestBookController {
	@GetMapping
	public void retrieveBook(){
		
	}
	
	@PostMapping
	public void createBook(){
		
	}
	
	@PutMapping
	public void updateBook(){
		
	}
	
	@DeleteMapping
	public void deleteBook(){
		
	}
}
