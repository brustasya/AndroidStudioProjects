package com.example.task19;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PieChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    private static final int TEXT_SIZE = 40;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = buildIntent();
        startActivity(intent);
    }

    public Intent buildIntent() {
        int[] values = new int[]{3, 12, 20, 65};
        String[] bars = new String[]{"Белки", "Жиры", "Углеводы", "Прочее"};
        int[] colors = new int[]{Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE};
        CategorySeries series = new CategorySeries("Pie Chart");
        DefaultRenderer dr = new DefaultRenderer();
        for (int v = 0; v < 4; v++) {
            series.add(bars[v], values[v]);
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(colors[v]);
            dr.addSeriesRenderer(r);
        }
        dr.setZoomButtonsVisible(true);
        dr.setZoomEnabled(true);
        dr.setChartTitleTextSize(20);
        dr.setLegendTextSize(TEXT_SIZE);
        dr.setChartTitleTextSize(20);
        dr.setZoomButtonsVisible(false);
        dr.setLabelsTextSize(TEXT_SIZE);
        dr.setLegendTextSize(TEXT_SIZE);
        dr.setLabelsColor(Color.BLACK);
        return ChartFactory.getPieChartIntent(this, series, dr, "Fruit Salad");
    }
}
