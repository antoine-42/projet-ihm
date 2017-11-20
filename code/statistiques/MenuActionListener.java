import java.awt.event.* ;
import javax.swing.* ;
import java.awt.* ;

public class MenuActionListener implements ActionListener
{
	MenuDeroulant menu ;

	JComboBox<String> jour ;
	JComboBox<String> mois ;
	JComboBox<String> annee ;

	JComboBox<String> jour2 ;
	JComboBox<String> mois2 ;
	JComboBox<String> annee2 ;

	JCheckBox periode ;

	public MenuActionListener(MenuDeroulant menu_, JComboBox<String> jour_, JComboBox<String> mois_, JComboBox<String> annee_, JComboBox<String> jour2_, JComboBox<String> mois2_, JComboBox<String> annee2_, JCheckBox periode_)
	{
		menu = menu_ ;

		jour = jour_ ;
		mois = mois_ ;
		annee = annee_ ;

		jour2 = jour2_ ;
		mois2 = mois2_ ;
		annee2 = annee2_ ;

		periode = periode_ ;

	}

	public void actionPerformed(ActionEvent e)
	{
		String action =e.getActionCommand() ;

		if(action.equals("Valider"))
		{
			if(periode.isSelected())
			{
				menu.setDateSelected(jour.getSelectedItem(), mois.getSelectedItem(), annee.getSelectedItem(), jour2.getSelectedItem(), mois2.getSelectedItem(), annee2.getSelectedItem()) ;
				menu.getStatsPeriode() ;
			}
			else
			{
				menu.setDateSelected(jour.getSelectedItem(), mois.getSelectedItem(), annee.getSelectedItem()) ;
				menu.getStats() ;
			}


		}
	}
}