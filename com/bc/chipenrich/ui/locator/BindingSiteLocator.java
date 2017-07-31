package com.bc.chipenrich.ui.locator;

import com.bc.chipenrich.ui.CustomPlant;

public class BindingSiteLocator extends AbstractLocator {
 private static BindingSiteLocator INSTANCE = new BindingSiteLocator();
 
 protected String getArabidopsisDefault() {
   return "arabidopsis/Bindingsites-new-general-trimmed.txt"; //updated
 }

 protected String getBrassica() {
   return "brassica/Bindingsites-napus-June2016.txt"; //mod update
 }
 
 /* //mod (remove soybean stuff)
 protected String getSoybeanDefault() {
  return getArabidopsisDefault();
 }*/
 
 public static BindingSiteLocator getInstance() {
  return INSTANCE;
 }
 
 protected String getField(CustomPlant plant) {
  return plant.getBindingSite();
 }
 
 protected String getType() {
  return "Binding Site";
 }
}