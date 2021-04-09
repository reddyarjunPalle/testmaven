package com.slokam.healthcare.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;
import com.slokam.healthcare.util.StringUtils;

@Repository
public class Patientcriteria {
	@Autowired
	private EntityManager em;
	public List<Patient> patientFullSearch(PatientSearchPojo searchPojo){
		CriteriaBuilder cb=em.getCriteriaBuilder();
		
		CriteriaQuery<Patient> qp=cb.createQuery(Patient.class);
		Root<Patient> root=qp.from(Patient.class);
		List<Predicate> predicates=new ArrayList<Predicate>();
		if(searchPojo!=null) {
			if(StringUtils.blankCheck(searchPojo.getName())) {
				predicates.add(cb.like(root.get("name"),searchPojo.getName()+"%"));
			}
			if(searchPojo.getPhone()!=null) {
				predicates.add(cb.equal(root.get("phone"),searchPojo.getPhone()) );	
			}
			if(searchPojo.getFromdate()!=null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("regdate"),searchPojo.getFromdate() ));
			}
			if(searchPojo.getTodate()!=null) {
				predicates.add(cb.lessThan(root.get("regdate"), searchPojo.getTodate()));
			}
			if(searchPojo.getStartingAge()!=null) {
				predicates.add(cb.ge(root.get("age"),searchPojo.getStartingAge() ));	
			}
			if(searchPojo.getEndingAge()!=null) {
				predicates.add(cb.le(root.get("age"),searchPojo.getEndingAge() ));	
			}
		}
			qp.where(predicates.toArray(new Predicate[predicates.size()]));
			   TypedQuery<Patient> patientQuery = em.createQuery(qp);
		  
			   return patientQuery.getResultList();
		}
	public List<Patient> patientSearch(String name, String email) {
        
		   // from Patient where name=? and email like ?
		    CriteriaBuilder cb = em.getCriteriaBuilder();
	       
	        CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
	        
	        List<Predicate> predicates = new ArrayList<Predicate>();
	        
	        
	        Root<Patient> root = cq.from(Patient.class);
	        
	        if(name !=null && name.trim().length()>0) {
	        	Predicate p = cb.equal(root.get("name"), name);
	        	predicates.add(p);
	        }
	        if(email !=null && email.trim().length()>0) {
	        	Predicate p = cb.like(root.get("email"), "%" + email + "%");
	        	predicates.add(p);
	        }
	        
	        cq.where(predicates.toArray(new Predicate[predicates.size()]));

	        TypedQuery<Patient> query = em.createQuery(cq);
	        return query.getResultList();
	    }
	
	}
		


