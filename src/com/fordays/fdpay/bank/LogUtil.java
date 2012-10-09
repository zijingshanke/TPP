package com.fordays.fdpay.bank;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 * 不依赖于Log4j的轻量日志工具
 */
public class LogUtil {
	@SuppressWarnings("unchecked")
	public Class classIn = null;
	public PrintWriter logWriter = null;// 日志

	// 日志文件项
	// private String logFilePath =
	// "/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/logs";// 日志目录
	private String logFilePath = File.separator + "opt" + File.separator
			+ "IBM" + File.separator + "WebSphere" + File.separator
			+ "AppServer" + File.separator + "profiles" + File.separator
			+ "AppSrv01" + File.separator + "logs";// 日志目录

	private String logFile = "";// 目标日志文件路径

	// 日志属性参数
	private boolean isAppend = true;// 是否在原来的文件上追加
	private boolean iSystemOut = false;// 是否在控制台显示
	private boolean iPrintClass = false;// 是否打印使用LogUtil的类

	// 日志内容项
	private String dateStr = "";//
	private String className = "None"; // 使用LogUtil的类的名字

	// 日志等级常数
	private static final int logLevel_Error = 1;// 日志等级,错误信息
	private static final int logLevel_Info = 2;// 日志等级,一般信息
	private static final int logLevel_Detail = 3;// 日志等级,详细信息

	/**
	 * @param boolean
	 *            isSystemOut 是否在console打印日志
	 * @param boolean
	 *            isPrintClass 是否打印当前路径
	 * @param Class
	 *            userClass 当前类
	 */
	@SuppressWarnings("unchecked")
	public LogUtil(boolean isSystemOut, boolean isPrintClass, Class classIn) {
		init(isSystemOut, isPrintClass, classIn);
	}

	/**
	 * 日志初始化
	 * 
	 * @param boolean
	 *            isSystemOut
	 * @param boolean
	 *            isPrintClass
	 * @param boolean
	 *            useClass
	 */
	@SuppressWarnings("unchecked")
	public void init(boolean isSystemOut, boolean isPrintClass, Class useClass) {
		classIn = useClass;
		logFilePath = logFilePath + File.separator + "banklog-BackGround";

		File myLogFile = new File(logFilePath);
		if (!myLogFile.exists()) {
			myLogFile.mkdirs();
		}
		logFile = logFilePath + File.separator + "bank.Log."
				+ getDateStr("yyyyMMdd") + ".log";
		iSystemOut = isSystemOut;
		iPrintClass = isPrintClass;

		try {
			if (isAppend) // 是否在原来的文件上追加
				logWriter = new PrintWriter(new BufferedWriter(new FileWriter(
						logFile, true)), true);
			else
				logWriter = new PrintWriter(new BufferedWriter(new FileWriter(
						logFile, false)), true);
		} catch (Exception e) {
			System.out.println("logWriter initlizes failure!" + e.getMessage());
		}
	}

	/**
	 * 写日志
	 * 
	 * @param logLevel
	 *            等级 1：错误信息 2：一般信息
	 * @param logInfo
	 *            log信息
	 */
	private void log(int logLevel, String logInfo) {
		logInfo = getLogInfo(logLevel, logInfo, classIn);//		
		try {
			if (iSystemOut) {
				System.out.println(logInfo);
			}
			logWriter.print(logInfo);
			logWriter.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String getLogInfo(int logLevel, String logInfo, Class classIn) {
		if (logInfo == null || "".equals(logInfo)) {
			logInfo = "getLogInfo(int,String,Class)无";
		}

		if (checkLogLevel(logLevel)) {// 检查日志等级设置
			String printLocus = "";

			if (iPrintClass) {
				className = classIn.getName();
				printLocus = "类:" + className + " ";
			}

			dateStr = getDateStr("yyyy-MM-dd HH:mm:ss");

			if (logLevel == logLevel_Error) {
				logInfo = printLocus + "[错误:" + dateStr + "] " + logInfo;
			} else if (logLevel == logLevel_Info) {
				logInfo = printLocus + "[" + dateStr + "] " + logInfo;
			} else if (logLevel == logLevel_Detail) {
				logInfo = printLocus + "[详细信息:" + dateStr + "] " + logInfo;
			} else {
				logInfo = LogUtil.class.getName() + "打印日志未正确设置日志信息等级 "
						+ dateStr + "] " + logInfo;
			}
		} else {
			logInfo = LogUtil.class.getName() + "打印日志未正确设置日志信息等级 " + dateStr
					+ "] " + logInfo;
		}
		return logInfo;
	}

	/**
	 * 检查日志等级
	 */
	public boolean checkLogLevel(int logLevel) {
		return true;
	}

	public String getDateStr(String dateType) {
		SimpleDateFormat dateFormat = null; // 日期格式
		java.sql.Timestamp time = new java.sql.Timestamp(System
				.currentTimeMillis());// Timestamp

		dateFormat = new SimpleDateFormat(dateType);
		dateStr = dateFormat.format(time);
		return dateStr;
	}

	/**
	 * 写错误信息
	 * 
	 * @param logInfo
	 *            String
	 */
	public void error(String logInfo) {
		log(logLevel_Error, logInfo);
	}

	/**
	 * 写一般信息
	 * 
	 * @param logInfo
	 *            String
	 */
	public void info(String logInfo) {
		if (logInfo == null || "".equals(logInfo)) {
			logInfo = "无";
		}

		log(logLevel_Info, logInfo);
	}

	/**
	 * 写调试信息
	 * 
	 * @param logInfo
	 *            String
	 */
	public void detail(String logInfo) {
		log(logLevel_Detail, logInfo);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public PrintWriter getLogWriter() {
		return logWriter;
	}

	public void setLogWriter(PrintWriter logWriter) {
		this.logWriter = logWriter;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public boolean isAppend() {
		return isAppend;
	}

	public void setAppend(boolean isAppend) {
		this.isAppend = isAppend;
	}

	public static int getLogLevel_Error() {
		return logLevel_Error;
	}

	public static int getLogLevel_Info() {
		return logLevel_Info;
	}

	public static int getLogLevel_Detail() {
		return logLevel_Detail;
	}

	public String getLogFilePath() {
		return logFilePath;
	}

	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}

	public boolean isISystemOut() {
		return iSystemOut;
	}

	public void setISystemOut(boolean systemOut) {
		iSystemOut = systemOut;
	}

	public boolean isIPrintClass() {
		return iPrintClass;
	}

	public void setIPrintClass(boolean printClass) {
		iPrintClass = printClass;
	}

	public String getDateStr() {
		return dateStr;
	}

	@SuppressWarnings("unchecked")
	public Class getClassIn() {
		return classIn;
	}

	@SuppressWarnings("unchecked")
	public void setClassIn(Class classIn) {
		this.classIn = classIn;
	}
}
