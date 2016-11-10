package com.travelzen.tops.mediaserver.servlet;

import com.travelzen.framework.core.dict.MediaState;
import com.travelzen.tops.mediaserver.consts.Consts.COMMAND;
import com.travelzen.tops.mediaserver.consts.Consts.Header;
import com.travelzen.tops.mediaserver.consts.Identifier;
import com.travelzen.tops.mediaserver.dao.impl.MediaMongoBaseDao;
import com.travelzen.tops.mediaserver.db.projo.Image;
import com.travelzen.tops.mediaserver.db.projo.Media;
import com.travelzen.tops.mediaserver.db.projo.MediaType;
import com.travelzen.tops.mediaserver.util.HttpServletHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * 上传附件
 *
 * @author jiangning cui
 *
 */
public class UploadImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(UploadImageServlet.class);

	private static MediaMongoBaseDao mediaMongoBaseDao = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/*
	 * post处理 (non-Javadoc)
	 *
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long st = System.currentTimeMillis();
		try {

			logger.info("remote ip = [{}]", request.getRemoteAddr());

			// head print
			if (logger.isDebugEnabled()) {
				HttpServletHelper.printHeader(request, logger); // why need to
																// print
				// header
				// information.debug?
			}

			// get head-information
			String mediaCmd = request.getHeader(Header.Media_Cmd.getValue());
			MediaState cmd = Identifier.identifier(mediaCmd); // 判断请求方法正确性

			if (!cmd.equals(MediaState.OK)) {
				// the command is valid
				response.setContentType("image/jpeg");
				response.setHeader("Status", cmd.toString());
				response.setHeader("Reason", "" + cmd.getValue());
				return;
			}

			// before process , to initial the data
			Media media = createMedia(request);
			// after process, to check the result

			String mediaType = request.getHeader(Header.Media_Type.getValue());
			MediaType mtype = MediaType.valueOf(mediaType.toUpperCase());
			if (mtype.equals(MediaType.CONTRACT)) {
				mediaMongoBaseDao.addContract(media);
			} else {
				mediaMongoBaseDao.addMedia(media);
			}
//			mediaMongoBaseDao.addMedia(media);
			// MediaStatus status = mresp.getStatus();
			// if (status.equals(MediaStatus.OK)) {
			//
			// } else {
			// response.sendError(404);
			// }
			// the command is valid
			response.setHeader("Status", MediaState.OK.toString());
			long et = System.currentTimeMillis();
			logger.info("used time is {} ms", (et - st));

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * create a media object by the reqeust parameter
	 *
	 * @param request
	 * @return
	 */
	public Media createMedia(HttpServletRequest request) {

		String mediaType = request.getHeader(Header.Media_Type.getValue());
		String mediaName = request.getHeader(Header.Media_Name.getValue());
		try {
			mediaName = URLDecoder.decode(mediaName, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("error occured when decode [Media-Name]", e1);
			e1.printStackTrace();
		}
		String mediaId = request.getHeader(Header.Media_Id.getValue());
		String length = request.getHeader("Content-Length");
		String mediaCmd = request.getHeader(Header.Media_Cmd.getValue());

		Media media = null;

		// check metadata
		MediaType mtype = MediaType.valueOf(mediaType.toUpperCase());
		if (mtype.equals(MediaType.IMAGE) || mtype.equals(MediaType.CONTRACT)) {
			media = new Image();
		} else {
			return null;
		}
		if (COMMAND.add.toString().equals(mediaCmd) || COMMAND.crop.toString().equals(mediaCmd)) {
			String type = null;
			String filename = mediaName;
			if (mediaName != null) {
				type = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
				type = type.toUpperCase();

			}
			try {
				media.setInputStream(request.getInputStream());
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			media.setFilename(filename);
			media.setMediaId(mediaId);
			media.setType(type);
			media.setCreateTime(new Date());
			media.setLength(Integer.valueOf(length));

		} else if (COMMAND.get.toString().equals(mediaCmd)) {
			media.setMediaId(mediaId);
		}

		return media;
	}

	@Override
	public void init() throws ServletException {
		try {
			ServletContext application = getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
			mediaMongoBaseDao = (MediaMongoBaseDao) webApplicationContext.getBean("mediaMongoBaseDao");
		} catch (Exception e) {
			logger.error("", e);
		}

	}

}
