package com.caresys;


import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class LineSport extends Activity {
	double[] X = new double[500];
	double[] Y = new double[500];
	double[] Z = new double[500];
	double ymax = X[0];
	double Xmin = 0;
	int I;
	int i;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//取的intent中的bundle物件
		Bundle line =this.getIntent().getExtras();
		I = line.getInt("I");
		i = line.getInt("i");
		for(int k=0;k<i;k++) {
			X[k] = line.getDouble(String.valueOf(k));
			Y[k] = line.getDouble(String.valueOf(k+500));
			Z[k] = line.getDouble(String.valueOf(k+1000));
		}
		String[] titles = new String[] {"X加速度","Y加速度","Z加速度",""}; // 定義折線的名稱
		List<double[]> x = new ArrayList<double[]>(); // 點的x坐標
		List<double[]> y = new ArrayList<double[]>(); // 點的y坐標
		// 數值X,Y坐標值輸入
		double[] M = {0};
		double[] zero={0};
		if(I<500) {
			M = new double[I];
			zero=new double[I];
			for(int j=0;j<I;j++) {
				M[j]=j*0.1;
				zero[j]=0;
				if (X[j]>ymax)
					ymax=X[j];
               	if (Y[j]>ymax)
               		ymax=Y[j];
               	if (Z[j]>ymax)
               		ymax=Z[j];
            }
		}
		else if(I>499) {
			Xmin=0.1*I-50;
       		M=new double[500];
       		zero=new double[500];
       		for(int j=0;j<500;j++) {
       			M[j]=j*0.1+(I-500)*0.1;
       			zero[j]=0;
           		if (X[j]>ymax)
           			ymax=X[j];
           		if (Y[j]>ymax)
           			ymax=Y[j];
           		if (Z[j]>ymax)
           			ymax=Z[j];
           	}
       	}
		x.add(M);
		x.add(M);
		x.add(M);
		x.add(M);
		y.add(X);
		y.add(Y);
		y.add(Z);
		y.add(zero);
		XYMultipleSeriesDataset dataset = buildDatset(titles, x, y); // 儲存座標值
		int[] colors = new int[] { Color.BLUE,Color.GREEN,Color.RED,Color.BLACK };// 折線的顏色
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE,PointStyle.DIAMOND,PointStyle.TRIANGLE,PointStyle.POINT}; // 折線點的形狀
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles, true);
		setChartSettings(renderer, "折線圖", "時間", "加速度值", Xmin, 0.1*I, 0, 1.1*ymax, Color.BLACK);// 定義折線圖
		View chart = ChartFactory.getLineChartView(this, dataset, renderer);
		setContentView(chart);
	}
	// 定義折線圖名稱
	protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor) {
		renderer.setChartTitle(title); // 折線圖名稱
		renderer.setChartTitleTextSize(24); // 折線圖名稱字形大小
		renderer.setXTitle(xTitle); // X軸名稱
		renderer.setYTitle(yTitle); // Y軸名稱
		renderer.setXAxisMin(xMin); // X軸顯示最小值
		renderer.setXAxisMax(xMax); // X軸顯示最大值
		renderer.setXLabelsColor(Color.BLACK); // X軸線顏色
		renderer.setYAxisMin(yMin); // Y軸顯示最小值
		renderer.setYAxisMax(yMax); // Y軸顯示最大值
		renderer.setAxesColor(axesColor); // 設定坐標軸顏色
		renderer.setYLabelsColor(0, Color.BLACK); // Y軸線顏色
		renderer.setLabelsColor(Color.BLACK); // 設定標籤顏色
		renderer.setMarginsColor(Color.WHITE); // 設定背景顏色
		renderer.setShowGrid(true); // 設定格線
	}
	// 定義折線圖的格式
	private XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles, boolean fill) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			r.setFillPoints(fill);
			renderer.addSeriesRenderer(r); //將座標變成線加入圖中顯示
		}
		return renderer;
	}
	// 資料處理
	private XYMultipleSeriesDataset buildDatset(String[] titles, List<double[]> xValues,List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length; // 折線數量
		for (int i = 0; i < length; i++) {
			// XYseries對象,用於提供繪製的點集合的資料
			XYSeries series = new XYSeries(titles[i]); // 依據每條線的名稱新增
			double[] xV = xValues.get(i); // 獲取第i條線的資料
			double[] yV = yValues.get(i);
			int seriesLength = xV.length; // 有幾個點
			for (int k = 0; k < seriesLength; k++) {// 每條線裡有幾個點
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
		return dataset;
	}
}
