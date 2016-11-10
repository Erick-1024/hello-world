package com.cana.asset.server.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

public class TestZip {

	@Test
	public void test() throws IOException {
		/**
		 * 压缩文件
		 */
		File[] files = new File[]{new File("/tmp/未命名文件夹"), new File("/tmp/无标题文档")};
		File zip = new File("/tmp/压缩.zip");
		ZipFiles(zip, "", files);
	}

	/**
	 * 压缩文件-由于out要在递归调用外,所以封装一个方法用来
	 * 调用ZipFiles(ZipOutputStream out,String path,File... srcFiles)
	 * @param zip
	 * @param path
	 * @param srcFiles
	 * @throws IOException
	 * @author isea533
	 */
	public void ZipFiles(File zip,String path,File... srcFiles) throws IOException{
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
		ZipFiles(out, path, srcFiles);
		out.closeEntry();
		out.close();
	}
	/**
	 * 压缩文件-File
	 * @param zipFile  zip文件
	 * @param srcFiles 被压缩源文件
	 * @author isea533
	 */
	public void ZipFiles(ZipOutputStream out, String path, File... srcFiles){
		if(!path.endsWith("/"))
			path += "/";
		try {
			for(int i = 0; i < srcFiles.length; i++){
				if(srcFiles[i].isDirectory()){
					File[] files = srcFiles[i].listFiles();
					String srcPath = srcFiles[i].getName() + "/";
//					out.putNextEntry(new ZipEntry(path + srcPath));
					ZipFiles(out, path + srcPath, files);
				} else{
					FileInputStream in = new FileInputStream(srcFiles[i]);
					out.putNextEntry(new ZipEntry(path + srcFiles[i].getName()));
					int len;
					byte[] buf = new byte[1024];
					while((len=in.read(buf))>0)
						out.write(buf, 0, len);
					in.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
