package co.edu.uniquindio.software.safepet.config;


import javax.annotation.sql.DataSourceDefinition;


@DataSourceDefinition(
		name = "java:app/ejemplo_sql/mysql", //
		className = "com.mysql.cj.jdbc.MysqlDataSource", //
		initialPoolSize = 2,
		minPoolSize = 2,
		maxPoolSize = 10,
		serverName = "localhost", //
		portNumber = 3306, //
		user = "root", //
		password = "12345678", //
		databaseName = "ejemplo_sql", //
		properties = { //
		"useSSL=false", "useInformationSchema=true", "nullCatalogMeansCurrent=true", "nullNamePatternMatchesAll=false" //
		}
		)
public class Datasource {

}
