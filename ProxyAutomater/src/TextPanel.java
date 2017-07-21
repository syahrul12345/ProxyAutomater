import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel{
	private JTextArea textArea;
	
	public TextPanel() {
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(300,300));
		add(new JScrollPane(textArea),BorderLayout.CENTER);
	}

	public void appendText(String text) {
		textArea.append(text);
		
	}
	
}
