package codexbuilder.repository;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import codexbuilder.model.effects.AbstractEffect;
import codexbuilder.model.requirements.AbstractRequirement;

public class EffectRepository {
	private final SessionFactory sessionFactory;
	
	public EffectRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	// CRUD Operations
	
	/**
	 * Retrieve all Effects from the DB
	 * @return	a list of all Effects in the DB
	 */
	public List<AbstractEffect> findAll(){
		 try(Session session = sessionFactory.openSession()){
			 List<AbstractEffect> effects = 
					 session.createQuery(
						"FROM AbstractEffect",
						AbstractEffect.class
					).getResultList();
			 effects.forEach(this::initializeWargear);
			 return effects;
		 }
		 
	}
	
	/**
	 * Retrieve an Effect from the DB by its ID
	 * 
	 * @param id	Effect ID
	 * @return		the Effect matching the provided ID
	 */
	public AbstractEffect findById(Long id) {
		if (id == null) {
            return null;
        }
		try (Session session = sessionFactory.openSession()) {

            AbstractEffect effect =
                session.find(AbstractEffect.class, id);
            
            if (effect != null) {
            	initializeWargear(effect);
            }
           
            return effect;
        }
		
	}
	
	/**
	 * Save an Effect to the DB
	 * @param effect	The Effect to be saved
	 * @return			The Effect as saved in the DB
	 */
	public AbstractEffect save(AbstractEffect effect) {
    	Transaction transaction = null;
    	
    	try(Session session = sessionFactory.openSession()){
    		transaction = session.beginTransaction();
    		
    		AbstractEffect savedEffect= session.merge(effect);
    		initializeWargear(savedEffect);
    
    		transaction.commit();
    		
    		return savedEffect;
    		
    	} catch (RuntimeException e) {
    		if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
    	}
	}
	
	/**
	 * Delete an Effect from the DB
	 * @param effect	Effect to be deleted
	 */
    public void delete(AbstractEffect effect) {
    	Transaction transaction = null;
    	
    	try (Session session = sessionFactory.openSession()){
    		transaction = session.beginTransaction();
    		AbstractEffect toDelete =
    			    session.find(AbstractEffect.class, effect.getId());

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
    
    // Initialize Dependencies
    private void initializeWargear(AbstractEffect effect) {

        if (effect == null) {
            return;
        }

        if (effect.getGear() != null) {
            Hibernate.initialize(effect.getGear());
        }

        if (effect.getAdd() != null) {
            Hibernate.initialize(effect.getAdd());
        }
        
        if (effect.getRemove() != null) {
        	Hibernate.initialize(effect.getRemove());
        }


    }
    
    private void initializeModels(AbstractEffect effect) {
//      ModelDescription model = effect.getModel();
//      if (model != null) {
//          Hibernate.initialize(model);
//      }
//	    ModelDescription newModel = effect.getNewModel();
//	    if (newModel != null) {
//	        Hibernate.initialize(newModel);
//	    }
//	    ModelDescription oldModel = effect.getOldModel();
//	    if (oldModel != null) {
//	        Hibernate.initialize(oldModel);
//	    }
    }
}
