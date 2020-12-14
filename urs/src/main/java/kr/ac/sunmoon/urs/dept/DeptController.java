package kr.ac.sunmoon.urs.dept;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;

@RestController
@RequestMapping("/dept")
public class DeptController {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private DeptServiceImpl deptService;
	
	@GetMapping("/form")
	public ModelAndView addDeptForm() {
		ModelAndView mav = new ModelAndView("dept/add");
		
		return mav;
	}
	
	@PostMapping
	public ModelAndView addDept(@Valid Dept dept, Errors errors) {
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/dept"));
		
		if (errors.hasErrors()) {
			throw new RuntimeException();
		}
		
		try {
			deptService.addDept(dept);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@GetMapping
	public ModelAndView listDept() {
		ModelAndView mav = new ModelAndView("dept/list");
		
		return mav;
	}
	
	@GetMapping("/search/{name}")
	public String searchDept(Dept dept) {
		if (dept.getName() == null) {
			throw new RuntimeException();
		}
		
		Gson gson = new Gson();
		List<Dept> rows = null;
		String result = null;
		
		try {
			rows = deptService.listDept(dept);
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
	public ModelAndView viewDept(Dept dept) {
		ModelAndView mav = new ModelAndView("dept/view");
		
		try {
			dept = deptService.viewDept(dept);
			if (dept.getNo() == 0) {
				throw new RuntimeException();
			}
			
			mav.addObject("row", dept);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@GetMapping("/{no}/form")
	public ModelAndView editDeptForm(Dept dept) {		
		ModelAndView mav = new ModelAndView("dept/edit");
		
		try {
			mav.addObject("row", deptService.viewDept(dept));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@PutMapping
	public ModelAndView editDept(@Valid Dept dept, Errors errors) {
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/dept/" + dept.getNo()));
		
		if (errors.hasErrors()) {
			throw new RuntimeException();
		}
		
		try {
			deptService.editDept(dept);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@DeleteMapping("/{no}")
	public ModelAndView deleteDept(Dept dept) {
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/dept"));
		
		try {
			if (!deptService.deleteDept(dept)) {
				mav = new ModelAndView("error/notdelete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@GetMapping("/check/{name}")
	public String checkDept(Dept dept) {
		if (dept.getName() == null) {
			throw new RuntimeException();
		}
		
		Gson gson = new Gson();
		String result = null;
		
		try {
			dept = deptService.viewDept(dept);
			result = gson.toJson(dept);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return result;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new DeptValidator());
	}
}