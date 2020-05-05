/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author User
 */
public class AddRemoveGlassPane {
    
    private NewJFrame parent0;
    private DialogSettings parent1;
    //private JPanel parent2;
    private GlassPane2 glassPane;
    
    /*
    parent0 - основное рабочее окно
    parent1 - окно которое отображается поверх основного рабочего
    
    
    public AddRemoveGlassPane (NewJFrame parent0, DialogSettings parent1) {
        
        this.parent0 = parent0; //переменной parent0 присваиваем то, что было передано в параметрах
        this.parent1 = parent1; //переменной parent1 присваиваем то, что было передано в параметрах
        glassPane = new GlassPane2();//создаем объект с именем glassPane
        glassPane.add(parent1); //добавляем к нему то, что было передано в параметрах
        parent0.setGlassPane(glassPane); //Устанавливаем свойства glassPane
        parent0.add(glassPane, 0); // дабавляем glassPane к родительскому контейнеру на первое место
        glassPane.setVisible(true); //делаем glassPane видимой
        parent0.validate();
        parent0.repaint();        
    }
    */
    public void AddGlassPane (NewJFrame parent0, DialogSettings parent1) {
        
        this.parent0 = parent0; //переменной parent0 присваиваем то, что было передано в параметрах
        this.parent1 = parent1; //переменной parent1 присваиваем то, что было передано в параметрах
        glassPane = new GlassPane2();//создаем объект с именем glassPane
        glassPane.add(parent1); //добавляем к нему то, что было передано в параметрах
        parent0.setGlassPane(glassPane); //Устанавливаем свойства glassPane
        parent0.add(glassPane, 0); // дабавляем glassPane к родительскому контейнеру на первое место
        glassPane.setVisible(true); //делаем glassPane видимой
        parent0.validate();
        parent0.repaint();        
    }
    
    public void RemoveGlassPane (NewJFrame parent0) {
        parent0.remove(glassPane);
        parent0.validate();
        parent0.repaint();
        
    }
    
    
}
