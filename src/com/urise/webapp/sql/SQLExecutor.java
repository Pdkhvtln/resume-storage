package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by andrew on 11.03.17.
 */

public interface SQLExecutor<T> {
    T execute(PreparedStatement st) throws SQLException;
}
