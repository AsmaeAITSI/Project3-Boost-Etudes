package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.exception.UserException;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Teacher;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.TeacherDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher saveTeacher(Teacher newTeacher) {
        Teacher add = teacherDao.save(newTeacher);
        return add;
    }

    @Override
    public List<Teacher> getAllTeacher() {
        List<Teacher> list = teacherDao.findAll();

        if (list.size() > 0) {
            return list;
        } else {
            throw new UserException("No teacher found");

        }
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        Optional<Teacher> foundTeacher = teacherDao.findById(teacherId);

        if (foundTeacher.isPresent()) {
            return foundTeacher.get();
        } else throw new UserException("teacher not found by this id");
        // pas besoin de faire l'opération dans le controller car ça renverra une exception.
    }



}
