package dao;

import entities.*;

import java.util.List;

public interface ProfessorDao {

    void createTable();

    void insert(Professor professor);

    Professor selectByID(int id);

    List<Professor> selectAll();

    void delete(int id);

    void update(Professor professor, int id);

    Professor selectByUserID(int id);

    public List<DisciplineTableEntry> getDisciplineTableEntries(int professorId);

    public List<Subject> getSubjects(int professorId);

    public List<Group> getGroupsBySubject(int subjectId);

    void insertRegistry(Registry registry);

    List<ProfessorRegistryTableEntry> getProfessorRegistryTableEntries(int professorId);

    List<ProfessorRegistryTableEntry> getAllProfessorRegistryTableEntries();

    void deleteRegistry(int id);

    void updateRegistryGrade(int id, int grade);

}
