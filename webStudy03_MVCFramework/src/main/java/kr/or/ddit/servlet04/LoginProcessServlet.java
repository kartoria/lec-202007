package kr.or.ddit.servlet04;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
public class LoginProcessServlet{
	IAuthenticateService service = AuthenticateServiceImpl.getInstance();
	
	private boolean validate(String mem_id, String mem_pass) {
		return true;
	}
	
	@RequestMapping(value="/login/loginProcess.do", method=RequestMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		
		if(validate(mem_id, mem_pass)) {
			
		}
		
		HttpSession session = req.getSession();
		Object result  = service.authenticate(MemberVO.builder()
												.mem_id(mem_id)
												.mem_pass(mem_pass)
												.build());
		String goPage = null;
		if(result instanceof MemberVO) {
			MemberVO authMember = (MemberVO) result;
			session.setAttribute("authMember", authMember);
			goPage = "redirect:/";
		}else {
			String message = null;
			if(ServiceResult.NOTEXIST.equals(result)) {
				message = "아이디 오류, 그런 사람 없음.";
			}else if(ServiceResult.INVALIDPASSWORD.equals(result)){
				message = "비번 오류, 다시 입력하셈.";
				session.setAttribute("mem_id", mem_id);
			}else{
				message = "사용자가 유효하지 않슴돠.";
				session.setAttribute("mem_id", mem_id);
			}
			session.setAttribute("message", NotyMessageVO.builder(message).build());
			goPage = "redirect:/login/loginForm.do";	
		}
		return goPage;
	}
}