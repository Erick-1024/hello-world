package com.cana.vbam.front.biz.controller.asset;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.asset.api.IABSSpecialProgramApi;
import com.cana.asset.api.IAssetArchivesManagementApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.SpecialProgramDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestDTO;
import com.cana.vbam.common.asset.enums.BasicAssetType;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.netdisk.dto.GetMediaListRequest;
import com.cana.vbam.common.netdisk.dto.MediaDownloadDTO;
import com.cana.vbam.common.netdisk.dto.MediaDownloadRequest;
import com.cana.vbam.common.netdisk.dto.SaveMediaRequest;
import com.cana.vbam.common.netdisk.enums.Module;
import com.cana.vbam.common.netdisk.enums.Type;
import com.cana.vbam.common.utils.FrontExceptionHandler;

@Controller
@RequestMapping("/asset/archivesManagement")
public class AssetArchivesManagementController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAssetArchivesManagementApi assetArchivesManagementApiImpl;
	
	@Resource
	private IABSSpecialProgramApi assetSpecialProgramApi;
	
	@RequestMapping(value = "/saveFile", method = { RequestMethod.POST })
	public void saveMedia(MultipartFile file, String path, Type type, String groupId, HttpServletResponse httpServletResponse) throws IOException {
		try {
			SaveMediaRequest saveMediaRequest = new SaveMediaRequest();
			saveMediaRequest.setContents(file.getBytes());
			saveMediaRequest.setFileName(file.getOriginalFilename());
			saveMediaRequest.setCreatorId(SecurityContextUtils.getOperatorId());
			saveMediaRequest.setPath(path);
			saveMediaRequest.setModule(Module.ASSET_ARCHIVES);
			saveMediaRequest.setType(type);
			saveMediaRequest.setGroupId(groupId);
			assetArchivesManagementApiImpl.saveMedia(saveMediaRequest);
			httpServletResponse.getWriter().write("{\"status\":\"SUCCESS\",\"message\":\"文件保存成功！\"}");
		} catch (Exception e) {
			httpServletResponse.getWriter().write("{\"status\":\"FAILED\",\"message\":\"文件保存失败！\"}");
		}
	}
	
	@RequestMapping(value = "/saveDirectory", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> saveDirectory(String path, Type type, String groupId, String fileName) {
		try {
			SaveMediaRequest saveMediaRequest = new SaveMediaRequest();
			saveMediaRequest.setFileName(fileName);
			saveMediaRequest.setCreatorId(SecurityContextUtils.getOperatorId());
			saveMediaRequest.setPath(path);
			saveMediaRequest.setModule(Module.ASSET_ARCHIVES);
			saveMediaRequest.setType(type);
			saveMediaRequest.setGroupId(groupId);
			return ObjectResult.success("目录保存成功", assetArchivesManagementApiImpl.saveMedia(saveMediaRequest));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "/getMediaList", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> getMediaList(GetMediaListRequest getMediaListRequest) {
		try {
			getMediaListRequest.setModule(Module.ASSET_ARCHIVES);
			return ObjectResult.success("获取媒体文件列表成功", assetArchivesManagementApiImpl.getMediaList(getMediaListRequest, SecurityContextUtils.getOperatorId()));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "/download", method = { RequestMethod.GET })
	public void download(HttpServletRequest request, HttpServletResponse httpServletResponse, String[] ids, String groupId) {
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		//设置响应头,MIMEtype告诉浏览器传送的文件类型
		httpServletResponse.setContentType("application/octet-stream");
		try {
			//通过httpServletResponse获得ServletOutputStream对象
			ServletOutputStream sos = httpServletResponse.getOutputStream();
			List<MediaDownloadDTO> mediaDownloadDTOs;
			String userId = SecurityContextUtils.getOperatorId();
			MediaDownloadRequest mediaDownloadRequest = new MediaDownloadRequest();
			mediaDownloadRequest.setGroupId(groupId);
			mediaDownloadRequest.setModule(Module.ASSET_ARCHIVES);
			if(ids != null && ids.length != 0) {
				boolean headerOk = false;
				ZipOutputStream out = null;
				for (String id : ids) {
					mediaDownloadRequest.setId(id);
					mediaDownloadDTOs = assetArchivesManagementApiImpl.download(mediaDownloadRequest, userId);
					if(!headerOk) {
						MediaDownloadDTO mediaDownloadDTO = mediaDownloadDTOs.get(0);
						if(ids.length == 1 && mediaDownloadDTOs.size() == 1 && mediaDownloadDTO.getType() == Type.FILE) {
							httpServletResponse.setHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", convertFileName(userAgent, mediaDownloadDTO.getName())));
							httpServletResponse.addIntHeader("Content-Length", mediaDownloadDTO.getContents().length);// 设置下载文件大小
							httpServletResponse.getOutputStream().write(mediaDownloadDTO.getContents());
							httpServletResponse.getOutputStream().flush();
							httpServletResponse.flushBuffer();
							break;
						} else {
							String fileName = mediaDownloadDTO.getName();
							if(mediaDownloadDTOs.size() == 1 && mediaDownloadDTO.getType() == Type.DIRECTORY)
								httpServletResponse.setHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", convertFileName(userAgent, fileName) + ".zip"));
							else if(mediaDownloadDTO.getType() == Type.DIRECTORY)
								httpServletResponse.setHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", convertFileName(userAgent, "【批量下载】" + fileName + "等") + ".zip"));
							else
								httpServletResponse.setHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", convertFileName(userAgent, "【批量下载】" + fileName.substring(0, fileName.lastIndexOf(".")) + "等") + ".zip"));
							out = new ZipOutputStream(new BufferedOutputStream(sos));
							ZipFiles(out, mediaDownloadDTOs);
						}
						headerOk = true;
					} else
						ZipFiles(out, mediaDownloadDTOs);
				}
				if(out != null) {
					out.closeEntry();
					out.close();
				}
			} else {
				mediaDownloadDTOs = assetArchivesManagementApiImpl.download(mediaDownloadRequest, userId);
				MediaDownloadDTO mediaDownloadDTO = mediaDownloadDTOs.get(0);
				if(mediaDownloadDTOs.size() == 1 && mediaDownloadDTO.getType() == Type.FILE) {
					httpServletResponse.setHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", convertFileName(userAgent, mediaDownloadDTO.getName())));
					httpServletResponse.addIntHeader("Content-Length", mediaDownloadDTO.getContents().length);// 设置下载文件大小
					httpServletResponse.getOutputStream().write(mediaDownloadDTO.getContents());
					httpServletResponse.getOutputStream().flush();
					httpServletResponse.flushBuffer();
				} else {
					String fileName = mediaDownloadDTO.getName();
					if(mediaDownloadDTOs.size() == 1)
						httpServletResponse.setHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", convertFileName(userAgent, fileName) + ".zip"));
					else if(mediaDownloadDTO.getType() == Type.DIRECTORY)
						httpServletResponse.setHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", convertFileName(userAgent, "【批量下载】" + fileName + "等") + ".zip"));
					else
						httpServletResponse.setHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", convertFileName(userAgent, "【批量下载】" + fileName.substring(0, fileName.lastIndexOf(".")) + "等") + ".zip"));
					//获得ZipOutputStream对象
					ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(sos));
					ZipFiles(out, mediaDownloadDTOs);
					out.closeEntry();
					out.close();
				}
			}
		} catch (IOException e) {
			logger.error("下载错误", e);
		}
}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	@ResponseBody
	public ObjectResult<?> delete(@RequestParam String id) {
		try {
			assetArchivesManagementApiImpl.removeMedia(id, SecurityContextUtils.getOperatorId());
			return ObjectResult.success("删除成功");
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "/goto/list", method = { RequestMethod.GET })
	public String gotoArchivesManagementList(Model model) {
		logger.info("进入档案列表页面");
		model.addAttribute("specialProgramStatuss", SpecialProgramStatus.values());
		model.addAttribute("basicAssetTypes", BasicAssetType.values());
		return "page/asset/archivesmanagement/list";
	}
	
	//专项计划列表
	@RequestMapping(value = "/querySpecialProgram", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> querySpecialProgramList(SpecialProgramListRequestDTO request) {
		try {
			request.setUserId(SecurityContextUtils.getOperatorId());
			return assetSpecialProgramApi.querySpecialProgramList(request);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/goto/manage", method = { RequestMethod.GET })
	public String gotoManage(Model model, String id) {
		logger.info("进入档案管理页面");
		SpecialProgramDTO specialProgramDTO = assetSpecialProgramApi.getSpecialProgramById(id, SecurityContextUtils.getOperatorId());
		model.addAttribute("id", id);
		model.addAttribute("specialProgramName", specialProgramDTO.getSpecialProgramName());
		model.addAttribute("underlyingAssetTypeDesc", specialProgramDTO.getUnderlyingAssetType().desc());
		model.addAttribute("status", specialProgramDTO.getStatus());
		return "page/asset/archivesmanagement/manage";
	}
	
	private void ZipFiles(ZipOutputStream out, List<MediaDownloadDTO> mediaDownloadDTOs) throws IOException {
		for (MediaDownloadDTO mediaDownloadDTO : mediaDownloadDTOs) {
			if(Type.DIRECTORY == mediaDownloadDTO.getType()) {
				out.putNextEntry(new ZipEntry(mediaDownloadDTO.getPath() + mediaDownloadDTO.getName() + "/"));
			} else {
				out.putNextEntry(new ZipEntry(mediaDownloadDTO.getPath() + mediaDownloadDTO.getName()));
				out.write(mediaDownloadDTO.getContents());
			}
		}
	}

	private String convertFileName(String userAgent, String fileName) throws UnsupportedEncodingException {
		if (userAgent.matches(".*((MSIE)|(TRIDENT)|(EDGE)).*"))
			return URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");// IE浏览器
		else
			return new String(fileName.getBytes("UTF-8"), "ISO8859-1");
	}

}
