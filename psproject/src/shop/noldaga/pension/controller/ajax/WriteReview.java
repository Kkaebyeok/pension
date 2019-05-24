package shop.noldaga.pension.controller.ajax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import shop.noldaga.board.service.BoardServiceImpl;
import shop.noldaga.board.vo.BoardImg;
import shop.noldaga.board.vo.BoardVo;
import shop.noldaga.member.vo.MemberVo;


@WebServlet("/writeReview")
public class WriteReview extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, String> respCode = new HashMap<>();
		if(req.getSession() == null || req.getSession().getAttribute("member") == null) { //미로그인 상태
			respCode.put("result","NOT-LOGIN");
			resp.getWriter().print(new Gson().toJson(respCode));
		}
		
		else if(ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(3 * 1024 * 1024);
			factory.setRepository(new File("d:\\upload\\temp"));
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(40 * 1024 * 1024);
			upload.setSizeMax(50 * 1024 * 1024);
			
			String uploadPath = getServletContext().getRealPath("") + File.separator + "upload";
			System.out.println(uploadPath);

			File uploadStorage = new File(uploadPath);
			
			if(!uploadStorage.isDirectory()) {
				uploadStorage.mkdirs();
			}
			
			try {
				List<FileItem> list = upload.parseRequest(req);
				if(list != null && list.size() > 0) {
					BoardVo boardVo = new BoardVo();
					List<BoardImg> imgList = new ArrayList<>();
					String email = ((MemberVo)req.getSession().getAttribute("member")).getEmail();
					boardVo.setEmail(email);
					
					
					for(FileItem item : list) {
						if(item.isFormField()) { //파일이 아닐때
							String value = new String (item.getString().getBytes("iso-8859-1"),"utf-8");
							System.out.println(value);
							switch (item.getFieldName()) {
							case "title":
								boardVo.setTitle(value);
								break;
							case "cont" :
								boardVo.setCont(value);
								break;
							case "score" :
								boardVo.setScore(Integer.parseInt(value));
								break;
							case "psidx" :
								boardVo.setPsIdx(Integer.parseInt(value));
								break;
							default:
								break;
							}
						}
						else {	//파일일때
							String originName = item.getName();
							if(!originName.equals("")) {
							UUID uuid = UUID.randomUUID();
							BoardImg boardImg = new BoardImg();

							boardImg.setOriginName(originName);

							String realName = new File(uuid + "_" + originName).getName();
							boardImg.setRealName(realName);
							
							File storeFile = new File(uploadPath + File.separator + realName);
							item.write(storeFile);
							//System.out.println(storeFile + " :: 저장됨");
							
							imgList.add(boardImg);
							}
						}
					}
					boardVo.setImgList(imgList);
					
					new BoardServiceImpl().writeReview(boardVo);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				respCode.put("result", "error");
				resp.getWriter().print(new Gson().toJson(respCode));
				
			}
			respCode.put("result", "success");
			resp.getWriter().print(new Gson().toJson(respCode));
		}
		else {
			// ?
		}
	}
}
	
