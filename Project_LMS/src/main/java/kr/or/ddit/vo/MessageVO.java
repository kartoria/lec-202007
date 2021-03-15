package kr.or.ddit.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MessageVO {
	@NotBlank@Size(max=10) private String recipient;   //받는사람
	@NotBlank@Size(max=50) private String msgContent;   //내용
	@Size(max=7) private String msgTime;   //보낸시간
	@NotNull @Min(0) private Integer msgNum;   //메세지번호
}
