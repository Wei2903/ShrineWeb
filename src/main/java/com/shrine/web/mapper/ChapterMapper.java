package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {
    // Check main/resources/mapper/ChapterMapper.xml for actual implementation
    List<Chapter> queryChaptersBySeriesId(Integer id);

    Chapter getDetailedChapter(Integer chapterId);

    Long getChaptersCountNumberBySeriesId(Integer id);

    void insertChapter(Chapter chapter);
}
