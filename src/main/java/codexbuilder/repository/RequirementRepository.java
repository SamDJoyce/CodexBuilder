package codexbuilder.repository;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.WargearDescription;
import codexbuilder.model.requirements.AbstractRequirement;

public class RequirementRepository {

    private final SessionFactory sessionFactory;

    public RequirementRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * Retrieve all Requirements from the DB
     * 
     * @return	a list of all Requirements in the DB
     */
    public List<AbstractRequirement> findAll(){
    	try (Session session = sessionFactory.openSession()) {
    		List<AbstractRequirement> requirements = 
    				session.createQuery(
                     "FROM AbstractRequirement",
                     AbstractRequirement.class
             ).getResultList();
    		 
    		 requirements.forEach(this::initializeWargear);
    		 return requirements;
    	}
    }
    
    /**
     * Retrieve a Requirement from the DB by its ID
     * 
     * @param id	Requirement ID
     * @return		the Requirement matching the provided ID
     */
    public AbstractRequirement findById(Long id) {

        if (id == null) {
            return null;
        }

        try (Session session = sessionFactory.openSession()) {

            AbstractRequirement req =
                session.find(AbstractRequirement.class, id);
            
            if (req != null) {
            	initializeWargear(req);
            }
           
            return req;
        }
    }
    
    /**
     * Save a Requirement to the DB
     * 
     * @param req	the Requirement to be saved
     * @return		the Requirement as saved in the DB
     */
    public AbstractRequirement save(AbstractRequirement req) {
    	Transaction transaction = null;
    	
    	try(Session session = sessionFactory.openSession()){
    		transaction = session.beginTransaction();
    		
    		AbstractRequirement savedReq = session.merge(req);
    		initializeWargear(savedReq);
    		
    		transaction.commit();
    		
    		return savedReq;
    		
    	} catch (RuntimeException e) {
    		if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
    	}
    }
    
    /**
     * Delete a Requirement from the DB
     * 
     * @param req	The Requirement to be deleted
     */
    public void delete(AbstractRequirement req) {
    	Transaction transaction = null;
    	
    	try (Session session = sessionFactory.openSession()){
    		transaction = session.beginTransaction();
    		AbstractRequirement toDelete =
    			    session.find(AbstractRequirement.class, req.getId());

    			if (toDelete != null) {
    			    session.remove(toDelete);
    			}
    		transaction.commit();
    		
    	} catch (RuntimeException e) {
    		if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw e;
    	}
    }
    
    private void initializeWargear(AbstractRequirement req) {

        if (req == null) {
            return;
        }

        if (req.getBlockingGear() != null) {
            Hibernate.initialize(req.getBlockingGear());
        }

        if (req.getRequiredGear() != null) {
            Hibernate.initialize(req.getRequiredGear());
        }

//        ModelDescription model = req.getModel();
//        if (model != null) {
//            Hibernate.initialize(model);
//        }
    }
    
}
