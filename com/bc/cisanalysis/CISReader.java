package com.bc.cisanalysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.Set;
import java.util.TreeSet;

public class CISReader {
 private BufferedReader CISreader;
 private File patternFile;
 private PrintWriter CISwriter;
 private Set<String> cis;
 
 private static final double P_VAL_THRESHOLD = 0.001;
 
 public CISReader(File patternFile, PrintWriter writer) {
  this.patternFile = patternFile;
  CISwriter = writer;
  calculateSignificantMotif();
 }

 private void calculateSignificantMotif() {
  cis = new TreeSet<String>();
  if (!patternFile.exists()) {
   System.out.println("No motif file at " + patternFile.getName());
   return;
  }
  String pattern = patternFile.getName();
  
  //mod to allow for .csv files in addition to .txt files
  if (pattern.lastIndexOf(".txt.processed.txt") != -1)
  {
    pattern = pattern.substring(0, pattern.lastIndexOf(".txt.processed.txt"));
  }
  else if (pattern.lastIndexOf(".csv.processed.txt") != -1)
  {
    pattern = pattern.substring(0, pattern.lastIndexOf(".csv.processed.txt"));
  }
  else
  {
    System.out.println("Invalid text file format.");
  }
      
  InputStream is;
  try  {
   is = new FileInputStream(patternFile);
  } catch (Exception e) {
   return;
  }
  
  CISreader = new BufferedReader(new InputStreamReader(is));
  String nextLine = "";
  try {
   nextLine = CISreader.readLine();
  } catch (Exception e) {
   return;
  }
  while (nextLine != null) {
   String motifLine[] = nextLine.split("\t");
   double pval = Double.parseDouble(motifLine[5].trim());
   if (pval < P_VAL_THRESHOLD) //mod p-val
   {
    String nextElement = motifLine[0].trim();
    if (CISwriter != null) {
     CISwriter.println(pattern
      + '\t' + nextElement
      + "\t" + String.valueOf(Math.log10(pval)));
    }
    cis.add(nextElement);
   }
   try {
    nextLine = CISreader.readLine();
   } catch (Exception e) {
    return;
   }
  }
  return;
 }
 
 public Set<String> getSignificantMotifs() {
  return cis;
 }
}
