package com.topjava.webapp.sql;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.StorageException;
import java.sql.SQLException;

public class ExceptionCheck {

    public static StorageException checkDuplicateKey(SQLException e) {
        return e.getSQLState().equals("23505") ?
                new ExistStorageException(e.getMessage()) :
                new StorageException(e);
    }
}
