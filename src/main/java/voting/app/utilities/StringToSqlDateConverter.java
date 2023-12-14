package voting.app.utilities;

import org.springframework.format.Formatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class StringToSqlDateConverter implements Formatter<java.sql.Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public java.sql.Date parse(String text, Locale locale) throws ParseException {
        try {
            java.util.Date parsedDate = dateFormat.parse(text);
            return new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            // Handle parsing exception according to your needs
            e.printStackTrace();
            throw e; // Or throw a custom exception
        }
    }

    @Override
    public String print(java.sql.Date date, Locale locale) {
        return (date != null) ? dateFormat.format(date) : "";
    }
}
