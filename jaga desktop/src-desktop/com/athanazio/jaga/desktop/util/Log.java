package com.athanazio.jaga.desktop.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * logging layer
 * 
 * represents the type
 * 
 * @author HAL
 * 
 */
public class Log {

	public static final String logFile = "jaga-desktop.log";
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

	public static boolean DEBUG = true;
	public static BufferedWriter writer;

	public static void init() throws IOException {}

	/**
	 * show debug message
	 * 
	 * @param message
	 *            debug information
	 */
	public static void debug(String message) {
		if (DEBUG) {
			try {
				writer = new BufferedWriter(new FileWriter(logFile, true));
				String timestamp = sdf.format(Calendar.getInstance().getTime());
				writer.write("[" + timestamp + "] DEBUG " + message);
				writer.newLine();
				writer.flush();
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void warning(String message) {
		try {
			writer = new BufferedWriter(new FileWriter(logFile, true));
			String timestamp = sdf.format(Calendar.getInstance().getTime());
			writer.write("[" + timestamp + "] WARN " + message);
			writer.newLine();
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void error(Exception e, String message) {
		try {
			FileOutputStream out = new FileOutputStream(logFile, true);
			String timestamp = sdf.format(Calendar.getInstance().getTime());
			message = "[" + timestamp + "] ERROR " + message;

			out.write(message.getBytes());
			out.write("\n".getBytes());
			e.printStackTrace(new PrintStream(out));
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
