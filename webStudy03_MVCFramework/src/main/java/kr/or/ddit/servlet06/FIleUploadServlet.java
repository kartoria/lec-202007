package kr.or.ddit.servlet06;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.or.ddit.filter.fileupload.FileUploadRequestWrapper;
import kr.or.ddit.filter.fileupload.MultiPartFile;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.vo.FileUploadTestVO;

@Controller
public class FIleUploadServlet{
	@RequestMapping(value="/servlet06/fileUpload.do", method=RequestMethod.POST)
	public String doPost(@ModelAttribute("vo") FileUploadTestVO commandObject 
			, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(req.getServletContext().getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
		List<MultiPartFile> list = commandObject.getUploadFile();
		if(list!=null) {
			for(MultiPartFile multipartFile  : list) {
//				String filename = multipartFile.getOriginalFilename();
				String savename = multipartFile.saveToWithNewName(saveFolder);
				System.out.println(savename);
			}
		}
		System.out.println(commandObject);
		
		HttpSession session = req.getSession();
		session.setAttribute("result", commandObject);
		
		return "redirect:/html/12/fileUploadForm.jsp";
	}
}
