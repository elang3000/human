package com.wondersgroup.human.util;

import java.util.UUID;

import com.wondersgroup.common.utils.FtpTool;
import com.wondersgroup.config.ReadProperties;
import com.wondersgroup.framework.util.StringUtils;

import sun.misc.BASE64Decoder;

public class ImgPicUtil {
	
	public static String savePic(String photostr) {
		if (StringUtils.isNotBlank(photostr)) {
			String[] base64photostrarr = photostr.split(",");
			if (base64photostrarr.length > 1) {
				BASE64Decoder decoder = new BASE64Decoder();
				try {
					byte[] b = decoder.decodeBuffer(base64photostrarr[1]);
					String newName = UUID.randomUUID().toString() + ".jpg";
					FtpTool.ftpUpload(ReadProperties.getInstance().FTP_DIR_NAME_PHOTO, b, newName);
					return newName;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return null;
			}
		}else{
			return null;
		}
		return null;
	}
}
