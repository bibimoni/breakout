import javax.swing.JFrame;

public class frame extends JFrame {

    frame() {

        this.setTitle("Brick Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new panel());
        this.setResizable(true);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);

    }
}
