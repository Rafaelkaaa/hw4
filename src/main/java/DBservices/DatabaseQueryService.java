package DBservices;

import Model.MaxProjectCountClient;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public ResultSet resultOfSQLRequest(Database database, String path){
        String sql = null;
        try {
            sql = String.join("\n", Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return database.statementQuery(sql);
    }

    public List<MaxProjectCountClient> findMaxProjectsClient(Database database) throws SQLException {
        ResultSet rs = resultOfSQLRequest
                (database, "src/main/resources/SQL/find_max_projects_client.sql");
        List<MaxProjectCountClient> MPCCList  = new ArrayList();
        while (rs.next()){
            MaxProjectCountClient MPCC = new MaxProjectCountClient();
            MPCC.setName(rs.getString("NAME"));
            MPCC.setProjectCount(rs.getInt("COUNT(CLIENT_ID)"));
            MPCCList.add(MPCC);
        }
        return MPCCList;
    }

}
