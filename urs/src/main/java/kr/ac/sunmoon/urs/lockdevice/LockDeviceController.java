package kr.ac.sunmoon.urs.lockdevice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import kr.ac.sunmoon.urs.member.Member;

@RestController
@RequestMapping("/lockdevice")
public class LockDeviceController {
	@Autowired
	public HttpServletRequest request;
	
	@Autowired
	public LockDeviceServiceImpl lockDeviceService;
	
	@Autowired
	public DeptServiceImpl deptService;

	@GetMapping("/form")
	public ModelAndView addLockDeviceForm() {
		ModelAndView mav = new ModelAndView("/lockdevice/add");
	
		return mav;		
	}
	
	@PostMapping
	public ModelAndView addLockDevice(@Valid LockDevice lockDevice, Errors errors, HttpSession session) {
		ModelAndView mav = new ModelAndView(new RedirectView("/lockdevice"));
		
		if (errors.hasErrors()) {
			throw new RuntimeException();
		}
		
		System.out.println("번호 : " + lockDevice.getNo());
		
		try {
			lockDeviceService.addLockDevice(lockDevice, session);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return mav;		
	}
	
	@GetMapping
	public ModelAndView listLockDevice() {
		ModelAndView mav = new ModelAndView("/lockdevice/list");
		
		return mav;
	}
	
	@GetMapping("/search/{deptName}")
	public String searchLockDevice(LockDevice lockDevice) {
		
		Gson gson = new Gson();
		List<LockDevice> rows = null;
		String result = null;
		
		try {
			rows = lockDeviceService.listLockDevice(lockDevice);
			System.out.println(rows);
			if (rows != null) {
				result = gson.toJson(rows);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	@GetMapping("/{no}")
	public ModelAndView viewLockDevice(@Valid LockDevice lockDevice, Errors errors) {
		ModelAndView mav = new ModelAndView("/lockdevice/view");
		
		if (errors.hasErrors()) {
			throw new RuntimeException();
		}
		
		try {
			LockDevice row = lockDeviceService.viewLockDevice(lockDevice);
			mav.addObject("row", row);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;		
	}
	
	@DeleteMapping("/{no}")
	public ModelAndView deleteLockDevice(@Valid LockDevice lockDevice, Errors errors) {
		ModelAndView mav = new ModelAndView(new RedirectView("/lockdevice"));
		
		if (errors.hasErrors()) {
			throw new RuntimeException();
		}
		
		try {
			if (!lockDeviceService.deleteLockDevice(lockDevice)) {
				mav = new ModelAndView("error/notdelete");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;		
	}
	
	@GetMapping("/check/{no}/{status}")
	public String checkLockDevice(LockDevice lockDevice) {
		System.out.println(lockDevice.getNo() + "/" + lockDevice.getStatus());
		if (lockDevice.getNo() == 0 || lockDevice.getStatus() == null) {
			throw new RuntimeException();
		}
		
		Gson gson = new Gson();
		String result = null;
		
		try {
			LockDevice checkLockDeviceNo = new LockDevice();
			checkLockDeviceNo.setNo(lockDevice.getNo());
			LockDevice checkLockDeviceStatus = new LockDevice();
			checkLockDeviceStatus.setStatus(lockDevice.getStatus());
			
			checkLockDeviceNo = lockDeviceService.viewLockDevice(checkLockDeviceNo);
			checkLockDeviceStatus = lockDeviceService.viewLockDevice(checkLockDeviceStatus);
			
			lockDevice = new LockDevice();
			if (checkLockDeviceNo != null) {
				lockDevice.setNo(checkLockDeviceNo.getNo());
			}
			
			if (checkLockDeviceStatus != null) {
				lockDevice.setStatus(checkLockDeviceStatus.getStatus());
			}
			
			if (checkLockDeviceNo != null || checkLockDeviceStatus != null) {
				result = gson.toJson(lockDevice);
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
		binder.setValidator(new LockDeviceValidator());
	}
}
