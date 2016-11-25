package org.example.doit;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.example.organizer.model.Event;
import org.springframework.transaction.annotation.Transactional;

public class JpaEventDao implements EventDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Event> findAll() {
		CriteriaQuery<Event> query =em.getCriteriaBuilder().createQuery(Event.class);
		query.from(Event.class);
		
		List<Event> lResult = 	em.createQuery(query).getResultList();
		return lResult;
	}

	@Override
	@Transactional
	public void save(Event event) {
		if (event.getId()==null){
			em.persist(event);
		} else {
			em.merge(event);			
		}		
	}

	@Override
	@Transactional
	public void saveAll(List<Event> lEvents) {
		for (Event event: lEvents){
			save(event);
		}
	}

	@Override
	@Transactional
	public Event findById(Long id) {
		return em.find(Event.class, id);
	}

	@Override
	@Transactional
	public void delete(Event event) {
		em.remove(event);
		
	}

}
