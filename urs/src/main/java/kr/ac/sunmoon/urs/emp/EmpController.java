package kr.ac.sunmoon.urs.emp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.log.SystemLogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;

import kr.ac.sunmoon.urs.dept.Dept;
import kr.ac.sunmoon.urs.dept.DeptServiceImpl;
import kr.ac.sunmoon.urs.dept.DeptValidator;
import kr.ac.sunmoon.urs.member.Member;

@RestController
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private EmpServiceImpl empService;
	
	@Autowired
	private DeptServiceImpl deptService;
	
	@GetMapping("/form")
	public ModelAndView addEmpForm() {
		ModelAndView mav = new ModelAndView("emp/add");
		
		try {
			mav.addObject("rows", deptService.listDept(new Dept()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return mav;
	}

	@PostMapping
	public ModelAndView addEmp(@Valid Member member, Errors errors) {
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/emp"));
		
		if (errors.hasErrors()) {
			throw new RuntimeException();
		}
		
		try {
			empService.addEmp(member);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@GetMapping
	public ModelAndView listEmp() {
		ModelAndView mav = new ModelAndView("emp/list");
		
		return mav;
	}

	@GetMapping("/search/{memberNo}")
	public String searchEmp(Member member) {
		Gson gson = new Gson();
		String result = null;
		
		List<Member> msg = null;
		
		try {
			msg = empService.listEmp(member);
			if (msg != null) {
				result = gson.toJson(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		return result;
	}

	@GetMapping("/{memberNo}")
	public ModelAndView viewEmp(Member member) {
		ModelAndView mav = new ModelAndView("emp/view");

		try {
			mav.addObject("row", empService.viewEmp(member));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		return mav;
	}

	@GetMapping("/{memberNo}/form")
	public ModelAndView editEmpForm(Member member) {
		ModelAndView mav = new ModelAndView("emp/edit");

		try {
			mav.addObject("row", empService.viewEmp(member));
			mav.addObject("rows", deptService.listDept(null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}

	@PutMapping
	public ModelAndView editEmp(@Valid Member member, Errors errors) {
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/emp/" + member.getMemberNo()));
		
		if (errors.hasErrors()) {
			throw new RuntimeException();
		}
		
		if (member.getPassword() == null) {
			throw new RuntimeException();
		}
		
		try {
			empService.editEmp(member);
		
			mav.addObject("row", empService.viewEmp(member));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}

	@DeleteMapping("/{memberNo}")
	public ModelAndView deleteEmp(Member member) {
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/emp"));
		try {
			if (!empService.deleteEmp(member)) {
				mav = new ModelAndView("error/notdelete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;
	}
	
	@GetMapping("/check/{memberNo}/{cardNo}/{deptNo}")
	public String checkEmp(Member member) {
		if (member.getMemberNo() == 0) {
			throw new RuntimeException();
		}
		System.out.println(member.getCardNo() + "/" + member.getMemberNo() + "/" + member.getDeptNo());
		
		Gson gson = new Gson();
		String result = null;
		
		try {
			Member checkMemberNo = new Member();
			checkMemberNo.setMemberNo(member.getMemberNo());
			Member checkCardNo = new Member();
			checkCardNo.setCardNo(member.getCardNo());
			Member checkDeptNo = new Member();
			checkDeptNo.setDeptNo(member.getDeptNo());
			
			checkMemberNo = empService.viewEmp(checkMemberNo);
			checkCardNo = empService.viewEmp(checkCardNo);
			checkDeptNo = empService.viewEmp(checkDeptNo);
			
			member = new Member();
			if (checkMemberNo != null) {
				member.setMemberNo(checkMemberNo.getMemberNo());
			}
			if (checkCardNo != null) {
				member.setCardNo(checkCardNo.getCardNo());
			}
			if (checkDeptNo != null) {
				member.setDeptNo(checkDeptNo.getDeptNo());
			}
			
			if (checkMemberNo != null || checkCardNo != null || checkDeptNo != null) {
				result = gson.toJson(member);
			} else {
				result = gson.toJson(null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		System.out.println("result : " + result);
		return result;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new EmpValidator());
	}
}