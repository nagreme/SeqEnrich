package com.bc.chipenrich.ui.locator;

import com.bc.chipenrich.ui.CustomPlant;

public class ArrayLocator extends AbstractLocator {
 public static ArrayLocator INSTANCE = new ArrayLocator();
 
 protected String getArabidopsisDefault() {
  return "arabidopsis/ArrayAnnotationSummary.txt"; //same as the old one
 }
 
 protected String getBrassica() {
  return "brassica/ArrayAnnotationSummary.txt"; 
 }
 
 /* //mod (remove soybean stuff)
 protected String getSoybeanDefault() {
  return null;
 }*/
 
 public static ArrayLocator getInstance() {
  return INSTANCE;
 }

 protected String getField(CustomPlant plant) {
  return plant.getArray();
 }
 
 protected String getType() {
  return "Array";
 }
}
