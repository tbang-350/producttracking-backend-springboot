package com.example.demo.repository;

import com.example.demo.entity.ContractorChartdata;
import com.example.demo.entity.EmployeeChartdata;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT s FROM User s WHERE s.userName =?1")
    Optional<User> findUserByUsername(String userName);

    @Query(value = "select * from users,user_roles where users.user_id=user_roles.user_id and user_roles.role_id = 2",nativeQuery = true)
    public List<User> findAllContractors();

    @Query(value = "select * from users,user_roles where users.user_id=user_roles.user_id and user_roles.role_id = 3",nativeQuery = true)
    public List<User> findAllEmployees();

    @Query(value = "select count(user_name) from users,user_roles where users.user_id=user_roles.user_id and user_roles.role_id = 2",nativeQuery = true)
    public int countAllContractors();

    @Query(value = "select count(user_name) from users,user_roles where users.user_id=user_roles.user_id and user_roles.role_id = 3",nativeQuery = true)
    public int countAllEmployees();

    @Query(value = "select count(user_name) from users",nativeQuery = true)
    public int countAllUsers();

    @Query(value = "SELECT sum(IF(MONTH = 'jan', total, 0)) AS 'jan', sum(IF(MONTH = 'feb', total, 0)) AS 'feb', sum(IF(MONTH = 'mar', total, 0)) AS 'mar', SUM(IF(MONTH = 'apr', total, 0)) AS 'apr', SUM(IF(MONTH = 'may', total, 0)) AS 'may',"
            +" SUM(IF(MONTH = 'jun', total, 0)) AS 'jun', SUM(IF(MONTH = 'jul', total, 0)) AS 'jul', SUM(IF(MONTH = 'aug', total, 0)) AS 'aug', "
            +"SUM(IF(MONTH = 'sep', total, 0)) AS 'sep', SUM(IF(MONTH = 'oct', total, 0)) AS 'oct', SUM(IF(MONTH = 'nov', total, 0)) AS 'nov', SUM(IF(MONTH = 'dec', total, 0)) AS 'dec' "
            +"FROM ( SELECT MIN(DATE_FORMAT(registered_at, '%b')) AS MONTH, COUNT(user_name) AS total FROM users,user_roles WHERE users.user_id=user_roles.user_id and user_roles.role_id = 3 GROUP BY MONTH(registered_at) ORDER BY MONTH(registered_at) ) AS users",nativeQuery = true)
    EmployeeChartdata getChartdata();

    @Query(value = "SELECT sum(IF(MONTH = 'jan', total, 0)) AS 'jan', sum(IF(MONTH = 'feb', total, 0)) AS 'feb', sum(IF(MONTH = 'mar', total, 0)) AS 'mar',"
            +" SUM(IF(MONTH = 'apr', total, 0)) AS 'apr', SUM(IF(MONTH = 'may', total, 0)) AS 'may', SUM(IF(MONTH = 'jun', total, 0)) AS 'jun', "
            +"SUM(IF(MONTH = 'jul', total, 0)) AS 'jul', SUM(IF(MONTH = 'aug', total, 0)) AS 'aug', SUM(IF(MONTH = 'sep', total, 0)) AS 'sep', SUM(IF(MONTH = 'oct', total, 0)) AS 'oct', "
            +"SUM(IF(MONTH = 'nov', total, 0)) AS 'nov', SUM(IF(MONTH = 'dec', total, 0)) AS 'dec' FROM ( SELECT MIN(DATE_FORMAT(registered_at, '%b')) AS MONTH, COUNT(user_name) AS total "
            +"FROM users,user_roles WHERE users.user_id=user_roles.user_id and user_roles.role_id = 2 GROUP BY MONTH(registered_at) ORDER BY MONTH(registered_at) ) AS users",nativeQuery = true)
    ContractorChartdata getContractorChartdata();



}
