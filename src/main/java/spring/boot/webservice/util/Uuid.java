package spring.boot.webservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Uuid {

    public String getUuid() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss", Locale.KOREA);

        Date date = new Date();

        String uuid = sdf.format(date);

        return uuid;
    }

}
