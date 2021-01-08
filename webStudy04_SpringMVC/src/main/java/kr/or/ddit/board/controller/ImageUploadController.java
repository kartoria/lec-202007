package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class ImageUploadController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageUploadController.class);
	@Inject
	private WebApplicationContext container;
	@Value("#{appInfo.boardImages}")
	String saveFolderUrl;
	File saveFolder;
	@PostConstruct
	public void init() throws IOException {
		saveFolder = container.getResource(saveFolderUrl).getFile();
		LOGGER.info("{}", saveFolder.getAbsolutePath());
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}
	
	
	@PostMapping(value="/board/imageUpload.do")
	public String upload(@RequestPart(value="upload", required=true) MultipartFile imageFile, Model model) throws IOException {
		String saveName = UUID.randomUUID().toString();
		imageFile.transferTo(new File(saveFolder, saveName));
		
		model.addAttribute("fileName", imageFile.getOriginalFilename());
		model.addAttribute("uploaded", 1);
		String saveUrl = container.getServletContext().getContextPath() + saveFolderUrl+"/"+saveName;
		model.addAttribute("url", saveUrl);
		return "jsonView";
	}
	
	public String upload(MultipartHttpServletRequest req, Model model) throws IOException {
		MultipartFile imageFile = req.getFile("upload");
//		if(imageFile==null || StringUtils.isBlank(imageFile.getOriginalFilename())) {
//			resp.sendError(400);
//			return null;
//		}
		String saveName = UUID.randomUUID().toString();
		imageFile.transferTo(new File(saveFolder, saveName));
		
		model.addAttribute("fileName", imageFile.getOriginalFilename());
		model.addAttribute("uploaded", 1);
		String saveUrl = req.getContextPath() + saveFolderUrl+"/"+saveName;
		model.addAttribute("url", saveUrl);
		return "jsonView";
	}
}



















