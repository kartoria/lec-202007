package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.filter.fileupload.FileUploadRequestWrapper;
import kr.or.ddit.filter.fileupload.MultipartFile;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdUpdateController {
	
	private IProdService service = ProdServiceImpl.getInstance();
	
	@RequestMapping("/prod/prodUpdate.do")
	public String updateForm(@RequestParam("prod_id")String prod_id, HttpServletRequest req) {
 		ProdVO prod = service.retrieveProd(prod_id);
 		req.setAttribute("prod", prod);
 		return "prod/prodForm";
	}
	
	
	@RequestMapping(value="/prod/prodUpdate.do", method=RequestMethod.POST)
	public String update(@ModelAttribute("prod") ProdVO prod, HttpServletRequest req) throws IOException {
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		CommonValidator<ProdVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(prod, errors, UpdateGroup.class);
		
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(req.getServletContext().getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
		String goPage = null;
		if(valid) {
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
				case OK:
					goPage =  "redirect:/prod/prodView.do?what="+prod.getProd_id();
					break;
				default:	
					req.setAttribute("message", NotyMessageVO.builder("서버 오류").build());
					goPage = "prod/prodForm";
				break;
			}
		}else {
			goPage = "prod/prodForm";
		}
		return goPage;
	}
}
