package com.travelzen.tops.mediaserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.travelzen.tops.mediaserver.service.ImageLoadService;

/**
 * Servlet implementation class UploadRelation
 */
public class UploadRelation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger LOG = LoggerFactory.getLogger(UploadRelation.class);
	private ImageLoadService imageLoadService = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadRelation() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageUrl = request.getParameter("imageUrl");
		if (imageUrl == null) {
			response.sendError(404);
			return;
		}
		String mediaId = imageLoadService.putImageRelations(imageUrl);
		PrintWriter writer = response.getWriter();
		// writer.write("\n" + request.getRequestURL());
		// String basePath = request.getScheme() + "://" +
		// request.getServerName() + ":" + request.getServerPort() +
		// request.getContextPath() + "/";
		writer.write(request.getContextPath() + "imageservice?mediaImageId=" + mediaId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		try {
			ServletContext application = getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
			imageLoadService = (ImageLoadService) webApplicationContext.getBean("imageLoadService");
		} catch (BeansException e) {
			LOG.error(e.getMessage(), e);
		}

	}
}
