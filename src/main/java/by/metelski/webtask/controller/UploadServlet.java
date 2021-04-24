package by.metelski.webtask.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.SessionAttribute;

@WebServlet(name = "upload_servlet", urlPatterns = { "/upload" })
@MultipartConfig(fileSizeThreshold = 1024 *1024,
                 maxFileSize = 1024 * 1024 *5,
                 maxRequestSize = 1024 * 1024 *5 *5)
public class UploadServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		logger.log(Level.DEBUG, "Upload servlet");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uploadFileDir = "E:\\Down";
		File fileSaveDir = new File(uploadFileDir);
		if(!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		request.getParts().stream().forEach(part ->{
			try {
				String path = part.getSubmittedFileName();
				part.write(uploadFileDir + File.separator + path);
				request.setAttribute("upload_result","upload successfully");
			} catch (IOException e) {
				logger.log(Level.ERROR, "UPS... Cap we have a problem");
				request.setAttribute("upload_result","upload filed");
				// TODO: handle exception
			}
		});
		String page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
		request.getRequestDispatcher(page).forward(request,response);
	}

	@Override
	public void destroy() {

	}
}
