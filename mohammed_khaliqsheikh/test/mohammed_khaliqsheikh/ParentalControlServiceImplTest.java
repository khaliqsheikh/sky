package mohammed_khaliqsheikh;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.skytest.exception.TechnicalFailureException;
import com.skytest.exception.TitleNotFoundException;
import com.skytest.service.MovieService;
import com.skytest.service.ParentalControlService;
import com.skytest.service.impl.ParentalControlServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceImplTest {

	@Mock
    private MovieService movieService;
	
	final static String movieId = "1";
	final static String PG_RATING = "PG";
	final static String U_RATING = "U";
	final static String TWELVE_RATING = "12";
	final static String FIFTEEN_RATING = "15";
	final static String EIGHTEEN_RATING = "18";
	final static String UNKNOWN = "unknown";
	
	@Test(expected = TechnicalFailureException.class)
	public void testNullMovieParentalControlLevel() throws Exception{
		
		when(movieService.getParentalControlLevel("1")).thenReturn(null);
		
		ParentalControlService service = new ParentalControlServiceImpl(movieService);
		
		service.isMovieAccessibleForCustomer("PG", "1");
	}
	
	@Test(expected = TitleNotFoundException.class)
    public void testForTitleNotFoundException() throws Exception{

        when(movieService.getParentalControlLevel(anyString())).thenThrow(TitleNotFoundException.class);

        ParentalControlService service = new ParentalControlServiceImpl(movieService);
        service.isMovieAccessibleForCustomer(PG_RATING, "2");
    }
	
	@Test(expected = TechnicalFailureException.class)
    public void testForTechnicalFailureException() throws Exception{

        when(movieService.getParentalControlLevel(anyString())).thenThrow(TechnicalFailureException.class);

        ParentalControlService service = new ParentalControlServiceImpl(movieService);
        service.isMovieAccessibleForCustomer(PG_RATING, "3");
    }
	
	@Test(expected = Exception.class)
    public void testForException() throws Exception{

        when(movieService.getParentalControlLevel(anyString())).thenThrow(Exception.class);

        ParentalControlService service = new ParentalControlServiceImpl(movieService);
        service.isMovieAccessibleForCustomer(PG_RATING, "4");
    }
	
	@Test
	public void testForSameParentalControl() throws TitleNotFoundException, TechnicalFailureException{
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn(PG_RATING);
		
		ParentalControlService service = new ParentalControlServiceImpl(movieService);
		
		assertTrue(service.isMovieAccessibleForCustomer(PG_RATING, movieId));
	}
	
	@Test
	public void testForUMovieAndPGControl() throws TitleNotFoundException, TechnicalFailureException{
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn(U_RATING);
		
		ParentalControlService service = new ParentalControlServiceImpl(movieService);
		
		assertTrue(service.isMovieAccessibleForCustomer(PG_RATING, movieId));
	}
	
	@Test
	public void testForPGMovieAndUControl() throws TitleNotFoundException, TechnicalFailureException{
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn(PG_RATING);
		
		ParentalControlService service = new ParentalControlServiceImpl(movieService);
		
		assertFalse(service.isMovieAccessibleForCustomer(U_RATING, movieId));
	}
	
	@Test
	public void testFor12MovieAndUControl() throws TitleNotFoundException, TechnicalFailureException{
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn(TWELVE_RATING);
		
		ParentalControlService service = new ParentalControlServiceImpl(movieService);
		
		assertFalse(service.isMovieAccessibleForCustomer(U_RATING, movieId));
	}
	
	@Test
	public void testFor15MovieAndUControl() throws TitleNotFoundException, TechnicalFailureException{
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn(FIFTEEN_RATING);
		
		ParentalControlService service = new ParentalControlServiceImpl(movieService);
		
		assertFalse(service.isMovieAccessibleForCustomer(U_RATING, movieId));
	}
	
	@Test
	public void testFor18MovieAndUControl() throws TitleNotFoundException, TechnicalFailureException{
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn(EIGHTEEN_RATING);
		
		ParentalControlService service = new ParentalControlServiceImpl(movieService);
		
		assertFalse(service.isMovieAccessibleForCustomer(U_RATING, movieId));
	}
	
	/*
	 * Would continue exhaustive testing against each movie rating and control
	 * However if tests are present for same rating/control, higher rating/control
	 * and lower rating/control. Then the existing test would suffice.
	 */
	
	@Test(expected = TechnicalFailureException.class) 
	public void testForUnknownParentalControl() throws TitleNotFoundException, TechnicalFailureException{
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn(UNKNOWN);
		
		ParentalControlService service = new ParentalControlServiceImpl(movieService);
		
		service.isMovieAccessibleForCustomer(PG_RATING, "1");
	}
	
	@Test(expected = TechnicalFailureException.class) 
	public void testForUnknownMovieParentalControl() throws TitleNotFoundException, TechnicalFailureException{
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn(PG_RATING);
		
		ParentalControlService service = new ParentalControlServiceImpl(movieService);
		
		service.isMovieAccessibleForCustomer(UNKNOWN, "1");
	}
	
}
