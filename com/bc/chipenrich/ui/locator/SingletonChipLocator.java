package com.bc.chipenrich.ui.locator;

import com.bc.chipenrich.ui.CustomPlant;

public class SingletonChipLocator extends AbstractLocator {
 private static SingletonChipLocator INSTANCE = new SingletonChipLocator();
 
 protected String getArabidopsisDefault() {
   return "arabidopsis/SINGLETONS.txt"; //updated 
 }
 
 protected String getBrassica() {
   return "brassica/canola_singletons.txt"; //mod update
 }
 
 /* //mod (remove soybean stuff)
 protected String getSoybeanDefault() {
  return "soybean/Soybean_TranscriptOnArray_Singleton.txt";
 }*/
 
 public static SingletonChipLocator getInstance() {
  return INSTANCE;
 }
 
 protected String getField(CustomPlant plant) {
  return plant.getSingleton();
 }
 
 protected String getType() {
  return "Singleton Chip";
 }
}