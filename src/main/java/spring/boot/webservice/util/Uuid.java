package spring.boot.webservice.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Uuid {

    public String getUuid() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss", Locale.KOREA);

        String uuid = String.valueOf(sdf);

        return uuid;
    }

}
