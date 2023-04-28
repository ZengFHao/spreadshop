package com.zfh.jdbc1111.TestSpringBoot111;

import com.zfh.jdbc1111.TestSpringBoot111.dao.Dao;
import com.zfh.jdbc1111.TestSpringBoot111.dao.JDBC_connectorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TestSpringBoot111Application {

	public static void main(String[] args) {
		Dao dao = new JDBC_connectorImpl();
		//System.out.println(dao.find_goods("select * from goods"));
		SpringApplication.run(TestSpringBoot111Application.class, args);
	}

}
