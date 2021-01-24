package daoimpl;

import dao.StudentDao;
import entities.*;
import util.ConnectionConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public void createStudentTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute("create table if not exists student \n" +
                    "(\n" +
                    "\tid serial not null\n" +
                    "\t\tconstraint student_pkey\n" +
                    "\t\t\tprimary key,\n" +
                    "\tbadge varchar(10),\n" +
                    "\tname varchar(40),\n" +
                    "\tcnp varchar(13),\n" +
                    "\tdomicile varchar(30),\n" +
                    "\tdate_of_birth date,\n" +
                    "\tdate_of_registration date default CURRENT_DATE,\n" +
                    "\tid_utilizator integer\n" +
                    "\t\tconstraint student_id_utilizator_fkey\n" +
                    "\t\t\treferences utilizator\n" +
                    "\t\t\t\ton delete cascade,\n" +
                    "\tid_grupa integer\n" +
                    "\t\tconstraint student_id_grupa_fkey\n" +
                    "\t\t\treferences grupa\n" +
                    "\t\t\t\ton delete cascade\n" +
                    ");\n" +
                    "\n" +
                    "alter table student owner to postgres;\n" +
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
    public void insert(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO student(" +
                    "badge, name, cnp, domicile, date_of_birth, date_of_registration, id_utilizator, id_grupa)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, student.getBadge());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getCnp());
            preparedStatement.setString(4, student.getDomicile());
            preparedStatement.setDate(5, new java.sql.Date(student.getDateOfBirth().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(student.getDateOfRegistration().getTime()));
            preparedStatement.setInt(7, student.getIdUtilizator());
            preparedStatement.setInt(8, student.getIdGrupa());
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
    public Student selectByID(int id) {
        Student student = new Student();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setBadge(resultSet.getString("badge"));
                student.setName(resultSet.getString("name"));
                student.setCnp(resultSet.getString("cnp"));
                student.setDomicile(resultSet.getString("domicile"));
                student.setDateOfBirth(resultSet.getTimestamp("date_of_birth"));
                student.setDateOfRegistration(resultSet.getTimestamp("date_of_registration"));
                student.setIdUtilizator(resultSet.getInt("id_utilizator"));
                student.setIdGrupa(resultSet.getInt("id_grupa"));
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

        return student;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> studenti = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student");

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setBadge(resultSet.getString("badge"));
                student.setName(resultSet.getString("name"));
                student.setCnp(resultSet.getString("cnp"));
                student.setDomicile(resultSet.getString("domicile"));
                student.setDateOfBirth(resultSet.getTimestamp("date_of_birth"));
                student.setDateOfRegistration(resultSet.getTimestamp("date_of_registration"));
                student.setIdUtilizator(resultSet.getInt("id_utilizator"));
                student.setIdGrupa(resultSet.getInt("id_grupa"));

                studenti.add(student);
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
        return studenti;
    }

    @Override
    public List<Student> selectByGroupId(int groupId) {
        List<Student> studenti = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM student " +
                    "WHERE id_grupa = ?");
            preparedStatement.setInt(1, groupId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setBadge(resultSet.getString("badge"));
                student.setName(resultSet.getString("name"));
                student.setCnp(resultSet.getString("cnp"));
                student.setDomicile(resultSet.getString("domicile"));
                student.setDateOfBirth(resultSet.getTimestamp("date_of_birth"));
                student.setDateOfRegistration(resultSet.getTimestamp("date_of_registration"));
                student.setIdUtilizator(resultSet.getInt("id_utilizator"));
                student.setIdGrupa(resultSet.getInt("id_grupa"));

                studenti.add(student);
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
        return studenti;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM student WHERE id = ?");
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
    public void update(Student student, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE student SET " +
                    "badge =?," +
                    "name = ?," +
                    "cnp = ?," +
                    "domicile = ?," +
                    "date_of_birth = ?," +
                    "date_of_registration = ?," +
                    "id_utilizator = ?," +
                    "id_grupa = ?" +
                    "WHERE id = ?");
            preparedStatement.setString(1, student.getBadge());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getCnp());
            preparedStatement.setString(4, student.getDomicile());
            preparedStatement.setDate(5, new java.sql.Date(student.getDateOfBirth().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(student.getDateOfRegistration().getTime()));
            preparedStatement.setInt(7, student.getIdUtilizator());
            preparedStatement.setInt(8, student.getIdGrupa());
            preparedStatement.setInt(9, id);

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
    public List<Registry> getStudentRegistry(int studentId) {
        List<Registry> studentRegistry = new ArrayList<Registry>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM registry WHERE id_student = ?");
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Registry studentReg = new Registry();
                studentReg.setId(resultSet.getInt("id"));
                studentReg.setGrade(resultSet.getInt("grade"));
                studentReg.setGradingDate(resultSet.getTimestamp("grading_date"));
                studentReg.setIdSubject(resultSet.getInt("id_subject"));
                studentReg.setIdStudent(resultSet.getInt("id_student"));

                studentRegistry.add(studentReg);
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
        return studentRegistry;
    }

    @Override
    public List<RegistryTableEntry> getStudentGradedRegistryTableEntries(int studentId, int year, int semester) {
        List<RegistryTableEntry> studentRegistry = new ArrayList<RegistryTableEntry>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT subject.name as subjectname, subject.credits as subjectcredits, p.name as professor, registry.grade as grade, registry.grading_date as gradingdate, subject.year as subjectyear, subject.semester as subjectsemester\n" +
                    "FROM (registry" +
                    " INNER JOIN (subject INNER JOIN professor p on subject.id_professor = p.id) ON registry.id_subject = subject.id)" +
                    " WHERE id_student = ? AND subject.year = ? AND subject.semester = ?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, semester);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RegistryTableEntry studentReg = new RegistryTableEntry();
                studentReg.setMaterie(resultSet.getString("subjectname"));
                studentReg.setCredite(resultSet.getInt("subjectcredits"));
                studentReg.setProfesor(resultSet.getString("professor"));
                studentReg.setStatus(resultSet.getInt("grade") > 5 ? "promovat" : "restant");
                studentReg.setNota(String.valueOf(resultSet.getInt("grade")));
                studentReg.setDataNotarii(resultSet.getDate("gradingdate").toString());
                studentReg.setAn(resultSet.getInt("subjectyear"));
                studentReg.setSemestru(resultSet.getInt("subjectsemester"));

                studentRegistry.add(studentReg);
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
        return studentRegistry;
    }

    @Override
    public List<RegistryTableEntry> getStudentUngradedRegistryTableEntries(int studentId, int year, int semester) {
        List<RegistryTableEntry> studentRegistry = new ArrayList<RegistryTableEntry>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT subj.name as subjectname, subj.credits as subjectcredits, p.name as professor, subj.year as subjectyear, subj.semester as subjectsemester\n" +
                    "FROM (subject_grupa INNER JOIN\n" +
                    "    (subject as subj INNER JOIN professor p on subj.id_professor = p.id) on subject_grupa.id_subject = subj.id\n" +
                    "    INNER JOIN student s on subject_grupa.id_grupa = s.id_grupa)\n" +
                    "WHERE s.id = ? AND subj.year = ? AND subj.semester = ?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, semester);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RegistryTableEntry studentReg = new RegistryTableEntry();
                studentReg.setMaterie(resultSet.getString("subjectname"));
                studentReg.setCredite(resultSet.getInt("subjectcredits"));
                studentReg.setProfesor(resultSet.getString("professor"));
                studentReg.setAn(resultSet.getInt("subjectyear"));
                studentReg.setSemestru(resultSet.getInt("subjectsemester"));

                studentRegistry.add(studentReg);
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
        return studentRegistry;
    }

    @Override
    public Student selectByUserID(int id) {
        Student student = new Student();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE id_utilizator = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setBadge(resultSet.getString("badge"));
                student.setName(resultSet.getString("name"));
                student.setCnp(resultSet.getString("cnp"));
                student.setDomicile(resultSet.getString("domicile"));
                student.setDateOfBirth(resultSet.getTimestamp("date_of_birth"));
                student.setDateOfRegistration(resultSet.getTimestamp("date_of_registration"));
                student.setIdUtilizator(resultSet.getInt("id_utilizator"));
                student.setIdGrupa(resultSet.getInt("id_grupa"));
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

        return student;
    }

    @Override
    public Group getStudentGroup(int groupId) {
        Group studentGroup = new Group();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM grupa WHERE id = ?");
            preparedStatement.setInt(1, groupId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                studentGroup.setId(resultSet.getInt("id"));
                studentGroup.setCode(resultSet.getString("code"));
                studentGroup.setSpecialization(resultSet.getString("specialization"));
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
        return studentGroup;
    }

    @Override
    public int getGroupStudentsCount(int groupId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM\n" +
                    "(student INNER JOIN grupa g on student.id_grupa = g.id) WHERE id_grupa = ?");
            preparedStatement.setInt(1, groupId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt("count");
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
        return count;
    }

    @Override
    public int getStudentsCount() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM student");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt("count");
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
        return count;
    }

    @Override
    public int getStudentGroupRank(int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rank = 0;
        int groupId = selectByID(studentId).getIdGrupa();

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT id_student, AVG(grade) AS media\n" +
                    "FROM (registry inner join student s on registry.id_student = s.id) WHERE id_grupa=?\n" +
                    "GROUP BY id_student\n" +
                    "ORDER BY media DESC");
            preparedStatement.setInt(1, groupId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ++rank;
                int id = resultSet.getInt("id_student");
                if (id == studentId) {
                    return rank;
                }
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
        return rank;
    }

    @Override
    public int getStudentRank(int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rank = 0;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT id_student, AVG(grade) AS media\n" +
                    "FROM registry GROUP BY id_student\n" +
                    "ORDER BY media DESC");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ++rank;
                int id = resultSet.getInt("id_student");
                if (id == studentId) {
                    return rank;
                }
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
        return rank;
    }

    @Override
    public int getGradeScholarshipsCount(int groupId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT grade_s_count FROM scholarship" +
                    " WHERE id_grupa = ?");
            preparedStatement.setInt(1, groupId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt("grade_s_count");
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
        return count;
    }

    @Override
    public List<Group> selectAllGroups() {
        List<Group> groups = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM grupa");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setCode(resultSet.getString("code"));
                group.setSpecialization(resultSet.getString("specialization"));

                groups.add(group);
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
        return groups;
    }

    @Override
    public List<StudentTableEntry> getAllStudentTableEntries() {
        List<StudentTableEntry> studenti = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student " +
                    "inner join grupa g on student.id_grupa = g.id " +
                    "inner join utilizator u on student.id_utilizator = u.id");

            while (resultSet.next()) {
                StudentTableEntry student = new StudentTableEntry();
                student.setId(resultSet.getInt("id"));
                student.setUtilizatorId(resultSet.getInt("id_utilizator"));

                student.setBadge(resultSet.getString("badge"));
                student.setName(resultSet.getString("name"));
                student.setCnp(resultSet.getString("cnp"));
                student.setDomicile(resultSet.getString("domicile"));
                student.setDateOfBirth(resultSet.getDate("date_of_birth").toString());
                student.setDateOfRegistration(resultSet.getDate("date_of_registration").toString());
                student.setMail(resultSet.getString("mail"));
                student.setGroup(resultSet.getString("code"));

                studenti.add(student);
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
        return studenti;
    }

    @Override
    public List<ScholarshipTableEntry> getScholarshipTableEntries() {
        List<ScholarshipTableEntry> scholarships = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM scholarship " +
                    "inner join grupa g on scholarship.id_grupa = g.id");

            while (resultSet.next()) {
                ScholarshipTableEntry scholarship = new ScholarshipTableEntry();
                scholarship.setId(resultSet.getInt("id"));
                scholarship.setGrupa(resultSet.getString("code"));
                scholarship.setGradeScolarshipCount(resultSet.getInt("grade_s_count"));
                scholarship.setSpecialScolarshipCount(resultSet.getInt("special_s_count"));

                scholarships.add(scholarship);
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
        return scholarships;
    }

    @Override
    public void deleteScholarship(int scholarshipId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM scholarship WHERE id = ?");
            preparedStatement.setInt(1, scholarshipId);
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
    public void insertScholarship(Scholarship scholarship) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO scholarship(" +
                    "id_grupa, grade_s_count, special_s_count)" +
                    "SELECT ?, ?, ?" +
                    " WHERE NOT EXISTS(SELECT 1 FROM scholarship WHERE id_grupa = ?)");
            preparedStatement.setInt(1, scholarship.getGrupaId());
            preparedStatement.setInt(2, scholarship.getGradeSCount());
            preparedStatement.setInt(3, scholarship.getSpecialSCount());
            preparedStatement.setInt(4, scholarship.getGrupaId());
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
