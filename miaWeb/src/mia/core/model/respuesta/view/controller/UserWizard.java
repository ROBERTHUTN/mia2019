package mia.core.model.respuesta.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import mia.core.model.login.view.controller.BeanLogin;
import mia.core.model.reporte.ManagerReporte;
import mia.core.model.reporte.ManagerReportePrePost;
import mia.core.model.reporte.User;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

@Named
@ViewScoped
public class UserWizard implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private BeanLogin beanLogin;

	@EJB
	private ManagerReporte managerReporte; 
	
	@EJB
	private ManagerReportePrePost managerReporteprepost; 
	
	
	    private User user = new User();
	     
	    
	    


	    
	    
	    private boolean skip;
	     
	    public User getUser() {
	        return user;
	    }
	 
	    public void setUser(User user) {
	        this.user = user;
	    }
	     
	    public String save() {        
	    	try {
	    	managerReporteprepost.respuestasIE(user);
	    	
	    	System.out.println(" entra");
	    	
	     
	    	
	    	
	        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getUno());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
	        return"tipoLiderazgo2.xhtml?faces-redirect=true";
	    	}catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			}
	    	return"";
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

	
	