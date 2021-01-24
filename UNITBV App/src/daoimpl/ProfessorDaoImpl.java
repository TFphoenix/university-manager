package daoimpl;

import dao.ProfessorDao;
import entities.*;
import util.ConnectionConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDaoImpl implements ProfessorDao {

    @Override
    public void createTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute("create table if not exists professor\n" +
                    "(\n" +
                    "\tid serial not null\n" +
                    "\t\tconstraint professor_pkey\n" +
                    "\t\t\tprimary key,\n" +
                    "\tname varchar(40),\n" +
                    "\ttitle varchar(30),\n" +
                    "\tid_utilizator integer\n" +
                    "\t\tconstraint professor_id_utilizator_fkey\n" +
                    "\t\t\treferences utilizator\n" +
                    "\t\t\t\ton delete cascade\n" +
                    ");\n" +
                    "\n" +
                    "alter table professor owner to postgres;\n" +
                    "\n");
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
    public void insert(Professor professor) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO professor(" +
                    "name, title, id_utilizator)" +
                    "VALUES (?, ?, ?)");
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getTitle());
            preparedStatement.setInt(3, professor.getIdUtilizator());
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
    public Professor selectByID(int id) {
        Professor professor = new Professor();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM professor WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                professor.setId(resultSet.getInt("id"));
                professor.setName(resultSet.getString("name"));
                professor.setTitle(resultSet.getString("title"));
                professor.setIdUtilizator(resultSet.getInt("id_utilizator"));
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

        return professor;
    }

    @Override
    public List<Professor> selectAll() {
        List<Professor> professori = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM professor");

            while (resultSet.next()) {
                Professor professor = new Professor();
                professor.setId(resultSet.getInt("id"));
                professor.setName(resultSet.getString("name"));
                professor.setTitle(resultSet.getString("title"));
                professor.setIdUtilizator(resultSet.getInt("id_utilizator"));

                professori.add(professor);
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
        return professori;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM professor WHERE id = ?");
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
    public void update(Professor professor, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE professor SET " +
                    "name = ?," +
                    "title = ?," +
                    "id_utilizator = ?" +
                    " WHERE id = ?");
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getTitle());
            preparedStatement.setInt(3, professor.getIdUtilizator());

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
    public Professor selectByUserID(int id) {
        Professor professor = new Professor();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM professor WHERE id_utilizator = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                professor.setId(resultSet.getInt("id"));
                professor.setName(resultSet.getString("name"));
                professor.setTitle(resultSet.getString("title"));
                professor.setIdUtilizator(resultSet.getInt("id_utilizator"));
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

        return professor;
    }

    @Override
    public List<DisciplineTableEntry> getDisciplineTableEntries(int professorId) {
        List<DisciplineTableEntry> professorRegistry = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT name, credits, year, semester FROM subject" +
                    " WHERE id_professor = ?");
            preparedStatement.setInt(1, professorId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DisciplineTableEntry professorReg = new DisciplineTableEntry();
                professorReg.setNume(resultSet.getString("name"));
                professorReg.setCredite(resultSet.getInt("credits"));
                professorReg.setAn(resultSet.getInt("year"));
                professorReg.setSemestru(resultSet.getInt("semester"));

                professorRegistry.add(professorReg);
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
        return professorRegistry;
    }

    @Override
    public List<Subject> getSubjects(int professorId) {
        List<Subject> professorRegistry = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT id, name, credits, year, semester, id_professor FROM subject" +
                    " WHERE id_professor = ?");
            preparedStatement.setInt(1, professorId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Subject professorReg = new Subject();
                professorReg.setId(resultSet.getInt("id"));
                professorReg.setName(resultSet.getString("name"));
                professorReg.setCredits(resultSet.getInt("credits"));
                professorReg.setYear(resultSet.getInt("year"));
                professorReg.setSemester(resultSet.getInt("semester"));
                professorReg.setIdProfessor(resultSet.getInt("id_professor"));

                professorRegistry.add(professorReg);
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
        return professorRegistry;
    }

    @Override
    public List<Group> getGroupsBySubject(int subjectId) {
        List<Group> professorRegistry = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT grupa.id as grupaid, code, specialization " +
                    "FROM (grupa inner join subject_grupa sg on grupa.id = sg.id_grupa)" +
                    " WHERE sg.id_subject = ?");
            preparedStatement.setInt(1, subjectId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Group professorReg = new Group();
                professorReg.setId(resultSet.getInt("grupaid"));
                professorReg.setCode(resultSet.getString("code"));
                professorReg.setSpecialization(resultSet.getString("specialization"));

                professorRegistry.add(professorReg);
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
        return professorRegistry;
    }

    @Override
    public void insertRegistry(Registry registry) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO registry(" +
                    "grade, grading_date, id_subject, id_student)" +
                    "SELECT ?, ?, ?, ?" +
                    " WHERE NOT EXISTS(SELECT 1 FROM registry WHERE id_subject = ? AND id_student = ?)");
            preparedStatement.setInt(1, registry.getGrade());
            preparedStatement.setDate(2, new Date(registry.getGradingDate().getTime()));
            preparedStatement.setInt(3, registry.getIdSubject());
            preparedStatement.setInt(4, registry.getIdStudent());
            preparedStatement.setInt(5, registry.getIdSubject());
            preparedStatement.setInt(6, registry.getIdStudent());
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
    public List<ProfessorRegistryTableEntry> getProfessorRegistryTableEntries(int professorId) {
        List<ProfessorRegistryTableEntry> professorRegistry = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT registry.id as rId, subj.name as subjname, registry.grade as rgrade, registry.grading_date as rgradingdate, g.code as gcode, s.name as studentname\n" +
                    "FROM (registry inner join student s on registry.id_student = s.id inner join grupa g on s.id_grupa = g.id)\n" +
                    "         inner join subject subj on registry.id_subject = subj.id\n" +
                    "WHERE subj.id_professor = ?");
            preparedStatement.setInt(1, professorId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProfessorRegistryTableEntry professorReg = new ProfessorRegistryTableEntry();
                professorReg.setId(resultSet.getInt("rId"));
                professorReg.setDisciplina(resultSet.getString("subjname"));
                professorReg.setNota(resultSet.getInt("rgrade"));
                professorReg.setDataNotarii(resultSet.getDate("rgradingdate").toString());
                professorReg.setGrupa(resultSet.getString("gcode"));
                professorReg.setStudent(resultSet.getString("studentname"));

                professorRegistry.add(professorReg);
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
        return professorRegistry;
    }

    @Override
    public List<ProfessorRegistryTableEntry> getAllProfessorRegistryTableEntries() {
        List<ProfessorRegistryTableEntry> professorRegistry = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT registry.id as rId, subj.name as subjname, registry.grade as rgrade, registry.grading_date as rgradingdate, g.code as gcode, s.name as studentname, p.name as professorname\n" +
                    "FROM (registry inner join student s on registry.id_student = s.id inner join grupa g on s.id_grupa = g.id)\n" +
                    "inner join subject subj on registry.id_subject = subj.id inner join professor p on subj.id_professor = p.id");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProfessorRegistryTableEntry professorReg = new ProfessorRegistryTableEntry();
                professorReg.setId(resultSet.getInt("rId"));
                professorReg.setDisciplina(resultSet.getString("subjname"));
                professorReg.setNota(resultSet.getInt("rgrade"));
                professorReg.setDataNotarii(resultSet.getDate("rgradingdate").toString());
                professorReg.setGrupa(resultSet.getString("gcode"));
                professorReg.setStudent(resultSet.getString("studentname"));

                professorReg.setProfesor(resultSet.getString("professorname"));

                professorRegistry.add(professorReg);
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
        return professorRegistry;
    }

    @Override
    public void deleteRegistry(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM registry WHERE id = ?");
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
    public void updateRegistryGrade(int id, int grade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE registry SET " +
                    "grade = ?" +
                    " WHERE id = ?");
            preparedStatement.setInt(1, grade);
            preparedStatement.setInt(2, id);

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
