package com.ddim.happygo.web.admin.tinymce;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mdbs.util.FileUtil;
import com.mdbs.util.Id;
import com.mdbs.util.ObjectUtil;
import com.mdbs.util.WebUtil;

/**
 * 建立日期：2015年3月7日
 * 程式摘要：com.ddim.happygo.web.admin.tinymce<P> 
 * 類別名稱：UploadImgController.java<P>
 * 程式內容說明：編輯器上傳圖檔控制項<P>
 * @author Ringo
 * */
@Controller
@RequestMapping(value = "/admin/tinymce/uploadImg")
public class UploadImgController {
	
	private String uploadFilePath;
	@Value("${file.upload.path}")
	private void setFolder(String folder){
		uploadFilePath = folder + "tinymce/";
	}
	
	private String fileReadUrl;
	@Value("${file.read.url}")
	private void setURL(String url){
		fileReadUrl = url + "tinymce/";
	}
		
	@Value("${sync.folder}")
	private String syncPath;
	
	final String[] DISALLOWED_FIELDS = new String[] { "dummy" };
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
	  binder.setDisallowedFields(DISALLOWED_FIELDS);
	}
	
	@RequestMapping(value = "/upload")
	public @ResponseBody void upload(@ModelAttribute UploadImgForm uploadImgForm, HttpServletResponse response)
			throws Exception {

		JSONObject obj = new JSONObject();
		String errStr = null;

		if (uploadImgForm.getFile() != null && !uploadImgForm.getFile().isEmpty()) {
			if (uploadImgForm.getFile().getContentType().indexOf("image/") < 0) {
				errStr = "上傳的內容必須是圖片!";
			}
		} else {
			errStr = "請選擇圖檔上傳!";
		}

		if (!ObjectUtil.containsNonSpace(errStr)) {
			String originalFilename = uploadImgForm.getFile().getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
			String mainFilename = Id.dateId20();
			String mediaFilename = mainFilename + extName;
			File toFile = new File(uploadFilePath, WebUtil.removeControlCharacter(mediaFilename));
			FileUtil.createPathIfMissing(toFile);
			uploadImgForm.getFile().transferTo(toFile);

			obj.put("error", false);
			obj.put("path", fileReadUrl + mediaFilename);
		} else {
			obj.put("error", errStr);
		}
		response.setContentType("text/html");
		response.getWriter().write(WebUtil.removeControlCharacter(obj.toString()));
	}
}
