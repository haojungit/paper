package net.dqsy.papermg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeCounter {
	static long commentLine = 0L;
	static long whiteLine = 0L;
	static long normalLine = 0L;
	static long totalLine = 0L;
	static boolean comment = false;

	public static void main(String[] args) {
		File file = new File("D:\\prolunwen\\javaeepaper\\PaperManager");
		getChild(file);
		System.out.println("有效代码行数: " + normalLine);
		System.out.println("注释行数: " + commentLine);
		System.out.println("空白行数: " + whiteLine);
		System.out.println("总代码行数: " + totalLine);
	}

	private static void getChild(File child) {
		String line;
		if (child.getName().matches(".*\\.js$")) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(child));
				line = "";
				while ((line = br.readLine()) != null)
					parse(line);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (child.listFiles() != null) {
			File[] arrayOfFile;
			int str1 = (arrayOfFile = child.listFiles()).length;
			for (int line1 = 0; line1 < str1; line1++) {
				File f = arrayOfFile[line1];
				getChild(f);
			}
		}
	}

	private static void parse(String line) {
		line = line.trim();
		totalLine += 1L;
		if (line.length() == 0) {
			whiteLine += 1L;
		} else if (comment) {
			commentLine += 1L;
			if (line.endsWith("*/")) {
				comment = false;
			} else if (line.matches(".*\\*/.+")) {
				normalLine += 1L;
				comment = false;
			}
		} else if (line.startsWith("//")) {
			commentLine += 1L;
		} else if (line.matches(".+//.*")) {
			commentLine += 1L;
			normalLine += 1L;
		} else if ((line.startsWith("/*")) && (line.matches(".+\\*/.+"))) {
			commentLine += 1L;
			normalLine += 1L;
			if (findPair(line))
				comment = false;
			else
				comment = true;
		} else if ((line.startsWith("/*")) && (!line.endsWith("*/"))) {
			commentLine += 1L;
			comment = true;
		} else if ((line.startsWith("/*")) && (line.endsWith("*/"))) {
			commentLine += 1L;
			comment = false;
		} else if ((line.matches(".+/\\*.*")) && (!line.endsWith("*/"))) {
			commentLine += 1L;
			normalLine += 1L;
			if (findPair(line))
				comment = false;
			else
				comment = true;
		} else if ((line.matches(".+/\\*.*")) && (line.endsWith("*/"))) {
			commentLine += 1L;
			normalLine += 1L;
			comment = false;
		} else {
			normalLine += 1L;
		}
	}

	private static boolean findPair(String line) {
		int count1 = 0;
		int count2 = 0;
		Pattern p = Pattern.compile("/\\*");
		Matcher m = p.matcher(line);
		while (m.find()) {
			count1++;
		}
		p = Pattern.compile("\\*/");
		m = p.matcher(line);
		while (m.find()) {
			count2++;
		}
		return count1 == count2;
	}
}