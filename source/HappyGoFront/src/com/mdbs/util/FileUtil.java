package com.mdbs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	/**
	 * 檔案上傳-自動產生檔案名稱
	 * @param file
	 * @param uploadFilePath
	 * @return
	 * @throws Exception
	 */
	public static String getUploadFileName(MultipartFile file, String uploadFilePath) throws Exception {
		return getUploadFileName(file, uploadFilePath, null);
	}

	/**
	 * 檔案上傳-自定檔案名稱
	 * @param file
	 * @param uploadFilePath
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static String getUploadFileName(MultipartFile file, String uploadFilePath, String id) throws Exception {
		String originalFilename = file.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
		String fileName = Id.id32() + extName;
		if (id != null)
			fileName = id;

		File toFile = new File(WebUtil.removeControlCharacter(uploadFilePath),
				WebUtil.removeControlCharacter(fileName));
		if ((null != toFile.getParentFile()) && (!toFile.getParentFile().exists())) {
			toFile.getParentFile().mkdirs();
		}
		file.transferTo(toFile);
		return fileName;
	}

	/**
	 * 檔案上傳-原始檔案名稱
	 * @param file
	 * @param uploadFilePath
	 * @return
	 * @throws Exception
	 */
	public static String getUploadFileOriginalName(MultipartFile file, String uploadFilePath) throws Exception {
		String fileName = file.getOriginalFilename();
		File toFile = new File(WebUtil.removeControlCharacter(uploadFilePath),
				WebUtil.removeControlCharacter(fileName));
		if ((null != toFile.getParentFile()) && (!toFile.getParentFile().exists())) {
			toFile.getParentFile().mkdirs();
		}
		file.transferTo(toFile);
		return fileName;
	}

	/**
	 * @param fromFileName
	 * @param toFileName
	 * @throws Exception
	 */
	public static void copy(String fromFileName, String toFileName) throws Exception {
		copy(new File(fromFileName), new File(toFileName));
	}

	/**
	 * @param fromFile
	 * @param toFile
	 * @throws Exception
	 */
	public static void copy(File fromFile, File toFile) throws Exception {
		FileInputStream from = null;
		FileOutputStream to = null;
		try {
			from = new FileInputStream(fromFile);
			// create destination directory automatically if necessary
			if ((toFile.getParentFile() != null) && (!toFile.getParentFile().exists())) {
				toFile.getParentFile().mkdirs();
			}
			to = new FileOutputStream(toFile);
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = from.read(buffer)) != -1) {
				to.write(buffer, 0, bytesRead);
			} // write
		} finally {
			if (from != null) {
				try {
					from.close();
				} catch (IOException e) {
				}
			}
			if (to != null) {
				try {
					to.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * @param from
	 * @param toFile
	 * @throws Exception
	 */
	public static void copy(InputStream from, File toFile) throws Exception {
		FileOutputStream to = null;
		try {
			// create destination directory automatically if necessary
			if ((toFile.getParentFile() != null) && (!toFile.getParentFile().exists())) {
				toFile.getParentFile().mkdirs();
			}
			to = new FileOutputStream(toFile);
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = from.read(buffer)) != -1) {
				to.write(buffer, 0, bytesRead);
			} // write
		} finally {
			if (from != null) {
				try {
					from.close();
				} catch (IOException e) {
				}
			}
			if (to != null) {
				try {
					to.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * @param file
	 * @throws Exception
	 */
	public static void createPathIfMissing(File file) throws Exception {
		if ((null != file.getParentFile()) && (!file.getParentFile().exists())) {
			file.getParentFile().mkdirs();
		}
	}

	/**
	 * @param parentPath
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String parentPath, String filePath) {
		File file = new File(parentPath, filePath);
		if (file.exists())
			if (file.delete())
				return true;
		return false;
	}

	/**
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists())
			if (file.delete())
				return true;
		return false;
	}

}
