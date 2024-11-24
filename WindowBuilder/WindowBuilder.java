import javax.swing.*;
import java.awt.Color;
import helper_classes.*;

public class WindowBuilder {
  public static void main(String[] args) {

     JFrame frame = new JFrame("My Awesome Window");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(896, 484);
     JPanel panel = new JPanel();
     panel.setLayout(null);
     panel.setBackground(Color.decode("#1e1e1e"));

     grid.setBounds(433, 84, 350, 350);

     grid.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
     OnFocusEventHelper.setOnFocusText(grid, "Your long Input!", Color.decode("#353535"),   Color.decode("#656565"));
     panel.add(grid);

     colhints.setBounds(434, 11, 350, 70);

     colhints.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
     OnFocusEventHelper.setOnFocusText(colhints, "Your long Input!", Color.decode("#353535"),   Color.decode("#656565"));
     panel.add(colhints);

     JTextArea rowhints = new JTextArea("");
     rowhints.setBounds(360, 84, 70, 350);

     rowhints.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
     OnFocusEventHelper.setOnFocusText(rowhints, "Your long Input!", Color.decode("#353535"),   Color.decode("#656565"));
     panel.add(rowhints);

     JButton savebutton = new JButton("save");
     savebutton.setBounds(150, 60, 150, 50);
     savebutton.setBackground(Color.decode("#2e2e2e"));
     savebutton.setForeground(Color.decode("#D9D9D9"));
     savebutton.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 14));
     savebutton.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
     savebutton.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(savebutton, Color.decode("#232323"), Color.decode("#2e2e2e"));
     panel.add(savebutton);

     JButton loadbutton = new JButton("load");
     loadbutton.setBounds(150, 120, 150, 50);
     loadbutton.setBackground(Color.decode("#2e2e2e"));
     loadbutton.setForeground(Color.decode("#D9D9D9"));
     loadbutton.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 14));
     loadbutton.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
     loadbutton.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(loadbutton, Color.decode("#232323"), Color.decode("#2e2e2e"));
     panel.add(loadbutton);

     JButton solvebutton = new JButton("solution");
     solvebutton.setBounds(150, 180, 150, 50);
     solvebutton.setBackground(Color.decode("#2e2e2e"));
     solvebutton.setForeground(Color.decode("#D9D9D9"));
     solvebutton.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 14));
     solvebutton.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
     solvebutton.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(solvebutton, Color.decode("#232323"), Color.decode("#2e2e2e"));
     panel.add(solvebutton);

     JButton newbutton = new JButton("new");
     newbutton.setBounds(150, 240, 150, 50);
     newbutton.setBackground(Color.decode("#2e2e2e"));
     newbutton.setForeground(Color.decode("#D9D9D9"));
     newbutton.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 14));
     newbutton.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
     newbutton.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(newbutton, Color.decode("#232323"), Color.decode("#2e2e2e"));
     panel.add(newbutton);

     JTextField sizeinput = new JTextField("");
     sizeinput.setBounds(150, 300, 150, 21);
     sizeinput.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 14));
     sizeinput.setBackground(Color.decode("#B2B2B2"));
     sizeinput.setForeground(Color.decode("#656565"));
     sizeinput.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
     OnFocusEventHelper.setOnFocusText(sizeinput, "size", Color.decode("#353535"),   Color.decode("#656565"));
     panel.add(sizeinput);

     frame.add(panel);
     frame.setVisible(true);

  }
}