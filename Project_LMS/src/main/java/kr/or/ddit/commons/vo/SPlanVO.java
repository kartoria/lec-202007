package kr.or.ddit.commons.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = { "splanCode" })
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SPlanVO {
	private String splanCode ;
	private String splanName ;
	private Date   splanStart;
	private Date   splanEnd  ;
}
