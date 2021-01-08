package kr.or.ddit.servlet06;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.FileUploadTestVO;

//@WebServlet("/servlet06/fileUpload.do")
@Controller
public class FileUploadServlet{
	@RequestMapping(value="/servlet06/fileUpload.do", method=RequestMethod.POST)
	public String doPost(@ModelAttribute("vo") FileUploadTestVO commandObject 
			, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(req.getServletContext().getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
		List<MultipartFile> list = commandObject.getUploadFile();
		if(list!=null) {
			for(MultipartFile multipartFile  : list) {
//				String filename = multipartFile.getOriginalFilename();
//				String savename = multipartFile.saveToWithNewName(saveFolder);
//				System.out.println(savename);
			}
		}
		
		
		System.out.println(commandObject);
		
		HttpSession session = req.getSession();
		session.setAttribute("result", commandObject);
		
		return "redirect:/12/fileUploadForm.jsp";
	}
}




















