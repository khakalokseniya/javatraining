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
		log.append(message).append('\n');
	}

	public StringBuilder getLog() {
		return log;
	}

	public String fingLog(int index1, int index2) {
		return log.substring(index1, index2);

	}

}
