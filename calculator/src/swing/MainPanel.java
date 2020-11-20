package swing;


import javax.swing.JPanel;

public class MainPanel extends JPanel{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MainPanel() {
        
        setVisible(true);
        add(new TopPanel());
        add(new MiddlePanel());
        add(new BottomPanel());

    }
    
    

}
