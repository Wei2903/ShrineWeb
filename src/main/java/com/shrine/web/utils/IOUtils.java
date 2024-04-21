package com.shrine.web.utils;

import com.shrine.web.entity.Series;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOUtils {
    public static String staticDirectoryPath = "/Users/weiwei/Desktop/Group Project S1/static";

    public static boolean SaveCoverImage(String title, MultipartFile coverImage) throws IOException {
        title = StringUtils.removePunctuationAndSpace(title);
        String directoryPath = staticDirectoryPath
                + File.separator +"comics"
                + File.separator + title;
        Path path = Paths.get(directoryPath + File.separator + coverImage.getOriginalFilename());
        if (Files.exists(path.getParent()) && Files.isDirectory(path.getParent())){
            return false;
        }
        Files.createDirectories(path.getParent());
        Files.write(path, coverImage.getBytes());
        return true;
    }

    public static boolean SaveChapterLogo(Series series, String title, MultipartFile chapterImage) throws IOException {
        String seriesTitle = StringUtils.removePunctuationAndSpace(series.getTitle());
        String directoryPath = staticDirectoryPath
                + File.separator +"comics"
                + File.separator + seriesTitle;
        directoryPath = directoryPath + File.separator + StringUtils.removePunctuationAndSpace(title);
        Path path = Paths.get(directoryPath + File.separator + chapterImage.getOriginalFilename());
        if (Files.exists(path.getParent()) && Files.isDirectory(path.getParent())){
            return false;
        }
        Files.createDirectories(path.getParent());
        Files.write(path, chapterImage.getBytes());
        return true;
    }
}
