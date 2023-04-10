package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {

    protected ControlPanel controlPanel;
    protected  View view;
    protected Model model;
    protected AppFactory factory;
    private SafeFrame frame;

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        this.model = this.factory.makeModel();
        this.view = this.factory.makeView(model);
        this.controlPanel = new ControlPanel();

        setLayout((new GridLayout(1,2)));
        this.add(this.controlPanel);
        this.add(this.view);

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        frame.setBackground(Color.BLACK);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(this.factory.getTitle());
        frame.setSize(1000, 500);
        cp.add(this);
    }

    public void display() {
        frame.setVisible(true);
    }
    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    private void toSave(){
        if(model.unsavedChanges){
            boolean answer = Utilities.confirm("Do you want to save your changes?");
            if(answer){
                if(model.fileName != null){
                    Utilities.save(model, false);
                }
                else{
                    Utilities.save(model, true);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch(cmmd){
                case "Save": {
                    if(model.getFileName() != null) {
                        Utilities.save(model, false);
                    }
                    else {
                        Utilities.save(model, true);
                    }
                    break;
                }

                case "SaveAs": {
                    Utilities.save(model, true);
                    break;
                }

                case "Open": {
                    toSave();
                    String fName = Utilities.getFileName((String) null, true);
                    ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                    model = (Model) is.readObject();
                    view.setModel(model);
                    is.close();
                    model.setFileName(fName);
                    model.setUnsavedChanges(false);
                    break;
                }

                case "New": {
                    toSave();
                    model = factory.makeModel();
                    view.setModel(model);
                    model.setFileName(null);
                    model.setUnsavedChanges(false);
                    break;
                }

                case "Quit": {
                    toSave();
                    System.exit(1);
                    break;
                }

                case "About": {
                    Utilities.inform(factory.about());
                    break;
                }

                case "Help": {
                    Utilities.inform(factory.getHelp());
                    break;
                }

                default: {
                    factory.makeEditCommand(model, cmmd, this).execute();
                    break;
                }
            }
        } catch (Exception ex) {
            handleException(ex); // all error handling done here!
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    public void addControl(JComponent c){
        controlPanel.add(c);
    }

    public static class ControlPanel extends JPanel {
        public ControlPanel() {
//            setBackground(Color.PINK);
        }
    }
}
