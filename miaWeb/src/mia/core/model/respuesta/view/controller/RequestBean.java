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

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Named
@ViewScoped
public class RequestBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<String> getList() {
        return list;
    }

    public void setList(java.util.List<String> list) {
        this.list = list;
        System.out.println("Values set: " + list);
    }

    private List<String> list;
    
    private boolean skip;
       
    
    @PostConstruct
    private void init() {
        list = new ArrayList<String>() {
            {
                add("one");
                add("two");
                add("three");
            }
        };
    }
            
    
    public void listener() {
        System.out.println("Listener called!");
    }
	    
	  
	    public void save() {        
	    	//managerReporte.respuestasIE(user);
	    	System.out.println(" entra");
	    //	managerReporte.ingresarIEEL(user);
	    	
	    	
	        FacesMessage msg = new FacesMessage("Successful", "Welcome :" );
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

	
	