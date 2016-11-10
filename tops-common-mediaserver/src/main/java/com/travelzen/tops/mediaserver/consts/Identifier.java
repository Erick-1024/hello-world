package com.travelzen.tops.mediaserver.consts;

import com.travelzen.framework.core.dict.MediaState;
import com.travelzen.tops.mediaserver.consts.Consts.COMMAND;
import com.travelzen.tops.mediaserver.db.projo.Media;

public class Identifier {

	/**
	 * identify media parameter
	 * 
	 * @param command
	 * @param media
	 * @return
	 */
	public static MediaState identifier(String command, Media media) {

		if (media == null) {
			return MediaState.NONE;
		}

		if (command.equals(COMMAND.add)) {
			String mediaId = (String) media.getMediaId();
			String filename = media.getFilename();
			java.util.Date d = media.getCreateTime();
			// byte[] content = media.getContent();
			String mediaType = media.getMediaType();

			if (mediaId == null || filename == null || d == null || mediaType == null) {
				return MediaState.BAD_PARAMETER;
			}
		} else if (command.equals(COMMAND.get)) {
			String mediaId = (String) media.getMediaId();
			String mediaType = media.getMediaType();

			if (mediaId == null || mediaType == null) {
				return MediaState.BAD_PARAMETER;
			}

		} else if (command.equals(COMMAND.crop)) {
			String mediaId = (String) media.getMediaId();
			String filename = media.getFilename();
			// byte[] content = media.getContent();
			String mediaType = media.getMediaType();

			if (mediaId == null || filename == null || mediaType == null) {
				return MediaState.BAD_PARAMETER;
			}

		}

		return MediaState.OK;
	}

	/**
	 * identify media command-parameter
	 * 
	 * @param command
	 * @return
	 */
	public static MediaState identifier(String command) {

		COMMAND cmd = (COMMAND) getEnum(COMMAND.none, command);
		return cmd == null ? MediaState.BAD_PARAMETER : MediaState.OK;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Enum getEnum(Enum enumClass, String name) {
		Enum subenum = null;
		try {
			subenum = Enum.valueOf(enumClass.getClass(), name);
		} catch (Exception e) {

		}
		return subenum;
	}

}
