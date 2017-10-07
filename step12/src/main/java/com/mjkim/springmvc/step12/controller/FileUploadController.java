package com.mjkim.springmvc.step12.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.mjkim.springmvc.step12.model.FileBucket;
import com.mjkim.springmvc.step12.model.MultiFileBucket;
import com.mjkim.springmvc.step12.util.FileValidator;
import com.mjkim.springmvc.step12.util.MultiFileValidator;

@Controller
public class FileUploadController {
	static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	private static String UPLOAD_LOCATION="/tmp/";
	
	@Autowired
	FileValidator fileValidator;
	
	@Autowired
	MultiFileValidator multiFileValidator;
	
	@InitBinder("fileBucket")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}
	
	@InitBinder("multiFileBucket")
	protected void initBinderMultiFileBucket(WebDataBinder binder) {
		binder.setValidator(multiFileValidator);
	}
	
	@RequestMapping(value= {"/", "/welcome"}, method=RequestMethod.GET)
	public String getHomePage(ModelMap model) {
		return "welcome";
	}
	
	@RequestMapping(value="/singleUpload", method=RequestMethod.GET)
	public String getSingleUploadPage(ModelMap model) {
		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);
		return "singleFileUploader";
	}
	
	@RequestMapping(value="/singleUpload", method=RequestMethod.POST)
	public String singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException {
		if (result.hasErrors()) {
			logger.info("validation errors");
			return "singleFileUploader";
		} else {
			logger.info("fetching file");
			MultipartFile multipartFile = fileBucket.getFile();
			FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
			String fileName = multipartFile.getOriginalFilename();
			model.addAttribute("fileName", fileName);
			return "success";
		}
	}
	
	@RequestMapping(value="/multiUpload", method=RequestMethod.GET)
	public String getMultiUploadPage(ModelMap model) {
		MultiFileBucket filesModel = new MultiFileBucket();
		model.addAttribute("multiFileBucket", filesModel);
		return "multiFileUploader";
	}
	
	@RequestMapping(value="/multiUpload", method=RequestMethod.POST)
	public String multiFileUpload(@Valid MultiFileBucket multiFileBucket, BindingResult result, ModelMap model) throws IOException {
		if (result.hasErrors()) {
			logger.info("validation erros in multi upload");
			return "multiFileUploader";
		} else {
			logger.info("fetching files");
			List<String> fileNames = new ArrayList<String>();
			
			for (FileBucket bucket : multiFileBucket.getFiles()) {
				FileCopyUtils.copy(bucket.getFile().getBytes(), new File(UPLOAD_LOCATION + bucket.getFile().getOriginalFilename()));
				fileNames.add(bucket.getFile().getOriginalFilename());
			}
			
			model.addAttribute("fileNames", fileNames);
			return "multiSuccess";
		}
	}
}
