package com.example.board.Util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUtil {

    public static String makePath() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String path = sdf.format(new Date());

        return path.replace("-", File.separator);
    }

    public static String getNewFileName(String fileName) {
        UUID uuid = UUID.randomUUID();

        return String.format("%s_%s", uuid.toString(), fileName);
    }
}
