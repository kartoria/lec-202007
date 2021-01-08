package kr.or.ddit.guestbook.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.guestbook.dao.IGuestBookDAO;

@Service
public class GuestBookServiceImpl implements IGuestBookService{
	@Inject
	private IGuestBookDAO dao;
}
