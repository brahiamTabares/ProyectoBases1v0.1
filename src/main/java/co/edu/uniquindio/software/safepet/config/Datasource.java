package co.edu.uniquindio.software.safepet.config;


import javax.annotation.sql.DataSourceDefinition;


@DataSourceDefinition(
		name = Datasource.DATASOURCE, //
		className = "com.mysql.cj.jdbc.MysqlDataSource", //
		initialPoolSize = 2,
		minPoolSize = 2,
		maxPoolSize = 10,
		serverName = "localhost", //
		portNumber = 3306, //
		user = "root", //
		password = "root", //
		databaseName = "safepetb1", //
		properties = { //
				"useSSL=false", "useInformationSchema=true", "nullCatalogMeansCurrent=true", "nullNamePatternMatchesAll=false" //
		}
)
public class Datasource {
	public static final String DATASOURCE = "java:app/safepetb1/mysql";
}
