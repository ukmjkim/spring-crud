package com.mjkim.springmvc.step12.model;

import java.util.List;
import java.util.ArrayList;

public class MultiFileBucket {
	List<FileBucket> files = new ArrayList<FileBucket>();
	
	public MultiFileBucket() {
		files.add(new FileBucket());
		files.add(new FileBucket());
		files.add(new FileBucket());
	}
	
	public List<FileBucket> getFiles() {
		return files;
	}
	
	public void setFiles(List<FileBucket> files) {
		this.files = files;
	}
}
