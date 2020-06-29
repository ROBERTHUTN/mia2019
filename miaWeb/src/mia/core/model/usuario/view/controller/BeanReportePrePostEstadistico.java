package mia.core.model.usuario.view.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.optionconfig.title.Title;

import mia.core.model.entities.Reporteprepost;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Named
@ViewScoped
public class BeanReportePrePostEstadistico implements Serializable {
	private static final long serialVersionUID = 1L;
	private BarChartModel mixedModelEjecValidProy;
	private BarChartModel barModel;
	private DashboardModel model;
	
	//private HorizontalBarChartModel hbarModel;
	private HorizontalBarChartModel hbarModelestres;
	public void init() {
		try {

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public BarChartModel createMixedModelEjecProyVal(List<Reporteprepost>reportes) {

		mixedModelEjecValidProy = new BarChartModel();
		ChartData data = new ChartData();
		List<Reporteprepost> reporteUsuario= reportes;
		BarChartDataSet dataSet = new BarChartDataSet();
		
		List<Number> values = new ArrayList<>();
		
		for (Reporteprepost rep: reporteUsuario) {
			values.add(rep.getAsesoriaEmocional());
			values.add(rep.getAutoconciencia());
			values.add(rep.getAutomotivacion());
			values.add(rep.getControlEmocion());
			values.add(rep.getInterpersonal());
			System.out.println(rep.getAsesoriaEmocional());
		}

		dataSet.setData(values);
		dataSet.setLabel("% de análisis");
		List<String> bgColor = new ArrayList<>();
		
		

		for (Reporteprepost rep: reporteUsuario) {
			if(rep.getResAsesoriaEmocional().equals("Bajo"))
			{
				bgColor.add("rgba(255, 99, 132, 0.2)");
			}else
			{
				bgColor.add("rgb(26,255, 26)");
			}
			
			if(rep.getResAutoconciencia().equals("Bajo"))
			{
				bgColor.add("rgba(255, 99, 132, 0.2)");
			}else
			{
				bgColor.add("rgb(26,255, 26)");
			}
			
			if(rep.getResAutomotivacion().equals("Bajo"))
			{
				bgColor.add("rgba(255, 99, 132, 0.2)");
			}else
			{
				bgColor.add("rgb(26,255, 26)");
			}
			if(rep.getResControlEmocion().equals("Bajo"))
			{
				bgColor.add("rgba(255, 99, 132, 0.2)");
			}else
			{
				bgColor.add("rgb(26,255, 26)");
			}
			if(rep.getResInterpersonal().equals("Bajo"))
			{
				bgColor.add("rgba(255, 99, 132, 0.2)");
			}else
			{
				bgColor.add("rgb(26,255, 26)");
			}
		}
		dataSet.setBorderColor(bgColor);
		dataSet.setBackgroundColor(bgColor);
		LineChartDataSet dataSet2 = new LineChartDataSet();
		List<Number> values2 = new ArrayList<>();
		
		for (Reporteprepost rep: reporteUsuario) {
			values2.add(rep.getAsesoriaEmocional());
			values2.add(rep.getAutoconciencia());
			values2.add(rep.getAutomotivacion());
			values2.add(rep.getControlEmocion());
			values2.add(rep.getInterpersonal());
		}
		dataSet2.setData(values2);
		dataSet2.setLabel("% máximo");
		dataSet2.setFill(false);
		dataSet2.setBorderColor("rgb(54, 162, 235)");

		data.addChartDataSet(dataSet);
		data.addChartDataSet(dataSet2);

		List<String> labels = new ArrayList<>();
	
			labels.add("Asesoría Emocional");
			labels.add("Autoconciencia");
			labels.add("Automotivación");
			labels.add("Control Emoción");
			labels.add("Interpersonal");
			
		
		data.setLabels(labels);

		mixedModelEjecValidProy.setData(data);

		// Options
		BarChartOptions options = new BarChartOptions();
		CartesianScales cScales = new CartesianScales();
		CartesianLinearAxes linearAxes = new CartesianLinearAxes();
		CartesianLinearTicks ticks = new CartesianLinearTicks();
		ticks.setBeginAtZero(true);
		linearAxes.setTicks(ticks);

		cScales.addYAxesData(linearAxes);
		options.setScales(cScales);
		mixedModelEjecValidProy.setOptions(options);
		return mixedModelEjecValidProy;
	}
	
	
	 public HorizontalBarChartModel createHorizontalBarModel(List<Reporteprepost>reportes) {
	        hbarModelestres = new HorizontalBarChartModel();
	        ChartData data = new ChartData();
	         
	        
	        List<Reporteprepost>reporteEstres=reportes;
	      
	        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
	        hbarDataSet.setLabel("Nivel Estrés");
	        List<Number> values = new ArrayList<>();
	        for (Reporteprepost reporteprepost : reporteEstres) {
	        	
	        	 if(reporteprepost.getNivelestres().equals("Baja vulnerabilidad al estrés."))
	        	 {
	        		 values.add(reporteprepost.getPorEstres());
	        		
	        	 }
	        	
	        	 if(reporteprepost.getNivelestres().equals("Vulnerable al estrés."))
	        	 {
	        		 values.add(reporteprepost.getPorEstres());
	        		 
	        	 }
	        	
	        	 if(reporteprepost.getNivelestres().equals("Seriamente vulnerable al estrés."))
	        	 {
	        		 values.add(reporteprepost.getPorEstres());
	        		 
	        	 }
	        	 
	        	 if(reporteprepost.getNivelestres().equals("Extremadamente vulnerable al estrés."))
	        	 {
	        		 values.add(reporteprepost.getPorEstres());
	        	 }
	        	values.add(100);
	        	
			}
	        hbarDataSet.setData(values);
	        List<String> bgColor = new ArrayList<>();
	        for (Reporteprepost reporteprepost : reporteEstres) {
	        	 
	        	 if(reporteprepost.getNivelestres().equals("Baja vulnerabilidad al estrés."))
	        	 {
	 				bgColor.add("rgb(26,255, 26)");
	        	 }
	        	
	        	 if(reporteprepost.getNivelestres().equals("Vulnerable al estrés."))
	        	 {
	        		 bgColor.add("rgb(255,255, 000)");
	        	 }
	        	
	        	 if(reporteprepost.getNivelestres().equals("Seriamente vulnerable al estrés."))
	        	 {
	        		 bgColor.add("rgb(255,164, 032)");
	        	 }
	        	 
	        	 if(reporteprepost.getNivelestres().equals("Extremadamente vulnerable al estrés."))
	        	 {
	        		 bgColor.add("rgba(255, 99, 132, 0.2)");
	        	 }
	        	
	        	
	  	       
			}
	        hbarDataSet.setBackgroundColor(bgColor);
	        
	        List<String> borderColor = new ArrayList<>();
	        borderColor.add("rgb(26,255, 26)");
	
	        hbarDataSet.setBorderColor(borderColor);
	        hbarDataSet.setBorderWidth(1);
	        
	        data.addChartDataSet(hbarDataSet);
	         
	        List<String> labels = new ArrayList<>();
	      
	        for (Reporteprepost reporteprepost : reporteEstres) {
	        	 
	        	 if(reporteprepost.getNivelestres().equals("Baja vulnerabilidad al estrés."))
	        	 {
	        		  labels.add("Baja vulnerabilidad al estrés.");
	        	 }
	        	
	        	 if(reporteprepost.getNivelestres().equals("Vulnerable al estrés."))
	        	 {
	        		  labels.add("Vulnerable al estrés.");
	        	 }
	        	
	        	 if(reporteprepost.getNivelestres().equals("Seriamente vulnerable al estrés."))
	        	 {
	        		  labels.add("Seriamente vulnerable al estrés.");
	        	 }
	        	 
	        	 if(reporteprepost.getNivelestres().equals("Extremadamente vulnerable al estrés."))
	        	 {
	        		  labels.add("Extremadamente vulnerable al estrés.");
	        	 }
	        	
	        	
	  	       
			}
	        data.setLabels(labels);
	        LineChartDataSet dataSet2 = new LineChartDataSet();
	        List<Number> values3 = new ArrayList<>();
	        values3.add(100);
	        dataSet2.setData(values3);
	        dataSet2.setLabel("Line Dataset");
	        dataSet2.setFill(false);
	        dataSet2.setBorderColor("rgb(54, 162, 235)");
	        
	        hbarModelestres.setData(data);
	         
	        //Options
	        BarChartOptions options = new BarChartOptions();
	        CartesianScales cScales = new CartesianScales();
	        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
	        CartesianLinearTicks ticks = new CartesianLinearTicks();
	        ticks.setBeginAtZero(true);
	        linearAxes.setTicks(ticks);
	        cScales.addXAxesData(linearAxes);
	        options.setScales(cScales);
	         
	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Nivel de Estrés");
	        options.setTitle(title);
	         
	        hbarModelestres.setOptions(options);
	        
	        return hbarModelestres;
	    }
	
	
	 public DashboardModel dashboarTipoLider(List<Reporteprepost>reportes)
	 {
		 model= new DefaultDashboardModel();
		 DashboardColumn column1 = new DefaultDashboardColumn();
		 DashboardColumn column2 = new DefaultDashboardColumn();
		 DashboardColumn column3 = new DefaultDashboardColumn();
		 
		 for (Reporteprepost reporteprepost : reportes) {
			column1.addWidget(" Centro de Gravedad");
			column1.addWidget(reporteprepost.getCentrogravedad());
			
			column1.addWidget("Lógica emergente");
			column1.addWidget(reporteprepost.getLogicaEmergente());
			
			column1.addWidget("Lógica de Retroceso");
			column1.addWidget(reporteprepost.getLogicaRetroceso());
		}
		 
		 model.addColumn(column1);
		 model.addColumn(column2);
		 model.addColumn(column3);
		 
		 return model;
	 }

	public BarChartModel getMixedModelEjecValidProy() {
		return mixedModelEjecValidProy;
	}

	public void setMixedModelEjecValidProy(BarChartModel mixedModelEjecValidProy) {
		this.mixedModelEjecValidProy = mixedModelEjecValidProy;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public HorizontalBarChartModel getHbarModelestres() {
		return hbarModelestres;
	}

	public void setHbarModelestres(HorizontalBarChartModel hbarModelestres) {
		this.hbarModelestres = hbarModelestres;
	}

}
