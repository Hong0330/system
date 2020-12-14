package kr.ac.sunmoon.urs.student;

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

import kr.ac.sunmoon.urs.dept.Dept;
import kr.ac.sunmoon.urs.dept.DeptServiceImpl;
import kr.ac.sunmoon.urs.emp.EmpServiceImpl;
import kr.ac.sunmoon.urs.member.Member;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	EmpServiceImpl empService;
	
	@Autowired
	StudentServiceImpl studentService;
	
	@Autowired
	DeptServiceImpl deptService;
	
	@GetMapping("/form")
	public ModelAndView addStudentForm() {
		ModelAndView mav = new ModelAndView("student/add");
		
		try {
			mav.addObject("rows", deptService.listDept(new Dept()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	@PostMapping
	public ModelAndView addStudent(@Valid Member member, Errors errors) {
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/student"));
		
		if (errors.hasErrors()) {
			throw new RuntimeException();
		}
		
		try {
			studentService.addStudent(member);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@GetMapping
	public ModelAndView listStudent() {
		ModelAndView mav = new ModelAndView("student/list");
		
		return mav;
	}

	@GetMapping("/search/{memberNo}")
	public String searchStudent(Member member) {
		Gson gson = new Gson();
		String result = null;
		
		List<Member> rows = null;
		
		try {
			rows = studentService.listStudent(member);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		if (rows != null) {
			result = gson.toJson(rows);
		}
		
		return result;
	}

	@GetMapping("/{memberNo}")
	public ModelAndView viewStudent(Member member) {
		ModelAndView mav = new ModelAndView("student/view");

		try {
			mav.addObject("row", studentService.viewStudent(member));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		return mav;
	}

	@GetMapping("/{memberNo}/form")
	public ModelAndView editStudentForm(Member member) {
		ModelAndView mav = new ModelAndView("student/edit");
		try {
			mav.addObject("row", studentService.viewStudent(member));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}

	@PutMapping
	public ModelAndView editStudent(@Valid Member member, Errors errors) {
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/student/" + member.getMemberNo()));
		
		if (errors.hasErrors()) {
			throw new RuntimeException();
		}
		
		try {
			studentService.editStudent(member);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}

	@DeleteMapping("/{memberNo}")
	public ModelAndView deleteStudent(Member member) {
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/student"));
		try {
			if (!studentService.deleteStudent(member)) {
				mav = new ModelAndView("error/notdelete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}

	@GetMapping("/check/{memberNo}/{cardNo}")
	public String checkStudent(Member member) {
		Gson gson = new Gson();
		String result = null;
		
		try {
			Member checkMemberNo = new Member();
			checkMemberNo.setMemberNo(member.getMemberNo());
			Member checkCardNo = new Member();
			checkCardNo.setCardNo(member.getCardNo());
			
			if (member.getMemberNo() != 2020123456) {
				checkMemberNo = studentService.viewStudent(checkMemberNo);
				if (checkMemberNo == null) {
					checkMemberNo = new Member();
					checkMemberNo.setMemberNo(member.getMemberNo());
					checkMemberNo = empService.viewEmp(checkMemberNo);
				}
			}
			
			checkCardNo = studentService.viewStudent(checkCardNo);
			
			member = new Member();
			if (checkMemberNo != null) {
				member.setMemberNo(checkMemberNo.getMemberNo());
			}
			
			if (checkCardNo != null) {
				member.setCardNo(checkCardNo.getCardNo());
			}
			
			if (checkMemberNo != null || checkCardNo != null) {
				result = gson.toJson(member);
			} else {
				result = gson.toJson(null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return result;
	}
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new StudentValidator());
	}
}
