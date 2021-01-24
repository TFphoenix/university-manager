package dao;

import entities.*;

import java.util.List;

public interface StudentDao {
    void createStudentTable();

    void insert(Student student);

    Student selectByID(int id);

    List<Student> selectAll();

    List<Student> selectByGroupId(int groupId);

    void delete(int id);

    void update(Student student, int id);

    List<Registry> getStudentRegistry(int studentId);

    List<RegistryTableEntry> getStudentGradedRegistryTableEntries(int studentId, int year, int semester);

    List<RegistryTableEntry> getStudentUngradedRegistryTableEntries(int studentId, int year, int semester);

    Student selectByUserID(int id);

    Group getStudentGroup(int groupId);

    int getGroupStudentsCount(int groupId);

    int getStudentsCount();

    int getStudentGroupRank(int studentId);

    int getStudentRank(int studentId);

    int getGradeScholarshipsCount(int groupId);

    List<Group> selectAllGroups();

    List<StudentTableEntry> getAllStudentTableEntries();

    List<ScholarshipTableEntry> getScholarshipTableEntries();

    void deleteScholarship(int scholarshipId);

    void insertScholarship(Scholarship scholarship);

}
