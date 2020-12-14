package kr.ac.sunmoon.urs.rental;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/rental")
public class RentalController {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private RentalServiceImpl rentalService;
	
	@GetMapping
	public ModelAndView listRental() {
		ModelAndView mav = new ModelAndView("rental/list");
		
		return mav;
	}
	
	@GetMapping("/search/{studentNo}")
	public String searchRental(Rental rental) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<Rental> rows = null;
		String result = null;
		
		try {
			rows = rentalService.listRental(rental);
			if (rows != null) {
				result = gson.toJson(rows);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return result;
	}
	
	@GetMapping("/{no}")
	public ModelAndView viewRental(Rental rental) {
		ModelAndView mav = new ModelAndView("rental/view");
		
		try {
			mav.addObject("row", rentalService.viewRental(rental));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@GetMapping("/{no}/form")
	public ModelAndView editRentalForm(Rental rental) {
		ModelAndView mav = new ModelAndView("rental/edit");
		
		try {
			mav.addObject("row", rentalService.viewRental(rental));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@PutMapping
	public ModelAndView editRental(@Valid Rental rental, Errors errors) {
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/rental/" + rental.getNo()));
		
		if (errors.hasErrors()) {
			throw new RuntimeException();
		}
		
		try {
			rentalService.editRental(rental);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@DeleteMapping("/{no}")
	public ModelAndView deleteRental(Rental rental) {
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/rental"));
		
		try {
			if (!rentalService.deleteRental(rental)) {
				mav = new ModelAndView("error/notdelete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@GetMapping("/check/{no}")
	public String checkRental(Rental rental) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Rental row = null;
		String result = null;
		
		try {
			row = rentalService.viewRental(rental);
			if (row != null) {
				result = gson.toJson(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return result;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new RentalValidator());
	}
}
