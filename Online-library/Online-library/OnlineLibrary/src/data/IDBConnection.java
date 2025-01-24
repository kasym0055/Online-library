package src.data;

import java.sql.Connection;

public interface IDBConnection {

    Connection getConnection();
}
