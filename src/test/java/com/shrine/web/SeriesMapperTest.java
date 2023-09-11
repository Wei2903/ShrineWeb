package com.shrine.web;

import com.shrine.web.mapper.SeriesMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SeriesMapperTest {
    @Autowired
    SeriesMapper seriesMapper;

    @Test
    public void testQueryAllSeries(){
        System.out.println(seriesMapper);
    }
}
