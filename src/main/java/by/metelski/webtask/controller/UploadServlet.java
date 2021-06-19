package by.metelski.webtask.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Message;
import by.metelski.webtask.command.ParameterAndAttribute;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet for file uploading
 * @author Yauhen Metelski
 *
 */
@WebServlet(name = "upload", urlPatterns = { "/upload" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024
		* 100)
public class UploadServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger();
	private static final String SAVE_DIR = "images";

	@Override
	public void init() throws ServletException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.DEBUG, "Upload servlet");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String appPath = request.getServletContext().getRealPath("");
		logger.log(Level.DEBUG, "appPath:" + appPath);
		String savePath = appPath + File.separator + SAVE_DIR;
		logger.log(Level.DEBUG, "savePath:" + savePath);
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			logger.log(Level.DEBUG, "try to mkdir:");
			fileSaveDir.mkdirs();
		}
		request.getParts().stream().forEach(part -> {
			try {
				String path = part.getSubmittedFileName();
				part.write(savePath + File.separator + path);
				request.setAttribute(ParameterAndAttribute.UPLOAD_RESULT, Message.SUCCESSFUL);
			} catch (IOException e) {
				logger.log(Level.ERROR, "IOExcception in doPost, uploadServlet" + e);
				request.setAttribute(ParameterAndAttribute.UPLOAD_RESULT, Message.UNSUCCESSFUL);
			}
		});
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		request.getRequestDispatcher(page).forward(request, response);
	}

	@Override
	public void destroy() {

	}
}
