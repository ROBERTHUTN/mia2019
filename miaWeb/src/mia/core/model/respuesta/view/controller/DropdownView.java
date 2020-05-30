package mia.core.model.respuesta.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import mia.core.model.reporte.ManagerReporte;
import mia.core.model.reporte.User;

import java.io.Serializable;

@Named
@ViewScoped
public class DropdownView implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerReporte managerReporte; 
	
	    private User user = new User();
	     
	    
	    


	    
	    
	    private boolean skip;
	     
	    public User getUser() {
	        return user;
	    }
	 
	    public void setUser(User user) {
	        this.user = user;
	    }
	     
	    public void save() {        
	    	managerReporte.respuestasIE(user);
	    	System.out.println(" entra");
	    	managerReporte.ingresarIEEL(user);
	    	
	    	
	        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getUno());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	     
	    public boolean isSkip() {
	        return skip;
	    }
	 
	    public void setSkip(boolean skip) {
	        this.skip = skip;
	    }
	     
	    public String onFlowProcess(FlowEvent event) {
	    
	        if(skip) {
	            skip = false;   //reset in case user goes back
	            return "confirm";
	        }
	        else {
	            return event.getNewStep();
	        }
	    }
	}

	
	