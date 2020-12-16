package kr.or.ddit.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Database property 하나의 정보를 담기 위한 Domain Layer 
 *
 */
//@Getter
//@Setter
@EqualsAndHashCode(of={"property_name"})
@ToString(exclude={"bytes"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatabasePropertyVO implements Serializable{
	private String property_name;
	private String property_value;
	private String description;
	
	@JsonIgnore
	private transient byte[] bytes;
	
	
	
}
