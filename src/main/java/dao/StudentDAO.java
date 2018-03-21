package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Student;

public class StudentDAO {

    private static final StudentDAO instance = new StudentDAO();

    private StudentDAO() {
    }

    public static StudentDAO getInstance() {
        return instance;
    }

    public List<Student> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Student c");
        return query.getResultList();
    }
    
    public List<String> findAllIndeks(EntityManager em) {
        Query query = em.createQuery("SELECT c.brindeksa FROM Student c");
        return query.getResultList();
    }

    public Student findStudentById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT c FROM Student c WHERE c.studentPK.iDStudent=:iDStudent");
        query.setParameter("iDStudent", id);
        return (Student) query.getSingleResult();
    }

    public Student add(EntityManager em, Student student) {
        em.persist(student);
        em.flush();
        return student;
    }

    public Student edit(EntityManager em, Student student) {
        em.merge(student);
        em.flush();
        return student;
    }

    public void delete(EntityManager em, Student student) {
        Student managedStudent = em.merge(student);
        em.remove(managedStudent);
    }

    public Student findStudentByBrInd(EntityManager entityManager, String brIndeksa) {
        Query query = entityManager.createQuery("SELECT c FROM Student c WHERE c.brindeksa=:brindeksa");
        query.setParameter("brindeksa", brIndeksa);
        return (Student) query.getSingleResult();
    }

}
