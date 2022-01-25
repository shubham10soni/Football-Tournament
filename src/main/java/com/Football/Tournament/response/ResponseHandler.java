package com.Football.Tournament.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


//THIS CLASS IS USED FOR GENERATING CUSTOM JSON RESPONSE  FOR ALL CONTROLLER METHODS.
public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(HttpStatus status, String message, Object responseObject) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("status", status.value());
		map.put("message", message);
		map.put("data", responseObject);

		return new ResponseEntity<Object>(map, status);
	}

}
