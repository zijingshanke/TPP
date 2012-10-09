package com.fordays.fdpay.bank;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

public class FileUtil {
	public FileUtil() {
	}

	public boolean makeDir(String dirs) throws Exception {
		boolean result = false;
		try {
			File fi = new File(dirs);
			result = fi.mkdirs();
		} catch (Exception e) {
			result = false;
			System.err.println(e.getMessage());
		}
		return result;
	}

	public boolean makeRemoteDir(HttpServletRequest request, String dirs)
			throws Exception {
		boolean result = false;
		if (dirs != null) {
			String pathString = "";
			pathString = request.getRealPath("");
			pathString = pathString.replace('\\', '/');
			pathString = (new StringBuilder(String.valueOf(pathString)))
					.append(dirs).toString();
			try {
				File fi = new File(pathString);
				result = fi.mkdirs();
			} catch (Exception e) {
				result = false;
				System.err.println(e.getMessage());
			}
		}
		return result;
	}

	public boolean deleteDirectory(String fullDirName) {
		boolean result = false;
		int len = 0;
		int i = 0;
		try {
			File Dire = new File(fullDirName);
			if (!Dire.exists())
				result = false;
			if (Dire.isFile())
				result = Dire.delete();
			File fi[] = Dire.listFiles();
			len = fi.length;
			if (len == 0)
				result = Dire.delete();
			for (i = 0; i < len; i++)
				if (fi[i].isDirectory())
					result = deleteDirectory(fi[i].getPath());
				else
					result = fi[i].delete();

			if (Dire.exists())
				result = Dire.delete();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	public boolean deleteRemoteDir(HttpServletRequest request, String filePath)
			throws Exception {
		boolean result = false;
		if (filePath != null) {
			String pathString = "";
			pathString = request.getRealPath("");
			pathString = pathString.replace('\\', '/');
			pathString = (new StringBuilder(String.valueOf(pathString)))
					.append(filePath).toString();
			int len = 0;
			int i = 0;
			try {
				File Dire = new File(pathString);
				if (!Dire.exists())
					result = false;
				if (Dire.isFile())
					result = Dire.delete();
				File fi[] = Dire.listFiles();
				len = fi.length;
				if (len == 0)
					result = Dire.delete();
				for (i = 0; i < len; i++)
					if (fi[i].isDirectory())
						result = deleteDirectory(fi[i].getPath());
					else
						result = fi[i].delete();

				if (Dire.exists())
					result = Dire.delete();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
		return result;
	}

	
	public boolean createFile(String fullFileName, String txt) {
		boolean restult = false;
		try {
			if (txt == null)
				txt = "";
			int last = fullFileName.lastIndexOf("\\");
			String dirName = fullFileName.substring(0, last);
			File Dire = new File(dirName);
			if (!Dire.exists())
				makeDir(dirName);
			PrintWriter pw = new PrintWriter(new FileOutputStream(fullFileName));
			pw.println(txt);
			restult = true;
			pw.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return restult;
	}

	public boolean createRemoteFile(HttpServletRequest request,
			String fullFileName, String txt) {
		boolean restult = false;
		String pathString = "";
		pathString = request.getRealPath("");
		pathString = pathString.replace('\\', '/');
		fullFileName = (new StringBuilder(String.valueOf(pathString))).append(
				fullFileName).toString();
		try {
			if (txt == null)
				txt = "";
			int last = fullFileName.lastIndexOf("/");
			String dirName = fullFileName.substring(0, last);
			dirName = dirName.replace('/', '\\');
			File Dire = new File(dirName);
			if (!Dire.exists())
				makeDir(dirName);
			PrintWriter pw = new PrintWriter(new FileOutputStream(fullFileName));
			pw.println(txt);
			restult = true;
			pw.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return restult;
	}

	public boolean deleteFile(String fullFileName) {
		boolean result = false;
		if (fullFileName == null || "".equals(fullFileName))
			result = false;
		fullFileName = StringUtils.replace(fullFileName, "//", "\\");
		File fl = new File(fullFileName);
		result = fl.delete();
		return result;
	}

	public boolean deleteRemoteFile(HttpServletRequest request, String filePath)
			throws Exception {
		boolean result = false;
		if (filePath != null) {
			String pathString = "";
			pathString = request.getRealPath("");
			pathString = pathString.replace('\\', '/');
			pathString = (new StringBuilder(String.valueOf(pathString)))
					.append(filePath).toString();
			try {
				File f = new File(pathString);
				if (f.exists())
					result = f.delete();
			} catch (Exception exception) {
			}
		}
		return result;
	}

	public String getFileExtName(String fullFileName) {
		int i = 0;
		int Len = 0;
		String charStr = "";
		String rtn = "";
		if (fullFileName == null)
			return "";
		fullFileName = fullFileName.trim();
		Len = fullFileName.length();
		if (Len <= 1)
			return "";
		for (i = Len - 1; i > 0; i--) {
			charStr = fullFileName.substring(i, i + 1);
			rtn = (new StringBuilder(String.valueOf(charStr))).append(rtn)
					.toString();
			if (charStr.compareTo(".") == 0)
				break;
		}

		if (rtn.length() > 5)
			return "";
		else
			return rtn;
	}

	public String getFileNoExtName(String fullFileName) {
		String rtn = "";
		String ext = "";
		if (fullFileName.length() <= 5) {
			return "";
		} else {
			fullFileName = fullFileName.replace('\\', '/');
			ext = getFileExtName(fullFileName);
			int Start = fullFileName.lastIndexOf("/");
			rtn = fullFileName.substring(Start + 1, fullFileName.length()
					- ext.length());
			return rtn;
		}
	}

	public static final String getFile(String path) {
		String result = "";
		if (path.length() < 5)
			return "";
		try {
			path = path.trim();
			String str = "";
			int i = path.length();
			for (i = i; i > 0; i--) {
				str = path.substring(i - 1, i);
				if (str.equals("/") || str.equals("\\"))
					break;
				result = (new StringBuilder(String.valueOf(str)))
						.append(result).toString();
			}

		} catch (Exception exception) {
		}
		return result;
	}

	public boolean isExist(String fullFileName) {
		if (fullFileName == null || "".equals(fullFileName))
			return false;
		fullFileName = StringUtils.replace(fullFileName, "//", "\\");
		File fl = new File(fullFileName);
		return fl.exists();
	}

	public boolean isRemoteExist(HttpServletRequest request, String fullFileName) {
		if (fullFileName == null || "".equals(fullFileName))
			return false;
		String pathString = "";
		pathString = request.getRealPath("");
		pathString = pathString.replace('\\', '/');
		pathString = (new StringBuilder(String.valueOf(pathString))).append(
				fullFileName).toString();
		File fl = new File(pathString);
		return fl.exists();
	}

	public boolean reName(String oldFileName, String newFileName) {
		boolean result = false;
		try {
			if ( "".equals(oldFileName) || "".equals(newFileName))
				result = false;
			else if (newFileName.indexOf("\\") > 0
					|| newFileName.indexOf("/") > 0
					|| newFileName.indexOf(":") > 0
					|| newFileName.indexOf("*") > 0
					|| newFileName.indexOf("?") > 0
					|| newFileName.indexOf("|") > 0
					|| newFileName.indexOf("<") > 0
					|| newFileName.indexOf(">") > 0) {
				result = false;
			} else {
				oldFileName = StringUtils.replace(oldFileName, "//", "\\");
				int last = oldFileName.lastIndexOf("\\");
				String filePath = oldFileName.substring(0, last);
				File fl = new File(oldFileName);
				File f2 = new File(
						(new StringBuilder(String.valueOf(filePath))).append(
								"\\").append(newFileName).toString());
				result = fl.renameTo(f2);
			}
		} catch (Exception e) {
			result = false;
			System.err.println(e.getMessage());
		}
		return result;
	}

	public boolean reNameRemoteFile(HttpServletRequest request,
			String oldFileName, String newFileName) {
		boolean result = false;
		try {
			if (oldFileName == null || "".equals(oldFileName) || newFileName == null
					|| "".equals(newFileName)) {
				result = false;
			} else {
				String pathString = "";
				if (newFileName.indexOf("\\") > -1
						|| newFileName.indexOf("/") > -1
						|| newFileName.indexOf(":") > -1
						|| newFileName.indexOf("*") > -1
						|| newFileName.indexOf("?") > -1
						|| newFileName.indexOf("|") > -1
						|| newFileName.indexOf("<") > 0
						|| newFileName.indexOf(">") > -1) {
					result = false;
				} else {
					pathString = request.getRealPath("");
					pathString = pathString.replace('\\', '/');
					pathString = (new StringBuilder(String.valueOf(pathString)))
							.append(oldFileName).toString();
					int last = pathString.lastIndexOf("/");
					String filePath = pathString.substring(0, last);
					File fl = new File(pathString);
					File f2 = new File((new StringBuilder(String
							.valueOf(filePath))).append("/")
							.append(newFileName).toString());
					result = fl.renameTo(f2);
				}
			}
		} catch (Exception e) {
			result = false;
			System.err.println(e.getMessage());
		}
		return result;
	}

	public boolean moveFile(String src, String des) throws Exception {
		boolean result = false;
		try {
			FileInputStream fi = new FileInputStream(src);
			BufferedInputStream ipt = new BufferedInputStream(fi);
			FileOutputStream fo = new FileOutputStream(des);
			BufferedOutputStream opt = new BufferedOutputStream(fo);
			for (boolean eof = false; !eof;) {
				int input = ipt.read();
				if (input == -1)
					break;
				opt.write(input);
			}

			ipt.close();
			opt.close();
			File Source = new File(src);
			if (Source.delete())
				result = true;
		} catch (Exception e) {
			result = false;
			System.err.println(e.getMessage());
		}
		return result;
	}
}
