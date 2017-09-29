package ga.rugal.sample.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.rugal.sample.core.dao.CourseDAO;
import ga.rugal.sample.core.entity.Course;
import ga.rugal.sample.core.service.CourseService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ml.rugal.sshcommon.hibernate.Updater;

/**
 *
 * @author Rugal Bernstein
 */
@Setter
@Service
@Slf4j
public class CourseServiceImpl implements CourseService
{

    @Autowired
    private CourseDAO courseDAO;

    @Override
    public CourseDAO getDAO()
    {
        return this.courseDAO;
    }

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

    @Override
    public Course update(Course course)
    {
        Updater<Course> updater = new Updater<>(course);
        return this.courseDAO.updateByUpdater(updater);
    }
}
