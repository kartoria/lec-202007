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

public class FileUploadRequestWrapper extends HttpServletRequestWrapper {
	
	private Map<String, List<MultipartFile>> fileMap;

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
				List<MultipartFile> already = fileMap.get(name);
				if(already==null) {
					already = new ArrayList<>();
					fileMap.put(name, already);
				}
				already.add(new MultipartFile(tmp));
			}			
		} catch (IOException | ServletException e) {
			throw new RuntimeException(e);
		}
	}

	
	public MultipartFile getFile(String name){
		List<MultipartFile> already = fileMap.get(name);
		MultipartFile file = null;
		if(already!=null && already.size()>0) {
			file = already.get(0);
		}
		return file;
	}
	
	public List<MultipartFile> getFiles(String name) {
		return fileMap.get(name);
	}
	
	public Enumeration<String> getFileNames() {
		final Iterator<String> it = fileMap.keySet().iterator();
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
	
	public Map<String, List<MultipartFile>> getFileMap() {
		return fileMap;
	}
	
	public void deleteAll() throws IOException {
		for(Entry<String, List<MultipartFile>> entry : fileMap.entrySet()) {
			for(MultipartFile tmp : entry.getValue()) {
				tmp.delete();
			}
		}
	}
}















