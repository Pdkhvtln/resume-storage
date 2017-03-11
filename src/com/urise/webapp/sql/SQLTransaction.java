package com.urise.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by andrew on 11.03.17.
 */
public interface SQLTransaction <T> {
    T execute(Connection conn) throws SQLException;
}
