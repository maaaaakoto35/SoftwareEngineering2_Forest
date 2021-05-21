package forest;

import javax.swing.*;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.*;

public class SetFile extends JFrame implements ActionListener
{
    
    File file = null;
    
    SetFile()
    {
        JButton button = new JButton("ファイル選択");
        button.addActionListener(this);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        JFileChooser filechooser = new JFileChooser();
        
        int selected = filechooser.showOpenDialog(this);
        
        if (selected == JFileChooser.APPROVE_OPTION)
        {
            this.file = filechooser.getSelectedFile();
            System.out.println("ファイルが選択されました = "+file.getName());
        }
        else if (selected == JFileChooser.CANCEL_OPTION)
        {
            System.out.println("キャンセルされました。");
        }
        else if (selected == JFileChooser.ERROR_OPTION)
        {
            System.out.println("エラー又は取り消しがありました。");
        }
    }
    
    public File getFile()
    {
        return this.file;
    }
}
