package com.urise.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by andrew on 11.03.17.
 */
public interface ConnectionFactory {
    Connection getConnection() throws SQLException;
}
