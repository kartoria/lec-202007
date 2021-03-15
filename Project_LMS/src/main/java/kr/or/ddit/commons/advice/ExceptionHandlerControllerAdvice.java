package kr.or.ddit.commons.advice;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import kr.or.ddit.CustomException;
import kr.or.ddit.commons.controller.BaseController;

/**
 * CustomException 을 발생하면, 404 상태 응답 전송
 *
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends BaseController{
	
	@ExceptionHandler(CustomException.class)
	public String customExceptionHandler(CustomException e, Model model) {
		model.addAttribute("exception", e);
		return "errors/500error";
	}
	
	@ExceptionHandler(SQLException.class)
	public String customExceptionHandler(SQLException e, Model model) {
		return "common/500error";
	}
	@ExceptionHandler(DataAccessException.class)
	public String customExceptionHandler(DataAccessException e, Model model) {
		return "common/500error";
	}
	@ExceptionHandler(TransactionException.class)
	public String customExceptionHandler(TransactionException e, Model model) {
		return "common/500error";
	}
	@ExceptionHandler(EgovBizException.class)
	public String customExceptionHandler(EgovBizException e, Model model) {
		return "common/500error";
	}
	@ExceptionHandler(AccessDeniedException.class)
	public String customExceptionHandler(AccessDeniedException e, Model model) {
		printError("AccessDeniedException", e.getMessage());
		return "common/403error";
	}
}





















