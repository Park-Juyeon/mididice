package com.mididice.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mididice.util.RandomString;

/* 
	?��?�� ?��?��로드 �? 결과?���? 컨트롤러
	Request : (/res?filename=?��?���?)  ==> (/res/randomstring) ==> mp3?��?�� ?��?��, ?��?�� ?��
*/

@Controller
public class FileController {
	
	RandomString r = new RandomString();
	
	private static final int offset = 25;
	
	/*	
	 	midi?��?�� 병합 ?��, mp3?��?���? �??��?��?�� ?��?��명을 ?��겨받?��?�� ?��?��!!^^
		filename?�� get방식?���? ?��겨주?��?�� ?�� (?�� : /res?filename=?��?���?)
	*/
	
	@RequestMapping(value = "/res", method=RequestMethod.GET)
	public String redirectResult(@RequestParam("filename")String filename, RedirectAttributes redi){
		String enc;
		
		if(filename.indexOf('.')==-1){
			//?��?��?��름이 ?��?��?���? ?��?��경우
			enc = r.encrypt(filename, offset);
		}else{
			//?��?��?��름이 ?��?��?���? ?��?��경우
			enc = r.encrypt(filename.substring(0, filename.length()-4), offset);
		}
		//System.out.println(enc);
		redi.addAttribute("name", "name");
		return "redirect:/res/"+enc;
	}
	
	//?��?���? �??�� url ?��?�� (?��: /res/randomstring)
	@RequestMapping(value = "/res/{enc}", method=RequestMethod.GET)
	public String resultUrl(Model model, @PathVariable String enc){
		String filen = r.decrypt(enc, offset);
		model.addAttribute("enc", enc);
		model.addAttribute("filename",filen);
		String playPath = "../resources/save/";
		model.addAttribute("p",playPath);
		model.addAttribute("resImg","res_"+enc+".jpg");
		return "result"; //결과?��?�� ==> result file�? �?경해?��?��
	}
	
	//?��?�� ?��?��로드
	@RequestMapping(value="/res/download.do", method=RequestMethod.GET)
	public ModelAndView download(@RequestParam("fe") String enc, HttpServletRequest request
			) throws Exception{
		
		//최종 mp3?��?��?�� 경로
		String realPath = request.getSession().getServletContext().getRealPath("/resources/save/");

		//File down = new File(realPath+r.decrypt(enc, offset)+".mid"); // ==> mp3�? �?경해?��?��
		File down = new File(realPath+r.decrypt(enc, offset)+".mp3");
		
		//System.out.println(realPath+r.decrypt(enc, offset));
		if(!down.canRead()){
			throw new Exception("?��?��?�� 찾을?�� ?��?��?��?��(^_^)");
			
		}
		return new ModelAndView("download","downloadFile",down);
	}
	
	//?��?�� ?��?��로드
		@RequestMapping(value="/res/download2.do", method=RequestMethod.GET)
		public ModelAndView download2(@RequestParam("m") String m, HttpServletRequest request
				) throws Exception{
			
			//최종 mp3?��?��?�� 경로
			String realPath = request.getSession().getServletContext().getRealPath("/resources/mp4/");

			//File down = new File(realPath+r.decrypt(enc, offset)+".mid"); // ==> mp3�? �?경해?��?��
			File down = new File(realPath+m+".mp4");
			
			//System.out.println(realPath+r.decrypt(enc, offset));
			if(!down.canRead()){
				throw new Exception("?��?��?�� 찾을?�� ?��?��?��?��(^_^)");
				
			}
			return new ModelAndView("download","downloadFile",down);
		}
	
}
