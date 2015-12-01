/*
 * Not yet working.
 */

/*
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.PrintStream;
import java.sql.ResultSet;

public class Driver {
	public static void main(String[] args) {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("heiTEC");
		dataSource.setServerName("132.199.143.90");
		dataSource.setDatabaseName("quizine");
		try {
			Connection conn = (Connection) dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from foodie_recipe_descriptions limit 100;");
			System.out.println(rs.getFetchSize());
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}
*/