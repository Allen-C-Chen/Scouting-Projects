package scouter;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
//used to autoscroll the output textArea
public class OutputDocListener implements DocumentListener {
	private JTextArea output;
	private JScrollBar vertical;
	public OutputDocListener(JTextArea output,JScrollBar vertical){
		this.output=output;
		this.vertical=vertical;
	}
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		

	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		vertical.setValue(vertical.getMaximum());

	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}

}
