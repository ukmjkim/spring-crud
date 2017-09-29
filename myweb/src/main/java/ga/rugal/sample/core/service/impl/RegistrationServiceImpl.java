package ga.rugal.sample.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.rugal.sample.core.dao.RegistrationDAO;
import ga.rugal.sample.core.entity.Registration;
import ga.rugal.sample.core.service.RegistrationService;
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
public class RegistrationServiceImpl implements RegistrationService
{

    @Autowired
    private RegistrationDAO registrationDAO;

    @Override
    public RegistrationDAO getDAO()
    {
        return this.registrationDAO;
    }

	public void setRegistrationDAO(RegistrationDAO registrationDAO) {
    	this.registrationDAO = registrationDAO;
    }

    @Override
    public Registration update(Registration registration)
    {
        Updater<Registration> updater = new Updater<>(registration);
        return this.registrationDAO.updateByUpdater(updater);
    }
}
