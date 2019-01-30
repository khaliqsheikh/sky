package com.skytest.service;

import com.skytest.exception.TechnicalFailureException;
import com.skytest.exception.TitleNotFoundException;

public interface MovieService {

	String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;
	
}
