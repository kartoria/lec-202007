package kr.or.ddit.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import kr.or.ddit.commons.service.BaseService;

@Service
public class AttendanceScheduler extends BaseService {

	@Scheduled(cron = "*/10 * * * * *")
    public void run() {
		LOGGER.info("cron 실행됨");
    }
	
}
