package com.skytest.service;

import com.skytest.exception.TechnicalFailureException;
import com.skytest.exception.TitleNotFoundException;

public interface ParentalControlService {

	public boolean isMovieAccessibleForCustomer(String parentalControlLevelPref, String movieId) throws TechnicalFailureException, TitleNotFoundException;
	
}
