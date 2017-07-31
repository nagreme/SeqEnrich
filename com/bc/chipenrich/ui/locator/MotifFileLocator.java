package com.bc.chipenrich.ui.locator;

import com.bc.chipenrich.ui.CustomPlant;

public class MotifFileLocator extends AbstractLocator {
 private static MotifFileLocator INSTANCE = new MotifFileLocator();
 
 protected String getArabidopsisDefault() {
   return "arabidopsis/Motifs-new-general-trimmed.txt"; //updated
 }
 
 protected String getBrassica() {
  return "brassica/Motifs-new-June2016.txt"; //updated
 }
 
 /* //mod (remove soybean stuff)
 protected String getSoybeanDefault() {
  return "soybean/Soybean_cis-Element.txt";
 }*/
 
 public static MotifFileLocator getInstance() {
  return INSTANCE;
 }
 
 protected String getField(CustomPlant plant) {
  return plant.getMotif();
 }
 
 protected String getType() {
  return "Motif";
 }
}
