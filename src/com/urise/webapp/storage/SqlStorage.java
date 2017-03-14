package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.SQLHelper;
import com.urise.webapp.storage.serializer.StreamSerializer;

import java.sql.*;
import java.util.*;

/**
 * Created by andrew on 11.03.17.
 */
public class SqlStorage implements Storage {
    public final SQLHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SQLHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void save(Resume r) {
        Resume res = r;
        sqlHelper.execute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", ps ->
        {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        });

        sqlHelper.transactionalExecute(conn -> {
            insertContact(conn, r);
            return null;
        });

        sqlHelper.transactionalExecute(conn -> {
            insertSection(conn, r);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        "SELECT * FROM resume r " +
                        "LEFT JOIN contact c " +
                        "ON r.uuid = c.resume_uuid " +
                        "WHERE r.uuid = ?"
                , ps ->
                {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContact(rs, r);
                    } while (rs.next());
                    return r;
                });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid=?", ps ->
        {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = sqlHelper.execute("SELECT * FROM resume r ORDER BY r.full_name, r.uuid", ps ->
        {
            List<Resume> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                list.add(new Resume(uuid, rs.getString("full_name")));
            }
            return list;
        });
        return sqlHelper.execute("SELECT * FROM contact c", ps ->
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("resume_uuid");
                for (int i = 0; i < resumes.size(); i++) {
                    if (uuid.equals(resumes.get(i).getUuid())) {
                        Resume r = resumes.get(i);
                        r.addContact(ContactType.valueOf(rs.getString("type")), rs.getString("value"));
                        resumes.set(i, r);
                    }
                }
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", ps ->
        {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
            else return 0;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            deleteContact(conn, r);
            insertContact(conn, r);
            return null;
        });
    }

    private void insertContact(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContact(Connection conn, Resume r) {
        sqlHelper.execute("DELETE FROM contact WHERE resume_uuid = ?", ps -> {
            ps.setString(1, r.getUuid());
            ps.execute();
            return null;
        });
    }

    private void insertSection(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement psSection = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {
                SectionType st = e.getKey();
                switch (st) {
                    case PERSONAL:
                    case OBJECTIVE:
                        psSection.setString(1, r.getUuid());
                        psSection.setString(2, st.name());
                        psSection.setString(3, ((TextSection) e.getValue()).getContent());
                        psSection.addBatch();
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        psSection.setString(1, r.getUuid());
                        psSection.setString(2, st.name());
                        StringBuilder sb = new StringBuilder();
                        List<String> list = ((ListSection) e.getValue()).getItems();
                        for (String item : list) {
                            sb.append(item + "\n");
                        }
                        psSection.setString(3, sb.toString());
                        psSection.addBatch();
                        break;
                }
            }
            psSection.executeBatch();
        }
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            r.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }
}