package com.klasevich.itrex.lab.repository.impl;

import com.klasevich.itrex.lab.entity.User;
import com.klasevich.itrex.lab.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCUserRepositoryImpl implements UserRepository {
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SECOND_NAME = "second_name";
    private static final String SURNAME = "surname";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String IDENTITY_PASSPORT_NUMBER = "identity_passport_number";
    private static final String PHONE_NUMBER = "phone_number";

    private static final String SELECT_ALL_QUERY = "SELECT * FROM users";
    private static final String INSERT_USER_QUERY = "INSERT INTO users (email, password, name, second_name, surname, " +
            "date_of_birth, identity_passport_number, phone_number) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET email=?, password=?, name=?, second_name=?," +
            " surname=?, date_of_birth=?, identity_passport_number=?, phone_number=? WHERE id=?";
    private static final String FIND_USER_BY_ID_QUERY = "SELECT id, email, password, name, second_name, surname, " +
            "date_of_birth, identity_passport_number, phone_number FROM users WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id=?";

    private final DataSource dataSource;

    public JDBCUserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            while (resultSet.next()) {
                User user = convertInUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void add(User user) {
        try (Connection connection = dataSource.getConnection()) {
            insertUser(user, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAll(List<User> users) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            for (User user : users) {
                insertUser(user, connection);
            }
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }


    @Override
    public void delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getSecondName());
            preparedStatement.setDate(6, Date.valueOf(user.getDateOfBirth()));
            preparedStatement.setString(7, user.getIdentityPassportNumber());
            preparedStatement.setString(8, user.getPhoneNumber());
            preparedStatement.setInt(9, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optionalUser = Optional.of(convertInUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
    }

    private User convertInUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setEmail(resultSet.getString(EMAIL));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setName(resultSet.getString(NAME));
            user.setSurname(resultSet.getString(SURNAME));
            user.setSecondName(resultSet.getString(SECOND_NAME));
            user.setDateOfBirth(resultSet.getDate(DATE_OF_BIRTH).toLocalDate());
            user.setIdentityPassportNumber(resultSet.getString(IDENTITY_PASSPORT_NUMBER));
            user.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
            user.setId(resultSet.getInt(ID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private void insertUser(User user, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getSecondName());
            preparedStatement.setDate(6, Date.valueOf(user.getDateOfBirth()));
            preparedStatement.setString(7, user.getIdentityPassportNumber());
            preparedStatement.setString(8, user.getPhoneNumber());

            int effectiveRows = preparedStatement.executeUpdate();

            if (effectiveRows == 1) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(ID));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
