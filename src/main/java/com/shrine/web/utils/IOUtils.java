package com.shrine.web.utils;

import com.shrine.web.entity.Chapter;
import com.shrine.web.entity.Series;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOUtils {
    public static String staticDirectoryPath = "D:\\static";

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

    public static void SaveComicPages(Series series, Chapter chapter, MultipartFile pageImage) throws IOException {
        String seriesTitle = StringUtils.removePunctuationAndSpace(series.getTitle());
        String chapterTitle = StringUtils.removePunctuationAndSpace(chapter.getTitle());
        String directoryPath = staticDirectoryPath
                + File.separator +"comics"
                + File.separator + seriesTitle + File.separator + chapterTitle;
        Path path = Paths.get(directoryPath + File.separator + pageImage.getOriginalFilename());
        Files.write(path,pageImage.getBytes());
    }

    public static boolean renameSeriesDirectory(String oldTitle, String newTitle) throws IOException{
        oldTitle = StringUtils.removePunctuationAndSpace(oldTitle);
        newTitle = StringUtils.removePunctuationAndSpace(newTitle);
        String oldDirectoryPath = staticDirectoryPath + File.separator + "comics" + File.separator + oldTitle;
        String newDirectoryPath = staticDirectoryPath + File.separator + "comics" + File.separator + newTitle;
        File oldDirectory = new File(oldDirectoryPath);
        File newDirectory = new File(newDirectoryPath);
        if (oldDirectory.exists() && oldDirectory.isDirectory()) {
            return oldDirectory.renameTo(newDirectory);
        }
        return false;
    }

    public static boolean renameChapterDirectory(Series series,String oldTitle, String newTitle) throws IOException{
        String seriesTitle = StringUtils.removePunctuationAndSpace(series.getTitle());
        oldTitle = StringUtils.removePunctuationAndSpace(oldTitle);
        newTitle = StringUtils.removePunctuationAndSpace(newTitle);
        String oldDirectoryPath = staticDirectoryPath + File.separator + "comics" + File.separator + seriesTitle + File.separator + oldTitle;
        String newDirectoryPath = staticDirectoryPath + File.separator + "comics" + File.separator + seriesTitle + File.separator + newTitle;
        File oldDirectory = new File(oldDirectoryPath);
        File newDirectory = new File(newDirectoryPath);
        if (oldDirectory.exists() && oldDirectory.isDirectory()) {
            return oldDirectory.renameTo(newDirectory);
        }
        return false;
    }

    public static boolean modifyImage(String title, String imageName, MultipartFile newImage) throws IOException {
        title = StringUtils.removePunctuationAndSpace(title);
        String newName = newImage.getOriginalFilename();
        String directoryPath = staticDirectoryPath + File.separator + "comics" + File.separator + title;
        Path imagePath = Paths.get(directoryPath + File.separator + imageName);
        System.out.println("--------------------------------");
        System.out.println("title:" + title);
        System.out.println("newName:" + newName);
        System.out.println("directoryPath:" + directoryPath);
        System.out.println("imagePath:" + imagePath.toString());
        if (Files.exists(imagePath) && Files.isRegularFile(imagePath)) {
            Files.write(imagePath, newImage.getBytes());
            File oldImage = new File(directoryPath, imageName);
            File renameImage = new File(directoryPath,newName);

            if (oldImage.exists() && oldImage.isFile()) {
                oldImage.renameTo(renameImage);
            }
            return true;
        }
        return false;
    }
    public static boolean modifyChapterImage(String seriesTitle, String title, String imageName, MultipartFile newImage) throws IOException {
        seriesTitle = StringUtils.removePunctuationAndSpace(seriesTitle);
        title = StringUtils.removePunctuationAndSpace(title);
        String newName = newImage.getOriginalFilename();
        String directoryPath = staticDirectoryPath + File.separator + "comics" + File.separator + seriesTitle + File.separator +title;
        Path imagePath = Paths.get(directoryPath + File.separator + imageName);
        System.out.println("--------------------------------");
        System.out.println("title:" + title);
        System.out.println("newName:" + newName);
        System.out.println("directoryPath:" + directoryPath);
        System.out.println("imagePath:" + imagePath.toString());
        if (Files.exists(imagePath) && Files.isRegularFile(imagePath)) {
            Files.write(imagePath, newImage.getBytes());
            File oldImage = new File(directoryPath, imageName);
            File renameImage = new File(directoryPath,newName);

            if (oldImage.exists() && oldImage.isFile()) {
                oldImage.renameTo(renameImage);
            }
            return true;
        }
        return false;
    }
    public static boolean deleteComicPages(String seriesTitle,String chapterTitle, String imageName){
        seriesTitle = StringUtils.removePunctuationAndSpace(seriesTitle);
        chapterTitle = StringUtils.removePunctuationAndSpace(chapterTitle);
        imageName = StringUtils.removePunctuationAndSpace(imageName);
        String directoryPath = staticDirectoryPath
                + File.separator +"comics"
                + File.separator + seriesTitle + File.separator + chapterTitle + File.separator + imageName;
        Path path = Paths.get(directoryPath);
        try {
            Files.delete(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean deleteChapter(String seriesTitle, String chapterTitle) {
        seriesTitle = StringUtils.removePunctuationAndSpace(seriesTitle);
        chapterTitle = StringUtils.removePunctuationAndSpace(chapterTitle);
        String directoryPath = staticDirectoryPath
                + File.separator +"comics"
                + File.separator + seriesTitle + File.separator + chapterTitle;
        Path path = Paths.get(directoryPath);
        try {
            Files.delete(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Boolean deleteSeries(String seriesTitle) {
        seriesTitle = StringUtils.removePunctuationAndSpace(seriesTitle);
        String directoryPath = staticDirectoryPath
                + File.separator +"comics"
                + File.separator + seriesTitle;
        Path path = Paths.get(directoryPath);
        try {
            Files.delete(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
