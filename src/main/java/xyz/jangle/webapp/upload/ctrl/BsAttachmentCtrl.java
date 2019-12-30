package xyz.jangle.webapp.upload.ctrl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件上传存放管理 控制层
 * 
 * @author jangle E-mail: jangle@jangle.xyz
 * @version Jangle生成工具v1.1
 */
@Controller
@RequestMapping("/bsAttachmentCtrl")
public class BsAttachmentCtrl {

	// 批量上传war
	@RequestMapping(value = "/uploadROOT", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadROOT(HttpServletRequest request,
			@RequestParam("file") MultipartFile[] files) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		if (files == null) {
//			logger.error("未获取到附件");
			map.put("msg", "未获取到附件");
		}
//		if (!bsUser2Service.currentLoggedIn().getModel().getJgCode().equals("jangle")) {
//			logger.error("非管理员进行了应用的发布:" + bsUser2Service.currentLoggedIn().getModel().getJgName());
//			return new HashMap<String, Object>();
//		}
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				try {
					writeToWebapps(files[i]);
				} catch (IOException e) {
//					logger.error("war文件写入时异常", e);
					System.out.println("war文件写入时异常");
					System.out.println(e.toString());
					map.put("msg", "war文件写入时异常"+e.toString());
					// 做相应的异常信息返回
				}
			} else {
//				logger.info("files is empty");
				System.out.println("上传的文件是空的");
				map.put("msg", "上传的文件是空的");
			}
		}
		return map;
	}

	/**
	 * 将文件写入本地磁盘
	 * 
	 * @param file
	 * @throws IOException
	 */
	private void writeToWebapps(MultipartFile file) throws IOException {
		System.out.println("开始进行文件写入");
//		File f = new File("/opt/apache-tomcat-7.0.90/webapps/" + file.getOriginalFilename());
		File f = new File("D:/d/p/c/" + file.getOriginalFilename());
		OutputStream out = new FileOutputStream(f);
		out.write(file.getBytes());
		out.flush();
		out.close();
		System.out.println("完成文件写入");
	}

}
