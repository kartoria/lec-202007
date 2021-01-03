package kr.or.ddit.filter.fileupload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

/**
 * Part wrapper 로 사용할 adapter
 *
 */
public class MultipartFile implements Part{
	public MultipartFile(Part adaptee) {
		this.adaptee = adaptee;
		this.savename = UUID.randomUUID().toString();
	}
	private Part adaptee;
	
	public byte[] getBytes() throws IOException{
		try(
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = getInputStream();
		){
			IOUtils.copy(is, baos);
			return baos.toByteArray();
		}		
	}
	
	private String savename;
	public String getSavename() {
		return savename;
	}
	
	public String getOriginalFilename() {
		String disposition = adaptee.getHeader("Content-Disposition");
		int lastIdx = disposition.lastIndexOf("filename");
		String filename = null;
		if(lastIdx!=-1) {
			int idx = disposition.indexOf("=", lastIdx);
			filename = disposition.substring(idx+1).replace("\"", "");
		}
		return filename;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return adaptee.getInputStream();
	}

	@Override
	public String getContentType() {
		return adaptee.getContentType();
	}

	@Override
	public String getName() {
		return adaptee.getName();
	}

	@Override
	public String getSubmittedFileName() {
		return adaptee.getSubmittedFileName();
	}

	@Override
	public long getSize() {
		return adaptee.getSize();
	}
	
	public String saveToWithNewName(File saveFolder) throws IOException {
		File saveFile = new File(saveFolder, savename);
		write(saveFile.getAbsolutePath());
		return savename;
	}

	@Override
	public void write(String fileName) throws IOException {
		adaptee.write(fileName);
	}

	@Override
	public void delete() throws IOException {
		adaptee.delete();
	}

	@Override
	public String getHeader(String name) {
		return adaptee.getHeader(name);
	}

	@Override
	public Collection<String> getHeaders(String name) {
		return adaptee.getHeaders(name);
	}

	@Override
	public Collection<String> getHeaderNames() {
		return adaptee.getHeaderNames();
	}
}
