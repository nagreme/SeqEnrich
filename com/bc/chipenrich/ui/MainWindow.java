package com.bc.chipenrich.ui;

import com.bc.chipenrich.ui.chooser.*;
import com.bc.chipenrich.ui.config.SettingsManager;
import com.bc.chipenrich.ui.locator.*;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.awt.Dimension;

import java.awt.Color;
import java.awt.Component;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class MainWindow extends JPanel  {
 private CustomPlantManager plantManager = new CustomPlantManager(this);
 private JPanel plantSelectorPanel;
 
 ChipEnrich parent; //mod
 
 private static final int PADDING = 20;
 
 public MainWindow(ChipEnrich parent) {
   this.parent = parent;
  this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //all content alignment (og: Y)
  SettingsManager.getInstance().addSettingsListener(plantManager);
  makeMenus(parent);
 }

 private void makeMenus(ChipEnrich parent) {
  JPanel topPanel = new JPanel();
  topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS)); //custom plant, then buttons and check boxes (og: X)
  topPanel.add(Box.createRigidArea(new Dimension(PADDING, 0))); 
  topPanel.add(makePlantPanel());
  topPanel.add(Box.createRigidArea(new Dimension(10, 0))); 
  makeSelectorPanel(topPanel);
  topPanel.add(Box.createRigidArea(new Dimension(PADDING, 0))); 

  this.add(Box.createRigidArea(new Dimension(0, PADDING))); 
  this.add(topPanel);
  this.add(Box.createRigidArea(new Dimension(0, 60)));

  JPanel bottomPanel = new JPanel();
  bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS)); //check boxes (og: X)
  makeBottomPanel(bottomPanel); //mod
  //makeWholechipPanel(bottomPanel); 
  //makeSingletonPanel(bottomPanel);
  this.add(bottomPanel);
  this.add(Box.createRigidArea(new Dimension(0, PADDING))); 
 }

 private JPanel makePlantPanel() {
  JPanel plantChooser = new JPanel();
  
  plantChooser.setLayout(new BoxLayout(plantChooser, BoxLayout.Y_AXIS)); //custom plant button, then radio buttons (og: Y)
  
  JLabel plantHeader = new JLabel("Select plant:");
  
  //Moved
  //JButton specify custom plant
  
  plantSelectorPanel = new JPanel();
  plantSelectorPanel.setLayout(new BoxLayout(plantSelectorPanel, BoxLayout.Y_AXIS));// radio buttons (og: Y)

  plantChooser.add(plantHeader);
  plantChooser.add(Box.createRigidArea(new Dimension(0, 10)));
  plantChooser.add(plantSelectorPanel);
  plantChooser.add(Box.createRigidArea(new Dimension(0, 70)));
  
  //Add a start button under the plant selector
  JButton startButton = new JButton("Start");
  startButton.setMinimumSize(new Dimension(46,30)); //modifying button size for a bigger start button
  startButton.setPreferredSize(new Dimension(70,40));
  startButton.setMaximumSize(new Dimension(70,40));
  //startButton.setBorderPainted(false);
  //Custom button icon
  //startButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/start-button.png")));
  startButton.addActionListener(new StartAction(parent)); //Link the action to it
  plantChooser.add(startButton);
  
  return plantChooser;
 }
 
 public void updatePlants() {
  plantSelectorPanel.removeAll();
  JRadioButton arabidopsisButton = new JRadioButton("Arabidopsis");
  arabidopsisButton.setActionCommand("arabidopsis");
  arabidopsisButton.addActionListener(PlantChooser.getInstance());
  arabidopsisButton.setSelected(true);
  
  JRadioButton brassicaButton = new JRadioButton("Brassica napus");
  brassicaButton.setActionCommand("Brassica napus");
  brassicaButton.addActionListener(PlantChooser.getInstance());
  
  //JRadioButton soybeanButton = new JRadioButton("soybean");
  //soybeanButton.setActionCommand("soybean");
  //soybeanButton.addActionListener(PlantChooser.getInstance());
  
  ButtonGroup plants = new ButtonGroup();
  plants.add(arabidopsisButton);
  plants.add(brassicaButton);  
  //plants.add(soybeanButton);

  plantSelectorPanel.add(arabidopsisButton);
  plantSelectorPanel.add(brassicaButton); 
  //plantSelectorPanel.add(soybeanButton);  

  // Add custom plants
  Vector<CustomPlant> newPlants = plantManager.getCustomPlants();
  for (CustomPlant nextPlant : newPlants) {
   String newName = nextPlant.getName();
   JRadioButton customButton = new JRadioButton(newName);
   customButton.setActionCommand(newName);
   customButton.addActionListener(PlantChooser.getInstance());
   plants.add(customButton);
   plantSelectorPanel.add(Box.createRigidArea(new Dimension(0, 5))); //Padding
   plantSelectorPanel.add(customButton);
  }
  plantSelectorPanel.revalidate();
 }

 private void makeSelectorPanel(JPanel menu) {
  JPanel selector = new JPanel();
  selector.setLayout(new BoxLayout(selector, BoxLayout.Y_AXIS)); //custom plant + radio buttons, then buttons (og: Y)

  //JButton specify custom plant
  JPanel customPlant = new JPanel();
  customPlant.setLayout(new BoxLayout(customPlant, BoxLayout.X_AXIS)); 
  JButton newPlant = new JButton("Create custom plant");
  newPlant.setPreferredSize(new Dimension(180,40)); //modifying button size
  newPlant.setMaximumSize(new Dimension(180,40));
  newPlant.addActionListener(new NewPlantAction(this, plantManager));
  customPlant.add(newPlant);
  customPlant.add(Box.createRigidArea(new Dimension(150, 0)));
  
  //GOs
  JPanel GOs = new JPanel();
  GOs.setLayout(new BoxLayout(GOs, BoxLayout.X_AXIS)); //button then associated text (og: X)
  JLabel GOLabel = new JLabel("Using Default GOs");
  JButton GOButton = new JButton("Use Custom GO Files");
  GOButton.setPreferredSize(new Dimension(165,35)); //modifying button size
  GOButton.setMaximumSize(new Dimension(165,35));
  GOButton.addActionListener(GOAnnotationLocator.getInstance());
  GOAnnotationLocator.getInstance().setLabel(GOLabel);
  GOs.add(GOButton);
  GOs.add(Box.createRigidArea(new Dimension(5, 0)));
  GOs.add(GOLabel);
  //Motif
  JPanel motifs = new JPanel();
  motifs.setLayout(new BoxLayout(motifs, BoxLayout.X_AXIS)); //button then associated text (og: X)
  JLabel motifLabel = new JLabel("Using Default Motifs");
  JButton motifButton = new JButton("Use Custom Motif Files");
  motifButton.setPreferredSize(new Dimension(180,35)); //modifying button size
  motifButton.setMaximumSize(new Dimension(180,35));
  motifButton.addActionListener(MotifFileLocator.getInstance());
  MotifFileLocator.getInstance().setLabel(motifLabel);
  motifs.add(motifButton);
  motifs.add(Box.createRigidArea(new Dimension(5, 0)));
  motifs.add(motifLabel);
  //BindingSite
  JPanel bindingSite = new JPanel();
  bindingSite.setLayout(new BoxLayout(bindingSite, BoxLayout.X_AXIS)); //button then associated text (og: X)
  JLabel bindingSiteLabel = new JLabel("Using Default Binding Site");
  JButton bindingSiteButton = new JButton("Use Custom Binding Site File");
  bindingSiteButton.setPreferredSize(new Dimension(220,35)); //modifying button size
  bindingSiteButton.setMaximumSize(new Dimension(220,35));
  bindingSiteButton.addActionListener(BindingSiteLocator.getInstance());
  BindingSiteLocator.getInstance().setLabel(bindingSiteLabel);
  bindingSite.add(bindingSiteButton);
  bindingSite.add(Box.createRigidArea(new Dimension(5, 0)));
  bindingSite.add(bindingSiteLabel);
  //Metabolic
  JPanel metabolic = new JPanel();
  metabolic.setLayout(new BoxLayout(metabolic, BoxLayout.X_AXIS)); //button then associated text (og: X)
  JLabel metabolicLabel = new JLabel("Using Default Metabolic");
  JButton metabolicButton = new JButton("Use Custom Metabolic File");
  metabolicButton.setPreferredSize(new Dimension(210,35)); //modifying button size
  metabolicButton.setMaximumSize(new Dimension(210,35));
  metabolicButton.addActionListener(MetabolicLocator.getInstance());
  MetabolicLocator.getInstance().setLabel(metabolicLabel);
  metabolic.add(metabolicButton);
  metabolic.add(Box.createRigidArea(new Dimension(5, 0)));
  metabolic.add(metabolicLabel);
  //TFF
  JPanel tff = new JPanel();
  tff.setLayout(new BoxLayout(tff, BoxLayout.X_AXIS)); //button then associated text (og: X)
  JLabel tffLabel = new JLabel("Using Default TFF");
  JButton tffButton = new JButton("Use Custom TFF File");
  tffButton.setPreferredSize(new Dimension(170,35)); //modifying button size
  tffButton.setMaximumSize(new Dimension(170,35));
  tffButton.addActionListener(TFFLocator.getInstance());
  TFFLocator.getInstance().setLabel(tffLabel);
  tff.add(tffButton);
  tff.add(Box.createRigidArea(new Dimension(5, 0)));
  tff.add(tffLabel);
  //Whole Chip
  JPanel wholechip = new JPanel();
  wholechip.setLayout(new BoxLayout(wholechip, BoxLayout.X_AXIS)); //button then associated text (og: X)
  JLabel wholechipLabel = new JLabel("Using Default Whole Chip");
  JButton wholechipButton = new JButton("Use Custom Whole Chip");
  wholechipButton.setPreferredSize(new Dimension(190,35)); //modifying button size
  wholechipButton.setMaximumSize(new Dimension(190,35));
  wholechipButton.addActionListener(WholeChipLocator.getInstance());
  WholeChipLocator.getInstance().setLabel(wholechipLabel);
  wholechip.add(wholechipButton);
  wholechip.add(Box.createRigidArea(new Dimension(5, 0)));
  wholechip.add(wholechipLabel);
  //Singleton Chip
  JPanel singleton = new JPanel();
  singleton.setLayout(new BoxLayout(singleton, BoxLayout.X_AXIS)); //button then associated text (og: X)
  JLabel singletonLabel = new JLabel("Using Default Singleton Chip");
  JButton singletonButton = new JButton("Use Custom Singleton Chip");
  singletonButton.setPreferredSize(new Dimension(210,35)); //modifying button size
  singletonButton.setMaximumSize(new Dimension(210,35));
  singletonButton.addActionListener(SingletonChipLocator.getInstance());
  SingletonChipLocator.getInstance().setLabel(singletonLabel);
  singleton.add(singletonButton);
  singleton.add(Box.createRigidArea(new Dimension(5, 0)));
  singleton.add(singletonLabel);
  
  selector.add(customPlant);
  selector.add(Box.createRigidArea(new Dimension(0, 30)));
  selector.add(GOs);
  selector.add(Box.createRigidArea(new Dimension(0, 5)));
  selector.add(motifs);
  selector.add(Box.createRigidArea(new Dimension(0, 5)));
  selector.add(bindingSite);
  selector.add(Box.createRigidArea(new Dimension(0, 5)));
  selector.add(metabolic);
  selector.add(Box.createRigidArea(new Dimension(0, 5)));
  selector.add(tff);
  selector.add(Box.createRigidArea(new Dimension(0, 5)));
  selector.add(wholechip);
  selector.add(Box.createRigidArea(new Dimension(0, 5)));
  selector.add(singleton);
  selector.add(Box.createRigidArea(new Dimension(0, 5)));
  
  menu.add(Box.createRigidArea(new Dimension(80, 0)));
  menu.add(selector);
 }

 
 //mod (new)
 private void makeBottomPanel(JPanel menu) 
 { 
   //Add create and use custom AGI-Motif Table buttons in the right
   //half of hte bottom panel
   JPanel options = new JPanel();
   options.setLayout(new BoxLayout(options, BoxLayout.X_AXIS));
   
   JButton makeTable = new JButton("Create a custom AGI-Motif Table...");
   makeTable.setPreferredSize(new Dimension(270,35)); //modifying button size
  makeTable.setMaximumSize(new Dimension(270,35));
   makeTable.addActionListener(new CustomTableMaker(parent));
   
   JButton loadTable = new JButton("Use custom AGI-Motif Table...");
   loadTable.setPreferredSize(new Dimension(230,35)); //modifying button size
   loadTable.setMaximumSize(new Dimension(230,35));
   loadTable.addActionListener(new AbstractAction() {
     public void actionPerformed(ActionEvent e) {
       JFileChooser chooser = new JFileChooser();
       chooser.setCurrentDirectory(new java.io.File("."));
       chooser.setDialogTitle("Select AGI-Motif Table...");
       chooser.setMultiSelectionEnabled(false);
       if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
         AGIMotifTableLocator.getInstance().setExternalFile(chooser.getSelectedFile());
       }
     }
   });
   
   options.add(makeTable);
   options.add(Box.createRigidArea(new Dimension(10, 0)));
   options.add(loadTable);

   menu.add(options);
 }
 
 /*
 private void makeWholechipPanel(JPanel menu) {
  final JCheckBox wholechipAnalysis = new JCheckBox("Include Whole Chip Analysis");

  JCheckBox wholechipArray = new JCheckBox("Include Whole Chip Array");
  wholechipArray.addItemListener(new ItemListener() {
   public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.DESELECTED) {
     RunnerChooser.getInstance().setATH1Array(false);
    }
    if (e.getStateChange() == ItemEvent.SELECTED) {
     RunnerChooser.getInstance().setATH1Array(true);
    }
   }
  });
  wholechipArray.setSelected(false);

  final JCheckBox wholechipGO = new JCheckBox("Include Whole Chip GO");
  wholechipGO.addItemListener(new ItemListener() {
   public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.DESELECTED) {
     RunnerChooser.getInstance().setATH1GO(false);
     wholechipAnalysis.setSelected(false);
    }
    if (e.getStateChange() == ItemEvent.SELECTED) {
     RunnerChooser.getInstance().setATH1GO(true);
    }
   }
  });
  wholechipGO.setSelected(false);

  JCheckBox wholechipMetabolic = new JCheckBox("Include Whole Chip Metabolic");
  wholechipMetabolic.addItemListener(new ItemListener() {
   public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.DESELECTED) {
     RunnerChooser.getInstance().setATH1Metabolic(false);
    }
    if (e.getStateChange() == ItemEvent.SELECTED) {
     RunnerChooser.getInstance().setATH1Metabolic(true);
    }
   }
  });
  wholechipMetabolic.setSelected(false);

  JCheckBox wholechipTFF = new JCheckBox("Include Whole Chip TFF");
  wholechipTFF.addItemListener(new ItemListener() {
   public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.DESELECTED) {
     RunnerChooser.getInstance().setATH1TFF(false);
    }
    if (e.getStateChange() == ItemEvent.SELECTED) {
     RunnerChooser.getInstance().setATH1TFF(true);
    }
   }
  });
  wholechipTFF.setSelected(false);

  final JCheckBox wholechipMotif = new JCheckBox("Include Whole Chip Motif");
  wholechipMotif.addItemListener(new ItemListener() {
   public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.DESELECTED) {
     RunnerChooser.getInstance().setATH1Motif(false);
     wholechipAnalysis.setSelected(false);
    }
    if (e.getStateChange() == ItemEvent.SELECTED) {
     RunnerChooser.getInstance().setATH1Motif(true);
    }
   }
  });
  wholechipMotif.setSelected(false);

  wholechipAnalysis.addItemListener(new ItemListener() {
   public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.DESELECTED) {
     RunnerChooser.getInstance().setATH1Analysis(false);
    }
    if (e.getStateChange() == ItemEvent.SELECTED) {
     RunnerChooser.getInstance().setATH1Analysis(true);
     wholechipMotif.setSelected(true);
        wholechipGO.setSelected(true);
       }
   }
     });
     wholechipAnalysis.setSelected(false);

     JPanel wholechip = new JPanel();
     wholechip.setLayout(new BoxLayout(wholechip, BoxLayout.Y_AXIS));
     wholechip.add(wholechipArray);
  wholechip.add(wholechipGO);
  wholechip.add(wholechipMetabolic);
  wholechip.add(wholechipTFF);
  wholechip.add(wholechipMotif);
  wholechip.add(wholechipAnalysis);
     menu.add(wholechip);
 }*/

 /*
 private void makeSingletonPanel(JPanel menu) {
     final JCheckBox SingletonAnalysis = new JCheckBox("Include Singleton Analysis");

     JCheckBox SingletonArray = new JCheckBox("Include Singleton Array");
     SingletonArray.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
       if (e.getStateChange() == ItemEvent.DESELECTED) {
        RunnerChooser.getInstance().setSingletonArray(true); //mod (og:false)
       }
       if (e.getStateChange() == ItemEvent.SELECTED) {
        RunnerChooser.getInstance().setSingletonArray(true);
       }
      }
     });
     SingletonArray.setSelected(true);

     final JCheckBox SingletonGO = new JCheckBox("Include Singleton GO");
     SingletonGO.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
       if (e.getStateChange() == ItemEvent.DESELECTED) {
        RunnerChooser.getInstance().setSingletonGO(true); //mod
        SingletonAnalysis.setSelected(false); //linked Button
       }
       if (e.getStateChange() == ItemEvent.SELECTED) {
        RunnerChooser.getInstance().setSingletonGO(true);
       }
      }
     });
     SingletonGO.setSelected(true);

     JCheckBox SingletonMetabolic = new JCheckBox("Include Singleton Metabolic");
     SingletonMetabolic.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
       if (e.getStateChange() == ItemEvent.DESELECTED) {
        RunnerChooser.getInstance().setSingletonMetabolic(true); //mod
       }
       if (e.getStateChange() == ItemEvent.SELECTED) {
        RunnerChooser.getInstance().setSingletonMetabolic(true);
       }
      }
     });
     SingletonMetabolic.setSelected(true);

     JCheckBox SingletonTFF = new JCheckBox("Include Singleton TFF");
     SingletonTFF.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
       if (e.getStateChange() == ItemEvent.DESELECTED) {
        RunnerChooser.getInstance().setSingletonTFF(true); //mod
       }
       if (e.getStateChange() == ItemEvent.SELECTED) {
        RunnerChooser.getInstance().setSingletonTFF(true);
       }
      }
     });
     SingletonTFF.setSelected(true);

     final JCheckBox SingletonMotif = new JCheckBox("Include Singleton Motif");
     SingletonMotif.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
       if (e.getStateChange() == ItemEvent.DESELECTED) {
        RunnerChooser.getInstance().setSingletonMotif(true); //mod
        SingletonAnalysis.setSelected(false); //linked button
       }
       if (e.getStateChange() == ItemEvent.SELECTED) {
        RunnerChooser.getInstance().setSingletonMotif(true);
       }
      }
     });
     SingletonMotif.setSelected(true);

     //Include Singleton Analysis
     SingletonAnalysis.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
       if (e.getStateChange() == ItemEvent.DESELECTED) {
        RunnerChooser.getInstance().setSingletonAnalysis(true); //mod
       }
       if (e.getStateChange() == ItemEvent.SELECTED) {
        RunnerChooser.getInstance().setSingletonAnalysis(true);
        SingletonMotif.setSelected(true);
        SingletonGO.setSelected(true);
       }
      }
     });
     SingletonAnalysis.setSelected(true);

     //og
     JPanel singletons = new JPanel();
     singletons.setLayout(new BoxLayout(singletons, BoxLayout.X_AXIS)); //check boxes (og: Y)
     singletons.add(SingletonArray);
     singletons.add(SingletonGO);
     singletons.add(SingletonMetabolic);
     singletons.add(SingletonTFF);
     singletons.add(SingletonMotif);
     singletons.add(SingletonAnalysis);
     menu.add(singletons);
     
     
     //mod test
     JPanel singletons1 = new JPanel();
     JPanel singletons2 = new JPanel();
     
     singletons1.setLayout(new BoxLayout(singletons1, BoxLayout.Y_AXIS)); //check boxes (og: Y)
     singletons2.setLayout(new BoxLayout(singletons2, BoxLayout.Y_AXIS)); //check boxes (og: Y)
     
     singletons1.add(SingletonArray);
     singletons2.add(SingletonGO);
     singletons1.add(SingletonMetabolic);
     singletons1.add(SingletonTFF);
     singletons2.add(SingletonMotif);
     singletons2.add(SingletonAnalysis);
     
     JPanel singletons = new JPanel();
     singletons.setLayout(new BoxLayout(singletons, BoxLayout.X_AXIS));
     
     singletons.add(singletons1);
     singletons.add(singletons2);
     
     menu.add(singletons);
     
 }*/
 
}










