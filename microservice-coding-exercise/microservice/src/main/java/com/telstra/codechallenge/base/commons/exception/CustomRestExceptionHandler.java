package com.telstra.codechallenge.base.commons.exception;

import static com.telstra.codechallenge.base.commons.rest.ApiResponseCodes.DATA_NOT_FOUND_ERROR_CODE;
import static com.telstra.codechallenge.base.commons.rest.ApiResponseCodes.FAILURE_STATUS;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.telstra.codechallenge.base.commons.rest.ApiResponse;

@RestControllerAdvice
public class CustomRestExceptionHandler {
	
//	private static final Logger LOG = LoggerFactory.getLogger(CustomRestExceptionHandler.class);
	
	@ExceptionHandler(value = DataNotFoundException.class)
	public ResponseEntity<ApiResponse> handleException(DataNotFoundException e) {
		ApiResponse response = new ApiResponse(String.valueOf(FAILURE_STATUS), DATA_NOT_FOUND_ERROR_CODE);
		response.setMessages(Arrays.asList(HttpStatus.INTERNAL_SERVER_ERROR.name()));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = HttpClientErrorException.class)
	public ResponseEntity<ApiResponse> handleException(HttpClientErrorException e) {
		ApiResponse response = new ApiResponse(String.valueOf(FAILURE_STATUS), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		response.setMessages(Arrays.asList(HttpStatus.INTERNAL_SERVER_ERROR.name()));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiResponse> handleException(Exception e) {
		ApiResponse response = new ApiResponse(String.valueOf(FAILURE_STATUS), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		response.setMessages(Arrays.asList(HttpStatus.INTERNAL_SERVER_ERROR.name()));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	
}
