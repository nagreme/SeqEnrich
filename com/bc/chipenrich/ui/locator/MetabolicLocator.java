package com.bc.chipenrich.ui.locator;

import com.bc.chipenrich.ui.CustomPlant;

public class MetabolicLocator extends AbstractLocator {
 private static MetabolicLocator INSTANCE = new MetabolicLocator();
 
 protected String getArabidopsisDefault() {
   return "arabidopsis/Pathways-2016ARAB.txt"; //updated
 }
 
 protected String getBrassica() {
   return "brassica/canola_Pathway.txt"; //mod update
 }
 
 /* //mod (remove soybean stuff)
 protected String getSoybeanDefault() {
  return "soybean/Soybean_Pathway.txt";
 }*/
 
 public static MetabolicLocator getInstance() {
  return INSTANCE;
 }
 
 protected String getField(CustomPlant plant) {
  return plant.getMetabolic();
 }
 
 protected String getType() {
  return "Metabolic Pathways";
 }
}