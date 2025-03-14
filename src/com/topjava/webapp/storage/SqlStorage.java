package com.topjava.webapp.storage;

import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.sql.ConnectionFactory;
import com.topjava.webapp.sql.ExceptionCheck;
import com.topjava.webapp.sql.SqlExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        };
    }

    @Override
    public int size() {
        String sqlCode ="SELECT COUNT(*) FROM resume";
        return (int) getConnection(sqlCode, new SqlExecutor() {
            @Override
            public Object execute(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt(1);
            }
        });
    }

    @Override
    public void update(Resume resume) {
        String sqlCode = "UPDATE resume SET full_name = ? WHERE uuid = ?";
        getConnection(sqlCode, new SqlExecutor() {
            @Override
            public Object execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if(ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
                return null;
            }
        });
    }

    @Override
    public Resume get(String uuid) {
        String sqlCode = "SELECT * FROM resume r WHERE r.uuid = ?";
        return (Resume) getConnection(sqlCode, new SqlExecutor() {
            @Override
            public Resume execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                return new Resume(uuid, rs.getString("full_name"));
            }
        });
    }

    @Override
    public void clear() {
        String sqlCode = "DELETE FROM resume";
        getConnection(sqlCode, new SqlExecutor() {
            @Override
            public Object execute(PreparedStatement ps) throws SQLException {
                ps.execute();
                return null;
            }
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        String sqlCode = "SELECT * FROM resume r ORDER BY full_name , uuid";
        return (List<Resume>) getConnection(sqlCode, new SqlExecutor() {
            @Override
            public List<Resume> execute(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                List<Resume> resumes = new ArrayList<>();
                while (rs.next()) {
                    resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
                }
                return resumes;
            }
        });
    }

    @Override
    public void save(Resume resume) {
        String sqlCode = "INSERT INTO resume(uuid, full_name) VALUES (?, ?)";
        getConnection(sqlCode, new SqlExecutor() {
            @Override
            public Object execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.executeUpdate();
                return null;
            }
        });
    }

    @Override
    public void delete(String uuid) {
        String sqlCode = "DELETE FROM resume WHERE uuid = ?";
        getConnection(sqlCode, new SqlExecutor() {
            @Override
            public Object execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, uuid);
                if(ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(uuid);
                }
                return null;
            }
        });
    }

    private <T> T getConnection(String sqlCode, SqlExecutor<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlCode)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw ExceptionCheck.checkDuplicateKey(e);
        }
    }
}
