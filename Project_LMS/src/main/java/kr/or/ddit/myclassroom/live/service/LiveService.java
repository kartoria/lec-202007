package kr.or.ddit.myclassroom.live.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CdnSettings;
import com.google.api.services.youtube.model.LiveBroadcast;
import com.google.api.services.youtube.model.LiveBroadcastContentDetails;
import com.google.api.services.youtube.model.LiveBroadcastListResponse;
import com.google.api.services.youtube.model.LiveBroadcastSnippet;
import com.google.api.services.youtube.model.LiveBroadcastStatus;
import com.google.api.services.youtube.model.LiveChatMessageListResponse;
import com.google.api.services.youtube.model.LiveStream;
import com.google.api.services.youtube.model.LiveStreamContentDetails;
import com.google.api.services.youtube.model.LiveStreamSnippet;

import kr.or.ddit.commons.service.BaseService;
import kr.or.ddit.myclassroom.live.dao.LiveDAO;
import kr.or.ddit.myclassroom.live.vo.LiveVO;

@Service
public class LiveService extends BaseService{
	@Inject
	private LiveDAO liveDAO;
	
	@Value("#{appInfo.applicationName}")
	private String applicationName;
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private NetHttpTransport httpTransport;
	
	@PostConstruct
	public void init() throws GeneralSecurityException, IOException {
		httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	}
	
    public YouTube getService(Credential credential){
        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
            .setApplicationName(applicationName)
            .build();
    }

    public void liveStreams(LiveVO liveVO) throws IOException {
        YouTube youtubeService = getService(liveVO.getCredential());
        List<String> part = new ArrayList<>();
        
        // Define the LiveStream object, which will be uploaded as the request body.
        LiveStream liveStream = new LiveStream();
        
        // Add the snippet object property to the LiveStream object.
        LiveStreamSnippet snippet = new LiveStreamSnippet();
        snippet.setTitle("tmpStream");
        liveStream.setSnippet(snippet);
        part.add("snippet");
        
        // Add the cdn object property to the LiveStream object.
        CdnSettings cdn = new CdnSettings();
        cdn.setFrameRate(liveVO.getFrameRate());
        cdn.setIngestionType("rtmp");
        cdn.setResolution(liveVO.getResolution());
        liveStream.setCdn(cdn);
        part.add("cdn");
        
        // Add the contentDetails object property to the LiveStream object.
        LiveStreamContentDetails contentDetails = new LiveStreamContentDetails();
        contentDetails.setIsReusable(false);
        liveStream.setContentDetails(contentDetails);
        part.add("contentDetails");

        // Define and execute the API request
        YouTube.LiveStreams.Insert request = youtubeService.liveStreams().insert(part, liveStream);
        LiveStream streamResponse = request.execute();
        printInfo("LiveStream insert response ", streamResponse);
        
        liveVO.setStreamId(streamResponse.getId());
        liveVO.setStreamKey(streamResponse.getCdn().getIngestionInfo().getStreamName());
    }
    
    public void liveBroadcast(LiveVO liveVO) throws IOException {
        YouTube youtubeService = getService(liveVO.getCredential());
        List<String> part = new ArrayList<>();
        
        Date sysdate = new Date();
        DateTime sysdateTime = new DateTime(sysdate);
        printInfo("sysdate", sysdate);

        // Define the LiveBroadcast object, which will be uploaded as the request body.
        LiveBroadcast liveBroadcast = new LiveBroadcast();
        
        // Add the snippet object property to the LiveBroadcast object.
        LiveBroadcastSnippet snippet = new LiveBroadcastSnippet();
        snippet.setTitle(liveVO.getBroadcastTitle());
        snippet.setDescription(liveVO.getBroadcastDescription());
        snippet.setScheduledStartTime(sysdateTime);
        liveBroadcast.setSnippet(snippet);
        part.add("snippet");
        
        // Add the status object property to the LiveBroadcast object.
        LiveBroadcastStatus status = new LiveBroadcastStatus();
        status.setPrivacyStatus(liveVO.getPrivacyStatus());
        status.setSelfDeclaredMadeForKids(false);
        liveBroadcast.setStatus(status);
        part.add("status");
        
        // Add the contentDetails object property to the LiveBroadcast object.
        LiveBroadcastContentDetails contentDetails = new LiveBroadcastContentDetails();
        contentDetails.setEnableAutoStart(true);
        contentDetails.setEnableAutoStop(true);
        contentDetails.setEnableDvr(false);
        contentDetails.setRecordFromStart(true);
        liveBroadcast.setContentDetails(contentDetails);
        part.add("contentDetails");

        YouTube.LiveBroadcasts.Insert request = youtubeService.liveBroadcasts().insert(part, liveBroadcast);
        LiveBroadcast response = request.execute();
        printInfo("LiveBroadcast insert response ", response);
        
        liveVO.setScheduledStartTime(sysdate);
        liveVO.setBroadcastId(response.getId());
        liveVO.setLiveChatId(response.getSnippet().getLiveChatId());
        
    }
    
    public void broadcastBind(LiveVO liveVO) throws IOException {
    	YouTube youtubeService = getService(liveVO.getCredential());
    	List<String> part = new ArrayList<>();
    	
    	String broadcastId = liveVO.getBroadcastId();
    	String streamId = liveVO.getStreamId();
    	
    	YouTube.LiveBroadcasts.Bind request = youtubeService.liveBroadcasts().bind(broadcastId, part);
        LiveBroadcast response = request.setStreamId(streamId).execute();
        printInfo("LiveBroadcast Bind response ", response);
    }
    
    public void liveChatList(LiveVO liveVO) throws IOException {
        YouTube youtubeService = getService(liveVO.getCredential());
        List<String> part = new ArrayList<>();
        part.add("id");
        part.add("snippet");
        part.add("authorDetails");
        
        String liveChatId = liveVO.getLiveChatId();
        
        // Define and execute the API request
        YouTube.LiveChatMessages.List request = youtubeService.liveChatMessages().list(liveChatId, part);
		LiveChatMessageListResponse response = request.execute();
		printInfo("LiveChatMessageListResponse ", response);
		liveVO.setLiveChatItems(response.getItems());
    }
    
    public String getbroadCastStatus(LiveVO liveVO) throws IOException {
    	YouTube youtubeService = getService(liveVO.getCredential());
        // Define and execute the API request
        YouTube.LiveBroadcasts.List request = youtubeService.liveBroadcasts().list(Arrays.asList("status"));
        LiveBroadcastListResponse response = request.setId(Arrays.asList(liveVO.getBroadcastId())).setBroadcastType("all").setMine(false).setBroadcastStatus("all").execute();
        printInfo("broadcastList response ", response);
        if(response.getItems().size() > 0)
        	return response.getItems().get(0).getStatus().getLifeCycleStatus();
        else return "removed";
    }
    
    

	public LiveVO selectTotalWeek(LiveVO liveVO) {
		return liveDAO.selectTotalWeek(liveVO);
	}
	
	public void insertBroadcast(LiveVO liveVO) {
		liveDAO.insertBroadcast(liveVO);
	}
	
	public List<LiveVO> selectLiveList(LiveVO liveVO){
		return liveDAO.selectLiveList(liveVO);
	}
	
	public void deleteLive(LiveVO liveVO) {
		liveDAO.deleteLive(liveVO);
	}

	
	
	public LiveVO selectLive(LiveVO liveVO) {
		LiveVO resultLiveVO = liveDAO.selectLive(liveVO);
		resultLiveVO.setCredential(liveVO.getCredential());
		
//		String lifeCycleStatus;
//		try {
//			lifeCycleStatus = getbroadCastStatus(resultLiveVO);
//		} catch (IOException e) {
//			printInfo("유튜브 에러", e.getMessage());
//			lifeCycleStatus = "";
//		}
//		resultLiveVO.setLifeCycleStatus(lifeCycleStatus);
		
		return resultLiveVO;
	}
	

	public void updateStudentAttendance(LiveVO liveVO) {
		LiveVO resultLiveVO = liveDAO.selectStudentAttendance(liveVO);
		if(resultLiveVO == null) {
			liveVO.setAttenCode("ATTEND");
			liveDAO.insertAttendance(liveVO);
		}else if(resultLiveVO.getAttenCode().equals("NULL")){
			liveVO.setAttenCode("ATTEND");
			liveDAO.updateAttendance(liveVO);
		}
	}
	
	public List<LiveVO> selectStudentAttendanceList(LiveVO liveVO){
		return liveDAO.selectStudentAttendanceList(liveVO);
	}
	
	public void updateAttendance(LiveVO liveVO){
		liveDAO.updateAttendance(liveVO);
	}
	
	public List<LiveVO> selectAttendanceCodeList(){
		return liveDAO.selectAttendanceCodeList();
	}

    
}
