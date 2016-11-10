package com.travelzen.tops.mediaserver.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.travelzen.tops.mediaserver.consts.Consts;
import com.travelzen.tops.mediaserver.consts.Consts.FileType;
import com.travelzen.tops.mediaserver.dao.impl.MediaMongoBaseDao;
import com.travelzen.tops.mediaserver.db.projo.Media;
import com.travelzen.tops.mediaserver.db.projo.MediaType;
import com.travelzen.tops.mediaserver.exception.MediaServerException;
import com.travelzen.tops.mediaserver.service.ImageLoadService;

public class ImageServlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = -1405685050282729807L;

	private static CacheManager cacheManager = null;
	private static Cache imageCache = null;
	private static Cache invalidImageIdCache = null;
	// private static LongSerializer longKeySerializer = LongSerializer.get();
	private static MediaMongoBaseDao mediaMongoBaseDao = null;

	private static ImageLoadService imageLoadService = null;

	private static Logger logger = LoggerFactory.getLogger(ImageServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long st = System.currentTimeMillis();
		logger.info("[begin doGet]");
		String mediaImageId = request.getParameter("mediaImageId");
		String mediaType = request.getParameter("mediaType");
		MediaType mtype = null;
		if (mediaType == null) {
			mtype = MediaType.IMAGE;
		} else {
			mtype = MediaType.valueOf(mediaType.toUpperCase());
		}

		if (mediaImageId == null || mediaImageId.length() <= 0) {
			response.sendError(404);
			return;
		}
		byte[] contentBytes;
		try {
			Media media = findContentFromCache(mediaImageId, mtype);
			if (media == null) {
				response.sendError(404);
				return;
			}
			contentBytes = media.getContent();

			if (invalidImageIdCache.get(mediaImageId) != null) {
				response.sendError(404);
				return;
			}

			if (contentBytes == null || contentBytes.length <= 0) {
				invalidImageIdCache.put(new Element(mediaImageId, "1"));
				logger.error("[No found the value relate to the '{}'] ", mediaImageId);
				response.sendError(404);
				return;
			}
			if(mtype.equals(MediaType.VIDEO) && StringUtils.isNotBlank(media.getType())){
				
				try{
					FileType fileType = FileType.valueOf(media.getType());
					response.setContentType(fileType.contentType());
					logger.info("文件类型:{}, 内容格式:{}",fileType.name(), fileType.contentType());
					if(fileType.equals(FileType.DOC) || fileType.equals(FileType.DOCX)){
						String fileName = request.getParameter("mediaName");
						String userAgent = request.getHeader("User-Agent").toUpperCase();
						if (StringUtils.isNotBlank(fileName)) {
							fileName = URLDecoder.decode(fileName, "UTF-8");
							if (userAgent.contains("MSIE")) {
								fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
							}
						} else {
							fileName = media.getFilename();
							if (userAgent.matches(".*((MSIE)|(TRIDENT)|(EDGE)).*"))
								fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");// IE浏览器
							else
								fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
						}
						if (StringUtils.isNotBlank(fileName)) {
							// 设置文件名
							response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
						}
					}
				}catch(Exception e){
					mtype = MediaType.DOWNLOAD;
				}
			}
			if (mtype.equals(MediaType.IMAGE)) {
				response.setContentType("image/jpeg");
			} else if (mtype.equals(MediaType.DOWNLOAD)) {
				String fileName = request.getParameter("mediaName");
				String userAgent = request.getHeader("User-Agent").toUpperCase();
				if (StringUtils.isNotBlank(fileName)) {
					fileName = URLDecoder.decode(fileName, "UTF-8");
					if (userAgent.contains("MSIE")) {
						fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
					}
				} else {
					fileName = media.getFilename();
					if (userAgent.matches(".*((MSIE)|(TRIDENT)|(EDGE)).*"))
						fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");// IE浏览器
					else
						fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				}
				if (StringUtils.isNotBlank(fileName)) {
					// 设置文件名
					response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
				}
				response.addIntHeader("Content-Length", contentBytes.length);// 设置下载文件大小
				response.setContentType("application/octet-stream");// 设置文件类型
			} else if (mtype.equals(MediaType.CONTRACT)) {
				response.setContentType("text/plain");
			}
			try {
				response.getOutputStream().write(contentBytes);
			} catch (Exception e) {
				logger.warn("", e.getMessage());
			}
		} catch (Exception e) {
			logger.error("", e);
			response.sendError(404);
		} finally {
			if (response.getOutputStream() != null) {
				response.getOutputStream().flush();
				response.flushBuffer();
			}
		}
		long ed = System.currentTimeMillis();
		logger.info("[end doGet cost time = {}.ms]", (ed - st));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		try {
			ServletContext application = getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
			cacheManager = (CacheManager) webApplicationContext.getBean("ehCacheManager");
			imageCache = cacheManager.getCache("imageCache");
			invalidImageIdCache = cacheManager.getCache("invalidImageIdCache");
			mediaMongoBaseDao = (MediaMongoBaseDao) webApplicationContext.getBean("mediaMongoBaseDao");
			imageLoadService = (ImageLoadService) webApplicationContext.getBean("imageLoadService");
			// cassandraHelper = new CassandraHelper(false);

		} catch (Exception e) {
			logger.error("", e);
		}

	}

	@SuppressWarnings("deprecation")
	private Media findContentFromCache(String mediaImageId, MediaType mediaType) throws Exception {

		Media result = null;

		long startTime = System.currentTimeMillis();

		if (imageCache == null) {
			imageCache = cacheManager.getCache("imageCache");
		}

		if (imageCache == null) {
			throw new MediaServerException("the ehcache instance is null.");
		}

		if (imageCache.get(mediaImageId) != null) {

			result = (Media) imageCache.get(mediaImageId).getValue();
			if (result != null) {
				long cacheEnd = System.currentTimeMillis();
				logger.info("[Get data from cache][Cost time = {}]", (cacheEnd - startTime));
				return result;
			}
		}

		if (mediaImageId != null && mediaImageId.length() > 0) {
			if (mediaType.equals(MediaType.CONTRACT)) {
				result = mediaMongoBaseDao.getContract(mediaImageId);
			} else {
				result = mediaMongoBaseDao.getMedia(mediaImageId);
			}
			long interval = Consts.UPDATE_FREQUENCY + 1;
			if (result != null && result.getCreateTime() != null) {
				interval = new Date().getTime() / 1000 - result.getCreateTime().getTime() / 1000;
			}
			if (interval > Consts.UPDATE_FREQUENCY) {
				boolean isUpdate = imageLoadService.setImageIntoMongo(mediaImageId);
				if (isUpdate) {
					result = mediaMongoBaseDao.getMedia(mediaImageId);
				}
				if (result != null) {
					long cassandraEnd = System.currentTimeMillis();
					logger.info("[Get data from cassandra][Cost time = {}]", (cassandraEnd - startTime));
					return result;
				}
			} else {
				long cassandraEnd = System.currentTimeMillis();
				logger.info("[Get data from cassandra][Cost time = {}]", (cassandraEnd - startTime));
				return result;
			}
			imageCache.put(new Element(mediaImageId, result));
		}

		long end = System.currentTimeMillis();
		logger.info("[findContentFromCache][Cost time = {}]", (end - startTime));
		return null;
	}

}
