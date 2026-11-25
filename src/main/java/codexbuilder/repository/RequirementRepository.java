package codexbuilder.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;

import codexbuilder.model.AbstractStatLine;
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
    		 return session.createQuery(
                     "FROM AbstractRequirement",
                     AbstractRequirement.class
             ).list();
    	}
    }
    
    /**
     * Retrieve a Requirement from the DB by its ID
     * 
     * @param id	Requirement ID
     * @return		the Requirement matching the provided ID
     */
    public AbstractRequirement findById(Long id) {
    	try (Session session = sessionFactory.openSession()) {
            return session.find(AbstractRequirement.class, id);
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
    		AbstractRequirement toDelete = session.merge(req);
    		session.remove(toDelete);
    		transaction.commit();
    		
    	} catch (RuntimeException e) {
    		if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw e;
    	}
    }
    
    
}
