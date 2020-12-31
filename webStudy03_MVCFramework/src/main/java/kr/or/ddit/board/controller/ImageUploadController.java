package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.filter.fileupload.FileUploadRequestWrapper;
import kr.or.ddit.filter.fileupload.MultipartFile;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.utils.JsonResponseUtils;

@Controller
public class ImageUploadController {
	@RequestMapping(value="/board/imageUpload.do", method=RequestMethod.POST)
	public String upload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		FileUploadRequestWrapper wrapper = (FileUploadRequestWrapper) req;
		MultipartFile imageFile = wrapper.getFile("upload");
		String saveFolderUrl = "/boardImages";
		File saveFolder = new File(req.getServletContext().getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		imageFile.saveToWithNewName(saveFolder);
		req.setAttribute("fileName", imageFile.getOriginalFilename());
		req.setAttribute("uploaded", 1);
		String saveUrl = req.getContextPath() + saveFolderUrl+"/"+imageFile.getSavename();
		req.setAttribute("url", saveUrl);
		JsonResponseUtils.toJsonResponse(req, resp);
		return null;
	}
}
