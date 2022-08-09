package com.example.demo.repository;


import com.example.demo.entity.Metadata;
import com.example.demo.entity.MetadataChartdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MetadataRepository extends JpaRepository<Metadata, Long> {

    @Query(value = "select count(metadata_id) from metadata",nativeQuery = true)
    public int countMetadata();

    @Query(value = "select * from metadata",nativeQuery = true)
    List<Metadata> getMetadata();

    @Query(value = "SELECT sum(IF(MONTH = 'jan', total, 0)) AS 'jan', sum(IF(MONTH = 'feb', total, 0)) AS 'feb', sum(IF(MONTH = 'mar', total, 0)) AS 'mar',"
            +" SUM(IF(MONTH = 'apr', total, 0)) AS 'apr', SUM(IF(MONTH = 'may', total, 0)) AS 'may', SUM(IF(MONTH = 'jun', total, 0)) AS 'jun', SUM(IF(MONTH = 'jul', total, 0)) AS 'jul',"
            +" SUM(IF(MONTH = 'aug', total, 0)) AS 'aug', SUM(IF(MONTH = 'sep', total, 0)) AS 'sep', SUM(IF(MONTH = 'oct', total, 0)) AS 'oct', SUM(IF(MONTH = 'nov', total, 0)) AS 'nov', SUM(IF(MONTH = 'dec', total, 0)) AS 'dec' "
            +"FROM ( SELECT MIN(DATE_FORMAT(register_date, '%b')) AS MONTH, COUNT(metadata_id) AS total FROM metadata GROUP BY MONTH(register_date) ORDER BY MONTH(register_date) ) AS users",nativeQuery = true)
    MetadataChartdata getMetachartdata();
}
