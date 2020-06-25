package mia.core.model.usuario.view.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;

import mia.core.model.entities.Reporteprepost;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class BeanReportePrePostEstadistico implements Serializable {
	private static final long serialVersionUID = 1L;
	private BarChartModel mixedModelEjecValidProy;
	private BarChartModel barModel;

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

	public BarChartModel getMixedModelEjecValidProy() {
		return mixedModelEjecValidProy;
	}

	public void setMixedModelEjecValidProy(BarChartModel mixedModelEjecValidProy) {
		this.mixedModelEjecValidProy = mixedModelEjecValidProy;
	}

}
