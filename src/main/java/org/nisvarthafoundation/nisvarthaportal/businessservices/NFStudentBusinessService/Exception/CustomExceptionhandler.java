package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.Exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.common.net.HttpHeaders;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionhandler extends ResponseEntityExceptionHandler {
	

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		
		List<String> details = new ArrayList<>() ;
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Server >>>>.. error",details);
		
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(studentNotFoundException.class)
	public final ResponseEntity<Object> handleUsrNotfoundException(studentNotFoundException ex, WebRequest request){
		
		List<String> details = new ArrayList<>() ;
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Student not found",details);
		
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
		
	}
	
//	@Override
//	public final ResponseEntity<Object> handleMethodArumentNotValid(MethodArgumentNotValidException ex, 
//								HttpRequest headers , 
//								HttpStatus status, 
//								WebRequest request){
//		
//		List<String> details = new ArrayList<>() ;
//		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
//			details.add(error.getDefaultMessage());
//		}
//		
//		ErrorResponse error = new ErrorResponse("validation failed ", details) ;
//		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
//		
//	}
	
//	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

    	List<String> details = new ArrayList<>() ;
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		
		ErrorResponse error = new ErrorResponse("validation failed ", details) ;
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		
	}

}
