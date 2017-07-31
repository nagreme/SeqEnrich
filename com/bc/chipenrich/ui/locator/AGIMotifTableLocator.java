package com.bc.chipenrich.ui.locator;

import com.bc.chipenrich.ui.CustomPlant;

public class AGIMotifTableLocator extends AbstractLocator {
 
 public static AGIMotifTableLocator INSTANCE = new AGIMotifTableLocator();
 
 protected String getArabidopsisDefault() {
   return "arabidopsis/motif-table-sortedJune2016trimmed.txt"; //updated 
 }
 
 protected String getBrassica() {
   return "brassica/napus-june2016-motiftable.txt"; //mod update
 }
 
 /* //mod (remove soybean stuff)
 protected String getSoybeanDefault() {
  return "soybean/Soy_AGI_Motif_Table.txt";
 }*/

 public static AGIMotifTableLocator getInstance() {
  return INSTANCE;
 }
 
 protected String getField(CustomPlant plant) {
  return plant.getTable();
 }
 
 protected String getType() {
  return "AGI Motif Table";
 }
}
