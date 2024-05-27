package urgency.xml.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SQLDateAdapter extends XmlAdapter<String, Date> {
		
		private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*java.util.Date sdfDate1 = dateTimeFormat.parse(rs.getString("boxDate"));
		Timestamp boxDate = new Timestamp(sdfDate1.getTime());*/

		//From Java objects to XML files: notice the plural/singular
		@Override
		public String marshal(Date sqlDate) throws Exception {
			return sqlDate.toLocalDate().format(formatter);
		}

		//From XML file to Java objects
		@Override
		public Date unmarshal(String string) throws Exception {
			LocalDate localDate = LocalDate.parse(string, formatter);
			return Date.valueOf(localDate);
		}

	}
