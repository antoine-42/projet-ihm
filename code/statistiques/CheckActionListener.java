import java.awt.event.* ;
import javax.swing.* ;
import java.awt.* ;

public class CheckActionListener implements ActionListener
{
	JComboBox<String> jour2 ;
	JComboBox<String> mois2 ;
	JComboBox<String> annee2 ;

	JCheckBox periode ;

	public CheckActionListener(JComboBox<String> jour2_, JComboBox<String> mois2_, JComboBox<String> annee2_, JCheckBox periode_)
	{
		jour2 = jour2_ ;
		mois2 = mois2_ ;
		annee2 = annee2_ ;

		periode = periode_ ;

	}

	public void actionPerformed(ActionEvent e)
	{
		String action =e.getActionCommand() ;

		if(periode.isSelected())
		{
			jour2.setEnabled(true) ;
			mois2.setEnabled(true) ;
			annee2.setEnabled(true) ;
		}
		else
		{
			jour2.setEnabled(false) ;
			mois2.setEnabled(false) ;
			annee2.setEnabled(false) ;
		}

	}
}