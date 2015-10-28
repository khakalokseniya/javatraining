package stringBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.StringBuilder;

public class CrazyLogger {
	DateFormat df = new SimpleDateFormat("dd-MM-YYYY : hh-mm");
	Date today = Calendar.getInstance().getTime();
	StringBuilder log = new StringBuilder();

	public CrazyLogger() {
	}

	public void addLog(String message) {
		log.append('\n').append(df.format(today));
		log.append('\t').append(message).append('\n');
	}

	public StringBuilder getLog() {
		return log;
	}

	public String findLog(String str) {
		int i = log.indexOf(str);
		int i2 = log.indexOf(str);
		int start = 0;
		int finish = 0;
		while (i >= 0) {
			i--;
			if (log.charAt(i) == '\n') {
				start = i;
				break;
			}
		}
		while (i2 >= 0) {
			i2++;
			if (log.charAt(i2) == '\n') {
				finish = i2;
				break;
			}
		}
		return log.substring(start, finish);
	}

	public int index(String str) {
		return log.indexOf(str);
	}
}
