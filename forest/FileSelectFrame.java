package forest;

import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * File Slect Frame
 */
public class FileSelectFrame extends JFrame implements ActionListener
{
    /**
     * a file
     */
    File aFile;

    /**
     * selecting file frame
     */
    FileSelectFrame()
    {
        this.aFile = null;
        JButton aButton = new JButton("ファイル選択");
        aButton.addActionListener(this);
        JPanel aPanel = new JPanel();
        aPanel.add(aButton);
        this.setSize(300,200);
        this.setTitle("ファイル名を選択");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(aPanel, BorderLayout.PAGE_END);
        this.setVisible(true);
    }

    /**
     * perfoming
     * @param anActionEvent action event
     */
    public void actionPerformed(ActionEvent anActionEvent)
    {
        JFileChooser filechooser = new JFileChooser();

        int selected = filechooser.showOpenDialog(this);

        if (selected == JFileChooser.APPROVE_OPTION)
        {
            this.aFile = filechooser.getSelectedFile();
            System.out.println("ファイルが選択されました = "+this.aFile.getName());
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

    /**
     * getting file
     * @return a file
     */
    public File getFile()
    {
        return this.aFile;
    }
}
