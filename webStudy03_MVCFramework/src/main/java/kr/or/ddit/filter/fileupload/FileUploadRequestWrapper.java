package kr.or.ddit.filter.fileupload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

public class FileUploadRequestWrapper extends HttpServletRequestWrapper{
	
	private Map<String, List<MultiPartFile>> fileMap; 
	
	public FileUploadRequestWrapper(HttpServletRequest request) {
		super(request);
		fileMap = new LinkedHashMap<>();
		parseRequest(request);
	}

	private void parseRequest(HttpServletRequest request) {
		try {
			Collection<Part> parts = request.getParts();
			for(Part tmp : parts) {
				if(tmp.getContentType()==null) continue;
				String name = tmp.getName();
				List<MultiPartFile> already = fileMap.get(name);
				if(already==null) {
					already = new ArrayList<>();
					fileMap.put(name, already);
				}
				already.add(new MultiPartFile(tmp));
			}
		} catch (IOException | ServletException e) {
			throw new RuntimeException(e);
		}
	}
	
	public MultiPartFile getFile(String name){
		List<MultiPartFile> already = fileMap.get(name);
		MultiPartFile file = null;
		if(already!=null && already.size() > 0) {
			file = already.get(0);
		}
		return file;
	}
	public List<MultiPartFile> getFiles(String name) {
		return fileMap.get(name);
	}
	
	public Enumeration<String> getFileName() {
		final Iterator<String> it =  fileMap.keySet().iterator();
		return new Enumeration<String>() {
			@Override
			public boolean hasMoreElements() {
				return it.hasNext();
			}
			@Override
			public String nextElement() {
				return it.next();
			}
		};
	}
	
	public Map<String, List<MultiPartFile>> getFileMap() {
		return fileMap;
	}
	
	public void deleteAll() throws IOException {
		for(Entry<String, List<MultiPartFile>> entry : fileMap.entrySet()) {
			for(MultiPartFile tmp : entry.getValue()) {
				tmp.delete();
			}
		}
	}
	
}
