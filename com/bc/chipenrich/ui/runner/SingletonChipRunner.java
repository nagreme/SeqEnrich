/**
 * 
 */
package com.bc.chipenrich.ui.runner;

import java.io.File;

import javax.swing.JLabel;

import com.bc.chipenrich.service.ChipEnrichService;
import com.bc.chipenrich.ui.locator.SingletonChipLocator;

/**
 * @author Jeremy Koch
 */
public class SingletonChipRunner extends AbstractRunner {

   public SingletonChipRunner(JLabel status, ChipEnrichService ces, File[] queryFiles,
         String baseOutputDir, boolean runGO, boolean runArray, boolean runTFF, boolean runMetabolics) {
      super(status, ces, queryFiles, "output", baseOutputDir, //og "singletons"
        ces.processBackgroundChip(SingletonChipLocator.getInstance().getInputStream()), true,
        runGO, runArray, runTFF, runMetabolics);
   }

   //"string" is root in abstract super class
   //baseOutputDir is same in abstract super class
   
   /* (non-Javadoc)
    * @see com.bc.chipenrich.ui.runner.AbstractRunner#getName()
    */
   @Override
   protected String getRunnerName() {
      return "Singleton";
   }

}
