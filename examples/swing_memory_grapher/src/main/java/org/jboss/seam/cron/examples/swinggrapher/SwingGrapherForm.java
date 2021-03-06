/**
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * SwingGrapherForm.java
 *
 * Created on May 25, 2009, 6:16:17 PM
 */
package org.jboss.seam.cron.examples.swinggrapher;

import java.awt.Dimension;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.jboss.logging.Logger;
import org.jboss.weld.environment.se.ShutdownManager;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

/**
 *
 * @author Pete Royle
 */
public class SwingGrapherForm extends javax.swing.JFrame
{

    private @Inject CategoryDataset catDataSet;
    private @Inject ShutdownManager shutdownManager;
    @Inject Logger log;

    /** Creates new form SwingGrapherForm */
    public SwingGrapherForm()
    {
        super("Seam Cron Swing Graph Example");
        initComponents();
        setSize(new Dimension(800, 600));

    }

    /**
     * Initialise the chart visuals.
     */
    @PostConstruct
    public void initChart()
    {
        log.info("Initializing");
        final JFreeChart chart = ChartFactory.createLineChart3D(
                "Free Memory", "Time", "Bytes", catDataSet, PlotOrientation.VERTICAL, true, true, true);
        final ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
    }

    /**
     * Main entry point of the program once the JSR-299 container has initialised.
     * @param ci The observed event.
     */
    public void main(@Observes ContainerInitialized ci)
    {
        log.info("Running");
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosed
    {//GEN-HEADEREND:event_formWindowClosed
        shutdownManager.shutdown();
    }//GEN-LAST:event_formWindowClosed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
