package com.bc.chipenrich.ui.locator;

import com.bc.chipenrich.ui.CustomPlant;

public class TFFLocator extends AbstractLocator {
 private static TFFLocator INSTANCE = new TFFLocator();
 
 protected String getArabidopsisDefault() {
   return "arabidopsis/TFF-new-general-trimmed.txt"; //updated
 }
 
 protected String getBrassica() {
   return "brassica/napus-TFFs-June2016.txt"; //updated
 }
 
 /* //mod (remove soybean stuff)
 protected String getSoybeanDefault() {
  return "soybean/Soybean_TF.txt";
 }*/
 
 public static TFFLocator getInstance() {
  return INSTANCE;
 }
 
 protected String getField(CustomPlant plant) {
  return plant.getTFF();
 }
 
 protected String getType() {
  return "Transcription Factor Family";
 }
}