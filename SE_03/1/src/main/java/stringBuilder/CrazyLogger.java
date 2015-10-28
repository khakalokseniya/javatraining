package stringBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.StringBuilder;

public class CrazyLogger {
	DateFormat df = new SimpleDateFormat("dd-mm-YYYY : hh-mm");
	Date today = Calendar.getInstance().getTime();
	StringBuilder log = new StringBuilder();

	public CrazyLogger() {
	}

	public void addLog(String message) {
		log.append(df.format(today));
		log.append('\t').append(message).append('\n');
	}

	public StringBuilder getLog() {
		return log;
	}

	public String findLog(String str) {
		int i = log.indexOf(str);
		int start;
		int finish;
		while (true) {
			i--;
			if (log.charAt(i) == '\t') {
				break;
			}
			start = i;
		}

		while (true) {
			i++;
			if (log.charAt(i) == '\n') {
				finish = i;
			}
			break;
		}
		
			return log.substring(start, finish);
		

	}

	public int index(String str) {
		return log.indexOf(str);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CrazyLogger other = (CrazyLogger) obj;
		if (df == null) {
			if (other.df != null)
				return false;
		} else if (!df.equals(other.df))
			return false;
		if (log == null) {
			if (other.log != null)
				return false;
		} else if (!log.equals(other.log))
			return false;
		if (today == null) {
			if (other.today != null)
				return false;
		} else if (!today.equals(other.today))
			return false;
		return true;
	}

}
