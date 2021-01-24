package daoimpl;

import dao.UtilizatorDao;
import entities.Utilizator;
import util.ConnectionConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizatorDaoImpl implements UtilizatorDao {

    @Override
    public void createUtilizatorTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS utilizator(" +
                    "id SERIAL primary key," +
                    "mail VARCHAR(60)," +
                    "parola VARCHAR(30)," +
                    "drepturi VARCHAR(30))");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void insert(Utilizator utilizator) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO utilizator(" +
                    "mail, parola, drepturi)" +
                    "VALUES (?, ?, ?)");
            preparedStatement.setString(1, utilizator.getMail());
            preparedStatement.setString(2, utilizator.getParola());
            preparedStatement.setString(3, utilizator.getDrepturi());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Utilizator selectByID(int id) {
        Utilizator utilizator = new Utilizator();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM utilizator WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                utilizator.setId(resultSet.getInt("id"));
                utilizator.setMail(resultSet.getString("mail"));
                utilizator.setParola(resultSet.getString("parola"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return utilizator;
    }

    @Override
    public Utilizator selectByMail(String mail) {
        Utilizator utilizator = new Utilizator();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM utilizator WHERE mail = ?");
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                utilizator.setId(resultSet.getInt("id"));
                utilizator.setMail(resultSet.getString("mail"));
                utilizator.setParola(resultSet.getString("parola"));
                utilizator.setDrepturi(resultSet.getString("drepturi"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return utilizator;
    }

    @Override
    public List<Utilizator> selectAll() {
        List<Utilizator> utilizatori = new ArrayList<Utilizator>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECt * FROM utilizator");

            while (resultSet.next()) {
                Utilizator utilizator = new Utilizator();
                utilizator.setId(resultSet.getInt("id"));
                utilizator.setMail(resultSet.getString("mail"));
                utilizator.setParola(resultSet.getString("parola"));
                utilizator.setDrepturi(resultSet.getString("drepturi"));

                utilizatori.add(utilizator);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return utilizatori;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM utilizator WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Utilizator utilizator, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE utilizator SET " +
                    "mail = ?," +
                    "parola = ?," +
                    "drepturi = ? " +
                    "WHERE id = ?");
            preparedStatement.setString(1, utilizator.getMail());
            preparedStatement.setString(2, utilizator.getParola());
            preparedStatement.setString(3, utilizator.getDrepturi());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
