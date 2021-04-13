package com.Ppppppe;

import java.time.ZoneId;

public class UtcOffset {

    public static ZoneId of(String in) {
        switch (in) {
            case "Владивосток":
                return ZoneId.of(("UTC+10"));
            case "Тель-Авив":
                return ZoneId.of(("UTC+3"));
            default:
                return ZoneId.of(("UTC"));
                //throw new IllegalArgumentException("should be Владивосток or Тель-Авив: " + in);
        }
    }
}
