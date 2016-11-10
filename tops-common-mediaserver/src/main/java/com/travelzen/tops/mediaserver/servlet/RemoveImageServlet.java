package com.travelzen.tops.mediaserver.servlet;

import com.travelzen.framework.core.dict.MediaState;
import com.travelzen.tops.mediaserver.consts.Consts.COMMAND;
import com.travelzen.tops.mediaserver.consts.Consts.Header;
import com.travelzen.tops.mediaserver.consts.Identifier;
import com.travelzen.tops.mediaserver.dao.impl.MediaMongoBaseDao;
import com.travelzen.tops.mediaserver.db.projo.MediaType;
import com.travelzen.tops.mediaserver.util.HttpServletHelper;

import org.apache.commons.lang3.StringUtils;
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

/**
 * 删除文件
 *
 * @author hu
 *
 */
public class RemoveImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(RemoveImageServlet.class);

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
			COMMAND cmd = (COMMAND) Identifier.getEnum(COMMAND.none, mediaCmd);
			MediaState state = null;
			String mediaId = request.getHeader(Header.Media_Id.getValue());
			if(StringUtils.isBlank(mediaId) || cmd == null || !COMMAND.delete.equals(cmd)){
				
				state = MediaState.BAD_PARAMETER;
			}else{
				state =  MediaState.OK;
			}

			if (!state.equals(MediaState.OK)) {
				// the command is valid
				response.setContentType("image/jpeg");
				response.setHeader("Status", state.toString());
				response.setHeader("Reason", "" + state.getValue());
				return;
			}

			String mediaType = request.getHeader(Header.Media_Type.getValue());
			MediaType mtype = MediaType.valueOf(mediaType.toUpperCase());
			if (mtype.equals(MediaType.CONTRACT)) {
				mediaMongoBaseDao.removeContractById(mediaId);
			} else {
				mediaMongoBaseDao.removeImageById(mediaId);
			}
			
			response.setHeader("Status", MediaState.OK.toString());
			long et = System.currentTimeMillis();
			logger.info("delete image used time is {} ms", (et - st));

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
		}
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
