package kr.or.ddit.myclassroom.live.vo;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.youtube.model.LiveChatMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *	https://developers.google.com/youtube/v3/live/docs/liveBroadcasts
 *	유튜브
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LiveVO {
	
	// DB 데이터
	private Integer liveNo;
	private String  memId;
	private String  lecCode;
	
	@NotNull(message="필수 입력사항입니다.")
	private Integer liveWeek;
	private Integer lecDays;
	
	private Integer attenNo;
	private Date attenDate;
	
	// Youtube API 데이터
	private String streamTitle;
	@NotBlank(message="필수 입력사항입니다.")
	private String frameRate;
	@NotBlank(message="필수 입력사항입니다.")
	private String resolution;
	
	@NotBlank(message="필수 입력사항입니다.")
	private String broadcastTitle;
	private String broadcastDescription;
	private Date   scheduledStartTime;
	private String uploadTime; // date -> string
	@NotBlank(message="필수 입력사항입니다.")
	private String privacyStatus; // public, private, unlisted
	
	private String broadcastId;
	private String streamId;
	private String streamKey;
	private String liveChatId;
	private String lifeCycleStatus;

	private Credential credential;
	private List<LiveChatMessage> liveChatItems;
	
	
	private String memName;
	private String attenTime;
	private String attenCode;
	private String codeName;
	private String professorId;
	
	private List<LiveVO> attenTimeList;
}
