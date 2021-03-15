package kr.or.ddit.commons.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.commons.service.BoardService;
import kr.or.ddit.enumpkg.Browser;
import kr.or.ddit.vo.AttachVO;

/**
 * 첨부파일 다운로드 
 */
@Controller
public class FileDownloadController {
	@Inject
	BoardService service;
	
	@RequestMapping("/board/download.do")
	public String download(@RequestParam(value="what", required=true) int attNo,
				@RequestHeader(value="User-Agent", required=false) String agent, Model model) {
		Browser browser = Browser.getBrowserConstant(agent);
		AttachVO attach = service.download(attNo);
		model.addAttribute("attach", attach);
		return "downloadView";
	}
	
}
