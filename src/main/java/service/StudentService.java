package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.StudentDAO;
import dao.EntityManagerWrapper;
import data.Student;

public class StudentService {

    private static final StudentService instance = new StudentService();

    private StudentService() {
    }

    public static StudentService getInstance() {
        return instance;
    }

    public List<Student> getAll() {
        return StudentDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public List<String> getAllIndeks() {
        return StudentDAO.getInstance().findAllIndeks(EntityManagerWrapper.getEntityManager());
    }
    
    public Student getStudentById(int id) {
        return StudentDAO.getInstance().findStudentById(EntityManagerWrapper.getEntityManager(), id);
    }

    public Student getStudentByBrInd(String brIndeksa) {
        return StudentDAO.getInstance().findStudentByBrInd(EntityManagerWrapper.getEntityManager(), brIndeksa);
    }
    
    public Student add(Student student) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(student);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Student newStudent = StudentDAO.getInstance().add(em, student);
            em.getTransaction().commit();

            return newStudent;
        } finally {
            em.close();
        }
    }

    public Student edit(Student student) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(student);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Student updatedStudent = StudentDAO.getInstance().edit(em, student);
            em.getTransaction().commit();

            return updatedStudent;
        } finally {
            em.close();
        }
    }

    public void delete(Student student) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (student.getStudentPK().getIDStudent() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            StudentDAO.getInstance().delete(em, student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Student student) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (student.getIme() == null || student.getIme().trim().isEmpty()) {
            errors.add("Parametar ime je obavezan.");
        }
        if (student.getPrezime() == null || student.getPrezime().trim().isEmpty()) {
            errors.add("Parametar prezime je obavezan.");
        }
        if (student.getBrindeksa() == null || student.getBrindeksa().trim().isEmpty()) {
            errors.add("Parametar broj indeksa je obavezan.");
        }
        if (student.getFKLog() ==null) {
            errors.add("Parametar login je obavezan.");
        }
        if (student.getSmer() ==null) {
            errors.add("Parametar smer je obavezan.");
        }
        if (student.getEmail()== null || student.getEmail().trim().isEmpty()) {
            errors.add("Parametar email je obavezan.");
        }
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

}
