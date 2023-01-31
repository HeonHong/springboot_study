package user.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//@Component
@Controller
@RequestMapping(value = "user")
public class UserController2 {
	
	 @GetMapping(value = "uploadForm") // @RequestMapping(value = "/writeForm")차이
	 public String uploadForm() {
		return "user/uploadForm";
	}
	 
	 /*
	 @PostMapping(value="upload")
	 @ResponseBody
	 public void upload(@RequestParam MultipartFile img) {
		 //여기 있는 이름은 사진이 저장된 input태그와 변수명이 동일해야 받아올 수 있다. 
		 
		 //가상폴더. 이클립스 가상폴더 위치로 보내준다.
		 String filePath="E:/spring/workspace/chapter06_SpringWebMaven/src/main/webapp/WEB-INF/storage";
		 //자바에서 주소는 역슬래쉬 두 개로 표현
		 String fileName = img.getOriginalFilename();
		 
		 File file = new File(filePath, fileName);
		 
		 try {
			
			// FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
			 
			 img.transferTo(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//복사
		 
	 }
	 */
	 
	 
//	 upload가 1개일 때
//	 @PostMapping(value="upload")
//	 @ResponseBody
//	 public String upload(@RequestParam MultipartFile img, HttpSession session) {
//		 //컨테이너가 알아서 세션을 준다
//		 
//		 //실제 폴더에 넣기
//		 //이러면 이클립스 가상폴더에는 올라오지 않는다.
//		 //String filePath=session.getServletContext().getRealPath("storage");
//		 //이거 어디에 생기는 거지??
//		 String filePath=session.getServletContext().getRealPath("/WEB-INF/storage");
//
//		 System.out.println("실제폴더 : " + filePath);
//		 //자바에서 주소는 역슬래쉬 두 개로 표현
//		 String fileName = img.getOriginalFilename();
//		 
//		 File file = new File(filePath, fileName);
//		 
//		 try {
//			
//			// FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
//			 
//			 img.transferTo(file);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//복사
//		
//		 
//		 return "<img src='../storage/"+ fileName + "' width='300' height='300'/>";
//	 }
	 
	 //name="img" 2개 이상일 경우, 배열로 받아온다.
	 //<input type="file" name="img"></input>
	//<input type="file" name="img"></input> 이렇게 복수 input태그인 경우
		/*
		 * @PostMapping(value="upload")
		 * 
		 * @ResponseBody public void upload(@RequestParam MultipartFile[] img,
		 * HttpSession session) {
		 * 
		 * String filePath=session.getServletContext().getRealPath("/WEB-INF/storage");
		 * String fileName; File file;
		 * 
		 * if(img[0]!=null) { fileName= img[0].getOriginalFilename(); file = new
		 * File(filePath, fileName);
		 * 
		 * try { img[0].transferTo(file); } catch (IOException e) {
		 * 
		 * e.printStackTrace(); }//복사 }
		 * 
		 * if(img[1]!=null) { fileName= img[1].getOriginalFilename(); file = new
		 * File(filePath, fileName);
		 * 
		 * try { img[1].transferTo(file); } catch (IOException e) {
		 * 
		 * e.printStackTrace(); }//복사 } }
		 */
	 
	 //한 번에 여러 파일 선택
	 //<input type="file" name="img[]" multiple>
	 @PostMapping(value="upload")
	 @ResponseBody
	 public void upload(@RequestParam("img[]") List<MultipartFile> list, HttpSession session) {
	 
		String filePath=session.getServletContext().getRealPath("/storage");
	 	String fileName;
	 	File file;
	 	
	 	for(MultipartFile img : list) {
	 		
	 		fileName= img.getOriginalFilename();
	 		file = new File(filePath, fileName);
	 		try {
				 img.transferTo(file);
			} catch (IOException e) {

			e.printStackTrace();
			}
	 	}
	 	
	 }//upload
	 
 
	 @PostMapping(value="upload2", produces="text/html;charset-UTF-8")
	 @ResponseBody
	 public String upload2(@RequestParam MultipartFile img, HttpSession session) {
		 //컨테이너가 알아서 세션을 준다
		 
		 //실제 폴더에 넣기
		 //이러면 이클립스 가상폴더에는 올라오지 않는다.
		 //String filePath=session.getServletContext().getRealPath("storage");
		 //이거 어디에 생기는 거지??
		 String filePath=session.getServletContext().getRealPath("/storage");

	 	System.out.println("실제폴더 : " + filePath);
		 //자바에서 주소는 역슬래쉬 두 개로 표현
		 String fileName = img.getOriginalFilename();
		 
		 File file = new File(filePath, fileName);
		 
		 try {
			
			// FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
			 
			 img.transferTo(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//복사
		
		 
		 return "<img src='../storage/"+ fileName + "' width='300' height='300'/>";
	 }
	 
	 
}
