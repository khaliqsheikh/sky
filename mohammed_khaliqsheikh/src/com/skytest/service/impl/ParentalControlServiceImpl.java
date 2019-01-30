package com.skytest.service.impl;

import com.skytest.exception.TechnicalFailureException;
import com.skytest.exception.TitleNotFoundException;
import com.skytest.service.MovieService;
import com.skytest.service.ParentalControlService;

public class ParentalControlServiceImpl implements ParentalControlService{

	private MovieService movieService;
	
	public ParentalControlServiceImpl(MovieService movieService) {
		// add null check
		this.movieService = movieService;
	}

	public boolean isMovieAccessibleForCustomer(String parentalControlLevelPref, String movieId) throws TitleNotFoundException, TechnicalFailureException {
		
		int intMovieParentalControlLevelPref = getMovieParentalControlLevel(movieId);
		int intCustomerParentalControlLevelPref = getIntValFromParentalControlLevel(parentalControlLevelPref);
		return intMovieParentalControlLevelPref <= intCustomerParentalControlLevelPref;
	}
	
	// following method could be refactored using enum object
	// used a switch statement for simplicity
	private int getIntValFromParentalControlLevel(String customerParentalControlLevelPref) throws TechnicalFailureException{
		
        switch (customerParentalControlLevelPref) {
        case "U":
            return 0;
        case "PG":
            return 1;
        case "12":
            return 2;
        case "15":
            return 3;
        case "18":
            return 4;
        default:
            throw new TechnicalFailureException("Unknown parental control level " + customerParentalControlLevelPref);
        }
	}

	private int getMovieParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException{
		
		try {
			String parentalControlLevel = movieService.getParentalControlLevel(movieId);
			if (parentalControlLevel == null){
				throw new TechnicalFailureException("MovieService returned no level for movie "+movieId);
			}
			return getIntValFromParentalControlLevel(parentalControlLevel);
		} catch (TitleNotFoundException tnfe) {
			throw new TitleNotFoundException("MovieService could not find the movie "+movieId);
		}  catch (Exception e) {
			throw new TechnicalFailureException("An unknown error has occured");
		}

	}
	
}
	

