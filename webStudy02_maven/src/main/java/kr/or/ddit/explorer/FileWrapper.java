package kr.or.ddit.explorer;

import java.io.File;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of="key")
public class FileWrapper implements Comparable<FileWrapper>{
	
	public FileWrapper(File adaptee, String relativePath) {
		this.title = adaptee.getName();
		this.key = this.title;
		this.expanded = adaptee.isDirectory()? false:true;
		this.extraClasses = adaptee.isDirectory()? "folder":"file";
		this.folder = adaptee.isDirectory();
		this.lazy = this.folder;
		this.tooltip = title;
		
	}
	private String title;
	private String key;
	private boolean expanded;
	private String extraClasses;
	private boolean folder;
	private String statusNodeType;
	private boolean lazy;
	private boolean selected;
	private String tooltip;
	
	@Override
	public int compareTo(FileWrapper o) {
		int ret = 0;
		if(this.folder && !o.isFolder())      ret = -1;
		else if(!this.folder && o.isFolder()) ret = 1;
		else ret = this.title.toLowerCase().compareTo(o.title.toLowerCase());
		return ret;
	}
	
}
