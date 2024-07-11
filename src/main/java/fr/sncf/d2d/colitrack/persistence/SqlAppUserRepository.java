package fr.sncf.d2d.colitrack.persistence;

import fr.sncf.d2d.colitrack.domain.AppUser;
import fr.sncf.d2d.colitrack.domain.AppUserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SqlAppUserRepository implements AppUserRepository {

    private final JdbcTemplate jdbc;

    public SqlAppUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public AppUser save(AppUser input) {
        String sql = """
                INSERT INTO app_user (username, password)
                VALUES (?, ?)
                """;
        this.jdbc.update(sql, input.getUsername(), input.getPassword());
        return input;
    }

    @Override
    public Optional<AppUser> findById(String id) {
        String sql = """
                SELECT * FROM app_user
                WHERE username = ?
                """;
        return this.jdbc.query(sql, this::extractOne, id);
    }

    private Optional<AppUser> extractOne(ResultSet rs) throws SQLException {
        if (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            return Optional.of(new AppUser(username, password));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<AppUser> find() {
        String sql = """
                SELECT * FROM app_user
                """;
        return this.jdbc.query(sql, this::extractAll);
    }

    private List<AppUser> extractAll(ResultSet rs) throws SQLException {
        List<AppUser> result = new ArrayList<>();
        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            result.add(new AppUser(username, password));
        }
        return result;
    }

    @Override
    public void delete(String id) {
        String sql = """
                DELETE FROM app_user
                WHERE username = ?
                """;
        this.jdbc.update(sql, id);
    }
}
