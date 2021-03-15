package kr.or.ddit.admin.student.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeModelVO {
 
    private String id;         // 부모
    private String pid;        // 자식
    private String name;       // 트리이름
    private String icon;	   // 아이콘
    private String open;       // 트리 OPEN 여부, true/false
    private String isParent;   // 부모 여부, true/false
    private String web;        // 링크 URL
    
//    public TreeModel(String id, String pid, String name, String open, String isParent) {
//        super();
//        this.id = id;
//        this.pid = pid;
//        this.name = name;
//        this.open = open;
//        this.isParent = isParent;
//        this.web = web;
//    }
}