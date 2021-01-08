package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.Browser;
import kr.or.ddit.vo.AttatchVO;

@Controller
public class DownloadController {
	@Inject
	private IBoardService service;
	
	@RequestMapping("/board/download.do")
	public String download(
		@RequestParam(value="what", required=true) int att_no
		,@RequestHeader(value="User-Agent", required=false) String agent
		, Model model
	) throws IOException {
//		Content-Disposition: attatchment; filename=""
		Browser browser = Browser.getBrowserConstant(agent);
		AttatchVO attatch = service.download(att_no);
		model.addAttribute("attatch", attatch);
		return "downloadView";
	}
}


















